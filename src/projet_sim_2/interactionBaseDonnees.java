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
        
    public patient getPatient (int eID) throws SQLException, ParseException{
        String sql = "SELECT eID, nom, prenom, adresse,date_de_naissance FROM patient WHERE eID = ?";
        PreparedStatement ps;
        Connection c = projet_sim_2.Connection.getInstance().getConn();
        ps = c.prepareStatement(sql);
        ps.setInt(1, eID);
        ResultSet resultat = ps.executeQuery();
        patient patient = null;
        if (!resultat.next()){
            System.out.println("Patient non existant");
            return patient;
        }
        else{
            patient = new patient(resultat.getInt("eID"), resultat.getString("nom"),resultat.getString("prenom"), resultat.getString("adresse"), resultat.getString("date_de_naissance") );
            if (patient.getNom()== null){
                patient.setNom("");
            }
            if (patient.getPrenom()== null){
                patient.setPrenom("");
            }
            if (patient.getAdresse()== null){
                patient.setAdresse("");
            }
            if (patient.getDate_naissance() == null){
                patient.setDate_naissance("");
            }//Plus utile à partir du moment où l'on crée des patients via l'interface.
            return patient;
		}
    }
    
        public prescription getPrescription (int pID) throws SQLException, ParseException{
        String sql = "SELECT pID, mID, eID, inami, posologie, date_prescription, date_delivrance, delivre FROM prescription WHERE pID = ?";
        PreparedStatement ps;
        Connection c = projet_sim_2.Connection.getInstance().getConn();
        ps = c.prepareStatement(sql);
        ps.setInt(1, pID);
        ResultSet resultat = ps.executeQuery();
        prescription prescription = null;
        if (!resultat.next()){
            System.out.println("Prescription non existante");
            return prescription;
        }
        else{
            prescription = new prescription(resultat.getInt("pID"),resultat.getInt("mID"), resultat.getInt("eID"), resultat.getInt("inami"), resultat.getString("posologie"), resultat.getString("date_prescription"),resultat.getString("date_delivrance"),resultat.getBoolean("delivre") );
            /*if (p.getmID()== null){
                p.setmID(0);
            }
            if (p.geteID()== null){
                p.seteID(0);
            }
            if (p.getInami()== null){
                p.setInami(0);
            }*/
            if (prescription.getPosologie() == null){
                prescription.setPosologie("");
            }
            if (prescription.getDate_prescription() == null){
                prescription.setDate_prescription("");
            }
            if (prescription.getDate_delivrance() == null){
                prescription.setDate_delivrance("");
            }
            if (prescription.getDelivre() == null){
                prescription.setDelivre(false);
            }
//Plus utile à partir du moment où l'on crée des patients via l'interface.
            return prescription;
		}
    }
     
        /* public medicament getMedicament (int cti) throws SQLException, ParseException{
        String sql = "SELECT cti, dosis, generic, pack_size, quantite, ActuSubs_Name, unit, mp_name, PharmFormFr, PackFr, DelivFr FROM medicament WHERE cti = ?";
        PreparedStatement ps;
        Connection c = projet_sim_2.Connection.getInstance().getConn();
        ps = c.prepareStatement(sql);
        ps.setInt(1, cti);
        ResultSet resultat = ps.executeQuery();
        medicament medicament = null;
        if (!resultat.next()){
            System.out.println("Medicament non existante");
            return medicament;
        }
        else{
            medicament = new medicament(resultat.getInt("cti"),resultat.getInt("dosis"), resultat.getInt("generic"), resultat.getInt("pack_size"), resultat.getInt("quantite"), resultat.getString("ActSubs_Name"),resultat.getString("unit"), resultat.getString("mp_name"), resultat.getString("mah"),resultat.getString("PharmFormFr"),resultat.getString("PackFr"), resultat.getString("DelivFr") ); 
            if (p.getmID()== null){
                p.setmID(0);
            }
            if (p.geteID()== null){
                p.seteID(0);
            }
            if (p.getInami()== null){
                p.setInami(0);
            }
            if (p.getPosologie() == null){
                p.setPosologie("");
            }
            if (p.getDate_prescription() == null){
                p.setDate_prescription("");
            }
            if (p.getDate_delivrance() == null){
                p.setDate_delivrance("");
            }
            if (p.getDelivre() == null){
                p.setDelivre(false);
            }
//Plus utile à partir du moment où l'on crée des patients via l'interface.
            return p;
		}
    } */
        
    
     
    
    public patient createPatient(int eID) throws SQLException{
        String sql = "INSERT INTO patient (eID) VALUES (?)";
        PreparedStatement ps;
        Connection c = projet_sim_2.Connection.getInstance().getConn();
        ps = c.prepareStatement(sql);
        ps.setInt(1, eID);
        int statut = ps.executeUpdate(); 
        patient patient = new patient (eID,"", "", "",""); 
        return patient;    
    }

    
    /*public void createMedicament(int cti, int dosis, int generic, int pack_size, int quantite, String ActSubs_Name, String unit, String mp_name, String mah, String PharmFormFr, String PackFr, String DelivFr) throws SQLException{
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
        
    }*/
    
    public void removePatient(int eID) throws SQLException{
        String sql = "DELETE FROM patient WHERE eID=?";
        PreparedStatement ps;
        patient patient = null;
        Connection c = projet_sim_2.Connection.getInstance().getConn();
        ps = c.prepareStatement(sql);
        ps.setInt(1, eID);
        int statut = ps.executeUpdate(); 
    }

    
    /*public void removeMedicament(int cti) throws SQLException{
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
    }*/
    
    public void updatePatient(patient patient) throws SQLException{
        String sql = "UPDATE patient SET nom=?,prenom=?,adresse=?,date_de_naissance=? WHERE eID=?";
        PreparedStatement ps;
        Connection c = projet_sim_2.Connection.getInstance().getConn();
        ps = c.prepareStatement(sql);
        ps.setString(1, patient.getNom());
        ps.setString(2, patient.getPrenom());
        ps.setString(3, patient.getAdresse());
        ps.setString(4, patient.getDate_naissance());
        ps.setInt(5, patient.geteID());
        int statut = ps.executeUpdate();
    }

    
     /*public void updateMedicament(int cti, int dosis, int generic, int pack_size, int quantite, String ActSubs_Name, String unit, String mp_name, String mah, String PharmFormFr, String PackFr, String DelivFr) throws SQLException{
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
    }*/
   
}

