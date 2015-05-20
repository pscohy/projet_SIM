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
import javax.swing.JFrame;
import javax.swing.KeyStroke;

/**
 *
 * @author philo
 */

public class fPharmStock extends javax.swing.JDialog {

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
    private ResultSet resultatTable;
    private java.awt.Frame fenetre_precedente;
        
    
    
    public fPharmStock(java.awt.Frame parent, boolean modal) throws SQLException {
        super(parent, modal);
        initComponents();
        this.setTitle("Stock du pharmacien");
        this.fenetre_precedente = parent;
        
        this.patient = null;
        this.prescription = null;
        this.medicament = null;
        this.base = new interactionBaseDonnees();
        this.buttonGenerique.setEnabled(false);
        this.buttonTout.setEnabled(false);
        this.buttonOriginaux.setEnabled(false);
        
        this.buttonGenerique.setToolTipText("Aucune substance active mentionnée");
        this.buttonTout.setToolTipText("Aucune substance active mentionnée");
        this.buttonOriginaux.setToolTipText("Aucune substance active mentionnée");
       
       this.setResizable(true);
       
       this.tableModel = new ResultSetTableModel();
       this.tableMedicament.setModel(this.tableModel);
       this.tableModel.setResultSet(this.getAllMedicament());

       

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
    
    //a modifier
    private ResultSet getAllMedicament() throws SQLException{
        String sql = "SELECT mID,quantite, nom, mah, generic, pack_size, PharmFormFr, PackFr, DelivFr, ActSubsts FROM medicament";
        PreparedStatement ps;
        java.sql.Connection c = projet_sim_2.Connection.getInstance().getConn();
        ps = c.prepareStatement(sql);
        ResultSet resultat = ps.executeQuery();
        return resultat;
    }

            
    
    /**
     * @return the return status of this dialog - one of RET_OK or RET_CANCEL
     */
    public int getReturnStatus() {
        return returnStatus;
    }
    
    private void refresh(){
        this.spinnerQuantite.setValue(0);
        if (this.textFieldActSub.getText().length() !=0){
            try {
                String ActSubsts = this.textFieldActSub.getText();
                String sql = "SELECT mID,quantite, nom, mah, generic, pack_size, PharmFormFr, PackFr, DelivFr, ActSubsts FROM medicament WHERE ActSubsts LIKE '%"+ActSubsts+"%'";
                PreparedStatement ps;
                java.sql.Connection c = projet_sim_2.Connection.getInstance().getConn();
                ps = c.prepareStatement(sql);
                this.resultatTable = ps.executeQuery();
                this.tableModel.setResultSet(this.resultatTable);
            } catch (SQLException ex) {
              Logger.getLogger(fPharmStock.class.getName()).log(Level.SEVERE, null, ex);
          }
        }
          else if (this.textFieldMedicament.getText().length() !=0){
            try {
                String nom = this.textFieldMedicament.getText();
                String sql = "SELECT mID,quantite, nom, mah, generic, pack_size, PharmFormFr, PackFr, DelivFr, ActSubsts FROM medicament WHERE nom LIKE '%"+nom+"%'";
                PreparedStatement ps;
                java.sql.Connection c = projet_sim_2.Connection.getInstance().getConn();
                ps = c.prepareStatement(sql);
                this.resultatTable = ps.executeQuery();
                this.tableModel.setResultSet(this.resultatTable);
            } catch (SQLException ex) {
                Logger.getLogger(fPharmStock.class.getName()).log(Level.SEVERE, null, ex);
            }
           }
          else{
            try {
                this.tableModel.setResultSet(this.getAllMedicament());
            } catch (SQLException ex) {
                Logger.getLogger(fPharmStock.class.getName()).log(Level.SEVERE, null, ex);
            }
          }
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonAjouter = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        scrolePanelTable = new javax.swing.JScrollPane();
        tableMedicament = new javax.swing.JTable();
        textFieldMedicament = new javax.swing.JTextField();
        labelMedicament = new javax.swing.JLabel();
        buttonGenerique = new javax.swing.JButton();
        buttonOriginaux = new javax.swing.JButton();
        buttonTout = new javax.swing.JButton();
        labelQuantite = new javax.swing.JLabel();
        spinnerQuantite = new javax.swing.JSpinner();
        labelActSub = new javax.swing.JLabel();
        textFieldActSub = new javax.swing.JTextField();
        buttonTotal = new javax.swing.JToggleButton();
        buttonRetour = new javax.swing.JButton();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        buttonAjouter.setText("Ajouter");
        buttonAjouter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAjouterActionPerformed(evt);
            }
        });

        cancelButton.setText("Fermer");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        tableMedicament.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        scrolePanelTable.setViewportView(tableMedicament);

        textFieldMedicament.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                textFieldMedicamentCaretUpdate(evt);
            }
        });
        textFieldMedicament.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldMedicamentActionPerformed(evt);
            }
        });

        labelMedicament.setText("Médicament:");

        buttonGenerique.setText("Génériques");
        buttonGenerique.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGeneriqueActionPerformed(evt);
            }
        });

        buttonOriginaux.setText("Originaux");
        buttonOriginaux.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonOriginauxActionPerformed(evt);
            }
        });

        buttonTout.setText("Tout");
        buttonTout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonToutActionPerformed(evt);
            }
        });

        labelQuantite.setText("quantité");

        labelActSub.setText("Substance active");

        textFieldActSub.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                textFieldActSubCaretUpdate(evt);
            }
        });
        textFieldActSub.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldActSubActionPerformed(evt);
            }
        });

        buttonTotal.setText("Au Total");
        buttonTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonTotalActionPerformed(evt);
            }
        });

        buttonRetour.setText("Retour");
        buttonRetour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRetourActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(scrolePanelTable, javax.swing.GroupLayout.DEFAULT_SIZE, 1243, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addComponent(labelMedicament)
                                .addGap(18, 18, 18)
                                .addComponent(textFieldMedicament, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(82, 82, 82)
                                .addComponent(labelActSub)
                                .addGap(28, 28, 28)
                                .addComponent(textFieldActSub, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(146, 146, 146)
                                .addComponent(buttonOriginaux)
                                .addGap(96, 96, 96)
                                .addComponent(buttonGenerique)
                                .addGap(162, 162, 162)
                                .addComponent(buttonTout))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(labelQuantite)
                                .addGap(30, 30, 30)
                                .addComponent(spinnerQuantite, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(buttonAjouter, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(buttonTotal))
                                .addGap(219, 219, 219)
                                .addComponent(buttonRetour)
                                .addGap(68, 68, 68)
                                .addComponent(cancelButton)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(977, 977, 977))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFieldMedicament, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelMedicament)
                    .addComponent(labelActSub)
                    .addComponent(textFieldActSub, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonTout)
                    .addComponent(buttonOriginaux)
                    .addComponent(buttonGenerique))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrolePanelTable, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                .addGap(78, 78, 78)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cancelButton)
                                .addComponent(buttonRetour))
                            .addGap(22, 22, 22))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(spinnerQuantite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(labelQuantite))
                            .addGap(41, 41, 41)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonAjouter)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonTotal)
                        .addGap(22, 22, 22))))
        );

        getRootPane().setDefaultButton(buttonAjouter);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonAjouterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAjouterActionPerformed
            
      String mID = (String) this.tableModel.getValueAt(this.tableMedicament.getSelectedRow(), 0);
      medicament medicament;
        try {
            medicament = this.base.getMedicament(mID);
            int quantite = medicament.getQuantite();
            int ajout = (int) this.spinnerQuantite.getValue();
            quantite = quantite +ajout;
      
            medicament.setQuantite(quantite);
      
            this.base.updateMedicament(medicament);
        } catch (SQLException ex) {
            Logger.getLogger(fPharmStock.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(fPharmStock.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.refresh();
        
    }//GEN-LAST:event_buttonAjouterActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        doClose(RET_CANCEL);
    }//GEN-LAST:event_cancelButtonActionPerformed

    /**
     * Closes the dialog
     */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        doClose(RET_CANCEL);
    }//GEN-LAST:event_closeDialog

    private void textFieldMedicamentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldMedicamentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldMedicamentActionPerformed

    private void buttonGeneriqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGeneriqueActionPerformed
        try {
            String ActSubsts = this.textFieldActSub.getText();
            String sql = "SELECT mID,quantite, nom, mah, generic, pack_size, PharmFormFr, PackFr, DelivFr, ActSubsts FROM medicament WHERE ActSubsts LIKE '%"+ActSubsts+"%' AND (generic!='0' AND generic !='')";
            PreparedStatement ps;
            java.sql.Connection c = projet_sim_2.Connection.getInstance().getConn();
            ps = c.prepareStatement(sql);
            ResultSet resultat = ps.executeQuery();
            this.tableModel.setResultSet(resultat);
        } catch (SQLException ex) {
            Logger.getLogger(fPharmStock.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonGeneriqueActionPerformed

    private void buttonToutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonToutActionPerformed
        try {
            this.tableModel.setResultSet(this.resultatTable);
        } catch (SQLException ex) {
            Logger.getLogger(fPharmStock.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonToutActionPerformed

    private void textFieldActSubActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldActSubActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldActSubActionPerformed

    private void textFieldMedicamentCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_textFieldMedicamentCaretUpdate
        this.textFieldActSub.setText("");

        this.buttonGenerique.setEnabled(false);
        this.buttonTout.setEnabled(false);
        this.buttonOriginaux.setEnabled(false);

        this.buttonGenerique.setToolTipText("Aucune substance active mentionnée");
        this.buttonTout.setToolTipText("Aucune substance active mentionnée");
        this.buttonOriginaux.setToolTipText("Aucune substance active mentionnée");
 
        String nom = this.textFieldMedicament.getText();
        String sql = "SELECT mID,quantite, nom, mah, generic, pack_size, PharmFormFr, PackFr, DelivFr, ActSubsts FROM medicament WHERE nom LIKE '%"+nom+"%'";
        PreparedStatement ps;
        java.sql.Connection c = projet_sim_2.Connection.getInstance().getConn();
        try {
            ps = c.prepareStatement(sql);
            this.resultatTable = ps.executeQuery();
            this.tableModel.setResultSet(this.resultatTable);
        } catch (SQLException ex) {
            Logger.getLogger(fMedPrescriptions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_textFieldMedicamentCaretUpdate

    private void textFieldActSubCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_textFieldActSubCaretUpdate
        this.textFieldMedicament.setText("");
        
        if (this.textFieldActSub.getText().length()==0){
            this.buttonGenerique.setEnabled(false);
            this.buttonTout.setEnabled(false);
            this.buttonOriginaux.setEnabled(false);

            this.buttonGenerique.setToolTipText("Aucune substance active mentionnée");
            this.buttonTout.setToolTipText("Aucune substance active mentionnée");
            this.buttonOriginaux.setToolTipText("Aucune substance active mentionnée");
        }
        
        else{
            this.buttonGenerique.setEnabled(true);
            this.buttonTout.setEnabled(true);
            this.buttonOriginaux.setEnabled(true);

            this.buttonGenerique.setToolTipText("");
            this.buttonTout.setToolTipText("");
            this.buttonOriginaux.setToolTipText("");
        }
        String ActSubsts = this.textFieldActSub.getText();
        String sql = "SELECT mID,quantite, nom, mah, generic, pack_size, PharmFormFr, PackFr, DelivFr, ActSubsts FROM medicament WHERE ActSubsts LIKE '%"+ActSubsts+"%'";
        PreparedStatement ps;
        java.sql.Connection c = projet_sim_2.Connection.getInstance().getConn();
        try {
            ps = c.prepareStatement(sql);
            this.resultatTable = ps.executeQuery();
            this.tableModel.setResultSet(this.resultatTable);
        } catch (SQLException ex) {
            Logger.getLogger(fMedPrescriptions.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_textFieldActSubCaretUpdate

    private void buttonOriginauxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonOriginauxActionPerformed
        try {
            String ActSubsts = this.textFieldActSub.getText();
            String sql = "SELECT mID, quantite, nom, mah, generic, pack_size, PharmFormFr, PackFr, DelivFr, ActSubsts FROM medicament WHERE ActSubsts LIKE '%"+ActSubsts+"%' AND (generic='0' OR generic ='')";
            PreparedStatement ps;
            java.sql.Connection c = projet_sim_2.Connection.getInstance().getConn();
            ps = c.prepareStatement(sql);
            ResultSet resultat = ps.executeQuery();
            this.tableModel.setResultSet(resultat);
        } catch (SQLException ex) {
            Logger.getLogger(fPharmStock.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_buttonOriginauxActionPerformed

    private void buttonTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTotalActionPerformed
        String mID = (String) this.tableModel.getValueAt(this.tableMedicament.getSelectedRow(), 0);
        medicament medicament;
        try {
            medicament = this.base.getMedicament(mID);
            int quantite = (int) this.spinnerQuantite.getValue();      
            medicament.setQuantite(quantite);
      
            this.base.updateMedicament(medicament);
        } catch (SQLException ex) {
            Logger.getLogger(fPharmStock.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(fPharmStock.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.refresh();
    }//GEN-LAST:event_buttonTotalActionPerformed

    private void buttonRetourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRetourActionPerformed
        this.dispose();
        this.fenetre_precedente.setVisible(true);
    }//GEN-LAST:event_buttonRetourActionPerformed

        
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
        /*java.awt.EventQueue.invokeLater(new Runnable() {
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
        });*/
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAjouter;
    private javax.swing.JButton buttonGenerique;
    private javax.swing.JButton buttonOriginaux;
    private javax.swing.JButton buttonRetour;
    private javax.swing.JToggleButton buttonTotal;
    private javax.swing.JButton buttonTout;
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel labelActSub;
    private javax.swing.JLabel labelMedicament;
    private javax.swing.JLabel labelQuantite;
    private javax.swing.JScrollPane scrolePanelTable;
    private javax.swing.JSpinner spinnerQuantite;
    private javax.swing.JTable tableMedicament;
    private javax.swing.JTextField textFieldActSub;
    private javax.swing.JTextField textFieldMedicament;
    // End of variables declaration//GEN-END:variables

    private int returnStatus = RET_CANCEL;
}
