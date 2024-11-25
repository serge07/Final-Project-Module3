<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div class="header">
    <img src="<c:url value='/images/SpaceQuest_logo.png' />" alt="Game Logo" class="header-logo">
    <h1>Text Quest</h1>
</div>

<style>
    .header {
        text-align: center; /* Center align the header content */
        padding: 20px; /* Add padding */
        background-color: #4A90E2; /* Header background color */
        color: white; /* Text color */
    }

    .header-logo {
        max-width: 150px; /* Limit logo size */
        height: auto; /* Maintain aspect ratio */
    }
</style>
