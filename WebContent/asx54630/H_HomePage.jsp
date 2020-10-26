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
<jsp:include page="/fragment/header.jsp" />

<div class="container">
        <h2>歡迎回來! 業者</h2>
        <br>
		<table class="table">
            <thead>
            <tr>
                <th>名稱</th>
                <td>${hoteldata.NAME}</td>
                <td><button type="submit" class="btn btn-primary mb-2" data-toggle="modal" data-target="#exampleModal" onclick="clickdetail(${hoteldata.NAME})" >修改</button></td>
            </tr>
            <tr>
                <th>地址</th>
                <td>${hoteldata.ADDRESS}</td>
				<td><button type="submit" class="btn btn-primary mb-2" data-toggle="modal" data-target="#exampleModal" onclick="clickdetail(${hoteldata.ADDRESS})">修改</button></td>
          	</tr>
            <tr>
                <th>電話</th>
                <td>${hoteldata.TEL}</td>
                <td><button type="submit" class="btn btn-primary mb-2" data-toggle="modal" data-target="#exampleModal" onclick="clickdetail(${hoteldata.TEL})">修改</button></td>
            </tr>
            <tr>
                <th>房型:雙人房</th>
                <td>${hoteldata.DOUBLE_ROOM}</td> 
                <td><button type="submit" class="btn btn-primary mb-2" data-toggle="modal" data-target="#exampleModal" onclick="clickdetail(${hoteldata.DOUBLE_ROOM})">修改</button></td>
            </tr>
            <tr>
                <th>房型:四人房</th>
                 <td>${hoteldata.QUADRUPLE_ROOM}</td>
                 <td><button type="submit" class="btn btn-primary mb-2" data-toggle="modal" data-target="#exampleModal" onclick="clickdetail(${hoteldata.QUADRUPLE_ROOM})">修改</button></td>
            </tr>
            <tr>
                <th>介紹</th>
                <td>${hoteldata.DESCRIPTION}</td>
                <td><button type="submit" class="btn btn-primary mb-2" data-toggle="modal" data-target="#exampleModal" onclick="clickdetail(${v.DESCRIPTION})">修改</button></td>
            </tr>
            <tr>
                <th>營業時間</th>
                <td>${hoteldata.OPENTIME}</td>
                <td><button type="submit" class="btn btn-primary mb-2" data-toggle="modal" data-target="#exampleModal" onclick="clickdetail(${hoteldata.OPENTIME})">修改</button></td>
            </tr>
            <tr>
                <th>住宿類型</th>
                <td>${hoteldata.TYPE}</td>
                <td><button type="submit" class="btn btn-primary mb-2" data-toggle="modal" data-target="#exampleModal" onclick="clickdetail(${hoteldata.TYPE})">修改</button></td>
            </tr>
            </thead>
        </table>
        <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
              <div class="modal-content">
                <div class="modal-header">
                  <h5 class="modal-title" id="exampleModalLabel">店家資訊修改</h5>
                  <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                  </button>
                </div>
                <div class="modal-body">
                  <form action="<%=pageContext.getServletContext().getContextPath()%>/H_HomePageUpdateServlet" method="POST">
                    <div class="form-group">
                      <label for="recipient-name" class="col-form-label">要修改為:</label><br>
                      <p>temp</p>
                      <input type="text" name="" >
                    </div>                
                    <div class="modal-footer">
                  		<button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
                 		<button type="submit" class="btn btn-primary">確認</button>
               		 </div> 
               	</form>	
               		<script type="text/javascript">
					function clickdetail(getval){
						let temp = getval.value;
							
							}		
				</script>    
              	</div>
            </div>
          </div>
        </div>
</body>
</html>