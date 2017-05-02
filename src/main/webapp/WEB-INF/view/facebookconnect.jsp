<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Le styles -->
    <link href="resources/bootstrap/css/bootstrap.css" rel="stylesheet">
    <style>
        body {
            padding-top: 60px; /* 60px to make the container go all the way
              to the bottom of the topbar */
        }
    </style>
    <link href="resources/bootstrap/css/bootstrap-responsive.css"
          rel="stylesheet">
    <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js">
    </script>
    <![endif]-->
    <!-- Le fav and touch icons -->
    <link rel="shortcut icon" href="resources/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144"
          href="resources/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114"
          href="resources/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72"
          href="resources/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed"
          href="resources/ico/apple-touch-icon-57-precomposed.png">
    <style>
        .error {
            color: #ff0000;
        }

        .errorblock {
            color: #000;
            background-color: #ffEEEE;
            border: 3px solid #ff0000;
            padding: 8px;
            margin: 16px;
        }
    </style>
</head>
<body>

<div class="container">
    <div>
        <h1>Create User</h1>
    </div>
    <form:form commandName="/connectFacebook" class="form-horizontal" method="POST">
        <input type="hidden" name="scope" value="user_posts" />
        <div class="formInfo">
            <p>You aren't connected to Facebook yet. Click the button to connect this application with your Facebook account.</p>
        </div>
        <div class="form-group row">
            <input type="submit" class="btn" value="Connect to Facebook" />
        </div>
    </form:form>
    <div class="control-group"></div>
</div>

<script src="jquery-1.8.3.js">

</script>
<script src="resources/bootstrap/js/bootstrap.js">

</script>
</body>
</html>
