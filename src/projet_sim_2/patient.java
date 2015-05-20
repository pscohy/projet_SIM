/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet_sim_2;


/**
 *
 * @author INFO-H-400
 */
public class patient implements Ipatient {
    
    String nom, prenom,adresse;
    String date_naissance;
    long eID;
    
    public patient(){
        nom = "Proutprout";
        prenom = "Jean-Michel";
        adresse = "rue des cerises 43, 6150 Proutville";
        date_naissance = "1993/04/03";
        eID = 456789;
    }
    
    
    public patient(long eID, String nom, String prenom, String adresse, String date_naissance){
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.date_naissance = date_naissance;
        this.eID = eID;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getDate_naissance() {
        return date_naissance;
    }

    public long geteID() {
        return eID;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setDate_naissance(String date_naissance) {
        this.date_naissance = date_naissance;
    }

    public void seteID(long eID) {
        this.eID = eID;
    }


}
