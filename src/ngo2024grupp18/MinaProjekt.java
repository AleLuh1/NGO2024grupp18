/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ngo2024grupp18;

import java.util.HashMap;
import oru.inf.InfDB;

/**
 *
 * @author alex
 */
public class MinaProjekt extends javax.swing.JFrame {
    
    private InfDB idb;
    private String pid;
    private String aid; 
    /**
     * Creates new form MinaProjekt
     */
    public MinaProjekt(InfDB idb, String pid, String aid) {
        initComponents();
        this.idb = idb;
        this.pid = pid;
        this.aid = aid; 
        try {
            String sqlFraga = "SELECT * FROM projekt JOIN ans_proj ON projekt.pid = ans_proj.pid WHERE ans_proj.aid = " + aid;
            System.out.println(sqlFraga);
            HashMap<String, String> minaProjekt = idb.fetchRow(sqlFraga);
            tfProjektID.setText(minaProjekt.get("pid"));
            tfProjektID.setEditable(false);
            lblProjektID.requestFocus();
            tfProjektNamn.setText(minaProjekt.get("projektnamn"));
            tfProjektNamn.setEditable(false);
            tfBeskrivningProjekt.setText(minaProjekt.get("beskrivning"));
            tfBeskrivningProjekt.setEditable(false);
            tfStartdatum.setText(minaProjekt.get("startdatum"));
            tfStartdatum.setEditable(false);
            tfSlutdatum.setText(minaProjekt.get("slutdatum"));
            tfSlutdatum.setEditable(false);
            tfKostnad.setText(minaProjekt.get("kostnad"));
            tfKostnad.setEditable(false);
            tfStatus.setText(minaProjekt.get("status"));
            tfStatus.setEditable(false);
            tfPrioProjekt.setText(minaProjekt.get("prioritet"));
            tfPrioProjekt.setEditable(false);
            tfProjektchef.setText(minaProjekt.get("projektchef"));
            tfProjektchef.setEditable(false);
            tfLand.setText(minaProjekt.get("land"));
            tfLand.setEditable(false);
            
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
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

        btnTillbakaMinaProj = new javax.swing.JButton();
        lblProjektID = new javax.swing.JLabel();
        tfProjektID = new javax.swing.JTextField();
        lblProjektNamn = new javax.swing.JLabel();
        tfProjektNamn = new javax.swing.JTextField();
        lblBeskrivningProjekt = new javax.swing.JLabel();
        tfBeskrivningProjekt = new javax.swing.JTextField();
        lblStartdatum = new javax.swing.JLabel();
        tfStartdatum = new javax.swing.JTextField();
        lblSlutdatum = new javax.swing.JLabel();
        tfSlutdatum = new javax.swing.JTextField();
        lblKostnad = new javax.swing.JLabel();
        tfKostnad = new javax.swing.JTextField();
        lblStatus = new javax.swing.JLabel();
        tfStatus = new javax.swing.JTextField();
        lblPrioProjekt = new javax.swing.JLabel();
        tfPrioProjekt = new javax.swing.JTextField();
        lblProjektchef = new javax.swing.JLabel();
        tfProjektchef = new javax.swing.JTextField();
        lblLand = new javax.swing.JLabel();
        tfLand = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnTillbakaMinaProj.setText("Tillbaka");

        lblProjektID.setText("Projekt-ID");

        tfProjektID.setText("jTextField1");

        lblProjektNamn.setText("Projektnamn");

        tfProjektNamn.setText("jTextField1");

        lblBeskrivningProjekt.setText("Beskrivning");

        tfBeskrivningProjekt.setText("jTextField1");

        lblStartdatum.setText("Startdatum");

        tfStartdatum.setText("jTextField1");

        lblSlutdatum.setText("Slutdatum");

        tfSlutdatum.setText("jTextField1");

        lblKostnad.setText("Kostnad");

        tfKostnad.setText("jTextField1");

        lblStatus.setText("Status");

        tfStatus.setText("jTextField1");

        lblPrioProjekt.setText("Prioritet");

        tfPrioProjekt.setText("jTextField1");

        lblProjektchef.setText("Projektchef");

        tfProjektchef.setText("jTextField1");

        lblLand.setText("Land");

        tfLand.setText("jTextField2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblLand)
                    .addComponent(lblProjektchef)
                    .addComponent(lblPrioProjekt)
                    .addComponent(lblStatus)
                    .addComponent(lblKostnad)
                    .addComponent(lblStartdatum)
                    .addComponent(lblBeskrivningProjekt)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnTillbakaMinaProj)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lblProjektID, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(tfProjektID, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(2, 2, 2)
                                    .addComponent(lblProjektNamn, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(lblSlutdatum))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(tfProjektNamn)
                                .addComponent(tfStartdatum, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tfSlutdatum, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tfKostnad, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tfStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tfPrioProjekt, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tfProjektchef, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tfLand, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tfBeskrivningProjekt, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(128, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblProjektID)
                            .addComponent(tfProjektID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfProjektNamn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblProjektNamn))
                        .addGap(18, 18, 18)
                        .addComponent(lblBeskrivningProjekt))
                    .addComponent(tfBeskrivningProjekt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblStartdatum)
                    .addComponent(tfStartdatum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfSlutdatum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSlutdatum))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblKostnad)
                    .addComponent(tfKostnad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblStatus)
                    .addComponent(tfStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPrioProjekt)
                    .addComponent(tfPrioProjekt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblProjektchef)
                    .addComponent(tfProjektchef, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLand)
                    .addComponent(tfLand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addComponent(btnTillbakaMinaProj)
                .addGap(39, 39, 39))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTillbakaMinaProj;
    private javax.swing.JLabel lblBeskrivningProjekt;
    private javax.swing.JLabel lblKostnad;
    private javax.swing.JLabel lblLand;
    private javax.swing.JLabel lblPrioProjekt;
    private javax.swing.JLabel lblProjektID;
    private javax.swing.JLabel lblProjektNamn;
    private javax.swing.JLabel lblProjektchef;
    private javax.swing.JLabel lblSlutdatum;
    private javax.swing.JLabel lblStartdatum;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JTextField tfBeskrivningProjekt;
    private javax.swing.JTextField tfKostnad;
    private javax.swing.JTextField tfLand;
    private javax.swing.JTextField tfPrioProjekt;
    private javax.swing.JTextField tfProjektID;
    private javax.swing.JTextField tfProjektNamn;
    private javax.swing.JTextField tfProjektchef;
    private javax.swing.JTextField tfSlutdatum;
    private javax.swing.JTextField tfStartdatum;
    private javax.swing.JTextField tfStatus;
    // End of variables declaration//GEN-END:variables
}