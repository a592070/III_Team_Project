<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Student
  Date: 2020/10/12
  Time: 下午 02:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="${pageContext.servletContext.contextPath}/static/jquery-3.5.1.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

    <style>
        body {
            margin: 0 auto;
            max-width: 800px;
            padding: 0 20px;
        }

        .chat-container {
            height: 400px;
            overflow: auto;
            /*transform: rotate(180deg);*/
            /*direction: rtl;*/
        }
        .message {
            border: 2px solid #dedede;
            background-color: #f1f1f1;
            border-radius: 5px;
            padding: 10px;
            margin: 10px 0;
            /*transform: rotate(180deg);*/
            /*direction: ltr;*/
        }

        .darker {
            border-color: #cccccc;
            background-color: #dddddd;
        }

        .message::after {
            content: "";
            clear: both;
            display: table;
        }

        .send-message-form input {
            width: 100%;
            border: 2px solid #cccccc;
            font-size: 16px;
            padding: 10px;
            margin-top: 10px;
        }
        .send-message-form button {
            border: #cccccc;
            display: none;
        }

    </style>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

    <script type="text/javascript">
        // var username = window.prompt("username: ");
        // if(username == null || username == ""){
        //     username = "guest";
        // }
    </script>
</head>
<body>
<jsp:include page="../fragment/header.jsp" />


<h2>Chat Messages</h2>
<div class="chat-container">

    <div class="message darker">
        <p>歡迎光臨</p>
    </div>


</div>


<input type="text" name="input" class="input" placeholder="請輸入...">
<button type="submit" class="submit" onclick="send()">Send</button>
<button onclick="closeWebSocket()">Close</button>

<br>
<br>
<div class="message" id="onlineCount"></div>

<script type="text/javascript">
    const darkType = 1;
    var websocket = null;
    var onlineCount;
    var onlineCountDiv = document.querySelector("#onlineCount");

    var input =  document.querySelector(".input");

    var messageJson = {"message":"null"};
    var ip;
    var message;

    document.querySelector(".user").value = username;

    const url = "ws://localhost:8080${pageContext.servletContext.contextPath}/chat/<%=request.getAttribute("userChatId")%>";
    websocket = new WebSocket(url);
    // console.log(websocket.);

    websocket.onopen = function (event){
        messageJson["message"] = "online";
        websocket.send(JSON.stringify(messageJson));
    }

    websocket.onmessage = function (event){

        console.log(event.data);
        console.log(websocket);
        var value = event.data;

        messageJson = JSON.parse(value);
        console.log(messageJson);


        if(messageJson["message"] == "online" || messageJson["message"] == "offline"){
            setMessageInnerHtml(messageJson["ip"]+": "+messageJson["message"], darkType);
            onlineCountDiv.textContent = "目前人數: "+messageJson["count"];
        }else{
            setMessageInnerHtml(messageJson["ip"]+": "+messageJson["message"], 0);
            onlineCountDiv.textContent = "目前人數: "+messageJson["count"];
        }

    }
    websocket.onclose = function (event){
        messageJson["message"] = "offline";
        websocket.send(JSON.stringify(messageJson));
        setMessageInnerHtml("close", darkType);
        websocket.close();
    }
    window.onbeforeunload = function (){
        messageJson["message"] = "offline";
        websocket.send(JSON.stringify(messageJson));
        websocket.close();
    }


    function setMessageInnerHtml(context, type){
        let chat = document.querySelector(".chat-container");

        let eleDiv = document.createElement("div");
        eleDiv.className = "message";
        if(type == darkType){
            eleDiv.className = "message darker";
        }

        let eleContext = document.createElement("p");
        eleContext.className = "p";
        eleContext.textContent = context;

        eleDiv.appendChild(eleContext);

        chat.appendChild(eleDiv);
        chat.scrollTop = chat.scrollHeight;
        $("p").linkify();
    }

    function closeWebSocket(){
        websocket.close();
    }

    window.addEventListener("keypress", function (event){
        if(event.key == "Enter"){
            input.focus();
        }
    });

    input.addEventListener("keypress", function (event){
        if(event.key == "Enter"){
            send();
        }
    });


    function send(){
        let message = input.value;
        if(input.value != null && input.value!=""){
            messageJson["message"] = message;
            messageJson["user"] = username;
            websocket.send(JSON.stringify(messageJson));
            input.value = "";
        }
    }


</script>

</body>
</html>
