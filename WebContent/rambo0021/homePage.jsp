<%@page import="rambo0021.AccountBean"%>
<%@page import="rambo0021.RegisterDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>個人頁面</title>
<% %>
</head>
<body>
	<table border="1">
		<tr bgcolor="#FFFFE1">
			<td>
			     <c:out value="身分:${account.identity}"/>
			</td>
		</tr>
		<tr bgcolor="#FFFFE1">
			<td><c:out  value="照片:${account.picture}"/></td>
		</tr>
		<tr bgcolor="#FFFFE1">
			<td><c:out  value="帳號:${account.userName}"/></td>
		</tr>
		<tr bgcolor="#FFFFE1">
			<td><c:out  value="密碼:${account.password}"/></td>
		</tr>
		<tr bgcolor="#FFFFE1">
			<td><c:out value="暱稱:${account.nickName}"/></td>
		</tr>
		<tr bgcolor="#FFFFE1">
			<td><c:out  value="email:${account.email}"/></td>
		</tr>
		<tr bgcolor="#FFFFE1">
			<td><c:out  value="註冊日期:${account.register}"/></td>
		</tr>
		<tr bgcolor="#FFFFE1">
			<td><c:out  value="最後修改日期:${account.modify_Date}"/></td>
		</tr>
	</table>

</body>
</html>