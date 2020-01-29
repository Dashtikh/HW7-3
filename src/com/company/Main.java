package com.company;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        //insert
        try {
            CarpetServ.getInstance().save(new CarpetEnti().setid(1).setName("4000sahne").setprice(1000));
            System.out.println("saved successfully");
        } catch (Exception e) {
            System.out.println("Fail to save!" + e.getMessage());
        }


    }
}
