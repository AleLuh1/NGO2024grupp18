/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ngo2024grupp18;

import oru.inf.InfDB;
import oru.inf.InfException;

///**
// *
// * @author alex
// */
public class Anstalld {

    private InfDB idb;
    private String inloggadAnvandare;

    public Anstalld(InfDB idb, String inloggadAnvandare) {
        this.idb = idb;
        this.inloggadAnvandare = inloggadAnvandare;
    }

    public String getAID() {
        return inloggadAnvandare;
    }

    public String getFornamn() {
        try {
            String sqlFraga = "SELECT fornamn FROM anstalld WHERE aid = " + inloggadAnvandare;
            System.out.println(sqlFraga);
            String fornamn = idb.fetchSingle(sqlFraga);
            return fornamn;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public String getEfternamn() {
        try {
            String sqlFraga = "SELECT efternamn FROM anstalld WHERE aid = " + inloggadAnvandare;
            System.out.println(sqlFraga);
            String efternamn = idb.fetchSingle(sqlFraga);
            return efternamn;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public String getAdress() {
        try {
            String sqlFraga = "SELECT adress FROM anstalld WHERE aid = " + inloggadAnvandare;
            System.out.println(sqlFraga);
            String adress = idb.fetchSingle(sqlFraga);
            return adress;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public String getEpost() {
        try {
            String sqlFraga = "SELECT epost FROM anstalld WHERE aid = " + inloggadAnvandare;
            System.out.println(sqlFraga);
            String epost = idb.fetchSingle(sqlFraga);
            return epost;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public String getTelefon() {
        try {
            String sqlFraga = "SELECT telefon FROM anstalld WHERE aid = " + inloggadAnvandare;
            System.out.println(sqlFraga);
            String telefon = idb.fetchSingle(sqlFraga);
            return telefon;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public String getAnstallningsDatum() {
        try {
            String sqlFraga = "SELECT anstallningsdatum FROM anstalld WHERE aid = " + inloggadAnvandare;
            System.out.println(sqlFraga);
            String anstallningsdatum = idb.fetchSingle(sqlFraga);
            return anstallningsdatum;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public String getAvdelning() {
        try {
            String sqlFraga = "SELECT avdelning FROM anstalld WHERE aid = " + inloggadAnvandare;
            System.out.println(sqlFraga);
            String avdelning = idb.fetchSingle(sqlFraga);
            return avdelning;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public String getRoll() {
        if (isAdmin() == true) {
            return "admin";
        } else if (isProjektledare() == true) {
            return "projektledare";
        } else {
            return "handläggare";
        }
    }

    public boolean isAdmin() {
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

    public boolean isProjektledare() {
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

    public boolean isHandlaggare() {
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
}
