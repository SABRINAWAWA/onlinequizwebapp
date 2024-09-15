<%@ page contentType="text/html;charset=UTF-8" language="java" %>

    <html>

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Register</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
            integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <style>
            .content {
                margin: auto;
                width: 40%;
                padding: 20px;
            }
        </style>
    </head>

    <body>
        <%@ include file="navigation.jsp" %>
            <div class="content" align="center">
                <h2 align="center">Register</h2>
                <form method="post" action="/user-register" align="center" class="align-items-center">
                    <div class="mb-3 row">
                        <label class="col-sm-3 col-form-label">First Name:</label>
                        <div class="col-sm-9">
                            <input type="text" name="firstName" class="form-control">
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label class="col-sm-3 col-form-label">Last Name:</label>
                        <div class="col-sm-9">
                            <input type="text" name="lastName" class="form-control">
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label class="col-sm-3 col-form-label">Email</label>
                        <div class="col-sm-9">
                            <input type="email" name="email" class="form-control"  placeholder="name@example.com">
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label class="col-sm-3 col-form-label">Password</label>
                        <div class="col-sm-9">
                            <input type="password" name="password" class="form-control">
                        </div>
                    </div>
                    <button type="submit" class="btn btn-primary btn-sm">Register</button>
                </form>
                <a href="/login">Already Have An Account? Login Here</a>
            </div>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
                crossorigin="anonymous"></script>
    </body>

    </html>