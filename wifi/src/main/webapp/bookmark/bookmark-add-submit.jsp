<%@ page import="com.zerobase.mission.bookmark.*"%>
<%@ page import="com.zerobase.mission.bookmark.impl.*"%>
<%@ page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>와이파이 정보 구하기</title>
</head>
<body>
<%
request.setCharacterEncoding("UTF-8");

BookmarkServiceImpl bookmarkService = new BookmarkServiceImpl();

int bookmarkGroupId = 0;
String bookmarkGroupNm = "";

String wifiMgrNo = "";
String wifiMainNm = "";

boolean availableAddBookmark = false;

System.out.println(request.getParameter("bookmarkGroupId"));

if(null != request.getParameter("bookmarkGroupId") && null != request.getParameter("bookmarkGroupNm") && null != request.getParameter("wifiMgrNo") && null != request.getParameter("wifiMainNm")) {
	
	availableAddBookmark = true;
	
	bookmarkGroupId = Integer.parseInt(request.getParameter("bookmarkGroupId"));
	bookmarkGroupNm = request.getParameter("bookmarkGroupNm");
	wifiMgrNo = request.getParameter("wifiMgrNo");
	wifiMainNm = request.getParameter("wifiMainNm");
	
	System.out.println("wifiMainNm");

	
	bookmarkService.insertBookmark(bookmarkGroupId, bookmarkGroupNm, wifiMgrNo, wifiMainNm);

	/* 	
	Request TEST
	System.out.println("bookmarkGroupId = " + bookmarkGroupId);
	System.out.println("bookmarkGroupNm = " + bookmarkGroupNm);
	System.out.println("wifiMgrNo = " + wifiMgrNo);
	System.out.println("wifiMainNm = " + wifiMainNm); 
	*/
}

%>

<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
function AddBookmarkGroup(availableModifyBookmark) {
	if (availableModifyBookmark) {
		window.alert("북마크를 추가하였습니다..")
	} else {
		window.alert("북마크를 추가하던 중 오류가 발생했습니다.")
	}
	
	location.href="/bookmark/bookmark-list.jsp"
}

$(document).ready(function() {
	AddBookmarkGroup(<%= availableAddBookmark %>);
});
</script>

</body>
</html>