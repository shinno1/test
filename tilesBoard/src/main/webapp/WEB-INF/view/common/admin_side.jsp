<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<!-- 부트스트랩 홈페이지의 알약형 네비게이션에서 복붙: 아래로 내려오게 하기 위해 nav-stacked도 복붙 -->
<body>
<ul class="nav nav-pills nav-stacked">
  <li role="presentation" <c:if test="${memberVO.subMenu eq 'manageBuy' }">class="active"</c:if>><a href="manageBuy.ad?mainMenu=admin&subMenu=manageBuy">구매관리</a></li>
  <li role="presentation" <c:if test="${memberVO.subMenu eq 'insertItem' }">class="active"</c:if>><a href="insertItem.ad?mainMenu=admin&subMenu=insertItem">상품등록</a></li>
  <li role="presentation" <c:if test="${memberVO.subMenu eq 'manageItem' }">class="active"</c:if>><a href="manageItem.ad?mainMenu=admin&subMenu=manageItem">상품관리</a></li>
  <li role="presentation" <c:if test="${memberVO.subMenu eq 'manageMember' }">class="active"</c:if>><a href="manageMember.ad?mainMenu=admin&subMenu=manageMember">회원관리</a></li>
  <li role="presentation" <c:if test="${memberVO.subMenu eq 'salesManage' }">class="active"</c:if>><a href="salesManage.ad?mainMenu=admin&subMenu=salesManage">매출관리</a></li>
</ul>
</body>
</html>