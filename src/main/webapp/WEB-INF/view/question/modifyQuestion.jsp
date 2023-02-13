<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
	<script>
		$(document).ready(function() {
			
			$('.ox').each(function() {
				//console.log($(this).val());
				if($(this).val() == '정답') {
					$(this).next().prop('checked',true);
				}
			});
			
			$('#modifyBtn').click(function() {
				$('#modifyForm').submit();
			});
			
		});
	</script>

</head>
<body>
	<!-- Menu include -->
	<div>
		<c:import url="/WEB-INF/view/test/inc/testMenu.jsp"> </c:import>
	</div>
	
	<div>
		<h3>시험문제 수정</h3>
	</div>
	
	<div>
		<form method="post" action="${pageContext.request.contextPath}/teacher/question/modifyQuestion" id="modifyForm">
			<input type = "hidden" name = "testNo" value = "${testNo}">
			<input type = "hidden" name = "questionNo" value = "${questionNo}">
			<table>
				<tr>
					<td colspan = "2">
						${questionList.questionIdx}번 문제
						<input type="hidden" name="questionIdx" value="${questionList.questionIdx}">
					</td>
				</tr>
				<tr>
					<td colspan = "2">
						<input type = "text" name = "questionTitle" value="${questionList.questionTitle}">
					</td>
				</tr>
				<tr>
					<td colspan = "2">선택지</td>
				</tr>
				<c:forEach var="e" items="${exampleList}" begin="0" end="4" varStatus="status">
					<tr>
						<td>
							${e.exampleIdx} 번)
							<input type="hidden" name="exampleNo" value="${e.exampleNo}">
							<input type="hidden" name="exampleIdx" value="${e.exampleIdx}">
							<input type = "text" name = "exampleTitle" value="${e.exampleTitle}">
						</td>
						<td>
							<input type="hidden" class="ox" value="${e.exampleOx}">
							<input type ="radio" name = "eOx" value = "${status.index+1}"> 정답
						</td>
					</tr>
				</c:forEach>
			</table>
			<button type = "button" id = "modifyBtn">문제수정</button>
		</form>
	</div>
</body>
</html>