<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>註冊頁面</title>
    <script src="https://code.jquery.com/jquery-3.5.1.js"
        integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
    <style>
        body {
            font-family: 標楷體;
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
</head>

<body>
<jsp:include page="/rambo0021/top.jsp" />
    <div>
        <form ACTION="<%=pageContext.getServletContext().getContextPath()%>/registerServlet"
            enctype="multipart/form-data" method="post">
            <fieldset>
                <legend>註冊</legend>
                <div>
                    我是:<select name="identity">
                        <option value="2">一般會員</option>
                        <option value="3">餐廳業者</option>
                        <option value="4">住宿業者</option>
                        <option value="5">交通業者</option>
                    </select>
                </div>
                <div class="st1">
                    <label for="username">帳號:</label>
                    <input type="text" id="username" name="username" placeholder="請輸入帳號">
                    <!-- onblur="checkusr()"> -->
                    <img class="img" id="idfimg" src=""><span id="idsp"></span><br />

                </div>
                <div class="st1">
                    <label for="password">密碼:</label>
                    <input type="password" id="password" name="password" autocomplete="off" placeholder="請輸入密碼">
                    <!-- onblur="checkpwd()"> -->
                    <img class="img" id="idfimg2" src=""><span id="idsp2"></span><br />

                </div>
                <div class="st1">
                    <label for="checkpwd">密碼確認:</label>
                    <input type="password" id="checkpassword" name="checkpassword" autocomplete="off"
                        placeholder="請確認密碼" onblur="checkpwd()">
                    <img class="img" id="idfimg3" src=""><span id="idsp2"></span><br />
                </div>
            </fieldset>
            <fieldset>
                <legend>基本資料</legend>
                <div class="st1">
                    <label for="email">暱稱:</label>
                    <input type="text" id="nickname" name="nickname">
                    <img class="img" id="idfimg4" src=""><span id="idsp"></span><br />

                </div>
                <div class="st1">
                    <label for="email">電子信箱email:</label>
                    <input type="text" id="email" name="email">
                    <img class="img" id="idfimg5" src=""><span id="idsp"></span><br />
                </div>
                <div>

                </div>
                <div class="st1">
                    <label for="account">照片:</label>
                    <input type="file" id="picture" name="picture" accept="image/*">
                </div>
            </fieldset>
            <input type="submit" name="submit" value="送出">
        </form>
    </div>
</body>

</html>