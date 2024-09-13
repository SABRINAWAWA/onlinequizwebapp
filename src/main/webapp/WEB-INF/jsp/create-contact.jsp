<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Contact Us</title>
</head>

<body>
<%-- div is for grouping items --%>
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
</div>
</body>

</html>
