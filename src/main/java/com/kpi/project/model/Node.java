package com.kpi.project.model;

/**
 *
 */
public class Node {

    private int id;

    /* rectangle properties */
    private int width;
    private int height;
    /* processing time */
    private int time;

    public Node(int id, int width, int height) {
        this.id = id;
        this.width = width;
        this.height = height;
    }

    public Node() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Node{" +
            "id=" + id +
            ", width=" + width +
            ", height=" + height +
            ", time=" + time +
            '}';
    }
}
