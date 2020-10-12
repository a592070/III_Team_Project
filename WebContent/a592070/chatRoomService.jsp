<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="globalinit.Constant" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2020/10/12
  Time: 下午 07:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ServiceChatRoom</title>
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


</head>
<body>
<jsp:include page="../fragment/header.jsp" />
<div class="container p-3 my-3">
    <h2>Chat Messages</h2>
    <div class="btn-group" id="client_btn">
<%--        <button type="button" class="btn btn-primary hidden" data-toggle="collapse" data-target="#multiCollapseExample1" aria-expanded="false" aria-controls="multiCollapseExample1">client1</button>--%>
<%--        <button type="button" class="btn btn-primary" data-toggle="collapse" data-target="#multiCollapseExample2" aria-expanded="false" aria-controls="multiCollapseExample2">client2</button>--%>
<%--        <button type="button" class="btn btn-primary">client3</button>--%>
    </div>
    <div class="chat-container " id="accordion">

<%--        <div class="collapse multi-collapse" id="multiCollapseExample1" data-parent="#accordion">--%>
<%--            <div class="media border p-3">--%>
<%--                <div class="media-body">--%>
<%--                    <h4>YOU<small><i>2020/10/12 07:21:33</i></small></h4>--%>
<%--                    <p>Lorem ipsum...</p>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--            <div class="media border p-3">--%>
<%--                <div class="media-body">--%>
<%--                    <h4>client1<small><i>2020/10/12 07:22:33</i></small></h4>--%>
<%--                    <p>Lorem ipsum...</p>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--            <div class="media border p-3">--%>
<%--                <div class="media-body">--%>
<%--                    <h4>client1<small><i>2020/10/12 07:22:33</i></small></h4>--%>
<%--                    <p>Lorem ipsum...</p>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>

<%--        <div class="collapse multi-collapse" id="multiCollapseExample2" data-parent="#accordion">--%>

<%--            <div class="media border p-3">--%>
<%--                <div class="media-body">--%>
<%--                    <h4>YOU<small><i>2020/10/12 07:21:33</i></small></h4>--%>
<%--                    <p>Lorem ipsum...</p>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--            <div class="media border p-3">--%>
<%--                <div class="media-body">--%>
<%--                    <h4>client1<small><i>2020/10/12 07:22:33</i></small></h4>--%>
<%--                    <p>Lorem ipsum...</p>--%>
<%--                </div>--%>
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
    var clientList = [];

    function getChatClients(){
        $.get("${pageContext.servletContext.contextPath}/ChatServlet",
            {"method":"getChatClients"},
            function (data) {
                let json = JSON.parse(data);
                clientList = json;
                let contentBtn = "";
                let contentDiv = "";
                for(let ele in json){
                    contentBtn += `<button type="button" class="btn btn-primary" data-toggle="collapse" data-target="#\${json[ele]}" aria-expanded="false" aria-controls="\${json[ele]}">\${json[ele]}</button>`;
                    contentDiv += `
            <div class="collapse multi-collapse" id="\${json[ele]}" data-parent="#accordion"></div>`;
                }
                $("#client_btn").html(contentBtn);
                $("#accordion").html(contentDiv);
            })
    }


    const url = "ws://localhost:8080${pageContext.servletContext.contextPath}/chat";
    websocket = new WebSocket(url);

    websocket.onopen = function (event){

    }
    websocket.onmessage = function (event){
        let value = event.data;
        let messageJson = JSON.parse(value);
        console.log(messageJson);

        let method = messageJson.method;

        // let addClientID = messageJson.addClient;
        // let removeClientID = messageJson.removeClient;
        let httpsessionID = messageJson.httpSessionID;
        if("addClient" == method){
            if(!clientList.includes(httpsessionID)){
                clientList.push(httpsessionID);
                addClient(httpsessionID);
            }
        }else if("removeClient" == method){
            let i = clientList.indexOf(httpsessionID);
            if(i>-1){
                clientList.splice(i,1);
                removeClient(httpsessionID);
            }
        }else if("toServiceMsg" == method){
            let msg = messageJson.content;
            setMessageInHTML(msg, 1, httpsessionID);
        }
    }
    websocket.onclose = function (event){
        websocket.close();
    }
    window.onbeforeunload = function (event){
        websocket.close();
    }

    function addClient(httpSessionID){
        // $(".multi-collapse").toggle();


        let contentBtn = "";
        contentBtn += `<button type='button' class='btn btn-primary' data-toggle='collapse' data-target='#\${httpSessionID}' aria-expanded='false' aria-controls="\${httpSessionID}" id="\${httpSessionID}_btn" onclick="setCurrentClient($(this))">\${httpSessionID}</button>`;
        $("#client_btn").append(contentBtn);
        // tmp = $("#client_btn").html();
        // $("#client_btn").html(tmp+contentBtn);


        let contentDiv = "";
        contentDiv += `<div class="collapse multi-collapse" id="\${httpSessionID}" ></div>`;
        // data-parent="#accordion"
        $("#accordion").append(contentDiv);
        // let tmp = $("#accordion").html();
        // $("#accordion").html(tmp+contentDiv);

        $(".multi-collapse").collapse('hide');
        $('#'+httpSessionID).collapse('show');

    }
    function removeClient(httpSessionID){
        $("#"+httpSessionID).remove();
        $("#"+httpSessionID+"_btn").remove();
    }

    function setMessageInHTML(msg, type, httpsessionID){
        let content = "";
        content += `<div class="media border p-3" ><div class="media-body">`;
        if(type == 0){
            content += `<h4>YOU</h4><p>\${msg}</p>`;
        }else if(type == 1){
            content += `<h4>\${httpsessionID}</h4><p>\${msg}</p>`;
        }
        content+=`</div></div>`;

        $("#"+httpsessionID).append(content);
    }
    var currentClient;
    function send(){
        let val = $("#input_msg").val();
        setMessageInHTML(val, 0, currentClient);
        let json = {receive:currentClient,content:val};

        websocket.send(JSON.stringify(json));
        clear();
    }
    function setCurrentClient(obj){
        currentClient = obj.text();
        $("#input_msg").attr("placeholder","請輸入...to "+currentClient);

        $(".multi-collapse").collapse('hide');
        $('#'+currentClient).collapse('show');
    }
    function clear(){
        $("#input_msg").val("");
    }

</script>
</body>
</html>
