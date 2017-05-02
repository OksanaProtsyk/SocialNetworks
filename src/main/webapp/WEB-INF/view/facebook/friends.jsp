<%--
  Created by IntelliJ IDEA.
  User: okpr0814
  Date: 5/2/2017
  Time: 9:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
friends ${lengths}
<table class="table table-hover">
    <c:forEach items="${friends}" var="friend">
        <tr>
            <td> <img src="'http://graph.facebook.com/' + ${friend.id} + '/picture'" align="middle"/> </td>
            <td><span text="${friend.name}">name</span></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
