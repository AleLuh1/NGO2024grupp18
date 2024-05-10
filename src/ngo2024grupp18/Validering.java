/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ngo2024grupp18;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author alex
 */
public class Validering {

// En klassmetod för att kontrollera att användaren har skrivit in något i ett textfält    
    public static boolean finnsText (JTextField kontrolleraRuta) {
        boolean resultat = true;

        if (kontrolleraRuta.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vänligen ange en giltig e-postadress eller lösenord");
            resultat = false;
            kontrolleraRuta.requestFocus();
        }
        return resultat;
    }

    public static boolean isKorrektFormatEpost(JTextField kontrolleraRuta) {
        boolean resultat = true;
        
        try {
        String ePost = kontrolleraRuta.getText();
        // Regex för att kontrollera om användaren har skrivit in e-post som slutar med "@example.com"
        String regex = ".+@example\\.com";
        
        // Skapar ett Pattern-objekt
        Pattern mall = Pattern.compile(regex);
        
        // Skapar ett Matcher-objekt 
        Matcher ePostMatchar = mall.matcher(ePost);
        
        if(ePostMatchar.matches()) {
            //JOptionPane.showMessageDialog(null, "Vänligen ange korrekt e-post");
            //lblKorrektEpost.setVisible(false);
        }
        
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
     

        return resultat;
    }
    
}
