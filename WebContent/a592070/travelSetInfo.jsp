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
    <title>規劃旅程頁面</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

    <style>
        .btn.disabled, .btn:disabled {
            cursor: not-allowed;
        }
    </style>

    <script type="text/javascript">
        const optionCar = "optionCar";
        const optionHotel = "optionHotel";
        const optionRestaurant = "optionRestaurant";
        const optionAttraction = "optionAttraction";

        var currentPage;
        var totalPage;
        var totalCount;
        var currentType;

        var currentArea;

        var listCar = [];
        var listHotel = [];
        var listRestaurant = [];
        var listAttraction = [];
<%--        <c:if test="${!empty sessionScope.CONSTANT.travelSetListCar_session} ">--%>
<%--            listCar= ${sessionScope.travelSetListCar_session};--%>
<%--        </c:if>--%>
<%--        <c:if test="${! empty param.travelSetHotelList}">--%>
<%--            listHotel = ${param.travelSetHotelList};--%>
<%--        </c:if>--%>
<%--        <c:if test="${! empty param.travelSetRestaurantList}">--%>
<%--            listRestaurant = ${param.travelSetRestaurantList};--%>
<%--        </c:if>--%>
<%--        <c:if test="${! empty param.travelSetAttractionList}">--%>
<%--            listAttraction = ${param.travelSetAttractionList};--%>
<%--        </c:if>--%>

        function optionSelected(type){
            <%--$("#car_id").html(<jsp:include page="optionCar.jsp"/>)--%>
            if(type == optionCar){
                currentPage = null;
                currentType=optionCar;
                $("#selectArea_id").addClass("d-none");
            }else if(type == optionHotel){
                currentPage = null;
                currentType=optionHotel;
                $("#selectArea_id").addClass("d-none");
            }else if(type == optionRestaurant){
                currentPage = null;
                currentType=optionRestaurant;
                // $("#selectArea_id").addClass("d-none");
                $("#selectArea_id").removeClass("d-none");
            }else if(type==optionAttraction){
                currentPage = null;
                currentArea = null;
                currentType=optionAttraction;
                $("#selectArea_id").removeClass("d-none");
            }
            selectPage(currentPage, currentType, currentArea);
        }


        function selectPage(nowPage, optionType, nowArea){
            $.get({
                url: "${pageContext.servletContext.contextPath}/TravelSetServlet",
                data:{"method":optionType, "nowPage": nowPage, "area": nowArea},
                success: function (data) {
                    let dataJSON = JSON.parse(data);
                    currentPage = dataJSON.currentPage;
                    totalPage = dataJSON.totalPage;
                    totalCount = dataJSON.totalCount;
                    // console.log(dataJSON);

                    let paginationContext="";
                    paginationContext += "<li class='page-item page-link disabled'>共"+ totalCount +"條紀錄</li>";
                    paginationContext += "<li class='page-item page-link disabled'>"+currentPage+"/"+totalPage+"</li>";

                    paginationContext += "<li class='page-item  previous'><a class='page-link' href='#' onclick='selectPage(1,currentType, currentArea)'>First</li>";
                    paginationContext += "<li class='page-item  previous'><a class='page-link' href='#' onclick='selectPage(currentPage-1,currentType, currentArea)'>Previous</a></li>";

                    paginationContext += "<li class='page-item  next' ><a class='page-link' href='#' onclick='selectPage(currentPage+1,currentType, currentArea)'>Next</a></li>";
                    paginationContext += "<li class='page-item  next'><a class='page-link' href='#' onclick='selectPage(totalPage,currentType, currentArea)'>Last</a></li>";


                    $("#pagination_id").html(paginationContext);

                    let list = JSON.parse(dataJSON.currentPageList);
                    let contextHead="";
                    let contextBody="";
                    if(optionType == optionCar) {
                        contextHead += "<tr> <th>車種</th> <th>價格</th> <th>公司</th> </tr>";
                        for (let i = 0; i < list.length; i++) {
                            let tempobj = JSON.stringify(list[i]);
                            // let tempobj = list[i];
                            contextBody += `<tr>
                                <td>\${list[i].name}</td>
                                <td>\${list[i].price}</td>
                                <td>\${list[i].company}</td>
<!--                                <td><button type='button' class='btn btn-info' onclick='toDetailPage(optionCar,"\${list[i].sn}")'>看詳細</button></td>-->
                                <td><button type='button' class='btn btn-danger' value='\${tempobj}' onclick='addItem(\${currentType},$(this))'>+</button></td>
                                </tr>`;
                        }
                    }else if(optionType == optionHotel){
                        contextHead += "<tr> <th>旅館名稱</th> <th>雙人房價格</th> <th>四人房價格</th> <th>地址</th> <th>評分</th> </tr>";
                        for (let i = 0; i < list.length; i++) {
                            let tempobj = JSON.stringify(list[i]);
                            contextBody += `<tr>"+
                                <td >\${list[i].name}</td>
                                <td>\${list[i].doubleRoomPrice}</td>
                                <td>\${list[i].quadrupleRoomPrice}</td>
                                <td>\${list[i].address}</td>
                                <td>\${list[i].rating}</td>
<!--                                <td><button type='button' class='btn btn-info' onclick='toDetailPage(optionHotel,\${list[i].sn})'>看詳細</button></td>-->
                                <td><button type='button' class='btn btn-danger' value='\${tempobj}' onclick='addItem("\${currentType}",$(this))'>+</button></td>
                                </tr>`;
                        }
                    }else if(optionType == optionRestaurant){
                        contextHead += "<tr> <th>圖片</th> <th>餐廳名稱</th> <th>類型</th> <th>地址</th> <th>評分</th> </tr>";
                        for (let i = 0; i < list.length; i++) {
                            let tempobj = JSON.stringify(list[i]);
                            contextBody += `<tr>
                                <td><img src='\${list[i].picture}' class='img-thumbnail' onerror="this.src='../static/nopic.jpg'"/></td>
                                <td >\${list[i].name}</td>
                                <td>\${list[i].type}</td>
                                <td>\${list[i].address}</td>
                                <td>\${list[i].rating}</td>
                                <td><button type='button' class='btn btn-info' value='\${tempobj}' onclick='toDetailPage(optionRestaurant, $(this))'>看詳細</button></td>
                                <td><button type='button' class='btn btn-danger' value='\${tempobj}' onclick='addItem("\${currentType}",$(this))'>+</button></td>
                                </tr>`;
                        }
                    }else if(optionType == optionAttraction){
                        contextHead += "<tr> <th>圖片</th> <th>名稱</th> <th>地址</th> <th>票價</th> </tr>";
                        for (let i = 0; i < list.length; i++) {
                            let tempobj = JSON.stringify(list[i]);
                            contextBody += `<tr>
                                <td><img src='\${list[i].picture}' class='img-thumbnail' onerror="this.src='../static/nopic.jpg'" width='304' height='236'/></td>
                                <td >\${list[i].name}</td>
                                <td>\${list[i].address}</td>
                                <td>\${list[i].ticketInfo}</td>
                                <td><button type='button' class='btn btn-info' value='\${tempobj}' onclick='toDetailPage(optionAttraction, $(this))'>看詳細</button></td>
                                <td><button type='button' class='btn btn-danger' value='\${tempobj}' onclick='addItem("\${currentType}",$(this))'>+</button></td>
                                </tr>`;
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
                    // $(window).scrollTop(0);

                }
            })
        }

        function jump_to(num){
            var regexp=/^[1-9]\d*$/;
            let totalPageCount = totalPage;
            $("#inputPage").val("");
            //alert(totalPageCount);
            if(!regexp.test(num)){
                alert("請輸入大於0的整數！");
                return false;
            }else if((num-totalPageCount) > 0){
                alert("請輸入小於"+totalPageCount+"的整數");
                return false;
            }else{
                selectPage(num, currentType);
            }
        }

        function selectArea(value){
            $("#myInput").val("");
            currentArea = value;
            selectPage(null, currentType, currentArea);
        }


        function toDetailPage(type, obj){
            let json = JSON.parse(obj.val());
            console.log(json);
            if(type == optionCar){

            }else if(type == optionHotel){

            }else if(type == optionRestaurant){
                document.location.href="${pageContext.servletContext.contextPath}/RestaurantServlet?QUERY=QUERY&restaurant_name="+json.name;
            }else if(type == optionAttraction){
                document.location.href="${pageContext.servletContext.contextPath}/AttractionDetailServlet?attractionSn="+json.sn;
            }
        }

        function addItem(type, ele){
            console.log(ele.val());
            let target;
            let tempArr;
            let json = JSON.parse(ele.val());
            if(type == optionCar){
                listCar.push(json);
                listCar.sort((x,y) => x.sn-y.sn);
                <%--$.get("${pageContext.servletContext.contextPath}/TravelSetServlet",--%>
                <%--    {"method":"setItems", "selectType":"car", "list":JSON.stringify(listCar)});--%>
                tempArr = listCar;
                target = $("#addItemCar");
                ele.addClass("disabled");
                ele.attr('disabled',"true");
            }else if(type == optionHotel){
                listHotel.push(json);
                listHotel.sort((x,y) => x.sn-y.sn);
                <%--$.get("${pageContext.servletContext.contextPath}/TravelSetServlet",--%>
                <%--    {"method":"setItems", "selectType":"hotel", "list":JSON.stringify(listHotel)});--%>
                tempArr = listHotel;
                target = $("#addItemHotel");
                ele.addClass("disabled");
                ele.attr('disabled',"true");
            }else if(type == optionRestaurant){
                listRestaurant.push(json);
                listRestaurant.sort((x,y) => x.sn-y.sn);
                <%--$.get("${pageContext.servletContext.contextPath}/TravelSetServlet",--%>
                <%--    {"method":"setItems", "selectType":"restaurant", "list":JSON.stringify(listRestaurant)});--%>
                tempArr = listRestaurant;
                target = $("#addItemRestaurant");
                ele.addClass("disabled");
                ele.attr('disabled',"true");
            }else if(type == optionAttraction){
                listAttraction.push(json);
                listAttraction.sort((x,y) => x.sn-y.sn);
                <%--$.get("${pageContext.servletContext.contextPath}/TravelSetServlet",--%>
                <%--    {"method":"setItems", "selectType":"attraction", "list":JSON.stringify(listAttraction)});--%>
                tempArr = listAttraction;
                target = $("#addItemAttraction");
                ele.addClass("disabled");
                ele.attr('disabled',"true");
            }
            refreshSelectItem(target, tempArr, type);
        }

        function removeItem(type, index){
            let target;
            let tempArr;
            if(type == optionCar){
                listCar.splice(index,1);
                tempArr = listCar;
                target = $("#addItemCar");
            }else if(type == optionHotel){
                listHotel.splice(index,1);
                tempArr = listHotel;
                target = $("#addItemHotel");
            }else if(type == optionRestaurant){
                listRestaurant.splice(index,1);
                tempArr = listRestaurant;
                target = $("#addItemRestaurant");
            }else if(type == optionAttraction){
                listAttraction.splice(index,1);
                tempArr = listAttraction;
                target = $("#addItemAttraction");
            }
            refreshSelectItem(target, tempArr, type);
        }
        function removeAllItem(){
            listCar.splice(0);
            listHotel.splice(0);
            listRestaurant.splice(0);
            listAttraction.splice(0);

            refreshSelectItem($("#addItemCar"),listCar, );
            refreshSelectItem($("#addItemHotel"),listHotel);
            refreshSelectItem($("#addItemRestaurant"),listRestaurant);
            refreshSelectItem($("#addItemAttraction"),listAttraction);
        }
        function refreshSelectItem (target, arr, type){
            $.get("${pageContext.servletContext.contextPath}/TravelSetServlet",
                {"method":"setItems", "selectType":type, "list":JSON.stringify(arr)});
            let content = "";
            for (let i = 0; i < arr.length ; i++) {
                let json = JSON.stringify(arr[i]);
                content += `<tr>
                    <td>\${arr[i].name}</td>
                    <td><input type='datetime-local' class='btn btn-secondary' index='\${i}' onchange='setTravelTime("\${type}",$(this))'/></td>
                    <td><button type='button' class='btn btn-info' value='\${json}' onclick='toDetailPage("\${type}" ,$(this))'>看詳細</button></td>
                    <td><button type='button' class='btn btn-danger' value='\${i}' onclick='removeItem("\${type}",$(this).val())'>取消</button></td></tr>`;
            }
            target.html(content);
        }

        function setTravelTime(type, obj){
            $.get("${pageContext.servletContext.contextPath}/TravelSetServlet",
                {"method":"setItemsTime", "selectType":type, "index":obj.attr('index'), "time":Date.parse(obj.val())});
        }






        var sn;
        <c:if test="${! empty param.sn}">
             sn = ${param.sn};
        </c:if>
        $(document).ready(
            $.get({
                url:"${pageContext.servletContext.contextPath}/TravelSetServlet",
                data:{"method":"initTravelSet", "sn":sn},
                success: function (data){
                    let json = JSON.parse(data);
                    for (let i = 0; i < json.listEleCar.length; i++) {
                        listCar.push(JSON.parse(json.listEleCar[i]));
                    }
                    for (let i = 0; i < json.listEleHotel.length; i++) {
                        listHotel.push(JSON.parse(json.listEleHotel[i]));
                    }
                    for (let i = 0; i < json.listEleRestaurant.length; i++) {
                        listRestaurant.push(JSON.parse(json.listEleRestaurant[i]));
                    }
                    for (let i = 0; i < json.listEleAttraction.length; i++) {
                        listAttraction.push(JSON.parse(json.listEleAttraction[i]));
                    }

                    refreshSelectItem($("#addItemCar"),listCar, optionCar);
                    refreshSelectItem($("#addItemHotel"),listHotel, optionHotel);
                    refreshSelectItem($("#addItemRestaurant"),listRestaurant, optionRestaurant);
                    refreshSelectItem($("#addItemAttraction"),listAttraction, optionAttraction);

                    if(json.isLogin != true){
                        $("#saveTravelSet_id").addClass("disabled");
                        $("#saveTravelSet_id").attr('disabled',"true");
                        $("#newTravelSet_id").addClass("disabled");
                        $("#newTravelSet_id").attr('disabled',"true");
                    }else{
                        $("#saveTravelSet_id").removeClass("disabled");
                        $("#saveTravelSet_id").removeAttr("disabled");
                        $("#newTravelSet_id").removeClass("disabled");
                        $("#newTravelSet_id").removeAttr("disabled");
                    }
                }
            })
        );

        $(document).ready(
        function (){
            let regionList=["臺北市","新北市","桃園市","臺中市","高雄市"];
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
        });

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
                </table>
            </td>

            <td><button type="button" class="btn btn-primary" data-toggle="modal" data-target="#optionModal" onclick="optionSelected(optionCar)">選更多</button></td>
        </tr>
        <tr>
            <td>餐廳</td>
            <td>
                <table class="table">
                    <tbody id="addItemRestaurant"></tbody>
                </table>
            </td>
            <td><button type="button" class="btn btn-primary" data-toggle="modal" data-target="#optionModal" onclick="optionSelected(optionRestaurant)">選更多</button></td>
        </tr>
        <tr>
            <td>旅館</td>
            <td>
                <table class="table">
                    <tbody id="addItemHotel"></tbody>
                </table>
            </td>

            <td><button type="button" class="btn btn-primary" data-toggle="modal" data-target="#optionModal" onclick="optionSelected(optionHotel)">選更多</button></td>
        </tr>
        <tr>
            <td>景點</td>
            <td>
                <table class="table">
                    <tbody id="addItemAttraction"></tbody>
                </table>
            </td>
            <td><button type="button" class="btn btn-primary" data-toggle="modal" data-target="#optionModal" onclick="optionSelected(optionAttraction)">選更多</button></td>
        </tr>
        </tbody>
    </table>
    <div class="navbar-nav">
        <form class="form-group ">
<%--            <input class="d-none" id="submitMethodId" name="method" >--%>
<%--            <input class="d-none" id="submitCarId" name="travelSetCarList" >--%>
<%--            <input class="d-none" id="submitHotelId" name="travelSetHotelList" >--%>
<%--            <input class="d-none" id="submitRestaurantId" name="travelSetRestaurantList" >--%>
<%--            <input class="d-none" id="submitAttractionId" name="travelSetAttractionList" >--%>
            <label for="travelSetName">Name:</label>
            <input type="text" class="form-control" id="travelSetName" placeholder='我的旅程' value='我的旅程'>
            <label for="travelSetDescription">備註:</label>
            <textarea class="form-control" rows="5" id="travelSetDescription"></textarea>
            <button type="button" class="btn btn-primary" id='saveTravelSet_id' onclick='saveTravelSet()'>保存當前</button>
            <button type="button" class="btn btn-success" id='newTravelSet_id' onclick='newTravelSet()'>新項目</button>
            <button type="button" class="btn btn-outline-danger" onclick='removeAllItem()' >取消</button>
        </form>

    </div>
</div>




<div class="modal" id="optionModal">
    <div class="modal-dialog modal-xl">
        <div class="modal-content">

            <!-- Modal Header -->
            <div class="modal-header">
<%--                <h1 class="modal-title">Modal Header</h1>--%>
            <div class="container d-none" id="selectArea_id">
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
            <input class="form-control" id="myInput" type="text" placeholder="Search..">
            <button type="button" class="close" data-dismiss="modal">×</button>
            </div>


            <!-- Modal body -->
            <div class="modal-body" id="modal-body_id">
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
<script type="text/javascript">
    function checkSubmit(){
        if(listCar.length == 0 && listHotel.length ==0 && listRestaurant.length==0 && listAttraction.length==0){
            alert("沒有任何規劃")
            return false;
        }
        if($("#travelSetName").val() == "我的旅程"){
            if(!confirm("是否使用預設名稱: 我的旅程")){
                let text = prompt("請輸入名稱");
                $("#travelSetName").val(text);
            }
        }
        return true;
    }
    function saveTravelSet(){
        let flag = checkSubmit();
        console.log(flag);
        if(flag){
            $.get({
                url:"${pageContext.servletContext.contextPath}/TravelSetServlet",
                data:{"method":"saveTravelSet", "travelSetName":$("#travelSetName").val(), "travelSetDescription":$("#travelSetDescription").val()},
                success:function (data){
                    let json=JSON.parse(data);
                    if(json.status){
                        if(!alert("保存成功")) {
                            document.location.href="${pageContext.servletContext.contextPath}/a592070/travelSetSelect.jsp";
                        }
                    }else{
                        alert("保存失敗");
                    }
                }
            });
        }
    }
    function newTravelSet(){
        let flag = checkSubmit();
        if(flag){
            $.get({
                url:"${pageContext.servletContext.contextPath}/TravelSetServlet",
                data:{"method":"newTravelSet", "travelSetName":$("#travelSetName").val(), "travelSetDescription":$("#travelSetDescription").val()},
                success:function (data){
                    let json=JSON.parse(data);
                    if(json.status){
                        if(!alert("新建成功")) {
                            document.location.href="${pageContext.servletContext.contextPath}/a592070/travelSetSelect.jsp";
                        }else{
                            alert("新建失敗");
                        }
                    }else{
                        alert("新建失敗");
                    }
                }
            })
        }
    }
</script>
</body>
</html>
