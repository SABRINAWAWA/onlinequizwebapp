<%@ page contentType="text/html;charset=UTF-8" language="java" %>

    <html>

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Quiz Result</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
            integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <style>
            .content {
                margin: auto;
                width: 50%;
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
            <div class=".bg-light content" align="center">
                <h2 align="center">Quiz Result of ${quiz.quizName}</h2>
                <c:choose>
                    <c:when test="${user.getAdmin()==false}">
                        <a href="/take-quiz/category/${quiz.getCategory().getId()}" class="btn btn-primary">Take
                            another quiz</a>
                    </c:when>
                </c:choose>
                <table class="table-hover">
                    <tbody>

                        <tr>
                            <th scope="row">User: </th>
                            <td>${quiz.getQuizTaker().getFirstName()} ${quiz.getQuizTaker().getLastName()}</td>
                        </tr>
                        <tr>
                            <th scope="row">Category: </th>
                            <td>${quiz.getCategory().getName()}</td>
                        </tr>
                        <tr>
                            <th scope="row">Start Time: </th>
                            <td>${quiz.startTime}</td>
                        </tr>
                        <tr>
                            <th scope="row">End Time: </th>
                            <td>${quiz.endTime}</td>
                        </tr>
                        <tr>
                            <th scope="row">Time Duration: </th>
                            <td>
                                <c:choose>
                                    <c:when test="${quiz.getTimeDuration()<60}">
                                        <c:out value="${quiz.getTimeDuration()} seconds" />
                                    </c:when>
                                    <c:otherwise>
                                        <c:out value="${quiz.getTimeDuration()/60} mins" />
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row">No. Of Questions: </th>
                            <td>${quiz.getQuestionAnswerList().size()}</td>
                        </tr>
                        <tr>
                            <th scope="row">Quiz Score: </th>
                            <td>${quiz.numberOfCorrectQuestions}</td>
                        </tr>
                        <tr>
                            <th scope="row">Quiz Result: </th>
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
                        </tr>
                    </tbody>
                </table>
            </div>
            <div class=".bg-light content">
                <h3>Questions:</h3>
                <c:forEach var="question" items="${quiz.getQuestionAnswerList()}" varStatus="loop">
                    <p><b>${question.getQuestion().getDescription()}</b>
                        <c:choose>
                            <c:when test="${question.getCorrectAnswer()==true}">
                                <span class="badge text-bg-success">Success</span>
                            </c:when>
                            <c:otherwise>
                                <span class="badge text-bg-danger">Wrong</span>
                            </c:otherwise>
                        </c:choose>
                    </p>
                    <ul>
                        <c:forEach var="choice" items="${question.getQuestion().getChoices()}" varStatus="loop">
                            <li>
                                <p>${choice.getDescription()}
                                    <c:choose>
                                        <c:when test="${choice.getCorrect()==true}">
                                            <span class="badge text-bg-success">Correct</span>
                                        </c:when>
                                    </c:choose>
                                    <c:choose>
                                        <c:when test="${choice.getId()==question.getUserChoice().getId()}">
                                            <span class="badge text-bg-primary">Selected</span>
                                        </c:when>
                                    </c:choose>
                                </p>
                            </li>
                        </c:forEach>
                    </ul>
                </c:forEach>
            </div>
            <div class="content">
                <c:choose>
                    <c:when test="${user.getAdmin()==true}">
                        <a href="/quiz-result-management" class="btn btn-primary">Back</a>
                    </c:when>
                    <c:otherwise>
                        <a href="/home" class="btn btn-primary">Back</a>
                    </c:otherwise>
                </c:choose>
            </div>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
                crossorigin="anonymous"></script>
    </body>

    </html>