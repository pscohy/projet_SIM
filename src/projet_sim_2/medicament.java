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
public class medicament {
    String mID, nom, mah, generic, pack_size, PharmFormFr, PackFr, DelivFr, ActSubsts;
    int quantite;
    //mID Clé primaire. Quantite pour le stock. generic 0 si pas generic, different si generic
    

    public medicament(String mID, String nom, String mah, String generic, String pack_size, String PharmFormFr, String PackFr, String DelivFr, String ActSubsts, int quantite){
        this.mID = mID;
        this.nom = nom;
        this.mah = mah;
        this.generic = generic;
        this.pack_size = pack_size;
        this.PharmFormFr = PharmFormFr;
        this.PackFr = PackFr;
        this.DelivFr = DelivFr;
        this.ActSubsts = ActSubsts;
        this.quantite = quantite;
    }

    public void setmID(String mID) {
        this.mID = mID;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setMah(String mah) {
        this.mah = mah;
    }

    public void setGeneric(String generic) {
        this.generic = generic;
    }

    public void setPack_size(String pack_size) {
        this.pack_size = pack_size;
    }

    public void setPharmFormFr(String PharmFormFr) {
        this.PharmFormFr = PharmFormFr;
    }

    public void setPackFr(String PackFr) {
        this.PackFr = PackFr;
    }

    public void setDelivFr(String DelivFr) {
        this.DelivFr = DelivFr;
    }

    public void setActSubsts(String ActSubsts) {
        this.ActSubsts = ActSubsts;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getmID() {
        return mID;
    }

    public String getNom() {
        return nom;
    }

    public String getMah() {
        return mah;
    }

    public String getGeneric() {
        return generic;
    }

    public String getPack_size() {
        return pack_size;
    }

    public String getPharmFormFr() {
        return PharmFormFr;
    }

    public String getPackFr() {
        return PackFr;
    }

    public String getDelivFr() {
        return DelivFr;
    }

    public String getActSubsts() {
        return ActSubsts;
    }

    public int getQuantite() {
        return this.quantite;
    }

   

    
    
}