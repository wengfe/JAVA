<%--
  Created by IntelliJ IDEA.
  User: fuyiweng
  Date: 2019-03-21
  Time: 10:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- JSP page 配置指令 --%>
<html>
<head>
    <title>hello JSP</title>
</head>
<body>
<%! String name; %>
<!-- 变量声明 -->
request uri is <%=request.getRequestURI() %>
<!-- 内置对象的使用 -->
</br>
<%
    name = "abc";
    out.println("name is " + name);
%>
<!-- JSP 脚本 -->
</body>
</html>
