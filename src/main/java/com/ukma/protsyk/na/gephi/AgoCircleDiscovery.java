package com.ukma.protsyk.na.gephi;

import com.ukma.protsyk.na.tools.FeatureValue;
import org.gephi.appearance.api.*;
import org.gephi.appearance.plugin.PartitionElementColorTransformer;
import org.gephi.appearance.plugin.palette.Palette;
import org.gephi.appearance.plugin.palette.PaletteManager;
import org.gephi.filters.AttributeColumnPropertyEditor;
import org.gephi.graph.api.*;
import org.gephi.io.exporter.api.ExportController;
import org.gephi.io.importer.api.Container;
import org.gephi.io.importer.api.EdgeDirectionDefault;
import org.gephi.io.importer.api.ImportController;
import org.gephi.io.processor.plugin.DefaultProcessor;
import org.gephi.project.api.ProjectController;
import org.gephi.project.api.Workspace;
import org.gephi.statistics.plugin.Modularity;
import org.openide.util.Lookup;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by okpr0814 on 5/15/2017.
 */
public class AgoCircleDiscovery {
    public static String FILE_PATH_IN = "src/main/webapp/resources/static/assets/data/graph_my.gexf";
    public static String FILE_PATH_OUT = "src/main/webapp/resources/static/assets/data/graph_test_com.gexf";

    public void script() {
        //Init a project - and therefore a workspace
        ProjectController pc = Lookup.getDefault().lookup(ProjectController.class);
        pc.newProject();
        Workspace workspace = pc.getCurrentWorkspace();

        //Get controllers and models
        ImportController importController = Lookup.getDefault().lookup(ImportController.class);
        GraphModel graphModel = Lookup.getDefault().lookup(GraphController.class).getGraphModel();
        AppearanceController appearanceController = Lookup.getDefault().lookup(AppearanceController.class);
        AppearanceModel appearanceModel = appearanceController.getModel();

        //Import file
        Container container;
        try {
            File file = new File(FILE_PATH_IN);
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

        //Export
        ExportController ec = Lookup.getDefault().lookup(ExportController.class);
        Graph graphpp = graphModel.getUndirectedGraph();
        // Column c = graphModel.getNodeTable().getColumn("");
        Set<String> set = new HashSet<>();
        int count = 0;

        for(Node n:graphpp.getNodes()){
           System.out.println(graphpp.getNeighbors(n).toCollection().size()+"_______"+graphpp.getNeighbors(n));
           n.setSize(graphpp.getNeighbors(n).toArray().length/10);
        }

        System.out.println(set);
        System.out.println(count);



        //Export
        try {
            ec.exportFile(new File(FILE_PATH_OUT));
        } catch (IOException ex) {
            ex.printStackTrace();
            return;
        }
    }
}
