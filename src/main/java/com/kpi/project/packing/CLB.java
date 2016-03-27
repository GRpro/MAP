package com.kpi.project.packing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CLB extends Item2D {

    Packer packer;
    private ArrayList<Operation> operations;

    public CLB(String name, int width, int height) {
        super(name, width, height);
        packer = new Packer(width, height);
        operations = new ArrayList<Operation>();
    }

    public void activate(Operation ... ops) {
        activate(Arrays.asList(ops));
    }

    /** Places all operations on CLB. */
    public void activate(List<Operation> ops) {
        for (Operation op : ops) {
            while (!packer.place(op)) {
                packer.delete(operations.get(0));
                operations.remove(0);
            }
            operations.add(op);
        }
    }

}
