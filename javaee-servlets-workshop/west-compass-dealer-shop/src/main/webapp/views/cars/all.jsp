<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <c:import url="../templates/head.jsp"/>
</head>
<body>
<div class="container-fluid">
    <c:import url="../templates/header.jsp"/>
    <main>
        <h2 class="text-center text-white mt-5">West Compass Offers</h2>
        <hr style="width: 50%"/>
        <div class='row mb-4 d-flex justify-content-around'>
            <c:choose>
            <c:when test="${not empty requestScope.cars}">
            <c:forEach var="car" items="${requestScope.cars}">
            <div class="col-md-4 d-flex flex-column bg-text mb-3">
                <h2>Owner: ${car.userUsername}</h2>
                <h2>Brand: ${car.brand}</h2>
                <h4>Model: ${car.model}</h4>
                <h4>Year: ${car.year}</h4>
                <h4>Engine: ${car.engine}</h4>
            </div>
            </c:forEach>
            </c:when>
            <c:otherwise>
                <c:import url="../templates/noOffers.jsp"/>
            </c:otherwise>
            </c:choose>
    </main>
</div>
</body>
</html>
