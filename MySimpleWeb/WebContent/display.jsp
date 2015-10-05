<%@page import="java.util.Enumeration"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
out.println("request.getContextPath(): " + request.getContextPath() + "<br>");
out.println("Sum of unique users (from application context): " + application.getAttribute("sumofusers") + "<br>");
out.println("Current user (from session): " + session.getAttribute("userid") + "<br>");

out.println("Take all users from pageContext.getAttribute(\"users\"): " + "<br>");
int index = 0;
@SuppressWarnings("unchecked")
HashMap<String, String> users = (HashMap<String, String>)pageContext.getAttribute("users", PageContext.APPLICATION_SCOPE);
if (users == null)
{
	out.println("null! Maybe the attribute is not in the scope." + "<br>");
}
else
{
	for (Map.Entry<String, String> entry : users.entrySet())
	{
		String name = entry.getKey();
		String id = entry.getValue();
		out.println(++index + ": " + name + ", " + id + "<br>");
	}
}
out.println("Check config (ServletConfig):" + "<br>");
Enumeration<?> parms = config.getInitParameterNames();
while (parms.hasMoreElements())
	out.println(parms.nextElement() + "<br>");
ServletContext servContext = config.getServletContext();
out.println("config.getServletName(): " + config.getServletName() + "<br>");
%>
</body>
</html>