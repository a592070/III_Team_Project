<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Order List</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

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

        h1{
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

<header class="">
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <a class="navbar-brand" href="javascript:void(0)">Logo</a>
        <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navb">
            <span class="navbar-toggler-icon"></span>
        </button>


        <div class="collapse navbar-collapse  nav justify-content-center" id="navb">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="javascript:void(0)">找旅館</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="javascript:void(0)">找餐廳</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="javascript:void(0)">找租車</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="javascript:void(0)">找景點</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link disabled" href="javascript:void(0)">Disabled</a>
                </li>
            </ul>
        </div>
        <div>
            <form class="form-inline my-3">
                <input class="form-control mr-sm-2" type="text" placeholder="Search"/>
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

<div class="wrapper">
        <div class="container">
            <form action="<c:url value='OrderListServlet' />" method="POST">
                
               		<h1> 訂位成功 </h1>
                    <h1> Fun Taiwan 訂單號碼 </h1>
                    <div class="div-1">
                        <span>${roBean.order_id}</span>
                    </div>
                    <h1> 餐廳訂單號碼 </h1>
                    <div class="div-1">
                        <span>${roBean.r_sn_order}</span>
                    </div>
                
                <h1>  訂單訊息 </h1>
                <div class="">
                    <div class="div-1">
                        <label for="r-name">訂位餐廳</label>
                        <span>${r_name}</span>
                    </div>

                    <div class="div-1">
                        <label for="r-date">用餐日期</label>
                        <span>${roBean.booking_date}</span>
                    </div>
                    <div class="div-1">
                        <label for="b-name">訂位人姓名</label>
                        <span>${bean.customerName}</span>
                    </div>
                </div>
                <div class="div-1">
                    <label for="b-phone">訂位人手機</label>
                    <span>${bean.customerPhone}</span>
                </div>
                <div class="div-1">
                    <label for="b-number">用餐人數</label>
                    <span>${roBean.customerNum}</span>
                </div>
                <h1> 付款資訊</h1>
                <div class="div-1">
                    <label for="price">尚未付款</label>
                    <span>500 元</span>
                </div>
                <div class="div-btn">
                <button class="btn btn-secondary" name="cancel" value="cancel">cancel order</button>
                <!-- 隱藏欄位都會送到後端 /BookRestaurantServlet-->  
          		<Input type='hidden' name='r_sn_order' value='${roBean.r_sn_order}'>
            </div>
            </form>
            
            <FORM  action="<c:url value='/iring29/Restaurant_index.jsp' />" method="POST">
            <div class="div-btn">
                <button class="btn btn-secondary" name="" value="">訂其他餐廳</button>
            </div>
            </FORM>
        </div>
    </div>
</body>
</html>