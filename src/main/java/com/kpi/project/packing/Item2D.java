package com.kpi.project.packing;

public abstract class Item2D {

    private int width;
    private int height;
    private String name;

    public Item2D(String name, int width, int height) {
        this.name = name;
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getName() { return name;}

    public String toString() {
        return "Item " + getName()+" with width = " + getWidth() + " and height = " + getHeight() + ".";
    }
}
