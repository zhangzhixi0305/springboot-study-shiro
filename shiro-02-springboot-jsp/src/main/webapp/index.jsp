<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
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
<h2>系统主页v1.0(受限页面)</h2>
<label>
    <a href="${pageContext.request.contextPath}/user/logout">退出登录</a>
</label>
<%--获取当前登录的用户名--%>
<shiro:authenticated>
    <%--填写的是放在认证，SimpleAuthenticationInfo中的user数据--%>
    <h3>
        当前登录用户是：<shiro:principal property="username"/>
    </h3>
</shiro:authenticated>
<shiro:hasAnyRoles name="user,admin">
    <li><a href="">用户管理(满足user/admin权限)</a>
        <ul>
            <shiro:hasPermission name="user:add:*">
                <li><a href="">添加</a></li>
            </shiro:hasPermission>
            <shiro:hasPermission name="user:delete:*">
                <li><a href="">删除</a></li>
            </shiro:hasPermission>
            <shiro:hasPermission name="user:update:*">
                <li><a href="">修改</a></li>
            </shiro:hasPermission>
            <shiro:hasPermission name="order:find:*">
                <li><a href="">查询</a></li>
            </shiro:hasPermission>
        </ul>
    </li>
</shiro:hasAnyRoles>
<hr>

<shiro:hasRole name="admin">
<li><a href="">用户管理(满足admin权限)</a>
    <ul>
        <li><a href="">商品管理</a></li>
        <li><a href="">订单管理</a></li>
        <li><a href="">物流管理</a></li>
    </ul>
    </shiro:hasRole>
</body>
</html>