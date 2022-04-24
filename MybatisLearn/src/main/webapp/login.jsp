<%--
  Created by IntelliJ IDEA.
  User: git
  Date: 2022/4/13
  Time: 10:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h3>SSM整合案例之登录</h3>
    <hr>
    <form action="/user/login" >
        用户名: <input type="text" name="uname" value=""><br>
        密码: <input type="password" name="pwd" value=""<br>
        <input type="submit" value="登录">
    </form>

</body>
</html>
