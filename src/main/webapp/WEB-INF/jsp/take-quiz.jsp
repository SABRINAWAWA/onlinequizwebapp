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
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
                crossorigin="anonymous"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

            <style>

            .content {
                margin: auto;
                width: 50%;
                padding: 20px;
            }
            form{
                margin: auto;
                width: 40%;
                padding: 20px;
            }
            </style>
        </head>

        <body>
            <div>
                <%@ include file="navigation.jsp" %>
                    <div class="header content" align="center">
                        <h3 class="display-7">${quizName}</h3>
                        <h3 class="display-12">Answer 5 Questions in 5 Minutes</h3>
                        <div id="displayCounter">
                        </div>
                        <button onclick="ShowQuestionForm()" id="startButton" style="display: block;" class="btn btn-success">Start Quiz</button>
                    </div>
                    <form method="post" action="/submit-quiz" id="questionsForm" style="display: none;">
                        <div class="mb-3" id="questionBlock1">
                            <label class="form-label"> 1. ${questions.get(0).getDescription()}</label>
                            <c:forEach var="choice" items="${questions.get(0).getChoices()}" varStatus="loop">
                                <div>
                                    <input type="radio" name="selectedChoiceId1" value="${choice.id}"
                                        onclick="QuestionAnswered('questionBlock1')" class="form-check-input"/>
                                    <label class="form-check-label">${choice.getDescription()}</label>
                                </div>
                            </c:forEach>
                        </div>
                        <div class="mb-3" id="questionBlock2">
                            <label class="form-label"> 2. ${questions.get(1).getDescription()}</label>
                            <c:forEach var="choice" items="${questions.get(1).getChoices()}" varStatus="loop">
                                <div>
                                    <input type="radio" name="selectedChoiceId2" value="${choice.id}"
                                        onclick="QuestionAnswered('questionBlock2')" class="form-check-input"/>
                                    <label class="form-check-label">${choice.getDescription()}</label>
                                </div>
                            </c:forEach>
                        </div>
                        <div class="mb-3" id="questionBlock3">
                            <label class="form-label"> 3. ${questions.get(2).getDescription()}</label>
                            <c:forEach var="choice" items="${questions.get(2).getChoices()}" varStatus="loop">
                                <div>
                                    <input type="radio" name="selectedChoiceId3" value="${choice.id}"
                                        onclick="QuestionAnswered('questionBlock3')" class="form-check-input"/>
                                    <label class="form-check-label">${choice.getDescription()}</label>
                                </div>
                            </c:forEach>
                        </div>
                        <div class="mb-3" id="questionBlock4">
                            <label class="form-label"> 4. ${questions.get(3).getDescription()}</label>
                            <c:forEach var="choice" items="${questions.get(3).getChoices()}" varStatus="loop">
                                <div>
                                    <input type="radio" name="selectedChoiceId4" value="${choice.id}"
                                        onclick="QuestionAnswered('questionBlock4')" class="form-check-input"/>
                                    <label class="form-check-label">${choice.getDescription()}</label>
                                </div>
                            </c:forEach>
                        </div>
                        <div class="mb-3" id="questionBlock5">
                            <label class="form-label"> 5. ${questions.get(4).getDescription()}</label>
                            <c:forEach var="choice" items="${questions.get(4).getChoices()}" varStatus="loop">
                                <div>
                                    <input type="radio" name="selectedChoiceId5" value="${choice.id}"
                                        onclick="QuestionAnswered('questionBlock5')" class="form-check-input"/>
                                    <label class="form-check-label">${choice.getDescription()}</label>
                                </div>
                            </c:forEach>
                        </div>
                        <div align="center">
                            <button type="submit" class="btn btn-primary">submit</button>
                        </div>
                    </form>
            </div>
            <script>
                function CountDown(duration, display) {
                    if (!isNaN(duration)) {
                        var timer = duration, minutes, seconds;

                        var interVal = setInterval(function () {
                            minutes = parseInt(timer / 60, 10);
                            seconds = parseInt(timer % 60, 10);

                            minutes = minutes < 10 ? "0" + minutes : minutes;
                            seconds = seconds < 10 ? "0" + seconds : seconds;

                            $(display).html("<b>" + minutes + "m : " + seconds + "s" + "</b>");
                            if (--timer < 0) {
                                timer = duration;
                                SubmitFunction();
                                $('#displayCounter').empty();
                                clearInterval(interVal)
                            }
                        }, 1000);
                    }
                }

                function SubmitFunction() {
                    $('form').submit();
                }

                function ShowQuestionForm() {
                    CountDown(300, $('#displayCounter'));
                    document.getElementById('startButton').style.display = 'None';
                    document.getElementById('questionsForm').style.display = 'block';
                }

                function QuestionAnswered(id) {
                    document.getElementById(id).classList.add("bg-success-subtle");
                }

            </script>

        </body>

        </html>