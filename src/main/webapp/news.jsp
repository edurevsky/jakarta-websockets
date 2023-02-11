<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String id = request.getParameter("id");
%>
<html>
<head>
    <title>News</title>
    <style>
        #ws {
          position: absolute;
          right: 0;
          top: 0;
          min-width: 400px;
          background-color: lightcyan;
        }
    </style>
</head>
<body>

    <% if (id == null) { %>
        <h1>Latest News: </h1>
    <% } %>
    <div id="news"></div>
    <div id="ws">
        <h3>Feed:</h3>
    </div>

    <script>

        const websocket = new WebSocket('ws://localhost:8080<%= request.getContextPath() %>/news')

        const wsDiv = document.getElementById('ws')

        websocket.onmessage = function (event) {
            const data = JSON.parse(event.data)

            wsDiv.append(makeNewsNotification(data))
        }

        const newsDiv = document.getElementById('news');

        function makeNews({ title, creationDate, content, author, id}) {

            const div = document.createElement('div')
            const a = document.createElement('a')

            const h2 = document.createElement('h2')

            h2.innerText = `\${title} - \${author}`

            a.href = `http://localhost:8080<%= request.getContextPath() %>/news.jsp?id=\${id}`
            a.append(h2)

            const small = document.createElement('small')
            small.innerText = creationDate

            const p = document.createElement('p')
            p.innerText = content

            div.append(a, small, p)

            return div
        }

        function makeNewsNotification({ creationDate, title, id }) {
            const div = document.createElement('div')
            const a = document.createElement('a')
            a.innerText = `\${creationDate} - \${title}`
            a.href = `http://localhost:8080<%= request.getContextPath() %>/news.jsp?id=\${id}`

            div.append(a)

            return div
        }

        <% if (id == null) { %>
            fetch('http://localhost:8080<%= request.getContextPath() %>/api/news')
                .then(response => response.json())
                .then(newsList => {
                    for (const news of newsList) {
                        newsDiv.append(makeNews(news))
                    }
                })
                .catch(e => console.log(e))
        <% } else { %>
            fetch('http://localhost:8080<%= request.getContextPath() %>/api/news/<%= id %>')
                .then(response => response.json())
                .then(news => {
                    newsDiv.append(makeNews(news))
                })
        <% } %>

    </script>
</body>
</html>
