/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ngo2024grupp18;

import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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

    /**
     * Creates new form LaggTillProjekt
     */
    public LaggTillProjekt(InfDB idb, String aid, String avdid) {
        initComponents();
        this.idb = idb;
        this.aid = aid;
        this.avdid = avdid;
        LaggaTillNyttProjektPid();
        fyllCBVäljProjektchef();
        fyllCBVäljHallbarhetsmal();
        fyllCBVäljStatus();
        fyllCBVäljPrioritet();
        fyllCBVäljLand();
        fyllCBAllaAnstallda();
        nyTillagdaAnstallda = new ArrayList<String>();
        listModelAllaAnstallda = new DefaultListModel<String>();
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
    public void fyllCBVäljProjektchef() {
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
    public void fyllCBVäljHallbarhetsmal() {
        try {
            String sqlFraga = "SELECT DISTINCT namn FROM hallbarhetsmal";
            System.out.println(sqlFraga);
            ArrayList<String> hallbarhetsmalLista = idb.fetchColumn(sqlFraga);

            for (String ettHallbarhetsmal : hallbarhetsmalLista) {
                cbHallbarhetsmalLaggTillProjekt.addItem(ettHallbarhetsmal);
            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }

    }

    // Lägger till status i comboboxen
    public void fyllCBVäljStatus() {
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
    public void fyllCBVäljPrioritet() {
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
    public void fyllCBVäljLand() {
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

        cbHallbarhetsmalLaggTillProjekt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Välj hållbarhetsmål" }));

        btnAnstalld.setText("Anställda i projektet");

        jScrollPane1.setViewportView(jListAllaAnstallda);

        btnLaggTillAnstalld.setText("Lägg till");
        btnLaggTillAnstalld.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLaggTillAnstalldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(68, Short.MAX_VALUE)
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
                        .addGap(27, 27, 27))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(btnTillbakaLaggTIllProjekt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cbHallbarhetsmalLaggTillProjekt, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(265, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSparaLaggTIllProjekt)
                        .addGap(78, 78, 78))
                    .addGroup(layout.createSequentialGroup()
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
                            .addComponent(jScrollPane1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbLaggTillAnstalld, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnLaggTillAnstalld, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))))
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
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbProjektChefLaggTillProjekt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblProjektchefLaggTIllProjekt))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAnstalld)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblStartdatumLaggTIllProjekt)
                            .addComponent(tfStartdatumLaggTIllProjekt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
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
                            .addComponent(lblStatusLaggTIllProjekt))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblPrioritetLaggTIllProjekt)
                            .addComponent(cbPrioritetLaggTillProjekt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbLandLaggTillProjekt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblLandLaggTIllProjekt))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblHallbarhetsmalLaggTillProjekt, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cbHallbarhetsmalLaggTillProjekt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cbLaggTillAnstalld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(btnLaggTillAnstalld)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTillbakaLaggTIllProjekt)
                    .addComponent(btnSparaLaggTIllProjekt))
                .addContainerGap())
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

        String laggTillSlutdatum = tfSlutdatumLaggTIllProjekt.getText();
        if (laggTillSlutdatum.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vänligen fyll i en slutdatum");
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

            String sqlFragaHallbMal = "SELECT hid FROM hallbarhetsmal WHERE namn = '" + laggTillHallbarhetsmal + "'";
            System.out.println(sqlFragaHallbMal);
            String hidStr = idb.fetchSingle(sqlFragaHallbMal);
            //Gör om hid från String till int för att kunna lägga in i db
            int hidInt = Integer.parseInt(hidStr);
            System.out.println(hidInt);

            String sqlFraga1 = "INSERT INTO projekt (pid, projektnamn, beskrivning, startdatum, slutdatum, kostnad, status, prioritet, projektchef, land) VALUES (" + nyProjektPidInt + ", '" + laggTillProjektNamn + "', '" + laggTillBeskrivning + "', str_to_date('" + laggTillStartdatum + "', '%Y-%m-%d'),  str_to_date('" + laggTillSlutdatum + "', '%Y-%m-%d'), " + laggTillKostnad + ", '" + laggTillStatus + "', '" + laggTillPrioritet + "', " + projchefAidInt + ", " + LandId + ")";
            System.out.println(sqlFraga1);
            idb.insert(sqlFraga1);

            String sqlFraga2 = "INSERT INTO proj_hallbarhet (pid, hid) VALUES (" + nyProjektPidInt + ", " + hidInt + ")";
            System.out.println(sqlFraga2);
            idb.insert(sqlFraga2);

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

                String sqlFraga3 = "INSERT INTO ans_proj (pid, aid) VALUES (" + nyProjektPidInt + ", " + aid + ")";
                System.out.println(sqlFraga3);
                idb.insert(sqlFraga3);
            }
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnAnstalld;
    private javax.swing.JButton btnLaggTillAnstalld;
    private javax.swing.JButton btnSparaLaggTIllProjekt;
    private javax.swing.JButton btnTillbakaLaggTIllProjekt;
    private javax.swing.JComboBox<String> cbHallbarhetsmalLaggTillProjekt;
    private javax.swing.JComboBox<String> cbLaggTillAnstalld;
    private javax.swing.JComboBox<String> cbLandLaggTillProjekt;
    private javax.swing.JComboBox<String> cbPrioritetLaggTillProjekt;
    private javax.swing.JComboBox<String> cbProjektChefLaggTillProjekt;
    private javax.swing.JComboBox<String> cbStatusLaggTillProjekt;
    private javax.swing.JList<String> jListAllaAnstallda;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBeskrivningLaggTIllProjekt;
    private javax.swing.JLabel lblHallbarhetsmalLaggTillProjekt;
    private javax.swing.JLabel lblKostnadLaggTIllProjekt;
    private javax.swing.JLabel lblLaggTillProjektRuta;
    private javax.swing.JLabel lblLandLaggTIllProjekt;
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
