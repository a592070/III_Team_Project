<%@page import="rambo0021.AccountBean"%>
<%@page import="rambo0021.RegisterDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>個人頁面</title>

<%
AccountBean account=(AccountBean)request.getAttribute("account");
%>
</head>
<body>
<table>
<tr bgcolor="#FFFFE1">
<td>
身分:
<%=account.getIdentity()%>
</td>
<td>
照片:
<%=account.getPicture()%>
</td>
<td>
帳號:
<%=account.getUserName()%>
</td>
<td>
密碼:
<%=account.getPassword()%>
</td>
<td>
暱稱:
<%=account.getNickName()%>
</td>
<td>
email:
<%=account.getEmail()%>
</td>
<td>
註冊日期
<%//=account.getRegister()%>
</td>
<td>
最後修改日期
<%//=account.getModify_Date()%>
</td>
</tr>
</table>

</body>
</html>