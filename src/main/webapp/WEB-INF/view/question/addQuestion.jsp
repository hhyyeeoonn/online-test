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
			$('#addBtn').click(function() {
				$('#addForm').submit();
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
		<h3>시험문제 등록</h3>
	</div>
	
	<div>
		<form method="post" action="${pageContext.request.contextPath}/teacher/question/addQuestion?testNo=${testNo}" id="addForm">
			<input type = "hidden" name = "testNo" value = "${testNo}">
			<input type = "hidden" name = "questionIdx" value = "${questionCnt + 1}">
			<table>
				<tr>
					<td colspan = "2">${questionCnt + 1}번 문제</td>
				</tr>
				<tr>
					<td colspan = "2">
						<input type = "text" name = "questionTitle">
					</td>
				</tr>
				<tr>
					<td colspan = "2">선택지</td>
				</tr>
				<tr>
					<td>
						<input type = "hidden" name = "exampleIdx" value = "1">
						<input type = "text" name = "exampleTitle">
					</td>
					<td>
						<input type ="radio" name = "eOx" value = "1"> 정답
					</td>
				</tr>
				<tr>
					<td>
						<input type = "hidden" name = "exampleIdx" value = "2">
						<input type = "text" name = "exampleTitle">
					</td>
					<td>
						<input type ="radio" name = "eOx" value = "2"> 정답
					</td>
				</tr>
				<tr>
					<td>
						<input type = "hidden" name = "exampleIdx" value = "3">
						<input type = "text" name = "exampleTitle">
					</td>
					<td>
						<input type ="radio" name = "eOx" value = "3"> 정답
					</td>
				</tr>
				<tr>
					<td>
						<input type = "hidden" name = "exampleIdx" value = "4">
						<input type = "text" name = "exampleTitle">
					</td>
					<td>
						<input type ="radio" name = "eOx" value = "4"> 정답
					</td>
				</tr>
				<tr>
					<td>
						<input type = "hidden" name = "exampleIdx" value = "5">
						<input type = "text" name = "exampleTitle">
					</td>
					<td>
						<input type ="radio" name = "eOx" value = "5"> 정답
					</td>
				</tr>
			</table>
			<button type = "button" id = "addBtn">문제저장</button>
		</form>
	</div>
</body>
</html>