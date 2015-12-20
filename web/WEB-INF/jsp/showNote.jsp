<%--
    Created by IntelliJ IDEA.
    User: mtackes
    Date: 12/19/15
    Time: 2:05 PM
    To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${not empty note.title ? note.title : note.uuid}</title>
</head>
<body>
    <c:if test="${not empty note.title}">
    <h1>${note.title}</h1>
    </c:if>
    <h3><code>${note.uuid}</code></h3>
    <pre>${note.contentString}</pre>
    <form method="POST" action="/edit/${note.uuid}" style="display: inline;">
        <input type="hidden" name="noteUuid" value="${note.uuid}" />
        <input type="hidden" name="noteTitle" value="${note.title}" />
        <input type="hidden" name="noteBody" value="${note.contentString}" />
        <input type="submit" value="Edit" />
    </form>
    <form method="POST" action="/delete/${note.uuid}" style="display: inline;">
        <input type="hidden" name="noteUuid" value="${note.uuid}" />
        <input type="hidden" name="noteTitle" value="${note.title}" />
        <input type="submit" value="Delete" />
    </form>
</body>
</html>
