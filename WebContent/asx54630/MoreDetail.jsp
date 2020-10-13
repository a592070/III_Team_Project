<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
  <%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>詳細資訊</title>
   <script src="${pageContext.servletContext.contextPath}/static/jquery-3.5.1.js"></script>
   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
   <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
   <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
<jsp:include page="/fragment/header.jsp" />

<div style="text-align:center;"><h2>詳細資訊</h2></div>
<br>
<table class="table mx-auto my-3" style="width: 1100px">
            <tbody>
            <tr>
                <th>名稱</th>
                <td>${detaildata.NAME}</td>
            </tr>
            <tr>
                <th>地址</th>
                <td>${detaildata.ADDRESS}</td>
            </tr>
            <tr>
                <th>電話</th>
                <td>${detaildata.TEL}</td>
            </tr>
            <tr>
                <th>房型:雙人房</th>
                <td>${detaildata.DOUBLE_ROOM} $</td>
            </tr>
            <tr>
               	<th>房型:四人房</th>
                <td>${detaildata.QUADRUPLE_ROOM} $</td>
            </tr>
            <tr>
                <th>介紹</th>
                <td>${detaildata.DESCRIPTION}</td>
            </tr>
            <tr>
                <th>營業時間</th>
                <td>${detaildata.OPENTIME}</td>
            </tr>
            <tr>
                <th>住宿類型</th>
                <td>${detaildata.TYPE}</td>
            </tr>
            <tr>
                <th>功能</th>
                <td><button type="submit" class="btn btn-primary mb-2" data-toggle="modal" data-target="#exampleModal" >我要訂房</button></td>
            </tr>
            </body>
        </table>
        
         <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
              <div class="modal-content">
                <div class="modal-header">
                  <h5 class="modal-title" id="exampleModalLabel" >預訂飯店:</h5>
                  <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                  </button>
                </div>
                <div class="modal-body">
                  <form>
                    <div class="form-group">
                      <label for="recipient-name" class="col-form-label">房型:</label><br>
                      <input type="checkbox" value="doubleroom">雙人房
                      <input type="text" >間<br>
                      <input type="checkbox" value="quadrupleroom">四人房
                      <input type="text">間<br>
                    </div>
                    <div class="form-group">
                        <label for="" class="t1">入住日期:</label>
                        <input type="date" name="checkin"><br>
                        <label for="" class="t1">退房日期:</label>
                        <input type="date" name="checkout">
                      </div>
                  </form>
                </div>
                <div class="modal-footer">
                  <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
                  <button type="submit" class="btn btn-primary">確認</button>
                </div>
              </div>
            </div>
          </div>
</body>
</html>