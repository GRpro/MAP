package com.kpi.project.utils;

import com.kpi.project.model.Edge;
import com.kpi.project.model.Graph;
import com.kpi.project.model.Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by nikita on 08.04.16.
 */
public class MultilevelStructureBuilder {

    private LinkedList<ArrayList<Node>> structure;

    public MultilevelStructureBuilder(Graph graph) {

        structure = new LinkedList<ArrayList<Node>>();

        Boolean[] flags = new Boolean[graph.getMaxId()];
        Arrays.fill(flags, false);

        ArrayList<Node> rootLevel = getRootLevel(graph);
        ArrayList<Node> nextLevel;
        while (rootLevel.size() != 0) {
            structure.add(rootLevel);
            nextLevel = new ArrayList<Node>();
            for(Node node: rootLevel){
                for (Integer integer : graph.getChilds(node)) {
                    if (!flags[integer - 1]) {
                        nextLevel.add(graph.getNodeById(integer));
                        flags[integer - 1] = true;
                    }
                }
            }
            rootLevel = new ArrayList<Node>(nextLevel);
            Arrays.fill(flags, false);
        }
    }

    private ArrayList<Node> getRootLevel(Graph graph){
        ArrayList<Node> rootLevel = new ArrayList<Node>();

        for(Node node: graph.getNodes())
            if(!graph.hasTarget(node.getId()))
                rootLevel.add(graph.getNodeById(node.getId()));
        return rootLevel;
    }

    @Override
    public String toString(){
        String str = "";
        int i = 0;
        for (ArrayList<Node> nodes: structure) {
            str += "Level " + i++ + " ";
            for (Node node : nodes)
                str += node.toString() + " ";
            str += "\n";
        }

        return str;
    }
}
