/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ngo2024grupp18;

import oru.inf.InfDB;
import javax.swing.JOptionPane;

/**
 *
 * @author ellenor
 */
public class LaggTillAvdelning extends javax.swing.JFrame {

    private InfDB idb;
    private String aid;
    private String avdid;

    /**
     * Creates new form LaggTillAvdelning
     */
    public LaggTillAvdelning(InfDB idb, String aid, String avdid) {
        initComponents();
        this.idb = idb;
        this.aid = aid;
        this.avdid = avdid;
        this.setLocationRelativeTo(null);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblLaggTillAvdRuta = new javax.swing.JLabel();
        lblAvdIDLaggTillAvd = new javax.swing.JLabel();
        lblNamnLaggTillAvd = new javax.swing.JLabel();
        lblBeskrivningLaggTillAvd = new javax.swing.JLabel();
        lblAdressLaggTillAvd = new javax.swing.JLabel();
        lblEpostLaggTillAvd = new javax.swing.JLabel();
        lblTnrLaggTillAvd = new javax.swing.JLabel();
        lblStadLaggTillAvd = new javax.swing.JLabel();
        lblAvdchefLaggTillAvd = new javax.swing.JLabel();
        btnTillbakaLaggTillAvd = new javax.swing.JButton();
        btnSparaLaggTillAvd = new javax.swing.JButton();
        tfAvdIDLaggTillAvd = new javax.swing.JTextField();
        tfNamnLaggTillAvd = new javax.swing.JTextField();
        tfBeskrivningLaggTillAvd = new javax.swing.JTextField();
        tfAdressLaggTillAvd = new javax.swing.JTextField();
        tfEpostLaggTillAvd = new javax.swing.JTextField();
        tfTnrLaggTillAvd = new javax.swing.JTextField();
        tfStadLaggTillAvd = new javax.swing.JTextField();
        tfAvdchefLaggTillAvd = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblLaggTillAvdRuta.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        lblLaggTillAvdRuta.setText("Lägg till avdelning");

        lblAvdIDLaggTillAvd.setText("AvdelningsID");

        lblNamnLaggTillAvd.setText("Namn");

        lblBeskrivningLaggTillAvd.setText("Beskrivning");

        lblAdressLaggTillAvd.setText("Adress");

        lblEpostLaggTillAvd.setText("E-post");

        lblTnrLaggTillAvd.setText("Telefonnummer");

        lblStadLaggTillAvd.setText("Stad");

        lblAvdchefLaggTillAvd.setText("Avdelningschef");

        btnTillbakaLaggTillAvd.setText("Tillbaka");
        btnTillbakaLaggTillAvd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTillbakaLaggTillAvdActionPerformed(evt);
            }
        });

        btnSparaLaggTillAvd.setText("Spara");
        btnSparaLaggTillAvd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSparaLaggTillAvdActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblLaggTillAvdRuta)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblAvdIDLaggTillAvd)
                            .addComponent(lblBeskrivningLaggTillAvd)
                            .addComponent(lblNamnLaggTillAvd)
                            .addComponent(lblAdressLaggTillAvd)
                            .addComponent(lblEpostLaggTillAvd)
                            .addComponent(lblTnrLaggTillAvd)
                            .addComponent(lblStadLaggTillAvd)
                            .addComponent(lblAvdchefLaggTillAvd))
                        .addGap(49, 49, 49)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfNamnLaggTillAvd)
                            .addComponent(tfBeskrivningLaggTillAvd)
                            .addComponent(tfAdressLaggTillAvd)
                            .addComponent(tfEpostLaggTillAvd)
                            .addComponent(tfTnrLaggTillAvd)
                            .addComponent(tfStadLaggTillAvd)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(tfAvdIDLaggTillAvd, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(tfAvdchefLaggTillAvd))))
                .addGap(35, 35, 35))
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(btnTillbakaLaggTillAvd)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSparaLaggTillAvd)
                .addGap(23, 23, 23))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(lblLaggTillAvdRuta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAvdIDLaggTillAvd)
                    .addComponent(tfAvdIDLaggTillAvd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNamnLaggTillAvd)
                    .addComponent(tfNamnLaggTillAvd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblBeskrivningLaggTillAvd)
                    .addComponent(tfBeskrivningLaggTillAvd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAdressLaggTillAvd)
                    .addComponent(tfAdressLaggTillAvd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblEpostLaggTillAvd)
                    .addComponent(tfEpostLaggTillAvd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTnrLaggTillAvd)
                    .addComponent(tfTnrLaggTillAvd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblStadLaggTillAvd)
                    .addComponent(tfStadLaggTillAvd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAvdchefLaggTillAvd)
                    .addComponent(tfAvdchefLaggTillAvd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTillbakaLaggTillAvd)
                    .addComponent(btnSparaLaggTillAvd))
                .addGap(24, 24, 24))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTillbakaLaggTillAvdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTillbakaLaggTillAvdActionPerformed
        this.toBack();
        AvdelningAdmin nyAvdAdmin = new AvdelningAdmin(idb, aid, avdid);
        nyAvdAdmin.setVisible(true);
        nyAvdAdmin.toFront();
    }//GEN-LAST:event_btnTillbakaLaggTillAvdActionPerformed

    private void btnSparaLaggTillAvdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSparaLaggTillAvdActionPerformed

        // kontrollerar om textfields är tomma
        String laggTillNamn = tfNamnLaggTillAvd.getText();

        if (laggTillNamn.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vänligen fyll i namn");

        }
        
        String laggTillBeskrivning = tfBeskrivningLaggTillAvd.getText();

        if (laggTillBeskrivning.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vänligen fyll i beskrivning");

        }
        
        String laggTillAdress = tfAdressLaggTillAvd.getText();

        if (laggTillAdress.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vänligen fyll i adress");

        }
        
        String laggTillEpost = tfEpostLaggTillAvd.getText();

        if (laggTillEpost.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vänligen fyll i E-post");

        }
        
        String laggTillTelefonnummer = tfTnrLaggTillAvd.getText();

        if (laggTillTelefonnummer.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vänligen fyll i telefonnummer");

        }
        
        String laggTillStad = tfStadLaggTillAvd.getText();

        if (laggTillStad.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vänligen fyll i stad");

        }
        
        
        String laggTillAvdChef = tfAvdchefLaggTillAvd.getText();

        if (laggTillAvdChef.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vänligen fyll i avdelningschef");

        }
        
        

        try {
            String avdID = tfAvdIDLaggTillAvd.getText();
            String namn = tfNamnLaggTillAvd.getText();
            String beskrivning = tfBeskrivningLaggTillAvd.getText();
            String adress = tfAdressLaggTillAvd.getText();
            String epost = tfEpostLaggTillAvd.getText();
            String telefonnummer = tfTnrLaggTillAvd.getText();
            String stad = tfTnrLaggTillAvd.getText();
            String avdchef = tfAvdchefLaggTillAvd.getText();

            String sqlFraga = " INSERT INTO avdelning WHERE avdid = (namn, beskrivning, adress, epost, telefon, stad, chef, avdid) VALUES ('" + namn + "','" + beskrivning + "', '" + adress + "', '" + epost + "', '" + telefonnummer + "', '" + stad + "', '" + avdchef + "''" + avdid + "')";
            idb.insert(sqlFraga);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }


    }//GEN-LAST:event_btnSparaLaggTillAvdActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSparaLaggTillAvd;
    private javax.swing.JButton btnTillbakaLaggTillAvd;
    private javax.swing.JLabel lblAdressLaggTillAvd;
    private javax.swing.JLabel lblAvdIDLaggTillAvd;
    private javax.swing.JLabel lblAvdchefLaggTillAvd;
    private javax.swing.JLabel lblBeskrivningLaggTillAvd;
    private javax.swing.JLabel lblEpostLaggTillAvd;
    private javax.swing.JLabel lblLaggTillAvdRuta;
    private javax.swing.JLabel lblNamnLaggTillAvd;
    private javax.swing.JLabel lblStadLaggTillAvd;
    private javax.swing.JLabel lblTnrLaggTillAvd;
    private javax.swing.JTextField tfAdressLaggTillAvd;
    private javax.swing.JTextField tfAvdIDLaggTillAvd;
    private javax.swing.JTextField tfAvdchefLaggTillAvd;
    private javax.swing.JTextField tfBeskrivningLaggTillAvd;
    private javax.swing.JTextField tfEpostLaggTillAvd;
    private javax.swing.JTextField tfNamnLaggTillAvd;
    private javax.swing.JTextField tfStadLaggTillAvd;
    private javax.swing.JTextField tfTnrLaggTillAvd;
    // End of variables declaration//GEN-END:variables
}
