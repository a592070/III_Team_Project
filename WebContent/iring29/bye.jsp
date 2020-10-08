<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GoodBye</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
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

<header class="">
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
			<a class="navbar-brand" href="javascript:void(0)">Logo</a>
			<button class="navbar-toggler navbar-toggler-right" type="button"
				data-toggle="collapse" data-target="#navb">
				<span class="navbar-toggler-icon"></span>
			</button>


			<div class="collapse navbar-collapse  nav justify-content-center"
				id="navb">
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link"
						href="javascript:void(0)">找旅館</a></li>
					<li class="nav-item"><a class="nav-link"
						href="javascript:void(0)">找餐廳</a></li>
					<li class="nav-item"><a class="nav-link"
						href="javascript:void(0)">找租車</a></li>
					<li class="nav-item"><a class="nav-link"
						href="javascript:void(0)">找景點</a></li>

					<li class="nav-item"><a class="nav-link disabled"
						href="javascript:void(0)">Disabled</a></li>
				</ul>
			</div>
			<div>
				<form class="form-inline my-3">
					<input class="form-control mr-sm-2" type="text"
						placeholder="Search" />
					<button class="btn btn-success my-2 my-sm-0" type="button">Search</button>
				</form>
			</div>
			<div class="btn-group">
				<button type="button" class="btn btn-primary">登陸</button>
				<button type="button" class="btn btn-primary">註冊</button>
				<button type="button" class="btn btn-primary">論壇</button>
			</div>
		</nav>
	</header>

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