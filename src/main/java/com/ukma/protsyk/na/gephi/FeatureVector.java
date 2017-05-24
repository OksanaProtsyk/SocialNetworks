package com.ukma.protsyk.na.gephi;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by okpr0814 on 5/25/2017.
 */
public class FeatureVector {
    List<String> features = new ArrayList<>();

    public List<String> addFeature(String n){
        features.add(n);
        return features;
    }


}
