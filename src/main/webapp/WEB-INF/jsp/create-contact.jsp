<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Contact Us</title>
</head>

<body>
 <%@ include file="navigation.jsp" %>
<div>
    <form method="post" action="/create-contact">
        <div>
            <label>Subject</label>
            <input type="text" name="subject">
        </div>
        <div>
            <label>Email Address</label>
            <input type="email" name="email">
        </div>
        <div>
            <label>Message</label>
            <textarea type="message" name="message"></textarea>
        </div>
        <button type="submit">Submit</button>
    </form>
    <a href="/home">Back to Home Page</a>
</div>
</body>

</html>
