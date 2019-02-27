package com.alex.entity;

import javax.ws.rs.Produces;

@Produces("application/json")
public class Mantel {

    private int id;
    private String name;

    public Mantel(int id, String name) {

        this.id = id;
        this.name = name;
    }

    public Mantel (){}

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


}