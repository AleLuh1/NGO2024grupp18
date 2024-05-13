/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ngo2024grupp18;

import oru.inf.InfDB;
import oru.inf.InfException;
import java.util.HashMap;

/**
 *
 * @author alex
 */
public class MinaUppgifter extends javax.swing.JFrame {

    private InfDB idb;
    private String aid;
    

    /**
     * Creates new form MinaUppgifter
     */
    public MinaUppgifter(InfDB idb, String aid) {
        initComponents();
        this.idb = idb;
        this.aid = aid;
        try {
            String sqlFraga = "SELECT * FROM anstalld WHERE aid = " + aid;
            System.out.println(sqlFraga);
            HashMap<String, String> anstalld = idb.fetchRow(sqlFraga);
            lblAID.setText(("AnställningsID: " + anstalld.get("aid")));
            lblAID.requestFocus();
            lblRoll.setText("Roll: " + getRoll());
            tfFornamn.setText(anstalld.get("fornamn"));
            tfFornamn.setEditable(false);
            tfEfternamn.setText(anstalld.get("efternamn"));
            tfEfternamn.setEditable(false);
            tfAdress.setText(anstalld.get("adress"));
            tfAdress.setEditable(false);
            tfEpost.setText(anstalld.get("epost"));
            tfEpost.setEditable(false);
            tfTelefon.setText(anstalld.get("telefon"));
            tfTelefon.setEditable(false);
            tfAvdelning.setText(anstalld.get("avdelning"));
            tfAvdelning.setEditable(false);
            lblAnstallningsDatum.setText("Anställningsdatum: " + anstalld.get("anstallningsdatum"));
            if (isHandlaggare() || isProjektledare()) {
                btnAndraMinaUppgifter.setVisible(true);
            } else {
                btnAndraMinaUppgifter.setVisible(false);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public String getRoll() {
        if (isAdmin() == true) {
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
            System.out.println(ex.getMessage());
        }
        if (aidSomKollas != null) {
            return true;
        }
        return false;
    }

    public boolean isProjektledare() {
        String aidSomKollas = null;
        try {
            String sqlFraga = "SELECT aid FROM anstalld JOIN projekt ON projektchef = anstalld.aid WHERE anstalld.aid = " + aid;
            System.out.println(sqlFraga);
            aidSomKollas = idb.fetchSingle(sqlFraga);
            System.out.println("Aid som kollas: " + aidSomKollas);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
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
            System.out.println(ex.getMessage());
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
        tfFornamn = new javax.swing.JTextField();
        lblEfternamn = new javax.swing.JLabel();
        tfEfternamn = new javax.swing.JTextField();
        lblAdress = new javax.swing.JLabel();
        tfAdress = new javax.swing.JTextField();
        lblEpost = new javax.swing.JLabel();
        tfEpost = new javax.swing.JTextField();
        lblTelefon = new javax.swing.JLabel();
        tfTelefon = new javax.swing.JTextField();
        lblAnstallningsDatum = new javax.swing.JLabel();
        lblAvdelning = new javax.swing.JLabel();
        tfAvdelning = new javax.swing.JTextField();
        btnAndraMinaUppgifter = new javax.swing.JButton();
        btnTillbakaMU = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblAID.setText("AnställningsID");

        lblRoll.setText("Roll");

        lblFornamn.setText("Förnamn");

        tfFornamn.setText("jTextField1");

        lblEfternamn.setText("Efternamn");

        tfEfternamn.setText("jTextField1");

        lblAdress.setText("Adress");

        tfAdress.setText("jTextField1");

        lblEpost.setText("E-post");

        tfEpost.setText("jTextField1");

        lblTelefon.setText("Telefon");

        tfTelefon.setText("jTextField1");

        lblAnstallningsDatum.setText("Anställningsdatum");

        lblAvdelning.setText("Avdelning");

        tfAvdelning.setText("jTextField1");

        btnAndraMinaUppgifter.setText("Ändra");

        btnTillbakaMU.setText("Tillbaka");
        btnTillbakaMU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTillbakaMUActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnTillbakaMU)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAndraMinaUppgifter)
                        .addGap(63, 63, 63))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblAnstallningsDatum, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(lblFornamn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblAID, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE))
                                    .addComponent(lblEfternamn, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblAdress, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblEpost, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblTelefon, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblAvdelning, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(61, 61, 61)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblRoll, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfAdress, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                                    .addComponent(tfEpost)
                                    .addComponent(tfTelefon)
                                    .addComponent(tfFornamn)
                                    .addComponent(tfEfternamn)
                                    .addComponent(tfAvdelning))))
                        .addContainerGap(102, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAID)
                    .addComponent(lblRoll))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFornamn)
                    .addComponent(tfFornamn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblEfternamn)
                    .addComponent(tfEfternamn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAdress)
                    .addComponent(tfAdress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEpost)
                    .addComponent(tfEpost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTelefon)
                    .addComponent(tfTelefon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAvdelning)
                    .addComponent(tfAvdelning, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblAnstallningsDatum)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAndraMinaUppgifter)
                    .addComponent(btnTillbakaMU))
                .addGap(27, 27, 27))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTillbakaMUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTillbakaMUActionPerformed
        Meny nyMeny = new Meny(idb, aid);
    }//GEN-LAST:event_btnTillbakaMUActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAndraMinaUppgifter;
    private javax.swing.JButton btnTillbakaMU;
    private javax.swing.JLabel lblAID;
    private javax.swing.JLabel lblAdress;
    private javax.swing.JLabel lblAnstallningsDatum;
    private javax.swing.JLabel lblAvdelning;
    private javax.swing.JLabel lblEfternamn;
    private javax.swing.JLabel lblEpost;
    private javax.swing.JLabel lblFornamn;
    private javax.swing.JLabel lblRoll;
    private javax.swing.JLabel lblTelefon;
    private javax.swing.JTextField tfAdress;
    private javax.swing.JTextField tfAvdelning;
    private javax.swing.JTextField tfEfternamn;
    private javax.swing.JTextField tfEpost;
    private javax.swing.JTextField tfFornamn;
    private javax.swing.JTextField tfTelefon;
    // End of variables declaration//GEN-END:variables
}
