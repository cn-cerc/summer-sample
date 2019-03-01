<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>修改范例</title>
</head>
<body>

    <%@ include file="head.jspf" %>

    <form method="post" action="FrmPartInfo.modify">
        <input type="hidden" id="uid" name="uid" value="${record.items.UID_}">
        <div>
            <label>编号</label>
            <input id="code" name="code" value="${record.items.code_}" readonly="readonly" />
        </div>

        <div>
            <label>名称</label>
            <input id="desc" name="desc" value="${record.items.desc_}" />
        </div>
        <div>
            <label>规格</label>
            <input id="spec" name="spec" value="${record.items.spec_}" />
        </div>
        <div>
            <label>单位</label>
            <input id="unit" name="unit" value="${record.items.unit_}" />
        </div>
        <div>
            <label>备注</label>
            <input id="remark" name="remark" value="${record.items.remark_}" />
        </div>
        <button name="submit" value="append">保存</button>
    </form>
    <div>
        <a href="FrmPartInfo.delete?uid=${record.items.UID_}">删除</a>
    </div>
</body>
</html>