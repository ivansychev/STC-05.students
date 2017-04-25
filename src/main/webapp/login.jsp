<%--
  Created by IntelliJ IDEA.
  User: ivans
  Date: 18/04/2017
  Time: 15:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form action="/students/" method="post">
    <input type="text" name="login"/>
    <input type="text" name="password"/>
    <input type="submit" value="login"/>
</form>
<form method="get"><button value="addStu"></button></form>
</body>
</html>
