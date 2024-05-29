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

    private DefaultListModel<String> listModelPartners = new DefaultListModel<>();
    private ArrayList<String> NyTillagdaPartners = new ArrayList<>();
    private ArrayList<String> partnersSomSkaTasBortLista = new ArrayList<>();

    private ArrayList<String> anstalldaIProjLista;
    private ArrayList<String> nyTillagdaAnstallda;
    private ArrayList<String> nyTillagdaHBMal;
    private ArrayList<String> hbMalSomSkaTasBortLista = new ArrayList<>();

    private ArrayList<String> anstalldaSomTasBortLista;

    /**
     * Creates new form AllaProjekt
     */
    public AllaProjekt(InfDB idb, String aid, String avdid) {
        initComponents();
        this.idb = idb;
        this.aid = aid;
        this.avdid = avdid;
        fyllCBAllaProjekt();
        this.setLocationRelativeTo(null);
        btnTaBortAnstalldAllaProj.setVisible(false);
        nyTillagdaAnstallda = new ArrayList<String>();
        nyTillagdaHBMal = new ArrayList<String>();
        anstalldaSomTasBortLista = new ArrayList<String>();
        MinaUppgifter ny = new MinaUppgifter(idb, aid, avdid);
        if (ny.isAdmin()) {
            lblLaggTillProjekt.setVisible(true);
            btnLaggTillProjAllaProj.setVisible(true);
            lblTaBortProjAllaProj.setVisible(true);
            bnTaBortProj.setVisible(true);
        } else {
            lblLaggTillProjekt.setVisible(false);
            btnLaggTillProjAllaProj.setVisible(false);
            lblTaBortProjAllaProj.setVisible(false);
            bnTaBortProj.setVisible(false);
        }
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
            ex.printStackTrace();
        }

    }

    // Fyller i CB för hallbarhetsmål som inte redan finns kopplade till projektet
    public void fyllCBhallbarMal(int pid) {
        try {
            String sqlFraga = "SELECT DISTINCT hallbarhetsmal.hid FROM hallbarhetsmal "
                    + "JOIN proj_hallbarhet ON proj_hallbarhet.hid = hallbarhetsmal.hid "
                    + "WHERE NOT hallbarhetsmal.hid IN (SELECT hid FROM proj_hallbarhet WHERE pid = " + pid + ")";
            System.out.println(sqlFraga);
            ArrayList<String> allaHBMalLista = idb.fetchColumn(sqlFraga);
            cbAllaHallbMal.removeAllItems();
            for (String ettMal : allaHBMalLista) {
                String sqlFragaHBMalnamn = "SELECT namn FROM hallbarhetsmal WHERE hid = " + ettMal + "";
                cbAllaHallbMal.addItem(idb.fetchSingle(sqlFragaHBMalnamn));
            }
            //Loopar igenom listModel, det som finns i listModel tas bort från comboboxen
            for (int i = 0; i < jListHBmalAllaProj.getModel().getSize(); i++) {
                var hbMalNamn = jListHBmalAllaProj.getModel().getElementAt(i);
                cbAllaHallbMal.removeItem(hbMalNamn);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //Fyller i Cb för alla anställda som inte jobbar i projektet
    public void fyllCBAllaAnstallda(int pid) {
        try {
            String sqlFraga = "SELECT CONCAT(fornamn, ' ', efternamn) FROM anstalld LEFT OUTER "
                    + "JOIN ans_proj ON ans_proj.aid = anstalld.aid WHERE pid != " + pid + " OR pid IS NULL";
            System.out.println(sqlFraga);
            ArrayList<String> anstalldAidLista = idb.fetchColumn(sqlFraga);
            cbAllaAnstallda.removeAllItems();
            for (String enAid : anstalldAidLista) {
                cbAllaAnstallda.addItem(enAid);
            }
            //Loopar igenom listModel, det som finns i listModel tas bort från comboboxen
            for (int i = 0; i < jListAllaAnstallda.getModel().getSize(); i++) {
                var namn = jListAllaAnstallda.getModel().getElementAt(i);
                cbAllaAnstallda.removeItem(namn);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //Fyller i CB för projektchef
    public void fyllCBProjektChef() {
        try {
            cbProjektchefAllaProjekt.removeAllItems();
            String sqlFraga = "SELECT CONCAT(fornamn, ' ', efternamn) FROM anstalld JOIN handlaggare ON handlaggare.aid = anstalld.aid";
            System.out.println(sqlFraga);
            ArrayList<String> projektchefLista = idb.fetchColumn(sqlFraga);

            for (String enProjektchef : projektchefLista) {
                cbProjektchefAllaProjekt.addItem(enProjektchef);
            }
        } catch (Exception ex) {
            ex.printStackTrace();

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
            ex.printStackTrace();
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

    public void fyllPartnerList(String pid) {
        listModelPartners.removeAllElements();
        try {
            String sqlFraga = "SELECT partner_pid FROM projekt_partner WHERE pid=" + pid;
            System.out.println(sqlFraga);
            ArrayList<HashMap<String, String>> allaPartnersForProjektet = idb.fetchRows(sqlFraga);
            for (HashMap<String, String> enPartner : allaPartnersForProjektet) {
                String sqlFragaPartnerName = "SELECT namn FROM partner WHERE pid=" + enPartner.get("partner_pid");
                String partnerName = idb.fetchSingle(sqlFragaPartnerName);
                listModelPartners.addElement(partnerName);
            }
            jListPartnersAllaProj.setModel(listModelPartners);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void fyllCBValjPartner(int pid) {
        try {
            //Hämtar alla partner_pid som jobbar i projektet
            String sqlFragaPartnerPid = "SELECT DISTINCT partner_pid FROM projekt_partner JOIN partner ON partner.pid = partner_pid WHERE NOT partner.pid IN (SELECT partner_pid FROM projekt_partner WHERE pid = " + pid + ")";
            System.out.println(sqlFragaPartnerPid);
            ArrayList<String> partnersIProjekt = idb.fetchColumn(sqlFragaPartnerPid);
            cbPartnerAllaProj.removeAllItems();
            for (String partnerPid : partnersIProjekt) {
                String sqlFragaPartnerNamn = "SELECT namn FROM partner WHERE pid = " + partnerPid + "";
                cbPartnerAllaProj.addItem(idb.fetchSingle(sqlFragaPartnerNamn));
            }
            //Loopar igenom listModel, det som redan finns i listModel tas bort från cb
            for (int i = 0; i < jListPartnersAllaProj.getModel().getSize(); i++) {
                var partnerNamn = jListPartnersAllaProj.getModel().getElementAt(i);
                cbPartnerAllaProj.removeItem(partnerNamn);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
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
            ex.printStackTrace();
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
        lblProjektchefAllaProjekt = new javax.swing.JLabel();
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
        jListHBmalAllaProj = new javax.swing.JList<>();
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
        lblLaggTillProjekt = new javax.swing.JLabel();
        lblTaBortProjAllaProj = new javax.swing.JLabel();
        lblSamarbetspartnerAllProj = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jListPartnersAllaProj = new javax.swing.JList<>();
        lblValjPartnerLaggTillAllaProj = new javax.swing.JLabel();
        btnLaggTillPartnerAllaProj = new javax.swing.JButton();
        lblPartnerTaBortAllaProj = new javax.swing.JLabel();
        btnTaBortPartnerAllaProj = new javax.swing.JButton();
        cbPartnerAllaProj = new javax.swing.JComboBox<>();
        lblAnstalldTaBortAllaProj = new javax.swing.JLabel();
        lblValjHbMalTaBortAllaProj = new javax.swing.JLabel();
        btnTaBortHbMalAllaProj = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setFocusTraversalPolicyProvider(true);
        setMinimumSize(new java.awt.Dimension(1010, 750));
        setPreferredSize(new java.awt.Dimension(1010, 750));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnTillbakaAllaProj.setText("Tillbaka");
        btnTillbakaAllaProj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTillbakaAllaProjActionPerformed(evt);
            }
        });
        getContentPane().add(btnTillbakaAllaProj, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 660, -1, -1));

        lblAllaProjektRuta.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        lblAllaProjektRuta.setText("Alla projekt");
        getContentPane().add(lblAllaProjektRuta, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 28, -1, -1));

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
        getContentPane().add(cbAllaProjekt, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 52, 102, -1));

        lblBeskrivningAllaProjekt.setText("Beskrivning");
        getContentPane().add(lblBeskrivningAllaProjekt, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, -1, -1));

        lblStartDatumAllaProjekt.setText("Startdatum");
        getContentPane().add(lblStartDatumAllaProjekt, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, -1, 17));

        lblSlutdatumAllaProjekt.setText("Slutdatum");
        getContentPane().add(lblSlutdatumAllaProjekt, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 280, -1, 17));

        lblKostnadAllaProjekt.setText("Kostnad");
        getContentPane().add(lblKostnadAllaProjekt, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 320, -1, -1));

        lblStatusAllaProjekt.setText("Status");
        getContentPane().add(lblStatusAllaProjekt, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, -1, -1));

        lblPrioAllaProjekt.setText("Prioritet");
        getContentPane().add(lblPrioAllaProjekt, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 400, -1, -1));

        lblLandAllaProjekt.setText("Land");
        getContentPane().add(lblLandAllaProjekt, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 440, -1, -1));

        lblProjektchefAllaProjekt.setText("Projektchef");
        getContentPane().add(lblProjektchefAllaProjekt, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 161, 75, -1));
        getContentPane().add(tfBeskrivningAllaProjekt, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 200, 214, -1));
        getContentPane().add(tfStartdatumAllaProjekt, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 240, 214, -1));
        getContentPane().add(tfSlutDatumAllaProjekt, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 280, 214, -1));
        getContentPane().add(tfKostnadAllaProjekt, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 320, 214, -1));

        bnTaBortProj.setText("Ta bort");
        bnTaBortProj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bnTaBortProjActionPerformed(evt);
            }
        });
        getContentPane().add(bnTaBortProj, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 500, -1, -1));

        btnSparaAllaProjekt.setText("Spara");
        btnSparaAllaProjekt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSparaAllaProjektActionPerformed(evt);
            }
        });
        getContentPane().add(btnSparaAllaProjekt, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 660, -1, -1));

        lblProjektnamnAllaProjekt.setText("Projektnamn");
        getContentPane().add(lblProjektnamnAllaProjekt, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 75, 17));
        getContentPane().add(tfProjektnamnAllaProjekt, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 120, 214, -1));
        getContentPane().add(tfNyttProjektNyPid, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 80, 214, -1));

        lblNyttProjektNyPid.setText("Projekt-ID");
        getContentPane().add(lblNyttProjektNyPid, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 83, -1));

        btnLaggTillProjAllaProj.setText("Lägg till");
        btnLaggTillProjAllaProj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLaggTillProjAllaProjActionPerformed(evt);
            }
        });
        getContentPane().add(btnLaggTillProjAllaProj, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 610, -1, -1));

        lblAllaProjHallbMal.setText("Aktuella hållbarhetsmål");
        getContentPane().add(lblAllaProjHallbMal, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 480, 136, -1));

        jScrollPane1.setViewportView(jListHBmalAllaProj);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 480, 214, 140));

        cbAllaHallbMal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Välj ett mål" }));
        getContentPane().add(cbAllaHallbMal, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 500, 199, -1));

        lblLaggTillHBMal.setText("Välj");
        getContentPane().add(lblLaggTillHBMal, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 480, 61, -1));

        btnLaggTillHBMal.setText("Lägg till");
        btnLaggTillHBMal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLaggTillHBMalActionPerformed(evt);
            }
        });
        getContentPane().add(btnLaggTillHBMal, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 530, 199, -1));

        lblAllaProjAnstallda.setText("Anställda i projektet");
        getContentPane().add(lblAllaProjAnstallda, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 80, 117, -1));

        jListAllaAnstallda.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jListAllaAnstalldaValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(jListAllaAnstallda);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 100, 210, 130));

        getContentPane().add(cbAllaAnstallda, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 280, 210, -1));

        lblAllaProjLaggTillAnstalld.setText("Välj");
        getContentPane().add(lblAllaProjLaggTillAnstalld, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 250, -1, -1));

        btnLaggTillAnstalldAllaProj.setText("Lägg till");
        btnLaggTillAnstalldAllaProj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLaggTillAnstalldAllaProjActionPerformed(evt);
            }
        });
        getContentPane().add(btnLaggTillAnstalldAllaProj, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 320, 210, -1));

        cbProjektchefAllaProjekt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ändra projektchef" }));
        getContentPane().add(cbProjektchefAllaProjekt, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 160, 214, -1));

        cbStatusAllaProjekt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ändra status" }));
        getContentPane().add(cbStatusAllaProjekt, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 360, 214, -1));

        cbPrioAllaProjekt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ändra prioritet" }));
        getContentPane().add(cbPrioAllaProjekt, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 400, 214, -1));

        cbLandAllaProjekt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ändra land" }));
        getContentPane().add(cbLandAllaProjekt, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 440, 214, -1));

        btnTaBortAnstalldAllaProj.setText("Ta bort");
        btnTaBortAnstalldAllaProj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaBortAnstalldAllaProjActionPerformed(evt);
            }
        });
        getContentPane().add(btnTaBortAnstalldAllaProj, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 400, 210, -1));

        lblLaggTillProjekt.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblLaggTillProjekt.setText("Lägg till ett nytt projekt");
        getContentPane().add(lblLaggTillProjekt, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 570, 203, -1));

        lblTaBortProjAllaProj.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTaBortProjAllaProj.setText("Ta bort aktuellt projekt");
        getContentPane().add(lblTaBortProjAllaProj, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 470, 234, -1));

        lblSamarbetspartnerAllProj.setText("Samarbetspartner");
        getContentPane().add(lblSamarbetspartnerAllProj, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 80, 140, -1));

        jScrollPane3.setViewportView(jListPartnersAllaProj);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 100, 200, -1));

        lblValjPartnerLaggTillAllaProj.setText("Välj partner att lägga till i projektet");
        getContentPane().add(lblValjPartnerLaggTillAllaProj, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 250, 200, -1));

        btnLaggTillPartnerAllaProj.setText("Lägg till");
        btnLaggTillPartnerAllaProj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLaggTillPartnerAllaProjActionPerformed(evt);
            }
        });
        getContentPane().add(btnLaggTillPartnerAllaProj, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 320, 200, -1));

        lblPartnerTaBortAllaProj.setText("Markera partner att ta bort");
        getContentPane().add(lblPartnerTaBortAllaProj, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 380, 200, 20));

        btnTaBortPartnerAllaProj.setText("Ta bort");
        btnTaBortPartnerAllaProj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaBortPartnerAllaProjActionPerformed(evt);
            }
        });
        getContentPane().add(btnTaBortPartnerAllaProj, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 400, 200, -1));

        getContentPane().add(cbPartnerAllaProj, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 280, 200, -1));

        lblAnstalldTaBortAllaProj.setText("Markera anställd att ta bort");
        getContentPane().add(lblAnstalldTaBortAllaProj, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 380, 200, -1));

        lblValjHbMalTaBortAllaProj.setText("Markera hållbarhetsmål att ta bort");
        getContentPane().add(lblValjHbMalTaBortAllaProj, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 570, -1, -1));

        btnTaBortHbMalAllaProj.setText("Ta bort");
        btnTaBortHbMalAllaProj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaBortHbMalAllaProjActionPerformed(evt);
            }
        });
        getContentPane().add(btnTaBortHbMalAllaProj, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 590, 200, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbAllaProjektPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbAllaProjektPopupMenuWillBecomeInvisible
        // Hämtar ut projektnamnet för det selekterade projektet
        String projektNamn = cbAllaProjekt.getSelectedItem().toString();

        try {
            if(projektNamn.equals("Välj projekt")) {
                return;
            }
            
            String sqlFraga = "SELECT * FROM projekt WHERE projektnamn = '" + projektNamn + "'";
            System.out.println(sqlFraga);

            String sqlProjnamnTillPid = "SELECT pid FROM projekt WHERE projektnamn = '" + projektNamn + "'";
            System.out.println(sqlProjnamnTillPid);
            String pidStr = idb.fetchSingle(sqlProjnamnTillPid);
            int pid = Integer.parseInt(pidStr);
            fyllCBAllaAnstallda(pid);
            fyllCBhallbarMal(pid);
            fyllCBProjektChef();
            fyllCBLander();
            fyllPaStatus();
            fyllPaPrioritet();
            fyllPartnerList(pidStr);
            fyllCBValjPartner(pid);

            HashMap<String, String> projektNamnLista = idb.fetchRow(sqlFraga);

            String sqlFragaHallbMal = "SELECT DISTINCT namn FROM hallbarhetsmal "
                    + "JOIN proj_hallbarhet ON proj_hallbarhet.hid = hallbarhetsmal.hid "
                    + "JOIN projekt ON projekt.pid = proj_hallbarhet.pid "
                    + "WHERE projektnamn = '" + projektNamn + "'";
            System.out.println(sqlFragaHallbMal);
            ArrayList<String> projektHallbMal = idb.fetchColumn(sqlFragaHallbMal);

            // Hämtar namn på alla anställda som jobbar 
            String sqlFragaAnstalldNamn = "SELECT CONCAT(fornamn, ' ', efternamn) FROM anstalld "
                    + "JOIN ans_proj ON ans_proj.aid = anstalld.aid WHERE ans_proj.pid = " + pid;
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
            jListHBmalAllaProj.setModel(listModelHBMal);

//            String sqlFraga3 = "SELECT fornamn, efternamn FROM anstalld WHERE aid ='" + projektNamnLista.get("projektchef") + "'";
//            HashMap<String, String> projektchef = idb.fetchRow(sqlFraga3);
//
//            tfNyttProjektNyPid.setText(projektNamnLista.get("pid"));
//            tfProjektnamnAllaProjekt.setText(projektNamnLista.get("projektnamn"));
//            cbProjektchefAllaProjekt.setSelectedItem(projektchef.get("fornamn") + " " + projektchef.get("efternamn"));
//            tfBeskrivningAllaProjekt.setText(projektNamnLista.get("beskrivning"));
//            tfStartdatumAllaProjekt.setText(projektNamnLista.get("startdatum"));
//
//            tfSlutDatumAllaProjekt.setText(projektNamnLista.get("slutdatum"));
//            tfKostnadAllaProjekt.setText(projektNamnLista.get("kostnad"));
//            cbStatusAllaProjekt.setSelectedItem(projektNamnLista.get("status"));
//            cbPrioAllaProjekt.setSelectedItem(projektNamnLista.get("prioritet"));
//            cbLandAllaProjekt.setSelectedItem(land);
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
            ex.printStackTrace();
        }


    }//GEN-LAST:event_cbAllaProjektPopupMenuWillBecomeInvisible

    private void btnTillbakaAllaProjActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTillbakaAllaProjActionPerformed
        this.dispose();
        Projekt nyttProject = new Projekt(idb, aid, avdid);
        nyttProject.setVisible(true);
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
            nyTillagdaAnstallda.clear();

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
            nyTillagdaHBMal.clear();

            for (String hbMalAttTaBort : hbMalSomSkaTasBortLista) {
                String sqlFragaTaBortHid = "SELECT hid FROM hallbarhetsmal WHERE namn = '" + hbMalAttTaBort + "'";
                System.out.println(sqlFragaTaBortHid);
                String hid = idb.fetchSingle(sqlFragaTaBortHid);

                String sqlFragaHBMalTasBort = "DELETE FROM proj_hallbarhet WHERE pid = " + pid + " AND hid = " + hid + "";
                idb.delete(sqlFragaHBMalTasBort);
            }
            hbMalSomSkaTasBortLista.clear();

            for (String anstalldAttTaBort : anstalldaSomTasBortLista) {
                String anstalldFornamn = anstalldAttTaBort.split(" ")[0];
                String anstalldEfternamn = anstalldAttTaBort.split(" ")[1];

                String sqlFragaAnstalldId = "SELECT aid FROM anstalld WHERE fornamn = '" + anstalldFornamn + "' AND efternamn = '" + anstalldEfternamn + "'";
                String anstalldId = idb.fetchSingle(sqlFragaAnstalldId);

                if (!anstalldId.equals(aid)) {
                    String sqlFragaTaBort = "DELETE FROM ans_proj WHERE pid =" + pid + " AND aid =" + anstalldId;
                    idb.delete(sqlFragaTaBort);
                } else {
                    JOptionPane.showMessageDialog(null, "Projektchef " + anstalldFornamn + " " + anstalldEfternamn + " kan inte tas bort");
                }
            }
            anstalldaSomTasBortLista.clear();

            for (int i = 0; i < listModelPartners.size(); i++) {
                String partnerNamn = listModelPartners.getElementAt(i);

                String sqlFragaPartnerdId = "SELECT pid FROM partner WHERE namn = '" + partnerNamn + "'";
                String partnerId = idb.fetchSingle(sqlFragaPartnerdId);

                //skapa ny item i tabellen projekt_partner med pid och partner_pid. Använder WHERE NOT EXIST för att kontrollera att det inte finns en annan item som har redan pid+partner_pid
                String sqlFragaProjektPartner = "INSERT INTO projekt_partner (pid, partner_pid) "
                        + "SELECT " + pid + ", " + partnerId + " "
                        + "WHERE NOT EXISTS (SELECT 1 FROM projekt_partner WHERE pid = " + pid + " AND partner_pid = " + partnerId + ")";
                idb.insert(sqlFragaProjektPartner);
            }

            for (String tagitBortPartner : partnersSomSkaTasBortLista) {
                String sqlFragaPartnerId = "SELECT pid FROM partner WHERE namn = '" + tagitBortPartner + "'";
                String partnerId = idb.fetchSingle(sqlFragaPartnerId);

                String sqlFragaTaBortPartner = "DELETE FROM projekt_partner WHERE pid =" + pid + " AND partner_pid =" + partnerId;
                idb.delete(sqlFragaTaBortPartner);
            }
            partnersSomSkaTasBortLista.clear();

            cbAllaProjekt.removeAllItems();
            fyllCBAllaProjekt();
            JOptionPane.showMessageDialog(null, "Ändring sparad");
//            this.dispose();
//            new Meny(idb, aid, avdid).setVisible(true);
        } catch (Exception ex) {
            ex.printStackTrace();
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
            cbAllaProjektPopupMenuWillBecomeInvisible(null);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_bnTaBortProjActionPerformed

    private void btnLaggTillProjAllaProjActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLaggTillProjAllaProjActionPerformed
        new LaggTillProjekt(idb, aid, avdid).setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_btnLaggTillProjAllaProjActionPerformed

    private void btnLaggTillHBMalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLaggTillHBMalActionPerformed
        String malNamn = cbAllaHallbMal.getSelectedItem().toString();
        listModelHBMal.addElement(malNamn);
        jListHBmalAllaProj.setModel(listModelHBMal);
        String projektNamn = cbAllaProjekt.getSelectedItem().toString();
        try {
            String sqlProjnamnTillPid = "SELECT pid FROM projekt WHERE projektnamn = '" + projektNamn + "'";
            System.out.println(sqlProjnamnTillPid);
            String pidStr = idb.fetchSingle(sqlProjnamnTillPid);
            int pid = Integer.parseInt(pidStr);
            fyllCBhallbarMal(pid);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        nyTillagdaHBMal.add(malNamn);
    }//GEN-LAST:event_btnLaggTillHBMalActionPerformed

    private void btnLaggTillAnstalldAllaProjActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLaggTillAnstalldAllaProjActionPerformed
//        String anstalld = cbAllaAnstallda.getSelectedItem().toString();
//        listModelAnstallda.addElement(anstalld);
//        jListAllaAnstallda.setModel(listModelAnstallda);
//        String projektNamn = cbAllaProjekt.getSelectedItem().toString();
        try {
            String anstalld = cbAllaAnstallda.getSelectedItem().toString();
            listModelAnstallda.addElement(anstalld);
            jListAllaAnstallda.setModel(listModelAnstallda);
            String projektNamn = cbAllaProjekt.getSelectedItem().toString();

            String sqlProjnamnTillPid = "SELECT pid FROM projekt WHERE projektnamn = '" + projektNamn + "'";
            System.out.println(sqlProjnamnTillPid);
            String pidStr = idb.fetchSingle(sqlProjnamnTillPid);
            int pid = Integer.parseInt(pidStr);
            fyllCBAllaAnstallda(pid);
            nyTillagdaAnstallda.add(anstalld);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
//        nyTillagdaAnstallda.add(anstalld);
    }//GEN-LAST:event_btnLaggTillAnstalldAllaProjActionPerformed

    private void jListAllaAnstalldaValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jListAllaAnstalldaValueChanged
        btnTaBortAnstalldAllaProj.setVisible(true);
    }//GEN-LAST:event_jListAllaAnstalldaValueChanged

    private void btnTaBortAnstalldAllaProjActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaBortAnstalldAllaProjActionPerformed
        String anstalld = jListAllaAnstallda.getSelectedValue();
        listModelAnstallda.removeElement(anstalld);
        cbAllaAnstallda.addItem(anstalld);
        anstalldaSomTasBortLista.add(anstalld);
    }//GEN-LAST:event_btnTaBortAnstalldAllaProjActionPerformed

    private void btnLaggTillPartnerAllaProjActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLaggTillPartnerAllaProjActionPerformed
        NyTillagdaPartners.clear();
        String partner = cbPartnerAllaProj.getSelectedItem().toString();
        cbPartnerAllaProj.removeItem(partner);
        listModelPartners.addElement(partner);
    }//GEN-LAST:event_btnLaggTillPartnerAllaProjActionPerformed

    private void btnTaBortPartnerAllaProjActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaBortPartnerAllaProjActionPerformed
        String partner = jListPartnersAllaProj.getSelectedValue();
        listModelPartners.removeElement(partner);
        cbPartnerAllaProj.addItem(partner);
        NyTillagdaPartners.add(partner);
        partnersSomSkaTasBortLista.add(partner);
    }//GEN-LAST:event_btnTaBortPartnerAllaProjActionPerformed

    private void btnTaBortHbMalAllaProjActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaBortHbMalAllaProjActionPerformed
        String hbMal = jListHBmalAllaProj.getSelectedValue();
        listModelHBMal.removeElement(hbMal);
        cbAllaHallbMal.addItem(hbMal);
        hbMalSomSkaTasBortLista.add(hbMal);
    }//GEN-LAST:event_btnTaBortHbMalAllaProjActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bnTaBortProj;
    private javax.swing.JButton btnLaggTillAnstalldAllaProj;
    private javax.swing.JButton btnLaggTillHBMal;
    private javax.swing.JButton btnLaggTillPartnerAllaProj;
    private javax.swing.JButton btnLaggTillProjAllaProj;
    private javax.swing.JButton btnSparaAllaProjekt;
    private javax.swing.JButton btnTaBortAnstalldAllaProj;
    private javax.swing.JButton btnTaBortHbMalAllaProj;
    private javax.swing.JButton btnTaBortPartnerAllaProj;
    private javax.swing.JButton btnTillbakaAllaProj;
    private javax.swing.JComboBox<String> cbAllaAnstallda;
    private javax.swing.JComboBox<String> cbAllaHallbMal;
    private javax.swing.JComboBox<String> cbAllaProjekt;
    private javax.swing.JComboBox<String> cbLandAllaProjekt;
    private javax.swing.JComboBox<String> cbPartnerAllaProj;
    private javax.swing.JComboBox<String> cbPrioAllaProjekt;
    private javax.swing.JComboBox<String> cbProjektchefAllaProjekt;
    private javax.swing.JComboBox<String> cbStatusAllaProjekt;
    private javax.swing.JList<String> jListAllaAnstallda;
    private javax.swing.JList<String> jListHBmalAllaProj;
    private javax.swing.JList<String> jListPartnersAllaProj;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblAllaProjAnstallda;
    private javax.swing.JLabel lblAllaProjHallbMal;
    private javax.swing.JLabel lblAllaProjLaggTillAnstalld;
    private javax.swing.JLabel lblAllaProjektRuta;
    private javax.swing.JLabel lblAnstalldTaBortAllaProj;
    private javax.swing.JLabel lblBeskrivningAllaProjekt;
    private javax.swing.JLabel lblKostnadAllaProjekt;
    private javax.swing.JLabel lblLaggTillHBMal;
    private javax.swing.JLabel lblLaggTillProjekt;
    private javax.swing.JLabel lblLandAllaProjekt;
    private javax.swing.JLabel lblNyttProjektNyPid;
    private javax.swing.JLabel lblPartnerTaBortAllaProj;
    private javax.swing.JLabel lblPrioAllaProjekt;
    private javax.swing.JLabel lblProjektchefAllaProjekt;
    private javax.swing.JLabel lblProjektnamnAllaProjekt;
    private javax.swing.JLabel lblSamarbetspartnerAllProj;
    private javax.swing.JLabel lblSlutdatumAllaProjekt;
    private javax.swing.JLabel lblStartDatumAllaProjekt;
    private javax.swing.JLabel lblStatusAllaProjekt;
    private javax.swing.JLabel lblTaBortProjAllaProj;
    private javax.swing.JLabel lblValjHbMalTaBortAllaProj;
    private javax.swing.JLabel lblValjPartnerLaggTillAllaProj;
    private javax.swing.JTextField tfBeskrivningAllaProjekt;
    private javax.swing.JTextField tfKostnadAllaProjekt;
    private javax.swing.JTextField tfNyttProjektNyPid;
    private javax.swing.JTextField tfProjektnamnAllaProjekt;
    private javax.swing.JTextField tfSlutDatumAllaProjekt;
    private javax.swing.JTextField tfStartdatumAllaProjekt;
    // End of variables declaration//GEN-END:variables
}
