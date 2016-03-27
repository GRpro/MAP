package com.kpi.project.packing;


public class Operation extends Item2D {

    private int x;
    private int y;

    public Operation(String name, int width, int height) {
        super(name, width, height);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
