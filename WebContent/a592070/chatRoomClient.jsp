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
            height: 400px;
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

<div class="container p-3 my-3">
    <h2>Chat Messages</h2>
    <div class="chat-container  border p-4" id="msg_div">

<%--        <div class="media border p-3" >--%>
<%--            <div class="media-body">--%>
<%--                <h4>YOU<small><i>2020/10/12 07:21:33</i></small></h4>--%>
<%--                <p>Lorem ipsum...</p>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--        <div class="media border p-3">--%>
<%--            <div class="media-body">--%>
<%--                <h4>service<small><i>2020/10/12 07:22:33</i></small></h4>--%>
<%--                <p>Lorem ipsum...</p>--%>
<%--            </div>--%>
<%--        </div>--%>

    </div>
    <div class="input-group mb-3">
        <input type="text" class="form-control" placeholder="請輸入..." id="input_msg">
        <div class="input-group-append">

            <button class="btn btn-primary" type="button" onclick="send()">Send</button>
            <button class="btn btn-danger" type="button" onclick="clear()">Cancel</button>

        </div>
    </div>
</div>

<script type="text/javascript">


    const url = "ws://localhost:8080${pageContext.servletContext.contextPath}/chat";
    websocket = new WebSocket(url);
    // console.log(websocket.);

    websocket.onopen = function (event){
    }

    websocket.onmessage = function (event){
        let value = event.data;
        let messageJson = JSON.parse(value);
        console.log(messageJson);

        let method = messageJson.method;

        if("noService" == method){
            let msg = "目前沒有客服在線";
            setMessageInHTML(msg,1);
        }else if("toClientMsg" == method){
            let msg = messageJson.content;
            setMessageInHTML(msg, 1);
        }
    }
    websocket.onclose = function (event){
        websocket.close();
    }
    window.onbeforeunload = function (){
        websocket.close();
    }


    function setMessageInHTML(msg, type){
        let content = "";
        content += `<div class="media border p-3" ><div class="media-body">
                    `;
        if(type == 0){
            content += `<h4>YOU</h4><p>\${msg}</p>`;
        }else if(type == 1){
            content += `<h4>Service</h4><p>\${msg}</p>`;
        }
        content+=`</div></div>`;
        // console.log(content);
        // let temp = $("#msg_div").html();
        // $("#msg_div").html(temp+content);
        $("#msg_div").append(content);
    }

    function send(){
        let val = $("#input_msg").val();
        setMessageInHTML(val, 0);
        let json = {content:val};
        websocket.send(JSON.stringify(json));
        clear();
    }
    function clear(){
        $("#input_msg").val("");
    }


</script>

</body>
</html>
