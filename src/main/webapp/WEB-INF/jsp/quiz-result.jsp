<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Quiz Result</title>
</head>

<body>
<div>
    <div>
        <h4>${quiz.quizName}</h4>
    </div>
    <div>
        <h4>User: ${quiz.getQuizTaker().getFirstName()} ${quiz.getQuizTaker().getLastName()}</h4>
    </div>
    <div>
        <h4>Category: ${quiz.getCategory().getName()}</h4>
    </div>
    <div>
        <h4>Start Time: ${quiz.startTime}</h4>
    </div>
    <div>
        <h4>End Time: ${quiz.endTime}</h4>
    </div>
    <div>
        <h4>Quiz Score: ${quiz.numberOfCorrectQuestions}</h4>
    </div>
    <div>
        <h4>Quiz Result:
            <c:choose>
                <c:when test="${quiz.getPass()==true}">
                <c:out value="${'Pass'}"/>
                </c:when>
                <c:otherwise>
                <c:out value="${'Fail'}"/>
                </c:otherwise>
            </c:choose>
        </h4>
    </div>
    <div>
        <th>Questions</th>
        <c:forEach var="question" items="${quiz.getQuestionAnswerList()}" varStatus="loop">
            <p>${question.getQuestion().getDescription()}</p>
            <ul>
                <c:forEach var="choice" items="${question.getQuestion().getChoices()}" varStatus="loop">
                    <li>${choice.getDescription()}
                        <p>
                            <c:choose>
                                <c:when test="${choice.getCorrect()==true}">
                                <c:out value="${'Correct'}"/>
                                </c:when>
                           </c:choose>
                        </p>
                    </li>
                </c:forEach>
            </ul>
            <p> You selected: ${question.getUserChoice().getDescription()} </p>
        </c:forEach>
    </div>
    <a href="/quiz-result-management">Back</a>
</div>
</body>

</html>
