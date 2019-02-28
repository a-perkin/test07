package com.alex.services;

import com.alex.entity.Mantel;
import com.alex.entity.MaterialsToMantel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.alex.services.ConnectionDB.getDBConnection;

public class MantelService {

    //Засетить в mantel со связями с материалами (один ко многому)
    public static Object insertInMantelObj(Mantel mantel) throws SQLException {
        Connection dbConnection = null;
        PreparedStatement statement = null;
        List<MaterialsToMantel> materials;
        materials = mantel.getMaterials();

        try {
            dbConnection = getDBConnection();
            statement = dbConnection.prepareStatement("INSERT INTO  mantel (id, name) VALUES (DEFAULT, ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, mantel.getName());
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    mantel.setId(generatedKeys.getInt(1));
                }
                else {
                    throw new SQLException("Creating mantel failed, no ID obtained.");
                }
            }

            for(int i = 0; i < materials.size(); i++) {
                MaterialsToMantel element = materials.get(i);

                statement = dbConnection.prepareStatement("INSERT INTO  materials_to_mantel (id, id_mantel, id_materials, square) VALUES (DEFAULT, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
                statement.setInt(1, mantel.getId());
                statement.setInt(2, element.getId_materials());
                statement.setDouble(3, element.getSquare());

                element.setId_mantel(mantel.getId());

                statement.executeUpdate();

                try (ResultSet generatedKeys1 = statement.getGeneratedKeys()) {
                    if (generatedKeys1.next()) {
                        element.setId(generatedKeys1.getInt(1));
                    }
                    else {
                        throw new SQLException("Creating mantel failed, no ID obtained.");
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
        System.out.println("Set MANTEL with dependencies material" + mantel.toString());

        return mantel;
    }

    //Получить лист объектов mantel со связями с материалами
    public static List<Mantel> getMantels() throws SQLException {
        Connection dbConnection = null;
        Statement statement = null;
        PreparedStatement statement1 = null;
        int id;
        int id1;
        int id_materials;
        int id_mantel;
        double square;
        String name;
        long count = 0;
        long count1 = 0;
        List<Mantel> aoMantels = new ArrayList<>();


        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT id, name FROM mantel");

            while (rs.next()) {
                id = rs.getInt("id");
                name = rs.getString("name");
                statement1 = dbConnection.prepareStatement("SELECT id, id_mantel, id_materials, square  FROM materials_to_mantel WHERE id_mantel = ?");
                statement1.setInt(1, id);
                ResultSet rs1 = statement1.executeQuery();
                List<MaterialsToMantel> aoMaterialsToMantel = new ArrayList<>();

                while (rs1.next()) {
                    id1 = rs1.getInt("id");
                    id_mantel = rs1.getInt("id_mantel");
                    id_materials = rs1.getInt("id_materials");
                    square = rs1.getDouble("square");
                    aoMaterialsToMantel.add(new MaterialsToMantel(id1, id_mantel, id_materials, square));

                    count1++;
                }

                aoMantels.add(new Mantel(id, name, aoMaterialsToMantel));
                count++;
            }

            System.out.println("getMantels ... count: " +  aoMantels.size());

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

        return aoMantels;
    }
}