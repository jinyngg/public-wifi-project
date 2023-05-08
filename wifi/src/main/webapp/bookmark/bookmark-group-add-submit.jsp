<%@ page import="com.zerobase.mission.bookmarkGroup.*"%>
<%@ page import="com.zerobase.mission.bookmarkGroup.impl.*"%>
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

BookmarkGroupServiceImpl bookmarkGroupService = new BookmarkGroupServiceImpl();

boolean availableBookmarkOrder = false;
String bookmarkNm = "";
int bookmarkOrder = 0;

if(null != request.getParameter("bookmarkNm") && null != request.getParameter("bookmarkOrder")) {
	bookmarkNm = request.getParameter("bookmarkNm");
	bookmarkOrder = Integer.parseInt(request.getParameter("bookmarkOrder"));

	availableBookmarkOrder = bookmarkGroupService.validateBookmarkOrder(bookmarkOrder);

	// 사용가능한 순서일 때
	if(availableBookmarkOrder) {
		bookmarkGroupService.insertBookmarkGroup(bookmarkNm, bookmarkOrder);
	}
}

%>

<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
function postBookmarkGroup(availableBookmarkOrder) {
	if (availableBookmarkOrder) {
		window.alert("북마크 그룹 정보를 추가하였습니다.")
	} else {
		window.alert("중복된 순서가 존재합니다.")
	}
	
	location.href="/bookmark/bookmark-group.jsp"
}

$(document).ready(function() {
	postBookmarkGroup(<%= availableBookmarkOrder %>);
});
</script>

</body>
</html>