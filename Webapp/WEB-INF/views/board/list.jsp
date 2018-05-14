<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/mysite.css"
	rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/board.css"
	rel="stylesheet" type="text/css">

</head>
<body>
	<div id="container">

		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>

		<c:import url="/WEB-INF/views/includes/navigation.jsp"></c:import>

		<div id="content">
			<div id="board">
				<form id="search_form"
					action="${pageContext.request.contextPath}/board/search"
					method="post">
					<input type="text" id="kwd" name="kwd" value=""> <input
						type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>
					<c:forEach items="${requestScope.bMap.boardList}" var="vo">
						<tr>
							<td>${vo.no}</td>
							<td><a
								href="${pageContext.request.contextPath}/board/view?no=${vo.no}">${vo.title}</a></td>
							<td>${vo.name}</td>
							<td>${vo.hit}</td>
							<td>${vo.reg_date}</td>
							
							 <c:choose> 
								<c:when test="${authUser.no eq vo.user_no}">
									<td><a href="${pageContext.request.contextPath}/board/delete?no=${vo.no}" class="del">삭제</a></td>
								 </c:when> 
								 <c:otherwise>
									<td></td>
								</c:otherwise>
							</c:choose> 
						</tr>
					</c:forEach>
				</table>
				<div class="pager">
               <ul>
                  <c:if test="${requestScope.bMap.prev}">
                     <li><a href="${pageContext.request.contextPath}/board/list?crtPage=${requestScope.bMap.startPgaeBtnNo-1}">◀</a></li>
                  </c:if>
                  
                  <c:forEach var="i" begin="${requestScope.bMap.startPgaeBtnNo}" end="${requestScope.bMap.endPageBtnNo}">
                     <c:if test="${i <= requestScope.bMap.last}">
                        <li <c:if test="${i eq requestScope.bMap.crtPage}">class="selected"</c:if>>
                           <a href="${pageContext.request.contextPath}/board/list?crtPage=${i}">${i}</a>
                        </li>
                     </c:if>
                  </c:forEach>
                  
                  <c:if test="${requestScope.bMap.next}">
                     <li><a href="${pageContext.request.contextPath}/board/list?crtPage=${requestScope.bMap.endPageBtnNo+1}">▶</a></li>
                  </c:if>
                  
               </ul>
            </div>      
               
            <c:if test="${!empty sessionScope.authUser.no}">
               <div class="bottom">
                  <a href="${pageContext.request.contextPath}/board/writeform" id="new-book">글쓰기</a>
               </div>   
            </c:if>         
         </div>
		</div>

		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>

	</div>
</body>
</html>