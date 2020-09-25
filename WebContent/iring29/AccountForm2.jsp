<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<FORM ACTION="<%=pageContext.getServletContext().getContextPath()%>/HomepageServlet" method="post">
  User Name  :  <INPUT TYPE="TEXT" NAME="username"><BR>
  <CENTER>
    <INPUT NAME="QUERY"  TYPE="SUBMIT" VALUE="QUERY">
    <INPUT NAME="UPDATE" TYPE="SUBMIT" VALUE="UPDATE">
  </CENTER>
</FORM>

</body>
</html>