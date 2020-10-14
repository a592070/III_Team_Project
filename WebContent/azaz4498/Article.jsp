
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
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
	crossorigin="anonymous">

<title>文章頁面</title>
</head>

<body>
	<jsp:include page="/fragment/header.jsp" />
	<jsp:include page="/azaz4498/ForumHeader.jsp" />
	<!--media objects-->
	<div class="media pl-5 pt-3">


		<img class="mr-3" src="azaz4498\img\iconfinder_8_3898372.png"
			width="60px" height="60px" alt="user image">
		<div class="media-body">

			<h5 class="mt-0">${Article.artTitle}</h5>
			<h6>作者:${Article.artUserId }</h6>

			<p>

				<a class="btn btn-primary" role="button"
					href="<c:url value='/SelectArticleType?typeId=${Type.typeId }' />">#${Type.typeName
					}</a>

			</p>


			${Article.artContent }
			<c:forEach var='comm' items='${Comment}'>
				<div class="media mt-3">
					<a class="pr-3" href="#"> <img
						src="azaz4498\img\iconfinder_8_3898372.png" width="60px"
						height="60px" alt="Generic placeholder image">
					</a>
					<div class="media-body">

						<h5 class="mt-0">${comm.comUserId }</h5>
						${comm.comContent }
					</div>
				</div>

			</c:forEach>

		</div>
	</div>
	<% 
	if (session.getAttribute("Login") != null) {
	%>
	<form action="<c:url value='/NewCommentServlet'/> " name="CommentForm">
		<div class="form-group pl-5">
			<label for="exampleInputEmail1">撰寫評論...</label>

			<textarea class="form-control" name="commentarea" id="commentarea"
				rows="3" aria-describedby="commentHelp" placeholder=""></textarea>
			<small id="commentHelp" class="form-text text-muted">留言請勿包含謾罵以及人身攻擊等字句。</small>
		</div>
		<div style="height: 200px;">
			<!-- 有問題 -->
			<button type="submit" class="btn btn-primary ml-5">送出</button>
		</div>
	</form>
	<%
	}else{
	%>
	<form action="<c:url value='/NewCommentServlet'/> " name="CommentForm">
		<div class="form-group pl-5">
			<label for="exampleInputEmail1">撰寫評論...</label>

			<textarea class="form-control" name="commentarea" id="commentarea"
				rows="3" aria-describedby="commentHelp" placeholder="請先登入以發表評論..."></textarea>
			<small id="commentHelp" class="form-text text-muted">留言請勿包含謾罵以及人身攻擊等字句。</small>
		</div>
		<div style="height: 200px;">
			<!-- 有問題 -->
			<button type="submit" class="btn btn-secondary ml-5" disabled>送出</button>
		</div>
	</form>
	<%
	}
	%>
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