package com.ukma.protsyk.na.gephi;


import org.gephi.appearance.api.AppearanceController;
import org.gephi.appearance.api.AppearanceModel;
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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by okpr0814 on 5/23/2017.
 */
public class AgoTagCircle {
    public static String FILE_PATH = "src/main/webapp/resources/static/assets/data/";

    public void script(String fileName) {
        readFile(fileName);
    }

    public void readFile(String fileName) {
        //Init a project - and therefore a workspace
        ProjectController pc = Lookup.getDefault().lookup(ProjectController.class);
        pc.newProject();
        Workspace workspace = pc.getCurrentWorkspace();

        //Get controllers and models
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

        //See if graph is well imported
        UndirectedGraph graph = graphModel.getUndirectedGraph();
        System.out.println("Nodes: " + graph.getNodeCount());
        System.out.println("Edges: " + graph.getEdgeCount());
        System.out.println("Nodes:");


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
            Similarity s = new Similarity(graph, e.getSource(), e.getTarget());
            similaritySet.add(s);
        }

        double M_VPS = getM_VPS(similaritySet);
        double M_VTS = getM_VTS(similaritySet);

        for (Node i : graph.getNodes().toArray()) {
            for (Node j : graph.getNodes().toArray()) {
                if (i.equals(j) || checkEdgeExistence(graph.getEdges().toArray(), i.getId(), j.getId()) || checkEdgeExistence(graph.getEdges().toArray(), j.getId(), i.getId())) {
                    System.out.print("____________________________");
                    continue;
                }
                Similarity similarity = new Similarity(graph, i, j);
                if ((similarity.getVPS() >= M_VPS) && (similarity.getVTS() >= M_VTS)) {
                    Edge e = graphModel.factory().newEdge(i, j, false);
                    e.setAttribute("del", "0");
                    graphModel.getUndirectedGraph().addEdge(e);
                }

            }

        }
//second part
        double similarityThreshold = 0.5;

        List<SocialCircle> socialCircles = new ArrayList<>();

        List<Edge> searchedEdges = new ArrayList<>();
        Set<Edge> edgeCircle = new HashSet<>();

        for (Edge i : graph.getEdges().toArray()) {
            if (!searchedEdges.contains(i)) {
                searchedEdges.add(i);
                edgeCircle = new HashSet<>();
                edgeCircle.add(i);
                for (Edge j : edgeCircle) {
                    for (Edge k : graphModel.getUndirectedGraph().getEdges().toArray()) {
                        if (!searchedEdges.contains(k) && (new EdgeSimilarity(graphModel.getUndirectedGraph(), j, k).getS(0.2) > similarityThreshold)) {
                            edgeCircle.add(k);
                            searchedEdges.add(k);
                        }
                    }
                }
            }
            SocialCircle s = new SocialCircle();
            for (Edge ed : edgeCircle) {
                s.addToCircle(ed.getSource());
                s.addToCircle(ed.getTarget());
            }
            s.setVector(circlefeatureAbstarct(edgeCircle));
            socialCircles.add(s);

            System.out.println(socialCircles);
        }

        ExportController ec = Lookup.getDefault().lookup(ExportController.class);


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

    public  FeatureVector circlefeatureAbstarct(Set<Edge> es){
//TODO
        return null;
    }

    public boolean checkEdgeExistence(Edge[] edges, Object id_s, Object id_t) {
        for (Edge e : edges) {
            if (e.getSource().getId().equals(id_s) && e.getTarget().getId().equals(id_t)) {
                return true;
            }
        }
        return false;
    }
}
