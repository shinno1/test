/* 페이지 로딩 후 실행 */
$(document).ready(function(){
	//구매하기 버튼 클릭 시
	$(document).on('click', '#buyBtn', function() {
		var memberId = $('#loginId').val();
		
		//모달창에 가격정보를 세팅
		var cnt = $('input[type="number"]').val(); //수량
		var goodsPrice = $('#goodsPrice').text(); //단가
		var goodsDeliveryPrice = $('#goodsDeliveryPrice').text();
		var price = goodsPrice * cnt + parseInt(goodsDeliveryPrice); //parseInt() : 숫자로 형변환
		//가격을 총 가격으로 보이게 바꿔라.
		$('#modalPrice').text(price);
		//로그인 안되있으면...
		if(memberId == ''){
			//로그인 하라는 alert창
			alert('구매를 하시려면 로그인을 하세요.');
		}
		else{
			//구매정보 모달창 띄움
			$('#buyModal').modal();
		}
	
		});
	
	//모달 구매하기 버튼 클릭 시
	$(document).on('click', '#modalBuyBtn', function() {
		var goodsId = $('#goodsId').val();
		var cnt = $('input[type="number"]').val();
		
		alert('구매 페이지로 이동합니다.');
		
		location.href = 'buyPage.sh?goodsId=' + goodsId + '&cnt=' + cnt ;
	});
	
	//모달 취소하기 버튼 클릭 시
	$(document).on('click', '#modalCancelBtn', function() {
		$('#buyModal').modal('hide');
	});
	
	//장바구니 담기 버튼 클릭 시
	$(document).on('click', '#cartBtn', function() {
		insertCart();
	});
});

/* 함수선언 영역*/
(function($){
	//장바구니 담기
	insertCart = function(){
		var goodsCnt = $('#goodsCnt').val();
		var goodsId = $('#goodsId').val();
		
		//Ajax 시작
		$.ajax({
		      url: 'insertCart.sh', //요청경로
		      type: 'post',
		      data: {'goodsCnt':goodsCnt, 'goodsId':goodsId}, //요청경로로 던질 파라메터. '파레메터명':파라메터
		      success: function(result) { // ajax 통신 성공 시 실행부분. result가 결과 데이터를 가진다.
		    	  var cart = confirm('해당 상품을 장바구니에 담았습니다.\n장바구니로 이동할까요?');
		  			if(cart){
		  				location.href = "shopCart.sh";
		  			}
		  			else{
		  				return;
		  			}
		      },
		      error: function(){ //ajax통신 실패 시 실행부분
		    	  alert('오류!!!');
		      }
		});
	};
})(jQuery);