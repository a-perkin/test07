package com.alex.entity;

import javax.ws.rs.Produces;
import java.util.List;

@Produces("application/json")
public class Mantel {

    private int id;
    private String name;
    private List<MaterialsToMantel> materials;

    public Mantel(int id, String name, List<MaterialsToMantel> materials) {
        this.id = id;
        this.name = name;
        this.materials = materials;
    }

    public Mantel (){}

    @Override
    public String toString() {
        return "Mantel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", materials=" + materials +
                '}';
    }

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
    public List<MaterialsToMantel> getMaterials() { return materials; }
    public void setMaterials(List<MaterialsToMantel> materials) { this.materials = materials; }

}