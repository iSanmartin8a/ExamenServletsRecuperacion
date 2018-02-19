<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="listConsole" method="post">
		<input type="submit" value="ver listado">
	</form>
	<table border="1">
		<thead>
			<tr>
				<td>Name</td>
				<td>Company</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="console" items="${listAllConsoles}">
				<tr>
					<td><c:out value="${console.name}"/> </td>
					<td><c:out value="${console.company}"/> </td>
					<td><a href="/delete?name=${console.name}">Delete</a></td>
		    	</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>