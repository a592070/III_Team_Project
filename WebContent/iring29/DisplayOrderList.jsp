<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Order List</title>

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
        
        .div-btn-1{
        	/* width: 600px; */
            text-align: center;
            float: left;
            disply:inline;
            padding-left:100px;
        }
        .div-btn{
        	/* width: 600px; */
            text-align: center;
            /* float:flex */
            disply:inline;
            padding:0px;
            margin:0px;
            /* width: 150px; */
            
        }
        .form{
			margin:0px;
			
			display: inline;
			
		}
        input{
            padding-left: 10px;
        }
    </style>
</head>
<body>
<jsp:include page="/fragment/header.jsp" />


<div class="wrapper">
        <div class="container">
            <form name="form-c" action="<c:url value='OrderListServlet' />" method="POST">
                
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
                        <label for="r-name"><b>訂位餐廳</b></label>
                        <span>${r_name}</span>
                    </div>

                    <div class="div-1">
                        <label for="r-date"><b>用餐日期</b></label>
                        <span id="b-time"></span>
                   <script>
						let bspan = document.getElementById("b-time");
						let bTime = new Date("${roBean.booking_date}").toLocaleString();
						bspan.innerHTML= bTime;
					</script>     
                        
                    </div>
                    <div class="div-1">
                        <label for="b-name"><b>訂位人姓名</b></label>
                        <span>${roBean.customerName}</span>
                    </div>
                </div>
                <div class="div-1">
                    <label for="b-phone"><b>訂位人手機</b></label>
                    <span>${roBean.customerPhone}</span>
                </div>
                <div class="div-1">
                    <label for="b-number"><b>用餐人數</b></label>
                    <span>${roBean.customerNum}</span>
                </div>
                <h1> 付款資訊</h1>
                <div class="div-1">
                    <label for="price"><b>尚未付款</b></label>
                    <span>500 元</span>
                </div>
                <div class="div-btn-1">
                <input type="hidden" id="c" name="c" value=""> 
                
                <!-- 隱藏欄位都會送到後端 /BookRestaurantServlet-->  
          		<Input type='hidden' name='r_sn_order' value='${roBean.r_sn_order}'>
                <input type="button" class="btn btn-secondary" name="CancelBtn" value="取消訂單" onclick="cancelOrder()">
             </div>
            </form>
            
            
             <div class="div-btn"> 
             <FORM  class="form" action="<c:url value='/iring29/Restaurant_index.jsp' />" method="POST">
                <button class="btn btn-success" name="" value="">訂其他餐廳</button>
             </FORM>
             </div> 
            
        </div>
    </div>
    <script type="text/javascript">
    function cancelOrder() {
    	if (confirm("確定取消此份訂單 ? ") ) {
    		// 接收此資料的Servlet會使用 finalDecision 參數的值
    		document.forms["form-c"].c.value="cancel";
    		document.forms["form-c"].action="<c:url value='OrderListServlet' />";
    		document.forms["form-c"].method="POST";
    		document.forms["form-c"].submit();
    		return;
    	} else {
    		return;
    	}
    } 
    </script>
</body>
</html>