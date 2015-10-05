<%@page import="java.util.HashMap"%>
<%@page import="java.util.Random"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Helper: store information</title>
</head>
<body>
<%!
public final String USERS = "users";
public final String USER_SESSION = "userid";
String generateUserId(String usr)
{
	return usr + "-" + new Random().nextInt();
}
void getUserId(HttpServletRequest req)
{
//	ServletContext servContext = getServletContext();
//	Object users = servContext.getAttribute(USERS);

}
%>
<%
// create user id and add to attribute "users"
String userName = request.getParameter("userName");
out.println("hello " + userName + " (from helper.jsp)" + "<br>");

String userSess = (String)session.getAttribute(USER_SESSION);
if (null != userSess)
{
	out.println("Session $" + USER_SESSION + " already exists: " + userSess + "<br>");
}
else
{
	out.println("Session $" + USER_SESSION + " doesn't exist!" + "<br>");
}

Object users = pageContext.findAttribute("users");
if (null == users)
{
	users = new HashMap<String, String>();
}

@SuppressWarnings("unchecked")
HashMap<String, String> usersMap = (HashMap<String, String>)users;
String uid = usersMap.get(userName);
if (uid == null)
{
	while (true)
	{
        uid = generateUserId(userName);
        if (!usersMap.containsValue(uid))
			break;
	}
	usersMap.put(userName, uid);
	// default set to page scope
	//pageContext.setAttribute(USERS, usersMap);
	// set to application scope
	pageContext.setAttribute(USERS, usersMap, PageContext.APPLICATION_SCOPE);

	application.setAttribute("sumofusers", usersMap.size());
}
out.println("Set up session: " + uid + "<br>");
session.setAttribute(USER_SESSION, uid);

%>
<h2><a href="display.jsp">Click to see result page</a></h2>
</body>
</html>