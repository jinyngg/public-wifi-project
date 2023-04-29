<%@ page import="com.zerobase.mission.common.JDBCUtil" %>
<%@ page import="com.zerobase.mission.history.impl.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
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
 
	<br/>
	
	<div>
		<!-- @TODO method = "get" or "post"-->
		<form action="list.jsp" method="post" id="location">
			LAT: <input type="text" id="lat" name="lat" value="0.0">
			, LNT: <input type="text" id="lnt" name="lnt" value="0.0">
			<button type="button" onclick="getUserLocation()">내 위치 가져오기</button>
			<button onclick="getWifi()">근처 WIFI 정보보기</button>
		</form>
	</div>
	
	<br/>

	<table>
		<thead>
			<tr bgcolor="green">
				<th><font color="white">거리(km)</font></th>
				<th><font color="white">관리번호</font></th>
				<th><font color="white">자치구</font></th>
				<th><font color="white">와이파이명</font></th>
				<th><font color="white">도로명주소</font></th>
				<th><font color="white">상세주소</font></th>
				<th><font color="white">설치위치(층)</font></th>
				<th><font color="white">설치유형</font></th>
				<th><font color="white">설치기관</font></th>
				<th><font color="white">서비스구분</font></th>
				<th><font color="white">망종류</font></th>
				<th><font color="white">설치년도</font></th>
				<th><font color="white">실내외구분</font></th>
				<th><font color="white">WIFI접속환경</font></th>
				<th><font color="white">X좌표</font></th>
				<th><font color="white">Y좌표</font></th>
				<th><font color="white">작업일자</font></th>
			</tr>
		</thead>
		
		<!-- @TODO 데이터가 존재할 경우 메세지 숨김 처리 -->
		<tbody>
			<tr>
				<td colspan="17" align="center"><br>위치 정보를 입력한 후에 조회해 주세요.</td>
			</tr>
			
			
		</tbody>
	</table>
	
	<hr />
	
	<script>
		function getUserLocation() {
			if (navigator.geolocation) {
				// GPS 지원
				navigator.geolocation.getCurrentPosition(
						(position) => {
					// 위도(latitude), 경도(longitude) 값 가져오기
					var latitude = position.coords.latitude;
					var longitude = position.coords.longitude;
					
					document.getElementById("lat").value = latitude;
					document.getElementById("lnt").value = longitude;
				})
				
			} else {
				window.alert("위치를 가져올 수 없습니다.")
			}
		}
		
		function getWifi() {
			var lat = document.getElementById("lat").value;
			var lnt = document.getElementById("lnt").value;
			
			// @TODO 위도, 경도 예외처리 필요(checkFloat())
			if(lat === "" || lnt === "") {
				alert("위도 및 경도 값을 확인해주세요.");
				/* alert("validate() 추가"); */
			} else {
				$('location').submit();
			}
		}
	</script>
	
</body>
</html>