<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Order List</title>
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
        label{
            width: 100px;
            float: left;
            padding-right:20px;
            /* margin-left: 50px; */
            text-align: right;
        } 
        
        .div-btn{
            text-align: center;
        }
        
        input{
            padding-left: 10px;
        }
    </style>
</head>
<body>
<FORM  action="<c:url value='OrderListServlet'/>" method="POST">
<div class="wrapper">
        <div class="container">

                <h1>
                    訂單訊息
                </h1>
                <div class="">
                    <div class="div-1">
                        <label for="r-name">訂位餐廳</label>
                        <span>${res_name}</span>
                    </div>

                    <div class="div-1">
                        <label for="r-date">用餐日期</label>
                        <span>${book_date}</span>
                    </div>
                    <div class="div-1">
                        <label for="b-name">訂位人姓名</label>
                        <input type="text" name="b-name" placeholder="請輸入完整姓名">
                    </div>
                </div>
                <div class="div-1">
                    <label for="b-phone">訂位人手機</label>
                    <input type="text" name="b-phone" placeholder="請輸入手機號碼">
                </div>
                <div class="div-1">
                    <label for="b-number">用餐人數</label>
                    <span>${person_numer}</span>
                </div>
                <h1>
                    付款資訊
                </h1>
                <div class="div-1">
                    <label for="price">訂金</label>
                    <span>500 元</span>
                </div>
                
                <div class="div-btn">
                
                <button>Confirm</button>
                <!-- 隱藏欄位都會送到後端 /BookRestaurantServlet-->  
          		<Input type='hidden' name='res_name' value='${res_name}'>
          		<Input type='hidden' name='book_date' value='${book_date}'>
          		<Input type='hidden' name='person_numer' value='${person_numer}'>
          		<Input type='hidden' name='r_id' value='${r_id}'>
          		
          		<a href="<c:url value='/iring29/DisplayRestaurant.jsp'/>"><button>Back</button></a>
            </div>
            
        </div>
    </div>
    </FORM>
</body>
</html>