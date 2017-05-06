<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org"
      xmlns:social="http://spring.io/springsocial"
      xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout"
      layout:decorator="layout">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<head>
    <title>Spring Social Showcase</title>
    <link rel="stylesheet" th:href="@{/resources/page.css}" type="text/css" media="screen"></link>
    <link rel="stylesheet" th:href="@{/resources/form.css}" type="text/css" media="screen"></link>
    <link rel="stylesheet" th:href="@{/resources/messages/messages.css}" type="text/css" media="screen"></link>
</head>
<body>
<div id="header">
    <h1><a th:href="@{/}">Spring Social Showcase</a></h1>
</div>

<div id="leftNav">
    Left nav menu
</div>

<div id="content" layout:fragment="content">
    <h3>Your VK Friends</h3>

    <table class="table table-hover">
        <c:forEach items="${friends}" var="friend">
            <tr>
                <td> <img src="${friend.photo50}" align="middle"/> </td>
                <td><span text="${friend.firstName}">${friend.firstName}</span></td>
            </tr>
        </c:forEach>
    </table>

</div>
</body>
</html>
