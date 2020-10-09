<%@ page import="a592070.pojo.AttractionDO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2020/10/9
  Time: 下午 04:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>景點頁面</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>



</head>
<body>
<jsp:include page="../fragment/header.jsp" />

    <%AttractionDO attraction = (AttractionDO)request.getAttribute("attraction");%>
    <div class="jumbotron container">
        <h1><%=attraction.getName()%></h1>
        <div class="card w-100">
        <img class="card-img-top w-80" src="<%=attraction.getPicture()%>" onerror="this.src='${pageContext.servletContext.contextPath}/static/nopic.jpg'" >
        <hr>
        <p class="card-text"><%=attraction.getToldescribe()%></p>
        </div>
    </div>
<%--    <p><%=attraction.getDescription()%></p>--%>
<%--    <p>This is another text.</p>--%>

    <div  class="container">
        <ul class="list-group list-group-flush">地址
            <li class="list-group-item"><%=attraction.getAddress()%></li>
            <li class="list-group-item">map</li>
            <li class="list-group-item">
                <!--
                width = 寬度
                height = 高度
                q = 地址或經緯度，如果需要標明可在結尾加上()，於()中輸入表示名稱
                z = 地圖比例大小，可輸入數值為 1-18，值愈大地圖顯示比例愈大
                t = 地圖顯示模式，沒輸入值時為預設地圖；h為衛星圖加路線；p為地形圖
                hl = 地圖語系，語言代碼參考 https://developers.google.com/maps/faq?hl=zh-tw#languagesupport
                output=embed 指定Google Map為崁入模式
                -->
                <iframe
                        width="100%"
                        height="300"
                        frameborder="0"
                        src="https://www.google.com/maps?q=<%=attraction.getPy()%>,<%=attraction.getPx()%>&mrt=all&hl=zh-TW&z=15&output=embed ">
                </iframe>
            </li>
        </ul>
        <ul class="list-group list-group-flush">電話
            <li class="list-group-item"><%=attraction.getTel()%></li>
            <li class="list-group-item"></li>
        </ul>
        <ul class="list-group list-group-flush">門票
            <li class="list-group-item"><%=attraction.getTicketInfo()%></li>
            <li class="list-group-item"></li>
        </ul>
        <ul class="list-group list-group-flush">開放時間
            <li class="list-group-item"><%=attraction.getOpenTime()%></li>
            <li class="list-group-item"></li>
        </ul>
        <ul class="list-group list-group-flush">旅遊資訊
            <li class="list-group-item"><%=attraction.getTravellingInfo()%></li>
            <li class="list-group-item"></li>
        </ul>
        <ul class="list-group list-group-flush">備註
            <li class="list-group-item"><%=attraction.getRemarks()%></li>
            <li class="list-group-item"></li>
        </ul>
    </div>

</body>
</html>
