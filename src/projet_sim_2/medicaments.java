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
    
    int cti, dosis, generic, pack_size, quantite; //Cti Clé primaire. Quantite pour le stock. generic 0 si pas generic, different si generic
    String ActSubs_Name, unit, mp_name, mah, PharmFormFr, PackFr, DelivFr;//unit a associer avec l'attribut dosis

    public medicaments(int cti, int dosis, int generic, int pack_size, int quantite, String ActSubs_Name, String unit, String mp_name, String mah, String PharmFormFr, String PackFr, String DelivFr) {
        this.cti = cti;
        this.dosis = dosis;
        this.generic = generic;
        this.pack_size = pack_size;
        this.quantite = quantite;
        this.ActSubs_Name = ActSubs_Name;
        this.unit = unit;
        this.mp_name = mp_name;
        this.mah = mah;
        this.PharmFormFr = PharmFormFr;
        this.PackFr = PackFr;
        this.DelivFr = DelivFr;
    }

    public int getCti() {
        return cti;
    }

    public void setCti(int cti) {
        this.cti = cti;
    }

    public int getDosis() {
        return dosis;
    }

    public void setDosis(int dosis) {
        this.dosis = dosis;
    }

    public int getGeneric() {
        return generic;
    }

    public void setGeneric(int generic) {
        this.generic = generic;
    }

    public int getPack_size() {
        return pack_size;
    }

    public void setPack_size(int pack_size) {
        this.pack_size = pack_size;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getActSubs_Name() {
        return ActSubs_Name;
    }

    public void setActSubs_Name(String ActSubs_Name) {
        this.ActSubs_Name = ActSubs_Name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getMp_name() {
        return mp_name;
    }

    public void setMp_name(String mp_name) {
        this.mp_name = mp_name;
    }

    public String getMah() {
        return mah;
    }

    public void setMah(String mah) {
        this.mah = mah;
    }

    public String getPharmFormFr() {
        return PharmFormFr;
    }

    public void setPharmFormFr(String PharmFormFr) {
        this.PharmFormFr = PharmFormFr;
    }

    public String getPackFr() {
        return PackFr;
    }

    public void setPackFr(String PackFr) {
        this.PackFr = PackFr;
    }

    public String getDelivFr() {
        return DelivFr;
    }

    public void setDelivFr(String DelivFr) {
        this.DelivFr = DelivFr;
    }

    
    
}