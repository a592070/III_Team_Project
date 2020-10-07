<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Restaurant HomePage</title>
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
                <h3>Restaurant</h3>
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
        </div>

    </div>
</body>
</html>