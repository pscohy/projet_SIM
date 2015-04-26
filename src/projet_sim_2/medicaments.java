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
    int mID,quantite, DPP;
    
    
    public medicaments(){
        mID = 33;
        quantite = 5;
        nom = "reducteur_douleur";
        DPP = 12;
    }
    
    public medicaments(int mId, int quantite, int DPP, String nom ){
        mID = mID;
        quantite = quantite;
        nom = nom;
        DPP = DPP;
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
    
    public int getDPP() {
        return DPP;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setmID(int mID) {
        this.mID = mID;
    }
    
    public void setDPP(int DPP) {
        this.DPP = DPP;
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
