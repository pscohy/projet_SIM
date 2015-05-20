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
import java.util.ArrayList;
        

/**
 *
 * @author Madli
 */
public class interactionBaseDonnees {
    
    public interactionBaseDonnees(){
    }
        
    public patient getPatient (long eID) throws SQLException, ParseException{
        String sql = "SELECT eID, nom, prenom, adresse,date_de_naissance FROM patient WHERE eID = ?";
        PreparedStatement ps;
        Connection c = projet_sim_2.Connection.getInstance().getConn();
        ps = c.prepareStatement(sql);
        ps.setLong(1, eID);
        ResultSet resultat = ps.executeQuery();
        patient patient = null;
        if (!resultat.next()){
            System.out.println("Patient non existant");
            return patient;
        }
        else{
            patient = new patient(resultat.getLong("eID"), resultat.getString("nom"),resultat.getString("prenom"), resultat.getString("adresse"), resultat.getString("date_de_naissance") );
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
            }
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
            prescription = new prescription(resultat.getInt("pID"),resultat.getString("mID"), resultat.getLong("eID"), resultat.getInt("inami"), resultat.getString("posologie"), resultat.getString("date_prescription"),resultat.getString("date_delivrance"),resultat.getBoolean("delivre") );
            
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

            return prescription;
		}
    }
        
        public ArrayList<prescription> getPrescriptionPatient (long eID) throws SQLException, ParseException{
        String sql = "SELECT pID, mID, eID, inami, posologie, date_prescription, date_delivrance, delivre FROM prescription WHERE eID = ?";
        PreparedStatement ps;
        Connection c = projet_sim_2.Connection.getInstance().getConn();
        ps = c.prepareStatement(sql);
        ps.setLong(1, eID);
        ResultSet resultat = ps.executeQuery();
        prescription prescription = null;
        ArrayList <prescription> liste_prescription = new ArrayList<prescription>();
        if (!resultat.next()){
            System.out.println("Pas de prescription");
            return liste_prescription;
        }
        else{
            while (resultat.next()){
                prescription = new prescription(resultat.getInt("pID"),resultat.getString("mID"), resultat.getLong("eID"), resultat.getInt("inami"), resultat.getString("posologie"), resultat.getString("date_prescription"),resultat.getString("date_delivrance"),resultat.getBoolean("delivre") );
                liste_prescription.add(prescription);
                
                
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
            }
            return liste_prescription;
		}
    }
     

        public medicament getMedicament (String mID) throws SQLException, ParseException{
            String sql = "SELECT mID, nom, mah, generic, pack_size, PharmFormFr, PackFr, DelivFr, ActSubsts, quantite FROM medicament WHERE mID = ?";
            PreparedStatement ps;
            Connection c = projet_sim_2.Connection.getInstance().getConn();
            ps = c.prepareStatement(sql);
            ps.setString(1, mID);
            ResultSet resultat = ps.executeQuery();
            medicament medicament = null;
            if (!resultat.next()){
                System.out.println("Medicament non existant");
                return medicament;
            }
            else{
                medicament = new medicament(resultat.getString("mID"),resultat.getString("nom"), resultat.getString("mah"), resultat.getString("generic"), resultat.getString("pack_size"), resultat.getString("PharmFormFr"),resultat.getString("PackFr"), resultat.getString("DelivFr"), resultat.getString("ActSubsts"),resultat.getInt("quantite")); 
         
                if (medicament.getmID()== null){
                    medicament.setmID("");
                }
                if (medicament.getNom()== null){
                    medicament.setNom("");
                }
                
                if (medicament.getMah()== null){
                    medicament.setMah("");
                }
                if (medicament.getGeneric()== null){
                    medicament.setGeneric("");
                }
                
                if (medicament.getPack_size()== null){
                    medicament.setPack_size("");
                }
               
                if (medicament.getPharmFormFr()==null){
                    medicament.setPharmFormFr("");
                }
            
                if (medicament.getPackFr()==null){
                    medicament.setPackFr("");
                }
            
                if (medicament.getDelivFr()==null){
                    medicament.setDelivFr("");
                }
                if (medicament.getActSubsts()== null){
                    medicament.setActSubsts("");
                }
//Plus utile Ã  partir du moment oÃ¹ l'on crÃ©e des patients via l'interface.
                return medicament;
                }
        }

    public patient createPatient(long eID) throws SQLException{
        String sql = "INSERT INTO patient (eID) VALUES (?)";
        PreparedStatement ps;
        Connection c = projet_sim_2.Connection.getInstance().getConn();
        ps = c.prepareStatement(sql);
        ps.setLong(1, eID);
        int statut = ps.executeUpdate(); 
        patient patient = new patient (eID,"", "", "",""); 
        return patient;    
    }
    
    public prescription createPrescription(int pID) throws SQLException{
        String sql = "INSERT INTO prescription (pID) VALUES (?)";
        PreparedStatement ps;
        Connection c = projet_sim_2.Connection.getInstance().getConn();
        ps = c.prepareStatement(sql);
        ps.setInt(1, pID);
        int statut = ps.executeUpdate(); 
        prescription prescription = new prescription (pID,"",0,0 ,"", "", "", false); 
        return prescription;
    }
    
    public medicament createMedicament(String mID) throws SQLException{
        String sql = "INSERT INTO medicament (mID) VALUES (?)";
        PreparedStatement ps;
        Connection c = projet_sim_2.Connection.getInstance().getConn();
        ps = c.prepareStatement(sql);
        ps.setString(1, mID);
        int statut = ps.executeUpdate(); 
        medicament medicament = new medicament(mID, "", "", "", "", "", "", "", "", 0); 
        return medicament;
        
    }
    
    public void removePatient(long eID) throws SQLException{
        String sql = "DELETE FROM patient WHERE eID=?";
        PreparedStatement ps;
        patient patient = null;
        Connection c = projet_sim_2.Connection.getInstance().getConn();
        ps = c.prepareStatement(sql);
        ps.setLong(1, eID);
        int statut = ps.executeUpdate(); 
    }

    
    public void removePrescription(int pID) throws SQLException{
        String sql = "DELETE FROM prescription WHERE pID=?";
        PreparedStatement ps;
        prescription prescription = null;
        interactionBaseDonnees base = new interactionBaseDonnees();
        Connection c = projet_sim_2.Connection.getInstance().getConn();
        ps = c.prepareStatement(sql);
        ps.setInt(1, pID);
        int statut = ps.executeUpdate(); 
    }
    
    public void removeMedicament(String mID) throws SQLException{
        String sql = "DELETE FROM medicament WHERE mID=?";
        PreparedStatement ps;
        medicament medicament = null;
        interactionBaseDonnees base = new interactionBaseDonnees();
        Connection c = projet_sim_2.Connection.getInstance().getConn();
        ps = c.prepareStatement(sql);
        ps.setString(1, mID);
        int statut = ps.executeUpdate(); 
    }
    
    public void updatePatient(patient patient) throws SQLException{
        String sql = "UPDATE patient SET nom=?,prenom=?,adresse=?,date_de_naissance=? WHERE eID=?";
        PreparedStatement ps;
        interactionBaseDonnees base = new interactionBaseDonnees();
        Connection c = projet_sim_2.Connection.getInstance().getConn();
        ps = c.prepareStatement(sql);
        ps.setString(1, patient.getNom());
        ps.setString(2, patient.getPrenom());
        ps.setString(3, patient.getAdresse());
        ps.setString(4, patient.getDate_naissance());
        ps.setLong(5, patient.geteID());
        int statut = ps.executeUpdate();
    }
    
    
    public void updatePrescription(prescription p) throws SQLException{
        String sql = "UPDATE prescription SET mID=?, eID=?, inami=?, posologie=?, date_prescription=?, date_delivrance=?, delivre=? WHERE pID=?";
        PreparedStatement ps;
        interactionBaseDonnees base = new interactionBaseDonnees();
        Connection c = projet_sim_2.Connection.getInstance().getConn();
        ps = c.prepareStatement(sql);
        ps.setString(1, p.getmID());
        ps.setLong(2, p.geteID());
        ps.setInt(3, p.getInami());
        ps.setString(4, p.getPosologie());
        ps.setString (5, p.getDate_prescription());
        ps.setString (6, p.getDate_delivrance());
        ps.setBoolean(7, p.getDelivre());
        ps.setInt(8, p.getpID());
        int statut = ps.executeUpdate();
    }
    
    public void updateMedicament(medicament m) throws SQLException{
//medicament(String mID, String nom, String mah, String generic, String pack_size, String PharmFormFr, String PackFr, String DelivFr, String ActSubsts, int quantite)

        String sql = "UPDATE medicament SET nom=?,mah=?, generic=?, pack_size=?, PharmFormFr=?, PackFr=?, DelivFr=?, ActSubsts=?, quantite=? WHERE mID=?";
        PreparedStatement ps;
        interactionBaseDonnees base = new interactionBaseDonnees();
        Connection c = projet_sim_2.Connection.getInstance().getConn();
        ps = c.prepareStatement(sql);
        ps.setString(1, m.getNom());
        ps.setString(2, m.getMah());
        ps.setString(3, m.getGeneric());
        ps.setString(4, m.getPack_size());
        ps.setString(5, m.getPharmFormFr());
        ps.setString(6,m.getPackFr());
        ps.setString(7, m.getDelivFr());
        ps.setString (8, m.getActSubsts());
        ps.setInt(9, m.getQuantite());
        ps.setString(10, m.getmID()); 
        int statut = ps.executeUpdate();
    }
    
    public ArrayList getAllPrescription() throws SQLException{
        String sql = "SELECT * FROM prescription";
        PreparedStatement ps;
        Connection c = projet_sim_2.Connection.getInstance().getConn();
        ps = c.prepareStatement(sql);
        ResultSet resultat = ps.executeQuery();
        ArrayList prescriptions = new ArrayList();
        while (resultat.next()){
            prescriptions.add(new prescription(resultat.getInt("pID"),resultat.getString("mID"), resultat.getLong("eID"), resultat.getInt("inami"), resultat.getString("posologie"), resultat.getString("date_prescription"),resultat.getString("date_delivrance"),resultat.getBoolean("delivre")));
        }
        return prescriptions;
    }
    
}

