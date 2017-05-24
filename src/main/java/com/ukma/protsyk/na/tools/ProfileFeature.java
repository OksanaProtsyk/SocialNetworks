package com.ukma.protsyk.na.tools;

/**
 * Created by okpr0814 on 5/25/2017.
 */
public class ProfileFeature {
    FeatureValue featureValue;
    //0 or 1
    Integer data;


    public ProfileFeature(FeatureValue featureValue, Integer data) {
        this.featureValue = featureValue;
        this.data = data;
    }

    public FeatureValue getFeatureValue() {
        return featureValue;
    }

    public void setFeatureValue(FeatureValue featureValue) {
        this.featureValue = featureValue;
    }

    public Integer getData() {
        return data;
    }

    public void setData(Integer data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ProfileFeature{" +
                "featureValue=" + featureValue +
                ", data=" + data +
                '}';
    }
}
