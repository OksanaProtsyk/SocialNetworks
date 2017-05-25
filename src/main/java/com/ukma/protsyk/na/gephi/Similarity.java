package com.ukma.protsyk.na.gephi;

import com.ukma.protsyk.na.tools.ProfileFeature;
import org.gephi.graph.api.Graph;
import org.gephi.graph.api.Node;
import org.gephi.graph.api.NodeIterable;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by okpr0814 on 5/24/2017.
 */
public class Similarity {
    Node source;
    Node target;
    Graph graph;
    Map<Node, List<ProfileFeature>> nodeProfiles;

    public Similarity(Node source, Node target, Graph graph, Map<Node, List<ProfileFeature>> nodeProfiles) {
        this.source = source;
        this.target = target;
        this.graph = graph;
        this.nodeProfiles = nodeProfiles;
    }

    public double getVTS() {
        NodeIterable iNei = graph.getNeighbors(source);
        NodeIterable jNei = graph.getNeighbors(target);


        int countInter = 0;
        for (Node i : iNei) {
            for (Node j : iNei) {
                if (i.getId().equals(j.getId())) {
                    countInter++;
                }
            }
        }
        return (double) countInter / ((double) graph.getNeighbors(source).toCollection().size() + graph.getNeighbors(target).toCollection().size() - countInter);
    }

    public double getVPS() {
        //cosine similarity
        double ab = 0;
        double a_s = 0;
        double b_s = 0;
        List<ProfileFeature> sourceFeature = nodeProfiles.get(source);
        List<ProfileFeature> targetFeature = nodeProfiles.get(target);

        for (int i = 0; i < sourceFeature.size(); i++) {
            ab += (double) sourceFeature.get(i).getData() * (double) targetFeature.get(i).getData();
            a_s += (double) sourceFeature.get(i).getData() * (double) sourceFeature.get(i).getData();
            b_s += (double) targetFeature.get(i).getData() * (double) targetFeature.get(i).getData();
        }
        System.out.println("*" + ab);
        if ((a_s != 0) && (b_s != 0)) {
            return ab / (Math.sqrt(a_s) * Math.sqrt(b_s));
        } else {
            return 0;
        }


    }

    @Override
    public String toString() {
        return "Similarity{" +
                "target=" + target +
                ", source=" + source +
                "VPS=" + getVPS() +
                ", VTS=" + getVTS() +
                '}';
    }
}
