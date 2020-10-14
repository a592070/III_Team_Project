<%@page import="rambo0021.AccountBean"%>
<%@page import="rambo0021.RegisterDAO"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
session=request.getSession(false);
if (session.getAttribute("Login") == null) {
	// 請使用者登入
	response.sendRedirect(response.encodeRedirectURL(
			request.getContextPath() + "/rambo0021/login.jsp"));
	return;
} %>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">

	<title>個人頁面</title>

	<script src="https://code.jquery.com/jquery-3.5.1.js"
		integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
	<script src="https://www.w3schools.com/lib/w3.js"></script>
	<script src="${pageContext.servletContext.contextPath}/static/jquery-3.5.1.js"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/js-cookie@rc/dist/js.cookie.min.js"></script>
</head>

<body>

	<jsp:include page="/fragment/header.jsp" />

	<div align="center" style="margin:0 auto;width:800px;height: 2000px;" style="padding-top: 500px;">
		<div style="float: left;">
			<form action="<%=pageContext.getServletContext().getContextPath()%>/HomePageUpdateServlet"
				enctype="multipart/form-data" method="post">

				<table class="table" border="1" width="300px">
					<tr>
						<td>
							<c:out value="身分:${Login.identityString}" />
						</td>
					</tr>
					<tr>
						<td> <img width='300px'
								src="${pageContext.request.contextPath}/Homepage?userName=${Login.userName}"><br>
							<input type="hidden" id="picture" name="picture" value="更新照片" accept="image/*" disabled>
						</td>
					</tr>
					<tr>
						<td>
							<c:out value="帳號:${Login.userName}" />
						</td>
					</tr>
					<tr>
						<td>密碼:<input type="password" id="password" name="password" value="" disabled />
						</td>
					</tr>
					<tr>
						<td>暱稱:<input type="text" id="nickName" name="nickName" value="${Login.nickName}" disabled />
						</td>
					</tr>
					<tr>
						<td>email:<input type="text" id="email" name="email" value="${Login.email}" disabled /></td>
					</tr>
					<tr>
						<td>
							<c:out value="註冊日期:${Login.registerString}" />
						</td>
					</tr>
					<tr>
						<td>
							<c:out value="最後修改日期:${Login.modify_DateString}" />
						</td>
					</tr>

				</table>
				<input type="button" class="btn btn-primary" value="修改" id="update" name="update">
				<input type="hidden" class="btn btn-primary" value="儲存" id="submit" name="submit">
				<input type="hidden" class="btn btn-primary" value="返回" id="exit" name="exit">
			</form>
		</div>
		<!-- modal window -->
		<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog">
			  <div class="modal-content">
				<div class="modal-header">
				  <h5 class="modal-title" id="exampleModalLabel">詳細訂單</h5>
				  <button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				  </button>
				</div>
				<div class="modal-body">
				  <table class="table">
					  <tr>
						  <th scope="col">餐廳訂單</th>
						  <th scope="col">住宿訂單</th>
						  <th scope="col">交通訂單</th>
					  </tr>
					  <tr>
						  <td id="r"></td>
						  <td id="h"></td>
						  <td id="t"></td>
					  </tr>
				  </table>
				</div>
				<div class="modal-footer">
				  <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
				</div>
			  </div>
			</div>
		  </div>
		<div style="float: right;" >
			<table class="table">
				<thead>
					<tr>
						<th scope="col">#</th>
						<th scope="col">訂單編號</th>
						<th scope="col">日期</th>
					</tr>
				</thead>
				<tbody id="tablebody">
					
				</tbody>
			</table>

			</table>
		</div>
	</div>
	<script>
		
		$("#update").click(function () {
			console.log("aaaaa")
			$("#update").prop("type", "hidden");
			$("#submit").prop("type", "submit");
			$("#exit").prop("type", "button");
			$("#password").attr("disabled", false);
			$("#nickName").attr("disabled", false);
			$("#email").attr("disabled", false);
			$("#picture").attr("disabled", false).prop("type", "file");
		})
		$("#exit").click(function () {
			$("#submit").prop("type", "hidden");
			$("#exit").prop("type", "hidden");
			$("#update").prop("type", "button");
			$("#password").attr("disabled", true);
			$("#nickName").attr("disabled", true);
			$("#email").attr("disabled", true);
			$("#picture").attr("disabled", true).prop("type", "hidden");
		})
		var array=[]
		$(document).ready(function (){
			$.ajax(
                    {
                        type: 'POST',
                        url: '${pageContext.servletContext.contextPath}/OrderAjaxController',
						dataType: "json",
                        success:function(response){
						  buildOrderTable(response);
                        }
                    }
                )
		
	
		function buildOrderTable(response){
			var tablebody=document.getElementById("tablebody")
			console.log(response)
            for(i=0;i<response.length;i++){
				var orderId=response[i].orderId;
				var date=response[i].orderDateString;
				console.log(date);
				var num=i+1
                var row="<tr><th scope='row'>"
				+num+
				"</th><td>"
				+orderId+
				"</td><td>"
				+date+
				"</td><td><button type='button'  class='btn btn-primary' data-toggle='modal' data-target='#exampleModal' >詳細訂單</button></td></tr>"
				tablebody.innerHTML += row; 
			}			
		}
		$("#tablebody").on('click','.btn.btn-primary',function(){
		var orderid=$(this).parent().parent().children().eq(1).text();
		$.ajax(
                    {
                        type: 'POST',
                        url: '../ListAjaxController',
						data:{"orderid":orderid},					    
						dataType: "json", 
                        success:function(response){
							console.log(response["r_orderId"])							
							document.getElementById("r").innerHTML=response["r_orderId"]
							document.getElementById("h").innerHTML=response["h_orderId"]
							document.getElementById("t").innerHTML=response["t_orderId"]	

                        }
                    }
                )
		}
		)
	})
		
	</script>
</body>

</html>