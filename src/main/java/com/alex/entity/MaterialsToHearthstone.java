package com.alex.entity;

import javax.ws.rs.Produces;

@Produces("application/json")
public class MaterialsToHearthstone {

    private int id;
    private int id_hearthstone;
    private int id_materials;
    private double square;

    public MaterialsToHearthstone(int id, int id_hearthstone, int id_materials, double square) {

        this.id = id;
        this.id_hearthstone = id_hearthstone;
        this.id_materials = id_materials;
        this.square = square;
    }


    public MaterialsToHearthstone() {}

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getId_hearthstone() {
        return id_hearthstone;
    }
    public void setId_hearthstone(int id_hearthstone) {
        this.id_hearthstone = id_hearthstone;
    }
    public int getId_materials() {
        return id_materials;
    }
    public void setId_materials(int id_materials) {
        this.id_materials = id_materials;
    }
    public double getSquare() {
        return square;
    }
    public void setSquare(double square) {
        this.square = square;
    }

}