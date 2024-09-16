<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

        <nav class="navbar navbar-expand-lg bg-body-tertiary">
            <div class="container-fluid">
                <c:choose>
                    <c:when test="${user.getAdmin()==true}">
                        <a class="navbar-brand" aria-current="page" href="/admin-management-portal">Online Quiz</a>
                    </c:when>
                    <c:otherwise>
                        <a class="navbar-brand" aria-current="page" href="/home">Online Quiz</a>
                    </c:otherwise>
                </c:choose>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false"
                    aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNavDropdown">
                    <ul class="navbar-nav">
                        <c:choose>
                            <c:when test="${session.getAttribute('user') !=null}">
                                <c:choose>
                                    <c:when test="${user.getAdmin() ==true}">
                                        <li class="nav-item">
                                            <a class="nav-link active" aria-current="page"
                                                href="/admin-management-portal">Home</a>
                                        </li>
                                    </c:when>
                                    <c:otherwise>
                                        <li class="nav-item">
                                            <a class="nav-link active" aria-current="page" href="/home">Home</a>
                                        </li>
                                    </c:otherwise>
                                </c:choose>
                            </c:when>
                        </c:choose>
                        <c:choose>
                            <c:when test="${session.getAttribute('user') ==null}">
                                <li class="nav-item">
                                    <a class="nav-link" href="/login">Log in</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="/user-register">Register</a>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li class="nav-item">
                                    <a class="nav-link" href="/logout">Log out</a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${user.getAdmin()==true}">
                                <li class="nav-item dropdown">
                                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                                        aria-expanded="false">
                                        Managements
                                    </a>
                                    <ul class="dropdown-menu">
                                        <li><a class="dropdown-item" href="/contact-us-management">Contacts</a></li>
                                        <li><a class="dropdown-item" href="/questions-management">Questions</a></li>
                                        <li><a class="dropdown-item" href="/quiz-result-management/page/1">Quiz Results</a>
                                        </li>
                                        <li><a class="dropdown-item" href="/user-management/page/1">Users</a></li>
                                    </ul>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <c:choose>
                                    <c:when test="${hasOpenQuiz==true}">
                                        <li class="nav-item">
                                            <a class="nav-link" href="/take-quiz/category/${categoryId}">Taking Quiz</a>
                                        </li>
                                    </c:when>
                                </c:choose>
                                <li class="nav-item">
                                    <a class="nav-link active" aria-current="page" href="/create-contact">Contact Us</a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </ul>
                </div>
            </div>
        </nav>