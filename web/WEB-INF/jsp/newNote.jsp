<%--
    Created by IntelliJ IDEA.
    User: mtackes
    Date: 12/15/15
    Time: 3:50 AM
    To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New Note</title>
</head>
<body>
    <h1>New Note</h1>
    <form method="POST" action="/newNote">
        <label for="noteBody">Note Body</label>
        <br />
        <textarea id="noteBody" name="noteBody" rows="10" cols="60">${noteBody}${param["noteBody"]}</textarea>
        <br />
        <label for="notePassword">Password</label>
        <br />
        <input id="notePassword" name="notePassword" type="password" />
    </form>
</body>
</html>
