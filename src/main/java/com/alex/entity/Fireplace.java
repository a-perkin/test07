package com.alex.entity;

public class Fireplace {

    private Integer id;
    private String name;
    private Double price;
    private Integer id_facing;
    private Integer id_hearthstone;
    private Integer id_mantel;

    public Fireplace(Integer id, String name, Double price, Integer id_facing, Integer id_hearthstone, Integer id_mantel) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.id_facing = id_facing;
        this.id_hearthstone = id_hearthstone;
        this.id_mantel = id_mantel;
    }

    public Fireplace(Integer id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Fireplace{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", id_facing=" + id_facing +
                ", id_hearthstone=" + id_hearthstone +
                ", id_mantel=" + id_mantel +
                '}';
    }

    public Fireplace () {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getId_facing() {
        return id_facing;
    }

    public void setId_facing(Integer id_facing) {
        this.id_facing = id_facing;
    }

    public Integer getId_hearthstone() {
        return id_hearthstone;
    }

    public void setId_hearthstone(Integer id_hearthstone) {
        this.id_hearthstone = id_hearthstone;
    }

    public Integer getId_mantel() {
        return id_mantel;
    }

    public void setId_mantel(Integer id_mantel) {
        this.id_mantel = id_mantel;
    }
}