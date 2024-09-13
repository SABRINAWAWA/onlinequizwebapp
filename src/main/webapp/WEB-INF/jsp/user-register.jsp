<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Register</title>
</head>

<body>
<%-- div is for grouping items --%>
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
</div>
</body>

</html>
