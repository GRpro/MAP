package com.kpi.project.packing;


public class Operation extends DecartItem2D {

    // processing time
    private int time;

    public Operation(int id, String name, int width, int height, int time) {
        super(id, name, width, height, -1, -1);
        this.time = time;
    }

    // is not implemented!
    public boolean isWorking() {
        return false;
    }
}
