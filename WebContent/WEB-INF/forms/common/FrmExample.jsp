<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>欢迎学习summer框架</title>
</head>
<body>

    <%@ include file="head.jspf" %>

    <form method="post" action="FrmExample">
        <label id="searchText">条件</label>
        <input id="searchText" name="searchText" value="${param.searchText}" />
        <button>查询</button>
    </form>

    <div><a href="FrmExample.append">增加</a></div>

    <table>
        <tr>
            <th>学号</th>
            <th>姓名</th>
            <th>性别</th>
            <th>年龄</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${dataSet.records}" var="record">
        <tr>
            <td>${record.items.code_}</td>
            <td>${record.items.name_}</td>
            <td>${record.items.sex_}</td>
            <td>${record.items.age_}</td>
            <td><a href="FrmExample.modify?uid=${record.items.code_}">修改</a></td>
        </tr>
        </c:forEach>
    </table>
</body>
</html>