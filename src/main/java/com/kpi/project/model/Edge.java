package com.kpi.project.model;

/**
 *
 */
public class Edge {

    private int source;
    private int target;

    public Edge(int source, int target) {
        this.source = source;
        this.target = target;
    }

    public Edge() {
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "source=" + source +
                ", target=" + target +
                '}';
    }
}
