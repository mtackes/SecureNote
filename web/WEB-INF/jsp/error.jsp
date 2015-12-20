<%--
    Created by IntelliJ IDEA.
    User: mtackes
    Date: 12/19/15
    Time: 1:38 PM
    To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Failed to retrieve note</title>
</head>
<body>
<h1>Error</h1>
<h3 style="color: red">${errorMessage}</h3>
<c:choose>
    <c:when test="${not empty header['referer']}">
        <p><a href="${header["referer"]}">Go back</a> and try again, or <a href="/">start over</a>.</p>
    </c:when>
    <c:otherwise>
        <p>Try again, or <a href="/">start over</a>.</p>
    </c:otherwise>
</c:choose>
</body>
</html>
