<%@page contentType="text/html;charset=UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = "ws://" + request.getServerName() + ":" + request.getServerPort() + path;  //!注意协议是ws://
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>WebSocket 原生api测试</title>
    <script language="javascript" type="text/javascript">
        var echo_websocket;

        function init() {
            output = document.getElementById("output");
        }

        function send_echo() {
            // "ws://localhost:8080/MySSMDemo/ws.do"
            echo_websocket = new WebSocket("<%=basePath%>/ws.do");
            echo_websocket.onopen = function (evt) {
                writeToScreen("Connected !");
                doSend(textID.value);
            };
            echo_websocket.onmessage = function (evt) {
                writeToScreen("Received message: " + evt.data);
                echo_websocket.close();
            };
            echo_websocket.onerror = function (evt) {
                writeToScreen('<span style="color: red;">ERROR:</span> ' + evt.data);
                echo_websocket.close();
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
        <input onclick="send_echo()" value="发送请求" type="button">
        <input id="textID" name="message" value="Hello World, Web Sockets" type="text">
        <br>
    </form>
</div>
<div id="output"></div>
</body>
</html>