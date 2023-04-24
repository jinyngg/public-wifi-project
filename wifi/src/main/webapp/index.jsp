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
		<a href="<%= request.getContextPath() %>">홈</a> 
		| <a href="">위치 히스토리 목록</a> 
		| <a href="">Open API 와이파이 정보 가져오기</a> 
		| <a href="">즐겨 찾기 보기</a> 
		| <a href="">즐겨 찾기 그룹 관리</a>
	</div>

	<br/>
	
	<div>
		<!-- @TODO method = "get" or "post"-->
		<form action="<%= request.getContextPath() %>" method="post">
			LAT: <input type="text" id="lat" value="0.0">
			, LNT: <input type="text" id="lnt" value="0.0">
			<button type="button" onclick="getUserLocation()">내 위치 가져오기</button>
			<button type="button" onclick="">근처 WIFI 정보보기</button>
		</form>
	</div>

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
				<td colspan="20" align="center"><br>위치 정보를 입력한
					후에 조회해 주세요.<br> <br></td>
			</tr>
		</tbody>
	</table>
	
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
				
			}
		}
		
	</script>
</body>
</html>