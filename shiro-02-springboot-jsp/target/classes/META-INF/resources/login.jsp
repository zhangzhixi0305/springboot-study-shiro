<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<h1>登录页面</h1>
<form action="${pageContext.request.contextPath}/user/login" method="post">
    <label>
        用户名:
        <input type="text" name="username">
    </label> <br/>
    <label>
        密码 :
        <input type="text" name="password">
    </label> <br>
    <input type="submit" value="登录"> &nbsp;&nbsp;&nbsp;<a href="/register.jsp">没有账号?点我注册</a>
</form>

</body>
</html>