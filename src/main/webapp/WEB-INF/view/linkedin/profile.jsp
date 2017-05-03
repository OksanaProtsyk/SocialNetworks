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
    <h3>Your LinkedIn Profile</h3>

    <p>Hello, <span th:text="${profile.firstName}">first name</span>!</p>
    <img th:src="${profile.profilePictureUrl}"/>
    <dl>
        <dt>LinkedIn ID:</dt>
        <dd><a th:href="${profile.publicProfileUrl}" target="_blank" th:text="${profile.id}">profile id</a></dd>
        <dt>Email Address:</dt>
        <dd th:text="${profile.emailAddress}"></dd>
        <dt>Headline:</dt>
        <dd th:text="${profile.headline}"></dd>
        <dt>Industry:</dt>
        <dd th:text="${profile.industry}"></dd>
        <dt>Summary:</dt>
        <dd th:text="${profile.summary}"></dd>
    </dl>

    <form id="disconnect" th:action="@{/connect/linkedin}" method="post">
        <input type="hidden" name="_csrf" th:value="${_csrf.token}" />
        <button type="submit">Disconnect from LinkedIn</button>
        <input type="hidden" name="_method" value="delete" />
    </form>
</div>
</body>
</html>
