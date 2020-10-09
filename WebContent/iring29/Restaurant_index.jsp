<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Search</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<style>
* {
	margin: 0;
	padding: 0;
}

.div_box {
	margin: 50px auto;
	width: 500px;
	/* height: 400px; */
	/* border: 1px solid black; */
}

.div_search {
	width: 500px auto;
	border: 1px solid black;
	/* display: flex; */
}

.search {
	/* width: 500px; */
	text-align: center;
	border-bottom: solid 1px #c1c0c1;
	cursor: pointer;
	padding: 20px;
}

.search_date {
	/* width: 500px; */
	text-align: center;
	border-bottom: solid 1px #c1c0c1;
	cursor: pointer;
	padding: 25px;
}

.sp_search {
	/* border: 1px solid black; */
	width: 100px;
	float: left;
	padding-top: 10px;
	margin-left: 50px;
	text-align: right;
}
.sp_search-1{
	width: 100px;
	float: left;
	padding-top: 10px;
	margin-left: 50px;
	margin-right: 28px;
	text-align: right;
}
#inputState.form-control{
	width: 250px;
	padding-left: 20px;
}
input {
	width: 250px;
	line-height: 35px;
}

button {
	padding: 5px;
	margin-left: 10px;
}
</style>
</head>

<body>
	<header class="">
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
			<a class="navbar-brand" href="javascript:void(0)">Logo</a>
			<button class="navbar-toggler navbar-toggler-right" type="button"
				data-toggle="collapse" data-target="#navb">
				<span class="navbar-toggler-icon"></span>
			</button>


			<div class="collapse navbar-collapse  nav justify-content-center"
				id="navb">
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link"
						href="javascript:void(0)">找旅館</a></li>
					<li class="nav-item"><a class="nav-link"
						href="javascript:void(0)">找餐廳</a></li>
					<li class="nav-item"><a class="nav-link"
						href="javascript:void(0)">找租車</a></li>
					<li class="nav-item"><a class="nav-link"
						href="javascript:void(0)">找景點</a></li>

					<li class="nav-item"><a class="nav-link disabled"
						href="javascript:void(0)">Disabled</a></li>
				</ul>
			</div>
			<div>
				<form class="form-inline my-3">
					<input class="form-control mr-sm-2" type="text"
						placeholder="Search" />
					<button class="btn btn-success my-2 my-sm-0" type="button">Search</button>
				</form>
			</div>
			<div class="btn-group">
				<button type="button" class="btn btn-primary">登陸</button>
				<button type="button" class="btn btn-primary">註冊</button>
				<button type="button" class="btn btn-primary">論壇</button>
			</div>
		</nav>
	</header>

	<FORM
		ACTION="<%=pageContext.getServletContext().getContextPath()%>/RestaurantServlet"
		method="post">
		<div class="div_box">
			<div class="div_search">
				<div class="search" class="form-group col-md-2">
					<span class="sp_search-1" >餐廳地區搜尋</span>
						<select name="region_name" id="inputState" class="form-control">
						<option value="">請選擇地區</option>
						<option value="基隆">基隆</option>
						<option value="新北">新北</option>
						<option value="台北">台北</option>
						<option value="桃園">桃園</option>
						<option value="新竹">新竹</option>
						<option value="苗栗">苗栗</option>
						<option value="台中">台中</option>
						<option value="彰化">彰化</option>
						<option value="南投">南投</option>
						<option value="雲林">雲林</option>
						<option value="嘉義">嘉義</option>
						<option value="台南">台南</option>
						<option value="高雄">高雄</option>
						<option value="屏東">屏東</option>
						<option value="宜蘭">宜蘭</option>
						<option value="花蓮">花蓮</option>
						<option value="台東">台東</option>
						<option value="澎湖">澎湖</option>
						<option value="金門">金門</option>
						<option value="連江">連江</option>
					</select>
				</div>
				<div class="search">
					<span class="sp_search">餐廳名稱搜尋</span> <input type="text"
						name="restaurant_name" placeholder="請輸入關鍵字">
				</div>
				<div class="search_date">
					<span class="sp_search">日期</span> 
					<input type="date" name="book_date" id="theDate">
					<script>
						var date = new Date();

						var day = date.getDate();
						var month = date.getMonth() + 1;
						var year = date.getFullYear();

						if (month < 10)
							month = "0" + month;
						if (day < 10)
							day = "0" + day;

						var today = year + "-" + month + "-" + day;
						console.log(typeof(today));
						document.getElementById("theDate").value = today;
					</script>
				</div>
				<div class="search"  class="form-group col-md-2">
					<span class="sp_search-1">人數</span> 
					<select name="person_numer" id="inputState" class="form-control">
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="5">6</option>
						<option value="5">7</option>
						<option value="5">8</option>
						<option value="5">9</option>
						<option value="5">10</option>
					</select>
				</div>
				<div class="search">
					<button name="QUERY" type="SUBMIT" value="QUERY" class="btn btn-secondary">Search</button>
				</div>
			</div>

		</div>
	</FORM>
</body>

</html>