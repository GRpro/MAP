package com.kpi.project.packing;

public class DecartItem2D extends Item2D {

    private int x;
    private int y;

    public DecartItem2D(int id, String name, int width, int height, int x, int y) {
        super(id, name, width, height);
        this.x = x;
        this.y = y;
    }

    public DecartItem2D(Item2D item2D, int x, int y) {
        super(item2D.getId(),item2D.getName(), item2D.getWidth(), item2D.getHeight());
        this.x = x;
        this.y = y;
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
