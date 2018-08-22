<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %><%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2018/8/15
  Time: 20:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%
    Map<String, String> map = new HashMap<String, String>();
    map.put("张三", "123456");
    map.put("李四", "123456");
    map.put("王五", "123456");
    request.setCharacterEncoding("utf-8");
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    String remember = request.getParameter("passcookies");
    if (!password.equals(map.get(username))) {
        out.println("<script>alert('账号密码错误');history.go(-1);</script>");
    } else {
        if (remember != null) {
            Cookie c1 = new Cookie("username", username);
            Cookie c2 = new Cookie("password", password);
            c1.setMaxAge(60 * 60 * 12 * 30);
            c2.setMaxAge(60 * 60 * 12 * 30);//这里设置保存这条Cookie的时间
            response.addCookie(c1); //添加Cookie
            response.addCookie(c2);
%>
密码账号已保存<br/>
<%
        } else {
            Cookie c1 = new Cookie("username", username);
            Cookie c2 = new Cookie("password", password);
            c1.setMaxAge(0);
            c2.setMaxAge(0);//这里设置保存这条Cookie的时间
            response.addCookie(c1); //添加Cookie
            response.addCookie(c2);
        }
    }
%>
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
        <td>编译原理</td>4
        <td>编译原理</td>
        <td></td>
    </tr>
    <tr>
        <td>大学生职业发展与就业指导</td>
    </tr>
    </table>
</body>
</html>
