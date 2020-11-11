<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>City List</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <style>
        table {
            text-align: center;
        }

        td {
            vertical-align: middle !important;
        }
    </style>
</head>
<body>

<div class="container">
    <H1>City List</H1>
    <div class="row">
        <div class="col">
            <br>
            <a class="btn btn-primary" href="city?action=add" style="margin-bottom: 10px">Add New City</a>
            <c:if test="${sessionScope.message != null}">
                <div class="alert alert-success alert-dismissible fade show" role="alert">
                    <strong>Successfully!</strong> ${sessionScope.message}
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
            </c:if>
            <c:remove var="message" scope="session" />
            <table class="table table-hover table-bordered">
                <thead>
                <tr>
                    <td>#</td>
                    <td>Name</td>
                    <td>Country</td>
                    <td>Action</td>
                </tr>
                </thead>
                <c:forEach var="city" items="${cities}" varStatus="loopStat">
                    <tr>
                        <td>${loopStat.index + 1}</td>
                        <td><a href="city?action=detail&id=${city.id}">${city.name}</a></td>
                        <td>${city.country}</td>
                        <td>
                            <a class="btn btn-primary" href="city?action=edit&id=${city.id}">Edit</a>
                            <a class="btn btn-danger" href="city?action=delete&id=${city.id}">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>
</body>
</html>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
