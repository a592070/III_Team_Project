<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Restaurant HomePage</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
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
            width: 850px;
            height: 300px;
            margin: auto;
			overflow: hidden;
			display:flex;
        }
		img{
		width: 100%;
		}

        div {
            border: 1px solid black;
        }
        h2 {
            padding: 20px;
        }

        .title {
            text-align: center;
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
            position: relative;
            background-color: #eeeeee;
            padding: 10px 30px;
            /* font-size: 15px; */
            /* font-weight: 400; */
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
        
        .sp_rating{
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

<div class="box">
        <div>
            <h2 class="title">餐廳資訊</h2>
            <div class="top">
                <div class="booking">訂位</div>
                <div class="info">資訊</div>
                <div class="comment">評論</div>
            </div>
            <p class="modify-img"><button>modify</button></p>
            <div class="div_img">
               <img src="${r_hp.picture}">
            </div>
            <div class="div-1">
                <h3>${r_hp.name}</h3>
            </div>
            <div>
                <h4 class="res_data">餐廳地點</h4>
            </div>
            <div>
                <p class="modify"><button>modify</button></p>
                <h4 class="res_result">地址</h4>
                <p class="p_result">${r_hp.address}</p>
            </div>
            <div>
                <p class="modify"><button>modify</button></p>
                <h4 class="res_result">交通方式</h4>
                <p class="p_result">${r_hp.transportation}</p>
            </div>
            <div>
                <h4 class="res_data">菜色介紹</h4>
            </div>
            <div>
                <p class="modify"><button>modify</button></p>
                <h4 class="res_result">適合聚餐類型</h4>
                <p class="p_result">${r_hp.serviceinfo}</p>
            </div>
            <div>
                <p class="modify"><button>modify</button></p>
                <h4 class="res_result">料理種類</h4>
                <p class="p_result">${r_hp.type}</p>
            </div>
            <div>
                <h4 class="res_data">餐廳資訊</h4>
            </div>
            <div>
                <p class="modify"><button>modify</button></p>
                <h4 class="res_result">營業時間</h4>
                <p class="p_result">${r_hp.opentime}</p>
            </div>
            <div>
                <p class="modify"><button>modify</button></p>
                <h4 class="res_result">餐廳描述</h4>
                <p class="p_result">${r_hp.description}</p>
            </div>
            <div>
            	<h4 class="res_result">我的訂單</h4>
                <c:forEach var="roBean" items="${roBean}">
                	<a class="p_result">${roBean.r_sn_order}</a>
                </c:forEach>
            </div>
        </div>

    </div>
</body>
</html>