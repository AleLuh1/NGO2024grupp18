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
import javax.swing.JOptionPane;
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
    //listModelHBMal fyller i hållbarhetsmål
    private DefaultListModel<String> listModelHBMal;
    //listModelAnstallda fyller i anställda
    private DefaultListModel<String> listModelAnstallda;
    private ArrayList<String> anstalldaIProjLista;
    private ArrayList<String> nyTillagdaAnstallda;
    private ArrayList<String> nyTillagdaHBMal;

    /**
     * Creates new form AllaProjekt
     */
    public AllaProjekt(InfDB idb, String aid, String avdid) {
        initComponents();
        this.idb = idb;
        this.aid = aid;
        this.avdid = avdid;
        fyllCBAllaProjekt();
        fyllCBProjektChef();
        fyllCBLander();
        fyllPaStatus();
        fyllPaPrioritet();
        this.setLocationRelativeTo(null);
        btnTaBortAnstalldAllaProj.setVisible(false);
        nyTillagdaAnstallda = new ArrayList<String>();
        nyTillagdaHBMal = new ArrayList<String>();
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

    // Fyller i CB för hallbarhetsmål som inte redan finns kopplade till projektet
    public void fyllCBhallbarMal(int pid) {
        try {
            String sqlFraga = "SELECT DISTINCT hallbarhetsmal.hid FROM hallbarhetsmal JOIN proj_hallbarhet ON proj_hallbarhet.hid = hallbarhetsmal.hid WHERE NOT hallbarhetsmal.hid IN (SELECT hid FROM proj_hallbarhet WHERE pid = " + pid + ")";
            System.out.println(sqlFraga);
            ArrayList<String> allaHBMalLista = idb.fetchColumn(sqlFraga);
            cbAllaHallbMal.removeAllItems();
            for (String ettMal : allaHBMalLista) {
                String sqlFragaHBMalnamn = "SELECT namn FROM hallbarhetsmal WHERE hid = " + ettMal + "";
                cbAllaHallbMal.addItem(idb.fetchSingle(sqlFragaHBMalnamn));
            }
            //Loop through model and remove everything from the first list allaHBMalLista
            for (int i = 0; i < jListHBMal.getModel().getSize(); i++) {
                var hbMalNamn = jListHBMal.getModel().getElementAt(i);
                cbAllaHallbMal.removeItem(hbMalNamn);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    //Fyller i Cb för alla anställda som inte jobbar i projektet
    public void fyllCBAllaAnstallda(int pid) {
        try {
            String sqlFraga = "SELECT CONCAT(fornamn, ' ', efternamn) FROM anstalld LEFT OUTER JOIN ans_proj ON ans_proj.aid = anstalld.aid WHERE pid != " + pid + " OR pid IS NULL";
            System.out.println(sqlFraga);
            ArrayList<String> anstalldAidLista = idb.fetchColumn(sqlFraga);
            cbAllaAnstallda.removeAllItems();
            for (String enAid : anstalldAidLista) {
                cbAllaAnstallda.addItem(enAid);
            }
            //Loop through model and remove everything from the first list anstalldAidLista
            for (int i = 0; i < jListAllaAnstallda.getModel().getSize(); i++) {
                var namn = jListAllaAnstallda.getModel().getElementAt(i);
                cbAllaAnstallda.removeItem(namn);
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
        bnTaBortProj = new javax.swing.JButton();
        btnSparaAllaProjekt = new javax.swing.JButton();
        lblProjektnamnAllaProjekt = new javax.swing.JLabel();
        tfProjektnamnAllaProjekt = new javax.swing.JTextField();
        tfNyttProjektNyPid = new javax.swing.JTextField();
        lblNyttProjektNyPid = new javax.swing.JLabel();
        btnLaggTillProjAllaProj = new javax.swing.JButton();
        lblAllaProjHallbMal = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListHBMal = new javax.swing.JList<>();
        cbAllaHallbMal = new javax.swing.JComboBox<>();
        lblLaggTillHBMal = new javax.swing.JLabel();
        btnLaggTillHBMal = new javax.swing.JButton();
        lblAllaProjAnstallda = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListAllaAnstallda = new javax.swing.JList<>();
        cbAllaAnstallda = new javax.swing.JComboBox<>();
        lblAllaProjLaggTillAnstalld = new javax.swing.JLabel();
        btnLaggTillAnstalldAllaProj = new javax.swing.JButton();
        cbProjektchefAllaProjekt = new javax.swing.JComboBox<>();
        cbStatusAllaProjekt = new javax.swing.JComboBox<>();
        cbPrioAllaProjekt = new javax.swing.JComboBox<>();
        cbLandAllaProjekt = new javax.swing.JComboBox<>();
        btnTaBortAnstalldAllaProj = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lblTaBortProjAllaProj = new javax.swing.JLabel();

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

        bnTaBortProj.setText("Ta bort");
        bnTaBortProj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bnTaBortProjActionPerformed(evt);
            }
        });

        btnSparaAllaProjekt.setText("Spara");
        btnSparaAllaProjekt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSparaAllaProjektActionPerformed(evt);
            }
        });

        lblProjektnamnAllaProjekt.setText("Projektnamn");

        lblNyttProjektNyPid.setText("Projekt-ID");

        btnLaggTillProjAllaProj.setText("Lägg till");
        btnLaggTillProjAllaProj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLaggTillProjAllaProjActionPerformed(evt);
            }
        });

        lblAllaProjHallbMal.setText("Aktuella hållbarhetsmål");

        jScrollPane1.setViewportView(jListHBMal);

        cbAllaHallbMal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Välj ett mål" }));

        lblLaggTillHBMal.setText("Välj hållbarhetsmål att lägga till");

        btnLaggTillHBMal.setText("Lägg till");
        btnLaggTillHBMal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLaggTillHBMalActionPerformed(evt);
            }
        });

        lblAllaProjAnstallda.setText("Anställda i projektet");

        jScrollPane2.setViewportView(jListAllaAnstallda);

        lblAllaProjLaggTillAnstalld.setText("Välj anställd att lägga till i projektet");

        btnLaggTillAnstalldAllaProj.setText("Lägg till");
        btnLaggTillAnstalldAllaProj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLaggTillAnstalldAllaProjActionPerformed(evt);
            }
        });

        cbProjektchefAllaProjekt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ändra projektchef" }));

        cbStatusAllaProjekt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ändra status" }));

        cbPrioAllaProjekt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ändra prioritet" }));

        cbLandAllaProjekt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ändra land" }));

        btnTaBortAnstalldAllaProj.setText("Ta bort anställd från projektet");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Lägg till ett nytt projekt");

        lblTaBortProjAllaProj.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTaBortProjAllaProj.setText("Ta bort aktuellt projekt");

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
                    .addComponent(lblBeskrivningAllaProjekt)
                    .addComponent(lblStartDatumAllaProjekt)
                    .addComponent(lblSlutdatumAllaProjekt)
                    .addComponent(lblKostnadAllaProjekt)
                    .addComponent(lblStatusAllaProjekt)
                    .addComponent(lblPrioAllaProjekt)
                    .addComponent(lblLandAllaProjekt)
                    .addComponent(lblAllaProjHallbMal, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSparaAllaProjekt)
                        .addGap(68, 68, 68))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(cbLandAllaProjekt, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbPrioAllaProjekt, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbStatusAllaProjekt, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbProjektchefAllaProjekt, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tfKostnadAllaProjekt, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfProjektnamnAllaProjekt, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfNyttProjektNyPid, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfBeskrivningAllaProjekt)
                                    .addComponent(tfStartdatumAllaProjekt)
                                    .addComponent(tfSlutDatumAllaProjekt)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblTaBortProjAllaProj, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(bnTaBortProj)
                                        .addGap(104, 104, 104)))))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(btnLaggTillHBMal)
                                        .addGap(96, 96, 96))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(lblAllaProjLaggTillAnstalld, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                                                .addComponent(cbAllaAnstallda, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(lblLaggTillHBMal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(cbAllaHallbMal, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnLaggTillAnstalldAllaProj, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnTaBortAnstalldAllaProj, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addGap(25, 25, 25))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(77, 77, 77)
                                .addComponent(btnLaggTillProjAllaProj)
                                .addGap(9, 9, 9))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAllaProjektRuta)
                    .addComponent(jLabel1)
                    .addComponent(lblTaBortProjAllaProj))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbAllaProjekt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLaggTillProjAllaProj)
                    .addComponent(bnTaBortProj))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(lblProduktchefAllaProjekt)
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblAllaProjAnstallda)
                                    .addComponent(lblAllaProjLaggTillAnstalld))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbAllaAnstallda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnLaggTillAnstalldAllaProj)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnTaBortAnstalldAllaProj)))
                        .addGap(30, 30, 30)
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfKostnadAllaProjekt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblKostnadAllaProjekt))
                        .addGap(22, 22, 22)
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
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblAllaProjHallbMal)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblLaggTillHBMal)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(cbAllaHallbMal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnLaggTillHBMal))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNyttProjektNyPid)
                            .addComponent(tfNyttProjektNyPid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblProjektnamnAllaProjekt, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfProjektnamnAllaProjekt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbProjektchefAllaProjekt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(54, 54, 54)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTillbakaAllaProj)
                    .addComponent(btnSparaAllaProjekt))
                .addGap(42, 42, 42))
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
            fyllCBAllaAnstallda(pid);
            fyllCBhallbarMal(pid);

            HashMap<String, String> projektNamnLista = idb.fetchRow(sqlFraga);

            String sqlFragaHallbMal = "SELECT DISTINCT namn FROM hallbarhetsmal JOIN proj_hallbarhet ON proj_hallbarhet.hid = hallbarhetsmal.hid JOIN projekt ON projekt.pid = proj_hallbarhet.pid WHERE projektnamn = '" + projektNamn + "'";
            System.out.println(sqlFragaHallbMal);
            ArrayList<String> projektHallbMal = idb.fetchColumn(sqlFragaHallbMal);

            // Hämtar namn på alla anställda som jobbar 
            String sqlFragaAnstalldNamn = "SELECT CONCAT(fornamn, ' ', efternamn) FROM anstalld JOIN ans_proj ON ans_proj.aid = anstalld.aid WHERE ans_proj.pid = " + pid;
            System.out.println(sqlFragaAnstalldNamn);
            anstalldaIProjLista = idb.fetchColumn(sqlFragaAnstalldNamn);

            //JList för att fylla i namn på alla anställda som jobbar på projektet som har selekterats
            listModelAnstallda = new DefaultListModel<String>();
            for (int i = 0; i < anstalldaIProjLista.size(); i++) {
                listModelAnstallda.addElement(anstalldaIProjLista.get(i));
            }
            jListAllaAnstallda.setModel(listModelAnstallda);

            //JList för att visa alla hallbarhetsmål 
            listModelHBMal = new DefaultListModel<String>();
            for (int i = 0; i < projektHallbMal.size(); i++) {
                listModelHBMal.addElement(projektHallbMal.get(i));
            }
            jListHBMal.setModel(listModelHBMal);

            JList<String> jList = new JList<String>(listModelHBMal);
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

    private void btnSparaAllaProjektActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSparaAllaProjektActionPerformed
        try {
            String projektNamn = cbAllaProjekt.getSelectedItem().toString();

            String projektnamn = tfProjektnamnAllaProjekt.getText();

            String sqlProjnamnTillPid = "SELECT pid FROM projekt WHERE projektnamn = '" + projektNamn + "'";
            System.out.println(sqlProjnamnTillPid);
            String pidStr = idb.fetchSingle(sqlProjnamnTillPid);
            int pid = Integer.parseInt(pidStr);

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

            for (String namn : nyTillagdaAnstallda) {
                //Behöver konvertera namnet till aid igen för att kunna lägga in i db
                String fornamn = namn.split(" ")[0];
                String efternamn = namn.split(" ")[1];
                String sqlFragaAid = "SELECT aid FROM anstalld WHERE fornamn = '" + fornamn + "' AND efternamn = '" + efternamn + "'";
                System.out.println(sqlFragaAid);
                String nyAnstalldAidStr = idb.fetchSingle(sqlFragaAid);
                System.out.println(nyAnstalldAidStr);
                int aid = Integer.parseInt(nyAnstalldAidStr);
                System.out.println(aid);

                String sqlFr = "INSERT INTO ans_proj (pid, aid) VALUES (" + pid + ", " + aid + ")";
                System.out.println(sqlFr);
                idb.insert(sqlFr);
            }

            for (String malNamn : nyTillagdaHBMal) {
                String sqlFragaHBMal = "SELECT hid FROM hallbarhetsmal WHERE namn = '" + malNamn + "'";
                System.out.println(sqlFragaHBMal);
                String hallbMalStr = idb.fetchSingle(sqlFragaHBMal);
                System.out.println(hallbMalStr);
                int hid = Integer.parseInt(hallbMalStr);
                System.out.println(hid);
                String sqlFrHBMal = "INSERT INTO proj_hallbarhet (pid, hid) VALUES (" + pid + ", " + hid + ")";
                System.out.println(sqlFrHBMal);
                idb.insert(sqlFrHBMal);
            }
            cbAllaProjekt.removeAllItems();
            fyllCBAllaProjekt();
        JOptionPane.showMessageDialog(null, "Ändring sparad");
        this.dispose();
        new Meny(idb, aid, avdid).setVisible(true);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }//GEN-LAST:event_btnSparaAllaProjektActionPerformed

    private void bnTaBortProjActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnTaBortProjActionPerformed

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
            cbProjektchefAllaProjekt.setSelectedItem(null);
            tfBeskrivningAllaProjekt.setText(" ");
            tfStartdatumAllaProjekt.setText(" ");
            tfSlutDatumAllaProjekt.setText(" ");
            tfKostnadAllaProjekt.setText(" ");
            cbStatusAllaProjekt.setSelectedItem(null);
            cbPrioAllaProjekt.setSelectedItem(null);
            cbLandAllaProjekt.setSelectedItem(null);

            // uppdaterar comboboxen
            cbAllaProjekt.removeAllItems();
            fyllCBAllaProjekt();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }//GEN-LAST:event_bnTaBortProjActionPerformed

    private void btnLaggTillProjAllaProjActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLaggTillProjAllaProjActionPerformed
        new LaggTillProjekt(idb, aid, avdid).setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_btnLaggTillProjAllaProjActionPerformed

    private void btnLaggTillHBMalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLaggTillHBMalActionPerformed
        String malNamn = cbAllaHallbMal.getSelectedItem().toString();
        listModelHBMal.addElement(malNamn);
        jListHBMal.setModel(listModelHBMal);
        String projektNamn = cbAllaProjekt.getSelectedItem().toString();
        try {
            String sqlProjnamnTillPid = "SELECT pid FROM projekt WHERE projektnamn = '" + projektNamn + "'";
            System.out.println(sqlProjnamnTillPid);
            String pidStr = idb.fetchSingle(sqlProjnamnTillPid);
            int pid = Integer.parseInt(pidStr);
           fyllCBhallbarMal(pid);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        nyTillagdaHBMal.add(malNamn);
    }//GEN-LAST:event_btnLaggTillHBMalActionPerformed

    private void btnLaggTillAnstalldAllaProjActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLaggTillAnstalldAllaProjActionPerformed
        String anstalld = cbAllaAnstallda.getSelectedItem().toString();
        listModelAnstallda.addElement(anstalld);
        jListAllaAnstallda.setModel(listModelAnstallda);
        String projektNamn = cbAllaProjekt.getSelectedItem().toString();
        try {
            String sqlProjnamnTillPid = "SELECT pid FROM projekt WHERE projektnamn = '" + projektNamn + "'";
            System.out.println(sqlProjnamnTillPid);
            String pidStr = idb.fetchSingle(sqlProjnamnTillPid);
            int pid = Integer.parseInt(pidStr);
            fyllCBAllaAnstallda(pid);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        nyTillagdaAnstallda.add(anstalld);
    }//GEN-LAST:event_btnLaggTillAnstalldAllaProjActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bnTaBortProj;
    private javax.swing.JButton btnLaggTillAnstalldAllaProj;
    private javax.swing.JButton btnLaggTillHBMal;
    private javax.swing.JButton btnLaggTillProjAllaProj;
    private javax.swing.JButton btnSparaAllaProjekt;
    private javax.swing.JButton btnTaBortAnstalldAllaProj;
    private javax.swing.JButton btnTillbakaAllaProj;
    private javax.swing.JComboBox<String> cbAllaAnstallda;
    private javax.swing.JComboBox<String> cbAllaHallbMal;
    private javax.swing.JComboBox<String> cbAllaProjekt;
    private javax.swing.JComboBox<String> cbLandAllaProjekt;
    private javax.swing.JComboBox<String> cbPrioAllaProjekt;
    private javax.swing.JComboBox<String> cbProjektchefAllaProjekt;
    private javax.swing.JComboBox<String> cbStatusAllaProjekt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList<String> jListAllaAnstallda;
    private javax.swing.JList<String> jListHBMal;
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
    private javax.swing.JLabel lblTaBortProjAllaProj;
    private javax.swing.JTextField tfBeskrivningAllaProjekt;
    private javax.swing.JTextField tfKostnadAllaProjekt;
    private javax.swing.JTextField tfNyttProjektNyPid;
    private javax.swing.JTextField tfProjektnamnAllaProjekt;
    private javax.swing.JTextField tfSlutDatumAllaProjekt;
    private javax.swing.JTextField tfStartdatumAllaProjekt;
    // End of variables declaration//GEN-END:variables
}
