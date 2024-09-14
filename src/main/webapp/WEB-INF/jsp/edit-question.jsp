<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <title>Update Question</title>
</head>

<body>
 <%@ include file="navigation.jsp" %>
        <div>
            <form method="post" action="/questions-management/question/edit/${question.getId()}">
                <h2>Update Question</h2>
                <div>
                    <label>Category: </label>
                    <select name="categoryId" id="categoryId">
                        <option value="" selected disabled hidden>Choose here</option>
                        <c:forEach var="category" items="${categories}">
                            <option value="${category.getId()}">${category.getName()}</option>
                        </c:forEach>
                    </select>
                </div>
                <div>
                    <label>Description: </label>
                    <textarea type="text" name="description" maxlength="500">${question.getDescription()}</textarea>
                </div>
                <div>
                    <h3>Option 1</h3>
                    <div>
                        <label>Description: </label>
                        <input type="hidden" id="choiceId1" name="choiceId1" value="${question.getChoices().get(0).getId()}">
                        <input type="text" name="choiceDescription1" value="${question.getChoices().get(0).getDescription()}">
                    </div>
                    <div>
                        <label>Correct Answer? </label>
                        <select name="choiceIsCorrect1" id="choiceIsCorrect1">
                            <option value="false">No</option>
                            <option value="true">Yes</option>
                        </select>
                    </div>
                </div>

                <div>
                    <h3>Option 2</h3>
                    <div>
                        <label>Description: </label>
                        <input type="hidden" id="choiceId2" name="choiceId2" value="${question.getChoices().get(1).getId()}">
                        <input type="text" name="choiceDescription2" value="${question.getChoices().get(1).getDescription()}">
                    </div>
                    <div>
                        <label>Correct Answer? </label>
                        <select name="choiceIsCorrect2" id="choiceIsCorrect2">
                            <option value="false">No</option>
                            <option value="true">Yes</option>
                        </select>
                    </div>
                </div>
                <div>
                    <h3>Option 3</h3>
                    <div>
                        <label>Description: </label>
                        <input type="hidden" id="choiceId3" name="choiceId3" value="${question.getChoices().get(2).getId()}">
                        <input type="text" name="choiceDescription3" value="${question.getChoices().get(2).getDescription()}">
                    </div>
                    <div>
                        <label>Correct Answer? </label>
                        <select name="choiceIsCorrect3" id="choiceIsCorrect3"">
                            <option value="false">No</option>
                            <option value="true">Yes</option>
                        </select>
                    </div>
                </div>
                <div>
                    <h3>Option 4</h3>
                    <div>
                        <label>Description: </label>
                        <input type="hidden" id="choiceId4" name="choiceId4" value="${question.getChoices().get(3).getId()}">
                        <input type="text" name="choiceDescription4" value="${question.getChoices().get(3).getDescription()}">
                    </div>
                    <div>
                        <label>Correct Answer? </label>
                        <select name="choiceIsCorrect4" id="choiceIsCorrect4">
                            <option value="false">No</option>
                            <option value="true">Yes</option>
                        </select>
                    </div>
                </div>
                <button type="submit">Submit</button>
                <button type="cancel"><a href="/questions-management">Cancel</a></button>
            </form>
        </div>
</body>

</html>