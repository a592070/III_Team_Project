<%@page import="rambo0021.RegisterDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>個人頁面</title>

<% RegisterDAO registerDAO= new RegisterDAO();
String path=request.getServletContext().getRealPath("")+"images";
registerDAO.selectPicture(path);
%>
</head>
<body>
<img src="<%=request.getContextPath()%>/images/123.png">
</body>
</html>