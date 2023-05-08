<%@page import="com.zerobase.mission.wifi.impl.*"%>
<%@page import="com.zerobase.mission.wifi.*"%>
<%@ page import="com.zerobase.mission.history.impl.*" %>
<%@ page import="com.zerobase.mission.history.*" %>
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
		HistoryServiceImpl historyService = new HistoryServiceImpl();
		History history = historyService.getlatestHistory();
	
		String mgrNo = request.getParameter("mgrNo");
		double lat = history.getLat();
		double lnt = history.getLnt();
		
		WifiServiceImpl wifiService = new WifiServiceImpl();
		Wifi wifi = new Wifi();
		wifi = wifiService.getWifiByMgrNo(lat, lnt, mgrNo);
		
	%>
	<h1>와이파이 정보 구하기</h1>

	<!-- location -->
	<div>
		<a href="index.jsp">홈</a> 
		| <a href="history.jsp">위치 히스토리 목록</a> 
		| <a href="load-wifi.jsp">Open API 와이파이 정보 가져오기</a> 
		| <a href="">즐겨 찾기 보기</a> 
		| <a href="">즐겨 찾기 그룹 관리</a>
	</div>
 
	<br/>
	
	<%
		BookmarkGroupServiceImpl bookmarkGroupService = new BookmarkGroupServiceImpl();
		List<BookmarkGroup> bookmarkGroupList = new ArrayList<>();
		
		bookmarkGroupList = bookmarkGroupService.getBookmarkGroupList();
	%>
	<form action="/bookmark/bookmark-add-submit.jsp" method="post" id="bookmarkAdd">
		<input type="hidden" id="bookmarkGroupId" name="bookmarkGroupId" value="">
		<input type="hidden" id="bookmarkGroupNm" name="bookmarkGroupNm" value="">
		<input type="hidden" id="wifiMgrNo" name="wifiMgrNo" value="<%= wifi.getX_SWIFI_MGR_NO() %>">
		<input type="hidden" id="wifiMainNm" name="wifiMainNm" value="<%= wifi.getX_SWIFI_MAIN_NM() %>">
		<select id="selectBookmarkGroup">
		<option name="selectBookmarkGroup" value="-1"> 북마크 그룹 이름 선택</option>
	<%
		if(bookmarkGroupList.size() != 0) {
			for(BookmarkGroup bookmarkGroup : bookmarkGroupList) {
	%>
		<option value="<%= bookmarkGroup.getBookmarkGroupId() %>" name="selectBookmarkGroup"><%= bookmarkGroup.getBookmarkGroupNm() %></option>
	<%
			}
		}
	%>
		</select>
		<button onclick="addBookmark()">북마크 추가하기</button>
	</form>
	
	<table>
		<tr bgcolor="green">
            <th><font color="white">거리(Km)</font></th>
            <td bgcolor="white"><%= wifi.getDistance() %></td>
        </tr>
        <tr bgcolor="green">
            <th><font color="white">관리번호</font></th>
            <td bgcolor="white"><%= wifi.getX_SWIFI_MGR_NO() %></td>
        </tr>
        <tr bgcolor="green">
            <th><font color="white">자치구</font></th>
            <td bgcolor="white"><%= wifi.getX_SWIFI_WRDOFC() %></td>
        </tr>
        <tr bgcolor="green">
            <th><font color="white">와이파이명</font></th>
            <td bgcolor="white"><%= wifi.getX_SWIFI_MAIN_NM() %></td>
        </tr>
        <tr bgcolor="green">
            <th><font color="white">도로명주소</font></th>
            <td bgcolor="white"><%= wifi.getX_SWIFI_ADRES1() %></td>
        </tr>
        <tr bgcolor="green">
            <th><font color="white">상세주소</font></th>
            <td bgcolor="white"><%= wifi.getX_SWIFI_ADRES2() %></td>
        </tr>
        <tr bgcolor="green">
            <th><font color="white">설치위치(층)</font></th>
            <td bgcolor="white"><%= wifi.getX_SWIFI_INSTL_FLOOR() %></td>
        </tr>
        <tr bgcolor="green">
            <th><font color="white">설치유형</font></th>
            <td bgcolor="white"><%= wifi.getX_SWIFI_INSTL_TY() %></td>
        </tr>
        <tr bgcolor="green">
            <th><font color="white">설치기관</font></th>
            <td bgcolor="white"><%= wifi.getX_SWIFI_INSTL_MBY() %></td>
        </tr>
        <tr bgcolor="green">
            <th><font color="white">서비스구분</font></th>
            <td bgcolor="white"><%= wifi.getX_SWIFI_SVC_SE() %></td>
        </tr>
        <tr bgcolor="green">
            <th><font color="white">망종류</font></th>
            <td bgcolor="white"><%= wifi.getX_SWIFI_CMCWR() %></td>
        </tr>
        <tr bgcolor="green">
            <th><font color="white">설치년도</font></th>
            <td bgcolor="white"><%= wifi.getX_SWIFI_CNSTC_YEAR() %></td>
        </tr>
        <tr bgcolor="green">
            <th><font color="white">실내외구분</font></th>
            <td bgcolor="white" bgcolor="white"><%= wifi.getX_SWIFI_INOUT_DOOR() %></td>
        </tr>
        <tr bgcolor="green">
            <th><font color="white">WIFI접속환경</font></th>
            <td bgcolor="white"><%= wifi.getX_SWIFI_REMARS3() %></td>
        </tr>
        <tr bgcolor="green">
            <th><font color="white">X좌표</font></th>
            <td bgcolor="white"><%= wifi.getLAT() %></td>
        </tr>
        <tr bgcolor="green">
            <th><font color="white">Y좌표</font></th>
            <td bgcolor="white"><%= wifi.getLNT() %></td>
        </tr>
        <tr bgcolor="green">
            <th><font color="white">작업일자</font></th>
            <td bgcolor="white"><%= wifi.getWORK_DTTM() %></td>
        </tr>
    </table>
    
    <script  src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script>
    	function addBookmark() {
    		var bookmarkGroup  = document.getElementById("selectBookmarkGroup");
    		var bookmarkGroupId = bookmarkGroup.options[bookmarkGroup.selectedIndex].value;
    		var bookmarkGroupNm = bookmarkGroup.options[bookmarkGroup.selectedIndex].text;

    		document.getElementById("bookmarkGroupId").value = bookmarkGroupId;
    		document.getElementById("bookmarkGroupNm").value = bookmarkGroupNm;
    		
			if(bookmarkGroupId == -1) {
				alert("북마크 그룹을 선택해주세요.");
			} else {
				$('bookmarkAdd').submit();
			}
			
    	}
    	
    </script>
	
</body>
</html>