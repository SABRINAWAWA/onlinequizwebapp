<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <title>Contact Management</title>
</head>

<body>
 <%@ include file="admin-navigation.jsp" %>
        <div>
            <div align="center">
                <h1>Contact Management</h1>
                <table border="1">
                    <th>Id</th>
                    <th>Email</th>
                    <th>Subject</th>
                    <th>Message</th>
                    <th>Created At</th>

                    <c:forEach var="contact" items="${contacts}" varStatus="status">
                        <tr>
                            <td>${contact.id}</td>
                            <td>${contact.email}</td>
                            <td>${contact.subject}</td>
                            <td>${contact.messageContent}</td>
                            <td>${contact.createdAt}</td>
                            <td>
                                <a href="/view-contact-us/${contact.id}">View</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <a href="/admin-management-portal">Back To Portal</a>
        </div>
</body>

</html>