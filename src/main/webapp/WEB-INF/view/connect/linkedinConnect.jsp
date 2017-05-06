<%--
  Created by IntelliJ IDEA.
  User: okpr0814
  Date: 5/5/2017
  Time: 5:21 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org"
      xmlns:social="http://spring.io/springsocial"
      xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout"
      layout:decorator="layout">
<head>
    <title>Spring Social Showcase</title>
    <link rel="stylesheet" th:href="@{/page.css}" type="text/css" media="screen"></link>
    <link rel="stylesheet" th:href="@{/form.css}" type="text/css" media="screen"></link>
    <link rel="stylesheet" th:href="@{/messages/messages.css}" type="text/css" media="screen"></link>
</head>
<body>
<div id="header">
    <h1><a th:href="@{/}">Spring Social Showcase</a></h1>
</div>

<div id="leftNav">
    Left nav menu
</div>

<div id="content" layout:fragment="content">
    <h3>Connect to LinkedIn</h3>

    <form action="/connect/linkedin" method="POST">
        <input type="hidden" name="_csrf" th:value="${_csrf.token}" />
        <input type="hidden" name="scope" value="r_basicprofile r_emailaddress r_network r_fullprofile rw_nus" />
        <div class="formInfo">
            <p>
                You haven't created any connections with LinkedIn yet. Click the button to connect Spring Social Showcase with your LinkedIn account.
                (You'll be redirected to LinkedIn where you'll be asked to authorize the connection.)
            </p>
        </div>
        <p><button type="submit">Connect with LinkedIn</button></p>
    </form>
</div>
</body>
</html>
