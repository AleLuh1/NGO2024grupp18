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
public class Person {

    private InfDB idb;
    private String inloggadAnvandare;

    public Person(InfDB idb, String inloggadAnvandare) {
        this.idb = idb;
        this.inloggadAnvandare = inloggadAnvandare;
    }

    public String getRoll(String inloggadAnvandare) {
        if (isAdmin(inloggadAnvandare) == true) {
            return "admin";
        } else if (isProjektledare(inloggadAnvandare) == true) {
            return "projektledare";
        } else {
            return "handläggare";
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
}
