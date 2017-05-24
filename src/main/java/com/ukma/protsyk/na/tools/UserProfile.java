package com.ukma.protsyk.na.tools;

import org.gephi.graph.api.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by okpr0814 on 5/25/2017.
 */
public class UserProfile {
    Node node;
    List<ProfileFeature> profileFeatures = new ArrayList<>();

    public  List<ProfileFeature> addFeature(ProfileFeature f){
        profileFeatures.add(f);
        return profileFeatures;
    }

    public UserProfile(Node node) {
        this.node = node;
    }

    public UserProfile(Node node, List<ProfileFeature> profileFeatures) {
        this.node = node;
        this.profileFeatures = profileFeatures;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public List<ProfileFeature> getProfileFeatures() {
        return profileFeatures;
    }

    public void setProfileFeatures(List<ProfileFeature> profileFeatures) {
        this.profileFeatures = profileFeatures;
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "node=" + node +
                ", profileFeatures=" + profileFeatures +
                '}';
    }
}
