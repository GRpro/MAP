package com.kpi.project.packing;

import com.kpi.project.model.Graph;
import com.kpi.project.model.Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CLB extends Item2D {

    Packer packer;
    private ArrayList<Operation> operations;

    public CLB(int id, String name, int width, int height) {
        super(id, name, width, height);
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

    public void load(Graph graph) {
        List<Node> nodes = graph.getNodes();
        ArrayList<Operation> ops = new ArrayList<Operation>();
        for (Node node : nodes) {
            ops.add(new Operation(
                    node.getId(),
                    "Operation with id" + node.getId(),
                    node.getWidth(),
                    node.getHeight()));
        }
        activate(ops);
    }

    public int[][] toMatrix() {
        return packer.makeMatrix();
    }

}
