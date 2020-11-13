/* 페이지 로딩 후 실행 */
$(document).ready(function(){
	//전체금액 계산
	setTotalPrice();
	
	//전체체크박스 클릭 시
	$(document).on('click', '#checkAll', function() {
		var isChecked = $('#checkAll').is(':checked');
		
		if(isChecked){//체크가 되었을때.
			$('.check').prop('checked', true);
		}
		else{
			$('.check').prop('checked', false);
		}
		
		setTotalPrice();
	});
	
	//하위 체크박스 변경 시 제목줄의 체크박스 변경
	$(document).on('click', '.check', function() {
		//.check의 개수
		var chkCnt = $('.check').length;
		//.check 중에서 체크가 된 노드의 개수
		var checkedCnt = $('.check:checked').length;
		
		if(chkCnt == checkedCnt){
			$('#checkAll').prop('checked', true);
		}
		else{
			$('#checkAll').prop('checked', false);
		}
		
	}); 
	
	//각 상품의 체크박스 클릭 시
	$(document).on('click', '.check', function() {
		setTotalPrice();
	});
	
	//장바구니 비우기 버튼 클릭 시
	$(document).on('click', '#deleteCartBtn', function() {
		var checkedCnt = $('.check:checked').length;
		
		if(checkedCnt == 0){
			alert('장바구니에서 비울 상품을 선택하세요.');
			return ;
		}

		//cartId가 담길 배열 생성
		var cartIds = new Array();
		
		//체크박스 중 체크가 된 노드의 value값(cartId)를 가져온다.
		$('.check:checked').each(function(index, element){
			var cartId = $(element).val();
			cartIds[index] = cartId;
		});
		
		location.href = 'deleteCart.sh?cartIds=' + cartIds;
		
	});
	
	//각 상품의 수량 변경 시
	$(document).on('change', '.goodsCnt', function() {
		var cartId = $(this).attr('data-cartId');
		var goodsCnt = $(this).val();
		var goodsPrice = $(this).parent().prev().text();
		var totalPrice = goodsPrice * goodsCnt;
		var totalPriceNode = $(this).parent().next();
		
		updateGoodsCnt(cartId, goodsCnt, totalPrice, totalPriceNode);
		
		//var selectedNode = $(this);
		//updateGoodsCnt(selectedNode);
	});
	
	//구매하기 버튼 클릭 시
	$(document).on('click', '#cartBuyBtn', function() {
		var cartIdArray = new Array();
		
		$('.check:checked').each(function(index, element){
			var cartId = $(element).val();
			cartIdArray[index] = cartId;
		});
		
		location.href = 'cartBuyPage.sh?cartIdArray=' + cartIdArray;
	});
	
	
});

/* 함수선언 영역*/
(function($){
	//총금액을 계산하는 함수
	setTotalPrice = function(){
		var totalPrice = 0;
		
		$('.check:checked').each(function(index, element){
			var price = $(element).parent().parent().children(':last').text();
			totalPrice += parseInt(price);
		});
		
		$('#totalPrice').text(totalPrice);
//			$(element).parent().parent().children().eq(0)
//			$(element).parent().parent().children().eq(':first')
//			$(element).parent().parent().children(':first')
			
//			$(element).parent().parent().children().eq(':last')
	};
	
	updateGoodsCnt = function(cartId, goodsCnt, totalPrice, totalPriceNode){
		//var cartId = $(this).attr('data-cartId');
		//var goodsCnt = $(this).val();
		//var goodsPrice = $(this).parent().prev().text();
		//var totalPrice = goodsPrice * goodsCnt;
		//var totalPriceNode = $(this).parent().next();
		$.ajax({
		      url: 'updateCartCnt.sh', //요청경로
		      type: 'post',
		      data: {'cartId' : cartId, 'goodsCnt' : goodsCnt}, //요청경로로 던질 파라메터. '파레메터명':파라메터
		      success: function(result) { // ajax 통신 성공 시 실행부분. result가 결과 데이터를 가진다.
		    	  $(totalPriceNode).text(totalPrice);
		    	  setTotalPrice();
		      },
		      error: function(){ //ajax통신 실패 시 실행부분
		    	  alert('오류!!!');
		      }
		});
		
	};
})(jQuery);