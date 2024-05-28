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
        cbMal = new javax.swing.JComboBox<>();
        btnLaggTillMal = new javax.swing.JButton();

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

        btnLaggTillMal.setText("Lägg till hållbarhetsmål");
        btnLaggTillMal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLaggTillMalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblAvdelningAdminRuta)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addGap(26, 26, 26)
                                .addComponent(jButton3)
                                .addGap(26, 26, 26)
                                .addComponent(btnSparaAvdelningAdmin))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(cbValjAvdAdmin, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(21, 21, 21)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel8)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel6)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblHallbarhetsmalAvdAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGap(39, 39, 39)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(cbMal, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cbAvdchefAdmin, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(tfAdressAvdAdmin)
                                            .addComponent(tfEpostAvdAdmin)
                                            .addComponent(tfTnrAvdAdmin)
                                            .addComponent(cbStadAvdAdmin, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jScrollPane1)
                                            .addComponent(tfAvdelningsIDAvdAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(tfNamnAvdAdmin)
                                            .addComponent(tfBeskrivningAvdAdmin))))
                                .addComponent(btnLaggTillMal, javax.swing.GroupLayout.Alignment.TRAILING)))
                        .addGap(19, 141, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAvdelningAdminRuta)
                .addGap(8, 8, 8)
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tfBeskrivningAvdAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                        .addComponent(lblHallbarhetsmalAvdAdmin)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(51, 51, 51)
                .addComponent(cbMal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLaggTillMal)
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(btnSparaAvdelningAdmin)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
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

            JOptionPane.showMessageDialog(null, "Ändring sparad");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }


    }//GEN-LAST:event_btnSparaAvdelningAdminActionPerformed

    private void btnLaggTillMalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLaggTillMalActionPerformed
        String selectedMal = cbMal.getSelectedItem().toString();
        cbMal.removeItem(selectedMal);
        listModelhallbarhetsmal.addElement(selectedMal);
    }//GEN-LAST:event_btnLaggTillMalActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLaggTillMal;
    private javax.swing.JButton btnSparaAvdelningAdmin;
    private javax.swing.JComboBox<String> cbAvdchefAdmin;
    private javax.swing.JComboBox<String> cbMal;
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
