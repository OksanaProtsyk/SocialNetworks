package com.ukma.protsyk.na.gephi;

/**
 * Created by okpr0814 on 5/25/2017.
 */

import com.ukma.protsyk.na.tools.ProfileFeature;
import org.gephi.graph.api.*;

import java.util.List;
import java.util.Map;

/**
 * Created by okpr0814 on 5/24/2017.
 */
public class EdgeSimilarity {
    Node node1;
    Node node2;
    Edge source;
    Edge target;
    Graph graph;
    Map<Node, List<ProfileFeature>> nodeProfiles;


    public EdgeSimilarity(Graph graph, Edge source, Edge target, Map<Node, List<ProfileFeature>> nodeProfiles) {
        this.graph = graph;
        this.source = source;
        this.target = target;
        this.nodeProfiles = nodeProfiles;
        comupeteNodes();
    }

    public double getLTS() {
        if (source.equals(target)) {
            return 0;
        }

        if(node1 == null){
            return 0;
        }


        NodeIterable iNei = graph.getNeighbors(node1);
        NodeIterable jNei = graph.getNeighbors(node2);


        int countInter = 0;
        for (Node i : iNei) {
            for (Node j : iNei) {
                if (i.getId().equals(j.getId())) {
                    countInter++;
                }
            }
        }
        return (double) countInter / ((double) graph.getNeighbors(node1).toCollection().size() + graph.getNeighbors(node2).toCollection().size() - countInter);

    }

    public double getLPS() {

        double ab = 0;
        double a_s = 0;
        double b_s = 0;
        List<ProfileFeature> sourceFeature = nodeProfiles.get(node1);
        List<ProfileFeature> targetFeature = nodeProfiles.get(node2);

        for (int i = 0; i < sourceFeature.size(); i++) {
            ab += (double) sourceFeature.get(i).getData() * (double) targetFeature.get(i).getData();
            a_s += (double) sourceFeature.get(i).getData() * (double) sourceFeature.get(i).getData();
            b_s += (double) targetFeature.get(i).getData() * (double) targetFeature.get(i).getData();
        }
        if ((a_s != 0) && (b_s != 0)) {
            return ab / (Math.sqrt(a_s) * Math.sqrt(b_s));
        } else {
            return 0;
        }
    }

    public double getS(double alpha) {
        return alpha * getLPS() + (1 - alpha) * getLTS();

    }

    void comupeteNodes() {

        Node oneS = source.getSource();
        Node oneT = source.getTarget();

        Node secondS = source.getSource();
        Node secondT = source.getTarget();
        if (oneS.equals(secondS)) {
            node1 = oneT;
            node2 = secondT;
        }
        if (oneS.equals(secondT)) {
            node1 = oneT;
            node2 = secondS;
        }
        if (oneT.equals(secondS)) {
            node1 = oneS;
            node2 = secondT;
        }

        if (oneT.equals(secondT)) {
            node1 = oneS;
            node2 = secondS;
        }


    }
}

