<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Error</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
        crossorigin="anonymous">
    <style>
        .content {
            padding: 50px;
            margin: 50px;
        }
    </style>
</head>

<body>
<%@ include file="navigation.jsp" %>
    <div>
        <div class="alert alert-danger" role="alert">
            <h4>This user account is suspended. Please
            <a href="/create-contact" class="alert-link">contact admin</a> to resolve this issue.
            </h4>
        </div>
    </div>
</body>

</html>
