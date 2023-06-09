<%@ page import="com.zerobase.mission.bookmarkGroup.*"%>
<%@ page import="com.zerobase.mission.bookmarkGroup.impl.*"%>
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
	request.setCharacterEncoding("UTF-8");
	
	BookmarkGroupServiceImpl bookmarkGroupService = new BookmarkGroupServiceImpl();
	
	String bookmarkNm = "";
	int bookmarkOrder = 0;
	int bookmarkNo = -1;
	
	boolean availableModifyBookmark = false;
	
	if(null != request.getParameter("bookmarkNo")) {
		
		availableModifyBookmark = true;
		
		bookmarkNo = Integer.parseInt(request.getParameter("bookmarkNo"));
		bookmarkGroupService.delete(bookmarkNo);
	}

%>

	<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script>
	function modifyBookmarkGroup(availableModifyBookmark) {
		if (availableModifyBookmark) {
			window.alert("북마크 그룹 정보를 삭제하였습니다.")
		} else {
			window.alert("북마크 그룹 정보를 삭제하던 중 오류가 발생했습니다.")
		}
		
		location.href="/bookmark/bookmark-group.jsp"
	}
	
	$(document).ready(function() {
		modifyBookmarkGroup(<%= availableModifyBookmark %>);
	});
	</script>

</body>
</html>