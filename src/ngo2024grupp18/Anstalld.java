/*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ngo2024grupp18;

import oru.inf.InfDB;
import oru.inf.InfException;
import java.util.HashMap;

///**
// *
// * @author alex
// */
public class Anstalld {

    private InfDB idb;
    private String inloggadAnvandare;
    private String fornamn;
    private String efternamn;
    private String adress;
    private String epost;
    private String telefon;
    private String anstallningsdatum;
    private String avdelning;

    public Anstalld(InfDB idb) {
        this.idb = idb;
    }

    public Anstalld(String fornamn, String efternamn, String adress, String epost, String telefon, String anstallningsdatum, String avdelning) {
        this.idb = idb;
        this.inloggadAnvandare = inloggadAnvandare;
        this.fornamn = fornamn;
        this.efternamn = efternamn;
        this.adress = adress;
        this.epost = epost;
        this.telefon = telefon;
        this.anstallningsdatum = anstallningsdatum;
        this.avdelning = avdelning;

    }
    public String getAID(){
        return inloggadAnvandare;
    }

    public String getRoll() {
        if (isAdmin(inloggadAnvandare) == true) {
            return "Admin";
        } else if (isProjektledare(inloggadAnvandare) == true) {
            return "Projektledare";
        } else {
            return "Handläggare";
        }
    }

    public boolean isAdmin(String inloggadAnvandare) {
        String aid = null;

        try {
            String sqlFraga = "SELECT aid FROM admin WHERE aid = " + inloggadAnvandare;
            System.out.println(sqlFraga);
            aid = idb.fetchSingle(sqlFraga);
            System.out.println(aid);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        if (aid != null) {
            return true;
        }

        return false;
    }

    public boolean isProjektledare(String inloggadAnvandare) {
        String aid = null;

        try {
            String sqlFraga = "SELECT aid FROM ans_proj WHERE aid = " + inloggadAnvandare;
            System.out.println(sqlFraga);
            aid = idb.fetchSingle(sqlFraga);
            System.out.println(aid);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        if (aid != null) {
            return true;
        }

        return false;
    }

    public boolean isHandlaggare(String inloggadAnvandare) {
        String aid = null;

        try {
            String sqlFraga = "SELECT aid FROM handlaggare WHERE aid = " + inloggadAnvandare;
            System.out.println(sqlFraga);
            aid = idb.fetchSingle(sqlFraga);
            System.out.println(aid);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        if (aid != null) {
            return true;
        }

        return false;
    }

    public Anstalld getInloggad(String aid) {
        try {
            String sqlFraga = "SELECT * FROM Anstalld WHERE aid = '" + aid + "'";
            System.out.println(sqlFraga);
            var anstalld = idb.fetchRow(sqlFraga);
            Anstalld inloggadAnstalld = new Anstalld(anstalld.get("fornamn"), anstalld.get("efternamn"), anstalld.get("adress"), anstalld.get("epost"), anstalld.get("telefon"), anstalld.get("anstallningsdatum"), anstalld.get("avdelning"));
            return inloggadAnstalld;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public String getFornamn() {
        return fornamn;
    }

    public String getEfternamn() {
        return efternamn;
    }

    public String getAdress() {
        return adress;
    }

    public String getEpost() {
        return epost;
    }

    public String getTelefon() {
        return telefon;
    }

    public String getAnstallningsdatum() {
        return anstallningsdatum;
    }

    public String getAvdelning() {
        return avdelning;
    }

    /*public int setAid() {
            this.aid = aid; 
        }

        public String setFornamn() {
            this.fornamn = fornamn;
        }

        public String setEfternamn() {
            this.efternamn = efternamn;
        }

        public String setAdress() {
            this.adress = adress;
        }

        public String setEpost() {
            this.epost = epost;
        }

        public String setTelefon() {
            this.telefon = telefon;
        }

        public String setAnstallningsdatum() {
            this.anstallningsdatum = anstallningsdatum;
        }

        public String setLosenord() {
            this.losenord = losenord;
        }

        public String setAvdelning() {
            this.avdelning = avdelning;
        }
     */
}
