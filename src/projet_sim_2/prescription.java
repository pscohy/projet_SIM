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
    int pID, mID, eID,inami;
<<<<<<< HEAD
    String posologie;
    String date_prescription, date_delivrance;
=======
    String posologie, date_prescription, date_delivrance;
>>>>>>> 156116add2c76d43dd9b48a0c03e94e35f67a59e
    Boolean delivre;
    
    
    /*public prescription(){
        pID = 4;
        mID = 33;
        eID = 456789;
        inami = 17;
        posologie = "3x/jour";
        date_prescription = "2015/05/12";
        date_delivrance = "2015/05/14";
        delivre = false;
    }*/

    public prescription(int pID, int mID, int eID, int inami, String posologie, String date_prescription, String date_delivrance, Boolean delivre) {
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

    public int getmID() {
        return mID;
    }

    public int geteID() {
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

    public void setmID(int mID) {
        this.mID = mID;
    }

    public void seteID(int eID) {
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
