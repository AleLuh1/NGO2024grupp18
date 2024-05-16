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
public class LaggTillAnstalld extends javax.swing.JFrame {

    private InfDB idb;
    private String aid;
    private String pid;
    private String avdid; 

    /**
     * Creates new form Anstallda
     */
    public LaggTillAnstalld(InfDB idb) {
        initComponents();
        this.idb = idb;
<<<<<<< Updated upstream
=======
        this.aid = aid;
        this.pid = pid; 
        this.avdid = avdid; 
>>>>>>> Stashed changes
        LaggaTillNyAnstalldAid();
    }

    private String LaggaTillNyAnstalldAid() {
        String nyAnstalldAid = null;
        try {
            //SQL-fråga för att hämta ut största aid som finns i anstalld
            String sqlFragaHogstAid = "SELECT max(aid) FROM anstalld";
            System.out.println(sqlFragaHogstAid);
            //Hämtar ut resultatet från sql-frågan i en sträng 
            String hogstAnstalldAidDB = idb.fetchSingle(sqlFragaHogstAid);
            //Konverterar strängen med högst aid till en int
            int hogstAnstalldAidInt = Integer.parseInt(hogstAnstalldAidDB);
            int nyAnstalldAidInt = hogstAnstalldAidInt + 1;
            System.out.println(nyAnstalldAidInt);
            nyAnstalldAid = Integer.toString(nyAnstalldAidInt);
            tfAidNyAnstalld.setText(nyAnstalldAid);
            tfAidNyAnstalld.setEditable(false);
            lblAidNyAnstalld.requestFocus();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return nyAnstalldAid;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblLaggTillAnstalldRuta = new javax.swing.JLabel();
        lblFornamnNyAnstalld = new javax.swing.JLabel();
        lblEfternamnNyAnstalld = new javax.swing.JLabel();
        tfFornamnNyAnstalld = new javax.swing.JTextField();
        tfEfternamnNyAnstalld = new javax.swing.JTextField();
        btnSparaNyAnstalld = new javax.swing.JButton();
        btnTillbakaLaggTillAnstalld = new javax.swing.JButton();
        lblAidNyAnstalld = new javax.swing.JLabel();
        tfAidNyAnstalld = new javax.swing.JTextField();
        lblAdressNyAnstalld = new javax.swing.JLabel();
        tfAdressNyAnstalld = new javax.swing.JTextField();
        lblEpostNyAnstalld = new javax.swing.JLabel();
        tfEpostNyAnstalld = new javax.swing.JTextField();
        lblTelefonNyAnstalld = new javax.swing.JLabel();
        tfTelefonNyAnstalld = new javax.swing.JTextField();
        lblAnstallningsdatumNyAnstalld = new javax.swing.JLabel();
        tfAnstallningsdatumNyAnstalld = new javax.swing.JTextField();
        lblLosenordNyAnstalld = new javax.swing.JLabel();
        tfLosenordNyAnstalld = new javax.swing.JTextField();
        lblAvdNyAnstalld = new javax.swing.JLabel();
        tfAvdNyAnstalld = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblLaggTillAnstalldRuta.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblLaggTillAnstalldRuta.setText("Lägg till anställd");

        lblFornamnNyAnstalld.setText("Förnamn");

        lblEfternamnNyAnstalld.setText("Efternamn");

        btnSparaNyAnstalld.setText("Spara");
        btnSparaNyAnstalld.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSparaNyAnstalldActionPerformed(evt);
            }
        });

        btnTillbakaLaggTillAnstalld.setText("Tillbaka");
        btnTillbakaLaggTillAnstalld.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTillbakaLaggTillAnstalldActionPerformed(evt);
            }
        });

        lblAidNyAnstalld.setText("Anställnings-ID");

        lblAdressNyAnstalld.setText("Adress");

        lblEpostNyAnstalld.setText("E-post");

        lblTelefonNyAnstalld.setText("Telefon");

        lblAnstallningsdatumNyAnstalld.setText("Anställningsdatum");

        lblLosenordNyAnstalld.setText("Lösenord");

        lblAvdNyAnstalld.setText("Avdelning");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(btnTillbakaLaggTillAnstalld)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 212, Short.MAX_VALUE)
                .addComponent(btnSparaNyAnstalld)
                .addGap(25, 25, 25))
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblLaggTillAnstalldRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblAvdNyAnstalld, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblLosenordNyAnstalld, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(lblTelefonNyAnstalld, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblEpostNyAnstalld, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblAdressNyAnstalld, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(lblFornamnNyAnstalld, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblEfternamnNyAnstalld, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE))
                                .addComponent(lblAidNyAnstalld, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE))
                            .addComponent(lblAnstallningsdatumNyAnstalld, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE))
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tfFornamnNyAnstalld)
                            .addComponent(tfAidNyAnstalld)
                            .addComponent(tfEfternamnNyAnstalld)
                            .addComponent(tfAdressNyAnstalld)
                            .addComponent(tfEpostNyAnstalld)
                            .addComponent(tfTelefonNyAnstalld)
                            .addComponent(tfAnstallningsdatumNyAnstalld)
                            .addComponent(tfLosenordNyAnstalld)
                            .addComponent(tfAvdNyAnstalld, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblLaggTillAnstalldRuta)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblAidNyAnstalld)
                                    .addComponent(tfAidNyAnstalld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(tfFornamnNyAnstalld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblFornamnNyAnstalld))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblEfternamnNyAnstalld)
                                .addGap(6, 6, 6))
                            .addComponent(tfEfternamnNyAnstalld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tfAdressNyAnstalld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblAdressNyAnstalld)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEpostNyAnstalld)
                    .addComponent(tfEpostNyAnstalld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfTelefonNyAnstalld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTelefonNyAnstalld))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAnstallningsdatumNyAnstalld)
                    .addComponent(tfAnstallningsdatumNyAnstalld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblLosenordNyAnstalld)
                    .addComponent(tfLosenordNyAnstalld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAvdNyAnstalld)
                    .addComponent(tfAvdNyAnstalld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSparaNyAnstalld)
                    .addComponent(btnTillbakaLaggTillAnstalld))
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSparaNyAnstalldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSparaNyAnstalldActionPerformed
        //get text från fälten och insert i databas
        String nyAnstalldAidStr = tfAidNyAnstalld.getText();
        int nyAnstalldAidInt = Integer.parseInt(nyAnstalldAidStr);
        String nyAnstalldFornamn = tfFornamnNyAnstalld.getText();
        String nyAnstalldEfternamn = tfEfternamnNyAnstalld.getText();
        String nyAnstalldAdress = tfAdressNyAnstalld.getText();
        String nyAnstalldEpost = tfEpostNyAnstalld.getText();
        String nyAnstalldTelefon = tfTelefonNyAnstalld.getText();
        String NyAnstalldAnstallningsDatum = tfAnstallningsdatumNyAnstalld.getText();
        String NyAnstalldLosenord = tfLosenordNyAnstalld.getText();
        String nyAnstalldAvd = tfAvdNyAnstalld.getText();
        int nyAnstalldAvdInt = Integer.parseInt(nyAnstalldAvd);
        try {
            String sqlLaggTill = "INSERT INTO anstalld (aid, fornamn, efternamn, adress, epost, telefon, anstallningsdatum, losenord, avdelning) VALUES (" + nyAnstalldAidInt + ", '" + nyAnstalldFornamn + "', '" + nyAnstalldEfternamn + "', '" + nyAnstalldAdress + "', '" + nyAnstalldEpost + "', '" + nyAnstalldTelefon + "', str_to_date('" + NyAnstalldAnstallningsDatum + "', '%Y-%m-%d'), '" + NyAnstalldLosenord + "', " + nyAnstalldAvdInt + ")";
            idb.insert(sqlLaggTill);
        } catch (InfException ex) {
            System.out.println(ex.getMessage());
        }
    }//GEN-LAST:event_btnSparaNyAnstalldActionPerformed

    private void btnTillbakaLaggTillAnstalldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTillbakaLaggTillAnstalldActionPerformed
        this.toBack();
        Meny nyMeny = new Meny(idb, aid, pid, avdid);
        nyMeny.setVisible(true);
        nyMeny.toFront();
    }//GEN-LAST:event_btnTillbakaLaggTillAnstalldActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSparaNyAnstalld;
    private javax.swing.JButton btnTillbakaLaggTillAnstalld;
    private javax.swing.JLabel lblAdressNyAnstalld;
    private javax.swing.JLabel lblAidNyAnstalld;
    private javax.swing.JLabel lblAnstallningsdatumNyAnstalld;
    private javax.swing.JLabel lblAvdNyAnstalld;
    private javax.swing.JLabel lblEfternamnNyAnstalld;
    private javax.swing.JLabel lblEpostNyAnstalld;
    private javax.swing.JLabel lblFornamnNyAnstalld;
    private javax.swing.JLabel lblLaggTillAnstalldRuta;
    private javax.swing.JLabel lblLosenordNyAnstalld;
    private javax.swing.JLabel lblTelefonNyAnstalld;
    private javax.swing.JTextField tfAdressNyAnstalld;
    private javax.swing.JTextField tfAidNyAnstalld;
    private javax.swing.JTextField tfAnstallningsdatumNyAnstalld;
    private javax.swing.JTextField tfAvdNyAnstalld;
    private javax.swing.JTextField tfEfternamnNyAnstalld;
    private javax.swing.JTextField tfEpostNyAnstalld;
    private javax.swing.JTextField tfFornamnNyAnstalld;
    private javax.swing.JTextField tfLosenordNyAnstalld;
    private javax.swing.JTextField tfTelefonNyAnstalld;
    // End of variables declaration//GEN-END:variables
}
