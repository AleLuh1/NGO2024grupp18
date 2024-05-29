/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ngo2024grupp18;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import oru.inf.InfDB;

/**
 *
 * @author alex
 */
public class Validering {

    private InfDB idb;

    public Validering(InfDB idb) {
        this.idb = idb;
    }
// En klassmetod för att kontrollera att användaren har skrivit in något i ett textfält    

    public static boolean finnsTextTF(JTextField kontrolleraRuta) {
        boolean resultat = true;

        if (kontrolleraRuta.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vänligen ange en giltig e-postadress");
            resultat = false;
            kontrolleraRuta.requestFocus();
        }
        return resultat;
    }

    public static boolean finnsTextPF(JPasswordField kontrolleraRutaPF) {
        boolean resultat = true;
        
        if (kontrolleraRutaPF.getPassword().length == 0) {
            JOptionPane.showMessageDialog(null, "Vänligen ange ett giltigt lösenord");
            resultat = false;
            kontrolleraRutaPF.requestFocus();
        }
        return resultat;
    }
    public static boolean finnsTextTA(JTextArea kontrolleraTA) {
        boolean resultat = true;

        if (kontrolleraTA.getText().isEmpty()) {
            resultat = false;
            kontrolleraTA.requestFocus();
        }
        return resultat;
    }

// En klassmetod för att kolla om e-posten som användaren har skrivit in slutar med @example.com
    public static boolean isKorrektFormatEpost(JTextField kontrolleraRuta) {
        boolean resultat = true;
        String ePost = kontrolleraRuta.getText();
        // Regex för att kontrollera om användaren har skrivit in e-post som slutar med "@example.com"
        String regex = ".+@example\\.com";
        // Skapar ett Pattern-objekt
        Pattern mall = Pattern.compile(regex);
        // Skapar ett Matcher-objekt 
        Matcher ePostMatchar = mall.matcher(ePost);
        if (!(ePostMatchar.matches())) {
            JOptionPane.showMessageDialog(null, "Vänligen ange korrekt e-postformat som slutar på '@example.com'");
            resultat = false;
        }
        return resultat;
    }

    public static boolean isKorrektFormatEpostPartner(JTextField kontrolleraRuta) {
        boolean resultat = true;
        String ePost = kontrolleraRuta.getText();
        // Regex för att kontrollera om användaren har skrivit in e-post som slutar med "@example.com"
        String regex = "^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$";
        // Skapar ett Pattern-objekt
        Pattern mall = Pattern.compile(regex);
        // Skapar ett Matcher-objekt 
        Matcher ePostMatchar = mall.matcher(ePost);
        if (!(ePostMatchar.matches())) {
            JOptionPane.showMessageDialog(null, "Vänligen ange korrekt e-post");
            resultat = false;
        }
        return resultat;
    }
    public static boolean isKorrektValuta(JTextField kontrolleraRuta){
        boolean resultat = true;
        String valuta = kontrolleraRuta.getText();
        //Regex för att kontrollera så att när en admin skapar ett nytt land så blir formatet på valutan rätt ex. xx.xxx, eller att det kan variera på siffror framför och efter punkten
        String regex = "^\\d+\\.\\d$";
        Pattern mall = Pattern.compile(regex);
        Matcher valutaMatchar = mall.matcher(valuta);
        if (!valutaMatchar.matches()){
            JOptionPane.showMessageDialog(null, "Vänligen ange en korrekt valuta i detta format xx.xx");
            resultat = false;
        }
        return resultat;
    }
}
