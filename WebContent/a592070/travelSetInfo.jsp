<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2020/10/9
  Time: 下午 09:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>選擇行程頁面</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

    <script type="text/javascript">
        var currentPage;
        var totalPage;
        var totalCount;
        var currentType;

        var listCar = [];
        var listHotel = [];
        var listRestaurant = [];

        function optionSelected(type){
            <%--$("#car_id").html(<jsp:include page="optionCar.jsp"/>)--%>
            if(type == "car"){
                currentPage = null;
                currentType="optionCar";
            }else if(type == "hotel"){
                currentPage = null;
                currentType="optionHotel";
            }else if(type == "restaurant"){
                currentPage = null;
                currentType="optionRestaurant";
            }
            selectPage(currentPage, currentType);
        }


        function selectPage(nowPage, optionType){
            $.get({
                url: "${pageContext.servletContext.contextPath}/TravelSetServlet",
                data:{"method":optionType, "nowPage": nowPage},
                success: function (data) {
                    let dataJSON = JSON.parse(data);
                    currentPage = dataJSON.currentPage;
                    totalPage = dataJSON.totalPage;
                    totalCount = dataJSON.totalCount;
                    // console.log(dataJSON);

                    let paginationContext="";
                    paginationContext += "<li class='page-item page-link disabled'>共"+ totalCount +"條紀錄</li>";
                    paginationContext += "<li class='page-item page-link disabled'>"+currentPage+"/"+totalPage+"</li>";

                    paginationContext += "<li class='page-item  previous'><a class='page-link' href='#' onclick='selectPage(1,currentType)'>First</li>";
                    paginationContext += "<li class='page-item  previous'><a class='page-link' href='#' onclick='selectPage(currentPage-1,currentType)'>Previous</a></li>";

                    paginationContext += "<li class='page-item  next' ><a class='page-link' href='#' onclick='selectPage(currentPage+1,currentType)'>Next</a></li>";
                    paginationContext += "<li class='page-item  next'><a class='page-link' href='#' onclick='selectPage(totalPage,currentType)'>Last</a></li>";


                    $("#pagination_id").html(paginationContext);

                    let list = JSON.parse(dataJSON.currentPageList);
                    let contextHead="";
                    let contextBody="";
                    if(optionType == "optionCar") {
                        contextHead += "<tr> <th>車種</th> <th>價格</th> <th>公司</th> </tr>";
                        for (let i = 0; i < list.length; i++) {
                            let tempobj = JSON.stringify(list[i]);
                            // let tempobj = list[i];
                            contextBody += "<tr>"+
                                "<td>" + list[i].carType + "</td>" +
                                "<td>" + list[i].price + "</td>" +
                                "<td>" + list[i].company + "</td>" +
                                "<td><button type='button' class='btn btn-info' onclick='toDetailPage("+list[i].sn+")'>看詳細</button></td>"+
                                "<td><button type='button' class='btn btn-danger' value='"+tempobj+"' onclick='addItem(\""+currentType+"\",$(this).val())'>+</button></td>"+
                                "</tr>";
                        }
                    }else if(optionType == "optionHotel"){
                        contextHead += "<tr> <th>旅館名稱</th> <th>雙人房價格</th> <th>四人房價格</th> <th>地址</th> <th>評分</th> </tr>";
                        for (let i = 0; i < list.length; i++) {
                            let tempobj = JSON.stringify(list[i]);
                            contextBody += "<tr>"+
                                "<td>" + list[i].name + "</td>" +
                                "<td>" + list[i].doubleRoomPrice + "</td>" +
                                "<td>" + list[i].quadrupleRoomPrice + "</td>" +
                                "<td>" + list[i].address + "</td>" +
                                "<td>" + list[i].rating + "</td>" +
                                "<td><button type='button' class='btn btn-info' onclick='toDetailPage("+list[i].sn+")'>看詳細</button></td>"+
                                "<td><button type='button' class='btn btn-danger' value='"+tempobj+"' onclick='addItem(\""+currentType+"\",$(this).val())'>+</button></td>"+
                                "</tr>";
                        }
                    }else if(optionType == "optionRestaurant"){
                        contextHead += "<tr> <th>圖片</th> <th>餐廳名稱</th> <th>類型</th> <th>地址</th> <th>評分</th> </tr>";
                        for (let i = 0; i < list.length; i++) {
                            let tempobj = JSON.stringify(list[i]);
                            contextBody += "<tr>"+
                                "<td>" + "<img src='"+list[i].picture+"' class='img-thumbnail' onerror=\"this.src='../static/nopic.jpg'\"/>" + "</td>" +
                                "<td>" + list[i].name + "</td>" +
                                "<td>" + list[i].type + "</td>" +
                                // "<td>" + list[i].description + "</td>" +
                                "<td>" + list[i].address + "</td>" +
                                "<td>" + list[i].rating + "</td>" +
                                "<td><button type='button' class='btn btn-info' onclick='toDetailPage("+list[i].sn+")'>看詳細</button></td>"+
                                "<td><button type='button' class='btn btn-danger' value='"+tempobj+"' onclick='addItem(\""+currentType+"\",$(this).val())'>+</button></td>"+
                                "</tr>";
                        }
                    }
                    $("#thead_id").html(contextHead);
                    $("#tbody_id").html(contextBody);


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
                $("#inputPage").val("");
                return false;
            }else if((num-totalPageCount) > 0){
                alert("請輸入小於"+totalPageCount+"的整數");
                $("#inputPage").val("");
                return false;
            }else{
                selectPage(num, currentType);
            }
        }

        function toDetailPage(sn){
            console.log(sn);
        }

        function addItem(type, obj){
            let ele;
            let tempArr;
            obj = JSON.parse(obj);
            if(type == "optionCar"){
                listCar.push(obj);
                listCar.sort((x,y) => x.sn-y.sn);
                tempArr = listCar;
                ele = $("#addItemCar");
                let content = "";
                for (let i = 0; i < tempArr.length; i++) {
                    content += "<tr><td>"+tempArr[i].carType+"</td>"+
                        "<td><input type='datetime-local' class='btn btn-secondary'/></td>"+
                        "<td><button type='button' class='btn btn-info'>看詳細</button></td>"+
                        "<td><button type='button' class='btn btn-danger' value='"+i+"' onclick='removeItem(\""+type+"\",$(this).val())'>取消</button></td>"+"</tr>";
                }
                ele.html(content);
                return;
            }else if(type == "optionHotel"){
                listHotel.push(obj);
                listHotel.sort((x,y) => x.sn-y.sn);
                tempArr = listHotel;
                ele = $("#addItemHotel");
            }else if(type == "optionRestaurant"){
                listRestaurant.push(obj);
                listRestaurant.sort((x,y) => x.sn-y.sn);
                tempArr = listRestaurant;
                ele = $("#addItemRestaurant");
            }
            let content = "";
            for (let i = 0; i < tempArr.length; i++) {
                content += "<tr><td>"+tempArr[i].name+"</td>"+
                    "<td><input type='datetime-local' class='btn btn-secondary'/></td>"+
                    "<td><button type='button' class='btn btn-info'>看詳細</button></td>"+
                    "<td><button type='button' class='btn btn-danger' value='"+i+"' onclick='removeItem(\""+type+"\",$(this).val())'>取消</button></td>"+"</tr>";
            }
            ele.html(content);
        }

        function removeItem(type, index){
            let ele;
            let tempArr;
            if(type == "optionCar"){
                listCar.splice(index,1);
                let content = "";
                for (let i = 0; i < listCar.length; i++) {
                    content += "<tr><td>"+listCar[i].carType+"</td>"+
                        "<td><input type='datetime-local' class='btn btn-secondary'/></td>"+
                        "<td><button type='button' class='btn btn-info'>看詳細</button></td>"+
                        "<td><button type='button' class='btn btn-danger' value='"+i+"' onclick='removeItem(\""+type+"\",$(this).val())'>取消</button></td>"+"</tr>";
                }
                $("#addItemCar").html(content);
                return;
            }else if(type == "optionHotel"){
                listHotel.splice(index,1);
                tempArr = listHotel;
                ele = $("#addItemHotel");
            }else if(type == "optionRestaurant"){
                listRestaurant.splice(index,1);
                tempArr = listRestaurant;
                ele = $("#addItemRestaurant");
            }
            let content = "";
            for (let i = 0; i < tempArr.length; i++) {
                content += "<tr><td>"+tempArr[i].name+"</td>"+
                    "<td><input type='datetime-local' class='btn btn-secondary'/></td>"+
                    "<td><button type='button' class='btn btn-info'>看詳細</button></td>"+
                    "<td><button type='button' class='btn btn-danger' value='"+i+"' onclick='removeItem(\""+type+"\",$(this).val())'>取消</button></td>"+"</tr>";
            }
            ele.html(content);
        }

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

</head>
<body>
<jsp:include page="../fragment/header.jsp" />


<div class="container">
    <h2>行程選擇</h2>

    <table class="table">
        <thead>
        <tr>
            <th>項目</th>
            <th></th>
            <th>選項</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>交通</td>
            <td>
                <table class="table">
                    <tbody id="addItemCar"></tbody>
<%--                    <tr>--%>
<%--                        <td>租車1</td>--%>
<%--                        <td><input type="datetime-local" class="btn btn-secondary"/></td>--%>
<%--                        <td><button type="button" class="btn btn-info">看詳細</button></td>--%>
<%--                        <td><button type="button" class="btn btn-danger">取消</button></td>--%>
<%--                    </tr>--%>
                </table>
            </td>

            <td><button type="button" class="btn btn-primary" data-toggle="modal" data-target="#optionModal" onclick="optionSelected('car')">選更多</button></td>
        </tr>
        <tr>
            <td>餐廳</td>
            <td>
                <table class="table">
                    <tbody id="addItemRestaurant"></tbody>
<%--                    <tr>--%>
<%--                        <td>餐廳1</td>--%>
<%--                        <td><input type="datetime-local" class="btn btn-secondary"/></td>--%>
<%--                        <td><button type="button" class="btn btn-info">看詳細</button></td>--%>
<%--                        <td><button type="button" class="btn btn-danger">取消</button></td>--%>
<%--                    </tr>--%>
                </table>
            </td>
            <td><button type="button" class="btn btn-primary" data-toggle="modal" data-target="#optionModal" onclick="optionSelected('restaurant')">選更多</button></td>
        </tr>
        <tr>
            <td>旅館</td>
            <td>
                <table class="table">
                    <tbody id="addItemHotel"></tbody>
<%--                    <tr>--%>
<%--                        <td>旅館1</td>--%>
<%--                        <td><input type="datetime-local" class="btn btn-secondary"/></td>--%>
<%--                        <td><button type="button" class="btn btn-info">看詳細</button></td>--%>
<%--                        <td><button type="button" class="btn btn-danger">取消</button></td>--%>
<%--                    </tr>--%>
                </table>
            </td>

            <td><button type="button" class="btn btn-primary" data-toggle="modal" data-target="#optionModal" onclick="optionSelected('hotel')">選更多</button></td>
        </tr>
        <tr>
            <td>景點</td>
            <td>
                <table class="table">
                    <tbody id="addItemAttraction"></tbody>
<%--                    <tr>--%>
<%--                        <td>景點1</td>--%>
<%--                        <td><input type="datetime-local" class="btn btn-secondary"/></td>--%>
<%--                        <td><button type="button" class="btn btn-info">看詳細</button></td>--%>
<%--                        <td><button type="button" class="btn btn-danger">取消</button></td>--%>
<%--                    </tr>--%>
                </table>
            </td>
            <td><button type="button" class="btn btn-primary" data-toggle="modal" data-target="#optionModal" onclick="optionSelected('attraction')">選更多</button></td>
        </tr>
        </tbody>
    </table>
    <div class="navbar-nav"><button type="submit" class="btn btn-primary">確認</button></div>
</div>




<div class="modal" id="optionModal">
    <div class="modal-dialog modal-xl">
        <div class="modal-content">

            <!-- Modal Header -->
            <div class="modal-header">
<%--                <h1 class="modal-title">租車選擇</h1>--%>
            <input class="form-control" id="myInput" type="text" placeholder="Search..">
            <button type="button" class="close" data-dismiss="modal">×</button>
            </div>


            <!-- Modal body -->
            <div class="modal-body">
                <div class="container" >
                    <table class="table">
                        <thead id="thead_id">
                        </thead>
                        <tbody id="tbody_id">
                        </tbody>
                    </table>
                </div>

            </div>
            <div class="container">
                <form class="form-inline my-2 my-lg-0">
                    <input type="text" class="form-control mr-sm-2" placeholder="跳轉頁面" id="inputPage" />
                    <button class="btn btn-success my-2 my-sm-0" type="button" onClick='jump_to($("#inputPage").val())'>Go</button>
                </form>
                <ul class="pagination" id="pagination_id">
                </ul>
            </div>
            <!-- Modal footer -->
            <div class="modal-footer">

                <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
            </div>

        </div>
    </div>
</div>
<div class="modal" id="optionModalAttraction">
    <div class="modal-dialog modal-xl">
        <div class="modal-content">

            <!-- Modal Header -->
            <div class="modal-header">
                <%--                <h1 class="modal-title">租車選擇</h1>--%>
                <button type="button" class="close" data-dismiss="modal">×</button>
            </div>

            <!-- Modal body -->
            <div class="modal-body">

            </div>

            <!-- Modal footer -->
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
            </div>

        </div>
    </div>
</div>

<script type="text/javascript">
    console.log(currentPage);
</script>
</body>
</html>
