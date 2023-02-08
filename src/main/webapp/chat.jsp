<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Chat</title>
</head>
<body>

    <input type="text" id="message-input" />
    <button id="send" type="button" onclick="sendMessage()">Send</button>
    <button id="disconnect" type="button" onclick="disconnect()">Disconnect</button>
    <div id="messages"></div>

    <script>

        function getUsername() {
            const username = prompt('Insert your username:')
            if (!username) {
                return getUsername()
            }
            return username
        }

        const messages = document.getElementById('messages')
        const messageInput = document.getElementById('message-input')

        const username = getUsername()

        const websocket = new WebSocket(`ws://localhost:8080<%= request.getContextPath() %>/chat/\${username}`)

        websocket.onopen = function () {
            console.log('open')
        }

        websocket.onclose = function () {
            console.log('close')
        }

        websocket.onmessage = function (event) {
            const data = JSON.parse(event.data)

            const message = createMessage(data)
            appendMessage(messages, message)
        }

        function sendMessage() {
            const text = messageInput.value

            const json = JSON.stringify({ content: text })

            websocket.send(json)

            messageInput.value = ''
            return false
        }

        function createMessage(data) {
            const p = document.createElement('p')
            p.innerText = `\${data.username}: \${data.content}`
            return p
        }

        function appendMessage(messagesElement, message) {
            messagesElement.append(message)
        }

        function disconnect() {
            websocket.close(1000, 'User closed connection')
            window.location.reload()
        }

    </script>
</body>
</html>