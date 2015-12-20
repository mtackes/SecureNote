<%--
    Created by IntelliJ IDEA.
    User: mtackes
    Date: 12/19/15
    Time: 9:07 AM
    To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Retrieve Note</title>
</head>
<body>
<h1>Retrieve Note</h1>
<form method="POST" action="/note/${uuid}">
    <label for="noteUUID">Note UUID</label>
    <br />
    <input type="text" id="noteUUID" maxlength="36" size="36" value="${uuid}" disabled="disabled" />
    <br />
    <label for="notePassword">Password</label>
    <br />
    <input id="notePassword" name="notePassword" type="password" autofocus="autofocus" />

    <input type="submit" />
</form>
<script>
    document.addEventListener("DOMContentLoaded", function() {
        document.getElementsByTagName("form")[0].addEventListener("submit", function(event) {
            var lightRed = "#FFAAAA";

            var passwordField = document.getElementById("notePassword");

            if (passwordField.value.length === 0) {
                // No password
                passwordField.style.backgroundColor = lightRed;
                event.preventDefault();
                return false;
            }
        });
    });
</script>
</body>
</html>
