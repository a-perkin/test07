package com.alex.services;

import com.alex.entity.Hearthstone;
import com.alex.entity.MaterialsToHearthstone;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HearthstoneService extends ConnectionDB{

    //Засетить в hearthstone со связями с материалами (один ко многому)
    public static Object insertInHearthstoneObj(Hearthstone hearthstone) throws SQLException {
        Connection dbConnection = null;
        PreparedStatement statement = null;
        List<MaterialsToHearthstone> materials;
        materials = hearthstone.getMaterials();

        try {
            dbConnection = getDBConnection();
            statement = dbConnection.prepareStatement("INSERT INTO  hearthstone (id, name) VALUES (DEFAULT, ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, hearthstone.getName());
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    hearthstone.setId(generatedKeys.getInt(1));
                }
                else {
                    throw new SQLException("Creating hearthstone failed, no ID obtained.");
                }
            }

            for(int i = 0; i < materials.size(); i++) {
                MaterialsToHearthstone element = materials.get(i);

                statement = dbConnection.prepareStatement("INSERT INTO  materials_to_hearthstone (id, id_hearthstone, id_materials, square) VALUES (DEFAULT, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
                statement.setInt(1, hearthstone.getId());
                statement.setInt(2, element.getId_materials());
                statement.setDouble(3, element.getSquare());

                element.setId_hearthstone(hearthstone.getId());

                statement.executeUpdate();

                try (ResultSet generatedKeys1 = statement.getGeneratedKeys()) {
                    if (generatedKeys1.next()) {
                        element.setId(generatedKeys1.getInt(1));
                    }
                    else {
                        throw new SQLException("Creating hearthstone failed, no ID obtained.");
                    }
                }
            }
            statement.close();

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
        System.out.println("Hearthstone with dependencies material" + hearthstone.toString());

        return hearthstone;
    }

    public static List<Hearthstone> getHearthstones() throws SQLException {
        Connection dbConnection = null;
        Statement statement = null;
        PreparedStatement statement1 = null;
        int id;
        int id1;
        int id_materials;
        int id_hearthstone;
        double square;
        String name;
        long count = 0;
        long count1 = 0;
        List<Hearthstone> aoHearthstones = new ArrayList<>();


        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT id, name FROM hearthstone");

            while (rs.next()) {
                id = rs.getInt("id");
                name = rs.getString("name");
                statement1 = dbConnection.prepareStatement("SELECT id, id_hearthstone, id_materials, square  FROM materials_to_hearthstone WHERE id_hearthstone = ?");
                statement1.setInt(1, id);
                ResultSet rs1 = statement1.executeQuery();
                List<MaterialsToHearthstone> aoMaterialsToHearthstone = new ArrayList<>();

                while (rs1.next()) {
                    id1 = rs1.getInt("id");
                    id_hearthstone = rs1.getInt("id_hearthstone");
                    id_materials = rs1.getInt("id_materials");
                    square = rs1.getDouble("square");
                    aoMaterialsToHearthstone.add(new MaterialsToHearthstone(id1, id_hearthstone, id_materials, square));

                    count1++;
                }

                aoHearthstones.add(new Hearthstone(id, name, aoMaterialsToHearthstone));
                count++;
            }

            System.out.println("getHearthstones ...");

            statement.close();
            statement1.close();

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

        return aoHearthstones;
    }

}