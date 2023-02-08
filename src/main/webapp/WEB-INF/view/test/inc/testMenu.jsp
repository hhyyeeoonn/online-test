<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div>
	<a href="${pageContext.request.contextPath}/teacher/modifyTeacherPw">비밀번호변경</a>
	
	<!-- 시험회차(table:test) 관리(CRUD), 개별 시험회차를 클릭하면 그 회차별 문제리스트를 출력(문제관련 CRUD), 개별 문제를 클릭하면 그 문제의 보기 리스트 출력(보기 CRUD) -->
	<a href="${pageContext.request.contextPath}/teacher/test/getTestList">시험관리</a>
	<a href="${pageContext.request.contextPath}/teacher/teacherHome">강사페이지</a>
	<!-- 문제관리를 넣을까? -->
	<!-- 문제개수별 점수분배 -->
	<a href="${pageContext.request.contextPath}/teacher/logout">로그아웃</a>
</div>