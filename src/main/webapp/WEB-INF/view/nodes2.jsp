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
    <title>NODES</title>
</head>
<body>
Something is not working
<style type="text/css">
    #sigma-container {
        max-width: 100%;
        height: 100%;
        margin: auto;
    }
</style>
<!-- [...] -->
<div id="sigma-container"></div>

<div class="eight columns">
    <div class="box" id="sigma-tuto-step2"></div>
</div>
<!-- [...] -->
</body>
<script src="/resources/static/assets/js/sigma.js"></script>
<script src="/resources/static/assets/js/plugins/sigma.parsers.gexf.min.js"></script>
<script>
    sigma.parsers.gexf(
        '/resources/static/assets/data/graph_out.gexf',
        {
            container: 'sigma-container',
            settings: {
                defaultNodeColor: '#ec5148'
            }
        })
</script>
</html>