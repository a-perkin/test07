package com.alex.entity;

import javax.ws.rs.Produces;

@Produces("application/json")
public class MaterialsToMantel {

    private int id;
    private int id_mantel;
    private int id_materials;
    private double square;

    public MaterialsToMantel(int id, int id_mantel, int id_materials, double square) {
        this.id = id;
        this.id_mantel = id_mantel;
        this.id_materials = id_materials;
        this.square = square;
    }

    public MaterialsToMantel (){}

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getId_mantel() {
        return id_mantel;
    }
    public void setId_mantel(int id_mantel) {
        this.id_mantel = id_mantel;
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