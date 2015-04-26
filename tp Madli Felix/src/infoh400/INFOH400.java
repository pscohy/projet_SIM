/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infoh400;

import be.belgium.eid.eidlib.BeID;
import be.belgium.eid.exceptions.EIDException;
import be.belgium.eid.objects.IDData;
import be.belgium.eid.objects.IDPhoto;
//import ca.uhn.hl7v2.HL7Exception;
//import ca.uhn.hl7v2.llp.LLPException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author INFO-H-400
 */
public class INFOH400 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws EIDException {
        
        
        /*new Dicom().setVisible(true);*/
       /* try {
            new HL7().createADTA01();*/
            
// TODO code application logic here
            new MainWindow().setVisible(true);
            String driver ="com.mysql.jdbc.Driver";
            String userName = "root";
            String password = "";
            String url = "jdbc:mysql://localhost:3306/projetsim";
            try {
            try {
            Class.forName (driver).newInstance ();
            } catch (InstantiationException ex) {
            Logger.getLogger(INFOH400.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
            Logger.getLogger(INFOH400.class.getName()).log(Level.SEVERE, null, ex);
            }
            } catch (ClassNotFoundException ex) {
            Logger.getLogger(INFOH400.class.getName()).log(Level.SEVERE, null, ex);
            }
            Connection conn;
            try {
            conn = DriverManager.getConnection (url, userName, password);
            
            String sql = "INSERT INTO patient(eID,nom,prenom) VALUES (?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, 1345);
            ps.setString(2, "Bayot");
            ps.setString(3, "Siloé");
            ps.executeUpdate();
            
            
            /*BeID b = new BeID(true);
            IDData data = b.getIDData();
            IDPhoto photo = b.getIDPhoto();
            System.out.println ("Mes données = " + data.get1stFirstname() + "," + data.getBirthDate() + "," + data.getName() );
            
            String sql = "INSERT INTO patient(nom,prenom,Image) VALUES (?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, data.getName());
            ps.setString(2, data.get1stFirstname());
            ps.setBytes(3, photo.getPhoto());
            ps.executeUpdate();*/
            
            
            
            
            /*Statement s;
            try {
            s = conn.createStatement ();
            //s.executeQuery ("INSERT  INTO Personne (Nom, Prenom) VALUES ('" + 
                    //data.getName()+"','"+data.get1stFirstname()+"')");
            s.executeQuery ("SELECT * FROM patient");
            ResultSet rs = s.getResultSet ();
            while (rs.next ())
            {
            int intVal = rs.getInt ("eID");
            String strVal = rs.getString ("nom");
            System.out.println ("Mon patient = " + intVal + ", " + strVal);
            }
            rs.close ();
            s.close ();
            
            } catch (SQLException ex) {
            Logger.getLogger(INFOH400.class.getName()).log(Level.SEVERE, null, ex);
            }*/
            } catch (SQLException ex) {
            Logger.getLogger(INFOH400.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        /*} catch (HL7Exception ex) {
            Logger.getLogger(INFOH400.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(INFOH400.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LLPException ex) {
            Logger.getLogger(INFOH400.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        
    }
    
}
