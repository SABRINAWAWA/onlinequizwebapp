<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <html>

        <head>
            <meta charset="utf-8">
            <meta name="viewport" content="width=device-width, initial-scale=1">
            <title>Add New Question</title>
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
                integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
                crossorigin="anonymous">
            <style>
                .content {
                    padding: 50px;
                    margin: 50px;
                }
            </style>
        </head>

        <body>
            <%@ include file="admin-navigation.jsp" %>
                <div class="content">
                    <h2 align="center">Create a new question</h2>
                    <form method="post" action="/questions-management/question/new">
                        <div class="row mb-3">
                            <label class="col-sm-2 col-form-label">Category: </label>
                            <div class="col-sm-10">
                                <select name="categoryId" id="categoryId" class="form-select">
                                <option value="" selected disabled hidden>Choose here</option>
                                    <c:forEach var="category" items="${categories}">
                                        <option value="${category.getId()}">${category.getName()}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="row mb-3">
                            <label class="col-sm-2 col-form-label">Description: </label>
                            <div class="col-sm-10">
                                <textarea type="text" name="description" class="form-control"
                                    placeholder="Write question here"></textarea>
                            </div>
                        </div>
                        <h4>Option 1</h4>
                        <div>
                            <div class="row mb-3">
                                <label class="col-sm-2 col-form-label">Description: </label>
                                <div class="col-sm-10">
                                    <input type="text" name="choiceDescription1" class="form-control">
                                </div>
                            </div>
                            <div class="row mb-3">
                                <label class="col-sm-2 col-form-label">Correct Answer? </label>
                                <div class="col-sm-10">
                                    <select name="choiceIsCorrect1" id="choiceIsCorrect1" class="form-select">
                                        <option value="false">No</option>
                                        <option value="true">Yes</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <h4>Option 2</h4>
                        <div>
                            <div class="row mb-3">
                                <label class="col-sm-2 col-form-label">Description: </label>
                                <div class="col-sm-10">
                                    <input type="text" name="choiceDescription2" class="form-control">
                                </div>
                            </div>
                            <div class="row mb-3">
                                <label class="col-sm-2 col-form-label">Correct Answer? </label>
                                <div class="col-sm-10">
                                    <select name="choiceIsCorrect2" id="choiceIsCorrect2" class="form-select">
                                        <option value="false">No</option>
                                        <option value="true">Yes</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <h4>Option 3</h4>
                        <div>
                            <div class="row mb-3">
                                <label class="col-sm-2 col-form-label">Description: </label>
                                <div class="col-sm-10">
                                    <input type="text" name="choiceDescription3" class="form-control">
                                </div>
                            </div>
                            <div class="row mb-3">
                                <label class="col-sm-2 col-form-label">Correct Answer? </label>
                                <div class="col-sm-10">
                                    <select name="choiceIsCorrect3" id="choiceIsCorrect3" class="form-select">
                                        <option value="false">No</option>
                                        <option value="true">Yes</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <h4>Option 4</h4>
                        <div>
                            <div class="row mb-3">
                                <label class="col-sm-2 col-form-label">Description: </label>
                                <div class="col-sm-10">
                                    <input type="text" name="choiceDescription4" class="form-control">
                                </div>
                            </div>
                            <div class="row mb-3">
                                <label class="col-sm-2 col-form-label">Correct Answer? </label>
                                <div class="col-sm-10">
                                    <select name="choiceIsCorrect4" id="choiceIsCorrect4" class="form-select">
                                        <option value="false">No</option>
                                        <option value="true">Yes</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div align="center">
                        <button type="submit" class="btn btn-primary btn-sm">Submit</button>
                        <a class="btn btn-secondary btn-sm" href="/questions-management">Cancel</a>
                        </div>
                    </form>
                </div>
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
                    integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
                    crossorigin="anonymous"></script>
        </body>

        </html>