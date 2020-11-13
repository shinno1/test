/* 페이지 로딩 후 실행 */
$(document).ready(function(){
	$(document).on('change', '#year', function() {
		var salesPerYear = $('#year').val();
		selectSalesList(salesPerYear);
	});
	//매출 월 클릭 시...
	$(document).on('click', '.salesMonth', function() {
		var month = $(this).text();
		selectSalesListPerMonth(month);
	});
});

/* 함수선언 영역*/
(function($){
	//셀렉트박스의 값 변경 시 해당 연도의 매출액 정보를 조회
	selectSalesList = function(salesPerYear){
		//Ajax 시작
		$.ajax({
		      url: 'selectSalesListAjax.ad', //요청경로
		      type: 'post',
		      data: {'salesPerYear':salesPerYear}, //요청경로로 던질 파라메터. '파레메터명':파라메터
		      success: function(result) { // ajax 통신 성공 시 실행부분. result가 결과 데이터를 가진다.
		    	  $('#selectTbody').empty();
		    	 
		    	  var month = [];
		    	  var sales = [];
		    	  var str = '';
		    	  
		    	 
		    	  
		    	  for(var i = 0 ; i < result.length ; i++){
		    		  month[i] = result[i].buyMonth;
		    		  sales[i] = result[i].salesPerMonth;
		    		  str += `<tr><td class="salesMonth">${month[i]}</td><td>${sales[i]}</td></tr>`;
		    	  }
		    	  
		    	  $('#selectTbody').append(str);
		    	  
//		    	  $(result).each(function(index, element){
//		    		  str += '<tr>';
//		    		  
//		    		  str += '<td>' + element.buyMonth + '</td>';
//		    		  str += '<td>' + result[index].buyMonth + '<td>';
//		    		  str += `<td>${result[index].buyMonth}</td>`;
//		    		  str += `<td>${element.buyMonth}</td>`;
//		    		  str += '<td>' + element.salesPerMonth + '</td>';
//		    		  str += '</tr>';
//		    		  
//		    	  });
		      },
		      error: function(){ //ajax통신 실패 시 실행부분
	
		      }
		});
	};
	
	//일별 매출액 목록 조회
	selectSalesListPerMonth = function(month){
		var year = $('#year').val();
		var yearMonth = year + month;
		$.ajax({
		      url: 'selectSalesListPerMonth.ad', //요청경로
		      type: 'post',
		      data: {'yearMonth':yearMonth}, //요청경로로 던질 파라메터. '파레메터명':파라메터
		      success: function(result) { // ajax 통신 성공 시 실행부분. result가 결과 데이터를 가진다.
				  $('#salesPerDayDiv').empty();
				 
				  var str = '';
				   
				  str += '<table class="table table-striped">';
				  str += '<tr>';
				  str += '<td>일</td>';
				  str += '<td>매출액</td>';
				  str += '</tr>';
				  str += '<tbody>';
				  
				  $(result).each(function(index, element){ //result = salesList
					  str += '<tr>';
					  str += '<td>' + element.buyDay + '</td>';
					  str += '<td>' + element.salesPerDay + '</td>';
					  str += '</tr>';
				  });
				  
				  str += '</tbody>';
				  str += '</table>';
				   
				  $('#salesPerDayDiv').append(str);
			      },
			       error: function(){ //ajax통신 실패 시 실행부분
		
		      }
		});
	};
	
})(jQuery);