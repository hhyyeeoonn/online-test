<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div>
	<a href="${pageContext.request.contextPath}/teacher/logout">시험리스트</a> <!-- 리스트와 시험 응시여부 응시완료된 시험은 점수표시 -->
	<!-- 
		지나간 시험(table:test)리스트+점수 - 점수확인버튼 - 제출답안지 확인(table:paper) o,x로 맞았는지 틀렸는지 확인 가능 - 등수도 출력?
	 	오늘날짜 시험 리스트는 응시버튼 -시험지 출력(table:question x example) - 답안지 제출(table:paper)
	 -->
	<a href="${pageContext.request.contextPath}/student/modifyStudentPw">비밀번호변경</a>
	<a href="${pageContext.request.contextPath}/student/logout">로그아웃</a>
</div>