<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create News</title>
</head>
<body>

    <div id="form" style="max-width: 500px;">
        <div style="display: grid;">
            <label for="title">Title</label>
            <input name="title" id="title" type="text">
        </div>

        <div style="display: grid;">
            <label for="author">Author</label>
            <input name="author" id="author" type="text">
        </div>

        <div style="display: grid;">
            <label for="content">Content</label>
            <textarea name="content" id="content" cols="30" rows="10"></textarea>
        </div>

        <button onclick="submit()" type="button">Submit</button>
    </div>

    <script>

        function submit() {
            const title = document.getElementById('title').value
            const author = document.getElementById('author').value
            const content = document.getElementById('content').value

            const data = { title: title, author: author, content: content }

            function clearForm() {
                title.value = ''
                author.value = ''
                content.value = ''
            }

            fetch('http://localhost:8080<%= request.getContextPath() %>/api/news', {
                method: 'POST',
                body: JSON.stringify(data),
                headers: {
                    'Content-Type': 'application/json'
                }})
                .then(response => {
                    if (response.status === 200) {
                        clearForm()
                    } else {
                        console.log(response)
                    }
                })
                .catch(e => console.log(e))
        }

    </script>
</body>
</html>
