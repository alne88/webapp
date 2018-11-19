<%@ page import="java.util.List" %>
<%@ page import="dto.Student" %>
<%@ page import="servlets.LoginServlet" %>
<%@ page import="servlets.LogoutServlet" %>
<%--
  Created by IntelliJ IDEA.
  dto.Studentent: alo_n
  Date: 05/11/2018
  Time: 19:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List of students</title>
</head>

<script>
    window.addEventListener("beforeunload", function(){
        $.get("${request.contextPath}/logout");
    });
</script>

<body>
    <h1>Greetings</h1>
    <table border="1">
        <tr>
            <th style="width: 150px; text-align: left">Name</th>
            <th style="width: 150px; text-align: left">Grade</th>
        </tr>
    <%
        List<Student> students = (List<Student>) request.getAttribute("students");
        for (Student student : students) {
            out.println("<tr><td>" + student.getStudentName() + "</td><td>" + student.getGrade() + "</td></tr>");
        }
    %>
    </table>

    <p style="margin-top: 20px"><button onclick="location.href='/addstudent'">Add new student</button><br></p>

    <form method="POST" style="margin-top: 20px">
        Sort students by:<br>
        <input type="radio" name="sort" value="name">By name<br>
        <input type="radio" name="sort" value="grade">By grade<br>
        <input type="submit" value="Submit">
    </form>
    <p style="margin-top: 20px"><button onclick="location.href='/logout'">Logout</button><br></p>
</body>
</html>
