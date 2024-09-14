<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <html>

    <head>
        <title>Home</title>
    </head>

    <body>
    <%@ include file="navigation.jsp" %>
    <div>
        <h1>Welcome to the Online Quiz Website</h1>
        <h2> Start New Quiz </h2>
        <c:forEach var="category" items="${categories}" varStatus="status">
            <button>
                <a href="/take-quiz/category/${category.getId()}">${category.getName()}</a>
            </button>
        </c:forEach>

        <h2> Quiz History </h2>
        <table border="1">
            <th>Name</th>
            <th>Start Time</th>
            <th>End Time</th>
            <th>Quiz Score</th>
            <th>Quiz Result</th>
            <c:forEach var="quiz" items="${quizzes}" varStatus="status">
                <tr>
                    <td>${quiz.quizName}</td>
                    <td>${quiz.startTime}</td>
                    <td>${quiz.endTime}</td>
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
                    <td>${quiz.numberOfCorrectQuestions}</td>
                    <td>
                        <a href="/quiz/${quiz.getId()}">View Details</a>
                    </td>
                </tr>
            </c:forEach>
    </div>
</body>

</html>