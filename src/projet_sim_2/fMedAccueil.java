/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet_sim_2;

import be.belgium.eid.eidlib.BeID;
import be.belgium.eid.exceptions.EIDException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.smartcardio.CardException;
import javax.smartcardio.CardTerminal;
import javax.smartcardio.TerminalFactory;

/**
 *
 * @author Madli
 */
public class fMedAccueil extends javax.swing.JFrame {

    /**
     * Creates new form fMedAccueil
     */
    private patient p;
    private interactionBaseDonnees a;
    public fMedAccueil() {
        initComponents();
        this.setTitle("Page d'accueil médecin");
        this.btnCreer.setEnabled(false);
        this.btnModifier.setEnabled(false);
        this.btnSupprimer.setEnabled(false);
        this.lblAppartenance.setText("");
        this.textFieldIDAccueil.setText("");
        this.p = null;
        this.a = new interactionBaseDonnees();
    }
    
    public patient getPatient(){
        return this.p;
    }
    
    public void refresh(){
        this.btnCreer.setEnabled(false);
        this.btnModifier.setEnabled(false);
        this.btnSupprimer.setEnabled(false);
        this.textFieldIDAccueil.setText("");
        this.lblAppartenance.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblID = new javax.swing.JLabel();
        btnChercher = new javax.swing.JButton();
        lblAppartenance = new javax.swing.JLabel();
        btnModifier = new javax.swing.JButton();
        btnCreer = new javax.swing.JButton();
        btnSupprimer = new javax.swing.JButton();
        buttonLecteur = new javax.swing.JButton();
        labelCard = new javax.swing.JLabel();
        textFieldIDAccueil = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblID.setText("ID du patient :");

        btnChercher.setText("Chercher");
        btnChercher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChercherActionPerformed(evt);
            }
        });

        btnModifier.setText("Modifier");
        btnModifier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModifierActionPerformed(evt);
            }
        });

        btnCreer.setText("Créer");
        btnCreer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreerActionPerformed(evt);
            }
        });

        btnSupprimer.setText("Supprimer");
        btnSupprimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSupprimerActionPerformed(evt);
            }
        });

        buttonLecteur.setText("Lecteur de carte");
        buttonLecteur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLecteurActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(btnModifier)
                        .addGap(68, 68, 68)
                        .addComponent(btnCreer)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                        .addComponent(btnSupprimer)
                        .addGap(30, 30, 30))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(lblAppartenance, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(btnChercher)
                                .addGap(61, 61, 61)
                                .addComponent(labelCard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblID)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(textFieldIDAccueil, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(buttonLecteur)))
                        .addGap(50, 50, 50))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblID)
                    .addComponent(buttonLecteur)
                    .addComponent(textFieldIDAccueil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnChercher, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelCard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(lblAppartenance, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnModifier)
                    .addComponent(btnCreer)
                    .addComponent(btnSupprimer))
                .addContainerGap(124, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSupprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSupprimerActionPerformed
        // TODO add your handling code here:
        try {
            String textID = this.textFieldIDAccueil.getText();
            long eID = Long.parseLong(textID);
            this.a.removePatient(eID);
        } catch (SQLException ex) {
            Logger.getLogger(fMedAccueil.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.refresh();
    }//GEN-LAST:event_btnSupprimerActionPerformed

    private void btnChercherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChercherActionPerformed
        // TODO add your handling code here:
        try {
            String textID = this.textFieldIDAccueil.getText();
            long eID = Long.parseLong(textID);
            this.p = this.a.getPatient(eID);
        } catch (SQLException ex) {
            Logger.getLogger(fMedAccueil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(fMedAccueil.class.getName()).log(Level.SEVERE, null, ex);
        }
        chercherPatient();
    }//GEN-LAST:event_btnChercherActionPerformed

    private void chercherPatient(){
        if (this.p == null){
            lblAppartenance.setText("Ce patient n'appartient pas encore à la base de données");
            btnCreer.setEnabled(true);
            btnModifier.setEnabled(false);
            btnSupprimer.setEnabled(false);
            btnModifier.setToolTipText("Toute modification est impossible, car le patient mentionné n'existe pas!");
            btnSupprimer.setToolTipText("Toute modification est impossible, car le patient mentionné n'existe pas!");
        }
        else{
            lblAppartenance.setText("Ce patient appartient déjà à la base de données");
            btnModifier.setEnabled(true);
            btnSupprimer.setEnabled(true);
            btnCreer.setEnabled(false);
            btnCreer.setToolTipText("Vous ne pouvez pas créer un patient déjà existant!");
        }
    }
    
    private void btnModifierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModifierActionPerformed
        // TODO add your handling code here:
        fMedBDPatient di = new fMedBDPatient(this , true,true);
        this.display(this.p, di);
        di.setPatient(p);
        di.setVisible(true);
        System.out.println(di.getReturnStatus());//1 = ok; 0 = cancel
        this.refresh();
    }//GEN-LAST:event_btnModifierActionPerformed

    private void btnCreerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreerActionPerformed
        // TODO add your handling code here:
        try {

            fMedBDPatient di = new fMedBDPatient(this , true,false);
            String textID = this.textFieldIDAccueil.getText();
            long eID = Long.parseLong(textID);
            this.p = a.createPatient(eID);
            this.display(this.p, di);
            di.setPatient(p);
            di.setVisible(true);
            System.out.println(di.getReturnStatus());//1 = ok; 0 = cancel
        } catch (SQLException ex) {
            Logger.getLogger(fMedAccueil.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.refresh();
        
    }//GEN-LAST:event_btnCreerActionPerformed

    private void buttonLecteurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLecteurActionPerformed
        TerminalFactory factory = TerminalFactory.getDefault();
        try {
            List<CardTerminal> terminals = factory.terminals().list();
            CardTerminal terminal = terminals.get(0);
            if (terminal.isCardPresent()){
                final BeID Carte = new BeID(false);
                String textID = Carte.getIDData().getNationalNumber();
                long eID = Long.valueOf(textID);
                try {
                    this.p = this.a.getPatient(eID);
                } catch (SQLException ex) {
                    Logger.getLogger(fPharmPrescription.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(fPharmPrescription.class.getName()).log(Level.SEVERE, null, ex);
                } 
                this.textFieldIDAccueil.setText(textID);
                chercherPatient();
            }
            else{
                labelCard.setText("Il n'y a pas de carte");
            }
        }
        catch (CardException | EIDException ex) {
            Logger.getLogger(fPharmAccueil.class.getName()).log(Level.SEVERE, null, ex);
        }

    
    }//GEN-LAST:event_buttonLecteurActionPerformed

    public void display (patient p, fMedBDPatient di){
        String textID = Long.toString(this.p.geteID());
        di.getTextFieldID().setText(textID);
        di.getTfNom().setText(this.p.getNom());
        di.getTfPrenom().setText(this.p.getPrenom());
        if (p.getDate_naissance() == ""){
            this.p.setDate_naissance("1/01/1800");
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
        try {
            java.util.Date d = (java.util.Date) sdf.parse(p.getDate_naissance());
            di.getDcDateDeNaissance().setDate(d);
        } catch (ParseException ex) {
            Logger.getLogger(fMedAccueil.class.getName()).log(Level.SEVERE, null, ex);
        }
        di.getTfAdresse().setText(this.p.getAdresse());
    }

     /* @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(fMedAccueil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(fMedAccueil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(fMedAccueil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(fMedAccueil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new fMedAccueil().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChercher;
    private javax.swing.JButton btnCreer;
    private javax.swing.JButton btnModifier;
    private javax.swing.JButton btnSupprimer;
    private javax.swing.JButton buttonLecteur;
    private javax.swing.JLabel labelCard;
    private javax.swing.JLabel lblAppartenance;
    private javax.swing.JLabel lblID;
    private javax.swing.JTextField textFieldIDAccueil;
    // End of variables declaration//GEN-END:variables
}
