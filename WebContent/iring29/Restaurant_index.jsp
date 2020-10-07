<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Search</title>
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
            padding:25px;
        }
        .sp_search{
            /* border: 1px solid black; */
            width: 100px;
            float: left;
            padding-top:10px;
            margin-left: 50px;
            text-align: right;
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
	<FORM
		ACTION="<%=pageContext.getServletContext().getContextPath()%>/RestaurantServlet"
		method="post">
    <div class="div_box">
        <div class="div_search">
            <div class="search">
                <span class="sp_search">餐廳地區搜尋</span>
                <input type="text" name="region_name" placeholder="請輸入關鍵字">
            </div>
            <div class="search">
                <span class="sp_search">餐廳名稱搜尋</span>
               <input type="text" name="restaurant_name" placeholder="請輸入關鍵字">
            </div>
            <div class="search_date">
                <span class="sp_search">日期</span>
               <input type="date" name="date">
            </div>
            <div class="search">
                <label for="">人數 </label> 
                <select name="person_numer">
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
                </select></div>
            <div class="search"><button name="QUERY" type="SUBMIT" value="QUERY"> Search</button></div>
        </div>

    </div>
</FORM>
</body>

</html>