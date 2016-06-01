package com.kpi.project.packing;

public abstract class Item2D {

    private int width;
    private int height;
    private String name;
    private int id;

    public Item2D(int id, String name, int width, int height) {
        this.id = id;
        this.name = name;
        this.width = width;
        this.height = height;
    }

    protected void setWidth(int width) {this.width = width;}

    public int getId() {
        return id;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getName() { return name;}

    public String toString() {
        return "Item("+getId()+") " + getName()+
                " with width = " + getWidth() +
                " and height = " + getHeight() + ".";
    }
}
