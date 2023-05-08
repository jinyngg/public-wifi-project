<%@ page import="com.zerobase.mission.bookmarkGroup.*"%>
<%@ page import="com.zerobase.mission.bookmarkGroup.impl.*"%>
<%@ page import="com.zerobase.mission.common.*" %>
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
		BookmarkGroupServiceImpl bookmarkGroupService = new BookmarkGroupServiceImpl();
		List<BookmarkGroup> bookmarkGroupList = new ArrayList<>();
		
		bookmarkGroupList = bookmarkGroupService.getBookmarkGroupList();
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
	
	<button onclick="location.href='bookmark-group-add.jsp'">북마크 그룹 이름 추가</button>

	<table>
		<thead>
			<tr bgcolor="green">
				<th><font color="white">ID</font></th>
        		<th><font color="white">북마크 이름</font></th>
        		<th><font color="white">순서</font></th>
        		<th><font color="white">등록 일자</font></th>
        		<th><font color="white">수정 일자</font></th>
        		<th><font color="white">비고</font></th>
			</tr>
		</thead>
		<tbody>
		
		<%
			if(null == bookmarkGroupList || bookmarkGroupList.size() == 0) {
				
		%>
		
			<tr>
				<td colspan="6" align="center"><br>정보가 존재하지 않습니다.</td>
			</tr>
		<%
			} else {
				for(BookmarkGroup bookMarkGroup : bookmarkGroupList) {
		%>
			
		<tr>
			<td><%=bookMarkGroup.getBookmarkGroupId()%></td>
			<td><%=bookMarkGroup.getBookmarkGroupNm()%></td>
			<td><%=bookMarkGroup.getBookmarkGroupOrder()%></td>
			<td><%=DateUtil.timestampToString(bookMarkGroup.getRegDate())%></td>
			<td><%=bookMarkGroup.getUpdDate() != null ? DateUtil.timestampToString(bookMarkGroup.getUpdDate()) : ""%></td>
			<td>
			<a href="/bookmark/bookmark-group-edit.jsp?id=<%= bookMarkGroup.getBookmarkGroupId()%>">수정</a>
			<a href="/bookmark/bookmark-group-delete.jsp?id=<%= bookMarkGroup.getBookmarkGroupId()%>">삭제</a>
			 </td>

		</tr>
		<%
				}
		}
		%>
		</tbody>
	</table>
</body>
</html>