package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Main {

    static final String url = "jdbc:mysql://localhost:3306/example?serverTimezone=UTC";
    static final String user = "root";
    static final String password = "*********";

    public static void main(String[] args) {

        try {
            Customer c1 = new Customer("Fatih");
            Customer c2 = new Customer("Bedra");
            Customer c3 = new Customer("Mefe");
            Operations operations = new Operations(url, user, password);
            operations.insert(c1);
            operations.update(c1, "bedra");
            operations.delete(null);
            operations.insert(c3);
            operations.select();

            ArrayList<String> names = operations.getAsArrayList();
            System.out.println(names);
            operations.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
