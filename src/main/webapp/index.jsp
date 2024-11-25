<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <title>Welcome to the Text Quest</title>
  <link rel="stylesheet" type="text/css" href="<c:url value='/css/styles.css' />">
</head>
<body>
<jsp:include page="header.jsp"/> <!-- Include the header -->
<h1><%= "Introduction" %></h1>
<div class="question-container">
<p>
  The game is set in the distant future - year 3018
  at a time when humans share the Earth with robots and space travel is routine.

</p>

<h1><%= "Meet the Crew" %></h1>

<p>
  My name is John Squirrels, I am the captain of the Galactic Rush. <br/>
  My name is Elanor Carrey. You can call me Ellie. I am the navigator here on the Galactic Rush.
  <br/>
  <br/>
  Do you accept the challenge to embark on a perilous journey?

</p>
  <br/>
  <br/>
<form action="quest" method="post">
  <label for="playerName">Enter your name:</label>
  <input type="text" id="playerName" name="playerName">
  <br/>
  <br/>
  <input type="hidden" name="action" value="accept">
  <button type="submit">Accept the challenge</button>

</form>
<br/>
<form action="quest" method="post">
<input type="hidden" name="action" value="reject">
<button type="submit">Reject the challenge</button>

</form>
</div>
</body>

</html>