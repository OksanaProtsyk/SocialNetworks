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
</div>

<div id="leftNav">
</div>

<div id="content" layout:fragment="content">
    <h3>Your LinkedIn Profile</h3>

    <p>Hello, <span text="${profile.firstName}">first name</span>!</p>
    <img src="${profile.profilePictureUrl}"/>
    <dl>
        <dt>LinkedIn ID:</dt>
        <dd><a href="${profile.publicProfileUrl}" target="_blank" text="${profile.id}">profile id</a></dd>
        <dt>Email Address:</dt>
        <dd text="${profile.emailAddress}"></dd>
        <dt>Headline:</dt>
        <dd text="${profile.headline}"></dd>
        <dt>Industry:</dt>
        <dd text="${profile.industry}"></dd>
        <dt>Summary:</dt>
        <dd text="${profile.summary}"></dd>
    </dl>

    <form id="disconnect" th:action="@{/connect/linkedin}" method="post">
        <input type="hidden" name="_csrf" th:value="${_csrf.token}" />
        <button type="submit">Disconnect from LinkedIn</button>
        <input type="hidden" name="_method" value="delete" />
    </form>
</div>
</body>
</html>
