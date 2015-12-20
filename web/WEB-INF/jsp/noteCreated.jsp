<%--
    Created by IntelliJ IDEA.
    User: mtackes
    Date: 12/18/15
    Time: 3:29 PM
    To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="rootUrl">${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}</c:set>
<html>
<head>
    <title>Note Created</title>
</head>
<body>
    <h1>Note Created</h1>
    <p>Your note was successfully created.</p>
    <p>Retrieve it with the ID <strong><code>${uuid}</code></strong> or at the url <a href="/note/${uuid}">${rootUrl}/note/${uuid}</a>.</p>
    <br />
    <p><strong>Keep this ID and your password safe!</strong> There is no recovering lost notes or passwords.</p>
</body>
</html>
