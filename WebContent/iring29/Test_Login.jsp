<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
    <form action="<%=pageContext.getServletContext().getContextPath()%>/Restaurant_HPServlet" method="POST">
        <label for="">Account</label>
        <input type="text" name="username">
        <span class="search"><button name="QUERY" type="SUBMIT" value="USERNAME"> Search</button></span>
    </form>
</body>

</html>