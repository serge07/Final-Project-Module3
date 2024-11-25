
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<footer>
    <hr>
    <p>Player Name: ${sessionScope.playerName}</p>
    <p>Player IP: ${sessionScope.playerIp}</p>
    <p>Game Attempts: ${sessionScope.attempts}</p>
</footer>

<style>
    footer {
        background-color: #4A90E2;  /* Light background for footer */
        padding: 20px;              /* Add padding */
        text-align: center;         /* Center align text */
        position: relative;         /* Adjust footer positioning */
        bottom: 0;
        width: 100%;
        margin-top: 20px;          /* Some space above the footer */
        border-top: 1px solid #CCC; /* Top border */
        color: white; /* Text color */
    }

    .footer-content {
        font-size: 14px;           /* Smaller font size */
        color: #333;               /* Text color */
    }

    hr {
        margin-top: 10px;          /* Space above horizontal rule */
        margin-bottom: 10px;       /* Space below horizontal rule */
        border: 0;                 /* Remove default border */
        height: 1px;               /* Height adjustment */
        background-color: #CCC;    /* Color for the hr */
    }
</style>
