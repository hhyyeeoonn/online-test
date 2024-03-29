<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page trimDirectiveWhitespaces="true" %> <!-- 공백제거코드 -->
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
			
			// 문제 출력할 때 줄바꾸는 코드
			$('.qIndex').each(function() {
				if($(this).val() == 10) {
					console.log($(this).val()+"줄바꿈");
					$(this).after('</td><td>');
				}
			});
			
			// 출제 상태 변경
			if($('#stateMsg').val() != null) {
				if($(this).val() == Y) {
					alert('촐제가 완료되었습니다');
				} else {
					alrert('출제상태 변경에 실패하였습니다 다시 시도하세요');
				}
			}
			
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
	<input type="hidden" id="stateMsg" value="${stateMsg}">
		
	<!-- 시험등록상태 변경 : 작성완료로 선택하면 문제 수정삭제는 불가능하게되며 시험삭제만 가능하다 또한 출제 문제수가 20일 때만 출제완료 상태로 변경할 수 있다 -->
	
	<div>* 한 시험당 출제 문제수는 20개입니다.</div>
	<c:choose>
		<c:when test="${countQuestion < 20}">
			<div>출제 문제수 : ${countQuestion}/20 &nbsp;
				<a href="${pageContext.request.contextPath}/teacher/question/addQuestion?testNo=${testNo}">문제등록</a>
			</div>
		</c:when>
		<c:when test="${countQuestion == 20}">
			<div>출제 문제수 : ${countQuestion}/20</div>
			<div>
				<a href="${pageContext.request.contextPath}/teacher/test/modifyTestState?testNo=${testNo}">출제완료</a>
			</div>
		</c:when>
		<c:when test="${countQuestion == 20 && stateMsg eq Y}">
			<div>출제 문제수 : ${countQuestion}/20</div>
			<div>* 출제가 완료되어 문제 수정이 불가합니다</div>
		</c:when>
	</c:choose>
	<br>
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
						<c:if test="${qstatus.index == 9}"> <!-- 11번 문제부터 열바꿈 -->
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