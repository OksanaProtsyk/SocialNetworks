package com.ukma.protsyk.na.tools;

/**
 * Created by okpr0814 on 5/25/2017.
 */
public class ProfileFeature {
    FeatureValue featureValue;
    //0 or 1
    Double data;


    public ProfileFeature(FeatureValue featureValue, Double data) {
        this.featureValue = featureValue;
        this.data = data;
    }

    public FeatureValue getFeatureValue() {
        return featureValue;
    }

    public void setFeatureValue(FeatureValue featureValue) {
        this.featureValue = featureValue;
    }

    public Double getData() {
        return data;
    }

    public void setData(Double data) {
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
