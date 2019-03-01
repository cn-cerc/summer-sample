<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>增加范例</title>
</head>
<body>

    <%@ include file="head.jspf" %>

    <form method="post" action="FrmPartInfo.append">
      <div>
            <label>账套</label>
            <input id=corpNo name="corpNo" value="${param.corpNo}" />
        </div>
        
        <div>
            <label>编号</label>
            <input id=code name="code" value="${param.code}" />
        </div>
        
        <div>
            <label>名称</label>
            <input id=desc name="desc" value="${param.desc}" />
        </div>
        
        <div>
        	<label>规格</label>
            <input id=spec name="spec" value="${param.spec}">
        </div>

        <div>
            <label>单位</label>
            <input id="unit" name="unit" value="${param.unit}" />
        </div>
        
        <div>
            <label>备注</label>
            <input id="remark" name="remark" value="${param.remark}" />
        </div>
        <button name="submit" value="append">保存</button>
    </form>
</body>
</html>