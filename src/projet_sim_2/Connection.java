/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet_sim_2;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Madli
 */
/**
 * Implémentation simple d'un singleton.
 * L'instance est créée à l'initialisation. 
 */
public class Connection {
    private java.sql.Connection conn;
    /** Constructeur privé */
    private Connection()
    {
    String driver ="com.mysql.jdbc.Driver";
    //String userName = "root";
    String userName = "student";
    //String password = "";
    String password = "1234";
    //String url = "jdbc:mysql://localhost:3306/projet";
    String url = "jdbc:mysql://192.168.3.108:3306/projet";
    
    try {
    try {
    Class.forName (driver).newInstance ();
    } catch (InstantiationException | IllegalAccessException ex) {
    Logger.getLogger(Projet_SIM_2.class.getName()).log(Level.SEVERE, null, ex);
    }
    } catch (ClassNotFoundException ex) {
    Logger.getLogger(Projet_SIM_2.class.getName()).log(Level.SEVERE, null, ex);
    }
    try {
    this.conn = DriverManager.getConnection (url, userName, password);
    } catch (SQLException ex) {
    Logger.getLogger(Projet_SIM_2.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    
    public java.sql.Connection getConn() {
        return conn;
    }
    /** Instance unique pré-initialisée */
    private static Connection INSTANCE = new Connection();
 
    /** Point d'accès pour l'instance unique du singleton */
	
    public static Connection getInstance()
	
    {	return INSTANCE;
	
    }

    
}

