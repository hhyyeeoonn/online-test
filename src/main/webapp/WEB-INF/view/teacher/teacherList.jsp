<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<h5>${loginTeacher.teacherName}님 안녕하세요</h5>

	<!-- Menu include -->
	<div>
		<c:import url="/WEB-INF/view/teacher/inc/teacherMenu.jsp"> </c:import>
	</div>
	<h1>teacher List</h1>
	<a href="${pageContext.request.contextPath}/teacher/addTeacher">강사등록</a>
	<table border="1">
		<tr>
			<th>Id</th>
			<th>Name</th>
			<th>강사삭제</th>
		</tr>
		<c:forEach var="e" items="${list}">
			<tr>
				<td>${e.teacherId}</td>
				<td>${e.teacherName}</td>
				<td><a href="${pageContext.request.contextPath}/teacher/removeTeacher?teacherNo=${e.teacherNo}">강사삭제</a></td>
			</tr>
		</c:forEach>
	</table>
	<div>
		<a href="${pageContext.request.contextPath}/teacher/teacherList?currentPage=${currentPage-1}">이전</a>
		<a href="${pageContext.request.contextPath}/teacher/teacherList?currentPage=${currentPage+1}">다음</a>
	</div>
</body>
</html>