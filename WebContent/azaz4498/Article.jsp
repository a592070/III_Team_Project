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
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
        integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">

    <title>文章頁面</title>
</head>

<body>

    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <a class="navbar-brand" href="#"></a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="#">主頁 <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                        data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        文章分類
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="#">景點</a>
                        <a class="dropdown-item" href="#">美食</a>
                        <a class="dropdown-item" href="#">住宿</a>
                    </div>
                </li>
                <li class="nav-item ">
                    <a class="nav-link " href= aria-disabled="true">撰寫文章</a>
                </li>
            </ul>
        </div>
    </nav>
    <!--media objects-->
    <div class="media pl-5 pt-3">
        
        <img class="mr-3" src="azaz4498\img\iconfinder_8_3898372.png" width="60px" height="60px"
            alt="user image">
        <div class="media-body">
            <h5 class="mt-0">${Article.artTitle}</h5>
            <p>
                <button type="button" class="btn btn-info btn-sm">#${Type.typeName }</button>
                
            </p> 
           
           ${Article.artContent }
           <c:forEach var='comm' items='${Comment}'>
            <div class="media mt-3">
                <a class="pr-3" href="#">
                    <img src="azaz4498\img\iconfinder_8_3898372.png" width="60px" height="60px" alt="Generic placeholder image">
                </a>
                <div class="media-body">
                    <h5 class="mt-0">${comm.comUserId }</h5>
                    ${comm.comContent }
                </div>
            </div>
            </c:forEach>
            
        </div>
    </div>
	<form action="<%=pageContext.getServletContext().getContextPath() %>/NewCommentServlet" name="CommentForm">
		<div class="form-group pl-5">
			<label for="exampleInputEmail1">撰寫評論...</label>
			<textarea class="form-control" name="commentarea" id="commentarea" rows="3"
				aria-describedby="commentHelp"></textarea>
			<small id="commentHelp" class="form-text text-muted">留言請勿包含謾罵以及人身攻擊等字句。</small>
		</div>

		<button type="submit" class="btn btn-primary ml-5" onClick="window.alert('新增評論成功');">送出</button>
	</form>
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
        integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
        crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
        integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV"
        crossorigin="anonymous"></script>
</body>

</html>