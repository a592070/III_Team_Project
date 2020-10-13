<%@page import="controller.ConnectionPool"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="azaz4498.ArticleDO"%>
<%@ page import="azaz4498.ForumDAO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
	crossorigin="anonymous">
<title>論壇</title>
</head>
<body>
	<jsp:include page="/fragment/header.jsp" />
	<jsp:include page="/azaz4498/ForumHeader.jsp" />


	<!--card group-->
	<div class="row">
		<c:forEach var='art' items='${Article}'>
			<div class="col-sm2">
			<div class="card">
					<div class="card-body">
						<img src='${art.artPic }' class="card-img-top h-75" alt="..." onerror="this.src='../azaz4498/img/sc_01.jpg'">
						<hr>
						<h3 class="card-title">${art.artTitle}</h3>
						<h5 class="card-title"></h5>
						<p class="card-text">
								<small class="text-muted">${art.artCommNum}
								comments ${art.artView} views </small>

							<a
								href="<c:url value='/ArticleServlet?artId=${art.artId}&art_TypeId=${art.artTypeId}'/>"
								class="btn btn-primary float-right">閱讀文章</a> 
						</p>

					</div>
				</div>
			</div>
		</c:forEach>
	</div>
	<div class="container  pt-3"></div>

	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
		integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
		integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
		integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV"
		crossorigin="anonymous"></script>
</body>
</html>