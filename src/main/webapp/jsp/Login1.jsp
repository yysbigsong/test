<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2018/8/15
  Time: 20:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    String username = "";
    String password = "";
    String checked = "";
    Cookie[] c = request.getCookies();
    if (c != null) {
        for (int i = 0; i < c.length; i++) {
            if ("username".equals(c[i].getName())) {
                username = c[i].getValue();
            } else if ("password".equals(c[i].getName())) {
                password = c[i].getValue();
            }
            if (password != null && !password.equals("")) {
                checked = "checked=\"checked\"";
            }
        }
    } else {
        username = " ";
        password = " ";
    }
%>
<div id="qq">
    <form action="loginCheck.jsp" method="post">
        <table>
            <tr>
                <td><input type="text" name="username" value="<%=username%>"/>
                </td>
            </tr>
            <tr>
                <td><input type="password" name="password"
                           value="<%=password%>"/>
                </td>
            </tr>
            <tr>
                <td>记住密码：<input type="checkbox" name="passcookies" <%=checked%>/>
                </td>
                <td><input type="submit" value="登录"/>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
