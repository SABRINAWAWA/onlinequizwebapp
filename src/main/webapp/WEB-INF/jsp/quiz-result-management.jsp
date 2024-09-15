<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <html>

        <head>
            <meta charset="utf-8">
            <meta name="viewport" content="width=device-width, initial-scale=1">
            <title>Quiz Results Management</title>
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

                table {
                    padding: 20px;
                    margin: 20px;
                }
            </style>
        </head>

        <body>
            <%@ include file="navigation.jsp" %>
                <div class="content" align="center">

                    <h1 class="display-5">Quiz Results Management</h1>
                    <a class="btn btn-warning" href="/quiz-result-management/page/1">Reset Filter</a>
                    <table class="table table-hover">
                        <thead class="table-success">
                            <th>ID</th>
                            <th>Quiz Name</th>
                            <th>
                                <div class="btn-group">
                                    <a type="button" class="btn btn-outline-success"
                                    href="/quiz-result-management/category/orderBy">Category</a>
                                    <button type="button"
                                        class="btn btn-outline-success dropdown-toggle dropdown-toggle-split"
                                        data-bs-toggle="dropdown" aria-expanded="false">
                                        <span class="visually-hidden">Toggle Dropdown</span>
                                    </button>
                                    <ul class="dropdown-menu">
                                        <c:forEach var="category" items="${categories}" varStatus="loop">
                                            <li><a class="dropdown-item"
                                                    href="/quiz-result-management/category/${category.getId()}">${category.name}</a>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                </div>
                            </th>
                            <th>
                                <div class="btn-group">
                                    <a type="button" class="btn btn-outline-success" href="/quiz-result-management/user/orderBy">User Name</a>
                                    <button type="button"
                                        class="btn btn-outline-success dropdown-toggle dropdown-toggle-split"
                                        data-bs-toggle="dropdown" aria-expanded="false">
                                        <span class="visually-hidden">Toggle Dropdown</span>
                                    </button>
                                    <ul class="dropdown-menu">
                                        <c:forEach var="user" items="${users}" varStatus="loop">
                                            <li><a class="dropdown-item"
                                                    href="/quiz-result-management/user/${user.getId()}">${user.firstName}
                                                    ${user.lastName}</a></li>
                                        </c:forEach>
                                    </ul>
                                </div>
                            </th>
                            <th>Taken Time</th>
                            <th>No. of Questions</th>
                            <th>Quiz Score</th>
                            <th>Quiz Result</th>
                            <th>Action</th>
                        </thead>

                        <tbody>
                            <c:forEach var="quiz" items="${quizzes}" varStatus="loop">
                                <tr>
                                    <td>${quiz.id}</td>
                                    <td>${quiz.quizName}</td>
                                    <td>${quiz.getCategory().getName()}</td>
                                    <td>${quiz.getQuizTaker().getFirstName()} ${quiz.getQuizTaker().getLastName()}</td>
                                    <td>${quiz.getStartTime()}</td>
                                    <td>${quiz.getQuestionAnswerList().size()}</td>
                                    <td>${quiz.numberOfCorrectQuestions}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${quiz.getPass()==true}">
                                                <c:out value="${'Pass'}" />
                                            </c:when>
                                            <c:otherwise>
                                                <c:out value="${'Fail'}" />
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <a href="/quiz/${quiz.getId()}" class="btn btn-primary btn-sm">View</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                    <nav aria-label="pagination-nav">
                        <ul class="pagination pagination-md justify-content-center">
                            <c:forEach var="i" begin="1" end="${numOfPage}">
                                <li class="page-item">
                                    <a class="page-link" href="/quiz-result-management/page/${i}">${i}</a>
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