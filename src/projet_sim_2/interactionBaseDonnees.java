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
import projet_sim_2.patient;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
        

/**
 *
 * @author Madli
 */
public class interactionBaseDonnees {
    
    public interactionBaseDonnees(){
    }
    
    private Connection connect (){
        String driver ="com.mysql.jdbc.Driver";
        String userName = "root";
        String password = "1234";
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
    
    public patient getPatient (int eID) throws SQLException, ParseException{
        String sql = "SELECT nom, prenom, adresse,date_de_naissance, eID FROM patient WHERE eID = ?";
        PreparedStatement ps;
        patient patient = null;
        interactionBaseDonnees base = new interactionBaseDonnees();
        Connection c = base.connect();
        ps = c.prepareStatement(sql);
        ps.setInt(1, eID);
        ResultSet resultat = ps.executeQuery();
        if (!(resultat.next())){
            System.out.println("Patient non existant");
        }
        else {
            //while (resultat.next()){
                //p = new patient(resultat.getString("nom"),resultat.getString("prenom"), resultat.getString("adresse"), resultat.getDate("date_de_naissance"), resultat.getInt("eID") );
                System.out.println(resultat.getString("prenom"));
            //}
            
        }
        
        return patient;
        
    }
    
     public medicament getMedicament(int cti) throws SQLException{
        String sql = "SELECT cti, dosis, generic, pack_size, quantite, ActuSubs_Name, unit, mp_name, PharmFormFr, PackFr, DelivFr FROM medicament WHERE cti = ?";
        PreparedStatement ps;
        medicament medicament = null;
        interactionBaseDonnees base = new interactionBaseDonnees();
        Connection c = base.connect();
        ps = c.prepareStatement(sql);
        ps.setInt(1, cti);
        ResultSet resultat = ps.executeQuery();
        if (!(resultat.next())){
            System.out.println("medicament non existant");
        }
        else {
            //while (resultat.next()){
                //p = new patient(resultat.getString("nom"),resultat.getString("prenom"), resultat.getString("adresse"), resultat.getDate("date_de_naissance"), resultat.getInt("eID") ); //pq creer un nouveau patient si on le get???
                System.out.println(resultat.getString("mp_name"));
            //}
            
        }
        
        return medicament;     
    }
    
    public patient getPrescription (int pID) throws SQLException, ParseException{
        String sql = "SELECT pID, mID, eID, inami, posologie, date_prescription, date_delivrance, delivre FROM prescription WHERE pID = ?";
        PreparedStatement ps;
        patient patient = null;
        interactionBaseDonnees base = new interactionBaseDonnees();
        Connection c = base.connect();
        ps = c.prepareStatement(sql);
        ps.setInt(1, pID);
        ResultSet resultat = ps.executeQuery();
        if (!(resultat.next())){
            System.out.println("Prescription non existante");
        }
        else {
            //while (resultat.next()){
                //p = new patient(resultat.getString("nom"),resultat.getString("prenom"), resultat.getString("adresse"), resultat.getDate("date_de_naissance"), resultat.getInt("eID") );
                System.out.println(resultat.getString("posologie"));
            //}
            
        }
        return patient;
        
    }
    
    public void createPatient(int eID,String nom, String prenom, String adresse, Date date_de_naissance) throws SQLException{
        String sql = "INSERT INTO patient (nom, prenom, adresse, date_de_naissance, eID) VALUES (?,?,?,?,?)";
        PreparedStatement ps;
        interactionBaseDonnees base = new interactionBaseDonnees();
        Connection c = base.connect();
        ps = c.prepareStatement(sql);
        ps.setInt(1, eID);
        ps.setString(2, prenom);
        ps.setString(3, adresse);
        ps.setDate(4, date_de_naissance);
        ps.setString(5, nom);
        int statut = ps.executeUpdate();
        
    }
    
    public void createMedicament(int cti, int dosis, int generic, int pack_size, int quantite, String ActSubs_Name, String unit, String mp_name, String mah, String PharmFormFr, String PackFr, String DelivFr) throws SQLException{
        String sql = "INSERT INTO medicament (cti, dosis, generic, pack_size, quantite, ActSubs_Name, unit, mp_name, mah, PharmFormFr, PackFr, DelivFr) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps;
        interactionBaseDonnees base = new interactionBaseDonnees();
        Connection c = base.connect();
        ps = c.prepareStatement(sql);
        ps.setInt(1, cti);
        ps.setInt(2, dosis);
        ps.setInt(3, generic);
        ps.setInt(4, pack_size);
        ps.setInt(5, quantite);
        ps.setString(6,ActSubs_Name);
        ps.setString(7, unit);
        ps.setString (8, mp_name);
        ps.setString(9, mah);
        ps.setString(10, PharmFormFr);
        ps.setString(11, PackFr);
        ps.setString(12, DelivFr);        
        int statut = ps.executeUpdate();
        
    }
    
     public void createPrescription(int pID, int mID, int eID, int inami, String posologie, Date date_prescription, Date date_delivrance, Boolean delivre) throws SQLException{
        String sql = "INSERT INTO prescription (pID, mID, eID, inami, posologie, date_prescription, date_delivrance, delivre) VALUES (?,?,?,?,?,?,?,?)";
        PreparedStatement ps;
        interactionBaseDonnees base = new interactionBaseDonnees();
        Connection c = base.connect();
        ps = c.prepareStatement(sql);
        
        ps.setInt(1, pID);
        ps.setInt(2, mID);
        ps.setInt(3, eID);
        ps.setInt(4, inami);
        ps.setString(5, posologie);
        ps.setDate (6, date_prescription);
        ps.setDate(7, date_delivrance);
        ps.setBoolean(8, delivre);
        int statut = ps.executeUpdate();
        
    }
    
    public void removePatient(int eID) throws SQLException{
        String sql = "DELETE*FROM patient WHERE eID=?";
        PreparedStatement ps;
        patient p = null;
        interactionBaseDonnees base = new interactionBaseDonnees();
        Connection c = base.connect();
        ps = c.prepareStatement(sql);
        ps.setInt(1, eID);
        int statut = ps.executeUpdate(); 
    }
    
    public void removeMedicament(int cti) throws SQLException{
        String sql = "DELETE*FROM medicament WHERE cti=?";
        PreparedStatement ps;
        medicament m = null;
        interactionBaseDonnees base = new interactionBaseDonnees();
        Connection c = base.connect();
        ps = c.prepareStatement(sql);
        ps.setInt(1, cti);
        int statut = ps.executeUpdate(); 
    }
    
    public void removePrescription(int pID) throws SQLException{
        String sql = "DELETE*FROM prescription WHERE pID=?";
        PreparedStatement ps;
        prescription pr = null;
        interactionBaseDonnees base = new interactionBaseDonnees();
        Connection c = base.connect();
        ps = c.prepareStatement(sql);
        ps.setInt(1, pID);
        int statut = ps.executeUpdate(); 
    }
    
    public void updatePatient(int eID, String nom, String prenom, String adresse, Date date_de_naissance) throws SQLException{
        String sql = "UPDATE patient SET nom=?,prenom=?,adresse=?,date_de_naissance=? WHERE eID=?";
        PreparedStatement ps;
        patient p = null;
        interactionBaseDonnees base = new interactionBaseDonnees();
        Connection c = base.connect();
        ps = c.prepareStatement(sql);
        ps.setString(1, nom);
        ps.setString(2, prenom);
        ps.setString(3, adresse);
        ps.setDate(4, date_de_naissance);
        ps.setInt(5, eID);
        int statut = ps.executeUpdate();
    }
    
     public void updateMedicament(int cti, int dosis, int generic, int pack_size, int quantite, String ActSubs_Name, String unit, String mp_name, String mah, String PharmFormFr, String PackFr, String DelivFr) throws SQLException{
        String sql = "UPDATE patient SET dosis=?,generic=?,pack_size=?, quantite=?, ActSubs_Name =?, unit=?, mp_name=?, mah=?, PharmFormFr=?, PackFr =?, DelivFr =? WHERE eID=?";
        PreparedStatement ps;
        medicament m = null;
        interactionBaseDonnees base = new interactionBaseDonnees();
        Connection c = base.connect();
        ps = c.prepareStatement(sql);
        ps.setInt(1, cti);
        ps.setInt(2, dosis);
        ps.setInt(3, generic);
        ps.setInt(4, pack_size);
        ps.setInt(5, quantite);
        ps.setString(6,ActSubs_Name);
        ps.setString(7, unit);
        ps.setString (8, mp_name);
        ps.setString(9, mah);
        ps.setString(10, PharmFormFr);
        ps.setString(11, PackFr);
        ps.setString(12, DelivFr); 
        int statut = ps.executeUpdate();
    }
    
    public void updatePrescription(int pID, int mID, int eID, int inami, String posologie, Date date_prescription, Date date_delivrance, Boolean delivre) throws SQLException{
        String sql = "UPDATE patient SET mID=?, eID=?,inami=?,posologie=?, date_prescription=?, date_delivrance=?, delivre=? WHERE pID=?";
        PreparedStatement ps;
        prescription pr = null;
        interactionBaseDonnees base = new interactionBaseDonnees();
        Connection c = base.connect();
        ps = c.prepareStatement(sql);
        ps.setInt(1, pID);
        ps.setInt(2, mID);
        ps.setInt(3, eID);
        ps.setInt(4, inami);
        ps.setString(5, posologie);
        ps.setDate (6, date_prescription);
        ps.setDate(7, date_delivrance);
        ps.setBoolean(8, delivre);
        int statut = ps.executeUpdate();
    }
   
}

