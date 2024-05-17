/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ngo2024grupp18;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;
import oru.inf.InfDB;
import oru.inf.InfException;

/**
 *
 * @author marin
 */
public class SokEfterHandlaggare extends javax.swing.JFrame {
    private InfDB idb;
    private String aid;
    private String avdid; 
    /**
     * Creates new form SokEfterHandlaggare
     */
    public SokEfterHandlaggare(InfDB idb, String aid, String avdid) {
        initComponents();
        this.idb = idb;
        this.aid = aid;
        this.avdid = avdid; 
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitel = new javax.swing.JLabel();
        tfSokFornamn = new javax.swing.JTextField();
        tfSokEpost = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taListaInfo = new javax.swing.JTextArea();
        btnSok = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTitel.setText("Sök efter handläggare på min avdelning");

        jLabel1.setText("Sök på förnamn");

        jLabel2.setText("Sök på e-post:");

        taListaInfo.setColumns(20);
        taListaInfo.setRows(5);
        jScrollPane1.setViewportView(taListaInfo);

        btnSok.setText("Sök");
        btnSok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSokActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(lblTitel))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1)
                                    .addComponent(btnSok))
                                .addGap(122, 122, 122)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tfSokFornamn, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                                    .addComponent(tfSokEpost)))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(276, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(lblTitel)
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tfSokFornamn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tfSokEpost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(35, 35, 35)
                .addComponent(btnSok)
                .addGap(47, 47, 47)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(314, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSokActionPerformed
        String sokNamn = tfSokFornamn.getText();
        String sokEpost = tfSokEpost.getText();
        int avdelning = Integer.parseInt(this.avdid);
        taListaInfo.setText("");
    if (sokNamn.isEmpty() && sokEpost.isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Vänligen fyll i minst ett fält");
            }
    else if (sokNamn.isEmpty()) {
        System.out.println("Söker på e-post");
        String sqlFraga = "SELECT fornamn, efternamn, telefon, epost, adress FROM anstalld WHERE epost = '" + sokEpost + "' AND avdelning = " + avdelning;
        try {
            ArrayList<HashMap<String, String>> listaEpost = idb.fetchRows(sqlFraga);
            for (int i = 0; i < listaEpost.size(); i++) {
                taListaInfo.append(listaEpost.get(i).get("fornamn") + "\n" + listaEpost.get(i).get("efternamn") + "\n" + listaEpost.get(i).get("telefon") + "\n" + listaEpost.get(i).get("epost") + "\n" + listaEpost.get(i).get("adress") + "\n" + "------------------- \n");
            }
            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    else if (sokEpost.isEmpty()) 
        {
        String sqlFraga = "SELECT fornamn, efternamn, telefon, epost, adress FROM anstalld WHERE fornamn = '" + sokNamn + "' AND avdelning = " + avdelning;
        try {
            ArrayList<HashMap<String, String>> listaEpost = idb.fetchRows(sqlFraga);
            for (int i = 0; i < listaEpost.size(); i++) {
                taListaInfo.append(listaEpost.get(i).get("fornamn") + "\n" + listaEpost.get(i).get("efternamn") + "\n" + listaEpost.get(i).get("telefon") + "\n" + listaEpost.get(i).get("epost") + "\n" + listaEpost.get(i).get("adress") + "\n" + "------------------- \n");
            }
            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        }
    else {
        String sqlFraga = "SELECT fornamn, efternamn, telefon, epost, adress FROM anstalld WHERE epost = '" + sokEpost + "' AND fornamn = '" + sokNamn + "' AND avdelning = " + avdelning;
        try {
            ArrayList<HashMap<String, String>> listaEpost = idb.fetchRows(sqlFraga);
            for (int i = 0; i < listaEpost.size(); i++) {
                taListaInfo.append(listaEpost.get(i).get("fornamn") + "\n" + listaEpost.get(i).get("efternamn") + "\n" + listaEpost.get(i).get("telefon") + "\n" + listaEpost.get(i).get("epost") + "\n" + listaEpost.get(i).get("adress") + "\n" + "------------------- \n");
            }
            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    }//GEN-LAST:event_btnSokActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSok;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTitel;
    private javax.swing.JTextArea taListaInfo;
    private javax.swing.JTextField tfSokEpost;
    private javax.swing.JTextField tfSokFornamn;
    // End of variables declaration//GEN-END:variables
}
