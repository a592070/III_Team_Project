<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="a592070.vo.AttractionsInfoVO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.OptionalInt" %>
<%@ page import="java.util.Optional" %><%--
  Created by IntelliJ IDEA.
  User: Student
  Date: 2020/9/26
  Time: 下午 04:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="../fragment/header.jsp" />
    <title>景點清單頁面</title>

    <script src="${pageContext.servletContext.contextPath}/static/jquery-3.5.1.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

    <script type="text/javascript">
        var area;
        <c:if test="${! empty param.area}">
             area = ${param.area};
        </c:if>
        var currentPage;
        var totalPage;
        var totalCount;
        var currentArea;
        var currentKeyword;


        function selectPage(nowPage, nowArea, keyword){
            $.post({
                url: "${pageContext.servletContext.contextPath}/AttractionsInfoServlet",
                data: {"method":"selectPage", "nowPage": nowPage, "area": nowArea, "keyword":keyword},
                success: function (data){
                    let dataJSON = JSON.parse(data);
                    currentPage = dataJSON.currentPage;
                    totalPage = dataJSON.totalPage;
                    totalCount = dataJSON.totalCount;
                    console.log(dataJSON);

                    let paginationContext="";
                    paginationContext += "<li class='page-item'>共"+ totalCount +"條紀錄</li>";
                    paginationContext += "<li class='page-item'>"+currentPage+"/"+totalPage+"</li>";

                    paginationContext += "<li class='page-item previous'><a class='page-link' href='#' onclick='selectPage(1,currentArea, currentKeyword)'>First</li>";
                    paginationContext += "<li class='page-item previous'><a class='page-link' href='#' onclick='selectPage(currentPage-1,currentArea, currentKeyword)'>Previous</a></li>";

                    paginationContext += "<li class='page-item next' ><a class='page-link' href='#' onclick='selectPage(currentPage+1,currentArea, currentKeyword)'>Next</a></li>";
                    paginationContext += "<li class='page-item next'><a class='page-link' href='#' onclick='selectPage(totalPage,currentArea, currentKeyword)'>Last</a></li>";


                    $("#pagination_id").html(paginationContext);


                    let list = JSON.parse(dataJSON.currentPageList);
                    let context="";
                    for (let i = 0; i < list.length; i++) {
                        context += "<tr>"+
                            "<td>" + "<img src='"+list[i].picture+"' class='img-thumbnail' width='304' height='236' onerror=\"this.src='../static/nopic.jpg'\"/>" + "</td>" +
                            "<td>" + list[i].name + "</td>" +
                            "<td>" + list[i].address + "</td>" +
                            // "<td>" + list[i].description + "</td>" +
                            "<td>" + list[i].ticketInfo + "</td>" +
                            "<td><button type='button' class='btn btn-info' onclick='toDetailPage("+list[i].sn+")'>看詳細</button></td>"+
                            "</tr>";
                    }
                    $("#tbody_id").html(context);

                    if(currentPage == 1) {
                        $(".previous").addClass("disabled");
                    }else{
                        $(".previous").removeClass("disabled");
                    }
                    if(currentPage == totalPage) {
                        $(".next").addClass("disabled");
                    }else{
                        $(".next").removeClass("disabled");
                    }
                    $(window).scrollTop(0);
                }
            })
        }
        function jump_to(num){
            var regexp=/^[1-9]\d*$/;
            let totalPageCount = totalPage;
            //alert(totalPageCount);
            if(!regexp.test(num)){
                alert("請輸入大於0的整數！");
                return false;
            }else if((num-totalPageCount) > 0){
                alert("請輸入小於"+totalPageCount+"的整數");
                return false;
            }else{
                selectPage(num, currentArea, currentKeyword);
            }
        }

        var regionList=["臺北市","新北市","桃園市","臺中市","高雄市"]
        function initRegion(){
            $.get({
                url: "${pageContext.servletContext.contextPath}/AttractionsInfoServlet",
                data: {"method": "initPageRegion"},
                success: function (data) {
                    let dataJSON = JSON.parse(data);
                    let list = JSON.parse(dataJSON.regions);
                    let context="";
                    for (let i = 0; i < list.length; i++) {
                        if(regionList.includes(list[i].region)) continue;
                        context += "<a class='dropdown-item' href='#' onclick='selectArea($(this).text())'>"+list[i].region+"</a>";
                    }
                    $("#moreRegion_id").html(context);
                }
            })
        }

        function selectArea(value){
            $("#myInput").val("");
            currentArea = value;
            selectPage(null, value);
        }

        function selectKeyword(value){
            currentKeyword = value;
            selectPage(null, null, currentKeyword);
        }


        function toDetailPage(sn){
            console.log(sn);
            document.location.href="${pageContext.servletContext.contextPath}/AttractionDetailServlet?attractionSn="+sn;
        }
    </script>

</head>
<body>

<script type="text/javascript">
    initRegion();
    selectPage(currentPage,  area);
</script>
<div class="container">
    <h2>選地區</h2>
    <div class="btn-group" id="defaultRegion_id">
        <button type="button" class="btn btn-primary" onclick="selectArea(null)">全部</button>
        <button type="button" class="btn btn-primary" onclick="selectArea($(this).text())">臺北市</button>
        <button type="button" class="btn btn-primary" onclick="selectArea($(this).text())">新北市</button>
        <button type="button" class="btn btn-primary" onclick="selectArea($(this).text())">桃園市</button>
        <button type="button" class="btn btn-primary" onclick="selectArea($(this).text())">臺中市</button>
        <button type="button" class="btn btn-primary" onclick="selectArea($(this).text())">高雄市</button>
        <div class="btn-group">
            <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
                看更多
            </button>
            <div class="dropdown-menu" id="moreRegion_id">
                <a class='dropdown-item' href='#' onclick='selectArea($(this).text())'>null</a>
            </div>
        </div>
    </div>
</div>

<div class="container">

    <br>
    <input class="form-control" id="myInput" type="text" placeholder="Search...本頁搜尋" />
    <button class="btn btn-success" type="button" onClick='selectKeyword($("#myInput").val())'>全部搜尋</button>
<%--    onblur="selectKeyword($(this).val())"--%>
    <br>
    <table class="table">
        <thead>
        <tr>
            <th>圖片</th>
            <th>名稱</th>
            <th>地址</th>
            <th>票價</th>
        </tr>
        </thead>
        <tbody id="tbody_id">
        </tbody>
    </table>
</div>
<div class="container">
    <ul class="pagination" id="pagination_id">
    </ul>

    <div class="navbar navbar-expand-sm">
        <form class="form-inline">
            <input type="text" class="form-control mr-sm-2" placeholder="跳轉頁面" id="inputPage" />
            <button class="btn btn-success" type="button" onClick='jump_to($("#inputPage").val())'>Go</button>
        </form>
    </div >
</div>
<script>
    $(document).ready(function(){
        $(".dropdown-toggle").dropdown();
    });
    $(document).ready(function(){
        $("#myInput").on("keyup", function() {
            var value = $(this).val().toLowerCase();
            $("#tbody_id tr").filter(function() {
                $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
            });
        });
    });
</script>
</body>
</html>
