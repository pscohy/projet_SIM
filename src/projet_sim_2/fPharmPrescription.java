/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet_sim_2;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.smartcardio.CardTerminal;
import be.belgium.eid.eidlib.BeID;
import be.belgium.eid.exceptions.EIDException;
import javax.smartcardio.CardException;
import java.util.List;
import javax.smartcardio.*;

/**
 *
 * @author philo
 */

public class fPharmPrescription extends javax.swing.JDialog {

    /**
     * A return status code - returned if Cancel button has been pressed
     */
    public static final int RET_CANCEL = 0;
    /**
     * A return status code - returned if OK button has been pressed
     */
    public static final int RET_OK = 1;

    /**
     * Creates new form fPharmAccueil
     */
    
    /**
     * Creates new form fPharmPrescription
     */
    private patient patient;
    private prescription prescription;
    private medicament medicament;
    private interactionBaseDonnees base;
    private ResultSetTableModel tableModel;
    private ArrayList <prescription> liste_prescription;
        
    
    
    public fPharmPrescription(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setTitle("Pharmacien");
        this.buttonDelivrer.setEnabled(false);
        this.textFieldID.setText("");
        
        this.patient = null;
        this.prescription = null;
        this.medicament = null;
        this.base = new interactionBaseDonnees();
        
       buttonDelivrer.setToolTipText("Aucune prescription sélectionnée");
       
       this.setResizable(true);
       
       this.tableModel = new ResultSetTableModel();
       this.tablePrescription.setModel(this.tableModel);
       //this.tableModel.setResultSet(this.getAllPrescription(this.patient.geteID()));
       

        // Close the dialog when Esc is pressed
        String cancelName = "cancel";
        InputMap inputMap = getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), cancelName);
        ActionMap actionMap = getRootPane().getActionMap();
        actionMap.put(cancelName, new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                doClose(RET_CANCEL);
            }
        });
    }
    
    private ResultSet getAllPrescription() throws SQLException{
        String sql = "SELECT p.pID, p.date_prescription, p.date_delivrance, p.delivre,  m.nom, m.mID,  m.quantite, p.posologie, m.generic, m.mah, m.pack_size, m.PharmFormFr, m.PackFr, m.DelivFr, m.ActSubsts  FROM prescription AS p, medicament AS m WHERE p.eID = ? AND p.mID=m.mID";
        PreparedStatement ps;
        java.sql.Connection c = projet_sim_2.Connection.getInstance().getConn();
        ps = c.prepareStatement(sql);
        ps.setLong(1, this.patient.geteID());
        ResultSet resultat = ps.executeQuery();
        return resultat;
    }

    private boolean prescriptionsNonDelivrees() throws SQLException{
        String sql = "SELECT delivre FROM prescription WHERE eID = ? AND delivre = 0";
        PreparedStatement ps;
        java.sql.Connection c = projet_sim_2.Connection.getInstance().getConn();
        ps = c.prepareStatement(sql);
        ps.setLong(1, this.patient.geteID());
        ResultSet resultat = ps.executeQuery();
        return resultat.next();
    }
    
    private void chercherPrescription(){
        if (this.patient == null){
            labelNomPatient.setText("Ce patient n'appartient pas à la base de données et n'a donc pas de prescription");
        }
        else{
            try {
                if (prescriptionsNonDelivrees()){
                    this.buttonDelivrer.setEnabled(true);
                    buttonDelivrer.setToolTipText("");
                }
            } catch (SQLException ex) {
                Logger.getLogger(fPharmAccueil.class.getName()).log(Level.SEVERE, null, ex);
            }
            String prenom = this.patient.getPrenom();
            String nom = this.patient.getNom();
            labelNomPatient.setText("Le patient s'appelle "+ prenom + " " + nom);
            try {
                ResultSet resultat = this.getAllPrescription();
                this.tableModel.setResultSet(resultat);
            } catch (SQLException ex) {
                Logger.getLogger(fPharmAccueil.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
            
    
    /**
     * @return the return status of this dialog - one of RET_OK or RET_CANCEL
     */
    public int getReturnStatus() {
        return returnStatus;
    }
    
    private void refresh(){  
        this.labelNomPatient.setText("");
        this.labelCard.setText("");
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonDelivrer = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        buttonChercher = new javax.swing.JButton();
        scrolePanelTable = new javax.swing.JScrollPane();
        tablePrescription = new javax.swing.JTable();
        labeleID = new javax.swing.JLabel();
        labelNomPatient = new javax.swing.JLabel();
        buttonLecteur = new javax.swing.JButton();
        labelCard = new javax.swing.JLabel();
        textFieldID = new javax.swing.JTextField();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        buttonDelivrer.setText("Délivrer");
        buttonDelivrer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDelivrerActionPerformed(evt);
            }
        });

        cancelButton.setText("Fermer");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        buttonChercher.setText("Chercher");
        buttonChercher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonChercherActionPerformed(evt);
            }
        });

        tablePrescription.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        scrolePanelTable.setViewportView(tablePrescription);

        labeleID.setText("eID");

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
                .addContainerGap()
                .addComponent(labeleID)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(scrolePanelTable, javax.swing.GroupLayout.PREFERRED_SIZE, 1243, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(textFieldID, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(48, 48, 48)
                                .addComponent(buttonChercher)
                                .addGap(74, 74, 74)
                                .addComponent(buttonLecteur)
                                .addGap(26, 26, 26)
                                .addComponent(labelCard, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(labelNomPatient, javax.swing.GroupLayout.PREFERRED_SIZE, 609, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(buttonDelivrer, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 448, Short.MAX_VALUE)
                        .addComponent(cancelButton)
                        .addGap(1551, 1551, 1551))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labeleID)
                        .addComponent(buttonChercher)
                        .addComponent(buttonLecteur)
                        .addComponent(textFieldID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(labelCard, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelNomPatient, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrolePanelTable, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cancelButton)
                    .addComponent(buttonDelivrer))
                .addGap(18, 18, 18))
        );

        getRootPane().setDefaultButton(buttonDelivrer);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonDelivrerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDelivrerActionPerformed
        Date date = new Date();
        SimpleDateFormat date_ajd = new SimpleDateFormat("yyyy/MM/dd");
        String date_delivre = date_ajd.format(date);
        
        int pID = (int) this.tableModel.getValueAt(this.tablePrescription.getSelectedRow(), 0);
        String mID = (String) this.tableModel.getValueAt(this.tablePrescription.getSelectedRow(), 5);
        
        try {
            this.medicament = this.base.getMedicament(mID);
        } catch (SQLException ex) {
            Logger.getLogger(fPharmPrescription.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(fPharmPrescription.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            this.prescription = base.getPrescription(pID);
        } catch (SQLException ex) {
            Logger.getLogger(fPharmPrescription.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(fPharmPrescription.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        int quantite = this.medicament.getQuantite();
        
        this.prescription.setDate_delivrance(date_delivre);
        this.prescription.setDelivre(true);
        this.medicament.setQuantite(quantite-1);
        try {
            this.base.updatePrescription(this.prescription);
        } catch (SQLException ex) {
            Logger.getLogger(fPharmPrescription.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            this.base.updateMedicament(this.medicament);
        } catch (SQLException ex) {
            Logger.getLogger(fPharmPrescription.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            this.tableModel.setResultSet(this.getAllPrescription());
        } catch (SQLException ex) {
            Logger.getLogger(fPharmPrescription.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.refresh();
    }//GEN-LAST:event_buttonDelivrerActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        doClose(RET_CANCEL);
    }//GEN-LAST:event_cancelButtonActionPerformed

    /**
     * Closes the dialog
     */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        doClose(RET_CANCEL);
    }//GEN-LAST:event_closeDialog

    private void buttonChercherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonChercherActionPerformed
        this.refresh();
        try {
            String textID = this.textFieldID.getText();
            long eID = Long.parseLong(textID);
            //long eID = Long.parseLong(textID);
            this.patient = this.base.getPatient(eID);
        } catch (SQLException ex) {
            Logger.getLogger(fPharmPrescription.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(fPharmPrescription.class.getName()).log(Level.SEVERE, null, ex);
        }
        chercherPrescription();
    }//GEN-LAST:event_buttonChercherActionPerformed

    
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
                    this.patient = this.base.getPatient(eID);
                } catch (SQLException ex) {
                    Logger.getLogger(fPharmPrescription.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(fPharmPrescription.class.getName()).log(Level.SEVERE, null, ex);
                }  
                this.textFieldID.setText(textID);
                chercherPrescription();
            }
            else{
                labelCard.setText("Il n'y a pas de carte");
            }
        }
        catch (CardException | EIDException ex) {
            Logger.getLogger(fPharmAccueil.class.getName()).log(Level.SEVERE, null, ex);
        }

    
    }                                             
//GEN-LAST:event_buttonLecteurActionPerformed
        
    private void doClose(int retStatus) {
        returnStatus = retStatus;
        setVisible(false);
        dispose();
    }

    /**
     * @param args the command line arguments
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
            java.util.logging.Logger.getLogger(fPharmPrescription.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(fPharmPrescription.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(fPharmPrescription.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(fPharmPrescription.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                fPharmPrescription dialog = new fPharmPrescription(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonChercher;
    private javax.swing.JButton buttonDelivrer;
    private javax.swing.JButton buttonLecteur;
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel labelCard;
    private javax.swing.JLabel labelNomPatient;
    private javax.swing.JLabel labeleID;
    private javax.swing.JScrollPane scrolePanelTable;
    private javax.swing.JTable tablePrescription;
    private javax.swing.JTextField textFieldID;
    // End of variables declaration//GEN-END:variables

    private int returnStatus = RET_CANCEL;
}
