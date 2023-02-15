<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<div>
		<c:import url="/WEB-INF/view/student/inc/studentMenu.jsp"></c:import>
	</div>
	
	<div>시험 목록</div>
	<div>
		<c:forEach var="t" items="${testList}">
			<div>
			 	<span>${t.testNo}/</span>
				<!-- if문으로 분기시키기 -->
				<span>
					<a href="${pageContext.request.contextPath}/student/paper/addPaper?testNo=${t.testNo}&studentNo=${studentNo}">
						${t.testTitle}
					</a>
				</span> 
				<span>( ${t.testDate} )</span> 
				<span>/</span>
			</div>
		</c:forEach>
	</div>
</body>
</html>