<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GoodBye</title>
    <script src="${pageContext.servletContext.contextPath}/static/jquery-3.5.1.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<style>
* {
	margin: 0;
	padding: 0;
}

#box {
	width: 500px;
	margin: 50px auto;
}
h3{
	float:left;
	margin-left: 10px;
	padding-bottom: 20px;
}
.btn.btn-success.home{
	margin-left: 40px;
}
</style>
</head>
<body>
<jsp:include page="/fragment/header.jsp" />

	<div id="box">
		<h3>訂單已取消，歡迎再次下訂</h3>
		<FORM  action="<c:url value='/iring29/Restaurant_index.jsp' />" method="POST">
			<button class="btn btn-primary">回訂餐首頁</button>
		</FORM>
		<div>
			<img src="https://i.gifer.com/5Tl.gif" alt="">
		</div>
		
		
		
	</div>
</body>
</html>