<%--
  Created by IntelliJ IDEA.
  User: serge
  Date: 11/14/2024
  Time: 8:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Defeated</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/styles.css' />">
</head>
<body>
<jsp:include page="header.jsp"/> <!-- Include the header -->
<div class="question-container">
  <h2>You have chosen to walk away.</h2>
  <p>Maybe next time, brave adventure</p>
    <a href="index.jsp">try again</a>
</body>
</html>
