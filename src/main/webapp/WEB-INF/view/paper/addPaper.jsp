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
			$('.pOx').each(function() {
				let oxLength=$('.pOx:checked').length;
				$(this).click(function() {
					console.log($('.pOx:checked').length);
					if(oxLength == 1) {
						$('.pOx').prop('checked', false);
					}
				});
			});
		});
	</script>
	
	<style>
		
	</style>
</head>
<body>
	<!-- Menu include -->
	<div>
		<c:import url="/WEB-INF/view/student/inc/studentMenu.jsp"> </c:import>
	</div>
	<form>
		<div>
			<c:forEach var="q" items="${questionList}">
				<div>
					<table>
						<tr>
							<td colspan = "3">${q.questionIdx} 번</td>
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
										<input type="radio" class="ox" value="${status.index+1}">
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
			</c:forEach>
		</div>
	</form>
</body>
</html>