<%@ page import="iring29.bean.RestaurantBean" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
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

        .div1 {
            margin: 50px auto;
            width: 800px;
            height: 100px;
            border: 1px solid black;
        }
        .div2{
            width: 600px;
            float: left;
        }
        a{
            width: 100px;
            height: 50px;
        }
        .go{
            margin-top: 25px;
            border: black;
            width: 50px;
            height: 50px;
        }
        h4 {
            padding-left: 30px;
            padding-top: 15px;
            margin-bottom: 15px;
        }

        .sp_result {
            padding: 30px;
        }

        .button {
            text-align: center;
        }
    </style>
</head>

<body>

    <div class="div_box">
        <div class="div_search">
            <div class="search">
                <span class="sp_search">餐廳地區搜尋</span>
                <input type="text" placeholder="請輸入關鍵字">
            </div>
            <div class="search">
                <span class="sp_search">餐廳名稱搜尋</span>
               <input type="text" placeholder="請輸入關鍵字">
            </div>
            <div class="search_date">
                <span class="sp_search">日期</span>
               <input type="date">
            </div>
            <div class="search">
                <label for="">人數 </label> 
                <select name="" id="">
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                </select></div>
            <div class="search"><button> Search</button></div>
        </div>

    </div>

    
    <c:forEach var="res" items="${res_data_region}">
    	<div class="div1">
        	<div class="div2">
            <h4>${res.name}</h4>
         
            <!-- <span class="sp_result">region</span> -->
            <%-- <span class="sp_result">rating：${res.rating}</span> --%>
            <span class="sp_result">${res.type}</span>
            
        </div>
        
        <button class="go"><a href="">Go</a></button>
        
    	</div>
    </c:forEach>
    
</body>

</html>