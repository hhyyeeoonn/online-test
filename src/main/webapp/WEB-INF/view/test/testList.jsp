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
		<c:import url="/WEB-INF/view/test/inc/testMenu.jsp"> </c:import>
	</div>

	<div>
		<form action="${pageContext.request.contextPath}/teacher/test/addTest" method="post">
			<div>새 시험 등록하기</div>
			<table>
				<tr>
					<td>시행일자: </td>
					<td><input type="text" name="testDate" placeholder="20xx-xx-xx"></td>
				</tr>
				<tr>
					<td>제목</td>
					<td><input type="text" name="testTitle"></td>
				</tr>
			</table>
			<button type="submit">시험등록</button>
		</form>
	</div>
	<br>
	<div>시험 목록</div>
	<div>
		<c:forEach var="t" items="${testList}">
			<div>
			 	<span>${t.testNo}/</span>
				<span>
					<a href="${pageContext.request.contextPath}/teacher/test/testListOne?testNo=${t.testNo}">
						${t.testTitle}
					</a>
				</span> 
				<span>( ${t.testDate} )</span> 
				<span>/ ${t.testState}</span>
			</div>
		</c:forEach>
	</div>
</body>
</html>