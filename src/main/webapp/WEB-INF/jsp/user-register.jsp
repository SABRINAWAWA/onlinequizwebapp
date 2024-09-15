<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Register</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
        crossorigin="anonymous">
</head>

<body>
 <%@ include file="navigation.jsp" %>
<div>
    <form method="post" action="/user-register">
        <div>
            <label>First Name: </label>
            <input type="text" name="firstName">
        </div>
        <div>
            <label>Last Name: </label>
            <input type="text" name="lastName">
        </div>
        <div>
            <label>Email Address: </label>
            <input type="email" name="email">
        </div>
        <div>
            <label>Password</label>
            <input type="password" name="password">
        </div>
        <button type="submit">Submit</button>
    </form>
    <a href="/login">Already have an account? Login.</a>
</div>
</body>

</html>
