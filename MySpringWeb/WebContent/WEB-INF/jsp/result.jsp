<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
	<title>Insert title here</title>
</head>
<body>
<h2>Submitted Student Information</h2>
   <table>
    <tr>
        <td>Name</td>
        <td>${name}</td>
        <td>${stuobj.name}</td>
    </tr>
    <tr>
        <td>Age</td>
        <td>${age}</td>
        <td>${stuobj.age }</td>
    </tr>
    <tr>
        <td>ID</td>
        <td>${id}</td>
        <td>${stuobj.id }</td>
    </tr>
</table>  
</body>
</html>