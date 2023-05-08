<%@ page import="com.zerobase.mission.tbPublicWifiInfo.impl.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>와이파이 정보 구하기</title>
</head>
<body>

	<%
		TbPublicWifiInfoServiceImpl tbPublicWifiInfoService = new TbPublicWifiInfoServiceImpl();
		int listTotalCount = tbPublicWifiInfoService.getListTotalCount();
		tbPublicWifiInfoService.saveAllWifiList();
	%>

	<div style="text-align: center">
		<h1><%= listTotalCount %>개의 와이파이 데이터를 가져왔습니다.</h1>
	</div>
	
	<div style="text-align: center"><a href="index.jsp">홈으로 가기</a></div>
</body>

</body>
</html>