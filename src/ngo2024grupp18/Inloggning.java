/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ngo2024grupp18;

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
        lblFelmeddelande.setVisible(false);
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
        lblFelmeddelande = new javax.swing.JLabel();
        tfEpost = new javax.swing.JTextField();
        tfLosenord = new javax.swing.JTextField();
        btnLoggaIn = new javax.swing.JButton();
        lblKorrektEpost = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblEpost.setText("E-post");

        lblLösenord.setText("Lösenord");

        lblFelmeddelande.setForeground(new java.awt.Color(255, 0, 51));
        lblFelmeddelande.setText("Felaktig e-post eller lösenord");

        tfEpost.setText("maria.g@example.com");

        tfLosenord.setText("password123");

        btnLoggaIn.setText("Logga in");
        btnLoggaIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoggaInActionPerformed(evt);
            }
        });

        lblKorrektEpost.setForeground(new java.awt.Color(255, 0, 51));
        lblKorrektEpost.setText("Vänligen ange korrekt e-post");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lblLösenord, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tfLosenord, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lblEpost, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tfEpost, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFelmeddelande)
                            .addComponent(lblKorrektEpost, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(btnLoggaIn)))
                .addContainerGap(189, Short.MAX_VALUE))
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
                    .addComponent(tfLosenord, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(lblFelmeddelande)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLoggaIn)
                    .addComponent(lblKorrektEpost))
                .addContainerGap(146, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoggaInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoggaInActionPerformed
        String ePost = tfEpost.getText();
        String losen = tfLosenord.getText();

// Kollar att användaren har angett text i rutorna för E-post och lösenord
        if (Validering.finnsText(tfEpost) && Validering.finnsText(tfLosenord)) {

// Får ut användarens lösenord ur databasen om den stämmer överens med angiven e-post         
            try {
                String sqlFraga1 = "SELECT losenord FROM anstalld WHERE epost = '" + ePost + "'";
                System.out.println(sqlFraga1);
                String dbLosen = idb.fetchSingle(sqlFraga1);

// Kollar att lösenordet användaren har skrivit in i fältet matchar lösenordet i db
// Nytt objekt av klassen Meny skapas om lösenordet är korrekt 
                if (losen.equals(dbLosen)) {
                    String sqlFraga2 = "SELECT aid FROM anstalld WHERE epost = '" + ePost + "'";
                    System.out.println(sqlFraga2);
                    String dbAid = idb.fetchSingle(sqlFraga2);
                    new Meny(idb, dbAid).setVisible(true);
                    this.setVisible(false);
                } else {
                    lblFelmeddelande.setVisible(true);
                }

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }//GEN-LAST:event_btnLoggaInActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLoggaIn;
    private javax.swing.JLabel lblEpost;
    private javax.swing.JLabel lblFelmeddelande;
    private javax.swing.JLabel lblKorrektEpost;
    private javax.swing.JLabel lblLösenord;
    private javax.swing.JTextField tfEpost;
    private javax.swing.JTextField tfLosenord;
    // End of variables declaration//GEN-END:variables
}
