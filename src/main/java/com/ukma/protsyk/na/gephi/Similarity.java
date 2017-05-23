package com.ukma.protsyk.na.gephi;

import org.gephi.graph.api.Graph;
import org.gephi.graph.api.Node;
import org.gephi.graph.api.NodeIterable;

import java.util.Collection;
import java.util.List;

/**
 * Created by okpr0814 on 5/24/2017.
 */
public class Similarity {
    Node  source;
    Node target;
    Graph graph;

    public Similarity(Graph graph,Node source, Node target) {
        this.graph =graph;
        this.source = source;
        this.target = target;
    }

    public double getVTS(){
        NodeIterable iNei = graph.getNeighbors(source);
        NodeIterable jNei = graph.getNeighbors(target);


        int countInter = 0;
        for (Node i:iNei){
            for (Node j:iNei){
                if(i.getId().equals(j.getId())){
                    countInter++;
                }
            }
        }
        return (double)countInter/((double)graph.getNeighbors(source).toCollection().size()+graph.getNeighbors(target).toCollection().size()-countInter);
    }

    public double getVPS(){
        return 0;
    }
}
