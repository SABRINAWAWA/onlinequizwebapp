<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <title>User Management</title>
</head>

<body>
    <%-- div is for grouping items --%>
        <div>
            <div align="center">
                <h1>User Management</h1>
                <table border="1">
                    <th>Id</th>
                    <th>Full Name</th>
                    <th>Email</th>
                    <th>Password</th>
                    <th>Status</th>
                    <th>Role</th>

                    <c:forEach var="user" items="${users}" varStatus="status">
                        <tr>
                            <td>${user.id}</td>
                            <td>${user.firstName} ${user.lastName}</td>
                            <td>${user.email}</td>
                            <td>${user.password}</td>
                            <td>
                            <c:choose>
                                <c:when test="${user.getActive()==true}">
                                <c:out value="${'Active'}"/>
                                </c:when>
                                <c:otherwise>
                                <c:out value="${'Suspended'}"/>
                                </c:otherwise>
                            </c:choose>
                            </td>
                            <td>
                             <c:choose>
                                 <c:when test="${user.getAdmin()==true}">
                                 <c:out value="${'Admin'}"/>
                                 </c:when>
                                <c:otherwise>
                                <c:out value="${'User'}"/>
                                </c:otherwise>
                            </c:choose>
                            </td>
                            <td>
                             <form method="post" action="/user-management/user/active/${user.id}">
                              <button type="submit">
                                Active
                              </button>
                             </form>
                            </td>
                            <td>
                            <form method="post" action="/user-management/user/suspend/${user.id}">
                             <button type="submit">
                                Suspend
                              </button>
                              </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <a href="/admin-management-portal">Back To Portal</a>
        </div>
</body>

</html>