<%--
  Created by IntelliJ IDEA.
  User: zhangs
  Date: 2017/12/14
  Time: 9:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>WebSocket sockjs的实现api调用测试</title>
    <script src="${pageContext.request.contextPath}/plugins/sockjs.min.js"></script>
    <script language="javascript" type="text/javascript">
        var echo_websocket;

        function init() {
            output = document.getElementById("output");
        }

        function send_echo() {
            echo_websocket = new SockJS("http://localhost:8080/sockjs/ws.do");   //初始化 websocket

            echo_websocket.onopen = function () {
                doSend(textID.value);
                console.log('Info: connection opened.');
            };

            echo_websocket.onmessage = function (event) {
                console.log('Received: ' + event.data); //处理服务端返回消息
                writeToScreen(event.data);
            };

            echo_websocket.onclose = function (event) {
                console.log('Info: connection closed.');
                console.log(event);
            };
        }

        function doSend(message) {
            echo_websocket.send(message);
            writeToScreen("Sent message: " + message);
        }

        function writeToScreen(message) {
            var pre = document.createElement("p");
            pre.style.wordWrap = "break-word";
            pre.innerHTML = message;
            output.appendChild(pre);
        }

        window.addEventListener("load", init, false);
    </script>
</head>
<body>

<h1>Echo Server</h1>
<div style="text-align: left;">
    <form action="">
        <input onclick="send_echo()" value="send websocket request" type="button">
        <input id="textID" name="message" value="Hello world, Web Sockets" type="text">
        <br>
    </form>
</div>
<div id="output"></div>
</body>
</html>