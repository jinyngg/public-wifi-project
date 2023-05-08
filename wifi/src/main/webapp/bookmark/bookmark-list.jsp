<%@ page import="com.zerobase.mission.bookmark.*"%>
<%@ page import="com.zerobase.mission.bookmark.impl.*"%>
<%@ page import="com.zerobase.mission.common.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.zerobase.mission.common.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>와이파이 정보 구하기</title>
</head>

<body>

	<%
		BookmarkServiceImpl bookmarkService = new BookmarkServiceImpl();
		List<Bookmark> bookmarkList = new ArrayList<>();
		
		bookmarkList = bookmarkService.getBookmarkList();
	%>

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
	
		<table>
		<thead>
			<tr bgcolor="green">
				<th><font color="white">ID</font></th>
				<th><font color="white">북마크 이름</font></th>
				<th><font color="white">와이파이명</font></th>
				<th><font color="white">등록일자</font></th>
				<th><font color="white">비고</font></th>
			</tr>
		</thead>
		<tbody>
		
		<%
			if(bookmarkList.size() != 0) {
				for(Bookmark bookmark : bookmarkList) {
				
		%>
		
		<tr>

			<td><%=bookmark.getBookmarkId()%></td>
			<td><%=bookmark.getBookmarkGroupNm()%></td>
			<td><%=bookmark.getWifiMainNm()%></td>
			<td><%=DateUtil.timestampToString(bookmark.getRegDate())%></td>
			<td>
				<a href="/bookmark/bookmark-delete.jsp?id=<%=bookmark.getBookmarkId()%>">삭제</a>
			</td>
		</tr>
		
		<%
				}
			} else {
		%>
		<tr>
			<td colspan="5" align="center"><br>위치 정보를 입력한 후에 조회해 주세요.</td>
		</tr>
		<%
			}
		 %>

			
		</tbody>
	</table>
	
	<hr />
<body>

</body>
</html>