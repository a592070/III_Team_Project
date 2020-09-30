<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Restaurant</title>
</head>
<body>
	<FORM
		ACTION="<%=pageContext.getServletContext().getContextPath()%>/RestaurantServlet"
		method="post">
		Restaurant name : <INPUT TYPE="TEXT" NAME="name"><BR>
		<button name="QUERY" type="SUBMIT" value="QUERY">QUERY</button>
</body>
</html>