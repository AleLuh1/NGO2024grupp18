/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ngo2024grupp18;

import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
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
        fyllCBVäljProjektchef();
        fyllValjPartner();
        this.setLocationRelativeTo(null);
    }

    // Fyller combobox
    public void fyllCBMinaProjekt() {
        try {
            String sqlFraga = "SELECT projektnamn FROM projekt JOIN ans_proj ON ans_proj.pid = projekt.pid WHERE aid = " + aid;
            System.out.println(sqlFraga);
            ArrayList<String> minaProjektLista = idb.fetchColumn(sqlFraga);
            for (String ettProjekt : minaProjektLista) {
                cbMinaProjekt.addItem(ettProjekt);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    //fyller list model för anställda list genom att hämta alla aid (och därefter fornam och efternamn) från projekt id i ans_proj tabel
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
            System.out.println(ex.getMessage());
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

            System.out.println(ex.getMessage());

        }

    }

    public void fyllPartnerListInfo(String pid) {
        listModelPartnersInfo.removeAllElements();
        try {

            String sqlFraga = "SELECT * FROM partner WHERE pid=" + pid;
            System.out.println(sqlFraga);
            ArrayList<HashMap<String, String>> allInfoPartner = idb.fetchRows(sqlFraga);

            for (HashMap<String, String> enPartner : allInfoPartner) {

                listModelPartnersInfo.addElement(enPartner.get("namn"));

            }
            jListPartnerInfoMinaUppgifter.setModel(listModelPartnersInfo);

        } catch (Exception ex) {

            System.out.println(ex.getMessage());

        }

    }

    // fyller på uppgifter om en samarbetspartner i text area
    public void fyllPaInfoTa(String partnerNamn) {

        try {
            String sqlFragaPartner = "SELECT * FROM partner WHERE namn ='" + partnerNamn + "'";

            HashMap<String, String> partner = idb.fetchRow(sqlFragaPartner);

            taPartnerInfoMinaUppgifter.setText(partner.get("namn") + "\n" + partner.get("kontaktperson") + "\n" + partner.get("kontaktepost") + "\n" + partner.get("telefon") + "\n" + partner.get("adress") + "\n" + partner.get("branch") + "\n" + partner.get("stad") + "\n -------------------------");

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
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
            System.out.println(ex.getMessage());
        }
    }

    private void fyllPaLander() {
        try {
            cbLandMinaProjekt.removeAllItems();
            String sqlFraga4 = "SELECT namn FROM land";
            ArrayList<HashMap<String, String>> allaLander = idb.fetchRows(sqlFraga4);
            for (HashMap<String, String> ettLand : allaLander) {
                cbLandMinaProjekt.addItem(ettLand.get("namn"));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void fyllCBVäljProjektchef() {
        try {
            cbProjektchefMinaProjekt.removeAllItems();
            String sqlFraga = "SELECT DISTINCT projektchef FROM projekt";
            System.out.println(sqlFraga);
            ArrayList<String> projektchefIdLista = idb.fetchColumn(sqlFraga);

            for (String enProjektchefId : projektchefIdLista) {
                String sqlFraga1 = "SELECT fornamn, efternamn FROM anstalld WHERE aid =" + enProjektchefId;
                HashMap<String, String> projektchef = idb.fetchRow(sqlFraga1);
                cbProjektchefMinaProjekt.addItem(projektchef.get("fornamn") + " " + projektchef.get("efternamn"));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }

    }

    public void fyllValjPartner() {

        try {
            String sqlFraga = "SELECT namn FROM partner";
            System.out.println(sqlFraga);
            ArrayList<String> partnerLista = idb.fetchColumn(sqlFraga);
            for (String enPartner : partnerLista) {
                cbValjPartnerMinaProjekt.addItem(enPartner);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    private void fyllPaStatus() {
        cbStatusMinaProjekt.removeAllItems();
        cbStatusMinaProjekt.addItem("Planerat");
        cbStatusMinaProjekt.addItem("Pågående");
        cbStatusMinaProjekt.addItem("Avslutat");

    }

    private void fyllPaPrioritet() {
        cbPrioritetMinaProjekt.removeAllItems();
        cbPrioritetMinaProjekt.addItem("Hög");
        cbPrioritetMinaProjekt.addItem("Medel");
        cbPrioritetMinaProjekt.addItem("Låg");

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
        btnAndraUppgifterMinaProjekt = new javax.swing.JButton();
        lblMinaProjektRuta = new javax.swing.JLabel();
        cbMinaProjekt = new javax.swing.JComboBox<>();
        btnStatistikMinaProjekt = new javax.swing.JButton();
        cbStatusMinaProjekt = new javax.swing.JComboBox<>();
        cbPrioritetMinaProjekt = new javax.swing.JComboBox<>();
        cbProjektchefMinaProjekt = new javax.swing.JComboBox<>();
        cbLandMinaProjekt = new javax.swing.JComboBox<>();
        btnLaggTillAnstalld = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListAnstalldaMinaProjekt = new javax.swing.JList<>();
        cbAnstalldaMinaProjekt = new javax.swing.JComboBox<>();
        btnTaBortAnstalld = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListPartnerMinaProjekt = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        cbValjPartnerMinaProjekt = new javax.swing.JComboBox<>();
        btnLaggTillPartnerMinaProjekt = new javax.swing.JButton();
        btnTaBortPartnerMinaProjekt = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jListPartnerInfoMinaUppgifter = new javax.swing.JList<>();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        taPartnerInfoMinaUppgifter = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnTillbakaMinaProj.setText("Tillbaka");
        btnTillbakaMinaProj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTillbakaMinaProjActionPerformed(evt);
            }
        });

        lblProjektID.setText("Projekt-ID");

        lblProjektNamn.setText("Projektnamn");

        lblBeskrivningProjekt.setText("Beskrivning");

        lblStartdatum.setText("Startdatum");

        lblSlutdatum.setText("Slutdatum");

        lblKostnad.setText("Kostnad");

        lblStatus.setText("Status");

        lblPrioProjekt.setText("Prioritet");

        lblProjektchef.setText("Projektchef");

        lblLand.setText("Land");

        btnAndraUppgifterMinaProjekt.setText("Spara");
        btnAndraUppgifterMinaProjekt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAndraUppgifterMinaProjektActionPerformed(evt);
            }
        });

        lblMinaProjektRuta.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblMinaProjektRuta.setText("Mina projekt");

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

        btnStatistikMinaProjekt.setText("Visa statistik");
        btnStatistikMinaProjekt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStatistikMinaProjektActionPerformed(evt);
            }
        });

        cbStatusMinaProjekt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ändra status" }));

        cbPrioritetMinaProjekt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ändra prioritet" }));

        cbProjektchefMinaProjekt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ändra projektchef", " " }));

        cbLandMinaProjekt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ändra land" }));

        btnLaggTillAnstalld.setText("Lägg till ");
        btnLaggTillAnstalld.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLaggTillAnstalldActionPerformed(evt);
            }
        });

        jLabel1.setText("Anställda i projekt");

        jListAnstalldaMinaProjekt.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jListAnstalldaMinaProjektValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jListAnstalldaMinaProjekt);

        btnTaBortAnstalld.setText("Ta bort");
        btnTaBortAnstalld.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaBortAnstalldActionPerformed(evt);
            }
        });

        jLabel4.setText("Välj anställd att lägga till i projektet");

        jScrollPane2.setViewportView(jListPartnerMinaProjekt);

        jLabel2.setText("Samarbetspartner");

        btnLaggTillPartnerMinaProjekt.setText("Lägg till");
        btnLaggTillPartnerMinaProjekt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLaggTillPartnerMinaProjektActionPerformed(evt);
            }
        });

        btnTaBortPartnerMinaProjekt.setText("Ta bort ");
        btnTaBortPartnerMinaProjekt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaBortPartnerMinaProjektActionPerformed(evt);
            }
        });

        jLabel3.setText("Välj partner att lägga till i projektet");

        jListPartnerInfoMinaUppgifter.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jListPartnerInfoMinaUppgifterValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(jListPartnerInfoMinaUppgifter);

        jLabel5.setText("Välj samarbetspartner");

        taPartnerInfoMinaUppgifter.setColumns(20);
        taPartnerInfoMinaUppgifter.setRows(5);
        jScrollPane4.setViewportView(taPartnerInfoMinaUppgifter);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMinaProjektRuta)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(lblSlutdatum)
                                                .addComponent(lblLand)
                                                .addComponent(lblProjektchef)
                                                .addComponent(lblPrioProjekt)
                                                .addComponent(lblStatus)
                                                .addComponent(lblKostnad)
                                                .addComponent(lblStartdatum))
                                            .addComponent(lblBeskrivningProjekt))
                                        .addComponent(lblProjektNamn, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblProjektID, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnTillbakaMinaProj)
                                        .addGap(46, 46, 46)))
                                .addGap(21, 21, 21)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tfProjektNamn, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                                    .addComponent(tfBeskrivningProjekt, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                                    .addComponent(tfProjektID)
                                    .addComponent(tfStartdatum)
                                    .addComponent(tfSlutdatum)
                                    .addComponent(tfKostnad)
                                    .addComponent(cbStatusMinaProjekt, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbPrioritetMinaProjekt, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbLandMinaProjekt, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbProjektchefMinaProjekt, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cbMinaProjekt, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(59, 59, 59)
                                .addComponent(btnStatistikMinaProjekt)))
                        .addGap(49, 49, 49)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel2)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cbValjPartnerMinaProjekt, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnLaggTillPartnerMinaProjekt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnTaBortPartnerMinaProjekt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(26, 26, 26)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnLaggTillAnstalld, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbAnstalldaMinaProjekt, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel1)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(btnTaBortAnstalld, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))))
                .addContainerGap(51, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAndraUppgifterMinaProjekt)
                .addGap(19, 19, 19))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblMinaProjektRuta)
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbMinaProjekt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnStatistikMinaProjekt))
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblProjektID)
                            .addComponent(tfProjektID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblProjektNamn)
                            .addComponent(tfProjektNamn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblBeskrivningProjekt)
                            .addComponent(tfBeskrivningProjekt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane4)
                            .addComponent(jScrollPane3))
                        .addGap(26, 26, 26)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblStartdatum)
                            .addComponent(tfStartdatum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSlutdatum)
                            .addComponent(tfSlutdatum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblKostnad)
                            .addComponent(tfKostnad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblStatus)
                            .addComponent(cbStatusMinaProjekt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPrioProjekt)
                            .addComponent(cbPrioritetMinaProjekt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblProjektchef)
                            .addComponent(cbProjektchefMinaProjekt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblLand)
                            .addComponent(cbLandMinaProjekt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbAnstalldaMinaProjekt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbValjPartnerMinaProjekt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnLaggTillAnstalld)
                            .addComponent(btnLaggTillPartnerMinaProjekt))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnTaBortAnstalld)
                            .addComponent(btnTaBortPartnerMinaProjekt))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAndraUppgifterMinaProjekt)
                    .addComponent(btnTillbakaMinaProj))
                .addGap(37, 37, 37))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTillbakaMinaProjActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTillbakaMinaProjActionPerformed
        this.toBack();
        Projekt nyttProject = new Projekt(idb, aid, avdid);
        nyttProject.setVisible(true);
        nyttProject.toFront();
    }//GEN-LAST:event_btnTillbakaMinaProjActionPerformed

    private void cbMinaProjektPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbMinaProjektPopupMenuWillBecomeInvisible
        String minaProjektNamn = cbMinaProjekt.getSelectedItem().toString();

        try {
            String sqlFraga1 = "SELECT * FROM projekt JOIN ans_proj ON projekt.pid = ans_proj.pid "
                    + "WHERE ans_proj.aid = " + aid + " AND projektnamn = '" + minaProjektNamn + "'";
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
            tfProjektID.setText(pid);
            tfProjektID.setEditable(false);
            lblProjektID.requestFocus();
            tfProjektNamn.setText(minaProjekt.get("projektnamn"));
            tfProjektNamn.setEditable(false);
            tfBeskrivningProjekt.setText(minaProjekt.get("beskrivning"));
            tfBeskrivningProjekt.setEditable(false);
            tfStartdatum.setText(minaProjekt.get("startdatum"));
            tfStartdatum.setEditable(false);
            tfSlutdatum.setText(minaProjekt.get("slutdatum"));
            tfSlutdatum.setEditable(false);
            tfKostnad.setText(minaProjekt.get("kostnad"));
            tfKostnad.setEditable(false);

            fyllAnstalldaList(pid);
            fyllAnstalldaCB(pid);
            fyllPartnerList(pid);
            fyllPartnerListInfo(pid);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }//GEN-LAST:event_cbMinaProjektPopupMenuWillBecomeInvisible

    private void btnStatistikMinaProjektActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStatistikMinaProjektActionPerformed
        new ProjektStatistik(idb, aid, avdid).setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_btnStatistikMinaProjektActionPerformed

    private void btnAndraUppgifterMinaProjektActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAndraUppgifterMinaProjektActionPerformed
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

            JOptionPane.showMessageDialog(null, "Ändring sparad");

            //laddar om listan
            fyllAnstalldaList(projektId);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }


    }//GEN-LAST:event_btnAndraUppgifterMinaProjektActionPerformed

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

    private void jListPartnerInfoMinaUppgifterValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jListPartnerInfoMinaUppgifterValueChanged
        taPartnerInfoMinaUppgifter.setText("");
        String partnerNamn = jListPartnerInfoMinaUppgifter.getSelectedValue();
        if (partnerNamn != null) {
            fyllPaInfoTa(partnerNamn);
        }
    }//GEN-LAST:event_jListPartnerInfoMinaUppgifterValueChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAndraUppgifterMinaProjekt;
    private javax.swing.JButton btnLaggTillAnstalld;
    private javax.swing.JButton btnLaggTillPartnerMinaProjekt;
    private javax.swing.JButton btnStatistikMinaProjekt;
    private javax.swing.JButton btnTaBortAnstalld;
    private javax.swing.JButton btnTaBortPartnerMinaProjekt;
    private javax.swing.JButton btnTillbakaMinaProj;
    private javax.swing.JComboBox<String> cbAnstalldaMinaProjekt;
    private javax.swing.JComboBox<String> cbLandMinaProjekt;
    private javax.swing.JComboBox<String> cbMinaProjekt;
    private javax.swing.JComboBox<String> cbPrioritetMinaProjekt;
    private javax.swing.JComboBox<String> cbProjektchefMinaProjekt;
    private javax.swing.JComboBox<String> cbStatusMinaProjekt;
    private javax.swing.JComboBox<String> cbValjPartnerMinaProjekt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JList<String> jListAnstalldaMinaProjekt;
    private javax.swing.JList<String> jListPartnerInfoMinaUppgifter;
    private javax.swing.JList<String> jListPartnerMinaProjekt;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblBeskrivningProjekt;
    private javax.swing.JLabel lblKostnad;
    private javax.swing.JLabel lblLand;
    private javax.swing.JLabel lblMinaProjektRuta;
    private javax.swing.JLabel lblPrioProjekt;
    private javax.swing.JLabel lblProjektID;
    private javax.swing.JLabel lblProjektNamn;
    private javax.swing.JLabel lblProjektchef;
    private javax.swing.JLabel lblSlutdatum;
    private javax.swing.JLabel lblStartdatum;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JTextArea taPartnerInfoMinaUppgifter;
    private javax.swing.JTextField tfBeskrivningProjekt;
    private javax.swing.JTextField tfKostnad;
    private javax.swing.JTextField tfProjektID;
    private javax.swing.JTextField tfProjektNamn;
    private javax.swing.JTextField tfSlutdatum;
    private javax.swing.JTextField tfStartdatum;
    // End of variables declaration//GEN-END:variables
}
