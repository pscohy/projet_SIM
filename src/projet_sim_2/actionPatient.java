/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet_sim_2;
import static com.sun.javafx.fxml.expression.Expression.not;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import projet_sim_2.patients;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
        

/**
 *
 * @author Madli
 */
public class actionPatient {
    
    public actionPatient(){
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
    public patients getPatient (int eID) throws SQLException, ParseException{
        String sql = "SELECT nom, prenom, adresse,date_de_naissance, eID FROM patient WHERE eID = ?";
        PreparedStatement ps;
        patients p = null;
        actionPatient a = new actionPatient();
        Connection c = a.connect();
        ps = c.prepareStatement(sql);
        ps.setInt(1, eID);
        ResultSet resultat = ps.executeQuery();
        if (!(resultat.next())){
            System.out.println("Patient non existant");
        }
        else {
            //while (resultat.next()){
                //p = new patients(resultat.getString("nom"),resultat.getString("prenom"), resultat.getString("adresse"), resultat.getDate("date_de_naissance"), resultat.getInt("eID") );
                System.out.println(resultat.getString("prenom"));
            //}
            
        }
        //return p;
        
        return null;
        
    }
    
    public void createPatient(String nom, String prenom, String adresse, Date date_de_naissance, int eID) throws SQLException{
        String sql = "INSERT INTO patient (nom, prenom, adresse, date_de_naissance, eID) VALUES (?,?,?,?,?)";
        PreparedStatement ps;
        patients p = null;
        actionPatient a = new actionPatient();
        Connection c = a.connect();
        ps = c.prepareStatement(sql);
        ps.setString(1, nom);
        ps.setString(2, prenom);
        ps.setString(3, adresse);
        ps.setDate(4, date_de_naissance);
        ps.setInt(5, eID);
        int statut = ps.executeUpdate();
        
    }
    public void removePatient(int eID) throws SQLException{
        String sql = "DELETE*FROM patient WHERE eID=?";
        PreparedStatement ps;
        patients p = null;
        actionPatient a = new actionPatient();
        Connection c = a.connect();
        ps = c.prepareStatement(sql);
        ps.setInt(1, eID);
        int statut = ps.executeUpdate(); 
    }
    public void updatePatient(String nom, String prenom, String adresse, Date date_de_naissance, int eID) throws SQLException{
        String sql = "UPDATE patient SET nom=?,prenom=?,adresse=?,date_de_naissance=? WHERE eID=?";
        PreparedStatement ps;
        patients p = null;
        actionPatient a = new actionPatient();
        Connection c = a.connect();
        ps = c.prepareStatement(sql);
        ps.setString(1, nom);
        ps.setString(2, prenom);
        ps.setString(3, adresse);
        ps.setDate(4, date_de_naissance);
        ps.setInt(5, eID);
        int statut = ps.executeUpdate();
    }
}