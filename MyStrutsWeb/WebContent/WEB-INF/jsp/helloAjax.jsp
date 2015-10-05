<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<html>
<head>
	<title>My Struts Web</title>
	<s:head/>
	<sx:head/>
</head>
<body>
	<s:form>
		<sx:autocompleter label="Favourite country" list="countries" name="country"/>	
		<br/>
		<sx:datetimepicker name="deliverydate" label="Delivery date" displayFormat="dd/MM/yyyy"/>
		<br/>
		<s:url id="url" value="/getHello.action"/>
		<sx:div href="%{#url}" delay="3000">Initial Content</sx:div>
		<br/>
		<sx:tabbedpanel id="tabContainer">
			<sx:div label="Tab 1">Tab 1</sx:div>	
			<sx:div label="Tab 2">Tab 2</sx:div>	
		</sx:tabbedpanel>
	</s:form>
</body>
</html>