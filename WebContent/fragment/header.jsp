<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2020/10/9
  Time: 下午 03:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--樣式跑掉，將下面4行加入引入頁面--%>
<%--    <script src="${pageContext.servletContext.contextPath}/static/jquery-3.5.1.js"></script>--%>
<%--    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">--%>
<%--    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>--%>
<%--    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>--%>

<header class="masthead mb-auto">
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <a class="navbar-brand" href="${pageContext.servletContext.contextPath}/index.jsp">Logo</a>

        <div class="collapse navbar-collapse nav justify-content-center" id="navb">
            <ul class="navbar-nav ">
                <li class="nav-item">
                    <a class="nav-link active" href="javascript:void(0)">找旅館</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" href="javascript:void(0)">找餐廳</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" href="javascript:void(0)">找租車</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" href="${pageContext.servletContext.contextPath}/a592070/attractionInfo.jsp">找景點</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link active" href="javascript:void(0)">我的行程</a>
                </li>
            </ul>
        </div>

        <div>
            <form class="form-inline my-3">
                <input class="form-control mr-sm-2" type="text" placeholder="Search"/>
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


