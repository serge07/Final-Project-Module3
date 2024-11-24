<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Welcome to the Text Quest</title>
</head>
<body>
<h1><%= "Introduction" %></h1>

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
  Do you want to play the game?

</p>
<br/>

<form action="accept-challenge.jsp">
  First Name: <input type="text" name="firstName"> <br/>
  Last Name:  <input tyoe="text" name="lastName"> <br/>

</form>

<a href="welcome-quest">Hello Servlet</a>
</body>
</html>