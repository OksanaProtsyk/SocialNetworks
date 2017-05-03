<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org"
      xmlns:social="http://spring.io/springsocial"
      xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout"
      layout:decorator="layout">
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
    <h3>Your LinkedIn Connections</h3>

    <p>First degree count: <span text="${firstDegreeCount}">first degree count</span></p>
    <p>Second degree count: <span text="${secondDegreeCount}">second degree count</span></p>

    <ul class="friends">
        <li each="connection : ${connections}">
            <img src="${connection.profilePictureUrl}" align="middle"/><span text="${connection.firstName}"></span> <span th:text="${connection.lastName}"></span></li>
    </ul>
</div>
</body>
</html>
