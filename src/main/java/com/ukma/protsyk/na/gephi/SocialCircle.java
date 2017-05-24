package com.ukma.protsyk.na.gephi;

import org.gephi.graph.api.Node;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by okpr0814 on 5/25/2017.
 */
public class SocialCircle {
    Set<Node> circle = new HashSet<>();
    FeatureVector vector = new FeatureVector();

    @Override
    public String toString() {
        return "SocialCircle{" +
                "circle=" + circle +
                ", vector=" + vector +
                '}';
    }

    public Set<Node> addToCircle(Node n){
        circle.add(n);
        return circle;
    }

    public FeatureVector getVector() {
        return vector;
    }

    public void setVector(FeatureVector vector) {
        this.vector = vector;
    }
}
