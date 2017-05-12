<%--
  Created by IntelliJ IDEA.
  User: lovi
  Date: 2017.05.11.
  Time: 15:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="webjars/jquery/3.2.0/jquery.min.js"></script>
    <script src="todos.js"></script>
</head>
<body>
<div>
    Welcome <%=session.getAttribute("user")%>!
</div>
<button class="add">Add</button> <%--ide jön az addolos jquery --%>
<input id="inputfield" type="text"> <%--innen ki kell szedni az adatot add nyomásra --%>
<div id="container"></div> <%--ebbe lesz bele az összes task --%>
<button class="all">All</button><button class="done">Done</button><button class="incomplete">Incomplete</button>
<%--összes task                              kész taskok                               incomplete taszkok      --%>
</body>
</html>
