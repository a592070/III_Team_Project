<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>註冊頁面</title>
    <script src="https://code.jquery.com/jquery-3.5.1.js"
        integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
    <!-- <script src="https://www.w3schools.com/lib/w3.js"></script> -->

    <style>


        form {
            display: inline-block;
        }

        fieldset {
            width: 430px;
            margin: 15px;
            border: 2px solid gray;
            border-radius: 15px;
        }

        .st1 {
            width: 400px;
            border-bottom: 1px solid gray;
            margin: 5px;
            padding-bottom: 5px;
        }

        .img {
            width: 13px;
        }
    </style>
    <script src="${pageContext.servletContext.contextPath}/static/jquery-3.5.1.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>

<body class="body">
    <jsp:include page="/fragment/header.jsp" />
<div style="margin:0 auto;width:300px" >
    <form ACTION="<%=pageContext.getServletContext().getContextPath()%>/registerServlet" enctype="multipart/form-data"
        method="post">
        <fieldset>
            <legend>註冊</legend>
            <div class="form-group">
                我是:<select name="identity" id="identity">
                    <option value="2">一般會員</option>
                    <option value="3">餐廳業者</option>
                    <option value="4">住宿業者</option>
                    <option value="5">交通業者</option>
                </select>
            </div>
            <div class="form-group">
                <label for="username">帳號:</label>
                <input type="text" id="username" name="username" placeholder="請輸入帳號" onblur="checkusr()" />
                <img class="img" id="idfimg" src=""><span id="idsp"></span><br />

            </div>
            <div class="form-group">
                <label for="password">密碼:</label>
                <input type="password" id="password" name="password" autocomplete="off" placeholder="請輸入密碼" />
                <img class="img" id="idfimg2" src=""><span id="idsp2"></span><br />

            </div>
            <div class="form-group">
                <label for="checkpwd">密碼確認:</label>
                <input type="password" id="checkpassword" name="checkpassword" placeholder="請確認密碼"
                    onblur="checkpwd()" />
                <img class="img" id="idfimg3" src=""><span id="idsp3"></span><br />
            </div>
        </fieldset>
        <fieldset>
            <legend>基本資料</legend>
            <div class="form-group">
                <label for="nickname">暱稱:</label>
                <input type="text" id="nickname" name="nickname">
                <img class="img" id="idfimg4" src=""><span id="idsp"></span><br />

            </div>
            <div class="form-group">
                <label for="email">電子信箱email:</label>
                <input type="text" id="email" name="email">
                <img class="img" id="idfimg5" src=""><span id="idsp"></span><br />
            </div>
            <div class="form-group">
                <label for="picture">照片:</label>
                <input type="file" id="picture" name="picture" accept="image/*">
            </div>
        </fieldset>
        <!-- <div style="display:none" id="includeDiv"
                w3-include-html="">
            </div><br> -->
        <!-- r=restaurant h=hotel t=transportation  -->
        <div id="rdiv" style="display:none">

            <fieldset>
                <legend>餐廳資料</legend>
                <div class="form-group">
                    <label for="">餐廳名稱:</label>
                    <input type="text" id="rname" name="rname" placeholder="請輸入餐廳名稱" onblur="" />
                </div>
                <div class="form-group">
                    <label for="">地址:</label>
                    <input type="text" id="raddress" name="raddress" placeholder="請輸入地址" onblur="" />
                </div>
                <div class="form-group">
                    <label for="">營業時間:</label>
                    <input type="text" id="ropentime" name="ropentime" placeholder="請輸入營業時間" onblur="" />
                </div>
                <div class="form-group">
                    <label for="">餐廳簡介:</label>
                    <input type="text" id="rdescription" name="rdescription" placeholder="請輸入餐廳描述" onblur="" />
                </div>
                <div class="form-group">
                    <label for="">交通方式:</label>
                    <input type="text" id="rtransportation" name="rtransportation" placeholder="請輸入交通方式" onblur="" />
                </div>
                <div class="form-group">
                    <label for="">餐廳類型:</label>
                    <input type="text" id="rtype" name="rtype" placeholder="請輸入餐廳類型" onblur="" />
                </div>
                <div class="form-group">
                    <label for="">餐廳照片url:</label>
                    <input type="text" id="rpicture" name="rpicture" placeholder="請輸入照片url" onblur="" />
                </div>
                <div class="form-group">
                    <label for="">餐廳用餐訊息:</label>
                    <input type="text" id="serviceinfo" name="serviceinfo" placeholder="請輸入用餐訊息" onblur="" />
                </div>
            </fieldset>

        </div>
        <div id="hdiv" style="display:none">
            <fieldset>
                <legend>住宿資料</legend>
                <div class="form-group">
                    <label for="">住宿名稱:</label>
                    <input type="text" id="hname" name="hname" placeholder="請輸入住宿名稱" onblur="" class="form-control" />
                </div>
                <div class="form-group">
                    <label for="">縣市:</label>
                    <select name="hregion" id="hregion">
                        <option value="基隆市">基隆市</option>
                        <option value="新北市">新北市</option>
                        <option value="臺北市">臺北市</option>
                        <option value="桃園市">桃園市</option>
                        <option value="新竹市">新竹市</option>
                        <option value="新竹縣">新竹縣</option>
                        <option value="苗栗縣">苗栗縣</option>
                        <option value="宜蘭縣">宜蘭縣</option>
                        <option value="臺中市">臺中市</option>
                        <option value="彰化縣">彰化縣</option>
                        <option value="南投縣">南投縣</option>
                        <option value="雲林縣">雲林縣</option>
                        <option value="嘉義市">嘉義市</option>
                        <option value="嘉義縣">嘉義縣</option>
                        <option value="臺南市">臺南市</option>
                        <option value="高雄市">高雄市</option>
                        <option value="屏東縣">屏東縣</option>
                        <option value="花蓮縣">花蓮縣</option>
                        <option value="臺東縣">臺東縣</option>
                        <option value="澎湖縣">澎湖縣</option>
                        <option value="金門縣">金門縣</option>
                        <option value="連江縣">連江縣</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="">地址:</label>
                    <input type="text" id="haddress" name="haddress" placeholder="請輸入地址" onblur=""
                        class="form-control" />
                </div>
                <div class="form-group">
                    <label for="">電話:</label>
                    <input type="text" id="htel" name="htel" placeholder="請輸入電話" onblur="" class="form-control" />
                </div>
                <!-- d=double q=quadruple -->
                <div class="form-group">
                    <label for="">雙人房價格:</label>
                    <input type="text" id="droom" name="droom" placeholder="請輸入雙人房價格" onblur="" class="form-control" />
                </div>
                <div class="form-group">
                    <label for="">四人房價格:</label>
                    <input type="text" id="qroom" name="qroom" placeholder="請輸入四人房價格" onblur="" class="form-control" />
                </div>
                <div class="form-group">
                    <label for="">住宿簡介:</label>
                    <input type="text" id="hdescription" name="hdescription" placeholder="請輸入住宿描述" onblur=""
                        class="form-control" />
                </div>
                <div class="form-group">
                    <label for="">營業時間:</label>
                    <input type="text" id="hopentime" name="hopentime" placeholder="請輸入營業時間" onblur=""
                        class="form-control" />
                </div>
                <div class="form-group">
                    <label for="">住宿類型:</label>
                    <input type="text" id="htype" name="htype" placeholder="請輸入住宿類型" onblur="" class="form-control" />
                </div>
            </fieldset>
        </div>
        <div id="tdiv" style="display:none">
            <fieldset>
                <legend>車行資料</legend>
                <div class="form-group">
                    <label for="">公司名稱:</label>
                    <input type="text" id="tname" name="tname" placeholder="請輸入公司名稱" onblur="" class="form-control" />
                </div>
                <div class="form-group">
                    <label for="">地址:</label>
                    <input type="text" id="taddress" name="taddress" placeholder="請輸入地址" onblur=""
                        class="form-control" />
                </div>
                <div class="form-group">
                    <label for="">簡介:</label>
                    <input type="text" id="tdescription" name="tdescription" placeholder="請輸入簡介" onblur=""
                        class="form-control" />
                </div>
                <div class=" st1">
                    <label for="">營業時間:</label>
                    <input type="text" id="topentime" name="topentime" placeholder="請輸入營業時間" onblur=""
                        class="form-control" />
                </div>
                <div class="form-group">
                    <label for="">電話:</label>
                    <input type="text" id="ttel" name="ttel" placeholder="請輸入電話" onblur="" class="form-control" />
                </div>
                <div class="form-group">
                    <label for="">租車車種:</label>
                    <input type="text" id="carType" name="carType" placeholder="請輸入租車車種" onblur=""
                        class="form-control" />
                </div>
                <div class="form-group">
                    <label for="">價格:</label>
                    <input type="text" id="tprice" name="tprice" placeholder="價格" onblur="" class="form-control" />
                </div>
            </fieldset>
        </div>

        <input type="submit" id="submit" name="submit" value="送出" disabled class="btn btn-primary">

    </form>
</div>





    <script>

        function checkusr() {
            let username = document.getElementById("username").value;
            let sp = document.getElementById("idsp");
            if (username == "") {
                sp.innerHTML = "帳號不能為空"
                sp.style.color = "red";
                sp.style.fontSize = "13px";
                sp.style.fontStyle = "italic";
                document.getElementById("idfimg").src = "/III_Team_Project/rambo0021/Images/error.png"
                document.getElementById("submit").disabled = true;
            } else {
                sp.innerHTML = "正確";
                sp.style.color = "black";
                sp.style.fontSize = "13px";
                sp.style.fontStyle = "italic";
                document.getElementById("idfimg").src = "/III_Team_Project/rambo0021/Images/check.png"
                document.getElementById("submit").disabled = false;
            }
        }
        function checkpwd() {
            let pwd = document.getElementById("password").value;
            let ckpwd = document.getElementById("checkpassword").value;
            let sp = document.getElementById("idsp3");
            if (pwd == "") {
                sp.innerHTML = "密碼不能為空"
                sp.style.color = "red";
                sp.style.fontSize = "13px";
                sp.style.fontStyle = "italic";
                document.getElementById("idfimg3").src = "/III_Team_Project/rambo0021/Images/error.png"
                document.getElementById("submit").disabled = true;
            } else if (pwd == ckpwd) {
                sp.innerHTML = "正確";
                sp.style.color = "black";
                sp.style.fontSize = "13px";
                sp.style.fontStyle = "italic";
                document.getElementById("idfimg3").src = "/III_Team_Project/rambo0021/Images/check.png"
                document.getElementById("submit").disabled = false;
            } else {
                sp.innerHTML = "密碼與確認密碼不符"
                sp.style.color = "red";
                sp.style.fontSize = "13px";
                sp.style.fontStyle = "italic";
                document.getElementById("idfimg3").src = "/III_Team_Project/rambo0021/Images/error.png"
                document.getElementById("submit").disabled = true;
            }
        }
        $(document).ready(function () {
            $('#identity').change(function () {
                let identity = document.getElementById("identity").value;
                if (identity == 2) {
                    // $("#includeDiv").css("display", "none");
                    // $("#includeDiv").attr("w3-include-html", "")
                    // w3.includeHTML();
                    $("#rdiv").css("display", "none");
                    $("#hdiv").css("display", "none");
                    $("#tdiv").css("display", "none");
                }
                else if (identity == 3) {
                    // $("#includeDiv").css("display", "block");

                    // $("#includeDiv").attr("w3-include-html", "<%=pageContext.getServletContext().getContextPath()%>/rambo0021/restaurantForm.jsp")

                    // w3.includeHTML();
                    $("#rdiv").css("display", "block");
                    $("#hdiv").css("display", "none");
                    $("#tdiv").css("display", "none");
                }
                else if (identity == 4) {
                    // $("#includeDiv").css("display", "block");
                    // $("#includeDiv").attr("w3-include-html", "<%=pageContext.getServletContext().getContextPath()%>/rambo0021/restaurantForm.jsp")
                    // w3.includeHTML();
                    $("#rdiv").css("display", "none");
                    $("#hdiv").css("display", "block");
                    $("#tdiv").css("display", "none");
                }
                else if (identity == 5) {
                    // $("#includeDiv").css("display", "block");
                    // $("#includeDiv").attr("w3-include-html", "<%=pageContext.getServletContext().getContextPath()%>/rambo0021/restaurantForm.jsp")
                    // w3.includeHTML();
                    $("#rdiv").css("display", "none");
                    $("#hdiv").css("display", "none");
                    $("#tdiv").css("display", "block");
                }


                // $.ajax(
                //     {
                //         type: 'POST',
                //         data: { identity: identity },
                //         url: '../StoreAjaxController',
                //         success:function(identity){

                //         }
                //     }
                // )
            })

        })

    </script>
</body>

</html>