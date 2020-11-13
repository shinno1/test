<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
th, td{
   text-align: center;
}
.table > tbody > tr > td {
   vertical-align: middle;
}
.table > thead > tr > th {
   font-weight: bold;
}
</style>
</head>
<body>
<div class="panel panel-default"> <!-- 회색 패널 시작 -->
   <div class="panel-heading">
      <div class="panel panel-default">
         <div class="panel-body" style="padding-left: 20px; padding-right: 20px;"><!-- 제목 패널 시작 -->
            <div class="row">
               <div class="col-md-12 text-right">
                  <ol class="breadcrumb" style="background-color: white; margin-bottom: 0px;">
                    <li><a href="#">내정보관리</a></li>
                    <li class="active">구매 목록 조회</li>
                  </ol>
               </div>
            </div>
            <div class="row">
               <div class="col-md-12">
                  <h2 style="margin-top: 5px;">ORDER INFOMATION</h2>
               </div>
            </div>
         </div><!-- 제목 패널 끝 -->
      </div>
      <div class="panel panel-default"><!-- 내용 패널 시작 -->
         <div class="panel-body" style="padding-left: 20px; padding-right: 20px;">
         	<c:forEach items="${buyList }" var="buyListPerDate" varStatus="mapStatus">
            <div class="panel panel-info" style="border-color: #ffdddd;">
               <div class="panel-heading" style="background: #ffdddd; border-color: #ffdddd;">
                  <h3 class="panel-title">${buyListPerDate.key }</h3>
               </div>
               <div class="panel-body">
                  <div class="col-md-12 text-center">
                     <table class="table table-striped">
                        <colgroup>
                           <col width="5%">
                           <col width="*">
                           <col width="30%">
                           <col width="20%">
                           <col width="5%">
                           <col width="25%">
                        </colgroup>
                        <thead>
                           <tr>
                              <th>번호</th>
                              <th>이미지</th>
                              <th>도서명</th>
                              <th>가 격</th>
                              <th>수 량</th>
                              <th>총가격</th>
                           </tr>
                        </thead>
                        <tbody>
                        <c:set var="sum" value="0"/>
                        <c:forEach items="${buyListPerDate.value }" var="buyInfo" varStatus="status">
                        	<tr> <!-- count는 1,2,3,4 ---, index는 0,1,2,3,4 !! -->
                        		<td>${status.count }</td>
                        		<td><img src="/upload/${buyInfo.fileName }" style="height: 150px;"> </td>
                        		<td>${buyInfo.goodsName }</td>
                        		<td>${buyInfo.goodsPrice }</td>
                        		<td>${buyInfo.orderGoodsCnt }</td>
                        		<td>${buyInfo.orderPrice }</td>
                        	</tr>
                        	<c:set var="sum" value="${sum + buyInfo.orderPrice }" />
                        </c:forEach>
                        </tbody>
                     </table>
                  </div>
                  <div class="col-md-12 text-right">
                  		<span style="font-weight: bold; font-size: 20px; color: red;"><fmt:formatNumber pattern="###,###,###" value="${orderPricePerDateList[mapStatus.index] }"></fmt:formatNumber>원</span>
                  </div>
               </div>
            </div>
            </c:forEach>
         </div>
      </div><!-- 내용 패널 끝 -->      
   </div>
</div><!-- 회색 패널 끝 -->
<!-- <script src="resources/js/buyPage.js?ver=15"></script> -->
</body>
</html>