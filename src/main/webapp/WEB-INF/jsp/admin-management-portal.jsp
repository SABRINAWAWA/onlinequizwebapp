<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Admin Portal</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>

<body>
 <%@ include file="navigation.jsp" %>
<div>
    <h1 class="display-4" align="center">Admin Management Portal</h1>
    <div class="list-group d-flex justify-content-center" align="center">
        <div class="list-group-item list-group-item-action" id="management-card">
            <a href="/user-management" class="alert-link">User Management</a>
        </div>
        <div class="list-group-item list-group-item-action" id="management-card">
            <a href="/contact-us-management" class="alert-link">Contact Us Management</a>
        </div>
        <div class="list-group-item list-group-item-action" id="management-card">
            <a href="/questions-management" class="alert-link">Question Management</a>
        </div>
        <div class="list-group-item list-group-item-action" id="management-card">
            <a href="/quiz-result-management" class="alert-link">Quiz Result Management</a>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</div>
</body>

</html>
