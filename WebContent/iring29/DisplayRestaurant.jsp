<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Restaurant Info</title>
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
    </style>
</head>
<body>
<div class="box">
        <div>
            <h2 class="title">餐廳資訊</h2>
            <div class="top">
                <div class="booking">訂位</div>
                <div class="info">資訊</div>
                <div class="comment">評論</div>
            </div>
            <div class="div_img" >
                <img src="${res_data.picture}">
            </div>
            <div class="div-1">
                <h3>${res_data.name}</h3>
                    <h4 class="rating">Rating</h4>
                    <span class="sp_rating">${res_data.rating}</span>
               
                <div class="book-btn">Booking</div>
            </div>
            <div>
                <h4 class="res_data">餐廳地點</h4>
            </div>
            <div>
                <h4 class="res_result">地址</h4>
                <p class="p_result">${res_data.address}</p>
            </div>
            <div>
                <h4 class="res_result">交通方式</h4>
                <p class="p_result">${res_data.transportation}</p>
            </div>
            <div>
                <h4 class="res_data">菜色介紹</h4>
            </div>
            <div>
                <h4 class="res_result">適合聚餐類型</h4>
                <p class="p_result">${res_data.serviceinfo}</p>
            </div>
            <div>
                <h4 class="res_result">料理種類</h4>
                <p class="p_result">${res_data.type}</p>
            </div>
            <div>
                <h4 class="res_data">餐廳資訊</h4>
            </div>
            <div>
                <h4 class="res_result">營業時間</h4>
                <p class="p_result">${res_data.opentime}</p>
            </div>
            <div>
                <h4 class="res_result">餐廳描述</h4>
                <p class="p_result">${res_data.description}</p>
            </div>
        </div>

    </div>
</body>
</html>