<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <title>Add New Question</title>
</head>

<body>
 <%@ include file="navigation.jsp" %>
        <div>
            <form method="post" action="/questions-management/question/new">
                <h2>Create a new question</h2>
                <div>
                    <label>Category: </label>
                    <select name="categoryId" id="categoryId">
                        <c:forEach var="category" items="${categories}">
                            <option value="${category.getId()}">${category.getName()}</option>
                        </c:forEach>
                    </select>
                </div>
                <div>
                    <label>Description: </label>
                    <textarea type="text" name="description"></textarea>
                </div>
                <div>
                    <h3>Option 1</h3>
                    <div>
                        <label>Description: </label>
                        <input type="text" name="choiceDescription1">
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
                        <input type="text" name="choiceDescription2">
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
                        <input type="text" name="choiceDescription3">
                    </div>
                    <div>
                        <label>Correct Answer? </label>
                        <select name="choiceIsCorrect3" id="choiceIsCorrect3">

                            <option value="false">No</option>
                            <option value="true">Yes</option>
                        </select>
                    </div>
                </div>
                <div>
                    <h3>Option 4</h3>
                    <div>
                        <label>Description: </label>
                        <input type="text" name="choiceDescription4">
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
            </form>
        </div>
</body>

</html>