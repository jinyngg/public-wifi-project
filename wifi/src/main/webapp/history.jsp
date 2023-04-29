<%@ page import="com.zerobase.mission.history.impl.*" %>
<%@ page import="com.zerobase.mission.history.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.zerobase.mission.common.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>와이파이 정보 구하기</title>
</head>

<body>
	<h1>와이파이 정보 구하기</h1>

	<!-- location -->
	<div>
		<a href="index.jsp">홈</a> 
		| <a href="history.jsp">위치 히스토리 목록</a> 
		| <a href="">Open API 와이파이 정보 가져오기</a> 
		| <a href="">즐겨 찾기 보기</a> 
		| <a href="">즐겨 찾기 그룹 관리</a>
	</div>

	<table>
		<thead>
			<tr bgcolor="green">
				<th><font color="white">ID</font></th>
				<th><font color="white">X좌표</font></th>
				<th><font color="white">Y좌표</font></th>
				<th><font color="white">조회일자</font></th>
				<th><font color="white">비고</font></th>

			</tr>
		</thead>
		
		<!-- @TODO 데이터가 존재할 경우 메세지 숨김 처리 -->
		<tbody>
			<%
			HistoryServiceImpl historyService = new HistoryServiceImpl();
			List<History> historyList = historyService.getHistoryList();
			
			// 삭제 요청 확인
			if(null != request.getParameter("historyId")){
				int historyId = Integer.parseInt(request.getParameter("historyId"));
				historyService.delete(historyId);
			}
			
			
			if(historyList == null || historyList.size() == 0) {
			%>
			<tr>
				<td colspan="5" align="center"><br>위치 히스토리 목록이 없습니다.</td>
			</tr>
			<%
			} else {
			%>
			
			
			<% for(History history : historyList) { %>
			<tr>
				<td><%= history.getHistoryId() %></td>
				<td><%= history.getLat() %></td>
				<td><%= history.getLnt() %></td>
				<td><%= DateUtil.timestampToString(history.getRegDate()) %></td>
				
				<td><button onclick="deleteHistory('<%= history.getHistoryId() %>')"><%= history.getHistoryId() %> 삭제</button></td>
				
				<%-- 
				java.sql.Date 형식으로 가져올 경우, 시 분 초를 가져오지 못하여 수정
				<td><%= DateUtil.dateToString(history.getRegDate()) %></td> 
				--%>
			</tr>
			
			<%
				}
			}
			%>

		</tbody>
	</table>
	
	<hr />
	
	<!-- ajax 사용시 jquery 필요 -->
	<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script>
	function deleteHistory(toBeDeletedHistoryId) {
        if (confirm("정말로 삭제하시겠습니까?")) {
            $.ajax({
                data: {historyId: toBeDeletedHistoryId},
                success: function () {
                	alert("삭제되었습니다.");
                	location.reload();
                }
            });
        }
    }
	

	</script>
	
</body>
</html>