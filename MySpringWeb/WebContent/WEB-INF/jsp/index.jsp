<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Spring page redirection</title>
</head>
<body>
	<form> 
		<table>
			<tr>
				<td><h3>Page redirection:</h3></td>
				<td><button type="button" onclick="location.href='/MySpring/redirect'">Go</button></td>
			</tr>
			<tr>
				<td><h3>Static page:</h3></td>
				<td><button type="button" onclick="location.href='/MySpring/staticPage'">Go</button></td>
				<td><button type="button" onclick="location.href='/MySpring/htmlPage'">Go</button></td>
			</tr>
			<tr></tr>	
		</table>
	</form>
</body>
</html>