<%--
  Created by IntelliJ IDEA.
  User: okpr0814
  Date: 5/2/2017
  Time: 11:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><html>
<head>
    <title>Title</title>
</head>
<body>
<body>
<div id="header">
    <h1><a th:href="@{/}">Spring Social Showcase</a></h1>
</div>

<div id="leftNav">
    Left nav menu
</div>

<div id="content" layout:fragment="content">
    <h3>Your Facebook Profile "${name}"</h3>
    <p>Hello, ${profile.link} !</p>

    <table class="table table-hover">
        <tr>
            <td>Facebook ID:</td>
            <td>${profile.id}</td>
        </tr>
        <tr>
            <td>Name:</td>
            <td>${profile.name}</td>
        </tr>
        <tr>
            <td>Email:</td>
            <td>${profile.email}</td>
        </tr>
    </table>

    <form id="disconnect" action="@{/connect/facebook}" method="post">
        <input type="hidden" name="_csrf" th:value="${_csrf.token}" />
        <button type="submit">Disconnect from Facebook</button>
        <input type="hidden" name="_method" value="delete" />
    </form>
</div>
</body>
</body>
</html>
