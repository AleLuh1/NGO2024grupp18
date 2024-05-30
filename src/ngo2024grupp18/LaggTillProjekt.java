/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ngo2024grupp18;

import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import oru.inf.InfDB;

/**
 *
 * @author ellenor
 */
public class LaggTillProjekt extends javax.swing.JFrame {

    private InfDB idb;
    private String aid;
    private String avdid;
    //listModelAllaAnstallda kommer fylla i alla anställda
    private DefaultListModel<String> listModelAllaAnstallda;
    //ArrayList för att lagra alla nytillagda anställda
    private ArrayList<String> nyTillagdaAnstallda;

    private DefaultListModel<String> listModelPartners = new DefaultListModel<>();
    private ArrayList<String> nyTillagdaPartners;

    private DefaultListModel<String> listModelHBMal = new DefaultListModel<>();
    private ArrayList<String> nyTillagdaHBMal = new ArrayList<>();

    /**
     * Creates new form LaggTillProjekt
     */
    public LaggTillProjekt(InfDB idb, String aid, String avdid) {
        initComponents();
        this.idb = idb;
        this.aid = aid;
        this.avdid = avdid;
        LaggaTillNyttProjektPid();
        fyllCBValjProjektchef();
        fyllCBValjHallbarhetsmal();
        fyllCBValjStatus();
        fyllCBValjPrioritet();
        fyllCBValjLand();
        fyllCBAllaAnstallda();
        fyllCBPartners();
        nyTillagdaAnstallda = new ArrayList<String>();
        listModelAllaAnstallda = new DefaultListModel<String>();
        nyTillagdaPartners = new ArrayList<String>();
        this.setLocationRelativeTo(null);
        JList<String> jList = new JList<String>(listModelAllaAnstallda);
        JScrollPane scrollPane = new JScrollPane(jList);
        JPanel panel = new JPanel();
        panel.add(scrollPane);
        add(panel);
    }

    // Genererar ett nytt projekt-id (genom att hitta högsta pid och lägger till +1
    private String LaggaTillNyttProjektPid() {
        String nyttProjektPid = null;
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
            nyttProjektPid = Integer.toString(nyProjektPidInt);
            tfProjektIDLaggTIllProjekt.setText(nyttProjektPid);
            tfProjektIDLaggTIllProjekt.setEditable(false);
            lblPorjektIDLaggTIllProjekt.requestFocus();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return nyttProjektPid;
    }

    // Lägger till alla projektchefer i combobox
    public void fyllCBValjProjektchef() {
        try {
            cbProjektChefLaggTillProjekt.removeAllItems();
            String sqlFraga = "SELECT CONCAT(fornamn, ' ', efternamn) FROM anstalld JOIN handlaggare ON handlaggare.aid = anstalld.aid";
            System.out.println(sqlFraga);
            ArrayList<String> projektchefLista = idb.fetchColumn(sqlFraga);

            for (String enProjektchef : projektchefLista) {
                cbProjektChefLaggTillProjekt.addItem(enProjektchef);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Lägger till beskrivning i combobox
    public void fyllCBValjHallbarhetsmal() {
        try {
            cbHallbarhetsmalLaggTillProjekt.removeAllItems();
            String sqlFraga = "SELECT namn FROM hallbarhetsmal";
            System.out.println(sqlFraga);
            ArrayList<String> hallbarhetsmalLista = idb.fetchColumn(sqlFraga);

            for (String ettHallbarhetsmal : hallbarhetsmalLista) {
                cbHallbarhetsmalLaggTillProjekt.addItem(ettHallbarhetsmal);
            }

            for (int i = 0; i < jListHBMal.getModel().getSize(); i++) {
                var partnerNamn = jListHBMal.getModel().getElementAt(i);
                cbHallbarhetsmalLaggTillProjekt.removeItem(partnerNamn);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Lägger till status i comboboxen
    public void fyllCBValjStatus() {
        try {
            String sqlFraga = "SELECT DISTINCT status FROM projekt";
            System.out.println(sqlFraga);
            ArrayList<String> statusLista = idb.fetchColumn(sqlFraga);

            for (String enStatus : statusLista) {
                cbStatusLaggTillProjekt.addItem(enStatus);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Lägger till prioritet i combobox
    public void fyllCBValjPrioritet() {
        try {
            String sqlFraga = "SELECT DISTINCT prioritet FROM projekt";
            System.out.println(sqlFraga);
            ArrayList<String> prioritetLista = idb.fetchColumn(sqlFraga);

            for (String enPrioritet : prioritetLista) {
                cbPrioritetLaggTillProjekt.addItem(enPrioritet);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Lägger till land i combobox
    public void fyllCBValjLand() {
        try {
            cbLandLaggTillProjekt.removeAllItems();
            String sqlFraga = "SELECT namn FROM land";
            System.out.println(sqlFraga);
            ArrayList<String> landLista = idb.fetchColumn(sqlFraga);

            for (String ettLand : landLista) {
                cbLandLaggTillProjekt.addItem(ettLand);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //Fyller i CB för alla anställda 
    public void fyllCBAllaAnstallda() {
        try {
            cbLaggTillAnstalld.removeAllItems();
            String sqlFraga = "SELECT CONCAT(fornamn, ' ', efternamn) FROM anstalld";
            System.out.println(sqlFraga);
            ArrayList<String> anstalldLista = idb.fetchColumn(sqlFraga);
            for (String anstalld : anstalldLista) {
                cbLaggTillAnstalld.addItem(anstalld);
            }

            //Loopar igenom listModel, det som finns i listModel tas bort från combo-boxen
            for (int i = 0; i < jListAllaAnstallda.getModel().getSize(); i++) {
                var namn = jListAllaAnstallda.getModel().getElementAt(i);
                cbLaggTillAnstalld.removeItem(namn);
            }
            listModelPartners.removeAllElements();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

   
    private void fyllCBPartners() {
        try {
            cbPartnersLaggTillProj.removeAllItems();
            String sqlFragaPartnerPid = "SELECT namn FROM partner";
            System.out.println(sqlFragaPartnerPid);
            ArrayList<String> allaPartnersLista = idb.fetchColumn(sqlFragaPartnerPid);

            for (String enPartner : allaPartnersLista) {
                cbPartnersLaggTillProj.addItem(enPartner);
            }

            for (int i = 0; i < jListPartnersLaggTillProj.getModel().getSize(); i++) {
                var partnerNamn = jListPartnersLaggTillProj.getModel().getElementAt(i);
                cbPartnersLaggTillProj.removeItem(partnerNamn);
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

        lblLaggTillProjektRuta = new javax.swing.JLabel();
        lblPorjektIDLaggTIllProjekt = new javax.swing.JLabel();
        lblProjektNamnLaggTIllProjekt = new javax.swing.JLabel();
        lblProjektchefLaggTIllProjekt = new javax.swing.JLabel();
        lblBeskrivningLaggTIllProjekt = new javax.swing.JLabel();
        lblStartdatumLaggTIllProjekt = new javax.swing.JLabel();
        lblSlutdatumLaggTIllProjekt = new javax.swing.JLabel();
        lblKostnadLaggTIllProjekt = new javax.swing.JLabel();
        lblStatusLaggTIllProjekt = new javax.swing.JLabel();
        lblPrioritetLaggTIllProjekt = new javax.swing.JLabel();
        lblLandLaggTIllProjekt = new javax.swing.JLabel();
        btnSparaLaggTIllProjekt = new javax.swing.JButton();
        tfProjektIDLaggTIllProjekt = new javax.swing.JTextField();
        tfProjektnamnLaggTIllProjekt = new javax.swing.JTextField();
        tfStartdatumLaggTIllProjekt = new javax.swing.JTextField();
        tfSlutdatumLaggTIllProjekt = new javax.swing.JTextField();
        tfKostnadLaggTIllProjekt = new javax.swing.JTextField();
        btnTillbakaLaggTIllProjekt = new javax.swing.JButton();
        cbProjektChefLaggTillProjekt = new javax.swing.JComboBox<>();
        cbStatusLaggTillProjekt = new javax.swing.JComboBox<>();
        cbPrioritetLaggTillProjekt = new javax.swing.JComboBox<>();
        cbLandLaggTillProjekt = new javax.swing.JComboBox<>();
        lblHallbarhetsmalLaggTillProjekt = new javax.swing.JLabel();
        cbHallbarhetsmalLaggTillProjekt = new javax.swing.JComboBox<>();
        tfBeskrivningLaggTillProjekt = new javax.swing.JTextField();
        btnAnstalld = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListAllaAnstallda = new javax.swing.JList<>();
        cbLaggTillAnstalld = new javax.swing.JComboBox<>();
        btnLaggTillAnstalld = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListHBMal = new javax.swing.JList<>();
        btnLaggTIllHBMal = new javax.swing.JButton();
        lblPartnersLaggTillProj = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jListPartnersLaggTillProj = new javax.swing.JList<>();
        cbPartnersLaggTillProj = new javax.swing.JComboBox<>();
        btnLaggTillPartner = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblLaggTillProjektRuta.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        lblLaggTillProjektRuta.setText("Lägg till projekt");

        lblPorjektIDLaggTIllProjekt.setText("Projekt-ID");

        lblProjektNamnLaggTIllProjekt.setText("Projektnamn");

        lblProjektchefLaggTIllProjekt.setText("Projektchef");

        lblBeskrivningLaggTIllProjekt.setText("Beskrivning");

        lblStartdatumLaggTIllProjekt.setText("Startdatum");

        lblSlutdatumLaggTIllProjekt.setText("Slutdatum");

        lblKostnadLaggTIllProjekt.setText("Kostnad");

        lblStatusLaggTIllProjekt.setText("Status");

        lblPrioritetLaggTIllProjekt.setText("Prioritet");

        lblLandLaggTIllProjekt.setText("Land");

        btnSparaLaggTIllProjekt.setText("Spara");
        btnSparaLaggTIllProjekt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSparaLaggTIllProjektActionPerformed(evt);
            }
        });

        btnTillbakaLaggTIllProjekt.setText("Tillbaka");
        btnTillbakaLaggTIllProjekt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTillbakaLaggTIllProjektActionPerformed(evt);
            }
        });

        cbProjektChefLaggTillProjekt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Välj projektchef" }));

        cbStatusLaggTillProjekt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Välj status" }));

        cbPrioritetLaggTillProjekt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Välj prioritet" }));

        cbLandLaggTillProjekt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Välj land" }));

        lblHallbarhetsmalLaggTillProjekt.setText("Hållbarhetsmål");

        cbHallbarhetsmalLaggTillProjekt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Välj" }));

        btnAnstalld.setText("Anställda i projektet");

        jScrollPane1.setViewportView(jListAllaAnstallda);

        cbLaggTillAnstalld.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Välj" }));

        btnLaggTillAnstalld.setText("Lägg till");
        btnLaggTillAnstalld.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLaggTillAnstalldActionPerformed(evt);
            }
        });

        jScrollPane2.setViewportView(jListHBMal);

        btnLaggTIllHBMal.setText("Lägg till");
        btnLaggTIllHBMal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLaggTIllHBMalActionPerformed(evt);
            }
        });

        lblPartnersLaggTillProj.setText("Samarbetspartners");

        jScrollPane3.setViewportView(jListPartnersLaggTillProj);

        cbPartnersLaggTillProj.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Välj partner" }));

        btnLaggTillPartner.setText("Lägg till samarbetspartner");
        btnLaggTillPartner.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLaggTillPartnerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(128, 128, 128)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPorjektIDLaggTIllProjekt)
                            .addComponent(lblLaggTillProjektRuta)))
                    .addComponent(lblSlutdatumLaggTIllProjekt)
                    .addComponent(lblKostnadLaggTIllProjekt)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(lblProjektNamnLaggTIllProjekt)
                        .addComponent(lblBeskrivningLaggTIllProjekt, javax.swing.GroupLayout.Alignment.LEADING))
                    .addComponent(lblProjektchefLaggTIllProjekt)
                    .addComponent(lblStartdatumLaggTIllProjekt)
                    .addComponent(lblStatusLaggTIllProjekt)
                    .addComponent(lblPrioritetLaggTIllProjekt)
                    .addComponent(lblHallbarhetsmalLaggTillProjekt)
                    .addComponent(lblLandLaggTIllProjekt)
                    .addComponent(btnAnstalld))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tfStartdatumLaggTIllProjekt)
                    .addComponent(tfBeskrivningLaggTillProjekt, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cbLandLaggTillProjekt, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbPrioritetLaggTillProjekt, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tfSlutdatumLaggTIllProjekt)
                    .addComponent(tfKostnadLaggTIllProjekt)
                    .addComponent(cbProjektChefLaggTillProjekt, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbStatusLaggTillProjekt, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tfProjektIDLaggTIllProjekt)
                    .addComponent(tfProjektnamnLaggTIllProjekt, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane2))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbHallbarhetsmalLaggTillProjekt, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbLaggTillAnstalld, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLaggTillAnstalld, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                    .addComponent(btnLaggTIllHBMal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblPartnersLaggTillProj, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(cbPartnersLaggTillProj, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLaggTillPartner, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(106, 106, 106))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(btnTillbakaLaggTIllProjekt)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSparaLaggTIllProjekt)
                .addGap(40, 40, 40))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(lblLaggTillProjektRuta)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPorjektIDLaggTIllProjekt)
                    .addComponent(tfProjektIDLaggTIllProjekt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfProjektnamnLaggTIllProjekt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblProjektNamnLaggTIllProjekt))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBeskrivningLaggTIllProjekt)
                    .addComponent(tfBeskrivningLaggTillProjekt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbProjektChefLaggTillProjekt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblProjektchefLaggTIllProjekt))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAnstalld)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblStartdatumLaggTIllProjekt)
                            .addComponent(tfStartdatumLaggTIllProjekt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(cbLaggTillAnstalld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(btnLaggTillAnstalld)
                        .addGap(24, 24, 24)
                        .addComponent(lblPartnersLaggTillProj)
                        .addGap(6, 6, 6)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblSlutdatumLaggTIllProjekt)
                            .addComponent(tfSlutdatumLaggTIllProjekt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblKostnadLaggTIllProjekt)
                            .addComponent(tfKostnadLaggTIllProjekt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbStatusLaggTillProjekt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblStatusLaggTIllProjekt)))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPrioritetLaggTIllProjekt)
                    .addComponent(cbPrioritetLaggTillProjekt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbPartnersLaggTillProj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbLandLaggTillProjekt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLandLaggTIllProjekt)
                    .addComponent(btnLaggTillPartner))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cbHallbarhetsmalLaggTillProjekt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnLaggTIllHBMal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSparaLaggTIllProjekt)
                            .addComponent(btnTillbakaLaggTIllProjekt))
                        .addGap(39, 39, 39))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblHallbarhetsmalLaggTillProjekt))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTillbakaLaggTIllProjektActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTillbakaLaggTIllProjektActionPerformed
        this.dispose();
        AllaProjekt nyttAllaProjekt = new AllaProjekt(idb, aid, avdid);
        nyttAllaProjekt.setVisible(true);
    }//GEN-LAST:event_btnTillbakaLaggTIllProjektActionPerformed

    private void btnSparaLaggTIllProjektActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSparaLaggTIllProjektActionPerformed
        //Gör om nya projektID till en int igen för att kunna lägga i db 
        String nyProjektPidStr = tfProjektIDLaggTIllProjekt.getText();
        int nyProjektPidInt = Integer.parseInt(nyProjektPidStr);

// säkerställer så användaren har fyllt alla textfields och comboboxar 
        String laggTillProjektNamn = tfProjektnamnLaggTIllProjekt.getText();
        if (laggTillProjektNamn.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vänligen fyll i ett projektnamn");
        }

        String laggTillBeskrivning = tfBeskrivningLaggTillProjekt.getText();
        if (laggTillBeskrivning.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vänligen fyll i en beskrivning");
        }

        String laggTillProjektchefStr = cbProjektChefLaggTillProjekt.getSelectedItem().toString();
        System.out.println(laggTillProjektchefStr);

        if (laggTillProjektchefStr.equals("Välj projektchef")) {
            JOptionPane.showMessageDialog(null, "Vänligen välj en projektchef");
        }

        String laggTillStartdatum = tfStartdatumLaggTIllProjekt.getText();
        if (laggTillStartdatum.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vänligen fyll i en startdatum");
        }
        if (!Validering.isKorrektFormatDatum(laggTillStartdatum)) {
            return;
        }
        String laggTillSlutdatum = tfSlutdatumLaggTIllProjekt.getText();
        if (laggTillSlutdatum.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vänligen fyll i en slutdatum");
        }
        if (!Validering.isKorrektFormatDatum(laggTillSlutdatum)) {
            return;
        }
        String laggTillKostnadStr = tfKostnadLaggTIllProjekt.getText();
        //Gör om kostnad till en double så kan lägga in i db
        double laggTillKostnad = Double.parseDouble(laggTillKostnadStr);

        if (laggTillKostnadStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vänligen fyll i en kostnad");
        }

        String laggTillStatus = cbStatusLaggTillProjekt.getSelectedItem().toString();
        if (laggTillStatus.equals("Välj status")) {
            JOptionPane.showMessageDialog(null, "Vänligen välj status");
        }

        String laggTillPrioritet = cbPrioritetLaggTillProjekt.getSelectedItem().toString();
        if (laggTillPrioritet.equals("Välj prioritet")) {
            JOptionPane.showMessageDialog(null, "Vänligen välj prioritet");
        }

        String laggTillLandStr = cbLandLaggTillProjekt.getSelectedItem().toString();
        if (laggTillLandStr.equals("Välj land")) {
            JOptionPane.showMessageDialog(null, "Vänligen välj ett land");
        }

        String laggTillHallbarhetsmal = cbHallbarhetsmalLaggTillProjekt.getSelectedItem().toString();
        if (laggTillHallbarhetsmal.equals("Välj hållbarhetsmål")) {
            JOptionPane.showMessageDialog(null, "Vänligen välj ett hållbarhetsmål");
        }

        try {
            String sqlFragaProjchef = "SELECT aid FROM anstalld WHERE CONCAT(fornamn, ' ', efternamn) = '" + laggTillProjektchefStr + "'";
            System.out.println(sqlFragaProjchef);
            String projchefAid = idb.fetchSingle(sqlFragaProjchef);
            //Gör om projektchef från String till int för att kunna lägga in i db
            int projchefAidInt = Integer.parseInt(projchefAid);
            System.out.println(projchefAidInt);

            String sqlFragaLand = "SELECT lid FROM land WHERE namn = '" + laggTillLandStr + "'";
            System.out.println(sqlFragaLand);
            String landIdStr = idb.fetchSingle(sqlFragaLand);
            // Gör om land från String till int för att kunna lägga in i db
            int LandId = Integer.parseInt(landIdStr);
            System.out.println(LandId);

//            String sqlFragaHallbMal = "SELECT hid FROM hallbarhetsmal WHERE namn = '" + laggTillHallbarhetsmal + "'";
//            System.out.println(sqlFragaHallbMal);
//            String hidStr = idb.fetchSingle(sqlFragaHallbMal);
//            //Gör om hid från String till int för att kunna lägga in i db
//            int hidInt = Integer.parseInt(hidStr);
//            System.out.println(hidInt);
//
            String sqlFraga1 = "INSERT INTO projekt (pid, projektnamn, beskrivning, startdatum, slutdatum, kostnad, status, prioritet, projektchef, land) VALUES (" + nyProjektPidInt + ", '" + laggTillProjektNamn + "', '" + laggTillBeskrivning + "', str_to_date('" + laggTillStartdatum + "', '%Y-%m-%d'),  str_to_date('" + laggTillSlutdatum + "', '%Y-%m-%d'), " + laggTillKostnad + ", '" + laggTillStatus + "', '" + laggTillPrioritet + "', " + projchefAidInt + ", " + LandId + ")";
            System.out.println(sqlFraga1);
            idb.insert(sqlFraga1);
//
//            String sqlFraga2 = "INSERT INTO proj_hallbarhet (pid, hid) VALUES (" + nyProjektPidInt + ", " + hidInt + ")";
//            System.out.println(sqlFraga2);
//            idb.insert(sqlFraga2);

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

                String nyProjPidStr = tfProjektIDLaggTIllProjekt.getText();
                int pid = Integer.parseInt(nyProjektPidStr);

                String sqlFraga3 = "INSERT INTO ans_proj (pid, aid) VALUES (" + pid + ", " + aid + ")";
                System.out.println(sqlFraga3);
                idb.insert(sqlFraga3);
            }
            nyTillagdaAnstallda.clear();
            
            for (String enPartner : nyTillagdaPartners) {
                String sqlFragaPartner = "SELECT namn FROM partner WHERE namn = '" + enPartner + "'";
                System.out.println(sqlFragaPartner);
                String partnerNamn = idb.fetchSingle(sqlFragaPartner);
                System.out.println(partnerNamn);

                String partnerIDsqlFraga = "SELECT pid FROM partner WHERE namn = '" + partnerNamn + "'";
                System.out.println(partnerIDsqlFraga);
                String partnerID = idb.fetchSingle(partnerIDsqlFraga);

                String sqlFragaPartner2 = "INSERT INTO projekt_partner (pid, partner_pid) VALUES (" + nyProjektPidInt + ", " + partnerID + ")";
                System.out.println(sqlFragaPartner2);
                idb.insert(sqlFragaPartner2);
            }
            nyTillagdaPartners.clear();
            
            

            for (String ettMal : nyTillagdaHBMal) {
                String sqlFragaHBMalNamn = "SELECT namn FROM hallbarhetsmal WHERE namn = '" + ettMal + "'";
                System.out.println(sqlFragaHBMalNamn);
                String hbMalNamn = idb.fetchSingle(sqlFragaHBMalNamn);
                System.out.println(hbMalNamn);

                String malIDSqlFraga = "SELECT hid FROM hallbarhetsmal WHERE namn = '" + hbMalNamn + "'";
                System.out.println(malIDSqlFraga);
                String hbmalIDStr = idb.fetchSingle(malIDSqlFraga);
                int hbMalID = Integer.parseInt(hbmalIDStr);

                String sqlFragaHBProj = "INSERT INTO proj_hallbarhet (pid, hid) VALUES (" + nyProjektPidInt + ", " + hbMalID + ")";
                System.out.println(sqlFragaHBProj);
                idb.insert(sqlFragaHBProj);
            }
            nyTillagdaHBMal.clear();

            JOptionPane.showMessageDialog(null, "Ändring sparad");
            this.dispose();
            new Meny(idb, aid, avdid).setVisible(true);

        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }//GEN-LAST:event_btnSparaLaggTIllProjektActionPerformed

    private void btnLaggTillAnstalldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLaggTillAnstalldActionPerformed
        String anstalld = cbLaggTillAnstalld.getSelectedItem().toString();
        listModelAllaAnstallda.addElement(anstalld);
        jListAllaAnstallda.setModel(listModelAllaAnstallda);
        fyllCBAllaAnstallda();
        nyTillagdaAnstallda.add(anstalld);
    }//GEN-LAST:event_btnLaggTillAnstalldActionPerformed

    private void btnLaggTillPartnerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLaggTillPartnerActionPerformed
        String partner = cbPartnersLaggTillProj.getSelectedItem().toString();
        listModelPartners.addElement(partner);
        jListPartnersLaggTillProj.setModel(listModelPartners);
        fyllCBPartners();
        nyTillagdaPartners.add(partner);
    }//GEN-LAST:event_btnLaggTillPartnerActionPerformed

    private void btnLaggTIllHBMalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLaggTIllHBMalActionPerformed
        String malNamn = cbHallbarhetsmalLaggTillProjekt.getSelectedItem().toString();
        listModelHBMal.addElement(malNamn);
        jListHBMal.setModel(listModelHBMal);
        fyllCBValjHallbarhetsmal();
        nyTillagdaHBMal.add(malNamn);
    }//GEN-LAST:event_btnLaggTIllHBMalActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnAnstalld;
    private javax.swing.JButton btnLaggTIllHBMal;
    private javax.swing.JButton btnLaggTillAnstalld;
    private javax.swing.JButton btnLaggTillPartner;
    private javax.swing.JButton btnSparaLaggTIllProjekt;
    private javax.swing.JButton btnTillbakaLaggTIllProjekt;
    private javax.swing.JComboBox<String> cbHallbarhetsmalLaggTillProjekt;
    private javax.swing.JComboBox<String> cbLaggTillAnstalld;
    private javax.swing.JComboBox<String> cbLandLaggTillProjekt;
    private javax.swing.JComboBox<String> cbPartnersLaggTillProj;
    private javax.swing.JComboBox<String> cbPrioritetLaggTillProjekt;
    private javax.swing.JComboBox<String> cbProjektChefLaggTillProjekt;
    private javax.swing.JComboBox<String> cbStatusLaggTillProjekt;
    private javax.swing.JList<String> jListAllaAnstallda;
    private javax.swing.JList<String> jListHBMal;
    private javax.swing.JList<String> jListPartnersLaggTillProj;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblBeskrivningLaggTIllProjekt;
    private javax.swing.JLabel lblHallbarhetsmalLaggTillProjekt;
    private javax.swing.JLabel lblKostnadLaggTIllProjekt;
    private javax.swing.JLabel lblLaggTillProjektRuta;
    private javax.swing.JLabel lblLandLaggTIllProjekt;
    private javax.swing.JLabel lblPartnersLaggTillProj;
    private javax.swing.JLabel lblPorjektIDLaggTIllProjekt;
    private javax.swing.JLabel lblPrioritetLaggTIllProjekt;
    private javax.swing.JLabel lblProjektNamnLaggTIllProjekt;
    private javax.swing.JLabel lblProjektchefLaggTIllProjekt;
    private javax.swing.JLabel lblSlutdatumLaggTIllProjekt;
    private javax.swing.JLabel lblStartdatumLaggTIllProjekt;
    private javax.swing.JLabel lblStatusLaggTIllProjekt;
    private javax.swing.JTextField tfBeskrivningLaggTillProjekt;
    private javax.swing.JTextField tfKostnadLaggTIllProjekt;
    private javax.swing.JTextField tfProjektIDLaggTIllProjekt;
    private javax.swing.JTextField tfProjektnamnLaggTIllProjekt;
    private javax.swing.JTextField tfSlutdatumLaggTIllProjekt;
    private javax.swing.JTextField tfStartdatumLaggTIllProjekt;
    // End of variables declaration//GEN-END:variables
}
