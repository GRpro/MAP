package com.kpi.project.packing;


public class Operation extends DecartItem2D {


    public Operation(int id, String name, int width, int height) {
        super(id, name, width, height, -1, -1);
    }

    // is not implemented!
    public boolean isWorking() {
        return false;
    }
}
