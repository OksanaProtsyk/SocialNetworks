package com.ukma.protsyk.na.tools;

import org.gephi.graph.api.Graph;
import org.gephi.graph.api.Node;

import java.util.*;

/**
 * Created by okpr0814 on 5/23/2017.
 */
public class TagExtractorOfCircle {

    public static String ATTR_OCCUPATION = "19";

    public static String ATTR_HOME_TOWN = "7";

    public static String ATTR_CITY = "2";

    public static String ATTR_UNIVERSITY = "10";

    public static String ATTR_SCHOOL = "18";

    public Set<FeatureValue> getAllFeaturesValues(Graph g) {

        Set<FeatureValue> f = new HashSet<>();
        for (Node n : g.getNodes().toArray()) {
            //Occupation
            Object o = n.getAttribute(ATTR_OCCUPATION);
            if (o != null) {
                f.add(new FeatureValue(FeatureTypes.OCCUPATION, o.toString()));
            }
            Object o2 = n.getAttribute(ATTR_HOME_TOWN);
            if (o2 != null) {
                f.add(new FeatureValue(FeatureTypes.HOME_TOWN, o2.toString()));
            }

            Object o3 = n.getAttribute(ATTR_CITY);
            if (o3 != null) {
                f.add(new FeatureValue(FeatureTypes.CITY, o3.toString()));
            }
            Object o4 = n.getAttribute(ATTR_UNIVERSITY);
            if (o4 != null) {
                f.add(new FeatureValue(FeatureTypes.UNIVERSITY, o4.toString()));
            }
            Object o5 = n.getAttribute(ATTR_SCHOOL);
            if (o5 != null) {
                f.add(new FeatureValue(FeatureTypes.SCHOOL, o5.toString()));
            }

        }
        return f;
    }

    public Map<Node, List<ProfileFeature>> getAllUsersProfiles(Set<FeatureValue> featureValues, Graph g) {
        Map<Node, List<ProfileFeature>> nodeProfiles = new HashMap<>();

        for (Node n : g.getNodes().toArray()) {
            nodeProfiles.put(n,getUserProfile(featureValues,n));
        }
        return nodeProfiles;
    }


    public List<ProfileFeature> getUserProfile(Set<FeatureValue> pfs, Node n) {
        List<ProfileFeature> userProfile = new ArrayList();
        for (FeatureValue f : pfs) {
            switch (f.getType()) {
                case OCCUPATION:
                    if (f.getValue().equals(n.getAttribute(ATTR_OCCUPATION))) {
                        userProfile.add(new ProfileFeature(f, 1.0));
                    } else {
                        userProfile.add(new ProfileFeature(f, 0.0));

                    }
                    break;
                case HOME_TOWN:
                    if (f.getValue().equals(n.getAttribute(ATTR_HOME_TOWN))) {
                        userProfile.add(new ProfileFeature(f, 1.0));
                    } else {
                        userProfile.add(new ProfileFeature(f, 0.0));

                    }
                    break;
                case CITY:
                    if (f.getValue().equals(n.getAttribute(ATTR_CITY))) {
                        userProfile.add(new ProfileFeature(f, 1.0));
                    } else {
                        userProfile.add(new ProfileFeature(f, 0.0));

                    }
                    break;
                case UNIVERSITY:
                    if (f.getValue().equals(n.getAttribute(ATTR_UNIVERSITY))) {
                        userProfile.add(new ProfileFeature(f, 1.0));
                    } else {
                        userProfile.add(new ProfileFeature(f, 0.0));

                    }
                    break;
                case SCHOOL:
                    if (f.getValue().equals(n.getAttribute(ATTR_SCHOOL))) {
                        userProfile.add(new ProfileFeature(f, 1.0));
                    } else {
                        userProfile.add(new ProfileFeature(f, 0.0));

                    }
                    break;

            }

        }
        return userProfile;
    }
}
