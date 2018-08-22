<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %><%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2018/8/14
  Time: 20:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%
    Map<String, String> map = new HashMap<String, String>();
    map.put("张三", "123456");
    map.put("李四", "123456");
    map.put("王五", "123456");
    request.setAttribute("map", map);

%>
<html>
<head>
    <title>login</title>
</head>
<body>

<c:choose>
<c:when test="${(param.password eq map.get(param.username) )&& param.password != null && param.username!=null}">
a=${param.password}<br/>
b=${param.username}
<table border="1" align="center">
    <tr width="50px">
        <th>一</th>
        <th>二</th>
        <th>三</th>
        <th>四</th>
        <th>五</th>
    </tr>
    <tr>
        <td>嵌入式系统</td>
        <td>编译原理</td>
        <td>编译原理</td>
        <td>编译原理</td>
        <td>计算机图形学</td>
    </tr>
    <tr>
        <td>嵌入式系统</td>
        <td>编译原理</td>
        <td>编译原理</td>
        <td>编译原理</td>
        <td>计算机图形学</td>
    </tr>
    <tr>
        <td></td>
        <td>计算机图形学</td>
        <td>编译原理</td>
        <td>编译原理</td>
        <td></td>
    </tr>
    <tr>
        <td></td>
        <td>计算机图形学</td>
        <td>编译原理</td>
        <td>编译原理</td>
        <td></td>
    </tr>
    <tr>
        <td>大学生职业发展与就业指导</td>
    </tr>
    <table>
        </c:when>
        <c:otherwise>
        <form action="${pageContext.request.requestURI}" method="post">
            账号：
            <input type="text" name="username"><br/>
            密码:
            <input type="text" name="password"><br/>
            <input type="submit" value="登录">
        </form>
        </c:otherwise>
        </c:choose>
</body>
</html>
