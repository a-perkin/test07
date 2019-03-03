package com.alex.entity;


import javax.ws.rs.Produces;
import javax.xml.bind.annotation.XmlRootElement;

@Produces("application/json")
@XmlRootElement(name = "MaterialsToFacing")
public class MaterialsToFacing {

    private int id;
    private int id_facing;
    private int id_materials;
    private double square;

    public MaterialsToFacing(int id, int id_facing, int id_materials, double square){
        this.id = id;
        this.id_facing = id_facing;
        this.id_materials = id_materials;
        this.square = square;
    }

    public MaterialsToFacing(int id_materials, double square){
        this.id_materials = id_materials;
        this.square = square;
    }

    public MaterialsToFacing(){}

    @Override
    public String toString() {
        return "MaterialsToFacing{" +
                "id=" + id +
                ", id_facing=" + id_facing +
                ", id_materials=" + id_materials +
                ", square=" + square +
                '}';
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getId_facing() {
        return id_facing;
    }
    public void setId_facing(int id_facing) {
        this.id_facing = id_facing;
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
