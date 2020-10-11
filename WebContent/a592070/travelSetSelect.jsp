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

        $(document).ready($.get({
            url:"${pageContext.servletContext.contextPath}/TravelSetSelectServlet",
            data:{},
            success: function (data){
                let json = JSON.parse(data);
                let content = "";
                for (let i = 0; i < json.length; i++) {
                    let obj = json[i];
                    content +=
                        `<tr>
                            <td>\${obj.name}</td>
                            <td>\${obj.description}</td>
                            <td>\${new Date(obj.updateTime).toLocaleString()}</td>
                            <td>\${new Date(obj.createdTime).toLocaleString()}</td>
                            <td><button type='button' class='btn btn-info' value="\${obj.sn}" onclick='travelSetDetail($(this))'>看詳細</button></td>
                        </tr>`;
                }
                $("#tbody_id").html(content);
            }
        }));

        function travelSetDetail(obj){
            document.location.href="${pageContext.servletContext.contextPath}/a592070/travelSetInfo.jsp?sn="+obj.val();
        }


    </script>

</head>
<body>
<jsp:include page="../fragment/header.jsp" />

<div class="container">
    <h2>你共有x套行程規劃</h2>
    <table class="table table-hover">
        <thead class="thead-dark ">
        <tr>
            <th>名稱</th>
            <th>描述</th>
            <th>last update time</th>
            <th>created time</th>
            <th></th>
        </tr>
        </thead>
        <tbody id="tbody_id">
        </tbody>
    </table>
</div>

</body>
</html>
