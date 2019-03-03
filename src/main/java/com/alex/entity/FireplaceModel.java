package com.alex.entity;

public class FireplaceModel {

    private int id;
    private String name;
    private double price;
    private Facing facing = new Facing();
    private Hearthstone hearthstone = new Hearthstone();
    private Mantel mantel = new Mantel();

    public FireplaceModel(int id, String name, double price, Facing facing, Hearthstone hearthstone, Mantel mantel) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.facing = facing;
        this.hearthstone = hearthstone;
        this.mantel = mantel;
    }

    @Override
    public String toString() {
        return "FireplaceModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", facing=" + facing +
                ", hearthstone=" + hearthstone +
                ", mantel=" + mantel +
                '}';
    }

    public FireplaceModel(){}

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Facing getFacing() {
        return facing;
    }

    public void setFacing(Facing facing) {
        this.facing = facing;
    }

    public Hearthstone getHearthstone() {
        return hearthstone;
    }

    public void setHearthstone(Hearthstone hearthstone) {
        this.hearthstone = hearthstone;
    }

    public Mantel getMantel() {
        return mantel;
    }

    public void setMantel(Mantel mantel) {
        this.mantel = mantel;
    }
}