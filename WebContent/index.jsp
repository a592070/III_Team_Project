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

<script type="text/javascript">

    $(document).load(loadPage());

    function loadPage(){
        $.get({
            url:"${pageContext.servletContext.contextPath}/TravelSetSelectServlet",
            data:{"system_travelset":"true"},
            success: function (data){
                let json = JSON.parse(data);
                let content = "";
                let historyTravelSets = json.historyTravelSets;
                for (let i = 0; i < historyTravelSets.length; i++) {
                    let obj = historyTravelSets[i];
                    content +=
                        `<h2>\${obj.name}</h2>
                         <h5>\${new Date(obj.updateTime).toLocaleString()}</h5>
                         <div class="fakeimg"><img class="img-thumbnail" alt="暫時沒有圖片" src="static/nopic.jpg"></div>
                         <p>\${obj.description}</p>
                         <p><button type='button' class='btn btn-info' value="\${obj.sn}" onclick='travelSetDetail($(this))'>看詳細</button></p>
                         `;
                }
                $("#system_travelset").html(content);
            }
        })
    }
    function travelSetDetail(obj){
        document.location.href="${pageContext.servletContext.contextPath}/a592070/travelSetInfo.jsp?sn="+obj.val();
    }

</script>
<body>
<div class="container">
    <div class="col-sm-8" id="system_travelset">
        <h2>TITLE HEADING</h2>
        <h5>Title description, Dec 7, 2017</h5>
        <div class="fakeimg"><img alt="暫時沒有圖片" src="static/nopic.jpg"></div>
        <p>Some text..</p>
        <p>Sunt in culpa qui officia deserunt mollit anim id est laborum consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco.</p>
        <br>
    </div>
</div>


<script type="text/javascript">
</script>
</body>
</html>
