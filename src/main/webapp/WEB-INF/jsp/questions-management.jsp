<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <title>Questions Management</title>
</head>

<body>
 <%@ include file="admin-navigation.jsp" %>
        <div>
            <div align="center">
                <h1>Questions Management</h1>
                <table border="1">
                    <th>Id</th>
                    <th>Description</th>
                    <th>Category</th>
                    <th>Password</th>
                    <th>Choices</th>
                    <th>Status</th>

                    <c:forEach var="question" items="${questions}" varStatus="status">
                        <tr>
                            <td>${question.id}</td>
                            <td>${question.description}</td>
                            <td>${question.category.name}</td>
                            <td>${question.choices}</td>
                            <td>
                            <c:choose>
                                <c:when test="${question.isActive()==true}">
                                <c:out value="${'Active'}"/>
                                </c:when>
                                <c:otherwise>
                                <c:out value="${'Suspended'}"/>
                                </c:otherwise>
                            </c:choose>
                            </td>
                            <td>
                              <button>
                                <a href="/questions-management/question/edit/${question.id}">Edit</a>
                              </button>
                            </td>
                            <td>
                             <form method="post" action="/questions-management/question/active/${question.id}">
                              <button type="submit">
                                Active
                              </button>
                             </form>
                            </td>
                            <td>
                            <form method="post" action="/questions-management/question/suspend/${question.id}">
                             <button type="submit">
                                Suspend
                              </button>
                              </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <a href="/questions-management/question/new">Add</a>
            <a href="/admin-management-portal">Back To Portal</a>
        </div>
</body>

</html>