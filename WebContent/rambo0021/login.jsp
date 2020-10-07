<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%-- 	<img src="<%=application.getContextPath()%>/Homepage" method="get" /> --%>
	<FORM ACTION="LoginServlet" >
		<div id='content' align="center">
			<Table
				style="border-width: 2; background: #E0E0E0; width: 500px; border-style: inset">
				<tr>
					<td>帳號: <INPUT TYPE="TEXT" NAME="userName">
					</td>
				</tr>
				<tr>
					<td><BR> 密碼: <INPUT TYPE="PASSWORD" NAME="password">
						<BR></td>
				</tr>

				<tr>
					<td>
						<CENTER>
							<INPUT TYPE="SUBMIT">
						</CENTER>
					</td>
				</tr>
				<tr>
					<td align="CENTER"><Font color='red' size="-1">
					${ErrorMsgKey.LoginError}</Font>
	</FORM>
	</Table>
	</div>
</body>
</html>