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
        <h2 class="text-center text-white mt-5">Login</h2>
        <form class="mx-auto w-15" method="post">
            <div class="row">
                <div class="col col-md-3"></div>
                <div class="col col-md-3">
                    <div class="form-group">
                        <div class="label-holder d-flex justify-content-center">
                            <label class="text-center text-white font-weight-bold">Username
                                <input type="text" class="form-control" name="username" value="${param.username}"
                                       placeholder="Username">
                            </label>
                        </div>
                    </div>
                </div>
                <div class="col col-md-3">
                    <div class="form-group">
                        <div class="label-holder d-flex justify-content-center">
                            <label class="text-center text-white font-weight-bold">Password
                                <input type="password" class="form-control" name="password" placeholder="Password">
                            </label>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col col-md-4"></div>
                <div class="col col-md-4">
                    <div class="button-holder d-flex justify-content-center">
                        <input type="submit" class="btn btn-secondary" value="Login"/>
                    </div>
                </div>
            </div>
        </form>
    </main>
</div>
<c:import url="../templates/errorMsg.jsp"/>
<c:import url="../templates/loader.jsp"/>
</body>
</html>
