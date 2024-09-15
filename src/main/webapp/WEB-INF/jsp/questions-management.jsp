<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <html>

        <head>
            <meta charset="utf-8">
            <meta name="viewport" content="width=device-width, initial-scale=1">
            <title>Questions Management</title>
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
                integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
                crossorigin="anonymous">
            <style>
                .content {
                    padding: 20px;
                    margin: 20px;
                }

                #backToHome {
                    color: white;
                    font-size: 16px;
                    text-decoration: none;
                }

                #action {
                    margin: 15px;
                    padding: 10px;
                    color: white;
                    font-size: 12px;
                    text-decoration: none;
                }

                table{
                    padding: 20px;
                    margin: 20px;
                }
            </style>
        </head>

        <body>
            <%@ include file="navigation.jsp" %>
                <div class="content" align="center">

                    <h1 class="display-5">Questions Management</h1>
                    <button type="button" class="btn btn-warning btn-sm">
                        <a href="/questions-management/question/new" id="action">Add New Question</a>
                    </button>
                    <table class="table table-hover">
                        <thead class="table-success">
                        <th>Id</th>
                        <th>Description</th>
                        <th>Category</th>
                        <th>Choices</th>
                        <th>Status</th>
                        <th colspan="3">Actions</th>
                        </thead>
                        <tbody>
                        <c:forEach var="question" items="${questions}" varStatus="loop">
                            <tr>
                                <td>${question.id}</td>
                                <td>${question.description}</td>
                                <td>${question.category.name}</td>
                                <td>
                                <ul>
                                <c:forEach var="choice" items="${question.getChoices()}" varStatus="status">
                                    <li>${choice.getDescription()}</li>
                                </c:forEach>
                                </ul>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${question.isActive()==true}">
                                            <c:out value="${'Active'}" />
                                        </c:when>
                                        <c:otherwise>
                                            <c:out value="${'Suspended'}" />
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <a href="/questions-management/question/edit/${question.id}" class="btn btn-outline-info btn-sm">Edit</a>
                                </td>
                                <td>
                                    <form method="post" action="/questions-management/question/active/${question.id}">
                                        <button type="submit" class="btn btn-success btn-sm">
                                            Active
                                        </button>
                                    </form>
                                </td>
                                <td>
                                    <form method="post" action="/questions-management/question/suspend/${question.id}">
                                        <button type="submit" class="btn btn-danger btn-sm">
                                            Suspend
                                        </button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <button type="button" class="btn btn-primary btn-sm">
                        <a href="/admin-management-portal" id="backToHome">Back To Portal</a>
                    </button>
                </div>
                </div>
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
                    integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
                    crossorigin="anonymous"></script>
        </body>

        </html>