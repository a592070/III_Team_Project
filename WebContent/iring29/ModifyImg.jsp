<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modify Image</title>
<script src="${pageContext.servletContext.contextPath}/static/jquery-3.5.1.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
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
            width: 800px;
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
<!-- 引入共同的頁首 -->
<jsp:include page="/fragment/header.jsp" />
<div class="wrapper">
	<FORM action="<%=pageContext.getServletContext().getContextPath()%>/Restaurant_HPServlet" method="POST">
	
        <div class="container">
            <h2>修改${r_hp.name}地點資訊</h2>
            <div class="">
                <div class="div-1">
                    <label for="r-name">請輸入圖片網址</label> 
                    <textarea name="picture" id="" cols="40" rows="5" placeholder="${r_hp.picture}"></textarea>
                </div>


            </div>
        </div>
        <div class="div-btn">
       		<button  name="confirm-img" value="confirm" class="btn btn-primary">確認</button>
        	<button  name="cancel" value="cancel" class="btn btn-secondary">取消</button>
        	
        	<!-- 隱藏資訊 -->
        	<Input type='hidden' name='rBean' value='${r_hp}'>
        	<Input type='hidden' name='roBean' value='${roBean}'>
        </div>
        
	</FORM>
</div>

</body>
</html>