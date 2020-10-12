<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登入</title>
<style>
	.img {
            width: 13px;
        }
</style>
</head>
<body>
<jsp:include page="/fragment/header.jsp" />
<%-- 	<img src="<%=application.getContextPath()%>/Homepage" method="get" /> --%>
	<FORM ACTION="<%=pageContext.getServletContext().getContextPath()%>/LoginServlet" method="POST" name="loginForm">
		<div id='content' align="center">
			<Table
				style="border-width: 2; background: #E0E0E0; width: 500px; border-style: inset">
				<tr>
					<td>帳號: <INPUT  TYPE="TEXT" id="userName" NAME="userName" onblur="checkusr()" />
						<img class="img" id="idfimg" src=""><span id="idsp"></span><br />
					</td>
				</tr>
				<tr>
					<td><BR> 密碼: <INPUT TYPE="PASSWORD" id="password" NAME="password" onblur="checkpwd()" />
						<img class="img" id="idfimg2" src=""><span id="idsp2"></span><br />
						</td>
				</tr>

				<tr>
					<td>
						<CENTER>
							<INPUT TYPE="SUBMIT" id="submit" value="登入" disabled>
						</CENTER>
					</td>
				</tr>
				<tr>
					<td align="CENTER"><Font color='red' size="-1">
					${ErrorMsgKey.LoginError}</Font>
	</FORM>
	</Table>
	</div>
	<script>
		  function checkusr() {
			let theUsrObjVal = document.getElementById("userName").value;
		    // let disable = document.getElementById("submit").disabled;
			
            let sp = document.getElementById("idsp");
            let theUsrlen = theUsrObjVal.length;

			if (theUsrObjVal == "") {
                sp.innerHTML = "帳號不可為空白"
                sp.style.color = "red";
                sp.style.fontSize = "13px";
                sp.style.fontStyle = "italic";
                document.getElementById("idfimg").src = "/III_Team_Project/rambo0021/Images/error.png"
				document.getElementById("submit").disabled = true;
			}else{
				sp.innerHTML ="";
				document.getElementById("idfimg").src = ""
				document.getElementById("submit").disabled = false;
			}
		  }
		  function checkpwd() {
			let theUsrObjVal = document.getElementById("password").value;
		    let disable = document.getElementById("submit").disabled;
            let sp = document.getElementById("idsp2");
            let theUsrlen = theUsrObjVal.length;

			if (theUsrObjVal == "") {
                sp.innerHTML = "密碼不可為空白"
                sp.style.color = "red";
                sp.style.fontSize = "13px";
                sp.style.fontStyle = "italic";
                document.getElementById("idfimg2").src = "/III_Team_Project/rambo0021/Images/error.png"
				document.getElementById("submit").disabled = true;
			}else{
				sp.innerHTML ="";
				document.getElementById("idfimg2").src ="";
				document.getElementById("submit").disabled = false;
			}
		  }
	</script>
</body>
</html>