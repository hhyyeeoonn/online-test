<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
	<script>
		//$(this).css('color', 'red');
		//$(#).append("#뒤에 요소 추가");
		$(document).ready(function() {
			$('.questionNo').each(function() {
				let questionNum=$(this).val();
				console.log(questionNum);
				$('.ox'+questionNum).each(function() {
					$('.ox'+questionNum).click(function() {
						$('#answer'+questionNum).val($(this).val()); // radio가 체크되면 controller에 넘길 input에 값 넣기
						console.log($('#answer'+questionNum).val());
					});
				});
			});
			$("#addBtn").click(function() {
				$("#addForm").submit();
			});
		});
	</script>
	
</head>
<body>
	<!-- Menu include -->
	<div>
		<c:import url="/WEB-INF/view/student/inc/studentMenu.jsp"> </c:import>
	</div>
	
	<form method="post" action="${pageContext.request.contextPath}/student/paper/addPaper" id="addForm">
		<input type="hidden" name="studentNo" value="${loginStudent.studentNo}">
		<table>
			<tr>
				<td>			
					<c:forEach var="q" items="${questionList}" varStatus="qstatus">
						<div>
							<div>
								<table>
									<tr>
										<td colspan = "3">
											${q.questionIdx} 번
											<input type="hidden" class="questionNo" name="questionNo" value="${q.questionNo}">
											<input type="hidden" name="answer" id="answer${q.questionNo}" value="0">
										</td>
									</tr>
									<tr>
										<td colspan = "3">
											${q.questionTitle}
										</td>
									</tr>
									<c:forEach var = "e" items = "${exampleList}" varStatus="status">
										<c:if test="${e.questionNo eq q.questionNo}">
											<tr>
												<td></td>
												<td>
													<input type="radio" class="ox${e.questionNo}" name="ox${e.questionNo}" value="${e.exampleIdx}"> <!-- radio에 name이 없으면 다중선택이 되어버린다 -->
													<span>${e.exampleIdx} 번)</span>
												</td>
												<td>
													<span>${e.exampleTitle}</span>
												</td>
											</tr>
										</c:if>
									</c:forEach>
								</table>
							</div>
							<br>
						</div>
													
						<c:if test="${qstatus.index == 9}"> <!-- 11번 문제부터 열바꿈 -->
							</td>
							<td>
						</c:if>
		
					</c:forEach>
				</td>
			</tr>
		</table>
		<button type="button" id="addBtn">제출</button>
	</form>
</body>
</html>