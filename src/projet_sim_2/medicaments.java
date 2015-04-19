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
public class medicaments {
    
    String nom;
    int mID,quantite;
    
    
    public medicaments(){
        mID = 33;
        quantite = 5;
        nom = "reducteur_douleur";
    }
    
    public medicaments(int mId, int quantite, String nom ){
        mID = mID;
        quantite = quantite;
        nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public int getmID() {
        return mID;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setmID(int mID) {
        this.mID = mID;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
    
    public int diminueStock(int modif) {
        return quantite = quantite - modif;
    }
    
    public int augmenteStock(int modif) {
        return quantite = quantite + modif;
    }
}
