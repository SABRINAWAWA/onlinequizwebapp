<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <html>

        <head>
            <meta charset="utf-8">
            <meta name="viewport" content="width=device-width, initial-scale=1">
            <title>User Management</title>
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
                    color: white;
                    font-size: 12px;
                    text-decoration: none;
                }
            </style>
        </head>

        <body>
            <%@ include file="navigation.jsp" %>
                <div class="content">
                    <div align="center">
                        <h1 class="display-5">User Management</h1>
                        <table class="table table-hover">
                            <thead class="table-success">
                                <tr>
                                    <th scope="col">ID</th>
                                    <th scope="col">Full Name</th>
                                    <th scope="col">Email</th>
                                    <th scope="col">Password</th>
                                    <th scope="col">Status</th>
                                    <th scope="col">Role</th>
                                    <th scope="col" colspan="2">Actions</th>
                                </tr>
                            </thead>
                            <tbody class="table-group-divider">
                                <c:forEach var="user" items="${users}" varStatus="loop">
                                    <tr>
                                        <th scope="row">${user.id}</td>
                                        <td>${user.firstName} ${user.lastName}</td>
                                        <td>${user.email}</td>
                                        <td>${user.password}</td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${user.getActive()==true}">
                                                    <c:out value="${'Active'}" />
                                                </c:when>
                                                <c:otherwise>
                                                    <c:out value="${'Suspended'}" />
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${user.getAdmin()==true}">
                                                    <c:out value="${'Admin'}" />
                                                </c:when>
                                                <c:otherwise>
                                                    <c:out value="${'User'}" />
                                                </c:otherwise>
                                            </c:choose>

                                        <td>
                                            <form method="post" action="/user-management/user/active/${user.id}">
                                                <button type="submit" class="btn btn-success btn-sm">
                                                    Activate
                                                </button>
                                            </form>
                                        </td>
                                        <td>
                                            <form method="post" action="/user-management/user/suspend/${user.id}">
                                                <button type="submit" class="btn btn-danger btn-sm">
                                                    Suspend
                                                </button>
                                            </form>
                                        </td>
                                    </tr>
                                </c:forEach>
                        </table>

                        <nav aria-label="pagination-nav">
                            <ul class="pagination pagination-md justify-content-center">
                                <c:forEach var="i" begin="1" end="${numOfPage}">
                                    <li class="page-item">
                                        <a class="page-link" href="/user-management/page/${i}">${i}</a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </nav>

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