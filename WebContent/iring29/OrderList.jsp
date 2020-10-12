<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Order List</title>

<script
	src="${pageContext.servletContext.contextPath}/static/jquery-3.5.1.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<style>
* {
	margin: 0;
	padding: 0;
}

.wrapper {
	margin: 100px auto;
	border: 1px solid black;
	width: 600px;
	padding: 50px;
}

h1 {
	margin-left: 50px;
	padding-bottom: 30px;
}

.div-1 {
	margin-left: 50px;
	padding-bottom: 20px;
}

.p-time {
	margin-left: 50px;
	padding-left: 100px
}

label {
	width: 100px;
	float: left;
	padding-right: 20px;
	/* margin-left: 50px; */
	text-align: right;
}

.div-btn {
	text-align: center;
}

#inputState.form-control {
	width: 250px;
	padding-left: 20px;
}

input {
	padding-left: 10px;
}

.btn-group {
	margin-left: 50px;
	padding-left: 100px
}
</style>
</head>
<body>
	<jsp:include page="/fragment/header.jsp" />

	<FORM id="form1" name="form1" action="<c:url value='OrderListServlet'/>" method="POST">
		<div class="wrapper">
			<div class="container">

				<h1>訂單訊息</h1>
				<div class="">
					<div class="div-1">
						<label for="r-name">訂位餐廳</label> <span>${res_name}</span>
					</div>

					<div class="div-1">
						<label for="r-date">用餐日期</label>
						<!--  	<span>${book_date}</span>-->
						<input type="date" name="book_date" id="theDate">
						<script>
						var book = "${book_date}";
						console.log(book);
						console.log(typeof(book));
						document.getElementById("theDate").value = book;
						</script>
					</div>

					<div class="div-1">
						<label for="r-date">用餐時間</label>
						<%-- <span>${time}</span> --%>
					
					<select name="time" id="inputState" class="form-control">
						<option value="">請選擇用餐時間</option>
						<option value="11:00">11:00</option>
						<option value="12:00">12:00</option>
						<option value="13:00">13:00</option>
						<option value="14:00">14:00</option>
						<option value="17:00">17:00</option>
						<option value="18:00">18:00</option>
						<option value="19:00">19:00</option>
						<option value="20:00">20:00</option>
					
					</select>
					</div>
					<!--  
					<p class="btn-group" role="group" aria-label="Basic example"> 
						<button class="btn btn-light" name="time" value="11:00">11:00</button>
						<button class="btn btn-light" name="time" value="12:00">12:00</button>
						<button class="btn btn-light" name="time" value="13:00">13:00</button>
						<button class="btn btn-light" name="time" value="14:00">14:00</button>
						
					</p>
					<p class="btn-group p-time" role="group" aria-label="Basic example">
						<button class="btn btn-light" name="time" value="17:00">17:00</button>
						<button class="btn btn-light" name="time" value="18:00">18:00</button>
						<button class="btn btn-light" name="time" value="19:00">19:00</button>
						<button class="btn btn-light" name="time" value="20:00">20:00</button>			
					</p>
					-->
					<div class="div-1">
						<label for="b-name">訂位人姓名</label> <input type="text" id="b-name"
							name="b-name" placeholder="請輸入完整姓名">
					</div>
				</div>
				<div class="div-1">
					<label for="b-phone">訂位人手機</label> 
					<input type="text" id="b-phone" onblur="checkPhone()" 
						name="b-phone" placeholder="請輸入手機號碼">
						<p id="uidt" style="margin-left: 10px"></p> 
				</div>
				<div class="div-1">
					<label for="b-number">用餐人數</label>
					<!--  <span>${person_numer}</span>  -->
					<select name="person_numer" id="inputState" class="form-control">
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
						<option value="7">7</option>
						<option value="8">8</option>
						<option value="9">9</option>
						<option value="10">10</option>
					</select>
					<script type="text/javascript">
        				var opts=document.getElementsByTagName("option");
       					opts[${person_numer}-1].selected=true;
   					</script>
				</div>
				<h1>付款資訊</h1>
				<div class="div-1">
					<label for="price">訂金</label> <span>500 元</span>
				</div>


				<%-- <div class="div-btn">

					<button name="confirm" value="confirm" class="btn btn-success">Confirm</button>
					<!-- 隱藏資訊 -->
					<Input type='hidden' name='res_name' value='${res_name}'> 
					<Input type='hidden' name='book_date' value='${book_date}'> 
					<Input type='hidden' name='person_numer' value='${person_numer}'>
					<Input type='hidden' name='r_id' value='${r_id}'>
					<Input type='hidden' name='time' value='${time}'> 

					<!-- 隱藏資訊 -->
					<button  name="back" value="back" class="btn btn-secondary">Back</button>
					<Input type='hidden' name='res_name' value='${res_name}'> 
					<Input type='hidden' name='book_date' value='${book_date}'> 
					<Input type='hidden' name='person_numer' value='${person_numer}'>

				</div>  --%>
				
					<input type="hidden" id="finalDecision" name="finalDecision" value=""> 
					<Input type='hidden' name='res_name' value='${res_name}'> 
					<Input type='hidden' name='book_date' value='${book_date}'> 
					<Input type='hidden' name='person_numer' value='${person_numer}'>
					<Input type='hidden' name='r_id' value='${r_id}'>
				<div class="div-btn">
					<input type="button" class="btn btn-success" name="confirm" value="confirm" onclick="reconfirmOrder()"> 
					<input type="button" class="btn btn-secondary" name="CancelBtn" value="cancel" onclick="cancelOrder()">
				</div>

			</div>
		</div>
	</FORM>
	<script type="text/javascript">
	
	function checkPhone() {
        var phone = document.getElementById('b-phone').value;
        if (!(/^09[0-9]{8}$/.test(phone))) {
            return document.getElementById('uidt').innerHTML = '請輸入正確的手機號';
            return false;
        } else { return document.getElementById('uidt').innerHTML = 'ok'; }
    }
	
 function cancelOrder() {
	if (confirm("確定取消此份訂單 ? ") ) {
		// 接收此資料的Servlet會使用 finalDecision 參數的值
		document.forms["form1"].finalDecision.value = "CANCEL";
		document.forms["form1"].action="<c:url value='/iring29/Restaurant_index.jsp' />";
		document.forms["form1"].method="POST";
		document.forms["form1"].submit();
		return;
	} else {
		return;
	}
} 
function reconfirmOrder() {
	var time = document.getElementById('inputState').value;
	if  (time === "") {
		window.alert ('請選擇用餐時間');
		return ; 
	}
	var name = document.getElementById('b-name').value;
	if  (name === "") {
		window.alert ('請填入訂餐人姓名');
		return ; 
	}
	var phone = document.getElementById('b-phone').value;
	if  (phone === "") {
		window.alert ('請填入訂餐人電話');
		return ; 
	}
	if (confirm("確定送出此份訂單 ? ") ) {
		console.log(document.forms["form1"]);
		console.log(document.forms["form1"].finalDecision.value);
		document.forms["form1"].finalDecision.value = "ORDER";
		console.log(document.forms["form1"].finalDecision.value);
		document.forms["form1"].action="<c:url value='OrderListServlet'/>";
		document.forms["form1"].method="POST";
		document.forms["form1"].submit();
		return;
	} else {
		return;
	}
}
</script>
</body>
</html>