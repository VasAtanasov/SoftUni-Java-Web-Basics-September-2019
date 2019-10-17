<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
    <nav class="navbar navbar-expand-lg navbar-background">
        <a class="nav-link text-white active h5" href="/index">Home</a>
        <div class="collapse navbar-collapse d-flex justify-content-end">
            <ul class="navbar-nav row">
                <c:choose>
                    <c:when test="${sessionScope.username == null}">
                        <li class="nav-item col-md-4">
                            <a class="nav-link text-white active font-weight-bold" href="/users/login">Login</a>
                        </li>
                        <li class="nav-item col-md-4">
                            <a class="nav-link text-white active font-weight-bold" href="/users/register">Register</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-item col-md-4">
                            <a class="nav-link text-white active font-weight-bold" href="/cars/create">Upload Car</a>
                        </li>
                        <li class="nav-item col-md-4">
                            <a class="nav-link text-white active font-weight-bold" href="/cars/all">All Cars</a>
                        </li>
                        <li class="nav-item col-md-4">
                            <a class="nav-link text-white active font-weight-bold" href="/logout">Logout</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </nav>
</header>
