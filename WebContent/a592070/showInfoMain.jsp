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
    <title>Title</title>
    <script src="${pageContext.servletContext.contextPath}/static/jquery-3.5.1.js"></script>
    <script type="text/javascript">
        window.onload = $.get({
                url: "${pageContext.servletContext.contextPath}/AttractionsInfoServlet",
                success: function (data){
                    let dataJSON = JSON.parse(data);
                    var totalNum = dataJSON.totalPage;
                    for (let i=0; i<totalNum; i++){
                        let optionEle = document.createElement("option");
                        optionEle.textContent = i+1;
                        // optionEle.setAttribute("name", "nowPage");
                        $("#select_id").append(optionEle);
                    }
                    let list = JSON.parse(dataJSON.nowPageInfo);
                    let context="";
                    for (let i = 0; i < list.length; i++) {
                        context += "<tr>"+
                            "<td>" + list[i].name + "</td>" +
                            "<td>" + list[i].address + "</td>" +
                            "<td>" + list[i].opentime + "</td>" +
                            "<td>" + list[i].region + "</td>" +
                            "<td>" + list[i].area + "</td>" +
                            "<td>" + list[i].type + "</td>" +
                            "<td>" + list[i].rating + "</td>" +
                            "</tr>";
                    }
                    $("#tbody_id").html(context);
                }
            }
        )
    </script>
</head>
<body>

<form action="${pageContext.servletContext.contextPath}/AttractionsInfoServlet">
<select name="nowPage" id="select_id" onchange="selectPage()">
</select>
</form>
<script type="text/javascript">
    function selectPage(){
        $.post({
            url: "${pageContext.servletContext.contextPath}/AttractionsInfoServlet",
            data: {"nowPage": $("#select_id").val()-1},
            success: function (data){
                let dataJSON = JSON.parse(data);
                let context="";
                for (let i = 0; i < dataJSON.length; i++) {
                    context += "<tr>"+
                        "<td>" + dataJSON[i].name + "</td>" +
                        "<td>" + dataJSON[i].address + "</td>" +
                        "<td>" + dataJSON[i].opentime + "</td>" +
                        "<td>" + dataJSON[i].region + "</td>" +
                        "<td>" + dataJSON[i].area + "</td>" +
                        "<td>" + dataJSON[i].type + "</td>" +
                        "<td>" + dataJSON[i].rating + "</td>" +
                        "</tr>";
                }
                $("#tbody_id").html(context);
            }
        })
    }
</script>

<div>
    <table>
        <tr>
            <%--  name address opentime region area type rating; --%>
            <th>名稱</th>
            <th>地址</th>
            <th>開放時間</th>
            <th>縣市</th>
            <th>區域</th>
            <th>類型</th>
            <th>評分</th>
        </tr>
        <tbody id="tbody_id">

        </tbody>
    </table>
</div>
</body>
</html>
