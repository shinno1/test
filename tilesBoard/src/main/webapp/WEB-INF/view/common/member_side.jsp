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
  <li role="presentation" <c:if test="${memberVO.subMenu eq 'selectBuyHistory' }">class="active"</c:if>><a href="buyHistory.me?mainMenu=member&subMenu=selectBuyHistory">구매내역</a></li>
  <li role="presentation" <c:if test="${memberVO.subMenu eq 'selectMember' }">class="active"</c:if>><a href="selectMember.me?mainMenu=member&subMenu=selectMember">내 정보조회</a></li>
  <li role="presentation" <c:if test="${memberVO.subMenu eq 'updateMember' }">class="active"</c:if>><a href="updateMember.me?mainMenu=member&subMenu=updateMember">내 정보변경</a></li>
  <li role="presentation" <c:if test="${memberVO.subMenu eq 'deleteMember' }">class="active"</c:if>><a href="deleteMember.me?mainMenu=member&subMenu=deleteMember">회원탈퇴</a></li>
</ul>
</body>
</html>