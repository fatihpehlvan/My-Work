package com.company;

import java.sql.*;
import java.util.ArrayList;

public class Operations {
    private Connection conn;
    private ResultSet resultSet;
    private Statement stmt;

    private void setSelect() throws SQLException {
        String selectQuery = "SELECT * FROM example.customers;";
        resultSet = stmt.executeQuery(selectQuery);
        print();
    }

    public Operations(String url, String user, String password) throws SQLException {
        conn = DriverManager.getConnection(url, user, password);
        stmt = conn.createStatement();
    }

    public ArrayList<String> getAsArrayList() throws SQLException {
        ArrayList<String> r = new ArrayList<>();
        String selectQuery = "SELECT * FROM example.customers;";
        ResultSet tmp = stmt.executeQuery(selectQuery);
        if (tmp != null){
            while (tmp.next()) {
                r.add(tmp.getString("username"));
            }
        }
        return r;
    }

    private void print() throws SQLException {
        ResultSet tmp = resultSet;
        if (tmp != null){
            while (tmp.next()) {
                System.out.println(tmp.getString("username"));
            }
        }
    }

    public void select () throws SQLException {
        String selectQuery = "SELECT * FROM example.customers;";
        System.out.println("\nSELECT\n***************");
        resultSet = stmt.executeQuery(selectQuery);
        print();
    }

    public void insert (Customer name) throws SQLException {
        System.out.println("\nINSERT\n***************");
        String insertQuery = "INSERT INTO customers VALUES ('" + name.getUsername() + "');";
        stmt.executeUpdate(insertQuery);
        setSelect();
    }

    public void update (Customer oldName, String newName) throws SQLException {
        System.out.println("\nUPDATE\n***************");
        String updateQuery = "UPDATE example.customers SET username = '"+ newName + "' WHERE (username = '"+ oldName.getUsername() + "');";
        oldName.setUsername(newName);
        stmt.executeUpdate(updateQuery);
        setSelect();
    }

    public void delete (Customer name) throws SQLException {
        System.out.println("\nDELETE\n***************");
        String deleteQuery;
        if (name==null)
            deleteQuery = "DELETE FROM example.customers";
        else
            deleteQuery = "DELETE FROM example.customers WHERE username = '" + name.getUsername() +"'";
        stmt.executeUpdate(deleteQuery);
        setSelect();
    }

    public void close() throws SQLException {
        stmt.close();
        conn.close();
    }
}
