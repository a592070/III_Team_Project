<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新增店家</title>
<script src="${pageContext.servletContext.contextPath}/static/jquery-3.5.1.js"></script>
   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
   <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
   <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>

<body>
<div style="text-align:center;"><h2>新增店家</h2></div>
<br>
<table class="table mx-auto my-3" style="width: 1100px">
            <tbody>
            <tr>
                <th>名稱</th>
                <td><input type="text" class="form-control"  name="hname" placeholder="飯店名稱"></td>
            </tr>
            <tr>
                <th>地址</th>
                <td><input type="text" class="form-control"  name="haddress" placeholder="飯店地址"></td>
            </tr>
            <tr>
                <th>電話</th>
                <td><input type="text" class="form-control"  name="htel" placeholder="飯店電話"></td>
            </tr>
            <tr>
                <th>房型:雙人房</th>
                <td><input type="text" class="form-control"  name="doubleroom" placeholder="雙人房價格"></td>
            </tr>
            <tr>
               	<th>房型:四人房</th>
                <td><input type="text" class="form-control"  name="quadroom" placeholder="四人房價格"></td>
            </tr>
            <tr>
                <th>介紹</th>
                <td><input type="text" class="form-control"  name="hdescription" placeholder="飯店簡介"></td>
            </tr>
            <tr>
                <th>營業時間</th>
                <td><input type="text" class="form-control"  name="hopentime" placeholder="營業時間"></td>
            </tr>
            <tr>
                <th>住宿類型</th>
                <td><input type="text" class="form-control"  name="htype" placeholder="住宿類型Ex:飯店 民宿等.."></td>
            </tr>
          
    	        <tr>
                	<td><button type="submit" class="btn btn-primary mb-2" name="checkinsert">確認新增</button></td>
                	<td><button type="submit" class="btn btn-primary mb-2" name="cencelinsert">取消新增</button></td>
            	</tr>
    
            </tbody>
        </table>
</body>
</html>