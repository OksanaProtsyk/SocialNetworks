<!DOCTYPE html >
<html xmlns:th="http://www.thymeleaf.org"
      xmlns:social="http://spring.io/springsocial"
      xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout"
      layout:decorator="layout">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<head>
    <title>VK friends</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" th:href="@{/resources/page.css}" type="text/css" media="screen"></link>
    <link rel="stylesheet" th:href="@{/resources/form.css}" type="text/css" media="screen"></link>
    <link rel="stylesheet" th:href="@{/resources/messages/messages.css}" type="text/css" media="screen"></link>
</head>
<body>
<div id="header">
</div>

<div id="leftNav">
</div>

<div id="content" layout:fragment="content">
    <h3>Your VK Friends</h3>

    <table class="table table-hover">
        <c:forEach items="${friends}" var="friend">
            <tr>
                <td> <img src="${friend.photo50}" align="middle"/> </td>
                <td><span text="${friend.firstName}">${friend.firstName}</span></td>
                <td><span text="${friend.bdate}">${friend.bdate}</span></td>
                <td><span text="${friend.city}">${friend.city}</span></td>
                <td><span text="${friend.country}">${friend.country}</span></td>
                <td><span text="${friend.universityName}">${friend.universityName}</span></td>
                <td><span text="${friend.interests}">${friend.interests}</span></td>

                <td><span text="${friend.music}">${friend.music}</span></td>
                <td><span text="${friend.movies}">${friend.movies}</span></td>
                <td><span text="${friend.tv}">${friend.tv}</span></td>
                <td><span text="${friend.books}">${friend.books}</span></td>
                <td><span text="${friend.games}">${friend.games}</span></td>






            </tr>
        </c:forEach>
    </table>

</div>
</body>
</html>
