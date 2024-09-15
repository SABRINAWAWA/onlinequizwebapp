<%@ page contentType="text/html;charset=UTF-8" language="java" %>

    <html>

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Contact Us</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
            integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <style>
            .content {
                margin: auto;
                width: 50%;
                padding: 20px;
            }
        </style>
    </head>

    <body>
        <%@ include file="navigation.jsp" %>
            <div class="content" align="center">
                <h2 align="center">Contact Us</h2>
                <form method="post" action="/create-contact" align="center" class="align-items-center">
                    <div class="mb-3 row">
                        <label class="col-sm-2 col-form-label">Subject</label>
                        <div class="col-sm-10">
                            <input type="text" name="subject" class="form-control">
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label class="col-sm-2 col-form-label">Email</label>
                        <div class="col-sm-10">
                            <input type="email" name="email" class="form-control">
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label class="col-sm-2 col-form-label">Message</label>
                        <div class="col-sm-10">
                            <textarea type="text" name="message" class="form-control"
                                placeholder="Write message here"></textarea>
                        </div>
                    </div>
                    <button type="submit" class="btn btn-primary btn-sm">Submit</button>
                </form>
                <a href="/home">Back to Home Page</a>
            </div>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
                crossorigin="anonymous"></script>
    </body>

    </html>