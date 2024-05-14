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
    private String pid;
    private String avdid; 

    /**
     * Creates new form Meny
     */
    public Meny(InfDB idb, String aid) {
        this.idb = idb;
        this.aid = aid;
        initComponents();
        // lblInloggadAnvandare.setText("AnställningsID: " + inloggadAnvandare);
        // lblRoll.setText("Roll: " + enAnstalld.getRoll());
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
        btnHallbarhetsMal = new javax.swing.JButton();
        lblMeny = new javax.swing.JLabel();

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

        btnHallbarhetsMal.setText("Hållbarhetsmål");
        btnHallbarhetsMal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHallbarhetsMalActionPerformed(evt);
            }
        });

        lblMeny.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblMeny.setText("Meny");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnHallbarhetsMal)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnMinaUppgifter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnProjekt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAvdelning, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(lblMeny, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(208, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(lblMeny)
                .addGap(18, 18, 18)
                .addComponent(btnMinaUppgifter)
                .addGap(18, 18, 18)
                .addComponent(btnProjekt)
                .addGap(26, 26, 26)
                .addComponent(btnAvdelning)
                .addGap(18, 18, 18)
                .addComponent(btnHallbarhetsMal)
                .addContainerGap(82, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMinaUppgifterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMinaUppgifterActionPerformed
        new MinaUppgifter(idb, aid).setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_btnMinaUppgifterActionPerformed
    
    private void btnProjektActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProjektActionPerformed
        new Projekt(idb, pid, aid).setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_btnProjektActionPerformed

    private void btnAvdelningActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAvdelningActionPerformed
        new Avdelning(idb, avdid).setVisible(true); 
        setVisible(false);
    }//GEN-LAST:event_btnAvdelningActionPerformed

    private void btnHallbarhetsMalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHallbarhetsMalActionPerformed
        new HallbarhetsMal(idb).setVisible(true); 
        setVisible(false);
    }//GEN-LAST:event_btnHallbarhetsMalActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAvdelning;
    private javax.swing.JButton btnHallbarhetsMal;
    private javax.swing.JButton btnMinaUppgifter;
    private javax.swing.JButton btnProjekt;
    private javax.swing.JLabel lblMeny;
    // End of variables declaration//GEN-END:variables
}
