<%@ page import="com.zerobase.mission.bookmark.*"%>
<%@ page import="com.zerobase.mission.bookmark.impl.*"%>
<%@ page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>와이파이 정보 구하기</title>
	<link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>

<%
	BookmarkServiceImpl bookmarkService = new BookmarkServiceImpl();
	int bookmarkId = -1;
	
	boolean availableDeleteBookmark = false;
	
	if(null != request.getParameter("id")) {
		bookmarkId = Integer.parseInt(request.getParameter("id"));
		availableDeleteBookmark = true;
		
		bookmarkService.delete(bookmarkId);
	}
%>

	<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script>
	function deleteBookmark(availableDeleteBookmark) {
		if (availableDeleteBookmark) {
			window.alert("북마크를 삭제하였습니다.")
		} else {
			window.alert("북마크를 삭제하던 중 오류가 발생했습니다.")
		}
		
		location.href="/bookmark/bookmark-list.jsp"
	}
	
	$(document).ready(function() {
		deleteBookmark(<%= availableDeleteBookmark %>);
	});
	</script>

</body>
</html>