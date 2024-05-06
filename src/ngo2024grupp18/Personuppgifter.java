/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ngo2024grupp18;

import oru.inf.InfDB;

/**
 *
 * @author alex
 */
public class Personuppgifter {
    private String fornamn;
    private InfDB idb;
            
    public Personuppgifter(InfDB idb) {
       this.idb = idb;
    }
    
    public void getFornamn() {
        
        try {
            String sqlFraga = "SELECT fornamn FROM anstalld";
            System.out.println(sqlFraga);
            
            String dbFornamn = idb.fetchSingle(sqlFraga);
            fornamn = dbFornamn;
            
            System.out.println("Förnamn: " + fornamn);
        }
        catch (Exception ex){
           System.out.println(ex.getMessage());
        }
    }
}
