<%--
  Created by IntelliJ IDEA.
  User: okpr0814
  Date: 5/4/17
  Time: 9:18 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Vk Graph</title>
</head>
<body style="background-color:rgb(0,0,0);">
<style type="text/css">
    #sigma-container {
        max-width: 100%;
        height: 100%;
        margin: auto;
        background-color: rgb(0, 0, 0);
    }
</style>
<!-- [...] -->
<div id="sigma-container"></div>

<div class="eight columns">
    <div class="box" id="sigma-tuto-step2"></div>
</div>
<!-- [...] -->
<script src="/resources/static/assets/js/sigma.js"></script>
<script src="/resources/static/assets/js/plugins/sigma.parsers.json.min.js"></script>
<script src="/resources/static/assets/js/plugins/sigma.parsers.gexf.min.js"></script>

<script>

    sigma.settings.labelThreshold=20;
    // Add a method to the graph model that returns an
    // object with every neighbors of a node inside:
    sigma.classes.graph.addMethod('neighbors', function(nodeId) {
        var k,
                neighbors = {},
                index = this.allNeighborsIndex[nodeId] || {};

        for (k in index)
            neighbors[k] = this.nodesIndex[k];

        return neighbors;
    });

    sigma.classes.graph.addMethod('sameColor', function(node,t) {
        var k,
            neighbors = {},

            index = t.nodesIndex;

        console.log("I am here",t);

      /*  for (var j = 0; j < t.length; j++){
            console.log(t[j].originalColor);
            if(t[j].originalColor == node.originalColor){
                neighbors[j] = t[j];
                console.log("IN circle");

            }
        }*/



        console.log(neighbors);
        return neighbors;
    });

    sigma.parsers.gexf(
            '/resources/static/assets/data/graph_my_out.gexf',
            {
                container: 'sigma-container'
            },
            function(s) {
                // We first need to save the original colors of our
                // nodes and edges, like this:
                var t = s.graph.nodes();

                s.graph.nodes().forEach(function(n) {
                    n.originalColor = n.color;
                });
                s.graph.edges().forEach(function(e) {
                    e.originalColor = e.color;
                });

                // When a node is clicked, we check for each node
                // if it is a neighbor of the clicked one. If not,
                // we set its color as grey, and else, it takes its
                // original color.
                // We do the same for the edges, and we only keep
                // edges that have both extremities colored.
                s.bind('clickNode', function(e) {
                    var nodeId = e.data.node.id,
                            toKeep = s.graph.neighbors(nodeId);
                    toKeep[nodeId] = e.data.node;

                    s.graph.nodes().forEach(function(n) {
                        if (toKeep[n.id])
                            n.color = n.originalColor;
                        else
                            n.color = '#636363';
                    });

                    s.graph.edges().forEach(function(e) {
                        if (toKeep[e.source] && toKeep[e.target])
                            e.color = e.originalColor;
                        else
                            e.color = '#636363';
                    });

                    // Since the data has been modified, we need to
                    // call the refresh method to make the colors
                    // update effective.
                    s.refresh();
                });

                // When the stage is clicked, we just color each
                // node and edge with its original color.
                s.bind('clickStage', function(e) {
                    s.graph.nodes().forEach(function(n) {
                        n.color = n.originalColor;
                    });

                    s.graph.edges().forEach(function(e) {
                        e.color = e.originalColor;
                    });

                    // Same as in the previous event:
                    s.refresh();
                });
            }
    );
</script>
</body>

</html>