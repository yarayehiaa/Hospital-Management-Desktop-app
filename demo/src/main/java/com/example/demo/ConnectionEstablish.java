package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionEstablish {
    public static Connection con;
    public static void connect() throws SQLException, ClassNotFoundException {

        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbctest","root","Nashwa123412");
    }
}