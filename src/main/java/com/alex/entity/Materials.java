package com.alex.entity;

public class Materials {
    private Integer id;
    private String name;
    private Double price;
    private Integer thickness;

    public Materials(String name, Double price, Integer thickness) {
        this.name = name;
        this.price = price;
        this.thickness = thickness;
    }

    public Materials(Integer id, String name, Double price, Integer thickness) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.thickness = thickness;
    }
    public Materials(){}

    @Override
    public String toString() {
        return "{" +
                "\"id\" : " + id +
                ", \"name\" : \"" + name +
                "\", \"price\" : " + price +
                ", \"thickness\" : " +thickness +
                '}';
    }

    public Integer getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public Double getPrice() {
        return price;
    }
    public Integer getThickness() {
        return thickness;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public void setThickness(Integer thickness) {
        this.thickness = thickness;
    }
}