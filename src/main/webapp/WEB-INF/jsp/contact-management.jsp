<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Contact Management</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style>
        .content{
            padding: 20px;
            margin: 20px;
        }
        #backToHome {
          color: white;
          font-size: 16px;
          text-decoration: none;
        }
        #action{
            color: white;
            font-size: 12px;
            text-decoration: none;
        }
    </style>
</head>

<body>
 <%@ include file="navigation.jsp" %>
        <div class="content">
            <div  align="center">
                <h1 class="display-5">Contact Management</h1>
                <table class="table table-hover">
                <thead class="table-success">
                    <tr>
                      <th scope="col">ID</th>
                      <th scope="col">Email</th>
                      <th scope="col">Subject</th>
                      <th scope="col">Message</th>
                      <th scope="col">Created At</th>
                      <th scope="col">Actions</th>
                    </tr>
                  </thead>
                  <tbody class="table-group-divider">
                    <c:forEach var="contact" items="${contacts}" varStatus="status">
                        <tr>
                            <th scope="row">${contact.id}</td>
                            <td>${contact.email}</td>
                            <td>${contact.subject}</td>
                            <c:choose>
                                <c:when test="${contact.getMessageContent().length()<=35}">
                                    <td>${contact.messageContent}</td>
                                </c:when>
                                <c:otherwise>
                                    <td>${contact.getMessageContent().substring(0,35)}...</td>
                                </c:otherwise>
                            </c:choose>

                            <td>${contact.createdAt}</td>
                            <td>
                                <button type="button" class="btn btn-primary">
                                <a href="/view-contact-us/${contact.id}" id="action">View</a>
                                </button>
                            </td>
                        </tr>
                    </c:forEach>
                </table>

                <button type="button" class="btn btn-primary btn-sm">
                    <a href="/admin-management-portal" id="backToHome">Back To Portal</a>
                </button>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>

</html>