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
	<h1>teacher List</h1>
	<a href="${pageContext.request.contextPath}/employee/teacher/addTeacher">강사등록</a>
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
				<td><a href="${pageContext.request.contextPath}/employee/teacher/removeTeacher?teacherNo=${e.teacherNo}">강사삭제</a></td>
			</tr>
		</c:forEach>
	</table>
	
	<form action="${pageContext.request.contextPath}/employee/teacher/teacherList"> <!-- 검색어는 웬만하면 get방식으로 -->
		<input type="text" name="searchWord" value="${searchWord}">
		<select name="searchContent">
			<option></option>
			<option value="sName">이름</option>
			<option value="sId">ID</option>
		</select>
		<button type="submit">검색</button>
	</form>
	<!-- 페이징 -->
	<div>
		<c:if test="${currentPage >1}">
			<a href="${pageContext.request.contextPath}/employee/teacher/teacherList?currentPage=1&searchWord=${searchWord}&searchContent=${searchContent}">처음으로</a>
		</c:if>
		<c:if test="${currentPage > 10}">
			<a href="${pageContext.request.contextPath}/employee/teacher/teacherList?currentPage=${startPage-1}&searchWord=${searchWord}&searchContent=${searchContent}">이전</a>
		</c:if>
		<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
			<a href="${pageContext.request.contextPath}/employee/teacher/teacherList?currentPage=${i}&searchWord=${searchWord}&searchContent=${searchContent}">${i}</a>
		</c:forEach>
		<c:if test="${currentPage < endPage+1 && currentPage != lastPage}">
			<a href="${pageContext.request.contextPath}/employee/teacher/teacherList?currentPage=${endPage+1}&searchWord=${searchWord}&searchContent=${searchContent}">다음</a>
		</c:if>
		<c:if test="${currentPage != lastPage}">
			<a href="${pageContext.request.contextPath}/employee/teacher/teacherList?currentPage=${lastPage}&searchWord=${searchWord}&searchContent=${searchContent}">끝으로</a>
		</c:if>
	</div>
</body>
</html>