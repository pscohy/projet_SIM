/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet_sim_2;


import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Madli
 */
public class fMedPrescriptions extends javax.swing.JFrame {

    
    ResultSetTableModel m;
    ResultSetTableModel n;
    long eID;
    int pID;
    /**
     * Creates new form MedPrescriptions
     */
    public fMedPrescriptions(long eID) throws SQLException {
        initComponents();
        this. eID = eID;
        this.pID = 0;
        this.setTitle("Boîte de dialogue prescription à l'usage du médecin");
        this.m = new ResultSetTableModel();
        this.tabPrescriptions.setModel(this.m);
        this.m.setResultSet(this.getAllP());
        this.n = new ResultSetTableModel();
        this.tabMedicament.setModel(this.n);
        this.n.setResultSet(this.getAllM());
        this.btnModifier.setEnabled(false);
        this.btnSupprimer.setEnabled(false);
        this.btnAjouter.setEnabled(true);
        
    }
    
    public ResultSet getAllM() throws SQLException{
        String sql = "SELECT mID, nom, mah, generic, pack_size, PharmFormFr, PackFr, DelivFr, ActSubsts FROM medicament";
        PreparedStatement ps;
        java.sql.Connection c = projet_sim_2.Connection.getInstance().getConn();
        ps = c.prepareStatement(sql);
        ResultSet resultat = ps.executeQuery();
        return resultat;
    }
   
    @SuppressWarnings("null")
    public ResultSet getAllP() throws SQLException{
        String sql = "SELECT p.pID, p.eID, p.inami, p.date_prescription, p.date_delivrance, p.delivre,"
                + " m.mID, m.nom, m.mah, m.generic, m.pack_size, m.PharmFormFr, m.PackFr, m.DelivFr, m.ActSubsts,p.posologie "
                + "FROM prescription AS p, medicament AS m"
                + " WHERE p.eID =? and p.mID = m.mID";
        PreparedStatement ps;
        java.sql.Connection c = projet_sim_2.Connection.getInstance().getConn();
        ps = c.prepareStatement(sql);
        ps.setLong(1, this.eID);
        ResultSet resultat = ps.executeQuery();
        while(resultat.next()){ // Juste pour tests avec Workbench.
            int value = resultat.getInt("p.inami");
            boolean nullValue = resultat.wasNull();
            if (resultat.wasNull()){
                
            }
            if ((resultat.getString("p.posologie")== null )){
                this.changePosologie(resultat.getInt("p.pID"));
            }
        }
        String sql2 = "SELECT p.pID, p.eID, p.inami, p.date_prescription, p.date_delivrance, p.delivre,"
                + " m.mID, m.nom, m.mah, m.generic, m.pack_size, m.PharmFormFr, m.PackFr, m.DelivFr, m.ActSubsts,p.posologie "
                + "FROM prescription AS p, medicament AS m"
                + " WHERE p.eID =? and p.mID = m.mID";
        ps = c.prepareStatement(sql2);
        ps.setLong(1, this.eID);
        ResultSet resultat2 = ps.executeQuery();
        return resultat2;
    }
    
    public void changePosologie(int pIDt) throws SQLException{
        String sql = "UPDATE prescription SET posologie =?  WHERE pID =?";
        PreparedStatement ps;
        interactionBaseDonnees base = new interactionBaseDonnees();
        java.sql.Connection c = projet_sim_2.Connection.getInstance().getConn();
        ps = c.prepareStatement(sql);
        ps.setString(1, "");
        ps.setInt(2, pIDt);
        int statut = ps.executeUpdate();
    }
    
    public void changeInami(int pIDt) throws SQLException{
        String sql = "UPDATE prescription SET inami =?  WHERE pID =?";
        PreparedStatement ps;
        interactionBaseDonnees base = new interactionBaseDonnees();
        java.sql.Connection c = projet_sim_2.Connection.getInstance().getConn();
        ps = c.prepareStatement(sql);
        ps.setInt(1, 0);
        ps.setInt(2, pIDt);
        int statut = ps.executeUpdate();
    }
    
    public void display (int raw) throws ParseException, SQLException{
        this.spinInami.setValue((int) this.m.getValueAt(raw, 2));
        /*if (this.m.getValueAt(raw,3) == ""){
            
        }*/
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
        java.util.Date d = (java.util.Date) sdf.parse((String) this.m.getValueAt(raw,3));
        this.dcDateDePrescription.setDate(d);
        String sql = "SELECT mID, nom, mah, generic, pack_size, PharmFormFr, PackFr, DelivFr, ActSubsts FROM medicament WHERE mID =?";
        PreparedStatement ps;
        java.sql.Connection c = projet_sim_2.Connection.getInstance().getConn();
        ps = c.prepareStatement(sql);
        ps.setString(1,(String) this.m.getValueAt(raw,6));
        ResultSet resultat = ps.executeQuery();
        this.n.setResultSet(resultat);
        this.txtPosologie.setText((String) this.m.getValueAt(raw, 15));
    }
    
    public void create (int inami, String date_prescription, String mID, String posologie) throws SQLException{
        String sql = "INSERT INTO prescription (mID,eID,inami,posologie, date_prescription) VALUES (?,?,?,?,?)";
        PreparedStatement ps;
        java.sql.Connection c = projet_sim_2.Connection.getInstance().getConn();
        ps = c.prepareStatement(sql);
        ps.setString(1, mID);
        ps.setLong(2, this.eID);
        ps.setInt(3, inami);
        ps.setString(4,posologie);
        ps.setString(5,date_prescription);
        int statut = ps.executeUpdate();
    }
    public void refresh() throws SQLException{
        this.spinInami.setValue(0);
        this.dcDateDePrescription.setCalendar(null);
        this.tfMedicament.setText("");
        this.tabMedicament.changeSelection(0,0,false,false);
        this.n.setResultSet(this.getAllM());
        this.txtPosologie.setText("");
        this.btnAjouter.setEnabled(true);
        this.btnModifier.setEnabled(false);
        this.btnSupprimer.setEnabled(false);
        this.m.setResultSet(this.getAllP());
        
        
    }
    public void update (int inami, String date_prescription, String mID, String posologie) throws SQLException{
        String sql = "UPDATE prescription SET inami=?,date_prescription=?,mID=?,posologie=? WHERE pID=?";
        PreparedStatement ps;
        java.sql.Connection c = projet_sim_2.Connection.getInstance().getConn();
        ps = c.prepareStatement(sql);
        ps.setInt(1, inami);
        ps.setString(2, date_prescription);
        ps.setString(3, mID);
        ps.setString(4, posologie);
        ps.setInt(5, this.pID);
        int statut = ps.executeUpdate();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabPrescriptions = new javax.swing.JTable();
        btnModifier = new javax.swing.JButton();
        btnAjouter = new javax.swing.JButton();
        btnSupprimer = new javax.swing.JButton();
        lblInami = new javax.swing.JLabel();
        lblDateDePrescription = new javax.swing.JLabel();
        dcDateDePrescription = new com.toedter.calendar.JDateChooser();
        spinInami = new com.toedter.components.JSpinField();
        lblPosologie = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtPosologie = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabMedicament = new javax.swing.JTable();
        lblMedicament = new javax.swing.JLabel();
        tfMedicament = new javax.swing.JTextField();
        lblPrescriptions = new javax.swing.JLabel();
        lblErreur = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tabPrescriptions.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tabPrescriptions.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabPrescriptionsMouseClicked(evt);
            }
        });
        tabPrescriptions.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                tabPrescriptionsInputMethodTextChanged(evt);
            }
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                tabPrescriptionsCaretPositionChanged(evt);
            }
        });
        jScrollPane1.setViewportView(tabPrescriptions);

        btnModifier.setText("Modifier");
        btnModifier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModifierActionPerformed(evt);
            }
        });

        btnAjouter.setText("Ajouter");
        btnAjouter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAjouterActionPerformed(evt);
            }
        });

        btnSupprimer.setText("Supprimer");
        btnSupprimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSupprimerActionPerformed(evt);
            }
        });

        lblInami.setText("Numéro INAMI :");

        lblDateDePrescription.setText("Date de prescription :");

        dcDateDePrescription.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                dcDateDePrescriptionCaretPositionChanged(evt);
            }
        });

        spinInami.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                spinInamiCaretPositionChanged(evt);
            }
        });

        lblPosologie.setText("Posologie :");

        txtPosologie.setColumns(20);
        txtPosologie.setRows(5);
        txtPosologie.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                txtPosologieInputMethodTextChanged(evt);
            }
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                txtPosologieCaretPositionChanged(evt);
            }
        });
        jScrollPane2.setViewportView(txtPosologie);

        tabMedicament.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tabMedicament.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabMedicamentMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tabMedicament);

        lblMedicament.setText("Médicament :");

        tfMedicament.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                tfMedicamentCaretUpdate(evt);
            }
        });

        lblPrescriptions.setText("Prescriptions :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblMedicament, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblInami))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addGap(125, 125, 125)
                                                .addComponent(spinInami, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addComponent(btnAjouter)
                                                .addGap(87, 87, 87)
                                                .addComponent(btnModifier))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(131, 131, 131)
                                        .addComponent(tfMedicament))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblDateDePrescription)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(dcDateDePrescription, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(43, 43, 43)
                        .addComponent(btnSupprimer))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblPosologie, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblPrescriptions, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(lblErreur, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(spinInami, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblInami)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dcDateDePrescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDateDePrescription))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMedicament)
                    .addComponent(tfMedicament, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblPosologie)
                        .addGap(120, 120, 120))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAjouter)
                    .addComponent(btnModifier)
                    .addComponent(btnSupprimer))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblErreur, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(lblPrescriptions, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tabPrescriptionsInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_tabPrescriptionsInputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_tabPrescriptionsInputMethodTextChanged

    private void btnAjouterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAjouterActionPerformed
        // TODO add your handling code here:
        this.lblErreur.setText("");
        if ((this.spinInami.getValue() == 0)||(this.dcDateDePrescription.getDate() == null)||(this.tabMedicament.getSelectedRow()<0)||(this.txtPosologie.getText().length()==0)){ 
            this.lblErreur.setText("Attention, la prescription n'a pas été enregistrée en base de données car certains champs restent vides!");
        }
        else{
        String resultat = String.format("%1$td/%1$tm/%1$tY",this.dcDateDePrescription.getDate());
        try {
            this.create(this.spinInami.getValue(), resultat, (String) this.n.getValueAt(this.tabMedicament.getSelectedRow(),0), this.txtPosologie.getText());
        } catch (SQLException ex) {
            Logger.getLogger(fMedPrescriptions.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            this.refresh();
        } catch (SQLException ex) {
            Logger.getLogger(fMedPrescriptions.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }//GEN-LAST:event_btnAjouterActionPerformed

    private void btnSupprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSupprimerActionPerformed
        // TODO add your handling code here:
        //System.out.println((int) this.m.getValueAt(this.tabPrescriptions.getSelectedRow(), 0));
        this.lblErreur.setText("");
        interactionBaseDonnees i = new interactionBaseDonnees();
        
        try {
            i.removePrescription((int) this.m.getValueAt(this.tabPrescriptions.getSelectedRow(), 0));
        } catch (SQLException ex) {
            Logger.getLogger(fMedPrescriptions.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            this.refresh();
        } catch (SQLException ex) {
            Logger.getLogger(fMedPrescriptions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSupprimerActionPerformed

    private void tfMedicamentCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_tfMedicamentCaretUpdate
        // TODO add your handling code here:
        String nom = this.tfMedicament.getText();
        String sql = "SELECT mID, nom, mah, generic, pack_size, PharmFormFr, PackFr, DelivFr, ActSubsts FROM medicament WHERE nom LIKE '%"+nom+"%'";
        PreparedStatement ps;
        java.sql.Connection c = projet_sim_2.Connection.getInstance().getConn();
        try {
            ps = c.prepareStatement(sql);
            ResultSet resultat = ps.executeQuery();
            this.n.setResultSet(resultat);
        } catch (SQLException ex) {
            Logger.getLogger(fMedPrescriptions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tfMedicamentCaretUpdate

    private void btnModifierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModifierActionPerformed
        this.lblErreur.setText("");
        if ((this.spinInami.getValue() == 0)||(this.dcDateDePrescription.getDate() == null)||(this.n.getRowCount()!=1 && this.tabMedicament.getSelectedRow()<0)||(this.txtPosologie.getText().length()==0)){ 
            this.lblErreur.setText("Attention, la prescription n'a pas été enregistrée en base de données car certains champs restent vides!");
        }
        else{
        String resultat = String.format("%1$td/%1$tm/%1$tY",this.dcDateDePrescription.getDate());
        try {
            this.update(this.spinInami.getValue(), resultat, (String) this.n.getValueAt(0,0), this.txtPosologie.getText());
        } catch (SQLException ex) {
            Logger.getLogger(fMedPrescriptions.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            this.refresh();
        } catch (SQLException ex) {
            Logger.getLogger(fMedPrescriptions.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }//GEN-LAST:event_btnModifierActionPerformed

    private void tabPrescriptionsCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_tabPrescriptionsCaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_tabPrescriptionsCaretPositionChanged

    private void tabPrescriptionsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabPrescriptionsMouseClicked
        try {
            // TODO add your handling code here:
            this.display(this.tabPrescriptions.getSelectedRow());
            this.pID = (int) this.m.getValueAt(this.tabPrescriptions.getSelectedRow(), 0);
        } catch (ParseException ex) {
            Logger.getLogger(fMedPrescriptions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(fMedPrescriptions.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.btnAjouter.setEnabled(false);
        this.btnModifier.setEnabled(true);
        this.btnSupprimer.setEnabled(true);
    }//GEN-LAST:event_tabPrescriptionsMouseClicked

    private void spinInamiCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_spinInamiCaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_spinInamiCaretPositionChanged

    private void dcDateDePrescriptionCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_dcDateDePrescriptionCaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_dcDateDePrescriptionCaretPositionChanged

    private void tabMedicamentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabMedicamentMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tabMedicamentMouseClicked

    private void txtPosologieCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txtPosologieCaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPosologieCaretPositionChanged

    private void txtPosologieInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txtPosologieInputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPosologieInputMethodTextChanged

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
            java.util.logging.Logger.getLogger(fMedPrescriptions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(fMedPrescriptions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(fMedPrescriptions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(fMedPrescriptions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            private long eID;
            public void run() {
                try {
                    new fMedPrescriptions(this.eID).setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(fMedPrescriptions.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAjouter;
    private javax.swing.JButton btnModifier;
    private javax.swing.JButton btnSupprimer;
    private com.toedter.calendar.JDateChooser dcDateDePrescription;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblDateDePrescription;
    private javax.swing.JLabel lblErreur;
    private javax.swing.JLabel lblInami;
    private javax.swing.JLabel lblMedicament;
    private javax.swing.JLabel lblPosologie;
    private javax.swing.JLabel lblPrescriptions;
    private com.toedter.components.JSpinField spinInami;
    private javax.swing.JTable tabMedicament;
    private javax.swing.JTable tabPrescriptions;
    private javax.swing.JTextField tfMedicament;
    private javax.swing.JTextArea txtPosologie;
    // End of variables declaration//GEN-END:variables
}
