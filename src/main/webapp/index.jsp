<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Index</title>
</head>
<body>
    <a href="<%= request.getContextPath() %>/chat.jsp">Go to chat</a>

    <script>

        let websocket = new WebSocket('ws://localhost:8080<%= request.getContextPath() %>/ping')

        websocket.onopen = function () {
            console.log('open')
        }

        websocket.onclose = function () {
            console.log('close')
        }

        setTimeout(function () {
            websocket.send('')
        }, 1500)

        setTimeout(function () {
            websocket.close()
        }, 5000)

    </script>
</body>
</html>
