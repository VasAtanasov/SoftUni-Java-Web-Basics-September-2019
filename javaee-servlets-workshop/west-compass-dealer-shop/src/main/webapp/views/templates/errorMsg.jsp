<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${requestScope.errMsg != null}">
    <div id="notifications">
        <div id="errorBox" class="notification">
            <span>Error</span>
        </div>
    </div>
    <div class="text-center text-white font-weight-bold">
    </div>
    <script>
        let errorBox = $('#errorBox');
        errorBox.find('span').text("${requestScope.errMsg}");
        errorBox.fadeIn();
        setTimeout(() => errorBox.fadeOut(), 5000);
    </script>
</c:if>