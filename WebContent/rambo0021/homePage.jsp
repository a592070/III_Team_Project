<%@page import="rambo0021.AccountBean"%>
<%@page import="rambo0021.RegisterDAO"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>個人頁面</title>
</head>
<body>
<jsp:include page="/rambo0021/top.jsp" />

	<table border="1" align="center" width="400px">
		<tr bgcolor="#FFFFE1">
			<td><c:out value="身分:${Login.identityString}" /></td>
		</tr>
		<tr bgcolor="#FFFFE1">
			<td>照片:<img width='300px' 
				src='${pageContext.request.contextPath}/Homepage'></td>
		</tr>
		<tr bgcolor="#FFFFE1">
			<td><c:out value="帳號:${Login.userName}" /></td>
		</tr>
		<tr bgcolor="#FFFFE1">
			<td><c:out value="密碼:${Login.password}" /></td>
		</tr>
		<tr bgcolor="#FFFFE1">
			<td><c:out value="暱稱:${Login.nickName}" /></td>
		</tr>
		<tr bgcolor="#FFFFE1">
			<td><c:out value="email:${Login.email}" /></td>
		</tr>
		<tr bgcolor="#FFFFE1">
			<td><c:out value="註冊日期:${Login.register}" /></td>
		</tr>
		<tr bgcolor="#FFFFE1">
			<td><c:out value="最後修改日期:${Login.modify_Date}" /></td>
		</tr>
	</table>

</body>
</html>