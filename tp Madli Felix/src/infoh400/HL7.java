/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infoh400;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.app.Connection;
import ca.uhn.hl7v2.app.ConnectionHub;
import ca.uhn.hl7v2.app.Initiator;
import ca.uhn.hl7v2.llp.LLPException;
import ca.uhn.hl7v2.llp.MinLowerLayerProtocol;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.v24.message.ADT_A01;
import ca.uhn.hl7v2.model.v24.segment.MSH;
import ca.uhn.hl7v2.model.v24.segment.PID;
import ca.uhn.hl7v2.parser.DefaultXMLParser;
import ca.uhn.hl7v2.parser.Parser;
import ca.uhn.hl7v2.parser.PipeParser;
import java.io.IOException;

/**
 *
 * @author Schenkel Arnaud
 */
 
public class HL7 {
    
    public HL7() {
    }  

    void createADTA01() throws HL7Exception, IOException, LLPException
	{
            ADT_A01 adt = new ADT_A01();
		// Populate the MSH Segment
		MSH mshSegment = adt.getMSH();
		mshSegment.getFieldSeparator().setValue("|");
		mshSegment.getEncodingCharacters().setValue("^~\\&");
		mshSegment.getDateTimeOfMessage().getTimeOfAnEvent().setValue("200701011539");
		mshSegment.getSendingApplication().getNamespaceID().setValue("TestSendingSystem");
                mshSegment.getMessageControlID().setValue("Message");
                mshSegment.getProcessingID().getProcessingID().setValue("Processing");
		mshSegment.getSequenceNumber().setValue("123");
		mshSegment.getMessageType().getMessageType().setValue("ADT");
		mshSegment.getMessageType().getTriggerEvent().setValue("A01");
		mshSegment.getMessageType().getMessageStructure().setValue("ADT A01");
		mshSegment.getVersionID().getVersionID().setValue("2.4");

		// Populate the PID Segment
		PID pid = adt.getPID(); 
		pid.getPatientName(0).getFamilyName().getSurname().setValue("Doe");
		pid.getPatientName(0).getGivenName().setValue("John");
		pid.getPatientIdentifierList(0).getID().setValue("123456");

		/*
		* Complete with more informations
		*/

		Parser parser = new PipeParser();
		String encodedMessage = parser.encode(adt);
		System.out.println("Printing ER7 Encoded Message:");//Traduction en HL7, format "pipes".
		System.out.println(encodedMessage);

		ConnectionHub connectionHub = ConnectionHub.getInstance();

		// A connection object represents a socket attached to an HL7 server
		int port= 8889;
		Connection connection = connectionHub.attach( "localhost", port, new PipeParser(), MinLowerLayerProtocol.class);

		// The initiator is used to transmit unsolicited messages
		Initiator initiator = connection.getInitiator();
		Message response = initiator.sendAndReceive(adt);

		String responseString = parser.encode(response);
		System.out.println("Received response:\n" + responseString);

		// Close the connection and server
		connection.close();

		// Next, let's use the XML parser to encode as XML
		parser = new DefaultXMLParser();
		encodedMessage = parser.encode(adt);
		System.out.println("Printing XML Encoded Message:");//Traduction en HL7, format XML.
		System.out.println(encodedMessage);
    }
}

