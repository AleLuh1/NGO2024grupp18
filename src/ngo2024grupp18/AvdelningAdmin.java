/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ngo2024grupp18;

import oru.inf.InfDB;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author ellenor
 */
public class AvdelningAdmin extends javax.swing.JFrame {

    private InfDB idb;
    private String aid;
    private String avdid;

    private DefaultListModel<String> listModelhallbarhetsmal = new DefaultListModel<>();

    private ArrayList<String> hallbarhetsmalSomSkaTasBort = new ArrayList<>();

    /**
     * Creates new form AvdelningAdmin
     */
    public AvdelningAdmin(InfDB idb, String aid, String avdid) {
        initComponents();
        this.idb = idb;
        this.aid = aid;
        this.avdid = avdid;
        this.setLocationRelativeTo(null);
        fyllCBValjAVD();
        fyllCBValjStad();
        fyllCBValjProjektchef();
    }

    //fyller list model för anställda list genom att hämta alla aid (och därefter fornam och efternamn) från projekt id i ans_proj tabel
    public void fyllHallbarhetsmal(String avdId) {
        listModelhallbarhetsmal.removeAllElements();
        try {
            String sqlFraga = "SELECT hid FROM avd_hallbarhet WHERE avdid = " + avdId;
            System.out.println(sqlFraga);
            ArrayList<HashMap<String, String>> hallbarhetsmalAvd = idb.fetchRows(sqlFraga);

            for (HashMap<String, String> ettMal : hallbarhetsmalAvd) {
                String sqlFraga1 = "SELECT namn FROM hallbarhetsmal WHERE hid=" + ettMal.get("hid");
                String hallbarhetsmalNamn = idb.fetchSingle(sqlFraga1);

                listModelhallbarhetsmal.addElement(hallbarhetsmalNamn);

            }

            jListHallbarhetsmalAvdAdmin.setModel(listModelhallbarhetsmal);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    // Lägger till alla avdelningar i combobox
    public void fyllCBValjAVD() {
        try {

            String sqlFraga = "SELECT DISTINCT namn FROM avdelning";
            System.out.println(sqlFraga);
            ArrayList<String> avdLista = idb.fetchColumn(sqlFraga);
            for (String enAvd : avdLista) {
                cbValjAvdAdmin.addItem(enAvd);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }
    }

    public void fyllCBHallbarhetsmal(String avdid) {
        cbHallbarhetsmal.removeAllItems();
        try {
            String sqlFraga = "SELECT hid FROM avd_hallbarhet WHERE avdid = " + avdid;
            System.out.println(sqlFraga);
            ArrayList<HashMap<String, String>> hallbarhetsmal = idb.fetchRows(sqlFraga);

            ArrayList<String> hidLista = new ArrayList<>();
            for (HashMap<String, String> ettMal : hallbarhetsmal) {
                hidLista.add(ettMal.get("hid"));
            }

            String allaHid = String.join(",", hidLista);

            String sqlFraga1;
            if (allaHid.isEmpty()) {
                sqlFraga1 = "SELECT namn FROM hallbarhetsmal";
            } else {
                sqlFraga1 = "SELECT namn FROM hallbarhetsmal WHERE hid NOT IN (" + allaHid + ")";
            }

            System.out.println(sqlFraga1);
            ArrayList<HashMap<String, String>> availableMal = idb.fetchRows(sqlFraga1);

            for (HashMap<String, String> mal : availableMal) {
                cbHallbarhetsmal.addItem(mal.get("namn"));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    // Lägger till alla städer i combobox
    public void fyllCBValjStad() {
        try {
            String sqlFraga = "SELECT DISTINCT namn FROM stad";
            System.out.println(sqlFraga);
            ArrayList<String> stadNamnLista = idb.fetchColumn(sqlFraga);

            for (String enStad : stadNamnLista) {
                cbStadAvdAdmin.addItem(enStad);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }

    }

    // Lägger till alla projektchefer i combobox
    public void fyllCBValjProjektchef() {
        try {

            String sqlFraga = "SELECT aid FROM handlaggare";
            System.out.println(sqlFraga);

            ArrayList<String> projektchefIdLista = idb.fetchColumn(sqlFraga);

            for (String enProjektchefId : projektchefIdLista) {
                String sqlFraga1 = "SELECT fornamn, efternamn FROM anstalld WHERE aid =" + enProjektchefId;
                HashMap<String, String> projektchef = idb.fetchRow(sqlFraga1);
                cbAvdchefAdmin.addItem(projektchef.get("fornamn") + " " + projektchef.get("efternamn"));
            }
        } catch (Exception ex) {
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

        lblAvdelningAdminRuta = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        tfAvdelningsIDAvdAdmin = new javax.swing.JTextField();
        tfNamnAvdAdmin = new javax.swing.JTextField();
        tfBeskrivningAvdAdmin = new javax.swing.JTextField();
        tfAdressAvdAdmin = new javax.swing.JTextField();
        tfEpostAvdAdmin = new javax.swing.JTextField();
        tfTnrAvdAdmin = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        btnSparaAvdelningAdmin = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        cbValjAvdAdmin = new javax.swing.JComboBox<>();
        cbStadAvdAdmin = new javax.swing.JComboBox<>();
        cbAvdchefAdmin = new javax.swing.JComboBox<>();
        lblHallbarhetsmalAvdAdmin = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListHallbarhetsmalAvdAdmin = new javax.swing.JList<>();
        btnLaggTilltMal = new javax.swing.JButton();
        btnTaBortMal = new javax.swing.JButton();
        cbHallbarhetsmal = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblAvdelningAdminRuta.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        lblAvdelningAdminRuta.setText("Avdelning ");

        jLabel1.setText("Namn");

        jLabel2.setText("Beskrivning");

        jLabel3.setText("Adress");

        jLabel4.setText("E-post");

        jLabel5.setText("Telefonnummer");

        jLabel6.setText("Stad");

        jLabel7.setText("Avdelningschef");

        jLabel8.setText("AvdelningsID");

        jButton1.setText("Tillbaka");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnSparaAvdelningAdmin.setText("Spara ändringar");
        btnSparaAvdelningAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSparaAvdelningAdminActionPerformed(evt);
            }
        });

        jButton3.setText("Lägg till avdelning");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        cbValjAvdAdmin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Välj avdelning" }));
        cbValjAvdAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbValjAvdAdminActionPerformed(evt);
            }
        });

        cbStadAvdAdmin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Välj stad" }));

        cbAvdchefAdmin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Välj avdelningschef" }));

        lblHallbarhetsmalAvdAdmin.setText("Hållbarhetsmål");

        jScrollPane1.setViewportView(jListHallbarhetsmalAvdAdmin);

        btnLaggTilltMal.setText("Lägg till mål");
        btnLaggTilltMal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLaggTilltMalActionPerformed(evt);
            }
        });

        btnTaBortMal.setText("Ta bort mål");
        btnTaBortMal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaBortMalActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblAvdelningAdminRuta)
                        .addGap(90, 90, 90)
                        .addComponent(tfBeskrivningAvdAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 220, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbValjAvdAdmin, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jLabel3)
                                                .addComponent(jLabel4)
                                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel6)
                                                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(lblHallbarhetsmalAvdAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(39, 39, 39)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(cbAvdchefAdmin, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(tfAdressAvdAdmin)
                                                .addComponent(tfEpostAvdAdmin)
                                                .addComponent(tfTnrAvdAdmin)
                                                .addComponent(cbStadAvdAdmin, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jScrollPane1)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addGap(201, 201, 201)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(btnTaBortMal)
                                                        .addComponent(btnLaggTilltMal)))))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addGap(101, 101, 101)
                                            .addComponent(cbHallbarhetsmal, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel8)
                                                .addComponent(jLabel1)
                                                .addComponent(jLabel2))
                                            .addGap(52, 52, 52)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jScrollPane2)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(tfNamnAvdAdmin, javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(tfAvdelningsIDAvdAdmin, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE))
                                                    .addGap(0, 0, Short.MAX_VALUE)))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButton1)
                                        .addGap(26, 26, 26)
                                        .addComponent(jButton3)
                                        .addGap(26, 26, 26)
                                        .addComponent(btnSparaAvdelningAdmin)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAvdelningAdminRuta)
                    .addComponent(tfBeskrivningAvdAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addComponent(cbValjAvdAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(tfAvdelningsIDAvdAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tfNamnAvdAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tfAdressAvdAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(tfEpostAvdAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(tfTnrAvdAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(cbStadAvdAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cbAvdchefAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(lblHallbarhetsmalAvdAdmin))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addComponent(btnTaBortMal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLaggTilltMal)
                    .addComponent(cbHallbarhetsmal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(btnSparaAvdelningAdmin)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
        Meny nyMeny = new Meny(idb, aid, avdid);
        nyMeny.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cbValjAvdAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbValjAvdAdminActionPerformed

        try {
            String avdNamn = cbValjAvdAdmin.getSelectedItem().toString();
            String sqlFraga = "SELECT * FROM avdelning WHERE namn = '" + avdNamn + "'";
            System.out.println(sqlFraga);

            HashMap<String, String> avdLista = idb.fetchRow(sqlFraga);

            if (avdLista != null) {
                fyllHallbarhetsmal(avdLista.get("avdid"));
                fyllCBHallbarhetsmal(avdLista.get("avdid"));
                String sqlFraga2 = " SELECT namn FROM stad WHERE sid = '" + avdLista.get("stad") + "'";
                String stad = idb.fetchSingle(sqlFraga2);

                String sqlFraga3 = "SELECT fornamn, efternamn FROM anstalld WHERE aid = '" + avdLista.get("chef") + "'";
                HashMap<String, String> avdChef = idb.fetchRow(sqlFraga3);

                tfAvdelningsIDAvdAdmin.setText(avdLista.get("avdid"));
                tfNamnAvdAdmin.setText(avdLista.get("namn"));
                tfBeskrivningAvdAdmin.setText(avdLista.get("beskrivning"));
                tfAdressAvdAdmin.setText(avdLista.get("adress"));
                tfEpostAvdAdmin.setText(avdLista.get("epost"));
                tfTnrAvdAdmin.setText(avdLista.get("telefon"));

                cbStadAvdAdmin.setSelectedItem(stad);
                if (stad == null) {
                    cbStadAvdAdmin.setSelectedItem("Välj stad");
                }

                cbAvdchefAdmin.setSelectedItem(avdChef.get("fornamn") + " " + avdChef.get("efternamn"));
                if (avdChef.get("fornamn") == null) {
                    cbAvdchefAdmin.setSelectedItem("Välj avdelningschef");
                }
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }//GEN-LAST:event_cbValjAvdAdminActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        new LaggTillAvdelning(idb, aid, avdid).setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnSparaAvdelningAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSparaAvdelningAdminActionPerformed

        try {
            String avdId = tfAvdelningsIDAvdAdmin.getText();
            String avdNamn = tfNamnAvdAdmin.getText();
            String avdBeskrivning = tfBeskrivningAvdAdmin.getText();
            String avdAddress = tfAdressAvdAdmin.getText();

            String avdEpost = tfEpostAvdAdmin.getText();
            String avdTnr = tfTnrAvdAdmin.getText();
            String avdStad = cbStadAvdAdmin.getSelectedItem().toString();
            String avdChef = cbAvdchefAdmin.getSelectedItem().toString();;

            String sqlFragaStadId = "SELECT sid FROM stad WHERE namn = '" + avdStad + "'";
            String stadId = idb.fetchSingle(sqlFragaStadId);

            String fornamn = avdChef.split(" ")[0];
            String efternamn = avdChef.split(" ")[1];

            String sqlFragaChefId = "SELECT aid FROM anstalld WHERE fornamn = '" + fornamn + "' AND efternamn = '" + efternamn + "'";

            String chefId = idb.fetchSingle(sqlFragaChefId);

            String sqlFragaUpdate = "UPDATE avdelning SET namn = '" + avdNamn + "', beskrivning = '" + avdBeskrivning + "', adress = '" + avdAddress + "', epost = '" + avdEpost + "', telefon = '" + avdTnr + "', stad = " + stadId + ", chef = " + chefId + " WHERE avdid=" + avdId;
            idb.update(sqlFragaUpdate);

            cbValjAvdAdmin.removeAllItems();
            fyllCBValjAVD();
            
            for (int i = 0; i < listModelhallbarhetsmal.size(); i++) {
                String malName = listModelhallbarhetsmal.getElementAt(i);

                String sqlFragaHid = "SELECT hid FROM hallbarhetsmal WHERE namn = '" + malName + "'";
                String hid = idb.fetchSingle(sqlFragaHid);

                String sqlFragaAvdMal = "INSERT INTO avd_hallbarhet (avdid, hid) "
                        + "SELECT " + avdId + ", " + hid + " WHERE NOT EXISTS (SELECT 1 FROM avd_hallbarhet WHERE avdid = " + avdId + " AND hid = " + hid + ")";

                idb.insert(sqlFragaAvdMal);
                
            }
            
             for (String tagitBortMal : hallbarhetsmalSomSkaTasBort) {
                String sqlFragaHid = "SELECT hid FROM hallbarhetsmal WHERE namn = '" + tagitBortMal + "'";
                String hid = idb.fetchSingle(sqlFragaHid);

                String sqlFragaTaBortPartner = "DELETE FROM avd_hallbarhet WHERE avdid =" + avdId + " AND hid =" + hid;
                idb.delete(sqlFragaTaBortPartner);

            }
             
             fyllHallbarhetsmal(avdId);

            JOptionPane.showMessageDialog(null, "Ändring sparad");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }


    }//GEN-LAST:event_btnSparaAvdelningAdminActionPerformed

    private void btnTaBortMalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaBortMalActionPerformed

        String mal = jListHallbarhetsmalAvdAdmin.getSelectedValue();
        listModelhallbarhetsmal.removeElement(mal);
        cbHallbarhetsmal.addItem(mal);
        hallbarhetsmalSomSkaTasBort.add(mal);

    }//GEN-LAST:event_btnTaBortMalActionPerformed

    private void btnLaggTilltMalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLaggTilltMalActionPerformed
        hallbarhetsmalSomSkaTasBort.clear();
        String selectedMal = cbHallbarhetsmal.getSelectedItem().toString();
        cbHallbarhetsmal.removeItem(selectedMal);
        listModelhallbarhetsmal.addElement(selectedMal);        // TODO add your handling code here:
    }//GEN-LAST:event_btnLaggTilltMalActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLaggTilltMal;
    private javax.swing.JButton btnSparaAvdelningAdmin;
    private javax.swing.JButton btnTaBortMal;
    private javax.swing.JComboBox<String> cbAvdchefAdmin;
    private javax.swing.JComboBox<String> cbHallbarhetsmal;
    private javax.swing.JComboBox<String> cbStadAvdAdmin;
    private javax.swing.JComboBox<String> cbValjAvdAdmin;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JList<String> jListHallbarhetsmalAvdAdmin;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel lblAvdelningAdminRuta;
    private javax.swing.JLabel lblHallbarhetsmalAvdAdmin;
    private javax.swing.JTextField tfAdressAvdAdmin;
    private javax.swing.JTextField tfAvdelningsIDAvdAdmin;
    private javax.swing.JTextField tfBeskrivningAvdAdmin;
    private javax.swing.JTextField tfEpostAvdAdmin;
    private javax.swing.JTextField tfNamnAvdAdmin;
    private javax.swing.JTextField tfTnrAvdAdmin;
    // End of variables declaration//GEN-END:variables
}
