<%@ page import="globalinit.Constant" %>
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
    <title>ClientChatRoom</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <style>
        .chat-container {
            height: 600px;
            overflow: auto;
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

    <script type="text/javascript">




    </script>

</head>
<body>
<jsp:include page="../fragment/header.jsp" />

<div class="container">
    <h2>Chat Messages</h2>
    <div class="chat-container  border p-4">

        <div class="media border p-3">
            <div class="media-body">
                <h4>YOU<small><i>2020/10/12 07:21:33</i></small></h4>
                <p>Lorem ipsum...</p>
            </div>
        </div>
        <div class="media border p-3">
            <div class="media-body">
                <h4>service<small><i>2020/10/12 07:22:33</i></small></h4>
                <p>Lorem ipsum...</p>
            </div>
        </div>

    </div>
    <div class="input-group mb-3">
        <input type="text" class="form-control" placeholder="請輸入...">
        <div class="input-group-append">
            <button class="btn btn-primary" type="button">Send</button>
            <button class="btn btn-danger" type="button">Cancel</button>
        </div>
    </div>
</div>

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
