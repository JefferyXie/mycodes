<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
	<title>My Struts Web - Index.jsp</title>
</head>
<body>
	<h2>Hello Struts2</h2>	
	<s:label>Try LoginAction.java</s:label>
	<s:form action="login">
		<s:textfield label="Name" key="name"/>
		<s:password label="Password" key="password"/>
		<s:submit label="Login"/>
	</s:form>
	<br/>
	<s:label>Try HelloAction.java</s:label>
	<!--form action="hello">
		<label for="name">Please enter your name</label><br/>
		<input type="text" name="name"/>
		<input type="submit" value="Say Hello"/>
	</form-->
	<s:form action="getHello" method="post">
		<s:textfield label="Input your name" key="name"/>
		<s:submit value="Call HelloAction"/>
	</s:form>
	<br/>
	<s:label>Try Annotations Employee.java</s:label>
	<s:form action="empinfo" method="post">
		<s:textfield name="name" label="Name" size="20"/>	
		<s:textfield name="age" label="Age" size="20"/>
		<s:submit name="submit" label="Submit" align="right"/>
	</s:form>
	<br/>
	<s:label>Try generator tag</s:label>
	<s:generator separator=", " val="%{'Violet, Indigo, Blue, Green, Yellow, Orange, Red'}" count="7">
		<s:iterator>
			<br/><s:property/>
		</s:iterator>	
	</s:generator>
	<br/><br/>
	<s:label>Try Ajax</s:label><br/>
	<a href="<s:url value="/ajax"/>">AjaxAutocomplete.java > helloAjax.jsp</a> 
</body>
</html>