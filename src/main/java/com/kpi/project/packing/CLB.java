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
        operations = new ArrayList<>();
    }

    public boolean activate(Operation ... ops) {
        return activate(Arrays.asList(ops));
    }

    /**
     * Places all operations on CLB.
     * @return true, if all operations have been activated.
     */
    public boolean activate(List<Operation> ops) {
        boolean result = true;
        for (Operation op : ops) {
            int i = 0;
            boolean flag = packer.place(op);
            while (!flag && i < operations.size()) {
                if (operations.get(i).isWorking()) {
                    i++;
                } else {
                    packer.delete(operations.get(i));
                    operations.remove(i);
                }
                flag = packer.place(op);
            }
            if (flag) {
                operations.add(op);
            } else {
                result = false;
            }
        }
        return result;
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

    public void erase(){
        this.operations.clear();
        packer = new Packer(getWidth(), getHeight());
    }
}
