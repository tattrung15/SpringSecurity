<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:url value="/login" var="url"></c:url>
	<form action="${url }" method="post">
		<input type="text" name="username" placeholder="username">
		<input type="password" name="password" placeholder="password">
		<button type="submit">Login</button>
	</form>
</body>
</html>