<%--
  Created by IntelliJ IDEA.
  dto.User: alo_n
  Date: 06/11/2018
  Time: 18:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<script>
    window.addEventListener("beforeunload", function(){
        $.get("${request.contextPath}/logout");
    });
</script>

<head>
    <title>NewUser</title>
</head>
<body>
    <h1>Enter new student info: </h1>
    <form method="POST">
        <input type="text" name="studentName" placeholder="Name">
        <input type="number" name="grade" placeholder="Grade">
        <input type="submit" value="Submit">
    </form>
</body>
</html>
