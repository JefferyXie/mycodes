<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dynamic app!</title>
</head>
<body>
<h2>Test Java Servlet in different ways!</h2><br><br>
<b>Trigger SimpleWebServ servlet: </b>&nbsp;
<button type="button" onclick="location.href='/SimpleWebServ/simplewebserv'">Click me!</button>
<br>
<br>
<!--call servlet<form action="paraserv">-->
<form action="helper.jsp">
<b>method="get"</b>
        <table>
        	<tr>
        		<td>Name:</td>
        		<td><input name="userName"/></td>	
        	</tr>
        	<tr>
        		<td>Password:</td>
        		<td><input name="password"/></td>
        	</tr>
        	<tr>
	        	<td><input type="submit" value="Submit"/></td>
        	</tr>
        </table>
</form>
<br>
<form method="post" action="paraserv">
<b>method="post"</b>
        <table>
        	<tr>
        		<td>Name:</td>
        		<td><input name="userName"/></td>	
        	</tr>
        	<tr>
        		<td>Password:</td>
        		<td><input name="password"/></td>
        	</tr>
        	<tr>
	        	<td><input type="submit" value="Submit"/></td>
        	</tr>
        </table>
</form>
</body>
</html>