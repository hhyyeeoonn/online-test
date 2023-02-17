<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
	
	<!-- 강사가 작성완료한 시험만 출력이 된다 -->
	<!-- 응시가 완료된 시험에 들어가면 점수와 함께 학생의 답안지를 볼 수 있다 -->
	
	<div>시험 목록</div>
	<div>
		<table>
			<c:forEach var="t" items="${testList}" varStatus="tstatus">
				<tr>
					<td>
					 	<span>${tstatus.count}</span>
					</td>
						
					<c:forEach var="p" items="${paper}">
						<c:choose>
						
							<c:when test="${p.testNo == t.testNo && p.paperCnt == 0 && t.testDate > now}"> <!-- 시험기간이 지나지않았고 미응시 상태일 때 -->
								<td>${t.testTitle}</td>
								<td>
									<a href="${pageContext.request.contextPath}/student/paper/addPaper?testNo=${t.testNo}&studentNo=${studentNo}">
										시험보기
									</a>
								</td> 
								<td>${t.testDate} </td> 
								<td>시험기간</td>
								<td>미응시</td>				
							</c:when>
							
							<c:when test="${p.testNo == t.testNo && p.paperCnt == 20 && t.testDate > now}"> <!-- 시험기간이 지나지않았고 응시를 완료했을 때 -->
								<td>${t.testTitle}</td>
								<td>
									<a href="${pageContext.request.contextPath}/student/paper/addPaper?testNo=${t.testNo}&studentNo=${studentNo}">
										점수확인
									</a>
								</td> 
								<td>${t.testDate}</td> 
								<td>시험기간</td>
								<td>응시완료</td>
							</c:when>
							
							<c:when test="${p.testNo == t.testNo && p.paperCnt == 0 &&t.testDate < now}"> 	<!-- 응시기간이 지났고 미응시상태일 때 -->
								<td>${t.testTitle}</td>
								<td>
									<a href="${pageContext.request.contextPath}/student/paper/addPaper?testNo=${t.testNo}&studentNo=${studentNo}">
										점수확인
									</a>
								</td> 
								<td>${t.testDate}</td> 
								<td>시험종료</td>
								<td>미응시</td>
							</c:when>
							
							<c:when test="${p.testNo == t.testNo && p.paperCnt == 20 && t.testDate < now}"> <!-- 응시기간이 지났고 응시완료일 때 -->
								<td>${t.testTitle}</td>
								<td>
									<a href="${pageContext.request.contextPath}/student/paper/addPaper?testNo=${t.testNo}&studentNo=${studentNo}">
										점수확인
									</a>
								</td> 
								<td>${t.testDate}</td> 
								<td>시험종료</td>
								<td>응시완료</td>
							</c:when>
							
						</c:choose>
					</c:forEach>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>