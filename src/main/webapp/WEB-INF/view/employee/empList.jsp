<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- empMenu include -->
	<div>
		<!-- jsp:include page="/WEB-INF/view/employee/inc/empMenu.jsp" /jsp:include -->
		<c:import url="/WEB-INF/view/employee/inc/empMenu.jsp"></c:import> <!-- requestDispatch가 하는 기술 백엔드 기술 forwarding과 include는 서버기준이므로 context가 필요없다 WEB-INF도 적어줘야함 -->
	</div>
	<h1>Employee List</h1>
	<a href="${pageContext.request.contextPath}/employee/addEmp">사원등록</a>
	<table border="1">
		<tr>
			<th>empId</th>
			<th>empName</th>
			<th>사원삭제</th>
		</tr>
		<c:forEach var="e" items="${list}">
			<tr>
				<td>${e.empId}</td>
				<td>${e.empName}</td>
				<td><a href="${pageContext.request.contextPath}/employee/removeEmp?empNo=${e.empNo}">사원삭제</a></td>
			</tr>
		</c:forEach>
	</table>
	<form action="${pageContext.request.contextPath}/employee/empList"> <!-- 검색어는 웬만하면 get방식으로 -->
		<input type="text" name="searchWord" value="${searchWord}">
		<select name="searchContent">
			<option></option>
			<option value="sName">이름</option>
			<option value="sId">ID</option>
		</select>
		<button type="submit">검색</button>
	</form>
	<div>
		<a href="${pageContext.request.contextPath}/employee/empList?currentPage=1&searchWord=${searchWord}&searchContent=${searchContent}">처음으로</a>
		<c:if test="${currentPage > 10}">
			<a href="${pageContext.request.contextPath}/employee/empList?currentPage=${startPage-1}&searchWord=${searchWord}&searchContent=${searchContent}">이전</a>
		</c:if>
		<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
			<a href="${pageContext.request.contextPath}/employee/empList?currentPage=${i}&searchWord=${searchWord}&searchContent=${searchContent}">${i}</a>
		</c:forEach>
		<c:if test="${currentPage < endPage+1 && currentPage != lastPage}">
			<a href="${pageContext.request.contextPath}/employee/empList?currentPage=${endPage+1}&searchWord=${searchWord}&searchContent=${searchContent}">다음</a>
		</c:if>	
		<a href="${pageContext.request.contextPath}/employee/empList?currentPage=${lastPage}&searchWord=${searchWord}&searchContent=${searchContent}">끝으로</a>
	</div>
</body>
</html>