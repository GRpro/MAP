package com.kpi.project.packing;


import java.util.ArrayList;

public class Level {

    private int height;
    private int width;
    private int y;
    private int residualWidth;
    private ArrayList<Item2D> items;

    public Level(int width, int height, int y) {
        this.height = height;
        this.width = width;
        this.y = y;
        this.residualWidth = width;
        items = new ArrayList<Item2D>();
    }

    public boolean canAccommodate(int width, int height) {
        return this.height >= height && this.residualWidth >= width;
    }

    public boolean canAccommodate(Item2D item) {
        return canAccommodate(item.getWidth(), item.getHeight());
    }

    /** @return true, if item has been placed. */
    public boolean accommodate(Item2D item) {
        boolean result = false;
        if (canAccommodate(item)) {
            items.add(item);
            residualWidth -= item.getWidth();
            result = true;
        }
        return result;
    }

    /**
     * After removing item, shifts all items to left.
     * @return true, if level contained this item.
     */
    public boolean removeAndSeal(Item2D item) {
        boolean result = items.remove(item);
        if (result) {
            residualWidth += item.getWidth();
        }
        return result;
    }

    void setHeight(int height) {
        this.height = height;
    }

    /** @return false, if level contains at least one item. */
    public boolean isFree() {
        return getResidualWidth() == getWidth();
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() { return width;}

    public int getResidualWidth() {
        return residualWidth;
    }

    public int getY() {return y;}

    public String toString() {
        StringBuilder result = new StringBuilder("Level with width = " + getWidth() + " and height = " +
                getHeight() + " at y = " + getY() + ". Contains:\n");
        for (Item2D item : items) {
            result.append("\t"+item.toString() + "\n");
        }
        result.append("Residual width = " + getResidualWidth());
        return result.toString();
    }
}
