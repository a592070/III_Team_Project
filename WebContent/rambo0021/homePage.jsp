<%@page import="rambo0021.AccountDO"%>
<%@page import="rambo0021.RegisterDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.SimpleDateFormat;;"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>個人頁面</title>

<% 
RegisterDAO registerDAO= new RegisterDAO();
SimpleDateFormat dateformat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
String path=request.getServletContext().getRealPath("")+"images";
ArrayList<AccountDO> list=registerDAO.selectUserData(path);
AccountDO account=(AccountDO)list.get(0);
//String identity=String.valueOf(account.getIdentity());
String username=account.getUserName();
String password=account.getPassword();
String nickname=account.getNickName();
String email=account.getEmail();
String register =dateformat.format(account.getRegister());
String modify_date= dateformat.format(account.getModify_Date());
%>
</head>
<body>
<table>
<tr bgcolor="#FFFFE1">
<td>
身分:
<%=String.valueOf(account.getIdentity())%>
</td>
<td>
照片:
<img src="<%=request.getContextPath()%>/images/""<%=username%>.png">
</td>
<td>
帳號:
</td>
<td>
密碼:
</td>
<td>
暱稱:
</td>
<td>
email:
</td>
<td>
註冊日期
</td>
<td>
最後修改日期
</td>


</tr>
</table>

</body>
</html>