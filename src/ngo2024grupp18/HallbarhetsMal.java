/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ngo2024grupp18;

import oru.inf.InfDB;
import oru.inf.InfException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author alex
 */
public class HallbarhetsMal extends javax.swing.JFrame {

    private InfDB idb;
    private String aid;
    private String avdid;

    /**
     * Creates new form HallbarhetsMal
     */
    public HallbarhetsMal(InfDB idb) {
        initComponents();
        this.idb = idb;
        fyllCBHallbarhetsmal();
        this.setLocationRelativeTo(null);
    }
// CB = ComboBox

    private void fyllCBHallbarhetsmal() {
        try {
            String sqlFraga = "SELECT namn FROM hallbarhetsmal";
            System.out.println(sqlFraga);
            ArrayList<String> hallbarhetsNamnLista;
            hallbarhetsNamnLista = idb.fetchColumn(sqlFraga);
            for (String ettNamn : hallbarhetsNamnLista) {
                cbHallbarhetsmal.addItem(ettNamn);

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

        lblHallbarhetsMalRuta = new javax.swing.JLabel();
        cbHallbarhetsmal = new javax.swing.JComboBox<>();
        lblMalnummer = new javax.swing.JLabel();
        lblBeskrivningHallbar = new javax.swing.JLabel();
        lblPrioritetHallbar = new javax.swing.JLabel();
        tfMalnummerHallbar = new javax.swing.JTextField();
        tfPrioritetHallbar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        taBeskrivningHallbar = new javax.swing.JTextArea();
        btnTillbakaHallbar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblHallbarhetsMalRuta.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblHallbarhetsMalRuta.setText("Hållbarhetsmål");

        cbHallbarhetsmal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Välj" }));
        cbHallbarhetsmal.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbHallbarhetsmalPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        lblMalnummer.setText("Målnummer");

        lblBeskrivningHallbar.setText("Beskrivning");

        lblPrioritetHallbar.setText("Prioritet");

        taBeskrivningHallbar.setColumns(20);
        taBeskrivningHallbar.setRows(5);
        jScrollPane1.setViewportView(taBeskrivningHallbar);

        btnTillbakaHallbar.setText("Tillbaka");
        btnTillbakaHallbar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTillbakaHallbarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbHallbarhetsmal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblHallbarhetsMalRuta)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblMalnummer, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblPrioritetHallbar, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfPrioritetHallbar, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfMalnummerHallbar, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(122, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblBeskrivningHallbar, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnTillbakaHallbar)
                .addGap(374, 374, 374))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(lblHallbarhetsMalRuta)
                .addGap(18, 18, 18)
                .addComponent(cbHallbarhetsmal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMalnummer)
                    .addComponent(tfMalnummerHallbar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPrioritetHallbar)
                    .addComponent(tfPrioritetHallbar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblBeskrivningHallbar)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(btnTillbakaHallbar)
                .addGap(25, 25, 25))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbHallbarhetsmalPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbHallbarhetsmalPopupMenuWillBecomeInvisible
        String hbMal = (String) cbHallbarhetsmal.getSelectedItem();
        try {
            String sqlFraga = " SELECT * FROM hallbarhetsmal WHERE namn = '" + hbMal + "'";
            System.out.println(sqlFraga);
            HashMap<String, String> hallbarhetsmal = idb.fetchRow(sqlFraga);
            tfMalnummerHallbar.setText(hallbarhetsmal.get("malnummer"));
            taBeskrivningHallbar.setText(hallbarhetsmal.get("beskrivning"));
            tfPrioritetHallbar.setText(hallbarhetsmal.get("prioritet"));
            taBeskrivningHallbar.setCaretPosition(0);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }//GEN-LAST:event_cbHallbarhetsmalPopupMenuWillBecomeInvisible

    private void btnTillbakaHallbarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTillbakaHallbarActionPerformed
        this.dispose();
        Projekt nyttProject = new Projekt(idb, aid, avdid);
        nyttProject.setVisible(true);
    }//GEN-LAST:event_btnTillbakaHallbarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTillbakaHallbar;
    private javax.swing.JComboBox<String> cbHallbarhetsmal;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBeskrivningHallbar;
    private javax.swing.JLabel lblHallbarhetsMalRuta;
    private javax.swing.JLabel lblMalnummer;
    private javax.swing.JLabel lblPrioritetHallbar;
    private javax.swing.JTextArea taBeskrivningHallbar;
    private javax.swing.JTextField tfMalnummerHallbar;
    private javax.swing.JTextField tfPrioritetHallbar;
    // End of variables declaration//GEN-END:variables
}
