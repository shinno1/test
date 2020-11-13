<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	body{
	border:1px solid black;
		width: 1200px;
		height: 650px;
		padding: 0px;
		margin: 0px;
	}
	.menuDiv{
		background:gray;
		width: 100%;
		height: 20%;
	}
	
	.sideDiv{
		background: lime;
		display: inline-block;
		width: 15%;
		height: 80%;
	}
	
	.mainDiv{
		background: yellow;
		display: inline-block;
		width: 84%;
		height: 80%;
	}
</style>
</head>
<body>
<div class="menuDiv"><tiles:insertAttribute name="header" /></div>
<div class="sideDiv"><tiles:insertAttribute name="side" /></div>
<div class="mainDiv"><tiles:insertAttribute name="body" /></div>
</body>
</html>










