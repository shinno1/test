/* 페이지 로딩 후 실행 */
$(document).ready(function(){
	//클릭 이벤트
	$(document).on('click', '.confirmOrderBtn', function() {
		var orderNum = $(this).attr('data-orderNum'); // 함수선언 영역에선 $(this)를 인식하지 않는다!!!
		var isConfirmTd = $(this).parent();
		updateIsConfirm(orderNum, isConfirmTd);
	});
	
	
});

/* 함수선언 영역*/
(function($){
	//주문확인을 N에서 Y로 업데이트하는 함수선언
	updateIsConfirm = function(orderNum, isConfirmTd){
		//Ajax 시작
		$.ajax({
		      url: 'updateIsConfirm.ad', //요청경로
		      type: 'post',
		      data: {'orderNum' : orderNum}, //요청경로로 던질 파라메터. '파레메터명':파라메터
		      success: function(result) { // ajax 통신 성공 시 실행부분. result가 결과 데이터를 가진다.
		    	  if(result != 1){
		    		  alert('수정 실패');
		    	  }
		    	  else{
		    		  $(isConfirmTd).empty();
		    		  $(isConfirmTd).append('<span style="color: red;">확인완료</span>');
		    	  }
		      },
		      error: function(){ //ajax통신 실패 시 실행부분
		    	  alert('오류!!!');
		      }
		});
	};
})(jQuery);