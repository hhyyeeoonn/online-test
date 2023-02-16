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
		<c:forEach var="t" items="${testList}" varStatus="tstatus">
			
			<div>
			 	<span>${tstatus.count}</span>
				
				
				<c:forEach var="p" items="${paper}">
				
				<!-- if문으로 분기시키기 -->
				<c:choose>
				
					<c:when test="${p.testNo == null}"> <!-- 응시 가능 시험일 때 -->

						<span>
							<a href="${pageContext.request.contextPath}/student/paper/addPaper?testNo=${t.testNo}&studentNo=${studentNo}">
								${t.testTitle}
							</a>
						</span> 
						<span>/ ${t.testDate} /</span> 
						<span>응시가능</span>
				
					</c:when>
					<c:when test="${p.testNo == t.testNo}"> <!-- 이미 응시한 시험일 때 -->
						<span>
						<a href="${pageContext.request.contextPath}/student/paper/addPaper?testNo=${t.testNo}&studentNo=${studentNo}">
							${t.testTitle}
						</a>
						</span> 
						<span>/ ${t.testDate} /</span> 
						<span>시험종료</span>
					</c:when>
				</c:choose>
				
				</c:forEach>
			</div>
		</c:forEach>
	</div>
</body>
</html>