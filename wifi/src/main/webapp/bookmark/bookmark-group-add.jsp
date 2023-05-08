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
	
	<form action="/bookmark/bookmark-group-add-submit.jsp" method="post" id="bookmarkGroupAdd">
		<table>
			<tr>
				<th>북마크 이름</th>
				<td><input type="text" id="bookmarkNm" name="bookmarkNm" value="북마크 이름을 입력해주세요."></td>
			</tr>
			<tr>
				<th>순서</th>
				<td><input type="text" id="bookmarkOrder" name="bookmarkOrder" value="0"></td>
			</tr>
		</table>
		<button onclick="bookmarkGroupAdd()">추가</button>
	</form>

	<hr />
	<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
	
	<script>
		function bookmarkGroupAdd() {
			var bookmarkNm = document.getElementById("bookmarkNm").value;
			var bookmarkOrder = document.getElementById("bookmarkOrder").value;
			
			if(bookmarkNm === "" || bookmarkOrder === "") {
				alert("입력 값을 확인해주세요.");
			} 
			
			if (bookmarkNm !== "" && bookmarkNm !== ""){
				$('bookmarkGroupAdd').submit();
			}
		}
	</script>
</body>
</html>