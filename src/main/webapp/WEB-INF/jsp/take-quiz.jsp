<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <title>Quiz</title>
</head>

<body>
    <div>
        <%@ include file="navigation.jsp" %>
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