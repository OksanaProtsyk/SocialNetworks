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
</div>

<div id="leftNav">
</div>

<div id="content" layout:fragment="content">
    <h3>Connected to LinkedIn</h3>

    <form id="disconnect" method="post">
        <input type="hidden" name="_csrf" th:value="${_csrf.token}" />
        <div class="formInfo">
            <p>The Spring Social Showcase sample application is already connected to your LinkedIn account.
                Click the button if you wish to disconnect</p>
        </div>

        <button type="submit">Disconnect</button>
        <input type="hidden" name="_method" value="delete" />
    </form>

    <p><a href="linkedin">View your LinkedIn profile</a></p>
</div>
</body>
</html>

