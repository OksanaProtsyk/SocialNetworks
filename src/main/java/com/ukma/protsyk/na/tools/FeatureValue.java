package com.ukma.protsyk.na.tools;

/**
 * Created by okpr0814 on 5/22/2017.
 */
public class FeatureValue {
    FeatureTypes type;
    String value;

    public FeatureValue(FeatureTypes type, String value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public String toString() {
        return "FeatureValue{" +
                "type=" + type +
                ", value='" + value + '\'' +
                '}';
    }
}
