/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet_sim_2;

import be.belgium.eid.exceptions.EIDException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author INFO-H-400
 */
public class Projet_SIM_2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws EIDException, SQLException, ParseException{
        actionPatient a = new actionPatient();
        patients p = a.getPatient(2345);
        //String prenom = p.getPrenom();
        //System.out.println(prenom);
    }
    
}



    
