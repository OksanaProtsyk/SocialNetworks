<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org"
      xmlns:social="http://spring.io/springsocial"
      xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout"
      layout:decorator="layout">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><html>

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

    <h3>Your Vkontakti Profile "${profile.firstName}"</h3>
    <p>Hello, ${pprofile.firstName} !</p>

    <table class="table table-hover">
        <tr>
            <td>VKontakte ID:</td>
            <td>${profile.id}</td>
        </tr>
        <tr>
            <td>Name:</td>
            <td>${profile.screenName}</td>
        </tr>
        <tr>
            <td>Email:</td>
            <td>${email}</td>
        </tr>
    </table>
    <form id="disconnect" th:action="@{/connect/vkontakte}" method="post">
        <input type="hidden" name="_csrf" th:value="${_csrf.token}" />
        <button type="submit">Disconnect from VK</button>
        <input type="hidden" name="_method" value="delete" />
    </form>
</div>
</body>
</html>
