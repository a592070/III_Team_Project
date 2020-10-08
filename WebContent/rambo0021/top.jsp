<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<tabel>
	<tr>
		<c:if test="${empty Login}">
			<td><a href="<c:url value='/rambo0021/register.jsp' />">註冊</a></td>
			<td><a href="<c:url value='/rambo0021/login.jsp' />">登入</a></td>
		</c:if>
		<c:if test="${!empty Login}">
			<td>您好，<c:out value="${Login.nickName}" /><br> <a
				href="<c:url value='/rambo0021/homePage.jsp' />">個人頁面</a>
			</td>
			<td><a href="<c:url value='/Logout' />">登出</a></td>
		</c:if>
		<td><a href="<c:url value='/rambo0021/index.jsp' />">首頁</a></td>
	</tr>


	</tabel>

</body>
</html>


