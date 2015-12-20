<%--
    Created by IntelliJ IDEA.
    User: mtackes
    Date: 12/19/15
    Time: 2:20 AM
    To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Retrieve Note</title>
</head>
<body>
<h1>Retrieve Note</h1>
<form method="POST" action="/note">
    <label for="noteUuid">Note ID</label>
    <br />
    <input type="text" id="noteUuid" name="noteUuid" maxlength="36" size="36" value="${param["noteUuid"]}" />
<script>
    // Only show these elements when JavaScript is enabled
    document.write('    <br />\r\n'
    + '    <label for="notePassword">Password</label>\r\n'
    + '    <br />'
    + '    <input id="notePassword" name="notePassword" type="password" />\r\n');

    document.addEventListener("DOMContentLoaded", function() {
        document.getElementsByTagName("form")[0].addEventListener("submit", function(event) {
            var lightRed = "#FFAAAA";

            var uuidField = document.getElementById("noteUuid");
            var uuidValue = uuidField.value;

            var passwordField = document.getElementById("notePassword");

            var uuidRegEx = /[\da-f]{8}-[\da-f]{4}-[\da-f]{4}-[\da-f]{4}-[\da-f]{12}/i;

            if (!uuidRegEx.test(uuidValue)) {
                // Not a real UUID - stop the request
                uuidField.style.backgroundColor = lightRed;
                event.preventDefault();
                return false;
            }
            uuidField.style.backgroundColor= ""; // Reset color if we got this far

            if (passwordField.value.length === 0) {
                // No password
                passwordField.style.backgroundColor = lightRed;
                event.preventDefault();
                return false;
            }

            // Change the form action to give it a meaningful URL
            this.action = "/note/" + uuidValue;
        });
    });
</script>
    <br />
    <input type="submit" />
</form>
</body>
</html>
