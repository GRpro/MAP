package com.kpi.project.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *  POJO representing graph object
 */
public class Graph {

    private List<Node> nodes;
    private List<Edge> edges;

    public Graph(List<Node> nodes, List<Edge> edges) {
        this.nodes = nodes;
        this.edges = edges;
    }

    public Graph() {
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    @Override
    public String toString() {
        return "Graph{" +
                "nodes=" + nodes +
                ", edges=" + edges +
                '}';
    }

    public Node getNodeById(int id){
        for (Node node: nodes)
            if (node.getId() == id)
                return node;
        return null;

    }

    public ArrayList<Integer> getChilds(Node node){

        ArrayList<Integer> result = new ArrayList<Integer>();
        for(Edge edge: edges)
            if(edge.getSource() == node.getId())
                result.add(edge.getTarget());
        return result;
    }

    public Boolean hasTarget(int id){

        for(Edge edge: edges)
            if(edge.getTarget() == id)
                return true;
        return false;
    }

    public int getMaxId(){

        int max = nodes.get(0).getId();
        for(Node node: nodes)
            if (max < node.getId())
                max = node.getId();
        return max;
    }
}
