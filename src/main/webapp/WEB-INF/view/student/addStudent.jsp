<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>학생추가</h1>
	<div>${errorMsg}</div>
	<form method="post" action="${pageContext.request.contextPath}/student/addStudent">
		<table border="1">
			<tr>
				<td>Id</td>
				<td><input type="text" name="studentId"></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><input type="text" name="studentPw"></td>
			</tr>
			<tr>
				<td>Name</td>
				<td><input type="text" name="studentName"></td>
			</tr>	
		</table>
		<button type="submit">학생추가</button>
	</form>
</body>
</html>