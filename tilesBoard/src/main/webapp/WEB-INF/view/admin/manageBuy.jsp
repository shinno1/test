<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구매관리 페이지</title>
<style type="text/css">
	span{
		vertical-align: middle;
		text-align: center;
	}
	input[type="date"]{
		width: 100%;
		text-align: center;
	}
	#searchBtn{
		height: 102px;
	}
	.menu{
		border-top: 1px solid black;
		border-bottom: 1px solid black;
	}
</style>
</head>
<body>
<h1>구매관리 페이지</h1>
<div style="height: 10px;"></div>
<div class="container-fluid">
	<form action="manageBuy.ad" method="post">
	<div class="row menu">
		<div class="col-md-12">
			<div class="row">
				<div class="col-md-10">
					<div class="row">
						<div class="col-md-3">
							<select name="searchKeyword" class="form-control">
								<option value="MEMBER_ID" <c:if test="${searchVO.searchKeyword eq 'MEMBER_ID' }">selected</c:if>>구매자ID</option>
								<option value="GOODS_NAME" <c:if test="${searchVO.searchKeyword eq 'GOODS_NAME' }">selected</c:if>>상품명</option>
								<option value="ORDER_ID" <c:if test="${searchVO.searchKeyword eq 'ORDER_ID' }">selected</c:if>>주문ID</option>
							</select>
						</div>
						<div class="col-md-9">
							<input type="text" name="searchValue" value="${searchVO.searchValue }" class="form-control" placeholder="검색어를 입력하세요.">
						</div>
					</div>
					<div style="height: 10px;"></div>
					<div class="row">
						<div class="col-md-3">
						FROM
							<input type="date" class="form-control" name="fromDate" value="${fromDate }">
						</div>
						<div class="col-md-3">
						TO
							<input type="date" class="form-control" name="toDate" value="${toDate }">
						</div>
						<div class="col-md-6">
							<span class="badge badge-default">검색조건</span>
							<span><label class="radio-inline">
							<input type="radio" name="isConfirm" value="N" <c:if test="${searchVO.isConfirm eq 'N' or empty searchVO.isConfirm  }">checked</c:if>> 전체
							</label></span>
							<span><label class="radio-inline">
							<input type="radio" name="isConfirm" value="Y" <c:if test="${searchVO.isConfirm eq 'Y' }">checked</c:if>> 구매확인
							</label></span>
						</div>
					</div>
				</div>
				<div class="col-md-2">
					<button class="btn btn-success btn-block" type="submit" id="searchBtn">
						검 색
					</button>
				</div>
			</div>
		</div>
	</div>
	</form>
	<div style="height: 20px;"></div>
	<div class="row">
		<div class="col-md-12">
			<table class="table" style="text-align: center">
				<colgroup>
					<col width="*">
					<col width="21%">
					<col width="10%">
					<col width="21%">
					<col width="21%">
					<col width="10%">
					<col width="8%">
				</colgroup>
				<tr>
					<td>번호</td>				
					<td>주문ID</td>				
					<td>구매자</td>				
					<td>구매일</td>				
					<td>상품명</td>				
					<td>주문금액</td>				
					<td>주문확인</td>				
				</tr>
				<c:forEach items="${buyList }" var="buy">
				<tr>
					<td>${buy.orderNum }</td>
					<td>${buy.orderId }</td>
					<td>${buy.memberId }</td>
					<td>${buy.buyDate }</td>
					<td>${buy.goodsName }</td>
					<td>${buy.orderPrice }</td>
					<td>
					<c:choose>
						<c:when test="${buy.isConfirm eq 'N' }">
							<input type="button" value="주문확인" class="btn btn-info btn-block confirmOrderBtn" data-orderNum="${buy.orderNum }">
						</c:when>
						<c:otherwise>
							<span style="color: red;">확인완료</span>
						</c:otherwise>
					</c:choose>
					</td>
				</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</div>
<script src="resources/js/manageBuy.js?ver=3"></script>
</body>
</html>