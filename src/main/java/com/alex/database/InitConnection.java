package com.alex.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InitConnection {
    public static void main(String[] args) throws SQLException {
        CreateBD();
    }

    public static Connection getConnection() {
        Connection dbConn = null;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            String url = "jdbc:postgresql://localhost:5432/";
            String login = "postgres";
            String password = "Sana2010";
            dbConn = DriverManager.getConnection(url, login, password);
            return dbConn;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConn;
    }

    public static void CreateBD(){
        String sqlCreateDB = "CREATE DATABASE KaminDatabase";
        Connection dbConn = null;

        dbConn = getConnection();
        Statement statement = null;
        try {
            statement = dbConn.createStatement();
            statement.executeUpdate(sqlCreateDB);
            statement.close();
            System.out.println("Database \"KaminDatabase\" created...");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}