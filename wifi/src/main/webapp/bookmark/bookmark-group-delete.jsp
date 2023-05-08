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

<%

request.setCharacterEncoding("UTF-8");

BookmarkGroupServiceImpl bookmarkGroupService = new BookmarkGroupServiceImpl();
BookmarkGroup bookmarkGroup = new BookmarkGroup();

int bookmarkNo = -1;

if(null != request.getParameter("id")) {
	
	bookmarkNo = Integer.parseInt(request.getParameter("id"));
	
	bookmarkGroup = new BookmarkGroup();
	bookmarkGroup = bookmarkGroupService.getBookmarkGroup(bookmarkNo);
}

%>

<body>
	<h1>와이파이 정보 구하기</h1>

	<!-- location -->
	<div>
		<a href="/index.jsp">홈</a> 
		| <a href="/history.jsp">위치 히스토리 목록</a> 
		| <a href="/load-wifi.jsp">Open API 와이파이 정보 가져오기</a> 
		| <a href="/bookmark/bookmark-list.jsp">즐겨 찾기 보기</a> 
		| <a href="/bookmark/bookmark-group.jsp">즐겨 찾기 그룹 관리</a>
	</div>
 
	<br/>
	
	<form action="/bookmark/bookmark-group-delete-submit.jsp" method="post" id="bookmarkGroupDelete">
		
		<!-- bookmarkNo 데이터 필요 -->
		<input type="hidden" name="bookmarkNo" value="<%= bookmarkNo %>">
		
		<table>
			<tr>
				<th>북마크 이름</th>
				<td><input type="text" id="bookmarkNm" name="bookmarkNm" value="<%= bookmarkGroup.getBookmarkGroupNm() %>"></td>
			</tr>
			<tr>
				<th>순서</th>
				<td><input type="text" id="bookmarkOrder" name="bookmarkOrder" value="<%= bookmarkGroup.getBookmarkGroupOrder() %>"></td>
			</tr>
		</table>
		<a href="/bookmark/bookmark-group.jsp">돌아가기</a>
		| <button onclick="bookmarkGroupDelete()">삭제</button>
	</form>

	<hr />
	<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script>
		function bookmarkGroupDelete() {
			var bookmarkNm = document.getElementById("bookmarkNm").value;
			var bookmarkOrder = document.getElementById("bookmarkOrder").value;
			var bookmarkNo = document.getElementById("bookmarkNo").value;
			
			if(bookmarkNm === "" || bookmarkOrder === "") {
				alert("입력 값을 확인해주세요.");
			} 
			
			if (bookmarkNm !== "" && bookmarkNm !== ""){
				$('bookmarkGroupDelete').submit();
			}
		}
	</script>
	

</body>
</html>