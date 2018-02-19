<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="listVideoGame" method="post">
		<input type="submit" value="see list">
	</form>
	<form action="OrderByTitle" method="post">
		<input type="submit" value="order by title" >
	</form>
	<form action="OrderByReleaseDate" method="post">
		<input type="submit" value="order by release date" >
	</form>
	<table border="1">
		<thead>
			<tr>
				<td>Title</td>
				<td>Age</td>
				<td>Release Date</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="videogame" items="${listAllVideoGames}">
				<tr>
					<td><c:out value="${videogame.title}"/> </td>
					<td><c:out value="${videogame.age}"/> </td>
					<td><c:out value="${videogame.releaseDate}"/> </td>
					<td><a href="/deleteVG?name=${videogame.title}">Delete</a></td>
					
		    	</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>