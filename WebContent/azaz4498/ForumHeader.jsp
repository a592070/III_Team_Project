<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="azaz4498.ArticleDO"%>
<%@ page import="azaz4498.CommentDO"%>
<%@ page import="azaz4498.ArticleTypeDO"%>
<%@ page import="azaz4498.ForumDAO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

</head>
<body>


	<!-- navbar -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<div class="d-flex flex-grow-1">
			<span class="w-100 d-lg-none d-block"> <!-- hidden spacer to center brand on mobile -->
			</span>

			<div class="w-100 text-right">
				<button class="navbar-toggler" type="button" data-toggle="collapse"
					data-target="#myNavbar7">
					<span class="navbar-toggler-icon"></span>
				</button>
			</div>
		</div>
		<div class="collapse navbar-collapse flex-grow-1 text-right"
			id="forumNavbar">
			<ul class="navbar-nav ml-auto flex-nowrap">
				<li class="nav-item"><a href="<c:url value='/ForumServlet' />"
					class="nav-link" >文章列表</a></li>
				<%
					if (session.getAttribute("Login") != null) {
				%>
				<li class="nav-item"><a
					href=<c:url value='/azaz4498/article_editing.jsp' />
					class="nav-link">撰寫文章</a></li>
				<%
					} else {
				%>
				<li class="nav-item"><a
					href=<c:url value='/rambo0021/login.jsp' />
					class="nav-link" onClick="window.alert('請先登入以發表文章');">撰寫文章</a></li>
				<%
					}
				%>

				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="" id="navbarDropdown"
					role="button" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false"> 文章分類 </a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdown">
						<a class="dropdown-item"
							href=<c:url value='/SelectArticleType?typeId=1' />>旅遊</a> <a
							class="dropdown-item"
							href=<c:url value='/SelectArticleType?typeId=2' />>住宿</a> <a
							class="dropdown-item"
							href=<c:url value='/SelectArticleType?typeId=3' />>美食</a> <a
							class="dropdown-item"
							href=<c:url value='/SelectArticleType?typeId=4' />>景點</a>
					</div></li>
			</ul>
		</div>
	</nav>

</body>
</html>