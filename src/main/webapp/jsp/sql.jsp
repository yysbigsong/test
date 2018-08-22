<%@ page import="java.util.Date" %>
<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2018/8/17
  Time: 14:44
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>sql</title>
    <%
        Date nowdate = new Date();
        int userid = 1;
        String addName = "王五";
        String delName = "马六";
        String sex = "男";
        int age = 18;
        String address = "中国";
        String phone = "10086";
        request.setAttribute("nowdate", nowdate);
        request.setAttribute("userid", userid);
        request.setAttribute("addName",addName);
        request.setAttribute("delName",delName);
        request.setAttribute("sex",sex);
        request.setAttribute("age",age);
        request.setAttribute("address",address);
        request.setAttribute("phone",phone);
    %>
</head>
<body>
<sql:setDataSource var="rain" driver="com.mysql.jdbc.Driver" url="jdbc:mysql://localhost:3306/qd" user="root" password="123456"/>

<!--将用户年龄加3-->
<sql:update dataSource="${rain}" var="updatecount">
    update user set age =age+?
    <c:set value="3" var="count"/>
    <sql:param value="${count}"/>
</sql:update>

<sql:update dataSource="${rain}" var="updatecount2">
    update user set createtime=? where id=?
    <sql:dateParam value="${nowdate}" type="timestamp"/>
    <sql:param value="${userid}"/>
</sql:update>

<sql:update dataSource="${rain}" var="updatecount3">
    insert into user(name,sex,age,phone,address,createtime) values(?,?,?,?,?,?)
    <sql:param value="${addName}"/>
    <sql:param value="${sex}"/>
    <sql:param value="${age}"/>
    <sql:param value="${phone}"/>
    <sql:param value="${address}"/>
    <sql:dateParam value="${nowdate}" type="timestamp"/>
</sql:update>

<sql:update dataSource="${rain}" var="updatecount4">
    delete from user where name=?
    <sql:param value="${delName}"/>
</sql:update>

<!--查询数据-->
<sql:query var="result" dataSource="${rain}" sql="SELECT * FROM user;"></sql:query>


<!--显示数据-->
<table border="1" width="100%">
    <tr>
        <td colspan="7" align="center">
            共查询到${result.rowCount}条用户记录
        </td>
    </tr>
    <tr>
        <th>用户ID</th>
        <th>用户姓名</th>
        <th>用户性别</th>
        <th>用户年龄</th>
        <th>联系电话</th>
        <th>地址</th>
        <th>创建日期</th>
    </tr>
    <c:forEach var="user" items="${result.rows}">
        <tr>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.sex}</td>
            <td>${user.age}</td>
            <td>${user.phone}</td>
            <td>${user.address}</td>
            <td><fmt:formatDate value="${user.createtime}" var="formatUsertime"/>${formatUsertime}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
