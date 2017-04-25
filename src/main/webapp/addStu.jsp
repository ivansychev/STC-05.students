<%--
  Created by IntelliJ IDEA.
  User: ivans
  Date: 20/04/2017
  Time: 23:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Student</title>
</head>
<body>
<div><h1>Add Student</h1></div>
<div>
    <form action="/students/addStu" method="get">
        <input type="text" name="group_id"/>
        <input type="text" name="name"/>
        <input type="text" name="age"/>
    </form>
</div>
<div>
    <% String message = (String) request.getAttribute("value"); %>
    <%=message%>
</div>
</body>
</html>
