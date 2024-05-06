/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ngo2024grupp18;

import oru.inf.InfDB;
import oru.inf.InfException;
/**
 *
 * @author alex
 */
public class Inloggning extends javax.swing.JFrame {
    private InfDB idb;
    /**
     * Creates new form Inloggning
     */
    public Inloggning(InfDB idb) {
        this.idb = idb;
        initComponents();
        lblFelmeddelande.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblEpost = new javax.swing.JLabel();
        lblLösenord = new javax.swing.JLabel();
        lblFelmeddelande = new javax.swing.JLabel();
        tfEpost = new javax.swing.JTextField();
        tfLösenord = new javax.swing.JTextField();
        btnLoggaIn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblEpost.setText("E-post");

        lblLösenord.setText("Lösenord");

        lblFelmeddelande.setForeground(new java.awt.Color(255, 0, 51));
        lblFelmeddelande.setText("Felaktig epost eller lösenord");

        tfEpost.setText("maria.g@example.com");

        tfLösenord.setText("password123");

        btnLoggaIn.setText("Logga in");
        btnLoggaIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoggaInActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnLoggaIn)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(54, 54, 54)
                            .addComponent(lblFelmeddelande))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(33, 33, 33)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(lblLösenord, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tfLösenord, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(lblEpost, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tfEpost, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(194, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEpost)
                    .addComponent(tfEpost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLösenord)
                    .addComponent(tfLösenord, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblFelmeddelande)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLoggaIn)
                .addContainerGap(146, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoggaInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoggaInActionPerformed
       String ePost = tfEpost.getText();
       String losen = tfLösenord.getText();
       
       try{
           String sqlFraga = "SELECT losenord FROM anstalld WHERE epost = '" + ePost + "'";
           System.out.println(sqlFraga);
           String dbLosen = idb.fetchSingle(sqlFraga);
           //Kom ihåg att kontrollera att användaren har skrivit in en epost
           if(losen.equals(dbLosen)){
              //new Meny(idb, ePost).setVisible(true);
              //this.setVisible(false);
             if(isAdmin(ePost)){
                System.out.println("administratör");
             }else if(isProjektledare(ePost)){
                System.out.println("projektledare");
             }else {
                System.out.println("handläggare");
             }
    
           }
           else{
               lblFelmeddelande.setVisible(true);
           }
           
       } catch (Exception ex){
           System.out.println(ex.getMessage());
       }
    }//GEN-LAST:event_btnLoggaInActionPerformed
    
    private boolean isAdmin(String ePost){
    String aid = null;
        try{ 
            String sqlFraga = "SELECT admin.aid FROM admin JOIN anstalld ON admin.aid = anstalld.aid WHERE anstalld.epost  = '" + ePost + "'";
              System.out.println(sqlFraga);
              aid = idb.fetchSingle(sqlFraga);
              System.out.println(aid);
        }catch (Exception ex){
              System.out.println(ex.getMessage());
          }
        
        if(aid != null) {
            return true;
        }
        
        return false;
    }
            
     private boolean isProjektledare(String ePost){
         String aid = null;
     
        try{ 
            String sqlFraga = "SELECT ans_proj.aid FROM ans_proj JOIN anstalld ON ans_proj.aid = anstalld.aid WHERE anstalld.epost = '" + ePost + "'";
              System.out.println(sqlFraga);
              aid = idb.fetchSingle(sqlFraga);
              System.out.println(aid);
        }catch (Exception ex){
              System.out.println(ex.getMessage());
          }
        
        if(aid != null) {
            return true;
        }
        
        return false;}       
            
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Inloggning.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inloggning.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inloggning.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inloggning.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLoggaIn;
    private javax.swing.JLabel lblEpost;
    private javax.swing.JLabel lblFelmeddelande;
    private javax.swing.JLabel lblLösenord;
    private javax.swing.JTextField tfEpost;
    private javax.swing.JTextField tfLösenord;
    // End of variables declaration//GEN-END:variables
}
