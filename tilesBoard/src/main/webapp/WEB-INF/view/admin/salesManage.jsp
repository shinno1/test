<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.salesMonth:hover{
	cursor: pointer;
}
#salesPerDayDiv{
	overflow-y: auto;
	overflow-x: hidden;
	height: 420px; 
}

</style>
</head>
<body>
<div class="col-md-12 text-center">
	<select id="year">
		<option value="2019">2019</option>
		<option value="2020" selected>2020</option>
		<option value="2021">2021</option>
	</select>
</div>
<div>
	<div class="col-md-6 text-center">
		<table class="table table-striped">
			<tr>
				<td>월</td>
				<td>매출액</td>
			</tr>
			<tbody id="selectTbody">
				<c:forEach items="${resultList }" var="result">
				<tr>
					<td class="salesMonth">${result.buyMonth }</td>
					<td>${result.salesPerMonth }</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<!-- <div class="col-md-6 text-center" id="salesPerDayDiv"> -->
		<div class="col-md-6 text-center">
			<div id="salesPerDayDiv">
		
			</div>
			<div>
			
			</div>
		
		</div>
	<!-- </div> -->
</div>
<script src="resources/js/salesManage.js?ver=14"></script>
</body>
</html>