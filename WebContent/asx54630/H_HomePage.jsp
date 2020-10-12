<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>住宿業者頁面</title> 
   <script src="${pageContext.servletContext.contextPath}/static/jquery-3.5.1.js"></script>
   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
   <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
   <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
<jsp:include page="/asx54630/TestHeader.jsp" />

<div class="container">
        <h2>歡迎回來! 業者</h2>
        <br>
        <div class="mx-auto my-3" style="width: 1100px">
            <form class="form-inline" action="<%=pageContext.getServletContext().getContextPath()%>/HotelController" method="POST">
                <div class="form-group mb-2">
                  <h5>請選擇要使用的功能:</h5>
                </div>
                
                <div class="form-group mx-sm-3 mb-2">
				<button type="submit" class="btn btn-primary mb-2" value="search" name="insert">新增店家資料</button>
                </div>
                
                 
                <div class="form-group mx-sm-3 mb-2">
				<button type="submit" class="btn btn-primary mb-2" value="search" name="update">修改店家資料</button>
                </div>
                
                <div class="form-group mx-sm-3 mb-2">
				<button type="submit" class="btn btn-primary mb-2" value="search" name="delete">刪除店家資料</button>
                </div>
            </form>
        </div>
		<br>
		<table class="table">
            <thead>
            <tr>
                <th>名稱</th>
                <th>地址</th>
                <th>電話</th>
                <th>房型:雙人房</th>
                <th>房型:四人房</th>
                <th>介紹</th>
                <th>營業時間</th>
                <th>住宿類型</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>晶華酒店</td>
                <td>臺北市中山區中山北路2段39巷3號</td>
                <td>886-2-25238000</td>
                <td>5000</td>
                <td>8000</td>
                <td>台北晶華酒店是國際麗晶酒店集團的旗艦店，也是全台北市最卓越、最受歡迎的國際五星級飯店之一。</td>
                <td>全年無休</td>
                <td>飯店</td>
            </tr>
            </tbody>
        </table>
</body>
</html>