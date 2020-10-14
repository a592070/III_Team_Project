<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modify Image</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
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
            width: 150px;
            float: left;
            padding-right: 20px;
            /* margin-left: 50px; */
            text-align: right;
        }
        .div-btn {
			text-align: center;
		}
		img{
			width:300px;
			height:200px;
		}
    </style>
</head>

<body>
<!-- 引入共同的頁首 -->
<jsp:include page="/fragment/header.jsp" />
<div class="wrapper">
	<FORM id="formL" name="formL" action="<%=pageContext.getServletContext().getContextPath()%>/Restaurant_HPServlet" method="POST">
	
        <div class="container">
            <h2>修改${r_hp.name}地點資訊</h2>
            <div class="">
                <div class="div-1">
                    <label for="r-name">請提供圖片網址</label> 
                    <textarea name="picture" id="newimg" cols="40" rows="5" placeholder="http://" onblur='checkimg1()' ></textarea>
                </div>
	
				<div class="div-1">
				<label for="r-name">顯示圖片如下：</label> 
				<img src="${r_hp.picture}" alt="" id="img_id"/>
				</div>
            </div>
        </div>
        
        <input type="hidden" id="finalDecision" name="finalDecision" value=""/>
        	<!-- 隱藏資訊 -->
        	<Input type='hidden' name='rBean' value='${r_hp}'/>
        	<Input type='hidden' name='roBean' value='${roBean}'/>
        
        <div class="div-btn">
       		<input type="button" class="btn btn-primary" name="confirm" value="confrim" onclick="confirmL()"/>
		    <input type="button" class="btn btn-secondary" name="CancelBtn" value="cancel" onclick="cancelL()"/>
        </div>
        
	</FORM>

</div>
<script type="text/javascript">
    function checkimg1() {
        var newimg = document.getElementById('newimg').value;
        // var img = document.querySelector("img");
        var img = document.getElementById("img_id");
        if (newimg != "") {
            img.src= newimg;
        } else {
            img.src='${r_hp.picture}';
        }
    }

    function confirmL(){
        if (confirm("確定送出修改 ? ") ) {
            console.log(document.forms["formL"]);
            console.log(document.forms["formL"].finalDecision.value);
            document.forms["formL"].finalDecision.value = "confirmL";
            console.log(document.forms["formL"].finalDecision.value);
            // $("#finalDecision").val("confirm-img");
            document.forms["formL"].action="<%=application.getContextPath()%>/Restaurant_HPServlet";
            document.forms["formL"].method="POST";
            document.forms["formL"].submit();
            return;
        } else {
            return;
        }
    }
    function cancelL(){
        if (confirm("確定取消修改 ? ") ) {
            console.log(document.forms["formL"]);
            console.log(document.forms["formL"].finalDecision.value);
            document.forms["formL"].finalDecision.value = "cancel";
            console.log(document.forms["formL"].finalDecision.value);
            document.forms["formL"].action="<%=application.getContextPath()%>/Restaurant_HPServlet";
            document.forms["formL"].method="POST";
            document.forms["formL"].submit();
            return;
        } else {
            return;
        }
    }
</script>
</body>
</html>