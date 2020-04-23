<%--
  Created by IntelliJ IDEA.
  User: 96556
  Date: 2020/4/23
  Time: 14:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="user/userlogin" method="post">
    <h1>用户登陆页面</h1>
    <hr/>
    <table align="left">
        <tr>
            <td>账号：</td>
            <td><input type="text" name="name" id="name"></td>
        </tr>
        <tr>
            <td>密码：</td>
            <td><input type="password" name="password" id="password"></td>
        </tr>
        <tr>
            <td colspan="1">
            </td>
            <td>
                <input type="submit" value="登陆"/>
<%--                <input type="reset" value="重置"/>--%>
                <a href="register.jsp" target="_blank">注册</a>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
