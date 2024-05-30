/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ngo2024grupp18;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import oru.inf.InfDB;
import oru.inf.InfException;

/**
 *
 * @author alex
 */
public class Inloggning extends javax.swing.JFrame {

    private InfDB idb;

    /**
     * Creates new form Inloggning
     */
    public Inloggning(InfDB idb) {
        initComponents();
        this.idb = idb;
        this.setLocationRelativeTo(null); //sätter rutan i mitten
        lblFelLosenord.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblEpost = new javax.swing.JLabel();
        lblLösenord = new javax.swing.JLabel();
        tfEpost = new javax.swing.JTextField();
        btnLoggaIn = new javax.swing.JButton();
        lblFelLosenord = new javax.swing.JLabel();
        pfLosenord = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblEpost.setText("E-post");

        lblLösenord.setText("Lösenord");

        tfEpost.setText("maria.g@example.com");

        btnLoggaIn.setText("Logga in");
        btnLoggaIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoggaInActionPerformed(evt);
            }
        });

        lblFelLosenord.setForeground(new java.awt.Color(255, 0, 0));
        lblFelLosenord.setText("Vänligen ange korrekt e-post eller lösenord");

        pfLosenord.setText("password123");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblFelLosenord, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 136, Short.MAX_VALUE)
                        .addComponent(btnLoggaIn))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblLösenord, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                            .addComponent(lblEpost, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tfEpost, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                            .addComponent(pfLosenord))))
                .addGap(99, 99, 99))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEpost)
                    .addComponent(tfEpost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLösenord)
                    .addComponent(pfLosenord, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLoggaIn)
                    .addComponent(lblFelLosenord))
                .addContainerGap(168, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoggaInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoggaInActionPerformed
        String ePost = tfEpost.getText();
        String losen = new String(pfLosenord.getPassword());

// Kollar att användaren har angett text i rutorna för E-post och lösenord
        if (Validering.finnsTextTF(tfEpost) && Validering.finnsTextPF(pfLosenord)
                && Validering.isKorrektFormatEpostTF(tfEpost)) {
            try {
                String sqlFraga = "SELECT losenord FROM anstalld WHERE epost = '" + ePost + "'";
                System.out.println(sqlFraga);
                String dbLosen = idb.fetchSingle(sqlFraga);
                if (losen.equals(dbLosen)) {
                    String sqlFraga2 = "SELECT aid FROM anstalld WHERE epost = '" + ePost + "'";
                    System.out.println(sqlFraga2);
                    String dbAid = idb.fetchSingle(sqlFraga2);
                    String sqlFragaAvdID = "SELECT avdelning FROM anstalld WHERE aid = " + dbAid;
                    String avdid = idb.fetchSingle(sqlFragaAvdID);
                    new Meny(idb, dbAid, avdid).setVisible(true);
                    this.setVisible(false);
                } else {
                    lblFelLosenord.setVisible(true);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
    }//GEN-LAST:event_btnLoggaInActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLoggaIn;
    private javax.swing.JLabel lblEpost;
    private javax.swing.JLabel lblFelLosenord;
    private javax.swing.JLabel lblLösenord;
    private javax.swing.JPasswordField pfLosenord;
    private javax.swing.JTextField tfEpost;
    // End of variables declaration//GEN-END:variables
}
