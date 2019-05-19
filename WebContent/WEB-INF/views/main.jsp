<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SpringJPA</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/save" method ="post">
	<input type="submit" value ="Agregar un nuevo usuario ">
	</form>
</body>
</html>
