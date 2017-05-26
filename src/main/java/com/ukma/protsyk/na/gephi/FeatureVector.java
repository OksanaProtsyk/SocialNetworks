package com.ukma.protsyk.na.gephi;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by okpr0814 on 5/25/2017.
 */
public class FeatureVector {
    Map<String,Double> features = new HashMap<>();

    public  Map<String,Double> addFeature(String n,double d){
        features.put(n,d);
        return features;
    }

    @Override
    public String toString() {
        return "FeatureVector{" +
                "features=" + features +
                '}';
    }
}
