<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="navigation">
	<ul>
		<li><a href="${pageContext.request.contextPath }/main">문승환</a></li>
		<li><a href="${pageContext.request.contextPath }/board/list">게시판</a></li>
		<li><a href="${pageContext.request.contextPath }/guestbook/list">방명록</a></li>
		<li><a href="${pageContext.request.contextPath }/guestbook/ajax-list">ajax방명록</a></li>
		<li><a href="${pageContext.request.contextPath }/gallery/list">갤러리</a></li>
		
	</ul>
</div>
<!-- /navigation -->