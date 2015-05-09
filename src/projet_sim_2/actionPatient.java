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
   
    public patients getPatient (int eID) throws SQLException, ParseException{
        String sql = "SELECT nom, prenom, adresse,date_de_naissance, eID FROM patient WHERE eID = ?";
        PreparedStatement ps;
        Connection c = projet_sim_2.Connection.getInstance().getConn();
        ps = c.prepareStatement(sql);
        ps.setInt(1, eID);
        ResultSet resultat = ps.executeQuery();
        patients p = null;
        if (!resultat.next()){
            System.out.println("Patient non existant");
            return p;
        }
        else{
            p = new patients(resultat.getString("nom"),resultat.getString("prenom"), resultat.getString("adresse"), resultat.getString("date_de_naissance"), resultat.getInt("eID") );
            if (p.getNom()== null){
                p.setNom("");
            }
            if (p.getPrenom()== null){
                p.setPrenom("");
            }
            if (p.getAdresse()== null){
                p.setAdresse("");
            }
            if (p.getDate_naissance() == null){
                p.setDate_naissance("");
            }//Plus utile à partir du moment où l'on crée des patients via l'interface.
            return p;
        }
    }
    
    public patients createPatient(int eID) throws SQLException{
        String sql = "INSERT INTO patient (eID) VALUES (?)";
        PreparedStatement ps;
        Connection c = projet_sim_2.Connection.getInstance().getConn();
        ps = c.prepareStatement(sql);
        ps.setInt(1, eID);
        int statut = ps.executeUpdate(); 
        patients p = new patients ("", "", "","", eID); 
        return p;    
    }
    
    public void removePatient(int eID) throws SQLException{
        String sql = "DELETE FROM patient WHERE eID=?";
        PreparedStatement ps;
        patients p = null;
        Connection c = projet_sim_2.Connection.getInstance().getConn();
        ps = c.prepareStatement(sql);
        ps.setInt(1, eID);
        int statut = ps.executeUpdate(); 
    }
    
    public void updatePatient(patients p) throws SQLException{
        String sql = "UPDATE patient SET nom=?,prenom=?,adresse=?,date_de_naissance=? WHERE eID=?";
        PreparedStatement ps;
        Connection c = projet_sim_2.Connection.getInstance().getConn();
        ps = c.prepareStatement(sql);
        ps.setString(1, p.getNom());
        ps.setString(2, p.getPrenom());
        ps.setString(3, p.getAdresse());
        ps.setString(4, p.getDate_naissance());
        ps.setInt(5, p.geteID());
        int statut = ps.executeUpdate();
    }
}
