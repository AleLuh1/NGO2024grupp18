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
    public static boolean isKorrektFormatEpostTF(JTextField kontrolleraRuta) {
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
    public static boolean isKorrektFormatEpostString(String epost) {
        boolean resultat = true;
        String epostFormat = ".+@example\\.com";
           if (!(epost.matches(epostFormat))) {
            JOptionPane.showMessageDialog(null, "Vänligen ange korrekt e-postformat som slutar på '@example.com'");
            resultat = false;
        }
        return resultat;
    }
    
    public static boolean isKorrektFormatEpostPartnerTF(JTextField kontrolleraRuta) {
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
    
    public static boolean isKorrektFormatEpostPartner(String epostPartner) {
       
         // Skapar ett Pattern-objekt
        if (!epostPartner.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")) {
            JOptionPane.showMessageDialog(null, "Vänligen ange korrekt epost-format som innehåller @-tecken");
            return false;
        }
        return true;
    }
    public static boolean isKorrektFormatTelnr(String telefon) {
        if (!telefon.matches("\\d+(-\\d+)*")) {
            JOptionPane.showMessageDialog(null, "Vänligen ange endast tillåtna tecken för telefonnummer (siffror och bindestreck)");
            return false;
        }
        return true;
        
        
    }
    public static boolean isKorrektFormatDatum(String datum) {
        String datumFormat = "^(\\d{4})-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$";
           if (!datum.matches(datumFormat)) {
               JOptionPane.showMessageDialog(null, "Vänligen ange datum i formatet ÅÅÅÅ-MM-DD");
               return false;
           }
           return true;
    }

    public static boolean isKorrektValuta(JTextField kontrolleraRuta) {
        boolean resultat = true;
        String valuta = kontrolleraRuta.getText();
        //Regex för att kontrollera så att när en admin skapar ett nytt land så blir formatet på valutan rätt ex. xx.xxx, eller att det kan variera på siffror framför och efter punkten
        String regex = "^\\d+\\.\\d$";
        Pattern mall = Pattern.compile(regex);
        Matcher valutaMatchar = mall.matcher(valuta);
        if (!valutaMatchar.matches()) {
            JOptionPane.showMessageDialog(null, "Vänligen ange en korrekt valuta i detta format xx.xx");
            resultat = false;
        }
        return resultat;
    }

    public static boolean isKorrektKontaktPerson(JTextField kontrolleraRuta) {
        boolean resultat = true;
        String namn = kontrolleraRuta.getText();
         // Kontrollerar så att namnet när man skapar ny samarbetspartner består av bokstäver och ett mellanslag, som visar på att det ska vara ett för och efternamn
        String regex = ("[a-zA-Z]+\\s[a-zA-Z]+");
        Pattern mall = Pattern.compile(regex);
        Matcher namnMatchar = mall.matcher(namn);
        if (!namnMatchar.matches()) {
            JOptionPane.showMessageDialog(null, "Vänligen ange ett giltigt för och efternamn för kontaktpersonen");
            resultat = false;
        }
        return resultat;      
    }

    public static boolean isKorrektAdress(JTextField kontrolleraRuta) {
        boolean resultat = true;
        String adress = kontrolleraRuta.getText();
        // Kontrollerar så att adressen när man skapar ny samarbetspartner innehåller bokstäver och siffror med eventuellt mellanslag
        String regex =  "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d\\s]+$";
        Pattern mall = Pattern.compile(regex);
        Matcher adressMatchar = mall.matcher(adress);
        if (!adressMatchar.matches()) {
            JOptionPane.showMessageDialog(null, "Vänligen ange en giltigt adress");
            resultat = false;
        }
        return resultat;
    }
    
    public static boolean isKorrektTelefon(JTextField kontrolleraRuta) {
         boolean resultat = true;
        String telefon = kontrolleraRuta.getText();
        // Kontrollerar så att telefonnumret när man skapar ny samarbetspartner följer något av de angivna formaten +xxxxxxxxxx eller xxx-xxx-xxxx
        String regex = ("\\+?\\d{10}|\\d{3}-\\d{3}-\\d{4}");
        Pattern mall = Pattern.compile(regex);
        Matcher telefonMatchar = mall.matcher(telefon);
        if (!telefonMatchar.matches()) {
            JOptionPane.showMessageDialog(null, "Vänligen ange ett giltigt telefonnummer, +xxxxxxxxxx eller xxx-xxx-xxxx");
            resultat = false;
        }
        return resultat;       
    }
}
