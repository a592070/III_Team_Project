<%@page import="rambo0021.AccountBean"%>
<%@page import="rambo0021.RegisterDAO"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
session=request.getSession(false);
if (session.getAttribute("Login") == null) {
	// 請使用者登入
	response.sendRedirect(response.encodeRedirectURL(
			request.getContextPath() + "/rambo0021/login.jsp"));
	return;
} %>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<title>個人頁面</title>
	
	<script src="https://code.jquery.com/jquery-3.5.1.js"
		integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
	<script src="https://www.w3schools.com/lib/w3.js"></script>

</head>

<body>

	<jsp:include page="/rambo0021/top.jsp" />
	<div align="center">
		<form action="HomePageUpdateServlet">
			<table border="1" width="300px">
				<tr bgcolor="#FFFFE1">
					<td>
						<c:out value="身分:${Login.identityString}" />
					</td>
				</tr>
				<tr bgcolor="#FFFFE1">
					<td> <img width='300px'
							src="${pageContext.request.contextPath}/Homepage?userName=${Login.userName}">
							<input type="hidden" id="picture" name="picture" value="更新照片" accept="image/*" disabled>
						</td>
				</tr>
				<tr bgcolor="#FFFFE1">
					<td>
						<c:out value="帳號:${Login.userName}" />
					</td>
				</tr>
				<tr bgcolor="#FFFFE1">
					<td>密碼:<input type="password" id="password" name="password" value="${Login.password}" disabled />
					</td>
				</tr>
				<tr bgcolor="#FFFFE1">
					<td>暱稱<input type="text" id="nickName" name="nickName" value=":${Login.nickName}" disabled />
					</td>
				</tr>
				<tr bgcolor="#FFFFE1">
					<td>email:<input type="text" id="email" name="email" value="${Login.email}" disabled /></td>
				</tr>
				<tr bgcolor="#FFFFE1">
					<td>
						<c:out value="註冊日期:${Login.register}" />
					</td>
				</tr>
				<tr bgcolor="#FFFFE1">
					<td>
						<c:out value="最後修改日期:${Login.modify_Date}" />
					</td>
				</tr>
			</table>
			<input type="button" value="修改" id="update" name="update">
			<input type="hidden" value="儲存" id="submit" name="submit">
			<input type="hidden" value="返回" id="exit" name="exit">
		</form>
	</div>
	<script>
		$("#update").click(function(){
			$("#update").prop("type","hidden");
             $("#submit").prop("type","submit");
             $("#exit").prop("type","button");
			 $("#password").attr("disabled",false);
			 $("#nickName").attr("disabled",false);
			 $("#email").attr("disabled",false);
			 $("#picture").attr("disabled",false).prop("type","file");
		})
		$("#exit").click(function(){
			$("#submit").prop("type","hidden");
			$("#exit").prop("type","hidden");
			$("#update").prop("type","button");
			$("#password").attr("disabled",true);
			 $("#nickName").attr("disabled",true);
			 $("#email").attr("disabled",true);
			 $("#picture").attr("disabled",true).prop("type","hidden");
		})
	</script>

</body>

</html>