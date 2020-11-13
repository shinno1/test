/* 페이지 로딩 후 실행 */
$(document).ready(function(){
	//아래의 정규식은 /정규식/i 형태로 작성
	//1) 숫자만 : ^[0-9]*$
	//2) 영문자만 : ^[a-zA-Z]*$
	//3) 한글만 : ^[가-힣]*$
	//4) 영어 & 숫자만 : ^[a-zA-Z0-9]*$
	//					 ^[a-zA-Z0-9]*$/i
	//5) E-Mail : ^[a-zA-Z0-9]+@[a-zA-Z0-9]+$, /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i
	//6) 휴대폰 : ^01(?:0|1|[6-9]) - (?:\d{3}|\d{4}) - \d{4}$m, /^(01[016789]{1}|02|0[3-9]{1}[0-9]{1})-?[0-9]{3,4}-?[0-9]{4}$/i
	//7) 일반전화 : ^\d{2,3} - \d{3,4} - \d{4}$
	//8) 주민등록번호 : \d{6} \- [1-4]\d{6}
	
	//logout 클릭 시 ...
	$(document).on('click', '#logoutSpan', function() {
		alert('로그아웃 되었습니다.');
		location.href = 'logout.me';
	});
	
	//Join Modal 열릴 때 실행 ...
	$(document).on('show.bs.modal', '#joinModal', function() {
	});
	
	//Join Modal 닫힐 때 실행 ...
	$(document).on('hidden.bs.modal', '#joinModal', function() {
		//input 태그의 내용을 초기화
		$('#joinModal').find('form')[0].reset();
		
		//validate된 label내용 삭제
		$('#joinForm label.error').remove();
	});
	
	//장바구니 클릭 시 ...
	$(document).on('click', '#cartListSpan', function() {
		location.href = 'shopCart.sh';
	});
	
	//validate(유효성 검사)
	$("#joinForm").validate({
        //테스트를 위하여 유효성 검사가 완료되어도 submit을 처리하지 않음.
        debug : false,
        //true : 유효성 체크 없이 무조건 submit false : 유효성 체크하고 submit
        onsubmit : true,
        //입력태그에 focus가 들어왔을 때 유효성을 검사한다.
        onfocusin: false,
        //입력태그에서 focus가 끝났을 때 유효성을 검사한다.
        onfocusout: function(element) { $(element).valid(); }, 
        //입력하자마자 유효성을 검사한다.
        onkeyup: false, 
        //onfocusout: false, 
        //검사할 필드와 검사 항목의 나열
//        groups:{
//        	 socialNum : 'socialNum1 socialNum2',
//           emergencyPhoneNum : 'emergencyPhoneNum2 emergencyPhoneNum3',
//           phoneNum : 'phoneNum2 phoneNum3'
//        },
        
        //유효성 처리
        rules : {
          // className:{
          //    required : true             //필수여부 지정
                //digits : true,               //숫자만 입력 지정
               // email : true,                //이메일만 입력 지정
               // url : true,                  //url만 입력 지정
               // minlength : 4,               //최소길이 지정
               // maxlength : 8,               //최대길이 지정
               // equalTo : "대상요소의 id 값" //특정 요소와 입력값이 같은지 여부 검사
          // },
        	//인풋태그에 아이디 값이 일치(memberId라는 id를 가진 인풋태그에)하면 그 인풋태그에 이런 설정값을 설정한다.
           memberId:{
             required : true,
             minlength : 2,
             maxlength : 10,
             regx:/^[a-zA-Z0-9]*$/i //영어&숫자만
           },
           memberPassword:{
        	 required : true,
        	 minlength : 4,
        	 maxlength : 20,
        	 regx:/^[a-zA-Z0-9]*$/i
           },
           memberPassword1:{
        	   required : true,
        	   minlength : 4,
        	   maxlength : 20,
        	   equalTo : memberPassword
           },
           memberName:{
        	   required : true,
        	   minlength : 2,
        	   maxlength : 18,
        	   regx : /^[가-힣a-zA-Z]*$/i
           },
           tel1:{
        	   required: true,
        	   minlength : 9, //02 000 0000
        	   maxlength : 13,//010 - 0000 - 0000
        	   regx : /^[0-9-]*$/i
           },
           tel2:{
        	   minlength : 9, //02 000 0000
        	   maxlength : 13,//010 - 0000 - 0000
        	   regx : /^[0-9-]*$/i
           },
           email:{
        	   required: true,
        	   email: true
//        	   regx: /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i
           },
           birthdayYear:{
        	   required: true,
        	   digits: true
           },
           birthdayMonth:{
        	   required: true,
        	   digits: true
           },
           birthdayDay:{
        	   required: true,
        	   digits: true
           },
           memberAddr:{
        	   required: true,
           }
           
        },
        //검사를 충족하지 못할 경우 표시될 메시지의 나열                                                         
        messages : {
          // className : "과정명을 입력하세요.",
          // className : {
          //      required : "필수 입력 항목 입니다.",
           //     number : "숫자만 입력하세요.",
          //      minlength : "최소 {0}글자 입니다.",
           //     maxlength : "최대 {0}글자 입니다.",
           //     equalTo : "입력이 잘못되었습니다."
           // },
           //memberId:'ID를 입력하세요',
           memberId:{
               required: 'ID는 필수항목입니다.',
               minlength:'ID는 2자 이상 입력하세요.',
               maxlength:'ID는 10자를 초과할 수 없습니다.',
               regx:'영어와 숫자만 사용가능합니다.'
           },
           memberPassword:{
        	   required: 'PASSWORD는 필수항목입니다.',
               minlength:'PASSWORD는 {0}자 이상 입력하세요.',
               maxlength:'PASSWORD는 {0}자를 초과할 수 없습니다.',
               regx:'영어와 숫자만 사용가능합니다.'
           },
           memberPassword1:{
        	   required: 'PASSWORD는 필수항목입니다.',
               minlength: 'PASSWORD는 {0}자 이상 입력하세요.',
               maxlength: 'PASSWORD는 {0}자를 초과할 수 없습니다.',
               equalTo: '비밀번호 입력값이 다릅니다.'
           },
           memberName:{
        	   required: '이름은 필수항목입니다.',
        	   minlength: '이름은 {0}자 이상 입력하세요.',
        	   maxlength: '이름은 {0}자를 초과할 수 없습니다.',
        	   regx: '한글과 영어만 사용가능합니다.'
           },
           tel1:{
        	   required: '본인연락처1은 필수항목입니다.',
        	   minlength: '전화번호는 {0}자 이상 입력하세요.',
        	   maxlength: '전화번호는 {0}자를 초과할 수 없습니다.',
        	   regx: '숫자와 -만 사용 가능합니다.'
           },
           tel2:{
        	   minlength: '전화번호는 {0}자 이상 입력하세요.',
        	   maxlength: '전화번호는 {0}자를 초과할 수 없습니다.',
        	   regx: '숫자와 -만 사용가능합니다.'
           },
           email:{
        	   required: '이메일은 필수항목입니다.',
        	   email: '이메일 형식으로 작성해주세요.'
           },
           birthdayYear:{
        	   required: '태어난 연도는 필수항목입니다.',
        	   digits: '숫자만 사용가능합니다.'
           },
           birthdayMonth:{
        	   required: '태어난 월은 필수항목입니다.',
        	   digits: '숫자만 사용가능합니다.'
           },
           birthdayDay:{
        	   required: '태어난 일은 필수항목입니다.',
        	   digits: '숫자만 사용가능합니다.'
           },
           memberAddr:{
        	   required: '주소는 필수항목입니다.',
           }
           
           
        },
        //유효성 검사 실패 시 메세지의 출력 방식을 설정
        errorPlacement: function(error, element){
           error.insertAfter(element);
        },
        //유효성 검사 완료(성공) 후 실행할 코드
        submitHandler: function(form) {
        	form.submit();
          // $( "#dialog-confirm" ).dialog( "open" );
        }
   });
});

/* 함수선언 영역*/
(function($){
	//aaa라는 함수선언
	//aaa = function(){
	
	//};
})(jQuery);