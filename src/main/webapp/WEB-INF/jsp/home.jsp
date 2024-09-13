<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
        <a href="/quiz/take-quiz/category/${category.getId()}">${category.getName()}</a>
    </button>
    </c:forEach>
    <h2> Quiz History </h2>
</div>
</body>

</html>
