<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<!-- Menu include -->
	<div>
		<c:import url="/WEB-INF/view/student/inc/studentMenu.jsp"> </c:import>
	</div>
	<h5>${loginStudent.studentName}님 안녕하세요</h5>
</body>
</html>