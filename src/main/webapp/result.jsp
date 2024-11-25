<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Game Result</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/styles.css' />">
</head>
<body>
<jsp:include page="header.jsp"/> <!-- Include the header -->
<div class="question-container">
    <h1>Congratulations!</h1>
    <p>You have successfully completed the challenge!</p>

    <h2>Game Summary</h2>
    <p>
        Player Name: ${sessionScope.playerName}<br>
        Player IP: ${sessionScope.playerIp}<br>
        Total Attempts: ${sessionScope.attempts}<br>
        <strong>Your Score: </strong>
        <c:choose>
            <c:when test="${sessionScope.attempts > 0}">
                ${sessionScope.attempts} attempts
            </c:when>
            <c:otherwise>
                1 attempt (Well done!)
            </c:otherwise>
        </c:choose>
    </p>

    <p>Thank you for playing!</p>
    <a href="index.jsp">Start New Game</a>
</div>

</body>
</html>
