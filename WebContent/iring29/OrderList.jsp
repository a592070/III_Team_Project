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
<div class="wrapper">
        <div class="container">
            <form action="" method="POST">

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
                        <input type="text" name="f-name" placeholder="請輸入完整姓名">
                    </div>
                </div>
                <div class="div-1">
                    <label for="b-phone">訂位人手機</label>
                    <input type="text" name="l-name" placeholder="請輸入手機號碼">
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
                <button><a href="<c:url value='/iring29/DisplayRestaurant.jsp'/>">Back</a></button>
            </div>
        </div>
        </form>
    </div>
</body>
</html>