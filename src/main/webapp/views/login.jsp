<%--
  Created by IntelliJ IDEA.
  dto.User: alo_n
  Date: 09/11/2018
  Time: 19:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login page</title>
</head>
<body>
    <div>
        Please login to see juicy details about students ( ͡° ͜ʖ ͡°)<br>

        <form method="POST">
            <input type="text" name="username" placeholder="Username">
            <input type="password" name="password" placeholder="Password"><br>
            <input type="submit" name="login" value="Login"><br>
            <input type="submit" name="register" value="Register">
        </form>
        <%
            String error = (String) request.getAttribute("showMessage");
            if (error != null) {
                out.println(error + "<br>");
            }
        %>
    </div>
</body>
</html>
