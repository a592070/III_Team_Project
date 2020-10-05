<%@page import="rambo0021.AccountBean"%>
<%@page import="rambo0021.RegisterDAO"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="java.text.SimpleDateFormat" %>


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>個人頁面</title>

<%
SimpleDateFormat dateformat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
AccountBean account=(AccountBean)request.getAttribute("account");
//String register =dateformat.format(account.getRegister());
//String modify_date= dateformat.format(account.getModify_Date());

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
<%=dateformat.format(account.getRegister())%>
</td>
<td>
最後修改日期
<%=dateformat.format(account.getModify_Date())%>
</td>
</tr>
</table>

</body>
</html>