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
public class prescription {
    long eID;
    int pID,inami;
    String posologie, date_prescription, date_delivrance, mID;
    Boolean delivre;

    public prescription(int pID, String mID, long eID, int inami, String posologie, String date_prescription, String date_delivrance, Boolean delivre) {
        this.pID = pID;
        this.mID = mID;
        this.eID = eID;
        this.inami = inami;
        this.posologie = posologie;
        this.date_prescription = date_prescription;
        this.date_delivrance = date_delivrance;
        this.delivre = delivre;
    }

    public int getpID() {
        return pID;
    }

    public String getmID() {
        return mID;
    }

    public long geteID() {
        return eID;
    }

    public int getInami() {
        return inami;
    }

    public String getPosologie() {
        return posologie;
    }

    public String getDate_prescription() {
        return date_prescription;
    }

    public String getDate_delivrance() {
        return date_delivrance;
    }

    public Boolean getDelivre() {
        return delivre;
    }

    public void setpID(int pID) {
        this.pID = pID;
    }

    public void setmID(String mID) {
        this.mID = mID;
    }

    public void seteID(long eID) {
        this.eID = eID;
    }

    public void setInami(int inami) {
        this.inami = inami;
    }

    public void setPosologie(String posologie) {
        this.posologie = posologie;
    }

    public void setDate_prescription(String date_prescription) {
        this.date_prescription = date_prescription;
    }

    public void setDate_delivrance(String date_delivrance) {
        this.date_delivrance = date_delivrance;
    }

    public void setDelivre(Boolean delivre) {
        this.delivre = delivre;
    }
    
    
    
}
