<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>View Contact</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style>
    .content{
        padding: 20px;
        margin: 20px;
    }
    tbody{
        margin:5px;
    }
    th{
        width:100px;
    }
    tr{
        margin:5px;
    }
    #backToHome{
        color: white;
        font-size: 16px;
        text-decoration: none;
    }
    table{
        width: 500px;
        margin:20px;
        font-size:14px;
    }
    </style>
</head>

<body>
 <%@ include file="navigation.jsp" %>
        <div class=".bg-light content" align="center">
                <h1 class="display-5">View Contact Message</h1>
                <table class="table-hover">
                <tbody>
                    <tr>
                      <th scope="row">Subject: </th>
                      <td>${contact.subject}</td>
                    </tr>
                    <tr>
                      <th scope="row">Email: </th>
                      <td>${contact.email}</td>
                    </tr>
                    <tr>
                      <th scope="row">Create At: </th>
                      <td>${contact.createdAt}</td>
                    </tr>
                    <tr>
                      <th scope="row">Message: </th>
                      <td>${contact.messageContent}</td>
                    </tr>
               </tbody>
               </table>

                <button type="button" class="btn btn-primary btn-sm">
                    <a href="/contact-us-management" id="backToHome">Back To Portal</a>
                </button>
        </div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>

</html>