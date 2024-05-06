/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ngo2024grupp18;

import oru.inf.InfDB;
import oru.inf.InfException;

import java.util.ArrayList;

/**
 *
 * @author alex
 */
public class Personuppgifter {
    private ArrayList<String> personLista;
    private InfDB idb;
            
    public Personuppgifter(InfDB idb) {
       personLista = new ArrayList<String>();
       this.idb = idb;
    }
    
    public void getFornamn() {
        
        try {
            String sqlFraga = "SELECT fornamn FROM anstalld";
            System.out.println(sqlFraga);
            String dbFornamn = idb.fetchSingle(sqlFraga);
            personLista.add(dbFornamn);
            System.out.println("Arraylist: " + personLista);
        }
        catch (Exception ex){
           System.out.println(ex.getMessage());
        }
    }
}
