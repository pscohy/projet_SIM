/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infoh400;

/**
 *
 * @author INFO-H-400
 */
public class Patient {
    //CONSTRUCTEUR
    public Patient(String n, String p){
        nom = n;
        prenom = p;
    }
    
    private String nom;
    private String prenom;
    
    
    //ACCESSEURS
    public String getNom(){
        return nom;
    }
    public String getPrenom(){
        return prenom;
    }
    //MUTATEURS
    public void setNom(String n){
        nom = n;
    }
    public void setPrenom(String p){
        prenom = p;
    }
    
}
