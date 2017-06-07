package com.ukma.protsyk.na.gephi;


import com.ukma.protsyk.na.tools.FeatureValue;
import com.ukma.protsyk.na.tools.ProfileFeature;
import com.ukma.protsyk.na.tools.TagExtractorOfCircle;
import org.gephi.appearance.api.AppearanceController;
import org.gephi.appearance.api.AppearanceModel;
import org.gephi.appearance.plugin.palette.Palette;
import org.gephi.appearance.plugin.palette.PaletteManager;
import org.gephi.datalab.api.AttributeColumnsController;
import org.gephi.graph.api.*;
import org.gephi.graph.impl.EdgeImpl;
import org.gephi.io.exporter.api.ExportController;
import org.gephi.io.importer.api.Container;
import org.gephi.io.importer.api.EdgeDirectionDefault;
import org.gephi.io.importer.api.ImportController;
import org.gephi.io.processor.plugin.DefaultProcessor;
import org.gephi.project.api.ProjectController;
import org.gephi.project.api.Workspace;
import org.openide.util.Lookup;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Array;
import java.util.*;
import java.util.List;

/**
 * Created by okpr0814 on 5/23/2017.
 */
public class AgoTagCircle {
    public static String FILE_PATH = "src/main/webapp/resources/static/assets/data/";
    private static String PHOTO_DEACTIVATED = "https://vk.com/images/deactivated_50.png";

    public void script(String fileName) {
        readFile(fileName);
    }

    public void readFile(String fileName) {
        ProjectController pc = Lookup.getDefault().lookup(ProjectController.class);
        pc.newProject();
        Workspace workspace = pc.getCurrentWorkspace();

        ImportController importController = Lookup.getDefault().lookup(ImportController.class);
        GraphModel graphModel = Lookup.getDefault().lookup(GraphController.class).getGraphModel();
        AppearanceController appearanceController = Lookup.getDefault().lookup(AppearanceController.class);
        AttributeColumnsController columnsController = Lookup.getDefault().lookup(AttributeColumnsController.class);

        AppearanceModel appearanceModel = appearanceController.getModel();

        //Import file
        Container container;
        try {
            File file = new File(FILE_PATH + fileName + ".gexf");
            container = importController.importFile(file);
            container.getLoader().setEdgeDefault(EdgeDirectionDefault.UNDIRECTED);   //Force DIRECTED
        } catch (Exception ex) {
            ex.printStackTrace();
            return;
        }

        //Append imported data to GraphAPI
        importController.process(container, new DefaultProcessor(), workspace);


        //remove deactivated nodes
        for (Node n : graphModel.getUndirectedGraph().getNodes().toArray()) {
            if (PHOTO_DEACTIVATED.equals(n.getAttribute("0"))) {
                graphModel.getUndirectedGraph().removeNode(n);
            }
        }


        //See if graph is well imported
        UndirectedGraph graph = graphModel.getUndirectedGraph();
        System.out.println("Nodes: " + graph.getNodeCount());
        System.out.println("Edges: " + graph.getEdgeCount());
        System.out.println("Nodes: ");

        TagExtractorOfCircle tagExtractorOfCircle = new TagExtractorOfCircle();
        Set<FeatureValue> featureValues = tagExtractorOfCircle.getAllFeaturesValues(graph);
        System.out.println(featureValues);
        System.out.println("***************************** ");

        Map<Node, List<ProfileFeature>> node_profiles = tagExtractorOfCircle.getAllUsersProfiles(featureValues, graph);
        System.out.println(node_profiles);

        System.out.println("================: ");


        //List node columns
       /* for (Column col : graphModel.getNodeTable()) {
            System.out.println(col);
        }

        System.out.println("Edge column:");

        for (Column col : graphModel.getEdgeTable()) {
            System.out.println(col);
        }
*/
        graphModel.getEdgeTable().addColumn("del", String.class);
        System.out.println("Edge column after:");


        System.out.println("Edges:");

        Set<Similarity> similaritySet = new HashSet<>();

        for (Edge e : graph.getEdges()) {
            Similarity s = new Similarity(e.getSource(), e.getTarget(), graph, node_profiles);
            similaritySet.add(s);
        }
        System.out.println("Similarity: " + similaritySet);
        double M_VPS = getM_VPS(similaritySet);
        double M_VTS = getM_VTS(similaritySet);

        for (Node i : graph.getNodes().toArray()) {
            i.setSize(graph.getNeighbors(i).toCollection().size() / 10 + 1);
            i.setColor(Color.WHITE);

            for (Node j : graph.getNodes().toArray()) {
                if (i.equals(j) || checkEdgeExistence(graph.getEdges().toArray(), i.getId(), j.getId())
                        || checkEdgeExistence(graph.getEdges().toArray(), j.getId(), i.getId())) {
                    // System.out.println("____________________________");
                    continue;
                }
                // System.out.println(M_VPS+"________"+M_VTS);
                Similarity similarity = new Similarity(i, j, graph, node_profiles);
                if ((similarity.getVPS() > M_VPS) && (similarity.getVTS() > M_VTS)) {
                    Edge e = graphModel.factory().newEdge(i, j, false);
                    e.setAttribute("del", "0");
                    graphModel.getUndirectedGraph().addEdge(e);
                }

            }

        }
//second part
        double similarityThreshold = 0.4;

        List<SocialCircle> socialCircles = new ArrayList<>();

        List<Edge> searchedEdges = new ArrayList<>();
        List<Edge> edgeCircle = new ArrayList<>();

        for (Edge i : graph.getEdges().toArray()) {
            if (!searchedEdges.contains(i)) {
                searchedEdges.add(i);
                edgeCircle = new ArrayList<>();
                edgeCircle.add(i);

                for (int ite = 0; ite < edgeCircle.size(); ite++) {
                    Edge j = edgeCircle.get(ite);
                    for (Edge k : getConnectedEdges(j, graph)) {
                        EdgeSimilarity edgeSimilarity = new EdgeSimilarity(graphModel.getUndirectedGraph(), j, k, node_profiles);
                        // System.out.println("EC= "+edgeCircle);
                        double sim = edgeSimilarity.getS(0.8);
                        //System.out.println(edgeSimilarity+" - "+sim);

                        if (!searchedEdges.contains(k) && (sim > similarityThreshold)) {
                            if (!edgeCircle.contains(k)) {
                                edgeCircle.add(k);
                            }
                            searchedEdges.add(k);

                        }
                    }
                }

                SocialCircle s = new SocialCircle();
                //System.out.println("& "+edgeCircle.size());
                for (Edge ed : edgeCircle) {
                    s.addToCircle(ed.getSource());
                    s.addToCircle(ed.getTarget());
                }
                s.setVector(circlefeatureAbstarct(edgeCircle, node_profiles));
                if (s.getCircle().size() > 3) {
                    socialCircles.add(s);
                }
            }

        }
        System.out.println(socialCircles);
        for (SocialCircle s : socialCircles) {
          System.out.println(s.getCircle().size());
          }

        for (Edge e : graphModel.getUndirectedGraph().getEdges().toArray()) {
            if ("0".equals(e.getAttribute("del"))) {
                graphModel.getUndirectedGraph().removeEdge(e);
            }
        }

        ExportController ec = Lookup.getDefault().lookup(ExportController.class);

        Palette palette2 = PaletteManager.getInstance().randomPalette(socialCircles.size());

        for (int i = 0; i < socialCircles.size(); i++) {
            SocialCircle socialCircle = socialCircles.get(i);
            for (Node n : socialCircle.getCircle()) {
                n.setColor(palette2.getColors()[i]);
            }
        }



        System.out.println("SC" + socialCircles.size());

        //Export
        try {
            ec.exportFile(new File(FILE_PATH + fileName + "_out.gexf"));
        } catch (IOException ex) {
            ex.printStackTrace();
            return;
        }
    }


    public double getM_VTS(Set<Similarity> similaritySet) {
        double sum = 0;
        for (Similarity s : similaritySet) {
            sum += s.getVTS();
        }
        return sum / similaritySet.size();
    }

    public double getM_VPS(Set<Similarity> similaritySet) {
        double sum = 0;
        for (Similarity s : similaritySet) {
            sum += s.getVPS();
        }
        return sum / similaritySet.size();
    }

    public FeatureVector circlefeatureAbstarct(List<Edge> es, Map<Node, List<ProfileFeature>> node_profiles) {
        List<ProfileFeature> sumVector = new ArrayList<>();
        List<ProfileFeature> features = node_profiles.get(es.get(0).getSource());
        for (int i = 0; i < features.size(); i++) {
            sumVector.add(new ProfileFeature(features.get(i).getFeatureValue(), 0.0));
        }


        for (Edge e : es) {
            List<ProfileFeature> node1Profile = node_profiles.get(e.getSource());
            List<ProfileFeature> node2Profile = node_profiles.get(e.getTarget());
            List<ProfileFeature> intF = intersectProfiles(node1Profile, node2Profile);
            sumVector = addProfiles(sumVector, intF);

        }

        sumVector = normalize(sumVector, es.size());
        return constractFeatureVector(sumVector);
    }

    public boolean checkEdgeExistence(Edge[] edges, Object id_s, Object id_t) {
        for (Edge e : edges) {
            if (e.getSource().getId().equals(id_s) && e.getTarget().getId().equals(id_t)) {
                return true;
            }
        }
        return false;
    }


    public List<ProfileFeature> intersectProfiles(List<ProfileFeature> node1Profile,
                                                  List<ProfileFeature> node2Profile) {
        List<ProfileFeature> p = new ArrayList<>();
        for (int i = 0; i < node1Profile.size(); i++) {
            if (node1Profile.get(i).getData().equals(node2Profile.get(i).getData())) {
                p.add(new ProfileFeature(node1Profile.get(i).getFeatureValue(), node1Profile.get(i).getData()));

            } else {
                p.add(new ProfileFeature(node1Profile.get(i).getFeatureValue(), 0.0));
            }
        }
        return p;
    }


    public List<ProfileFeature> addProfiles(List<ProfileFeature> node1Profile,
                                            List<ProfileFeature> node2Profile) {
        List<ProfileFeature> p = new ArrayList<>();
        for (int i = 0; i < node1Profile.size(); i++) {
            p.add(new ProfileFeature(node1Profile.get(i).getFeatureValue(), node1Profile.get(i).getData() + node2Profile.get(i).getData()));


        }
        return p;
    }


    public List<ProfileFeature> normalize(List<ProfileFeature> node1Profile,
                                          int norm) {
        List<ProfileFeature> p = new ArrayList<>();
        for (int i = 0; i < node1Profile.size(); i++) {
            p.add(new ProfileFeature(node1Profile.get(i).getFeatureValue(), node1Profile.get(i).getData() / norm));


        }
        return p;
    }

    public FeatureVector constractFeatureVector(List<ProfileFeature> profileFeatures) {
        double threshold = 0.1;
        FeatureVector fV = new FeatureVector();
        for (ProfileFeature pf : profileFeatures) {
            if (pf.getData() > threshold) {
                fV.addFeature(pf.getFeatureValue().getValue(), pf.getData());
            }
        }
        return fV;
    }

    public List<Edge> getConnectedEdges(Edge e, Graph graph) {
        List<Edge> toRet = new ArrayList<>();
        Node n1 = e.getTarget();
        Node n2 = e.getSource();

        Edge[] n1Edges = graph.getEdges(n1).toArray();

        Edge[] n2Edges = graph.getEdges(n1).toArray();
        for (Edge ej : n1Edges) {
            if (!toRet.contains(ej) && !e.equals(ej)) {
                toRet.add(ej);
            }
        }

        for (Edge ej : n2Edges) {
            if (!toRet.contains(ej) && !e.equals(ej)) {
                toRet.add(ej);
            }
        }

        return toRet;
    }
}
