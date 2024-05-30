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
public class Anstalld {

    private InfDB idb;
    private String aid;

    public Anstalld(InfDB idb, String aid) {
        this.idb = idb;
        this.aid = aid;
    }

    public boolean isAdmin() {
        String aidSomKollas = null;
        try {
            String sqlFraga = "SELECT aid FROM admin WHERE aid = " + aid;
            System.out.println(sqlFraga);
            aidSomKollas = idb.fetchSingle(sqlFraga);
            System.out.println("Aid som kollas: " + aidSomKollas);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return aidSomKollas != null;
    }

    public boolean isProjektledare() {
        String aidSomKollas = null;
        try {
            String sqlFraga = "SELECT aid FROM anstalld JOIN projekt ON projektchef = anstalld.aid WHERE anstalld.aid = " + aid;
            System.out.println(sqlFraga);
            aidSomKollas = idb.fetchSingle(sqlFraga);
            System.out.println("Aid som kollas: " + aidSomKollas);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (aidSomKollas != null) {
            return true;
        }
        return false;
    }

    public boolean isHandlaggare() {
        String aidSomKollas = null;
        try {
            String sqlFraga = "SELECT aid FROM handlaggare WHERE aid = " + aid;
            System.out.println(sqlFraga);
            aidSomKollas = idb.fetchSingle(sqlFraga);
            System.out.println("Aid som kollas: " + aidSomKollas);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (aidSomKollas != null) {
            return true;
        }
        return false;
    }

    public String getRoll() {
        if (isAdmin()) {
            return "Admin";
        } else if (isProjektledare() == true) {
            return "Projektledare";
        } else {
            return "Handläggare";
        }
    }

}
