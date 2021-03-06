package com.ukma.protsyk.na.gephi;

import org.gephi.graph.api.*;
import org.gephi.io.exporter.api.ExportController;
import org.gephi.io.generator.plugin.RandomGraph;
import org.gephi.io.importer.api.Container;
import org.gephi.io.importer.api.ImportController;
import org.gephi.io.processor.plugin.DefaultProcessor;
import org.gephi.layout.plugin.AutoLayout;
import org.gephi.layout.plugin.force.StepDisplacement;
import org.gephi.layout.plugin.force.yifanHu.YifanHuLayout;
import org.gephi.layout.plugin.forceAtlas.ForceAtlasLayout;
import org.gephi.project.api.ProjectController;
import org.gephi.project.api.Workspace;
import org.openide.util.Lookup;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by okpr0814 on 5/5/2017.
 */
public class VisualizeUsers {
    public static String FILE_PATH = "src/main/webapp/resources/static/assets/data/dataautolayout.gexf";
    public void script() {
        //Init a project - and therefore a workspace
        ProjectController pc = Lookup.getDefault().lookup(ProjectController.class);
        pc.newProject();
        Workspace workspace = pc.getCurrentWorkspace();

        //Generate a new random graph into a container
        Container container = Lookup.getDefault().lookup(Container.Factory.class).newContainer();
      /*  RandomGraph randomGraph = new RandomGraph();
        randomGraph.setNumberOfNodes(100);
        randomGraph.setWiringProbability(0.1);
        randomGraph.generate(container.getLoader());
*/


        //Append container to graph structure
        ImportController importController = Lookup.getDefault().lookup(ImportController.class);
        importController.process(container, new DefaultProcessor(), workspace);
        GraphModel graphModel = Lookup.getDefault().lookup(GraphController.class).getGraphModel(workspace);

        //Create three nodes
        Node n0 = graphModel.factory().newNode("n0");
        n0.setLabel("Node 0");
        Node n1 = graphModel.factory().newNode("n1");
        n1.setLabel("Node 1");
        Node n2 = graphModel.factory().newNode("n2");
        n2.setLabel("Node 2");

        //Create three edges
        Edge e1 = graphModel.factory().newEdge(n1, n2, 0, 1.0, true);
        Edge e2 = graphModel.factory().newEdge(n0, n2, 0, 2.0, true);
        Edge e3 = graphModel.factory().newEdge(n2, n0, 0, 2.0, true);   //This is e2's mutual edge

        //Append as a Directed Graph
        DirectedGraph directedGraph = graphModel.getDirectedGraph();
        directedGraph.addNode(n0);
        directedGraph.addNode(n1);
        directedGraph.addNode(n2);
        directedGraph.addEdge(e1);
        directedGraph.addEdge(e2);
        directedGraph.addEdge(e3);

        //Count nodes and edges
        System.out.println("Nodes: " + directedGraph.getNodeCount() + " Edges: " + directedGraph.getEdgeCount());

        //Get a UndirectedGraph now and count edges
        UndirectedGraph undirectedGraph = graphModel.getUndirectedGraph();
        System.out.println("Edges: " + undirectedGraph.getEdgeCount());   //The mutual edge is automatically merged

        //See if graph is well imported
      //  GraphModel graphModel = Lookup.getDefault().lookup(GraphController.class).getGraphModel();
       // DirectedGraph graph = graphModel.getDirectedGraph();
       // System.out.println("Nodes: " + graph.getNodeCount());
        //System.out.println("Edges: " + graph.getEdgeCount());

        //Layout for 1 minute
        AutoLayout autoLayout = new AutoLayout(1, TimeUnit.MINUTES);
        autoLayout.setGraphModel(graphModel);
        YifanHuLayout firstLayout = new YifanHuLayout(null, new StepDisplacement(1f));
        ForceAtlasLayout secondLayout = new ForceAtlasLayout(null);
        AutoLayout.DynamicProperty adjustBySizeProperty = AutoLayout.createDynamicProperty("forceAtlas.adjustSizes.name", Boolean.TRUE, 0.1f);//True after 10% of layout time
        AutoLayout.DynamicProperty repulsionProperty = AutoLayout.createDynamicProperty("forceAtlas.repulsionStrength.name", 500., 0f);//500 for the complete period
        autoLayout.addLayout(firstLayout, 0.5f);
        autoLayout.addLayout(secondLayout, 0.5f, new AutoLayout.DynamicProperty[]{adjustBySizeProperty, repulsionProperty});
        autoLayout.execute();


        //Export
        ExportController ec = Lookup.getDefault().lookup(ExportController.class);
        try {
            ec.exportFile(new File(FILE_PATH));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
