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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FeatureValue that = (FeatureValue) o;

        if (type != that.type) return false;
        return value != null ? value.equals(that.value) : that.value == null;
    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

    public FeatureTypes getType() {
        return type;
    }

    public void setType(FeatureTypes type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
