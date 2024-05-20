/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ngo2024grupp18;

import oru.inf.InfDB;
import oru.inf.InfException;

/**
 *
 * @author marin
 */
public class Meny extends javax.swing.JFrame {

    private InfDB idb;
    private String aid;
    private String avdid;

    /**
     * Creates new form Meny
     */
    public Meny(InfDB idb, String aid, String avdid) {
        this.idb = idb;
        this.aid = aid;
        this.avdid = avdid;
        initComponents();
        this.setLocationRelativeTo(null);
        MinaUppgifter ny = new MinaUppgifter(idb, aid, avdid);
        if (ny.isAdmin()) {
            btnLaggTillAnstalld.setVisible(true);
        } else {
            btnLaggTillAnstalld.setVisible(false);
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

        btnMinaUppgifter = new javax.swing.JButton();
        btnProjekt = new javax.swing.JButton();
        btnAvdelning = new javax.swing.JButton();
        lblMeny = new javax.swing.JLabel();
        btnLaggTillAnstalld = new javax.swing.JButton();
        btnSokAnstalld = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnMinaUppgifter.setText("Mina uppgifter");
        btnMinaUppgifter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMinaUppgifterActionPerformed(evt);
            }
        });

        btnProjekt.setText("Projekt");
        btnProjekt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProjektActionPerformed(evt);
            }
        });

        btnAvdelning.setText("Avdelning");
        btnAvdelning.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAvdelningActionPerformed(evt);
            }
        });

        lblMeny.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblMeny.setText("Meny");

        btnLaggTillAnstalld.setText("Lägg till anställd");
        btnLaggTillAnstalld.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLaggTillAnstalldActionPerformed(evt);
            }
        });

        btnSokAnstalld.setText("Sök anställd");
        btnSokAnstalld.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSokAnstalldActionPerformed(evt);
            }
        });

        jButton1.setText("Avdelning ADMIN");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblMeny, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(208, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnMinaUppgifter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnProjekt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAvdelning, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnLaggTillAnstalld)
                            .addComponent(btnSokAnstalld))
                        .addGap(86, 86, 86))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(lblMeny)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnMinaUppgifter)
                    .addComponent(btnLaggTillAnstalld))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnProjekt)
                    .addComponent(btnSokAnstalld))
                .addGap(18, 18, 18)
                .addComponent(btnAvdelning)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(90, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMinaUppgifterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMinaUppgifterActionPerformed
        new MinaUppgifter(idb, aid, avdid).setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_btnMinaUppgifterActionPerformed

    private void btnProjektActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProjektActionPerformed
        new Projekt(idb, aid, avdid).setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_btnProjektActionPerformed

    private void btnAvdelningActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAvdelningActionPerformed
        new Avdelning(idb, avdid, aid).setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_btnAvdelningActionPerformed

    private void btnLaggTillAnstalldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLaggTillAnstalldActionPerformed
        new LaggTillAnstalld(idb, aid, avdid).setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_btnLaggTillAnstalldActionPerformed

    private void btnSokAnstalldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSokAnstalldActionPerformed
        this.setVisible(false);
        new SokEfterHandlaggare(idb, aid, avdid).setVisible(true);
    }//GEN-LAST:event_btnSokAnstalldActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new AvdelningAdmin(idb, aid, avdid).setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAvdelning;
    private javax.swing.JButton btnLaggTillAnstalld;
    private javax.swing.JButton btnMinaUppgifter;
    private javax.swing.JButton btnProjekt;
    private javax.swing.JButton btnSokAnstalld;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel lblMeny;
    // End of variables declaration//GEN-END:variables
}
