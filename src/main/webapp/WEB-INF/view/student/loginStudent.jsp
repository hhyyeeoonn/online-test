<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<h1>학생 로그인</h1>
	<form method="post" action="${pageContext.request.contextPath}/student/loginStudent">
		<table>
			<tr>
				<td>ID</td>
				<td><input type="text" name="studentId"></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><input type="password" name="studentPw"></td>
			</tr>
		</table>
		<button type="submit">로그인</button>
	</form>
</body>
</html>