package com.alex.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InitDB {
    public static void main(String[] args) throws SQLException {
        //CreateDB("testdb");
        //DropDB("testbd");
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

    //Создать базу данных по названию
    public static void CreateDB(String sNameDB) throws SQLException {
            String sqlCreateDB = "CREATE DATABASE " + sNameDB;
            Connection dbConn = null;

            dbConn = getConnection();
            Statement statement = null;
            try {
                statement = dbConn.createStatement();
                statement.executeUpdate(sqlCreateDB);
                statement.close();
                System.out.println("Database \"" + sNameDB + "\" created...");

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } finally {
                if (statement != null) {
                    statement.close();
                }
                if (dbConn != null) {
                    dbConn.close();
                }
            }
        }

    //Удалить базу данных по названию
    public static void DropDB(String sNameDB) throws SQLException {
            String sqlDropDB = "DROP DATABASE " + sNameDB;
            Connection dbConn = null;

            dbConn = getConnection();
            Statement statement = null;
            try {
                statement = dbConn.createStatement();
                statement.executeUpdate(sqlDropDB);
                statement.close();
                System.out.println("Database \"" + sNameDB + "\" deleted...");

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } finally {
            if (statement != null) {
                statement.close();
            }
            if (dbConn != null) {
                dbConn.close();
            }
        }

    }

}