/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet_sim_2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author philo
 */
public class actionPrescription {

    public actionPrescription() {
    }
 
    
    public Connection connect (){
        String driver ="com.mysql.jdbc.Driver";
        String userName = "root";
        String password = "";
        //String url = "jdbc:mysql://localhost:3306/projetsim?zeroDateTimeBehavior=convertToNull";
        String url = "jdbc:mysql://localhost:3306/projetsim";
        try {
        try {
        Class.forName (driver).newInstance ();
        } catch (InstantiationException ex) {
        Logger.getLogger(Projet_SIM_2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
        Logger.getLogger(Projet_SIM_2.class.getName()).log(Level.SEVERE, null, ex);
        }
        } catch (ClassNotFoundException ex) {
        Logger.getLogger(Projet_SIM_2.class.getName()).log(Level.SEVERE, null, ex);
        }
        Connection conn = null;
        try {
        conn = DriverManager.getConnection (url, userName, password);
        } catch (SQLException ex) {
        Logger.getLogger(Projet_SIM_2.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }
    
    
}
