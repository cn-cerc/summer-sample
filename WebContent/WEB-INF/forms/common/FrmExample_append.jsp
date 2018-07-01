<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>增加范例</title>
</head>
<body>
    <div>${message}</div>
    <form method="post" action="FrmExample.append">
        <div>
            <label>姓名</label>
            <input id="name" name="name" value="${param.name}" />
        </div>

        <div>
            <label>性别</label>
            <select id="sex" name="sex">
                <option value="0">男</option>
                <option value="1">女</option>
            </select>
        </div>

        <div>
            <label>年龄</label>
            <input id="age" name="age" value="${param.age}" />
        </div>
        <button name="submit" value="append">保存</button>
    </form>
</body>
</html>