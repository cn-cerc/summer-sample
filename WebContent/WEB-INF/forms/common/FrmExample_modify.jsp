<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>修改范例</title>
</head>
<body>

    <%@ include file="head.jspf" %>

    <form method="post" action="FrmExample.modify">
        <input type="hidden" id="uid" name="uid" value="${record.items.UID_}">
        <div>
            <label>姓名</label>
            <input id="name" name="name" value="${record.items.name_}" readonly="readonly" />
        </div>

        <div>
            <label>性别</label>
            <input id="sex" name="sex" value="${record.items.sex_}" readonly="readonly" />
        </div>

        <div>
            <label>年龄</label>
            <input id="age" name="age" value="${record.items.age_}" />
        </div>
        <button name="submit" value="append">保存</button>
    </form>
    <div>
        <a href="FrmExample.delete?uid=${record.items.UID_}">删除</a>
    </div>
</body>
</html>