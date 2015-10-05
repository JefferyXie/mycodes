<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Test Java Bean</title>
</head>
<body>

<%@ page isELIgnored="false" %>
<jsp:useBean id="user" class="com.simpleweb.beans.User" scope="page"></jsp:useBean>
<jsp:setProperty name="user" property="*"/>

Name: <jsp:getProperty name="user" property="name"/><br/>
Birthday: <jsp:getProperty name="user" property="birth"/><br/>
<br/>
<form name="beanTest" method="POST" action="userbean.jsp">
    Enter a name: <input type="text" name="name" size="50"><br/>
    Enter birthday: <input type="text" name="birth" size = "50"><br/>
    <input type="submit" value="Submit">
</form> 

<!--
<table>
    <tr>
        <th>ID</th>
        <th>Name</th>
    </tr>
    <c:forEach items="${users}" var="user">
        <tr>
            <td>${user.id}</td>
            <td><c:out value="${user.name}" /></td>
        </tr>
    </c:forEach>
</table>
<-->
</body>
</html>