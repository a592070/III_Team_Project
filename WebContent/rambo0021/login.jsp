<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<img src="<%=application.getContextPath()%>/Homepage" method="get"/>
 <FORM ACTION="LoginServlet">
  帳號:  <INPUT TYPE="TEXT" NAME="userName"><BR>
  密碼: <INPUT TYPE="PASSWORD" NAME="password"><BR>
  <CENTER>
    <INPUT TYPE="SUBMIT">
  </CENTER>
  </FORM>
<
</body>
</html>