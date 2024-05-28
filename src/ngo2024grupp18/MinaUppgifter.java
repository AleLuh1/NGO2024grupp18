/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ngo2024grupp18;

import oru.inf.InfDB;
import oru.inf.InfException;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author alex
 */
public class MinaUppgifter extends javax.swing.JFrame {

    private InfDB idb;
    private String aid;
    private String avdid;

    /**
     * Creates new form MinaUppgifter
     */
    public MinaUppgifter(InfDB idb, String aid, String avdid) {
        initComponents();
        this.idb = idb;
        this.aid = aid;
        this.avdid = avdid;
        this.setLocationRelativeTo(null);
        try {
            String sqlFraga = "SELECT * FROM anstalld WHERE aid = " + aid;
            System.out.println(sqlFraga);
            HashMap<String, String> anstalld = idb.fetchRow(sqlFraga);

            String sqlFraga2 = "SELECT namn FROM avdelning WHERE avdid = '" + anstalld.get("avdelning") + "'";
            String avdelning = idb.fetchSingle(sqlFraga2);

            lblAID.setText(("AnställningsID: " + anstalld.get("aid")));
            lblAID.requestFocus();
            lblRoll.setText("Roll: " + getRoll());
            tfFornamnMinaUppgifter.setText(anstalld.get("fornamn"));
            tfFornamnMinaUppgifter.setEditable(false);

            tfEfternamnMinaUppgifter.setText(anstalld.get("efternamn"));
            tfEfternamnMinaUppgifter.setEditable(false);

            tfAdressMinaUppgifter.setText(anstalld.get("adress"));
            tfAdressMinaUppgifter.setEditable(false);

            tfEpostMinaUppgifter.setText(anstalld.get("epost"));
            tfEpostMinaUppgifter.setEditable(false);

            tfTelefonMinaUppgifter.setText(anstalld.get("telefon"));
            tfTelefonMinaUppgifter.setEditable(false);

            tftAvdMinaUppgifter.setText(avdelning);
            tftAvdMinaUppgifter.setEditable(false);
            
            String fragaHandlaggare = "SELECT ansvarighetsomrade FROM handlaggare WHERE aid='"+this.aid +"'";
            String ansvarsomrade = idb.fetchSingle(fragaHandlaggare);
            tfAnsvarsomradeMinaUppgifter.setText(ansvarsomrade);
            tfAnsvarsomradeMinaUppgifter.setEditable(false);

            lblAnstallningsDatum.setText("Anställningsdatum: " + anstalld.get("anstallningsdatum"));
            
            if (isAdmin()) {
                fyllBehorighetsnivaAdmin();
                lblBehorighetsnivaAdmin.setVisible(true);
                tfBehorighetsnivaAdmin.setVisible(true);
                tfBehorighetsnivaAdmin.setEditable(false);
            } else {
                lblBehorighetsnivaAdmin.setVisible(false);
                tfBehorighetsnivaAdmin.setVisible(false);
                tfBehorighetsnivaAdmin.setEditable(false);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String getRoll() {
        if (isAdmin()) {
            return "Admin";
        } else if (isProjektledare() == true) {
            return "Projektledare";
        } else {
            return "Handläggare";
        }
    }

    public boolean isAdmin() {
        String aidSomKollas = null;
        try {
            String sqlFraga = "SELECT aid FROM admin WHERE aid = " + aid;
            System.out.println(sqlFraga);
            aidSomKollas = idb.fetchSingle(sqlFraga);
            System.out.println("Aid som kollas: " + aidSomKollas);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return aidSomKollas != null;
    }
    
    private void fyllBehorighetsnivaAdmin(){
        try{
            String sqlFraga = "SELECT behorighetsniva FROM admin WHERE aid = " + aid;
            System.out.println(sqlFraga);
            String behorighetsniva = idb.fetchSingle(sqlFraga);
            tfBehorighetsnivaAdmin.setText(behorighetsniva);
          } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public boolean isProjektledare() {
        String aidSomKollas = null;
        try {
            String sqlFraga = "SELECT aid FROM anstalld JOIN projekt ON projektchef = anstalld.aid WHERE anstalld.aid = " + aid;
            System.out.println(sqlFraga);
            aidSomKollas = idb.fetchSingle(sqlFraga);
            System.out.println("Aid som kollas: " + aidSomKollas);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (aidSomKollas != null) {
            return true;
        }
        return false;
    }

    public boolean isHandlaggare() {
        String aidSomKollas = null;
        try {
            String sqlFraga = "SELECT aid FROM handlaggare WHERE aid = " + aid;
            System.out.println(sqlFraga);
            aidSomKollas = idb.fetchSingle(sqlFraga);
            System.out.println("Aid som kollas: " + aidSomKollas);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (aidSomKollas != null) {
            return true;
        }
        return false;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblAID = new javax.swing.JLabel();
        lblRoll = new javax.swing.JLabel();
        lblFornamn = new javax.swing.JLabel();
        tfFornamnMinaUppgifter = new javax.swing.JTextField();
        lblEfternamn = new javax.swing.JLabel();
        tfEfternamnMinaUppgifter = new javax.swing.JTextField();
        lblAdress = new javax.swing.JLabel();
        tfAdressMinaUppgifter = new javax.swing.JTextField();
        lblEpost = new javax.swing.JLabel();
        tfEpostMinaUppgifter = new javax.swing.JTextField();
        lblTelefon = new javax.swing.JLabel();
        tfTelefonMinaUppgifter = new javax.swing.JTextField();
        lblAnstallningsDatum = new javax.swing.JLabel();
        lblAvdelning = new javax.swing.JLabel();
        tftAvdMinaUppgifter = new javax.swing.JTextField();
        btnTillbakaMU = new javax.swing.JButton();
        lblMinaUppgifterRuta = new javax.swing.JLabel();
        btnMinaUppgifterRedigera = new javax.swing.JButton();
        lblBehorighetsnivaAdmin = new javax.swing.JLabel();
        tfBehorighetsnivaAdmin = new javax.swing.JTextField();
        lblAnsvarsomradeMinaUppgifter = new javax.swing.JLabel();
        tfAnsvarsomradeMinaUppgifter = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblAID.setText("AnställningsID");

        lblRoll.setText("Roll");

        lblFornamn.setText("Förnamn");

        lblEfternamn.setText("Efternamn");

        lblAdress.setText("Adress");

        lblEpost.setText("E-post");

        lblTelefon.setText("Telefonnummer");

        lblAnstallningsDatum.setText("Anställningsdatum");

        lblAvdelning.setText("Avdelning");

        btnTillbakaMU.setText("Tillbaka");
        btnTillbakaMU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTillbakaMUActionPerformed(evt);
            }
        });

        lblMinaUppgifterRuta.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblMinaUppgifterRuta.setText("Mina uppgifter");

        btnMinaUppgifterRedigera.setText("Ändra");
        btnMinaUppgifterRedigera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMinaUppgifterRedigeraActionPerformed(evt);
            }
        });

        lblBehorighetsnivaAdmin.setText("Behörighetsnivå");

        lblAnsvarsomradeMinaUppgifter.setText("Ansvarsområde");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(lblMinaUppgifterRuta)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblAdress, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblEpost, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblAvdelning, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblTelefon, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblBehorighetsnivaAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblEfternamn, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblFornamn, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblAnsvarsomradeMinaUppgifter))
                                .addGap(61, 61, 61))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblAID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(42, 42, 42)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tfAdressMinaUppgifter, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfFornamnMinaUppgifter, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfEfternamnMinaUppgifter, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfTelefonMinaUppgifter, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfEpostMinaUppgifter, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tftAvdMinaUppgifter, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfBehorighetsnivaAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblRoll, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfAnsvarsomradeMinaUppgifter, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblAnstallningsDatum, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 509, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(44, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(btnTillbakaMU)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnMinaUppgifterRedigera)
                .addGap(33, 33, 33))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblMinaUppgifterRuta)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAID)
                    .addComponent(lblRoll))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBehorighetsnivaAdmin)
                    .addComponent(tfBehorighetsnivaAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFornamn)
                    .addComponent(tfFornamnMinaUppgifter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblEfternamn)
                    .addComponent(tfEfternamnMinaUppgifter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAdress)
                    .addComponent(tfAdressMinaUppgifter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEpost)
                    .addComponent(tfEpostMinaUppgifter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTelefon)
                    .addComponent(tfTelefonMinaUppgifter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAvdelning)
                    .addComponent(tftAvdMinaUppgifter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAnsvarsomradeMinaUppgifter)
                    .addComponent(tfAnsvarsomradeMinaUppgifter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblAnstallningsDatum)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTillbakaMU)
                    .addComponent(btnMinaUppgifterRedigera))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTillbakaMUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTillbakaMUActionPerformed
        this.dispose();
        Meny nyMeny = new Meny(idb, aid, avdid);
        nyMeny.setVisible(true);
    }//GEN-LAST:event_btnTillbakaMUActionPerformed

    private void btnMinaUppgifterRedigeraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMinaUppgifterRedigeraActionPerformed
        if (btnMinaUppgifterRedigera.getText().equals("Ändra")) {
            btnMinaUppgifterRedigera.setText("Spara");
            
            tfTelefonMinaUppgifter.setEditable(true);
            tfAdressMinaUppgifter.setEditable(true);
        } else {
            try {
                String fornamn = tfFornamnMinaUppgifter.getText();
                String efternamn = tfEfternamnMinaUppgifter.getText();

                String adress = tfAdressMinaUppgifter.getText();
                if (adress.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Vänligen fyll i en adress");
                    return;
                }

                String epost = tfEpostMinaUppgifter.getText();

                String telefon = tfTelefonMinaUppgifter.getText();
                if (telefon.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Vänligen fyll i ett telefonnummer");
                    return;
                }

                String avdelning = tftAvdMinaUppgifter.getText();

                String sqlFraga = "UPDATE anstalld SET adress = '" + adress + "', telefon = '" + telefon + "' WHERE aid = " + aid;

                idb.update(sqlFraga);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            btnMinaUppgifterRedigera.setText("Ändra");
            tfTelefonMinaUppgifter.setEditable(false);
            tfAdressMinaUppgifter.setEditable(false);
            
        }
    }//GEN-LAST:event_btnMinaUppgifterRedigeraActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMinaUppgifterRedigera;
    private javax.swing.JButton btnTillbakaMU;
    private javax.swing.JLabel lblAID;
    private javax.swing.JLabel lblAdress;
    private javax.swing.JLabel lblAnstallningsDatum;
    private javax.swing.JLabel lblAnsvarsomradeMinaUppgifter;
    private javax.swing.JLabel lblAvdelning;
    private javax.swing.JLabel lblBehorighetsnivaAdmin;
    private javax.swing.JLabel lblEfternamn;
    private javax.swing.JLabel lblEpost;
    private javax.swing.JLabel lblFornamn;
    private javax.swing.JLabel lblMinaUppgifterRuta;
    private javax.swing.JLabel lblRoll;
    private javax.swing.JLabel lblTelefon;
    private javax.swing.JTextField tfAdressMinaUppgifter;
    private javax.swing.JTextField tfAnsvarsomradeMinaUppgifter;
    private javax.swing.JTextField tfBehorighetsnivaAdmin;
    private javax.swing.JTextField tfEfternamnMinaUppgifter;
    private javax.swing.JTextField tfEpostMinaUppgifter;
    private javax.swing.JTextField tfFornamnMinaUppgifter;
    private javax.swing.JTextField tfTelefonMinaUppgifter;
    private javax.swing.JTextField tftAvdMinaUppgifter;
    // End of variables declaration//GEN-END:variables
}
