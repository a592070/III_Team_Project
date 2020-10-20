<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="azaz4498.ArticleDO"%>
<%@ page import="azaz4498.CommentDO"%>
<%@ page import="azaz4498.ArticleTypeDO"%>
<%@ page import="azaz4498.ForumDAO2"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
        integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">

    <title>文章編輯</title>
</head>

<body>
<jsp:include page="/fragment/header.jsp"/>
 <jsp:include page="/azaz4498/ForumHeader.jsp"/>

    <form action="<c:url value='/NewArticleServlet'/>"name="ArticleForm">
        <div class="form-group pl-5 pt-3">
          <label for="article_title">文章標題</label>
          <input type="text" class="form-control" id="article_title" name="article_title" placeholder="請輸入文章標題">
        </div>
        <div class="form-group pl-5">
          <label for="article_type_select">文章分類</label>
          <select class="form-control" id="article_type_select" name="article_type_select">
            <option value="1">旅遊</option>
            <option  value="2">住宿</option>
            <option  value="3">美食</option>
            <option value="4">景點</option>
          </select>
        </div>
        
        <div class="form-group pl-5">
          <label for="article_content">本文</label>
          <textarea class="form-control "name="article_content" id="article_content"" rows="3"></textarea>
        </div>
        <div style="height:100px;">
        <button type="submit" class="btn btn-primary ml-5">送出</button>
        </div>
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