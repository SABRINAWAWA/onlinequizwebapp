<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Quiz</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
        crossorigin="anonymous">
</head>

<body>
    <div>
        <%@ include file="navigation.jsp" %>
        <h1>${quizName}</h1>
            <form method="post" action="/submit-quiz">
                <div>
                    <p> 1. ${questions.get(0).getDescription()}</p>
                    <c:forEach var="choice" items="${questions.get(0).getChoices()}" varStatus="loop">
                        <div>
                        <input type="radio" name="selectedChoiceId1" value="${choice.id}" />
                        <label>${choice.getDescription()}</label>
                        </div>
                    </c:forEach>
                </div>
                <div>
                    <p> 2. ${questions.get(1).getDescription()}</p>
                    <c:forEach var="choice" items="${questions.get(1).getChoices()}" varStatus="loop">
                        <div>
                        <input type="radio" name="selectedChoiceId2" value="${choice.id}" />
                        <label>${choice.getDescription()}</label>
                        </div>
                    </c:forEach>
                </div>
                <div>
                    <p> 3. ${questions.get(2).getDescription()}</p>
                    <c:forEach var="choice" items="${questions.get(2).getChoices()}" varStatus="loop">
                        <div>
                        <input type="radio" name="selectedChoiceId3" value="${choice.id}" />
                        <label>${choice.getDescription()}</label>
                        </div>
                    </c:forEach>
                </div>
                <div>
                    <p> 4. ${questions.get(3).getDescription()}</p>
                    <c:forEach var="choice" items="${questions.get(3).getChoices()}" varStatus="loop">
                        <div>
                        <input type="radio" name="selectedChoiceId4" value="${choice.id}" />
                        <label>${choice.getDescription()}</label>
                        </div>
                    </c:forEach>
                </div>

                <div>
                    <p> 5. ${questions.get(4).getDescription()}</p>
                    <c:forEach var="choice" items="${questions.get(4).getChoices()}" varStatus="loop">
                        <div>
                        <input type="radio" name="selectedChoiceId5" value="${choice.id}" />
                        <label>${choice.getDescription()}</label>
                        </div>
                    </c:forEach>
                </div>
                <button type="submit">submit</button>
            </form>
    </div>
</body>

</html>