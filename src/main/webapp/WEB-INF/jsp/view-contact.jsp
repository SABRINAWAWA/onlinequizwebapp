<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <title>View Contact</title>
</head>

<body>
 <%@ include file="navigation.jsp" %>
        <div>
            <div align="center">
                <h1>View Contact Message</h1>
                <div>
                    <div>
                        <label>Subject: </label>
                        <h3>${contact.subject}</h3>
                    </div>
                    <div>
                        <label>Email: </label>
                        <h3>${contact.email}</h3>
                    </div>
                    <div>
                        <label>Create At: </label>
                        <h3>${contact.createdAt}</h3>
                    </div>
                    <div>
                        <label>Message: </label>
                        <h3>${contact.messageContent}</h3>
                    </div>
                </div>
                <a href="/contact-us-management"> Back</a>
            </div>
        </div>
</body>

</html>