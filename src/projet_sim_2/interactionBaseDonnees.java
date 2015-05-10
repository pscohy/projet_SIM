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
    
    public prescription getPrescription (int pID) throws SQLException, ParseException{
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
        String sql = "DELETE FROM patient WHERE eID=?";
        PreparedStatement ps;
        patients p = null;
        Connection c = projet_sim_2.Connection.getInstance().getConn();
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

