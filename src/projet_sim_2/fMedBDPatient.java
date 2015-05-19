/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet_sim_2;

import com.toedter.calendar.JDateChooser;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

/**
 *
 * @author Madli
 */
public class fMedBDPatient extends javax.swing.JDialog {

    /**
     * A return status code - returned if Cancel button has been pressed
     */
    public static final int RET_CANCEL = 0;
    /**
     * A return status code - returned if OK button has been pressed
     */
    public static final int RET_OK = 1;

    /**
     * Creates new form fMedBDPatient
     */
    private patient p;
    public fMedBDPatient(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.p = null;
        this.setTitle("Boîte de dialogue patient à l'usage d'un médecin");
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
    
    /**
     * @return the return status of this dialog - one of RET_OK or RET_CANCEL
     */
    public int getReturnStatus() {
        return returnStatus;
    }
    
    public void setPatient(patient p){
        this.p = p;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        lblID = new javax.swing.JLabel();
        lblNom = new javax.swing.JLabel();
        lblPrenom = new javax.swing.JLabel();
        lblDateDeNaissance = new javax.swing.JLabel();
        lblAdresse = new javax.swing.JLabel();
        spinID = new javax.swing.JSpinner();
        tfNom = new javax.swing.JTextField();
        tfPrenom = new javax.swing.JTextField();
        dcDateDeNaissance = new com.toedter.calendar.JDateChooser();
        tfAdresse = new javax.swing.JTextField();
        btnPrescriptions = new javax.swing.JButton();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        okButton.setText("Ok");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        lblID.setText("ID :");

        lblNom.setText("Nom :");

        lblPrenom.setText("Prénom :");

        lblDateDeNaissance.setText("Date de naissance :");

        lblAdresse.setText("Adresse :");

        tfNom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNomActionPerformed(evt);
            }
        });

        btnPrescriptions.setText("Consulter les prescriptions");
        btnPrescriptions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrescriptionsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblID)
                        .addComponent(lblAdresse)
                        .addComponent(lblDateDeNaissance)
                        .addComponent(lblPrenom)
                        .addComponent(lblNom))
                    .addComponent(btnPrescriptions))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(okButton, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelButton))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(spinID, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(tfNom, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfPrenom, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dcDateDeNaissance, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                            .addComponent(tfAdresse, javax.swing.GroupLayout.Alignment.LEADING))))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cancelButton, okButton});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblID)
                            .addComponent(spinID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(lblNom))
                    .addComponent(tfNom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPrenom)
                    .addComponent(tfPrenom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDateDeNaissance)
                    .addComponent(dcDateDeNaissance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblAdresse)
                        .addGap(0, 55, Short.MAX_VALUE))
                    .addComponent(tfAdresse))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton)
                    .addComponent(okButton)
                    .addComponent(btnPrescriptions))
                .addContainerGap())
        );

        getRootPane().setDefaultButton(okButton);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void save () throws SQLException{
        this.p.seteID((int) this.spinID.getValue());
        this.p.setNom(this.tfNom.getText());
        this.p.setPrenom(this.tfPrenom.getText());
        this.p.setAdresse(this.tfAdresse.getText()); 
        String resultat = String.format("%1$td/%1$tm/%1$tY",this.dcDateDeNaissance.getDate());
        this.p.setDate_naissance(resultat);
        interactionBaseDonnees a = new interactionBaseDonnees();
        a.updatePatient(this.p);    
    }
    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        try {
            this.save();
        } catch (SQLException ex) {
            Logger.getLogger(fMedBDPatient.class.getName()).log(Level.SEVERE, null, ex);
        }
        doClose(RET_OK);
    }//GEN-LAST:event_okButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        doClose(RET_CANCEL);
    }//GEN-LAST:event_cancelButtonActionPerformed

    /**
     * Closes the dialog
     */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        doClose(RET_CANCEL);
    }//GEN-LAST:event_closeDialog

    private void tfNomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNomActionPerformed

    private void btnPrescriptionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrescriptionsActionPerformed
        // TODO add your handling code here:
        MedPrescriptions d;
        try {
            d = new MedPrescriptions((int)this.spinID.getValue());
            d.setVisible(true);
            this.dispose();
            d.setAlwaysOnTop(true);
        } catch (SQLException ex) {
            Logger.getLogger(fMedBDPatient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_btnPrescriptionsActionPerformed
    
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
            java.util.logging.Logger.getLogger(fMedBDPatient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(fMedBDPatient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(fMedBDPatient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(fMedBDPatient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                fMedBDPatient dialog = new fMedBDPatient(new javax.swing.JFrame(), true);
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
    
    public JDateChooser getDcDateDeNaissance(){
        return this.dcDateDeNaissance;
    }
    public JTextField getTfAdresse(){
        return this.tfAdresse;
    }
    public JTextField getTfNom(){
        return this.tfNom;
    }
    public JTextField getTfPrenom(){
        return this.tfPrenom;
    }
    public JSpinner getSpinID(){
        return this.spinID;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPrescriptions;
    private javax.swing.JButton cancelButton;
    private com.toedter.calendar.JDateChooser dcDateDeNaissance;
    private javax.swing.JLabel lblAdresse;
    private javax.swing.JLabel lblDateDeNaissance;
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblNom;
    private javax.swing.JLabel lblPrenom;
    private javax.swing.JButton okButton;
    private javax.swing.JSpinner spinID;
    private javax.swing.JTextField tfAdresse;
    private javax.swing.JTextField tfNom;
    private javax.swing.JTextField tfPrenom;
    // End of variables declaration//GEN-END:variables

    private int returnStatus = RET_CANCEL;
}
