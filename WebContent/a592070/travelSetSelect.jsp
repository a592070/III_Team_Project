<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2020/10/10
  Time: 下午 10:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>選擇旅程頁面</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

    <script type="text/javascript">

        $(document).load(loadPage());

        function loadPage(){
            $.get({
                url:"${pageContext.servletContext.contextPath}/TravelSetSelectServlet",
                data:{},
                success: function (data){
                    let json = JSON.parse(data);
                    let content = "";

                    let currentTravelSet = json.currentTravelSet;
                    if(currentTravelSet != null){
                        let obj = currentTravelSet;
                        content +=`
                        <th >當前項目</th>
                        <td colspan="2" >\${obj.name}</td>
                        <td colspan="2">\${obj.description}</td>
                        <td><button type='button' class='btn btn-info' onclick='travelSetDetail($(this))'>看詳細</button></td>`;
                    }

                    $("#currentTravelSet_id").html(content);

                    content = "";
                    let historyTravelSets = json.historyTravelSets;
                    for (let i = 0; i < historyTravelSets.length; i++) {
                        let obj = historyTravelSets[i];
                        content +=
                            `<tr>
                            <th>\${obj.name}</th>
                            <td>\${obj.description}</td>
                            <td>\${new Date(obj.updateTime).toLocaleString()}</td>
                            <td>\${new Date(obj.createdTime).toLocaleString()}</td>
                            <td><button type='button' class='btn btn-info' value="\${obj.sn}" onclick='travelSetDetail($(this))'>看詳細</button></td>
                            <td><button type='button' class='btn btn-danger' value="\${obj.sn}" onclick='deleteTravelSet($(this))'>刪除項目</button></td>
                        </tr>`;
                    }
                    $("#tbody_id").html(content);
                    $("#title_id").text("你共有"+historyTravelSets.length+"套旅遊規劃");
                }
            })
        }

        function travelSetDetail(obj){
            document.location.href="${pageContext.servletContext.contextPath}/a592070/travelSetInfo.jsp?sn="+obj.val();
        }

        function newTravelSet(){
            $.get(
                "${pageContext.servletContext.contextPath}/TravelSetSelectServlet",
                {"method": "initCurrentTravelSet"},
                function (data){
                    document.location.href = "${pageContext.servletContext.contextPath}/a592070/travelSetInfo.jsp";
                });
        }

        function deleteTravelSet(obj){
            $.get(
                "${pageContext.servletContext.contextPath}/TravelSetSelectServlet",
                {"method": "deleteTravelSet", "sn":obj.val()},
                function (data){
                    let json = JSON.parse(data);
                    if(json.status) {
                        loadPage();
                    }else{
                        alert("刪除失敗");
                    }
                }
            );
        }
    </script>

</head>
<body>
<jsp:include page="../fragment/header.jsp" />

<div class="container">
    <h2 id="title_id">你共有x套行程規劃</h2>
    <table class="table table-hover">
        <thead class="thead-light">
        <tr>
            <th scope="col"></th>
            <th scope="col"></th>
            <th scope="col"></th>
            <th scope="col"></th>
            <th scope="col"></th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td colspan="6"><button type='button' class='btn btn-info btn-lg btn-block' onclick='newTravelSet()'>新建項目</button></td>
        </tr>
        <tr id="currentTravelSet_id">
            <td colspan="2">當前項目</td>
            <td colspan="2">...</td>
            <td><button type='button' class='btn btn-info' onclick=''>看詳細</button></td>
        </tr>
        </tbody>
    </table>
    <table class="table table-hover">
        <thead class=" thead-dark">
        <tr>
            <th>名稱</th>
            <th>描述</th>
            <th>last update time</th>
            <th>created time</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody id="tbody_id">
        </tbody>
    </table>
</div>

</body>
</html>
