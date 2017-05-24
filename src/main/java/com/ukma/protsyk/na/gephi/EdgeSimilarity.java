package com.ukma.protsyk.na.gephi;

/**
 * Created by okpr0814 on 5/25/2017.
 */

import org.gephi.graph.api.*;

/**
 * Created by okpr0814 on 5/24/2017.
 */
public class EdgeSimilarity {
    Edge source;
    Edge target;
    Graph graph;

    public EdgeSimilarity(Graph graph,Edge source, Edge target) {
        this.graph =graph;
        this.source = source;
        this.target = target;
    }

    public double getLTS(){
        //TODO
        return 0;
    }

    public double getLPS()
    {

        //TODO
        return 0;
    }

    public double getS(double alpha){
        return alpha*getLPS()+(1-alpha)*getLTS();

    }
}

