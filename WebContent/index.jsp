<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Student
  Date: 2020/9/26
  Time: 下午 08:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="${pageContext.servletContext.contextPath}/static/jquery-3.5.1.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <jsp:include page="fragment/header.jsp" />
</head>
<body>
<div class="container">
    <div class="col-sm-8">
        <h2>TITLE HEADING</h2>
        <h5>Title description, Dec 7, 2017</h5>
        <div class="fakeimg"><img alt="暫時沒有圖片" src="static/nopic.jpg"></div>
        <p>Some text..</p>
        <p>Sunt in culpa qui officia deserunt mollit anim id est laborum consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco.</p>
        <br>
        <h2>TITLE HEADING</h2>
        <h5>Title description, Sep 2, 2017</h5>
        <div class="fakeimg"><img alt="暫時沒有圖片" src="static/nopic.jpg"></div>
        <p>Some text..</p>
        <p>Sunt in culpa qui officia deserunt mollit anim id est laborum consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco.</p>
    </div>
</div>


<script type="text/javascript">
</script>
</body>
</html>
