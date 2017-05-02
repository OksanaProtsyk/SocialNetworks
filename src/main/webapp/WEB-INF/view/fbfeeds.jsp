<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html lang="en">
<body>
<div>
    <form:form commandName="listOwnSkills">
    <table class="table table-hover">
        <c:forEach items="${feed}" var="fed">
            <tr>
                <td> ${fed.message}</td>
            </tr>
        </c:forEach>
    </table>
    <!--<input type="submit" class="btn" value="Зберегти" />-->
    </form:form>
</div>
</body>
</html>