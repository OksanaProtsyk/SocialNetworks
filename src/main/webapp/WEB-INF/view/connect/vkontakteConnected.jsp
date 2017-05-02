<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: okpr0814
  Date: 5/2/2017
  Time: 9:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div id="content" layout:fragment="content">
    <h3>Connected to VK</h3>

    <form id="disconnect" method="post">
        <input type="hidden" name="_csrf" th:th:value="${_csrf.token}" />
        <div class="formInfo">
            <p>
                Spring Social Showcase is connected to your VK account. ${vkemail}
                Click the button if you wish to disconnect.
            </p>
        </div>
        <button type="submit">Disconnect</button>
        <input type="hidden" name="_method" value="delete" />
    </form>

    <a href="/facebookp">View your VK profile</a>
    <a href="/facebook/friends">View your Friends</a>

</div>
</body>
</html>
