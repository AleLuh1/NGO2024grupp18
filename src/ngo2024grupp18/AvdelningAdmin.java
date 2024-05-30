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

        tfAvdelningsIDAvdAdmin.setEditable(false);

        // Delar upp en textrad i flera rader. 
        taBeskrivningAvdAdmin.setLineWrap(true);
        taBeskrivningAvdAdmin.setWrapStyleWord(true);
        taBeskrivningAvdAdmin.setEditable(false);
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
            ex.printStackTrace();
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
            ex.printStackTrace();

        }
    }

    public void fyllCBMal(String avdId) {
        cbMal.removeAllItems();
        try {
            String sqlFraga = "SELECT hid FROM avd_hallbarhet WHERE avdid = " + avdId;
            System.out.println(sqlFraga);
            ArrayList<HashMap<String, String>> hallbarhetsmalAvd = idb.fetchRows(sqlFraga);

            ArrayList<String> hidList = new ArrayList<>();
            for (HashMap<String, String> ettMal : hallbarhetsmalAvd) {
                hidList.add(ettMal.get("hid"));
            }

            String allaHid = String.join(",", hidList);
            String sqlFraga1;
            if (hidList.isEmpty()) {
                sqlFraga1 = "SELECT namn FROM hallbarhetsmal";
            } else {
                //använder string med alla aid för att hämta alla namn och efternamn på anställda som inte är med i listan
                sqlFraga1 = "SELECT namn FROM hallbarhetsmal WHERE hid NOT IN (" + allaHid + ")";
            }
            System.out.println(sqlFraga1);
            ArrayList<HashMap<String, String>> availableMal = idb.fetchRows(sqlFraga1);

            for (HashMap<String, String> avdMal : availableMal) {
                cbMal.addItem(avdMal.get("namn"));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void fyllCBValjStad() {
        try {
            String sqlFraga = "SELECT DISTINCT namn FROM stad";
            System.out.println(sqlFraga);
            ArrayList<String> stadNamnLista = idb.fetchColumn(sqlFraga);

            for (String enStad : stadNamnLista) {
                cbStadAvdAdmin.addItem(enStad);
            }

        } catch (Exception ex) {
            ex.printStackTrace();

        }

    }

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
            ex.printStackTrace();

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
        lblNamnAvd = new javax.swing.JLabel();
        lblBeskrivningAvd = new javax.swing.JLabel();
        lblAdressAvd = new javax.swing.JLabel();
        lblEpostAvd = new javax.swing.JLabel();
        lblTelnrAvd = new javax.swing.JLabel();
        lblStadAvd = new javax.swing.JLabel();
        lblAvdelningschef = new javax.swing.JLabel();
        lblAvdelningsID = new javax.swing.JLabel();
        tfAvdelningsIDAvdAdmin = new javax.swing.JTextField();
        tfNamnAvdAdmin = new javax.swing.JTextField();
        tfAdressAvdAdmin = new javax.swing.JTextField();
        tfEpostAvdAdmin = new javax.swing.JTextField();
        tfTnrAvdAdmin = new javax.swing.JTextField();
        btnTillbaka = new javax.swing.JButton();
        btnSparaAvdelningAdmin = new javax.swing.JButton();
        btnLaggTillAvd = new javax.swing.JButton();
        cbValjAvdAdmin = new javax.swing.JComboBox<>();
        cbStadAvdAdmin = new javax.swing.JComboBox<>();
        cbAvdchefAdmin = new javax.swing.JComboBox<>();
        lblHallbarhetsmalAvdAdmin = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListHallbarhetsmalAvdAdmin = new javax.swing.JList<>();
        cbMal = new javax.swing.JComboBox<>();
        btnLaggTillMal = new javax.swing.JButton();
        btnTaBortMal = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        taBeskrivningAvdAdmin = new javax.swing.JTextArea();
        lblMarkeraTaBortHB = new javax.swing.JLabel();
        lblValjHBAttLaggaTill = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblAvdelningAdminRuta.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        lblAvdelningAdminRuta.setText("Avdelning ");

        lblNamnAvd.setText("Namn");

        lblBeskrivningAvd.setText("Beskrivning");

        lblAdressAvd.setText("Adress");

        lblEpostAvd.setText("E-post");

        lblTelnrAvd.setText("Telefonnummer");

        lblStadAvd.setText("Stad");

        lblAvdelningschef.setText("Avdelningschef");

        lblAvdelningsID.setText("AvdelningsID");

        btnTillbaka.setText("Tillbaka");
        btnTillbaka.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTillbakaActionPerformed(evt);
            }
        });

        btnSparaAvdelningAdmin.setText("Spara ändringar");
        btnSparaAvdelningAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSparaAvdelningAdminActionPerformed(evt);
            }
        });

        btnLaggTillAvd.setText("Lägg till avdelning");
        btnLaggTillAvd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLaggTillAvdActionPerformed(evt);
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

        btnLaggTillMal.setText("Lägg till ");
        btnLaggTillMal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLaggTillMalActionPerformed(evt);
            }
        });

        btnTaBortMal.setText("Ta bort ");
        btnTaBortMal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaBortMalActionPerformed(evt);
            }
        });

        taBeskrivningAvdAdmin.setColumns(20);
        taBeskrivningAvdAdmin.setLineWrap(true);
        taBeskrivningAvdAdmin.setRows(5);
        taBeskrivningAvdAdmin.setAutoscrolls(false);
        jScrollPane2.setViewportView(taBeskrivningAvdAdmin);

        lblMarkeraTaBortHB.setText("Markera för att ta bort hållbarhetsmål");

        lblValjHBAttLaggaTill.setText("Välj hållbarhetsmål att lägga till");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTelnrAvd)
                            .addComponent(lblStadAvd)
                            .addComponent(lblAvdelningschef)
                            .addComponent(btnTillbaka))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbAvdchefAdmin, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblHallbarhetsmalAvdAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE)
                            .addComponent(cbMal, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnLaggTillMal)
                                    .addComponent(btnTaBortMal)
                                    .addComponent(lblMarkeraTaBortHB)
                                    .addComponent(lblValjHBAttLaggaTill))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblEpostAvd)
                            .addComponent(lblAdressAvd)
                            .addComponent(lblAvdelningAdminRuta)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblAvdelningsID)
                                    .addComponent(lblNamnAvd)
                                    .addComponent(lblBeskrivningAvd))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tfNamnAvdAdmin)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
                                    .addComponent(tfAdressAvdAdmin)
                                    .addComponent(tfEpostAvdAdmin)
                                    .addComponent(tfTnrAvdAdmin)
                                    .addComponent(cbStadAvdAdmin, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tfAvdelningsIDAvdAdmin))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSparaAvdelningAdmin))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(cbValjAvdAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLaggTillAvd)))
                .addGap(27, 27, 27))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblAvdelningAdminRuta)
                        .addGap(18, 18, 18)
                        .addComponent(cbValjAvdAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(btnLaggTillAvd, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblAvdelningsID)
                            .addComponent(tfAvdelningsIDAvdAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNamnAvd)
                            .addComponent(tfNamnAvdAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblBeskrivningAvd)
                                .addGap(106, 106, 106)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblAdressAvd)
                                    .addComponent(tfAdressAvdAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblEpostAvd)
                                    .addComponent(tfEpostAvdAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(8, 8, 8)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblTelnrAvd)
                                    .addComponent(tfTnrAvdAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblStadAvd)
                                    .addComponent(cbStadAvdAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblAvdelningschef)
                            .addComponent(cbAvdchefAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblHallbarhetsmalAvdAdmin)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                        .addComponent(lblMarkeraTaBortHB)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTaBortMal)))
                .addGap(18, 18, 18)
                .addComponent(lblValjHBAttLaggaTill)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbMal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLaggTillMal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTillbaka)
                    .addComponent(btnSparaAvdelningAdmin))
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTillbakaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTillbakaActionPerformed
        this.dispose();
        Meny nyMeny = new Meny(idb, aid, avdid);
        nyMeny.setVisible(true);
    }//GEN-LAST:event_btnTillbakaActionPerformed

    private void cbValjAvdAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbValjAvdAdminActionPerformed

        try {
            String avdNamn = cbValjAvdAdmin.getSelectedItem().toString();
            String sqlFraga = "SELECT * FROM avdelning WHERE namn = '" + avdNamn + "'";
            System.out.println(sqlFraga);

            HashMap<String, String> avdLista = idb.fetchRow(sqlFraga);

            String avdId = avdLista.get("avdid");
            if (avdLista != null) {
                fyllCBMal(avdId);
                fyllHallbarhetsmal(avdId);
                String sqlFraga2 = " SELECT namn FROM stad WHERE sid = '" + avdLista.get("stad") + "'";
                String stad = idb.fetchSingle(sqlFraga2);

                String sqlFraga3 = "SELECT fornamn, efternamn FROM anstalld WHERE aid = '" + avdLista.get("chef") + "'";
                HashMap<String, String> avdChef = idb.fetchRow(sqlFraga3);

                tfAvdelningsIDAvdAdmin.setText(avdLista.get("avdid"));
                tfNamnAvdAdmin.setText(avdLista.get("namn"));
                taBeskrivningAvdAdmin.setText(avdLista.get("beskrivning"));
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
            ex.printStackTrace();
        }

    }//GEN-LAST:event_cbValjAvdAdminActionPerformed

    private void btnLaggTillAvdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLaggTillAvdActionPerformed
        new LaggTillAvdelning(idb, aid, avdid).setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_btnLaggTillAvdActionPerformed

    private void btnSparaAvdelningAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSparaAvdelningAdminActionPerformed

        try {
            String avdId = tfAvdelningsIDAvdAdmin.getText();

            String avdNamn = tfNamnAvdAdmin.getText();
            String avdBeskrivning = taBeskrivningAvdAdmin.getText();
            String avdAddress = tfAdressAvdAdmin.getText();

            String avdEpost = tfEpostAvdAdmin.getText();
            String avdTnr = tfTnrAvdAdmin.getText();
            if (!Validering.isKorrektFormatTelnr(avdTnr)) {
                return;
            }
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

            for (int i = 0; i < listModelhallbarhetsmal.size(); i++) {
                String mal = listModelhallbarhetsmal.getElementAt(i);

                //hämtar aid från anstalld tabell genom att använda fornamn och efternamn
                String sqlFragaMalId = "SELECT hid FROM hallbarhetsmal WHERE namn = '" + mal + "'";
                String hid = idb.fetchSingle(sqlFragaMalId);

                //skapa ny item i tabellen ans_proj med pid och aid. Använder WHERE NOT EXIST för att kontrollera att det inte finns en annan item som har redan pid+aid
                String sqlFragaAvdMall = "INSERT INTO avd_hallbarhet (avdid, hid) "
                        + "SELECT " + avdId + ", " + hid + " "
                        + "WHERE NOT EXISTS (SELECT 1 FROM avd_hallbarhet WHERE avdid = " + avdId + " AND hid = " + hid + ")";

                idb.insert(sqlFragaAvdMall);

            }

            for (String tagitBortMal : hallbarhetsmalSomSkaTasBort) {

                String sqlFragaTagitBortMalId = "SELECT hid FROM hallbarhetsmal WHERE namn = '" + tagitBortMal + "'";
                String hid = idb.fetchSingle(sqlFragaTagitBortMalId);

                String sqlFragaTaBort = "DELETE FROM avd_hallbarhet WHERE avdid =" + avdId + " AND hid =" + hid;
                idb.delete(sqlFragaTaBort);

            }

            JOptionPane.showMessageDialog(null, "Ändring sparad");
        } catch (Exception ex) {
            ex.printStackTrace();

        }


    }//GEN-LAST:event_btnSparaAvdelningAdminActionPerformed

    private void btnLaggTillMalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLaggTillMalActionPerformed
        if (cbMal.getItemCount() > 0) {
            hallbarhetsmalSomSkaTasBort.clear();
            String selectedMal = cbMal.getSelectedItem().toString();
            cbMal.removeItem(selectedMal);
            listModelhallbarhetsmal.addElement(selectedMal);
        }

    }//GEN-LAST:event_btnLaggTillMalActionPerformed

    private void btnTaBortMalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaBortMalActionPerformed
        String mal = jListHallbarhetsmalAvdAdmin.getSelectedValue();
        listModelhallbarhetsmal.removeElement(mal);
        cbMal.addItem(mal);
        hallbarhetsmalSomSkaTasBort.add(mal);
    }//GEN-LAST:event_btnTaBortMalActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLaggTillAvd;
    private javax.swing.JButton btnLaggTillMal;
    private javax.swing.JButton btnSparaAvdelningAdmin;
    private javax.swing.JButton btnTaBortMal;
    private javax.swing.JButton btnTillbaka;
    private javax.swing.JComboBox<String> cbAvdchefAdmin;
    private javax.swing.JComboBox<String> cbMal;
    private javax.swing.JComboBox<String> cbStadAvdAdmin;
    private javax.swing.JComboBox<String> cbValjAvdAdmin;
    private javax.swing.JList<String> jListHallbarhetsmalAvdAdmin;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblAdressAvd;
    private javax.swing.JLabel lblAvdelningAdminRuta;
    private javax.swing.JLabel lblAvdelningsID;
    private javax.swing.JLabel lblAvdelningschef;
    private javax.swing.JLabel lblBeskrivningAvd;
    private javax.swing.JLabel lblEpostAvd;
    private javax.swing.JLabel lblHallbarhetsmalAvdAdmin;
    private javax.swing.JLabel lblMarkeraTaBortHB;
    private javax.swing.JLabel lblNamnAvd;
    private javax.swing.JLabel lblStadAvd;
    private javax.swing.JLabel lblTelnrAvd;
    private javax.swing.JLabel lblValjHBAttLaggaTill;
    private javax.swing.JTextArea taBeskrivningAvdAdmin;
    private javax.swing.JTextField tfAdressAvdAdmin;
    private javax.swing.JTextField tfAvdelningsIDAvdAdmin;
    private javax.swing.JTextField tfEpostAvdAdmin;
    private javax.swing.JTextField tfNamnAvdAdmin;
    private javax.swing.JTextField tfTnrAvdAdmin;
    // End of variables declaration//GEN-END:variables
}
