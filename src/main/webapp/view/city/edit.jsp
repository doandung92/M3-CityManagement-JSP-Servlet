<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<div class="container" style="width: 40%;">
    <H1>Edit ${city.name}</H1>
    <br>
    <form action="city?action=save" method="post">
        <input name="id" value="${city.id}" hidden/>
        <div class="form-group">
            <label for="name">Name</label>
            <input type="text" class="form-control" name="name" id="name" placeholder="Name" required value="${city.name}">
        </div>
        <div class="form-group">
            <label for="country">Country</label>
            <select class="form-control" id="country" name="country">
                <c:forEach var="country" items="${countries}">
                    <option <c:if test="${country == city.country}">selected</c:if> value="${country}">${country}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label for="area">Area</label>
            <input type="number" class="form-control" name="area" id="area" placeholder="Area" required value="${city.area}" min="1">
        </div>
        <div class="form-group">
            <label for="population">Population</label>
            <input type="number" class="form-control" name="population" id="population" placeholder="Population" min="1" required value="${city.population}">
        </div>
        <div class="form-group">
            <label for="gdp">GDP</label>
            <input type="number" class="form-control" name="gdp" id="gdp" placeholder="Gdp" required min="1" value="${city.gdp}">
        </div>
        <div class="form-group">
            <label for="description">Description</label>
            <textarea rows="3" class="form-control" name="description" id="description" placeholder="Description" required>${city.description}</textarea>
        </div>
        <button type="submit" class="btn btn-primary">Save</button>
        <a class="btn btn-primary" href="city">Cancel</a>
    </form>
</div>
</body>
</html>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
