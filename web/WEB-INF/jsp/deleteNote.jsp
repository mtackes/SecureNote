<%--
    Created by IntelliJ IDEA.
    User: mtackes
    Date: 12/20/15
    Time: 9:51 AM
    To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="noteTitle" value="${param['noteTitle']}" />
<c:set var="noteUuid" value="${param['noteUuid']}" />
<html>
<head>
    <title>Confirm deletion?</title>
</head>
<body>
    <h1>Delete note?</h1>
    <p>Are you sure you want to delete the note with the
        <c:if test="${not empty noteTitle}">
            title "${noteTitle}" and
        </c:if>
        ID <code>${noteUuid}?</code></p>
    <p style="color: red;">This action cannot be undone!</p>
    <br />
    <form method="POST" action="/delete/${noteUuid}">
        <label for="notePassword">Enter password to confirm</label>
        <br />
        <input id="notePassword" name="notePassword" type="password" autofocus="autofocus" />
        <input type="submit" value="Confirm deletion" />
    </form>
    <form method="GET" action="/">
        <input type="submit" value="Cancel and return to home page" />
    </form>
</body>
</html>
