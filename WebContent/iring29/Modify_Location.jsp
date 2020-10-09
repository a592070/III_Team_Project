<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modify Location</title>
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

        h2 {
            margin-left: 50px;
            padding-bottom: 30px;
        }

        .div-1 {
            margin-left: 50px;
            padding-bottom: 20px;
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
    </style>
</head>

<body>

<div class="wrapper">
	<FORM action="<%=pageContext.getServletContext().getContextPath()%>/Restaurant_HPServlet" method="POST">
	
        <div class="container">
            <h2>修改餐廳地點資訊</h2>
            <div class="">
                <div class="div-1">
                    <label for="r-name">地址</label> <textarea name="address" id="" cols="40" rows="5" placeholder="${r_hp.address}"></textarea>
                </div>

                <div class="div-1">
                    <label for="r-date">交通方式</label> <textarea name="transportation" id="" cols="40" rows="5" placeholder="${r_hp.transportation}"></textarea>
                </div>

            </div>
        </div>
        <div class="div-btn">
        	<button  name="cancel" value="cancel" class="btn btn-secondary">取消</button>
        	<button  name="confirm-location" value="confirm" class="btn btn-secondary">確認</button>
        	<!-- 隱藏資訊 -->
        	<Input type='hidden' name='rBean' value='${r_hp}'>
        	<Input type='hidden' name='roBean' value='${roBean}'>
        </div>
        
	</FORM>
</div>

</body>
</html>