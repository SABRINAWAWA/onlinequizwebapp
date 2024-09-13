<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <title>Quiz Results Management</title>
</head>

<body>
    <%-- div is for grouping items --%>
        <div>
            <div align="center">
                <h1>Quiz Results Management</h1>
                <table border="1">
                    <th>ID</th>
                    <th>Name</th>
                    <th>User Name</th>
                    <th>Start Time</th>
                    <th>End Time</th>
                    <th>Quiz Score</th>
                    <th>Quiz Result</th>
                    <th>Questions</th>

                    <c:forEach var="quiz" items="${quizzes}" varStatus="status">
                        <tr>
                            <td>${quiz.id}</td>
                            <td>${quiz.quizName}</td>
                            <td>${quiz.getQuizTaker().getFirstName()} ${quiz.getQuizTaker().getLastName()}</td>
                            <td>${quiz.startTime}</td>
                            <td>${quiz.endTime}</td>
                            <td>
                            <c:choose>
                                <c:when test="${quiz.getPass()==true}">
                                <c:out value="${'Pass'}"/>
                                </c:when>
                                <c:otherwise>
                                <c:out value="${'Fail'}"/>
                                </c:otherwise>
                            </c:choose>
                            </td>
                            <td>${quiz.numberOfCorrectQuestions}</td>
                            <td>
                             <c:forEach var="question" items="${quiz.getQuestionAnswerList()}" varStatus="status">
                                 <p>${question.getQuestion().getDescription()}</p>
                             </c:forEach>
                            </td>
                            <td>
                                <a href="/quiz/${quiz.getId()}">View</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <a href="/admin-management-portal">Back To Portal</a>
        </div>
</body>

</html>