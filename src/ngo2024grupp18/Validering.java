/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ngo2024grupp18;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author alex
 */
public class Validering {

// En klassmetod för att kontrollera att användaren har skrivit in något i ett textfält    
    public static boolean textFaltHarVarde(JTextField rutaAttKolla) {
        boolean resultat = true;

        if (rutaAttKolla.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vänligen ange en giltig e-postadress eller lösenord");
            resultat = false;
            rutaAttKolla.requestFocus();
        }
        return resultat;
    }

}
