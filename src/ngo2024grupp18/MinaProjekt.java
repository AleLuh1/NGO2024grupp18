/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ngo2024grupp18;

import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.ComboBoxEditor;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import oru.inf.InfDB;

/**
 *
 * @author alex
 */
public class MinaProjekt extends javax.swing.JFrame {

    private InfDB idb;
    private String aid;
    private String avdid;

    //List model som innehåller jlist items av typen String
    private DefaultListModel<String> listModel = new DefaultListModel<>();
    //ArrayList som håller koll på vilka anställda tas bort
    private ArrayList<String> anstalldaSomSkaTasBort = new ArrayList<>();
    private DefaultListModel<String> listModelPartners = new DefaultListModel<>();
    private ArrayList<String> visaPartner = new ArrayList<>();
    private DefaultListModel<String> listModelPartnersInfo = new DefaultListModel<>();
    private ArrayList<String> partnersSomSkaTasBort = new ArrayList<>();
    private ArrayList<String> visaPartnerInfo = new ArrayList<>();
    private DefaultListModel<String> listModelhallbarhetsmal = new DefaultListModel<>();
    private ArrayList<String> nyTillagdaHBMal = new ArrayList<String>();
    private ArrayList<String> hbMalSomTasBortLista;
    /**
     * Creates new form MinaProjekt
     */
    public MinaProjekt(InfDB idb, String aid, String avdid) {
        initComponents();
        this.idb = idb;
        this.aid = aid;
        this.avdid = avdid;
        fyllCBMinaProjekt();
        fyllPaLander();
        fyllPaStatus();
        fyllPaPrioritet();
        fyllCBValjProjektchef();
        this.setLocationRelativeTo(null);
        hbMalSomTasBortLista = new ArrayList<String>();
        jListHallbarhetsmalMinaProjekt.setModel(listModelhallbarhetsmal);
        cbEditor(cbStatusMinaProjekt);
        cbEditor(cbPrioritetMinaProjekt);
        cbEditor(cbProjektchefMinaProjekt);
        cbEditor(cbLandMinaProjekt);
        btnStatistikMinaProjekt.setVisible(false);
        lblValjPartnerMinaProj.setVisible(false);
        cbValjPartnerMinaProjekt.setVisible(false);
        btnLaggTillPartnerMinaProjekt.setVisible(false);
        lblTaBortPartnerMinaProj.setVisible(false);
        btnTaBortPartnerMinaProjekt.setVisible(false);
        lblValjLaggTillAnstalldMinaProj.setVisible(false);
        lblAnstalldaMinaProj.setVisible(false);
        jListAnstalldaMinaProjekt.setVisible(false);
        jScrollPane1.setVisible(false);
        cbAnstalldaMinaProjekt.setVisible(false);
        btnLaggTillAnstalld.setVisible(false);
        lblTaBortAnstalldMinaProj.setVisible(false);
        btnTaBortAnstalld.setVisible(false);
        btnMinaProjektRedigera.setEnabled(false);
        cbValjHbMalMinaProj.setVisible(false);
        btnLaggTillHbMalMinaProj.setVisible(false);
        lblTaBortHbMalMinaProj.setVisible(false);
        btnTaBortHbMalMinaProj.setVisible(false);
    }

    // 
    public static void cbEditor(JComboBox cb) {
        cb.setEditable(true);
        ComboBoxEditor editor = cb.getEditor();
        JTextField tf = (JTextField) editor.getEditorComponent();
        tf.setDisabledTextColor(UIManager.getColor("ComboBox.foreground"));
        tf.setBackground(UIManager.getColor("ComboBox.background"));
        cb.setEnabled(false);
    }

    // Fyller combobox
    public void fyllCBMinaProjekt() {
        try {
            String sqlFraga = "SELECT DISTINCT projektnamn FROM projekt\n"
                    + "LEFT OUTER JOIN ans_proj on ans_proj.pid = projekt.pid\n"
                    + "WHERE ans_proj.aid = " + aid + " OR projektchef = " + aid;
            System.out.println(sqlFraga);
            ArrayList<String> minaProjektLista = idb.fetchColumn(sqlFraga);
            for (String ettProjekt : minaProjektLista) {
                cbMinaProjekt.addItem(ettProjekt);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void fyllAnstalldaList(String pid) {
        listModel.removeAllElements();
        try {
            String sqlFraga = "SELECT aid FROM ans_proj WHERE pid = " + pid;
            System.out.println(sqlFraga);
            ArrayList<HashMap<String, String>> anstalldaIProjekt = idb.fetchRows(sqlFraga);
            for (HashMap<String, String> enAnstall : anstalldaIProjekt) {
                String sqlFraga1 = "SELECT fornamn,efternamn FROM anstalld WHERE aid=" + enAnstall.get("aid");
                HashMap<String, String> anstalld = idb.fetchRow(sqlFraga1);
                listModel.addElement(anstalld.get("fornamn") + " " + anstalld.get("efternamn"));
            }
            jListAnstalldaMinaProjekt.setModel(listModel);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
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
            jListPartnerMinaProjekt.setModel(listModelPartners);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // fyller på uppgifter om en samarbetspartner i text area
    public void fyllPaInfoTa(String partnerNamn) {

        try {
            String sqlFragaPartner = "SELECT * FROM partner WHERE namn ='" + partnerNamn + "'";
            HashMap<String, String> partner = idb.fetchRow(sqlFragaPartner);

            String sqlStad = "SELECT namn FROM stad WHERE sid ='" + partner.get("stad") + "'";
            String stadNamn = idb.fetchSingle(sqlStad);
            taPartnerInfoMinaUppgifter.setText(partner.get("namn") + "\n" + partner.get("kontaktperson") + "\n" + partner.get("kontaktepost") + "\n" + partner.get("telefon") + "\n" + partner.get("adress") + "\n" + partner.get("branch") + "\n" + stadNamn + "\n -------------------------");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //fyll på anställd combobox med alla anställda som jobbar inte i projektet
    public void fyllAnstalldaCB(String pid) {
        cbAnstalldaMinaProjekt.removeAllItems();
        try {
            //hämtar alla aid som jobbar i projekt
            String sqlFraga = "SELECT aid FROM ans_proj WHERE pid = " + pid;
            System.out.println(sqlFraga);
            ArrayList<HashMap<String, String>> anstalldaIProjekt = idb.fetchRows(sqlFraga);
            //skapa en arrayList med alla aid som jobbar i projektet
            ArrayList<String> aidList = new ArrayList<>();
            for (HashMap<String, String> enAnstall : anstalldaIProjekt) {
                aidList.add(enAnstall.get("aid"));
            }
            // bygger en string med alla aid tex: "1,2,5,4"
            String allaAid = String.join(",", aidList);
            String sqlFraga1;
            if (aidList.isEmpty()) {
                sqlFraga1 = "SELECT fornamn, efternamn FROM anstalld";
            } else {
                //använder string med alla aid för att hämta alla namn och efternamn på anställda som inte är med i listan
                sqlFraga1 = "SELECT fornamn, efternamn FROM anstalld WHERE aid NOT IN (" + allaAid + ")";
            }
            System.out.println(sqlFraga1);
            ArrayList<HashMap<String, String>> availableAnstallda = idb.fetchRows(sqlFraga1);
            for (HashMap<String, String> anstalld : availableAnstallda) {
                cbAnstalldaMinaProjekt.addItem(anstalld.get("fornamn") + " " + anstalld.get("efternamn"));
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
                cbProjektchefMinaProjekt.addItem(projektchef.get("fornamn") + " " + projektchef.get("efternamn"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //Fyller CB med partners som inte redan finns kopplade till det valda projektet
    public void fyllCBValjPartner(String pid) {
        try {
            //Hämtar alla partner_pid som jobbar i projektet
            String sqlFragaPartnerPid = "SELECT DISTINCT partner_pid FROM projekt_partner JOIN partner ON partner.pid = partner_pid WHERE NOT partner.pid IN (SELECT partner_pid FROM projekt_partner WHERE pid = " + pid + ")";
            System.out.println(sqlFragaPartnerPid);
            ArrayList<String> partnersIProjekt = idb.fetchColumn(sqlFragaPartnerPid);
            cbValjPartnerMinaProjekt.removeAllItems();
            for (String partnerPid : partnersIProjekt) {
                String sqlFragaPartnerNamn = "SELECT namn FROM partner WHERE pid = " + partnerPid + "";
                cbValjPartnerMinaProjekt.addItem(idb.fetchSingle(sqlFragaPartnerNamn));
            }
            //Loopar igenom listModel, det som redan finns i listModel tas bort från cb
            for (int i = 0; i < jListPartnerMinaProjekt.getModel().getSize(); i++) {
                var partnerNamn = jListPartnerMinaProjekt.getModel().getElementAt(i);
                cbValjPartnerMinaProjekt.removeItem(partnerNamn);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void fyllPaStatus() {
        cbStatusMinaProjekt.addItem("Planerat");
        cbStatusMinaProjekt.addItem("Pågående");
        cbStatusMinaProjekt.addItem("Avslutat");
    }

    private void fyllPaPrioritet() {
        cbPrioritetMinaProjekt.addItem("Hög");
        cbPrioritetMinaProjekt.addItem("Medel");
        cbPrioritetMinaProjekt.addItem("Låg");
    }

    private void fyllPaLander() {
        try {
            String sqlFraga4 = "SELECT namn FROM land";
            ArrayList<HashMap<String, String>> allaLander = idb.fetchRows(sqlFraga4);
            for (HashMap<String, String> ettLand : allaLander) {
                cbLandMinaProjekt.addItem(ettLand.get("namn"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //Fyller CB för HB-mål som inte redan finns kopplade till projektet
    private void fyllCBHallbarMal(String pid) {
        try {
            String sqlFraga = "SELECT DISTINCT hallbarhetsmal.hid FROM hallbarhetsmal "
                    + "JOIN proj_hallbarhet ON proj_hallbarhet.hid = hallbarhetsmal.hid "
                    + "WHERE NOT hallbarhetsmal.hid IN (SELECT hid FROM proj_hallbarhet WHERE pid = " + pid + ")";
            System.out.println(sqlFraga);
            ArrayList<String> allaHBMalLista = idb.fetchColumn(sqlFraga);
            cbValjHbMalMinaProj.removeAllItems();
            for (String ettMal : allaHBMalLista) {
                String sqlFragaHBMalnamn = "SELECT namn FROM hallbarhetsmal WHERE hid = " + ettMal + "";
                cbValjHbMalMinaProj.addItem(idb.fetchSingle(sqlFragaHBMalnamn));
            }
            //Loopar igenom listModel, det som finns i listModel tas bort från comboboxen
            //Could call getModel directly on the model (try to fix later)
            for (int i = 0; i < jListHallbarhetsmalMinaProjekt.getModel().getSize(); i++) {
                var hbMalNamn = jListHallbarhetsmalMinaProjekt.getModel().getElementAt(i);
                cbValjHbMalMinaProj.removeItem(hbMalNamn);
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

        btnTillbakaMinaProj = new javax.swing.JButton();
        lblProjektID = new javax.swing.JLabel();
        tfProjektID = new javax.swing.JTextField();
        lblProjektNamn = new javax.swing.JLabel();
        tfProjektNamn = new javax.swing.JTextField();
        lblBeskrivningProjekt = new javax.swing.JLabel();
        tfBeskrivningProjekt = new javax.swing.JTextField();
        lblStartdatum = new javax.swing.JLabel();
        tfStartdatum = new javax.swing.JTextField();
        lblSlutdatum = new javax.swing.JLabel();
        tfSlutdatum = new javax.swing.JTextField();
        lblKostnad = new javax.swing.JLabel();
        tfKostnad = new javax.swing.JTextField();
        lblStatus = new javax.swing.JLabel();
        lblPrioProjekt = new javax.swing.JLabel();
        lblProjektchef = new javax.swing.JLabel();
        lblLand = new javax.swing.JLabel();
        btnMinaProjektRedigera = new javax.swing.JButton();
        lblMinaProjektRuta = new javax.swing.JLabel();
        cbMinaProjekt = new javax.swing.JComboBox<>();
        btnStatistikMinaProjekt = new javax.swing.JButton();
        cbStatusMinaProjekt = new javax.swing.JComboBox<>();
        cbPrioritetMinaProjekt = new javax.swing.JComboBox<>();
        cbProjektchefMinaProjekt = new javax.swing.JComboBox<>();
        cbLandMinaProjekt = new javax.swing.JComboBox<>();
        btnLaggTillAnstalld = new javax.swing.JButton();
        lblAnstalldaMinaProj = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListAnstalldaMinaProjekt = new javax.swing.JList<>();
        cbAnstalldaMinaProjekt = new javax.swing.JComboBox<>();
        btnTaBortAnstalld = new javax.swing.JButton();
        lblValjLaggTillAnstalldMinaProj = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListPartnerMinaProjekt = new javax.swing.JList<>();
        lblPartnerMinaProj = new javax.swing.JLabel();
        cbValjPartnerMinaProjekt = new javax.swing.JComboBox<>();
        btnLaggTillPartnerMinaProjekt = new javax.swing.JButton();
        btnTaBortPartnerMinaProjekt = new javax.swing.JButton();
        lblValjPartnerMinaProj = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        taPartnerInfoMinaUppgifter = new javax.swing.JTextArea();
        lblHallbarhetsmalMinaProjekt = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jListHallbarhetsmalMinaProjekt = new javax.swing.JList<>();
        lblTaBortPartnerMinaProj = new javax.swing.JLabel();
        lblTaBortAnstalldMinaProj = new javax.swing.JLabel();
        lblKontaktUppgifterPartnerMinaProj = new javax.swing.JLabel();
        cbValjHbMalMinaProj = new javax.swing.JComboBox<>();
        btnLaggTillHbMalMinaProj = new javax.swing.JButton();
        lblTaBortHbMalMinaProj = new javax.swing.JLabel();
        btnTaBortHbMalMinaProj = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(950, 800));
        setPreferredSize(new java.awt.Dimension(950, 800));
        setSize(new java.awt.Dimension(950, 800));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnTillbakaMinaProj.setText("Tillbaka");
        btnTillbakaMinaProj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTillbakaMinaProjActionPerformed(evt);
            }
        });
        getContentPane().add(btnTillbakaMinaProj, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 638, -1, -1));

        lblProjektID.setText("Projekt-ID");
        getContentPane().add(lblProjektID, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 119, 114, -1));
        getContentPane().add(tfProjektID, new org.netbeans.lib.awtextra.AbsoluteConstraints(174, 116, 183, -1));

        lblProjektNamn.setText("Projektnamn");
        getContentPane().add(lblProjektNamn, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 159, 114, -1));
        getContentPane().add(tfProjektNamn, new org.netbeans.lib.awtextra.AbsoluteConstraints(174, 156, 183, -1));

        lblBeskrivningProjekt.setText("Beskrivning");
        getContentPane().add(lblBeskrivningProjekt, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 199, -1, -1));
        getContentPane().add(tfBeskrivningProjekt, new org.netbeans.lib.awtextra.AbsoluteConstraints(174, 196, 183, -1));

        lblStartdatum.setText("Startdatum");
        getContentPane().add(lblStartdatum, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 239, -1, -1));
        getContentPane().add(tfStartdatum, new org.netbeans.lib.awtextra.AbsoluteConstraints(174, 236, 183, -1));

        lblSlutdatum.setText("Slutdatum");
        getContentPane().add(lblSlutdatum, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 276, -1, -1));
        getContentPane().add(tfSlutdatum, new org.netbeans.lib.awtextra.AbsoluteConstraints(174, 276, 183, -1));

        lblKostnad.setText("Kostnad");
        getContentPane().add(lblKostnad, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 316, -1, -1));
        getContentPane().add(tfKostnad, new org.netbeans.lib.awtextra.AbsoluteConstraints(174, 316, 183, -1));

        lblStatus.setText("Status");
        getContentPane().add(lblStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 353, -1, -1));

        lblPrioProjekt.setText("Prioritet");
        getContentPane().add(lblPrioProjekt, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 394, -1, -1));

        lblProjektchef.setText("Projektchef");
        getContentPane().add(lblProjektchef, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 431, -1, -1));

        lblLand.setText("Land");
        getContentPane().add(lblLand, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 464, -1, -1));

        btnMinaProjektRedigera.setText("Spara");
        btnMinaProjektRedigera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMinaProjektRedigeraActionPerformed(evt);
            }
        });
        getContentPane().add(btnMinaProjektRedigera, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 640, -1, -1));

        lblMinaProjektRuta.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblMinaProjektRuta.setText("Mina projekt");
        getContentPane().add(lblMinaProjektRuta, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 28, -1, -1));

        cbMinaProjekt.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        cbMinaProjekt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Välj ett projekt" }));
        cbMinaProjekt.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbMinaProjektPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        getContentPane().add(cbMinaProjekt, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 67, 154, -1));

        btnStatistikMinaProjekt.setText("Visa statistik");
        btnStatistikMinaProjekt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStatistikMinaProjektActionPerformed(evt);
            }
        });
        getContentPane().add(btnStatistikMinaProjekt, new org.netbeans.lib.awtextra.AbsoluteConstraints(224, 69, -1, -1));

        cbStatusMinaProjekt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ändra status" }));
        getContentPane().add(cbStatusMinaProjekt, new org.netbeans.lib.awtextra.AbsoluteConstraints(174, 350, 183, -1));

        cbPrioritetMinaProjekt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ändra prioritet" }));
        getContentPane().add(cbPrioritetMinaProjekt, new org.netbeans.lib.awtextra.AbsoluteConstraints(174, 391, 183, -1));

        cbProjektchefMinaProjekt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ändra projektchef", " " }));
        getContentPane().add(cbProjektchefMinaProjekt, new org.netbeans.lib.awtextra.AbsoluteConstraints(174, 428, 183, -1));

        cbLandMinaProjekt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ändra land" }));
        getContentPane().add(cbLandMinaProjekt, new org.netbeans.lib.awtextra.AbsoluteConstraints(174, 464, 183, -1));

        btnLaggTillAnstalld.setText("Lägg till ");
        btnLaggTillAnstalld.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLaggTillAnstalldActionPerformed(evt);
            }
        });
        getContentPane().add(btnLaggTillAnstalld, new org.netbeans.lib.awtextra.AbsoluteConstraints(627, 504, 190, -1));

        lblAnstalldaMinaProj.setText("Anställda i projekt");
        getContentPane().add(lblAnstalldaMinaProj, new org.netbeans.lib.awtextra.AbsoluteConstraints(627, 276, -1, -1));

        jListAnstalldaMinaProjekt.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jListAnstalldaMinaProjektValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jListAnstalldaMinaProjekt);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(627, 316, 190, 106));

        getContentPane().add(cbAnstalldaMinaProjekt, new org.netbeans.lib.awtextra.AbsoluteConstraints(627, 464, 190, -1));

        btnTaBortAnstalld.setText("Ta bort");
        btnTaBortAnstalld.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaBortAnstalldActionPerformed(evt);
            }
        });
        getContentPane().add(btnTaBortAnstalld, new org.netbeans.lib.awtextra.AbsoluteConstraints(627, 561, 190, -1));

        lblValjLaggTillAnstalldMinaProj.setText("Välj anställd att lägga till i projektet");
        getContentPane().add(lblValjLaggTillAnstalldMinaProj, new org.netbeans.lib.awtextra.AbsoluteConstraints(627, 431, -1, -1));

        jListPartnerMinaProjekt.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jListPartnerMinaProjektValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(jListPartnerMinaProjekt);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(406, 116, 195, -1));

        lblPartnerMinaProj.setText("Samarbetspartner");
        getContentPane().add(lblPartnerMinaProj, new org.netbeans.lib.awtextra.AbsoluteConstraints(406, 72, -1, -1));

        getContentPane().add(cbValjPartnerMinaProjekt, new org.netbeans.lib.awtextra.AbsoluteConstraints(406, 316, 195, -1));

        btnLaggTillPartnerMinaProjekt.setText("Lägg till");
        btnLaggTillPartnerMinaProjekt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLaggTillPartnerMinaProjektActionPerformed(evt);
            }
        });
        getContentPane().add(btnLaggTillPartnerMinaProjekt, new org.netbeans.lib.awtextra.AbsoluteConstraints(406, 350, 195, -1));

        btnTaBortPartnerMinaProjekt.setText("Ta bort ");
        btnTaBortPartnerMinaProjekt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaBortPartnerMinaProjektActionPerformed(evt);
            }
        });
        getContentPane().add(btnTaBortPartnerMinaProjekt, new org.netbeans.lib.awtextra.AbsoluteConstraints(406, 428, 195, -1));

        lblValjPartnerMinaProj.setText("Välj partner att lägga till i projektet");
        getContentPane().add(lblValjPartnerMinaProj, new org.netbeans.lib.awtextra.AbsoluteConstraints(406, 276, 195, -1));

        taPartnerInfoMinaUppgifter.setColumns(20);
        taPartnerInfoMinaUppgifter.setRows(5);
        jScrollPane4.setViewportView(taPartnerInfoMinaUppgifter);

        getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(627, 116, 190, 130));

        lblHallbarhetsmalMinaProjekt.setText("Hållbarhetsmål");
        getContentPane().add(lblHallbarhetsmalMinaProjekt, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 497, -1, -1));

        jScrollPane5.setViewportView(jListHallbarhetsmalMinaProjekt);

        getContentPane().add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(174, 504, 183, 110));

        lblTaBortPartnerMinaProj.setText("Markera partner att ta bort ");
        getContentPane().add(lblTaBortPartnerMinaProj, new org.netbeans.lib.awtextra.AbsoluteConstraints(406, 394, 195, -1));

        lblTaBortAnstalldMinaProj.setText("Markera anställd att ta bort");
        getContentPane().add(lblTaBortAnstalldMinaProj, new org.netbeans.lib.awtextra.AbsoluteConstraints(627, 539, 190, -1));

        lblKontaktUppgifterPartnerMinaProj.setText("Kontaktuppgifter");
        getContentPane().add(lblKontaktUppgifterPartnerMinaProj, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 70, -1, -1));

        cbValjHbMalMinaProj.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Välj hållbarhetsmål" }));
        getContentPane().add(cbValjHbMalMinaProj, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 500, 190, -1));

        btnLaggTillHbMalMinaProj.setText("Lägg till");
        btnLaggTillHbMalMinaProj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLaggTillHbMalMinaProjActionPerformed(evt);
            }
        });
        getContentPane().add(btnLaggTillHbMalMinaProj, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 530, 190, -1));

        lblTaBortHbMalMinaProj.setText("Markera hallbarhetsmål att ta bort");
        getContentPane().add(lblTaBortHbMalMinaProj, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 570, 190, -1));

        btnTaBortHbMalMinaProj.setText("Ta bort");
        btnTaBortHbMalMinaProj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaBortHbMalMinaProjActionPerformed(evt);
            }
        });
        getContentPane().add(btnTaBortHbMalMinaProj, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 590, 190, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTillbakaMinaProjActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTillbakaMinaProjActionPerformed
        this.dispose();
        Projekt nyttProject = new Projekt(idb, aid, avdid);
        nyttProject.setVisible(true);
    }//GEN-LAST:event_btnTillbakaMinaProjActionPerformed

    private void cbMinaProjektPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbMinaProjektPopupMenuWillBecomeInvisible
        String minaProjektNamn = cbMinaProjekt.getSelectedItem().toString();

        try {
            if (minaProjektNamn.equals("Välj ett projekt")) {
                return;
            }
            String sqlFragaProjNamnForProjChef = "SELECT DISTINCT projektnamn FROM projekt JOIN anstalld ON anstalld.aid = projektchef WHERE aid = " + aid;
            System.out.println(sqlFragaProjNamnForProjChef);
            ArrayList<String> ProjNamnForProjChef = idb.fetchColumn(sqlFragaProjNamnForProjChef);
            if (ProjNamnForProjChef.contains(minaProjektNamn)) {
                btnMinaProjektRedigera.setEnabled(true);
                btnStatistikMinaProjekt.setVisible(true);
                lblValjPartnerMinaProj.setVisible(true);
                cbValjPartnerMinaProjekt.setVisible(true);
                btnLaggTillPartnerMinaProjekt.setVisible(true);
                lblTaBortPartnerMinaProj.setVisible(true);
                btnTaBortPartnerMinaProjekt.setVisible(true);
                lblAnstalldaMinaProj.setVisible(true);
                jListAnstalldaMinaProjekt.setVisible(true);
                jScrollPane1.setVisible(true);
                lblValjLaggTillAnstalldMinaProj.setVisible(true);
                cbAnstalldaMinaProjekt.setVisible(true);
                btnLaggTillAnstalld.setVisible(true);
                lblTaBortAnstalldMinaProj.setVisible(true);
                btnTaBortAnstalld.setVisible(true);
                tfBeskrivningProjekt.setEditable(true);
                tfSlutdatum.setEditable(true);
                tfKostnad.setEditable(true);
                cbStatusMinaProjekt.setEnabled(true);
                cbPrioritetMinaProjekt.setEnabled(true);
                cbProjektchefMinaProjekt.setEnabled(true);
                cbLandMinaProjekt.setEnabled(true);
                cbValjHbMalMinaProj.setVisible(true);
                btnLaggTillHbMalMinaProj.setVisible(true);
                lblTaBortHbMalMinaProj.setVisible(true);
                btnTaBortHbMalMinaProj.setVisible(true);
            } else {
                btnMinaProjektRedigera.setEnabled(false);
                btnStatistikMinaProjekt.setVisible(false);
                lblValjPartnerMinaProj.setVisible(false);
                cbValjPartnerMinaProjekt.setVisible(false);
                btnLaggTillPartnerMinaProjekt.setVisible(false);
                lblTaBortPartnerMinaProj.setVisible(false);
                btnTaBortPartnerMinaProjekt.setVisible(false);
                lblAnstalldaMinaProj.setVisible(false);
                jListAnstalldaMinaProjekt.setVisible(false);
                jScrollPane1.setVisible(false);
                lblValjLaggTillAnstalldMinaProj.setVisible(false);
                cbAnstalldaMinaProjekt.setVisible(false);
                btnLaggTillAnstalld.setVisible(false);
                lblTaBortAnstalldMinaProj.setVisible(false);
                btnTaBortAnstalld.setVisible(false);
                tfBeskrivningProjekt.setEditable(false);
                tfSlutdatum.setEditable(false);
                tfKostnad.setEditable(false);
                cbValjHbMalMinaProj.setVisible(false);
                btnLaggTillHbMalMinaProj.setVisible(false);
                lblTaBortHbMalMinaProj.setVisible(false);
                btnTaBortHbMalMinaProj.setVisible(false);
                cbEditor(cbStatusMinaProjekt);
                cbEditor(cbPrioritetMinaProjekt);
                cbEditor(cbProjektchefMinaProjekt);
                cbEditor(cbLandMinaProjekt);
            }

            System.out.println(aid);
            String sqlFraga1 = "SELECT DISTINCT projekt.pid, projektnamn, beskrivning, startdatum, slutdatum, kostnad, status, prioritet, projektchef, land  FROM projekt\n"
                    + "    LEFT OUTER JOIN ans_proj ON ans_proj.pid = projekt.pid\n"
                    + "         WHERE (ans_proj.aid = " + aid + " OR projektchef = " + aid + ") AND projektnamn = '" + minaProjektNamn + "'";
            System.out.println(sqlFraga1);

            HashMap<String, String> minaProjekt = idb.fetchRow(sqlFraga1);

            String sqlFraga2 = "SELECT namn FROM land WHERE lid = '" + minaProjekt.get("land") + "'";
            String land = idb.fetchSingle(sqlFraga2);

            String sqlFraga3 = "SELECT fornamn, efternamn FROM anstalld WHERE aid =" + minaProjekt.get("projektchef");
            HashMap<String, String> projektchef = idb.fetchRow(sqlFraga3);

            cbStatusMinaProjekt.setSelectedItem(minaProjekt.get("status"));

            cbPrioritetMinaProjekt.setSelectedItem(minaProjekt.get("prioritet"));
            cbLandMinaProjekt.setSelectedItem(land);
            cbProjektchefMinaProjekt.setSelectedItem(projektchef.get("fornamn") + " " + projektchef.get("efternamn"));

            String pid = minaProjekt.get("pid");
            System.out.println(pid);
            int pidInt = Integer.parseInt(pid);
            tfProjektID.setText(pid);
            tfProjektID.setEditable(false);
            lblProjektID.requestFocus();
            tfProjektNamn.setText(minaProjekt.get("projektnamn"));
            tfProjektNamn.setEditable(false);
            tfBeskrivningProjekt.setText(minaProjekt.get("beskrivning"));
            tfStartdatum.setText(minaProjekt.get("startdatum"));
            tfStartdatum.setEditable(false);
            tfSlutdatum.setText(minaProjekt.get("slutdatum"));
            tfKostnad.setText(minaProjekt.get("kostnad"));

            fyllAnstalldaList(pid);
            fyllAnstalldaCB(pid);
            fyllPartnerList(pid);
            fyllCBValjPartner(pid);
            fyllCBHallbarMal(pid);

            String sqlFragaHBMal = "SELECT DISTINCT namn FROM hallbarhetsmal "
                    + "JOIN proj_hallbarhet ON proj_hallbarhet.hid = hallbarhetsmal.hid "
                    + "JOIN projekt ON projekt.pid = proj_hallbarhet.pid "
                    + "WHERE projektnamn = '" + minaProjektNamn + "'";
            System.out.println(sqlFragaHBMal);
            ArrayList<String> projektHallbMal = idb.fetchColumn(sqlFragaHBMal);

            listModelhallbarhetsmal.clear();
            for (int i = 0; i < projektHallbMal.size(); i++) {
                listModelhallbarhetsmal.addElement(projektHallbMal.get(i));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_cbMinaProjektPopupMenuWillBecomeInvisible

    private void btnStatistikMinaProjektActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStatistikMinaProjektActionPerformed
        new ProjektStatistik(idb, aid, avdid).setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_btnStatistikMinaProjektActionPerformed

    private void btnMinaProjektRedigeraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMinaProjektRedigeraActionPerformed
        try {
            String projektId = tfProjektID.getText();
            String projektNamn = tfProjektNamn.getText();
            String projektBeskrivning = tfBeskrivningProjekt.getText();
            String projektStartDatum = tfStartdatum.getText();
            String projektSlutDatum = tfSlutdatum.getText();
            String projektKostnad = tfKostnad.getText();
            String status = cbStatusMinaProjekt.getSelectedItem().toString();
            String prioritet = cbPrioritetMinaProjekt.getSelectedItem().toString();

            String landNamn = cbLandMinaProjekt.getSelectedItem().toString();
            String sqlFragaLandId = "SELECT lid FROM land WHERE namn = '" + landNamn + "'";
            String landId = idb.fetchSingle(sqlFragaLandId);

            String projektChefNamn = cbProjektchefMinaProjekt.getSelectedItem().toString();
            String chefFornamn = projektChefNamn.split(" ")[0];
            String chefEfternamn = projektChefNamn.split(" ")[1];
            String sqlFragaChefId = "SELECT aid FROM anstalld WHERE fornamn = '" + chefFornamn + "' AND efternamn = '" + chefEfternamn + "'";
            String chefId = idb.fetchSingle(sqlFragaChefId);

            String startDatum = "str_to_date('" + projektStartDatum + "', '%Y-%m-%d')";
            String slutDatum = "str_to_date('" + projektSlutDatum + "', '%Y-%m-%d')";

            String sqlFraga = "UPDATE projekt SET projektnamn = '" + projektNamn + "', beskrivning = '" + projektBeskrivning + "', startdatum = " + startDatum + ", slutdatum = " + slutDatum + ", kostnad = " + projektKostnad + ", status = '" + status + "', land = " + landId + ", projektchef =" + chefId + ",prioritet='" + prioritet + "' WHERE pid=" + projektId;
            idb.update(sqlFraga);

            // tar alla namn på anställda i listModel och hämta deras aid för att skapa nytt item i tabellen ans_proj om det inte finns redan en item som har samma aid och pid
            for (int i = 0; i < listModel.size(); i++) {
                String anstalld = listModel.getElementAt(i);
                String anstalldFornamn = anstalld.split(" ")[0];
                String anstalldEfternamn = anstalld.split(" ")[1];

                //hämtar aid från anstalld tabell genom att använda fornamn och efternamn
                String sqlFragaAnstalldId = "SELECT aid FROM anstalld WHERE fornamn = '" + anstalldFornamn + "' AND efternamn = '" + anstalldEfternamn + "'";
                String anstalldId = idb.fetchSingle(sqlFragaAnstalldId);

                //skapa ny item i tabellen ans_proj med pid och aid. Använder WHERE NOT EXIST för att kontrollera att det inte finns en annan item som har redan pid+aid
                String sqlFragaProjektAnst = "INSERT INTO ans_proj (pid, aid) "
                        + "SELECT " + projektId + ", " + anstalldId + " "
                        + "WHERE NOT EXISTS (SELECT 1 FROM ans_proj WHERE pid = " + projektId + " AND aid = " + anstalldId + ")";

                idb.insert(sqlFragaProjektAnst);
            }

            for (String tagitBortAnstalld : anstalldaSomSkaTasBort) {
                String anstalldFornamn = tagitBortAnstalld.split(" ")[0];
                String anstalldEfternamn = tagitBortAnstalld.split(" ")[1];

                String sqlFragaAnstalldId = "SELECT aid FROM anstalld WHERE fornamn = '" + anstalldFornamn + "' AND efternamn = '" + anstalldEfternamn + "'";
                String anstalldId = idb.fetchSingle(sqlFragaAnstalldId);

                //tar bort item som har pid+aid för de anställda som blev bortagna från listan men kontrollerar att anställd id som ska tas bort inte är den projekt ansvarig aid som är inloggad 
                if (!anstalldId.equals(aid)) {
                    String sqlFragaTaBort = "DELETE FROM ans_proj WHERE pid =" + projektId + " AND aid =" + anstalldId;
                    idb.delete(sqlFragaTaBort);
                } else {
                    JOptionPane.showMessageDialog(null, "Projektchef " + anstalldFornamn + " " + anstalldEfternamn + " kan inte tas bort");
                }
            }

            for (int i = 0; i < listModelPartners.size(); i++) {
                String partnerNamn = listModelPartners.getElementAt(i);

                String sqlFragaPartnerdId = "SELECT pid FROM partner WHERE namn = '" + partnerNamn + "'";
                String partnerId = idb.fetchSingle(sqlFragaPartnerdId);

                //skapa ny item i tabellen projekt_partner med pid och partner_pid. Använder WHERE NOT EXIST för att kontrollera att det inte finns en annan item som har redan pid+partner_pid
                String sqlFragaProjektPartner = "INSERT INTO projekt_partner (pid, partner_pid) "
                        + "SELECT " + projektId + ", " + partnerId + " "
                        + "WHERE NOT EXISTS (SELECT 1 FROM projekt_partner WHERE pid = " + projektId + " AND partner_pid = " + partnerId + ")";

                idb.insert(sqlFragaProjektPartner);
            }

            for (String tagitBortPartner : partnersSomSkaTasBort) {
                String sqlFragaPartnerId = "SELECT pid FROM partner WHERE namn = '" + tagitBortPartner + "'";
                String partnerId = idb.fetchSingle(sqlFragaPartnerId);

                String sqlFragaTaBortPartner = "DELETE FROM projekt_partner WHERE pid =" + projektId + " AND partner_pid =" + partnerId;
                idb.delete(sqlFragaTaBortPartner);
            }

            for (String malNamn : nyTillagdaHBMal) {
                String sqlFragaHBMal = "SELECT hid FROM hallbarhetsmal WHERE namn = '" + malNamn + "'";
                System.out.println(sqlFragaHBMal);
                String hallbMalStr = idb.fetchSingle(sqlFragaHBMal);
                System.out.println(hallbMalStr);
                int hid = Integer.parseInt(hallbMalStr);
                System.out.println(hid);
                String sqlFrHBMal = "INSERT INTO proj_hallbarhet (pid, hid) VALUES (" + projektId + ", " + hid + ")";
                System.out.println(sqlFrHBMal);
                idb.insert(sqlFrHBMal);
            }
            nyTillagdaHBMal.clear();

            //Behöver en loop för att ta bort HB-mål också!
            //Får skrivas här
            for (String hbMalAttTaBort : hbMalSomTasBortLista) {
                String sqlFragaTaBortHid = "SELECT hid FROM hallbarhetsmal WHERE namn = '" + hbMalAttTaBort + "'";
                System.out.println(sqlFragaTaBortHid);
                String hid = idb.fetchSingle(sqlFragaTaBortHid);
                
                String sqlFragaHBMalTasBort = "DELETE FROM proj_hallbarhet WHERE pid = " + projektId + " AND hid = " + hid + ""; 
                idb.delete(sqlFragaHBMalTasBort);
            }
            hbMalSomTasBortLista.clear();
            
            
            JOptionPane.showMessageDialog(null, "Ändring sparad");

            //laddar om listan
            fyllAnstalldaList(projektId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }//GEN-LAST:event_btnMinaProjektRedigeraActionPerformed

    private void btnLaggTillAnstalldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLaggTillAnstalldActionPerformed
        anstalldaSomSkaTasBort.clear();
        String selectedAnstalld = cbAnstalldaMinaProjekt.getSelectedItem().toString();
        cbAnstalldaMinaProjekt.removeItem(selectedAnstalld);
        listModel.addElement(selectedAnstalld);
    }//GEN-LAST:event_btnLaggTillAnstalldActionPerformed

    private void btnTaBortAnstalldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaBortAnstalldActionPerformed
        String anstalld = jListAnstalldaMinaProjekt.getSelectedValue();
        listModel.removeElement(anstalld);
        cbAnstalldaMinaProjekt.addItem(anstalld);
        anstalldaSomSkaTasBort.add(anstalld);
    }//GEN-LAST:event_btnTaBortAnstalldActionPerformed

    private void jListAnstalldaMinaProjektValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jListAnstalldaMinaProjektValueChanged
        btnTaBortAnstalld.setVisible(true);
    }//GEN-LAST:event_jListAnstalldaMinaProjektValueChanged

    private void btnLaggTillPartnerMinaProjektActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLaggTillPartnerMinaProjektActionPerformed
        visaPartner.clear();
        String selectedAnstalld = cbValjPartnerMinaProjekt.getSelectedItem().toString();
        cbValjPartnerMinaProjekt.removeItem(selectedAnstalld);
        listModelPartners.addElement(selectedAnstalld);
    }//GEN-LAST:event_btnLaggTillPartnerMinaProjektActionPerformed

    private void btnTaBortPartnerMinaProjektActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaBortPartnerMinaProjektActionPerformed
        String anstalld = jListPartnerMinaProjekt.getSelectedValue();
        listModelPartners.removeElement(anstalld);
        cbValjPartnerMinaProjekt.addItem(anstalld);
        visaPartner.add(anstalld);
        partnersSomSkaTasBort.add(anstalld);
    }//GEN-LAST:event_btnTaBortPartnerMinaProjektActionPerformed

    private void jListPartnerMinaProjektValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jListPartnerMinaProjektValueChanged
        taPartnerInfoMinaUppgifter.setText("");
        String partnerNamn = jListPartnerMinaProjekt.getSelectedValue();
        if (partnerNamn != null) {
            fyllPaInfoTa(partnerNamn);
        }
    }//GEN-LAST:event_jListPartnerMinaProjektValueChanged

    private void btnLaggTillHbMalMinaProjActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLaggTillHbMalMinaProjActionPerformed
        String malNamn = cbValjHbMalMinaProj.getSelectedItem().toString();
        listModelhallbarhetsmal.addElement(malNamn);
        jListHallbarhetsmalMinaProjekt.setModel(listModelhallbarhetsmal);
        String projektNamn = cbMinaProjekt.getSelectedItem().toString();
        try {
            String sqlProjnamnTillPid = "SELECT pid FROM projekt WHERE projektnamn = '" + projektNamn + "'";
            System.out.println(sqlProjnamnTillPid);
            String pid = idb.fetchSingle(sqlProjnamnTillPid);
//            int pid = Integer.parseInt(pidStr);
            fyllCBHallbarMal(pid);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        nyTillagdaHBMal.add(malNamn);
    }//GEN-LAST:event_btnLaggTillHbMalMinaProjActionPerformed

    private void btnTaBortHbMalMinaProjActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaBortHbMalMinaProjActionPerformed
        String hbMal = jListHallbarhetsmalMinaProjekt.getSelectedValue();
        listModelhallbarhetsmal.removeElement(hbMal);
        cbValjHbMalMinaProj.addItem(hbMal);
        hbMalSomTasBortLista.add(hbMal);
    }//GEN-LAST:event_btnTaBortHbMalMinaProjActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLaggTillAnstalld;
    private javax.swing.JButton btnLaggTillHbMalMinaProj;
    private javax.swing.JButton btnLaggTillPartnerMinaProjekt;
    private javax.swing.JButton btnMinaProjektRedigera;
    private javax.swing.JButton btnStatistikMinaProjekt;
    private javax.swing.JButton btnTaBortAnstalld;
    private javax.swing.JButton btnTaBortHbMalMinaProj;
    private javax.swing.JButton btnTaBortPartnerMinaProjekt;
    private javax.swing.JButton btnTillbakaMinaProj;
    private javax.swing.JComboBox<String> cbAnstalldaMinaProjekt;
    private javax.swing.JComboBox<String> cbLandMinaProjekt;
    private javax.swing.JComboBox<String> cbMinaProjekt;
    private javax.swing.JComboBox<String> cbPrioritetMinaProjekt;
    private javax.swing.JComboBox<String> cbProjektchefMinaProjekt;
    private javax.swing.JComboBox<String> cbStatusMinaProjekt;
    private javax.swing.JComboBox<String> cbValjHbMalMinaProj;
    private javax.swing.JComboBox<String> cbValjPartnerMinaProjekt;
    private javax.swing.JList<String> jListAnstalldaMinaProjekt;
    private javax.swing.JList<String> jListHallbarhetsmalMinaProjekt;
    private javax.swing.JList<String> jListPartnerMinaProjekt;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lblAnstalldaMinaProj;
    private javax.swing.JLabel lblBeskrivningProjekt;
    private javax.swing.JLabel lblHallbarhetsmalMinaProjekt;
    private javax.swing.JLabel lblKontaktUppgifterPartnerMinaProj;
    private javax.swing.JLabel lblKostnad;
    private javax.swing.JLabel lblLand;
    private javax.swing.JLabel lblMinaProjektRuta;
    private javax.swing.JLabel lblPartnerMinaProj;
    private javax.swing.JLabel lblPrioProjekt;
    private javax.swing.JLabel lblProjektID;
    private javax.swing.JLabel lblProjektNamn;
    private javax.swing.JLabel lblProjektchef;
    private javax.swing.JLabel lblSlutdatum;
    private javax.swing.JLabel lblStartdatum;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblTaBortAnstalldMinaProj;
    private javax.swing.JLabel lblTaBortHbMalMinaProj;
    private javax.swing.JLabel lblTaBortPartnerMinaProj;
    private javax.swing.JLabel lblValjLaggTillAnstalldMinaProj;
    private javax.swing.JLabel lblValjPartnerMinaProj;
    private javax.swing.JTextArea taPartnerInfoMinaUppgifter;
    private javax.swing.JTextField tfBeskrivningProjekt;
    private javax.swing.JTextField tfKostnad;
    private javax.swing.JTextField tfProjektID;
    private javax.swing.JTextField tfProjektNamn;
    private javax.swing.JTextField tfSlutdatum;
    private javax.swing.JTextField tfStartdatum;
    // End of variables declaration//GEN-END:variables
}
