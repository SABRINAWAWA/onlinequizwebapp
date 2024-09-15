<%@ page contentType="text/html;charset=UTF-8" language="java" %>

    <html>

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Home</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
            integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <style>
            .content {
                margin: auto;
                width: 60%;
                padding: 20px;
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
                <h2 align="center">Welcome, ${user.firstName} ${user.lastName}</h2>
                <h5> Start New Quiz </h5>
                <div class="container text-center">
                    <div class="btn-group" role="group">
                        <c:forEach var="category" items="${categories}" varStatus="loop">
                            <a href="/take-quiz/category/${category.getId()}" type="button"
                                class="btn btn-outline-primary">${category.getName()}</a>
                        </c:forEach>
                    </div>
                </div>
                <br>
                <h5> Quiz History </h5>
                <table class="table table-hover">
                    <thead class="table-success">

                        <th>Quiz Name</th>
                        <th>Category</th>
                        <th>Taken Time</th>
                        <th>Quiz Score</th>
                        <th>Quiz Result</th>
                        <th>Action</th>
                    </thead>

                    <tbody>
                        <c:forEach var="quiz" items="${quizzes}" varStatus="loop">
                            <tr>
                                <td>${quiz.quizName}</td>
                                <td>${quiz.getCategory().getName()}</td>
                                <td>${quiz.getStartTime()}</td>
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
            </div>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
                crossorigin="anonymous"></script>
    </body>

    </html>