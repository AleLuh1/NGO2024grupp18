/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ngo2024grupp18;

import oru.inf.InfDB;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author alex
 */
public class AllaProjekt extends javax.swing.JFrame {

    private InfDB idb;
    private String aid;
    private String avdid;
    private DefaultListModel<String> listModel;
    private DefaultListModel<String> listModel2;

    /**
     * Creates new form AllaProjekt
     */
    public AllaProjekt(InfDB idb, String aid, String avdid) {
        initComponents();
        this.idb = idb;
        this.aid = aid;
        this.avdid = avdid;
        fyllCBAllaProjekt();
        fyllHBAllaProjekt();
        fyllCBAllaAnstallda();
        fyllCBProjektChef();
        fyllCBLander();
        fyllPaStatus();
        fyllPaPrioritet();
        this.setLocationRelativeTo(null);
    }

    // CB = combobox
    public void fyllCBAllaProjekt() {
        try {
            String sqlFraga = "SELECT projektnamn FROM projekt";
            System.out.println(sqlFraga);
            ArrayList<String> projektNamnLista = idb.fetchColumn(sqlFraga);

            for (String ettProjekt : projektNamnLista) {
                cbAllaProjekt.addItem(ettProjekt);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }

    }

    // Fyller i CB för hallbarhetsmål ifall man vill lägga till nytt hallbarhetsmål på befintligt projekt
    public void fyllHBAllaProjekt() {
        try {
            String sqlFraga = "SELECT namn FROM hallbarhetsmal";
            System.out.println(sqlFraga);
            ArrayList<String> allaHBMal = idb.fetchColumn(sqlFraga);

            for (String ettMal : allaHBMal) {
                cbAllaHallbMal.addItem(ettMal);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    //FYller i Cb för alla anstallda
    public void fyllCBAllaAnstallda() {
        try {
            String sqlFraga = "SELECT CONCAT(fornamn, ' ', efternamn) FROM anstalld";
            System.out.println(sqlFraga);
            ArrayList<String> anstalldAidLista = idb.fetchColumn(sqlFraga);

            for (String enAid : anstalldAidLista) {
                cbAllaAnstallda.addItem(enAid);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }

    }

    //Fyller i CB för projektchef
    public void fyllCBProjektChef() {
        try {
            cbProjektchefAllaProjekt.removeAllItems();
            String sqlFraga = "SELECT DISTINCT projektchef FROM projekt";
            System.out.println(sqlFraga);
            ArrayList<String> projektchefIdLista = idb.fetchColumn(sqlFraga);

            for (String enProjektchefId : projektchefIdLista) {
                String sqlFraga1 = "SELECT fornamn, efternamn FROM anstalld WHERE aid =" + enProjektchefId;
                HashMap<String, String> projektchef = idb.fetchRow(sqlFraga1);
                cbProjektchefAllaProjekt.addItem(projektchef.get("fornamn") + " " + projektchef.get("efternamn"));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }
    }

    //Fyller i CB för länder
    private void fyllCBLander() {
        try {
            cbLandAllaProjekt.removeAllItems();
            String sqlFraga4 = "SELECT namn FROM land";
            ArrayList<HashMap<String, String>> allaLander = idb.fetchRows(sqlFraga4);
            for (HashMap<String, String> ettLand : allaLander) {
                cbLandAllaProjekt.addItem(ettLand.get("namn"));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void fyllPaStatus() {
        cbStatusAllaProjekt.removeAllItems();
        cbStatusAllaProjekt.addItem("Planerat");
        cbStatusAllaProjekt.addItem("Pågående");
        cbStatusAllaProjekt.addItem("Avslutat");

    }

    private void fyllPaPrioritet() {
        cbPrioAllaProjekt.removeAllItems();
        cbPrioAllaProjekt.addItem("Hög");
        cbPrioAllaProjekt.addItem("Medel");
        cbPrioAllaProjekt.addItem("Låg");

    }
    
    // Genererar ny projekt-id (pid) genom att lägga till +1 på största befintliga pid
    private String LaggaTillNyPid() {
        String nyProjektPid = null;
        try {
            //SQL-fråga för att hämta ut största pid som finns i projekt
            String sqlFragaHogstPid = "SELECT max(pid) FROM projekt";
            System.out.println(sqlFragaHogstPid);
            //Hämtar ut resultatet från sql-frågan i en sträng 
            String hogstProjektPidDB = idb.fetchSingle(sqlFragaHogstPid);
            //Konverterar strängen med högst pid till en int
            int hogstProjektPidInt = Integer.parseInt(hogstProjektPidDB);
            int nyProjektPidInt = hogstProjektPidInt + 1;
            System.out.println(nyProjektPidInt);
            nyProjektPid = Integer.toString(nyProjektPidInt);
            tfNyttProjektNyPid.setText(nyProjektPid);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return nyProjektPid;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnTillbakaAllaProj = new javax.swing.JButton();
        lblAllaProjektRuta = new javax.swing.JLabel();
        cbAllaProjekt = new javax.swing.JComboBox<>();
        lblBeskrivningAllaProjekt = new javax.swing.JLabel();
        lblStartDatumAllaProjekt = new javax.swing.JLabel();
        lblSlutdatumAllaProjekt = new javax.swing.JLabel();
        lblKostnadAllaProjekt = new javax.swing.JLabel();
        lblStatusAllaProjekt = new javax.swing.JLabel();
        lblPrioAllaProjekt = new javax.swing.JLabel();
        lblLandAllaProjekt = new javax.swing.JLabel();
        lblProduktchefAllaProjekt = new javax.swing.JLabel();
        tfBeskrivningAllaProjekt = new javax.swing.JTextField();
        tfStartdatumAllaProjekt = new javax.swing.JTextField();
        tfSlutDatumAllaProjekt = new javax.swing.JTextField();
        tfKostnadAllaProjekt = new javax.swing.JTextField();
        bnTaBortAllaProjekt = new javax.swing.JButton();
        btnAndraAllaProjekt = new javax.swing.JButton();
        lblProjektnamnAllaProjekt = new javax.swing.JLabel();
        tfProjektnamnAllaProjekt = new javax.swing.JTextField();
        tfNyttProjektNyPid = new javax.swing.JTextField();
        lblNyttProjektNyPid = new javax.swing.JLabel();
        btnLaggTillProjektAllaProjekt = new javax.swing.JButton();
        lblAllaProjHallbMal = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        cbAllaHallbMal = new javax.swing.JComboBox<>();
        lblLaggTillHBMal = new javax.swing.JLabel();
        btnLaggTillHBMal = new javax.swing.JButton();
        lblAllaProjAnstallda = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListAllaAnstallda = new javax.swing.JList<>();
        cbAllaAnstallda = new javax.swing.JComboBox<>();
        lblAllaProjLaggTillAnstalld = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        cbProjektchefAllaProjekt = new javax.swing.JComboBox<>();
        cbStatusAllaProjekt = new javax.swing.JComboBox<>();
        cbPrioAllaProjekt = new javax.swing.JComboBox<>();
        cbLandAllaProjekt = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnTillbakaAllaProj.setText("Tillbaka");
        btnTillbakaAllaProj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTillbakaAllaProjActionPerformed(evt);
            }
        });

        lblAllaProjektRuta.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        lblAllaProjektRuta.setText("Alla projekt");

        cbAllaProjekt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Välj projekt" }));
        cbAllaProjekt.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbAllaProjektPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        lblBeskrivningAllaProjekt.setText("Beskrivning");

        lblStartDatumAllaProjekt.setText("Startdatum");

        lblSlutdatumAllaProjekt.setText("Slutdatum");

        lblKostnadAllaProjekt.setText("Kostnad");

        lblStatusAllaProjekt.setText("Status");

        lblPrioAllaProjekt.setText("Prioritet");

        lblLandAllaProjekt.setText("Land");

        lblProduktchefAllaProjekt.setText("Projektchef");

        bnTaBortAllaProjekt.setText("Ta bort projekt");
        bnTaBortAllaProjekt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bnTaBortAllaProjektActionPerformed(evt);
            }
        });

        btnAndraAllaProjekt.setText("Spara ändringar");
        btnAndraAllaProjekt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAndraAllaProjektActionPerformed(evt);
            }
        });

        lblProjektnamnAllaProjekt.setText("Projektnamn");

        lblNyttProjektNyPid.setText("Projekt-ID");

        btnLaggTillProjektAllaProjekt.setText("Lägg till projekt");
        btnLaggTillProjektAllaProjekt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLaggTillProjektAllaProjektActionPerformed(evt);
            }
        });

        lblAllaProjHallbMal.setText("Aktuella hållbarhetsmål");

        jScrollPane1.setViewportView(jList1);

        cbAllaHallbMal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Välj ett mål" }));

        lblLaggTillHBMal.setText("Lägg till hallbarhetsmål");

        btnLaggTillHBMal.setText("Lägg till hållbarhetsmål");
        btnLaggTillHBMal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLaggTillHBMalActionPerformed(evt);
            }
        });

        lblAllaProjAnstallda.setText("Anställda i projektet");

        jScrollPane2.setViewportView(jListAllaAnstallda);

        lblAllaProjLaggTillAnstalld.setText("Lägg till anställd");

        jButton2.setText("Lägg till anställd");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        cbProjektchefAllaProjekt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ändra projektchef" }));

        cbStatusAllaProjekt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ändra status" }));

        cbPrioAllaProjekt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ändra prioritet" }));

        cbLandAllaProjekt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ändra land" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbAllaProjekt, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAllaProjektRuta)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lblProjektnamnAllaProjekt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblProduktchefAllaProjekt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblNyttProjektNyPid, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(btnTillbakaAllaProj))
                    .addComponent(lblAllaProjAnstallda, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAllaProjLaggTillAnstalld, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblBeskrivningAllaProjekt)
                    .addComponent(lblStartDatumAllaProjekt)
                    .addComponent(lblSlutdatumAllaProjekt)
                    .addComponent(lblKostnadAllaProjekt)
                    .addComponent(lblStatusAllaProjekt)
                    .addComponent(lblPrioAllaProjekt)
                    .addComponent(lblLandAllaProjekt)
                    .addComponent(lblAllaProjHallbMal, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLaggTillHBMal, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(btnAndraAllaProjekt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(bnTaBortAllaProjekt)
                            .addComponent(btnLaggTillProjektAllaProjekt))
                        .addGap(60, 60, 60))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cbLandAllaProjekt, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbPrioAllaProjekt, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbStatusAllaProjekt, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbProjektchefAllaProjekt, javax.swing.GroupLayout.Alignment.LEADING, 0, 187, Short.MAX_VALUE)
                            .addComponent(cbAllaAnstallda, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbAllaHallbMal, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(tfKostnadAllaProjekt, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfProjektnamnAllaProjekt, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfNyttProjektNyPid, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfBeskrivningAllaProjekt)
                            .addComponent(tfStartdatumAllaProjekt)
                            .addComponent(tfSlutDatumAllaProjekt)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnLaggTillHBMal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(87, 87, 87))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(lblAllaProjektRuta)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbAllaProjekt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLaggTillProjektAllaProjekt))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNyttProjektNyPid)
                    .addComponent(tfNyttProjektNyPid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblProjektnamnAllaProjekt, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfProjektnamnAllaProjekt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblProduktchefAllaProjekt)
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblAllaProjAnstallda)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbAllaAnstallda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblAllaProjLaggTillAnstalld)
                            .addComponent(jButton2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfBeskrivningAllaProjekt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblBeskrivningAllaProjekt))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfStartdatumAllaProjekt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblStartDatumAllaProjekt, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfSlutDatumAllaProjekt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSlutdatumAllaProjekt, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfKostnadAllaProjekt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblKostnadAllaProjekt))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblStatusAllaProjekt)
                            .addComponent(cbStatusAllaProjekt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblPrioAllaProjekt)
                            .addComponent(cbPrioAllaProjekt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblLandAllaProjekt)
                            .addComponent(cbLandAllaProjekt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblAllaProjHallbMal))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblLaggTillHBMal)
                            .addComponent(cbAllaHallbMal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLaggTillHBMal))
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnTillbakaAllaProj)
                            .addComponent(btnAndraAllaProjekt)
                            .addComponent(bnTaBortAllaProjekt))
                        .addGap(42, 42, 42))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cbProjektchefAllaProjekt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbAllaProjektPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbAllaProjektPopupMenuWillBecomeInvisible
        // Hämtar ut projektnamnet för det selekterade projektet
        String projektNamn = cbAllaProjekt.getSelectedItem().toString();

        try {
            String sqlFraga = "SELECT * FROM projekt WHERE projektnamn = '" + projektNamn + "'";
            System.out.println(sqlFraga);

            String sqlProjnamnTillPid = "SELECT pid FROM projekt WHERE projektnamn = '" + projektNamn + "'";
            System.out.println(sqlProjnamnTillPid);
            String pidStr = idb.fetchSingle(sqlProjnamnTillPid);
            int pid = Integer.parseInt(pidStr);

            HashMap<String, String> projektNamnLista = idb.fetchRow(sqlFraga);

            String sqlFragaHallbMal = "SELECT DISTINCT namn FROM hallbarhetsmal JOIN proj_hallbarhet ON proj_hallbarhet.hid = hallbarhetsmal.hid JOIN projekt ON projekt.pid = proj_hallbarhet.pid WHERE projektnamn = '" + projektNamn + "'";
            System.out.println(sqlFragaHallbMal);
            ArrayList<String> projektHallbMal = idb.fetchColumn(sqlFragaHallbMal);

            // Hämtar namn på alla anställda som jobbar 
            String sqlFragaAnstalldNamn = "SELECT CONCAT(fornamn, ' ', efternamn) FROM anstalld JOIN ans_proj ON ans_proj.aid = anstalld.aid WHERE ans_proj.pid = " + pid;
            System.out.println(sqlFragaAnstalldNamn);
            ArrayList<String> anstalldaIProjLista = idb.fetchColumn(sqlFragaAnstalldNamn);

            //Testar med en JList för att fylla i namn på alla anställda som jobbar på projektet som har selekterats
            listModel2 = new DefaultListModel<String>();
            for (int i = 0; i < anstalldaIProjLista.size(); i++) {
                listModel2.addElement(anstalldaIProjLista.get(i));
            }
            jListAllaAnstallda.setModel(listModel2);

            // Testar med en JList för att visa alla hallbarhetsmål 
            listModel = new DefaultListModel<String>();
            for (int i = 0; i < projektHallbMal.size(); i++) {
                listModel.addElement(projektHallbMal.get(i));
            }
            jList1.setModel(listModel);

            JList<String> jList = new JList<String>(listModel);
            JScrollPane scrollPane = new JScrollPane(jList);
            JPanel panel = new JPanel();
            panel.add(scrollPane);
            add(panel);

            if (projektNamnLista != null) {
                String sqlFraga2 = "SELECT namn FROM land WHERE lid = '" + projektNamnLista.get("land") + "'";
                String land = idb.fetchSingle(sqlFraga2);

                String sqlFraga3 = "SELECT fornamn, efternamn FROM anstalld WHERE aid ='" + projektNamnLista.get("projektchef") + "'";
                HashMap<String, String> projektchef = idb.fetchRow(sqlFraga3);

                tfNyttProjektNyPid.setText(projektNamnLista.get("pid"));
                tfProjektnamnAllaProjekt.setText(projektNamnLista.get("projektnamn"));
                cbProjektchefAllaProjekt.setSelectedItem(projektchef.get("fornamn") + " " + projektchef.get("efternamn"));
                tfBeskrivningAllaProjekt.setText(projektNamnLista.get("beskrivning"));
                tfStartdatumAllaProjekt.setText(projektNamnLista.get("startdatum"));
                tfSlutDatumAllaProjekt.setText(projektNamnLista.get("slutdatum"));
                tfKostnadAllaProjekt.setText(projektNamnLista.get("kostnad"));
                cbStatusAllaProjekt.setSelectedItem(projektNamnLista.get("status"));
                cbPrioAllaProjekt.setSelectedItem(projektNamnLista.get("prioritet"));
                cbLandAllaProjekt.setSelectedItem(land);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }


    }//GEN-LAST:event_cbAllaProjektPopupMenuWillBecomeInvisible

    private void btnTillbakaAllaProjActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTillbakaAllaProjActionPerformed
        this.toBack();
        Projekt nyttProject = new Projekt(idb, aid, avdid);
        nyttProject.setVisible(true);
        nyttProject.toFront();
    }//GEN-LAST:event_btnTillbakaAllaProjActionPerformed

    private void btnAndraAllaProjektActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAndraAllaProjektActionPerformed
        try {
            String projektNamn = cbAllaProjekt.getSelectedItem().toString();

            String projektnamn = tfProjektnamnAllaProjekt.getText();
            String projektchefStr = cbProjektchefAllaProjekt.getSelectedItem().toString();
            String sqlFraga1 = "SELECT aid FROM anstalld WHERE CONCAT(fornamn, ' ', efternamn) = '" + projektchefStr + "'";
            System.out.println(sqlFraga1);
            String projChefAidStr = idb.fetchSingle(sqlFraga1);
            int projChefAidInt = Integer.parseInt(projChefAidStr);

            String beskrivning = tfBeskrivningAllaProjekt.getText();
            String startdatum = tfStartdatumAllaProjekt.getText();
            String slutdatum = tfSlutDatumAllaProjekt.getText();
            String kostnadStr = tfKostnadAllaProjekt.getText();
            double kostnad = Double.parseDouble(kostnadStr);

            String status = cbStatusAllaProjekt.getSelectedItem().toString();
            String prioritet = cbPrioAllaProjekt.getSelectedItem().toString();

            String landStr = cbLandAllaProjekt.getSelectedItem().toString();
            String sqlFraga2 = "SELECT lid FROM land WHERE namn = '" + landStr + "'";
            System.out.println(sqlFraga2);
            String landIDStr = idb.fetchSingle(sqlFraga2);
            int landID = Integer.parseInt(landIDStr);

            String sqlFraga3 = "UPDATE projekt SET projektnamn = '" + projektnamn + "', projektchef = " + projChefAidInt + ", beskrivning = '" + beskrivning + "', startdatum = str_to_date('" + startdatum + "', '%Y-%m-%d'), slutdatum = str_to_date('" + slutdatum + "', '%Y-%m-%d'), kostnad = '" + kostnad + "', status = '" + status + "', prioritet = '" + prioritet + "', land = " + landID + " WHERE projektnamn = '" + projektNamn + "'";
            idb.update(sqlFraga3);
            
            
            
            cbAllaProjekt.removeAllItems();
            fyllCBAllaProjekt();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }//GEN-LAST:event_btnAndraAllaProjektActionPerformed

    private void bnTaBortAllaProjektActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnTaBortAllaProjektActionPerformed

        try {
            String projektNamn = cbAllaProjekt.getSelectedItem().toString();
            String sqlFragaPid = "SELECT pid FROM projekt WHERE projektnamn = '" + projektNamn + "'";
            System.out.println(sqlFragaPid);
            String pid = idb.fetchSingle(sqlFragaPid);

            String sqlFraga1 = "DELETE FROM ans_proj WHERE pid = " + pid + "";
            System.out.println(sqlFraga1);
            idb.delete(sqlFraga1);

            String sqlFraga2 = "DELETE FROM proj_hallbarhet WHERE pid = " + pid + "";
            System.out.println(sqlFraga2);
            idb.delete(sqlFraga2);

            String sqlFraga3 = "DELETE FROM projekt_partner WHERE pid = " + pid + "";
            System.out.println(sqlFraga3);
            idb.delete(sqlFraga3);

            String sqlFraga4 = "DELETE FROM projekt WHERE pid = " + pid + "";
            System.out.println(sqlFraga4);
            idb.delete(sqlFraga4);

            String projchefTaBort = cbProjektchefAllaProjekt.getSelectedItem().toString();
            
            // rensar textfields från uppgifter
            tfNyttProjektNyPid.setText(" ");
            tfProjektnamnAllaProjekt.setText(" ");
            //tfProjektchefAllaProjekt.setText(" ");
            cbProjektchefAllaProjekt.setSelectedItem(null);
            tfBeskrivningAllaProjekt.setText(" ");
            tfStartdatumAllaProjekt.setText(" ");
            tfSlutDatumAllaProjekt.setText(" ");
            tfKostnadAllaProjekt.setText(" ");
            //tfStatusAllaProjekt.setText(" ");
            cbStatusAllaProjekt.setSelectedItem(null);
            //tfPrioAllaProjekt.setText(" ");
            cbPrioAllaProjekt.setSelectedItem(null);
            //tfLandAllaProjekt.setText(" ");
            cbLandAllaProjekt.setSelectedItem(null);

            // uppdaterar comboboxen
            cbAllaProjekt.removeAllItems();
            fyllCBAllaProjekt();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }//GEN-LAST:event_bnTaBortAllaProjektActionPerformed

    private void btnLaggTillProjektAllaProjektActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLaggTillProjektAllaProjektActionPerformed
        new LaggTillProjekt(idb, aid, avdid).setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_btnLaggTillProjektAllaProjektActionPerformed

    private void btnLaggTillHBMalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLaggTillHBMalActionPerformed
        String malNamn = cbAllaHallbMal.getSelectedItem().toString();
        listModel.addElement(malNamn);
        jList1.setModel(listModel);
    }//GEN-LAST:event_btnLaggTillHBMalActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String anstalld = cbAllaAnstallda.getSelectedItem().toString();
        listModel2.addElement(anstalld);
        jListAllaAnstallda.setModel(listModel2);
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bnTaBortAllaProjekt;
    private javax.swing.JButton btnAndraAllaProjekt;
    private javax.swing.JButton btnLaggTillHBMal;
    private javax.swing.JButton btnLaggTillProjektAllaProjekt;
    private javax.swing.JButton btnTillbakaAllaProj;
    private javax.swing.JComboBox<String> cbAllaAnstallda;
    private javax.swing.JComboBox<String> cbAllaHallbMal;
    private javax.swing.JComboBox<String> cbAllaProjekt;
    private javax.swing.JComboBox<String> cbLandAllaProjekt;
    private javax.swing.JComboBox<String> cbPrioAllaProjekt;
    private javax.swing.JComboBox<String> cbProjektchefAllaProjekt;
    private javax.swing.JComboBox<String> cbStatusAllaProjekt;
    private javax.swing.JButton jButton2;
    private javax.swing.JList<String> jList1;
    private javax.swing.JList<String> jListAllaAnstallda;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblAllaProjAnstallda;
    private javax.swing.JLabel lblAllaProjHallbMal;
    private javax.swing.JLabel lblAllaProjLaggTillAnstalld;
    private javax.swing.JLabel lblAllaProjektRuta;
    private javax.swing.JLabel lblBeskrivningAllaProjekt;
    private javax.swing.JLabel lblKostnadAllaProjekt;
    private javax.swing.JLabel lblLaggTillHBMal;
    private javax.swing.JLabel lblLandAllaProjekt;
    private javax.swing.JLabel lblNyttProjektNyPid;
    private javax.swing.JLabel lblPrioAllaProjekt;
    private javax.swing.JLabel lblProduktchefAllaProjekt;
    private javax.swing.JLabel lblProjektnamnAllaProjekt;
    private javax.swing.JLabel lblSlutdatumAllaProjekt;
    private javax.swing.JLabel lblStartDatumAllaProjekt;
    private javax.swing.JLabel lblStatusAllaProjekt;
    private javax.swing.JTextField tfBeskrivningAllaProjekt;
    private javax.swing.JTextField tfKostnadAllaProjekt;
    private javax.swing.JTextField tfNyttProjektNyPid;
    private javax.swing.JTextField tfProjektnamnAllaProjekt;
    private javax.swing.JTextField tfSlutDatumAllaProjekt;
    private javax.swing.JTextField tfStartdatumAllaProjekt;
    // End of variables declaration//GEN-END:variables
}
