<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Restaurant HomePage</title>
<script src="${pageContext.servletContext.contextPath}/static/jquery-3.5.1.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<style>
* {
	padding: 0;
	margin: 0;
}

.box {
	width: 900px;
	margin: 50px auto;
}

.div_img {
	width: 800px;
	height: 530px;
	margin: auto;
	overflow: hidden;
	display: flex;
}

img {
	width: 100%;
}

.box {
	border: 1px solid gray;
}

.booking, .info, .comment {
	border: 1px solid gray;
}

h2 {
	padding: 20px;
	border-bottom: 1px solid gray;
	color:white;
}
/* div {
            border: 1px solid rgb(212, 212, 212);
        } */
.title {
	text-align: center;
	background-color: #003060;
	
}

.top {
	display: flex;
}

.booking {
	width: 300px;
	padding: 10px 0;
	line-height: 22px;
	text-align: center;
	color: #000000;
	cursor: pointer;
	/* border-bottom: solid 1.5px #f04e2f; */
} 

.info {
	width: 300px;
	padding: 10px 0;
	line-height: 22px;
	text-align: center;
	color: #000000;
	cursor: pointer;
}

 .comment {
	width: 300px;
	padding: 10px 0;
	line-height: 22px;
	text-align: center;
	color: #000000;
	cursor: pointer;
}  

.res_data {
	/*position: relative;*/
	background-color: #eeeeee;
	padding: 10px 30px;
	/* font-size: 15px; */
	/* font-weight: 400; */
	border-top: 1px solid gray;
}

.res_result {
	padding: 10px 30px;
}

.p_result {
	padding: 10px 30px;
}

.rating {
	float: left;
	padding-right: 20px;
	padding: 10px 30px;
}

.sp_rating {
	padding: 10px 30px;
	line-height: 35px;
}

.book-btn {
	/* background-color: #ec7551; */
	border-radius: 4px;
	color: #000000;
	padding: 8px 0;
	text-align: center;
	cursor: pointer;
	width: 400px;
	margin: auto;
}

h3 {
	padding: 10px 30px;
}

.modify-img {
	padding: 5px;
	margin-right: 10px;
	text-align: right;
}

.modify {
	padding: 5px;
	margin-right: 10px;
	text-align: right;
	float: right;
}
.btn.btn-success.order{
	padding: 5px;
	margin-left:30px;
	margin-bottom: 20px;
}
.div-1{
	padding: 5px;
	margin-left:10px;
	margin-bottom: 20px;
}
.modal-title{
	margin-left:10px;
}
p{
	font-size:16px;
}
</style>
</head>
<body>

	<!-- 引入共同的頁首 -->
	<jsp:include page="/fragment/header.jsp" />

	<div class="container">
		<div class="box">
			
			
			<div>
				<h2 class="title">餐廳資訊</h2>
				<div class="top">
					<!-- <div class="booking">訂位</div>
					<div class="info">資訊</div>
					<div class="comment">評論</div> -->
				</div> 
				<FORM action="<c:url value='/iring29/ModifyImg.jsp'/>"
					method="POST">
				<p class="modify-img">
					<button class="btn btn-light">修改</button>
				</p>
				<div class="div_img">
					<img src="${r_hp.picture}">
				</div>
				</FORM>
				<div class="div-1">
					<h3>${r_hp.name}</h3>
				</div>
				
				<FORM action="<c:url value='/iring29/Modify_Location.jsp'/>"
					method="POST">
					<div>
						<p class="modify">
							<button class="btn btn-light" name="m-add">修改</button>
						</p>
						<h4 class="res_data">餐廳地點</h4>
					</div>

					<div>
						<h4 class="res_result">地址</h4>
						<p class="p_result">${r_hp.address}</p>
					</div>
					<div>
						<h4 class="res_result">交通方式</h4>
						<pre><p class="p_result">${r_hp.transportation}</p></pre>
					</div>
					<Input type='hidden' name='rBean' value='${r_hp}'> <Input
						type='hidden' name='roBean' value='${roBean}'>
				</FORM>

				<FORM action="<c:url value='/iring29/Modify_Type.jsp'/>"
					method="POST">
					<div>
						<p class="modify">
							<button class="btn btn-light">修改</button>
						</p>
						<h4 class="res_data">菜色介紹</h4>
					</div>
					<div>
						<h4 class="res_result">適合聚餐類型</h4>
						<p class="p_result">${r_hp.serviceinfo}</p>
					</div>
					<div>
						<h4 class="res_result">料理種類</h4>
						<p class="p_result">${r_hp.type}</p>
					</div>
				</FORM>
				<FORM action="<c:url value='/iring29/Modify_Info.jsp'/>"
					method="POST">
					<div>
						<p class="modify">
							<button class="btn btn-light">修改</button>
						</p>
						<h4 class="res_data">餐廳資訊</h4>
					</div>
					<div>
						<h4 class="res_result">營業時間</h4>
						<pre><p class="p_result">${r_hp.opentime}</p></pre>
					</div>
					<div>
						<h4 class="res_result">餐廳描述</h4>
						<p class="p_result">${r_hp.description}</p>
					</div>
				</FORM>
				<div>
					<h4 class="res_data">我的訂單</h4>
					<c:forEach var="roBean" items="${roBean}">


						<!-- Button trigger modal -->
						<button type="button" class="btn btn-success order" data-toggle="modal"
							data-target="#訂單${roBean.r_sn_order}">
							訂單${roBean.r_sn_order}</button>
						
						<!-- Modal -->
						<div class="modal fade" id="訂單${roBean.r_sn_order}" tabindex="-1"
							role="dialog" aria-labelledby="exampleModalCenterTitle"
							aria-hidden="true">
							<div class="modal-dialog modal-dialog-centered" role="document">
								<div class="modal-content">
									<div class="modal-header">
										<h5 class="modal-title" id="exampleModalLongTitle">訂單${roBean.r_sn_order}</h5>
										<button type="button" class="close" data-dismiss="modal"
											aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
									</div>
									<div class="modal-body">
										<div class="div-1">
											<label for="r-date">用餐日期</label> <span>${roBean.booking_date}</span>
										</div>
										<div class="div-1">
											<label for="b-name">訂位人姓名</label> <span>${roBean.customerName}</span>
										</div>
									
									<div class="div-1">
										<label for="b-phone">訂位人手機</label> <span>${roBean.customerPhone}</span>
									</div>
									<div class="div-1">
										<label for="b-number">用餐人數</label> <span>${roBean.customerNum}</span>
									</div>
									<div class="div-1">
										<label for="price">尚未付款</label> <span>500 元</span>
									</div>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-secondary"
											data-dismiss="modal">Close</button>
										<!-- <button type="button" class="btn btn-primary">Save
											changes</button> -->
									</div>
								</div>
							</div>
						</div>

					</c:forEach>

				</div>
			</div>

		</div>
		<Input type='hidden' name='rBean' value='${r_hp}'> <Input
			type='hidden' name='rBean' value='${roBean}'>




	</div>
</body>
</html>