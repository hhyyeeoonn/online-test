<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div>
	<a href="${pageContext.request.contextPath}/employee/empList">사원관리</a> <!-- 등록시 id체크(employee + teacher + student) -->
	<a href="${pageContext.request.contextPath}/employee/teacher/teacherList">강사관리</a> <!-- 강사목록, 강사등록, 강사삭제 --> <!-- 본인 비밀번호 등록 시험문제(등록, 삭제 등..)에 관련된 것들만 건들 수 있음 -->
	<a href="${pageContext.request.contextPath}/employee/student/studentList">학생관리</a> <!-- 학생목록, 학생등록, 학생삭제 --> <!-- 시험보는 것만 할 수 있음 -->
	<a href="${pageContext.request.contextPath}/employee/modifyEmpPw">비밀번호수정</a>
	<a href="${pageContext.request.contextPath}/employee/logout">로그아웃</a>
</div>