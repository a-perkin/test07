package com.alex.services;

import com.alex.entity.Fireplace;
import com.alex.entity.FireplaceModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.alex.services.ConnectionDB.getDBConnection;
import static com.alex.services.FacingService.getFacingById;
import static com.alex.services.HearthstoneService.getHearthstoneById;
import static com.alex.services.MantelService.getMantelById;

public class FireplaceService {

    public static void main(String[] args) throws SQLException {
        System.out.println(getFireplace(1).toString());

    }

    public static Fireplace insertInFireplaceObj (Fireplace fireplace) throws SQLException {

        Connection dbConnection = null;
        PreparedStatement statement = null;

        dbConnection = getDBConnection();
        statement = dbConnection.prepareStatement("INSERT INTO  fireplace (id, name, price, id_facing, id_hearthstone, id_mantel) VALUES (DEFAULT,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, fireplace.getName());
        statement.setDouble(2, fireplace.getPrice());
        statement.setInt(3, fireplace.getId_facing());
        statement.setInt(4, fireplace.getId_hearthstone());
        statement.setInt(5, fireplace.getId_mantel());

        statement.executeUpdate();

        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                fireplace.setId(generatedKeys.getInt(1));
            }
            else {
                throw new SQLException("Insert to fireplace failed...");
            }
        }
        System.out.println("Insert in fireplace successful!!!");
        statement.close();

        if (statement != null) {
            statement.close();
        }
        if (dbConnection != null) {
            dbConnection.close();
        }

        return fireplace;
    }

    //Получить список всех облицовок
    public static List<Fireplace> getAllFireplaces() throws SQLException {

        Connection dbConnection = null;
        Statement statement = null;
        Integer id;
        String name;
        Double price;
        List<Fireplace> aoFireplace = new ArrayList<>();

        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT id, name, price FROM fireplace");

            while (rs.next()){
                id = rs.getInt("id");
                name = rs.getString("name");
                price = rs.getDouble("price");
                aoFireplace.add(new Fireplace(id, name, price));
            }
            System.out.println("gets list aoFireplace ...");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }

        return aoFireplace;
    }

    //Получить полный объект одной облицовки
    public static FireplaceModel getFireplace(int id){

        Connection dbConnection = null;
        PreparedStatement statement = null;
        FireplaceModel oFireplaceModel = null;

        String name;
        double price;
        int id_facing;
        int id_hearthstone;
        int id_mantel;

        try{
            dbConnection = getDBConnection();
            statement = dbConnection.prepareStatement("SELECT id, name, price, id_facing, id_hearthstone, id_mantel FROM fireplace WHERE id = ?");
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                name = rs.getString("name");
                price = rs.getDouble("price");
                id_facing = rs.getInt("id_facing");
                id_hearthstone = rs.getInt("id_hearthstone");
                id_mantel = rs.getInt("id_mantel");

                oFireplaceModel = new FireplaceModel(id, name, price, getFacingById(id_facing), getHearthstoneById(id_hearthstone),getMantelById(id_mantel) );
            }

        } catch (SQLException e) {

        }


        return oFireplaceModel;
    }
}