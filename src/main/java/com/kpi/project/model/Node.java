package com.kpi.project.model;

/**
 *
 */
public class Node {

    private String title;
    private int id;

    /* rectangle properties */
    private int width;
    private int height;

    public Node(String title, int id, int width, int height) {
        this.title = title;
        this.id = id;
        this.width = width;
        this.height = height;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
}
