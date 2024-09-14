<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav>
    <ul>
        <li><a href="/home">Home</a></li>
        <c:choose>
             <c:when test="${session.getAttribute('user') ==null}">
             <li><a href="/login">Log in</a></li>
             <li><a href="/user-register">Register</a></li>
             </c:when>
            <c:otherwise>
            <li><a href="/logout">Log out</a></li>
            </c:otherwise>
        </c:choose>
        <c:choose>
             <c:when test="${user.getAdmin()==true && user.getActive()==true}">
             <li><a href="/contact-us-management">Contacts</a></li>
             <li><a href="/questions-management">Questions</a></li>
             <li><a href="/quiz-result-management">Quiz Results</a></li>
             <li><a href="/user-management">Users</a></li>
             </c:when>
            <c:otherwise>
            <li><a href="/create-contact">Contact Us</a></li>
            </c:otherwise>
        </c:choose>
    </ul>
</nav>