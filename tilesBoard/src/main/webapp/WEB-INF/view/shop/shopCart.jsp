<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
#totalPrice{
	font-size: 25px;
}
input[type="number"]{
	width:40px;
	text-align: center;
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
                    <li><a href="#">쇼핑하기</a></li>
                    <li class="active">구매정보</li>
                  </ol>
               </div>
            </div>
            <div class="row">
               <div class="col-md-12">
                  <h2 style="margin-top: 5px;">CART INFOMATION</h2>
               </div>
            </div>
         </div><!-- 제목 패널 끝 -->
      </div>
      <div class="panel panel-default"><!-- 내용 패널 시작 -->
         <div class="panel-body" style="padding-left: 20px; padding-right: 20px;">
            <div class="panel panel-info" style="border-color: #ffdddd;">
               <div class="panel-heading" style="background: #ffdddd; border-color: #ffdddd;" >
                  <h3 class="panel-title" style="color: gray;">카트리스트 정보</h3>
               </div>
               <div class="panel-body">
                  <div class="col-md-12 text-center">
                     <table class="table table-striped">
                        <colgroup>
                           <col width="3%">
                           <col width="10%">
                           <col width="*">
                           <col width="15%">
                           <col width="15%">
                           <col width="10%">
                           <col width="10%">
                           <col width="10%">
                        </colgroup>
                        <thead>
                           <tr>
                              <th><input type="checkbox" checked id="checkAll"></th>
                              <th>번호</th>
                              <th>상품이미지</th>
                              <th>상품명</th>
                              <th>구매자(이름)</th>
                              <th>구매일</th>
                              <th>가격</th>
                              <th>수량</th>
                              <th>총가격</th>
                           </tr>
                        </thead>
                        <tbody>
                           <c:set var="sum" value="0"/>
                           <c:forEach items="${cartList }" var="cartList" varStatus="status">
                           <tr>
                              <td><input type="checkbox" class="check" value="${cartList.cartId }" checked></td>
                              <td>${status.count }</td>
                              <td><img src="/upload/${cartList.fileName }" width="120px;"></td>
                              <td>${cartList.goodsName }</td>
                              <td>${cartList.memberId }(${cartList.memberName })</td>
                              <td>${cartList.createDate }</td>
                              <td>${cartList.goodsPrice }</td>
                              <td><input type="number" class="goodsCnt" value="${cartList.goodsCnt }" data-cartId="${cartList.cartId }" min="1"></td>
                              <td>${cartList.totalPrice }</td>
                           </tr>
                           <c:set var="sum" value="${sum + cartList.totalPrice}"/>
                           </c:forEach>
                        </tbody>
                     </table>
                  </div>
               </div>
            </div>
            <div class="panel panel-info" style="border-color: #ffdddd;">
               <div class="panel-heading" style="background: #ffdddd; border-color: #ffdddd;">
                  <h3 class="panel-title" style="color: gray;">주문 금액</h3>
               </div>
               <div class="panel-body">
                  <div class="form-horizontal text-right">
                     <h4><span style="font-weight: bold; color: red;" id="totalPrice"><c:out value="${sum }"></c:out>원</span></h4>
                  </div>
               </div>
            </div>
            <div class="row">
               <div class="col-md-12 text-center">
                  <input type="button" id="deleteCartBtn" class="btn btn-default" value="장바구니 비우기">
                  <input type="button" class="btn btn-success" value="구매하기" id="cartBuyBtn">
               </div>
            </div>
         </div>
      </div><!-- 내용 패널 끝 -->      
   </div>
</div><!-- 회색 패널 끝 -->

<script src="resources/js/shopCart.js?ver=17"></script>
</body>
</html>