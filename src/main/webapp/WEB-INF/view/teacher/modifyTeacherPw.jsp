<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<h5>${loginTeacher.teacherName}님 비밀번호 변경</h5>
	<form method="post" action="${pageContext.request.contextPath}/teacher/modifyTeacherPw">
		<table>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="oldPw"></td>
			</tr>
			<tr>
				<td>새 비밀번호</td>
				<td><input type="password" name="newPw"></td>
			</tr>
		</table>
		<button type="submit">변경</button>
	</form>
</body>
</html>