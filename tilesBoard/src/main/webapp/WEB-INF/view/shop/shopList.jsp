<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table class="table">
		<tr>
			<c:forEach items="${selectList }" var="item" varStatus="status">
				<c:if test="${(status.index + 1) % 5 eq 0 }">
		</tr>
		<tr>
			</c:if>
			<td style="padding: 10px;" align="center"><a
				href="#"><img width="180px;" src="/upload/${item.fileName }"></a><br>
				${item.goodsName }<br>
				${item.goodsPrice }
			</td>
			</c:forEach>
		</tr>
	</table>
</body>
</html>