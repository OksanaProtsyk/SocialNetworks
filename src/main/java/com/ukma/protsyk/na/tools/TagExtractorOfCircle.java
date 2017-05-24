package com.ukma.protsyk.na.tools;

import org.gephi.graph.api.Graph;
import org.gephi.graph.api.Node;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by okpr0814 on 5/23/2017.
 */
public class TagExtractorOfCircle {

    public Set<FeatureValue> getAllFeaturesValues(Graph g) {

        Set<FeatureValue> f = new HashSet<>();
        for (Node n : g.getNodes().toArray()) {
            //Occupation
            Object o = n.getAttribute("19");
            if (o != null) {
                f.add(new FeatureValue(FeatureTypes.OCCUPATION, o.toString()));
            }
            Object o2 = n.getAttribute("7");
            if (o2 != null) {
                f.add(new FeatureValue(FeatureTypes.HOME_TOWN, o2.toString()));
            }

        }
        return f;
    }

    public Set<UserProfile> getAllUsersProfiles(Set<FeatureValue> featureValues, Graph g) {
        Set<UserProfile> userProfiles = new HashSet<>();

        for (Node n : g.getNodes().toArray()) {
            userProfiles.add(getUserProfile(featureValues,n));
        }
        return userProfiles;
    }


    public UserProfile getUserProfile(Set<FeatureValue> pfs, Node n) {
        UserProfile userProfile = new UserProfile(n);
        for (FeatureValue f : pfs) {
            switch (f.getType()) {
                case OCCUPATION:
                    if (f.getValue().equals(n.getAttribute("19"))) {
                        userProfile.addFeature(new ProfileFeature(f, 1));
                    } else {
                        userProfile.addFeature(new ProfileFeature(f, 0));

                    }
                    break;
                case HOME_TOWN:
                    if (f.getValue().equals(n.getAttribute("8"))) {
                        userProfile.addFeature(new ProfileFeature(f, 1));
                    } else {
                        userProfile.addFeature(new ProfileFeature(f, 0));

                    }
                    break;
            }

        }
        return userProfile;
    }
}
