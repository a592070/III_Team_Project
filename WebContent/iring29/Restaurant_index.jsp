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
		Restaurant name : <INPUT TYPE="TEXT" NAME="name">
		<button name="QUERY1" type="SUBMIT" value="QUERY1">QUERY</button>
		<BR>
		Region name : <INPUT TYPE="TEXT" NAME="region">
		<button name="QUERY2" type="SUBMIT" value="QUERY2">QUERY</button>
</body>
</html>