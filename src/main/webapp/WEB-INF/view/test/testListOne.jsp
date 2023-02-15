<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
	<script>
		//$(this).css('color', 'red');
		//$(#).append("#뒤에 요소 추가");
		$(document).ready(function() {
			$('.ox').each(function() {
				if($(this).val() == '정답') {
					//$(this).before('<span><i class="fa fa-check" style="color:red"></i></span>');
					$(this).parent().siblings().find('.oxMsg').html('<i class="fa fa-check" style="color:red"></i>');
					$(this).next().css('color', 'red');
					$(this).parent().siblings().children().css('color','red');
					//console.log($(this).val());
				} else if($(this).val() == '오답') {
					//console.log($(this).val());
				}
			});	
			
			$('.qIndex').each(function() {
				if($(this).val() == 10) {
					console.log($(this).val()+"줄바꿈");
					$(this).after('</td><td>');
				}
				
			});
		});
	</script>
	
	<style>
		
	</style>
</head>
<body>
	<!-- Menu include -->
	<div>
		<c:import url="/WEB-INF/view/test/inc/testMenu.jsp"> </c:import>
	</div>

	<c:if test="${msg != null}">
		<div>${msg}</div>	
	</c:if>
	
	<div>
		<a href="${pageContext.request.contextPath}/teacher/question/addQuestion?testNo=${testNo}">문제등록</a>
	</div>
	
	<div>
		<table border="1">
			<tr>
				<td>		
					<c:forEach var="q" items="${questionList}" varStatus="qstatus">
						<div>
							<div>
								<table>
									<tr>
										<td colspan = "2">${q.questionIdx} 번</td>
										<td>
											<span>
												<a href="${pageContext.request.contextPath}/teacher/question/modifyQuestion?testNo=${testNo}&questionNo=${q.questionNo}">
													<button type="button">수정</button>
												</a>
											</span>
											<span>
												<a href="${pageContext.request.contextPath}/teacher/question/removeQuestion?testNo=${testNo}&questionNo=${q.questionNo}">
													<button type="button">삭제</button>
												</a>
											</span>
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
												<td><span class="oxMsg"></span></td>
												<td>
													<input type="hidden" class="ox" value="${e.exampleOx}">
													<span>${e.exampleIdx}번</span>
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
						<c:if test="${qstatus.index == 9}"> <!-- 11번 문제 줄바꿈 -->
							</td>
							<td>
						</c:if>
					</c:forEach>
			
				</td>
			</tr>
		</table>
	</div>
</body>
</html>