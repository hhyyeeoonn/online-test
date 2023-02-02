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
		<c:import url="/WEB-INF/view/employee/inc/empMenu.jsp"> </c:import>
	</div>
	<h1>Student List</h1>
	<a href="${pageContext.request.contextPath}/employee/student/addStudent">학생등록</a>
	<table border="1">
		<tr>
			<th>Id</th>
			<th>Name</th>
			<th>학생삭제</th>
		</tr>
		<c:forEach var="e" items="${list}">
			<tr>
				<td>${e.studentId}</td>
				<td>${e.studentName}</td>
				<td><a href="${pageContext.request.contextPath}/employee/student/removeStudent?studentNo=${e.studentNo}">학생삭제</a></td>
			</tr>
		</c:forEach>
	</table>
	
	<form action="${pageContext.request.contextPath}/employee/student/studentList"> <!-- 검색어는 웬만하면 get방식으로 -->
		<input type="text" name="searchWord" value="${searchWord}">
		<select name="searchContent">
			<option></option>
			<option value="sName">이름</option>
			<option value="sId">ID</option>
		</select>
		<button type="submit">검색</button>
	</form>
	<div>
		<c:if test="${currentPage} > 2">
			<a href="${pageContext.request.contextPath}/employee/student/studentList?startPage=${startPage}&seartchWord=${searchWord}&searchContent=${searchContent}">처음으로</a>
			<a href="${pageContext.request.contextPath}/employee/student/studentList?currentPage=${currentPage-1}&searchWord=${searchWord}&searchContent=${searchContent}">이전</a>
		</c:if>
		<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
			<a href="${pageContext.request.contextPath}/employee/student/studentList?currentPage=${i}&searchWord=${searchWord}&searchContent=${searchContent}">${i}</a>	
		</c:forEach>
		<c:if test="${currentPage} < ${endPage}">
			<a href="${pageContext.request.contextPath}/employee/student/studentList?currentPage=${currentPage+1}&searchWord=${searchWord}&searchContent=${searchContent}">다음</a>
			<a href="${pageContext.request.contextPath}/employee/student/studentList?endPage=${endPage}&searchWord=${searchWord}&searchContent=${searchContent}">끝으로</a>
		</c:if>
	</div>
</body>
</html>