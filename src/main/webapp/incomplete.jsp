<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Game Incomplete</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/styles.css' />">
</head>
<body>
<jsp:include page="header.jsp"/> <!-- Include the header -->
<div class="question-container">
    <h1>Incomplete Challenge</h1>
    <p>It looks like you didn't answer all the questions correctly. Don't worry! Every great adventure has its challenges.</p>
    <p>You can try again to conquer the quest!</p>

    <a href="index.jsp">Start New Game</a>
</div>
</body>
</html>
