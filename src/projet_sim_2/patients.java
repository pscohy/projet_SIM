/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet_sim_2;

import java.sql.Date;

/**
 *
 * @author INFO-H-400
 */
public class patients {
    
    String nom, prenom,adresse;
    Date date_naissance;
    int eID;
    
    public patients(){
        nom = "Proutprout";
        prenom = "Jean-Michel";
        adresse = "rue des cerises 43, 6150 Proutville";
        date_naissance = new java.sql.Date(1993,04,03);
        eID = 456789;
    }
    
    
    public patients(String nom, String prenom, String adresse, Date date_naissance, int eID){
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

    public Date getDate_naissance() {
        return date_naissance;
    }

    public int geteID() {
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

    public void setDate_naissance(Date date_naissance) {
        this.date_naissance = date_naissance;
    }

    public void seteID(int eID) {
        this.eID = eID;
    }


}
