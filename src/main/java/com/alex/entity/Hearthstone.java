package com.alex.entity;

import javax.ws.rs.Produces;
import java.util.List;

@Produces("application/json")
public class Hearthstone {

    private int id;
    private String name;
    private List <MaterialsToHearthstone> materials;

    public Hearthstone(int id, String name, List<MaterialsToHearthstone> materials) {
        this.id = id;
        this.name = name;
        this.materials = materials;
    }

    public Hearthstone (){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MaterialsToHearthstone> getMaterials() {
        return materials;
    }

    public void setMaterials(List<MaterialsToHearthstone> materials) {
        this.materials = materials;
    }
}