<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://fonts.googleapis.com/css2?family=Gamja+Flower&display=swap" rel="stylesheet">
<style type="text/css">
span:hover {
   cursor: pointer;
}

body{
   font-family: 'Gamja Flower', cursive;
   font-size: 17px;
}

b{
   font-size: 20px;
}
</style>
</head>
<body>
   <!-- 메뉴 내 3개로 층 나눔  -->
   <div class="row">
      <div class="col-md-6 col-md-offset-6 text-right">
         <!-- text-right: 글자 우측 정렬 -->
         <c:if test="${sessionScope.loginInfo.memberType eq 2 }">
            <b>♕VIP</b>
         </c:if>
         <c:if test="${empty sessionScope.loginInfo.memberId }">
         <span data-toggle="modal" data-target="#loginModal"><b>Login</b></span><!--span에 modal 띄우기 위한 작업  -->&nbsp;&nbsp;<span data-toggle="modal" data-target="#joinModal"><b>Join</b></span>
         </c:if>
         <c:if test="${not empty sessionScope.loginInfo.memberId }">
            <b>${sessionScope.loginInfo.memberName }</b>님 환영합니다!&nbsp;&nbsp;&nbsp;<span style="color: black; text-decoration: none;" id="logoutSpan"><b>Logout</b></span> <span id="cartListSpan">장바구니 가기</span>
         </c:if>
      </div>
   </div>
   
   <div class="row">
      <div class="col-md-12">
         <h1>Shop</h1>
      </div>
   </div>

   <!-- 부트스트랩 홈페이지에서 기본네비게이션바 소스 복붙 -->
   <div class="row">
      <nav class="navbar navbar-default">
         <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
               <button type="button" class="navbar-toggle collapsed"
                  data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                  <span class="sr-only">Toggle navigation</span> <span
                     class="icon-bar"></span> <span class="icon-bar"></span> <span
                     class="icon-bar"></span>
               </button>
               <a class="navbar-brand" href="#">Brand</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse"
               id="bs-example-navbar-collapse-1">
               <ul class="nav navbar-nav">
                              <!-- memberVO로 사용가능 -->
                  <li <c:if test="${goodsVO.mainMenu eq 'shopping' }">class="active"</c:if> ><a href="shopList.sh?mainMenu=shopping&subMenu=allCategory">쇼핑하기 <span class="sr-only">(current)</span></a></li>
                  <li <c:if test="${memberVO.mainMenu eq 'member' }">class="active"</c:if>><a href="buyHistory.me?mainMenu=member&subMenu=selectBuyHistory">내정보 관리</a></li>
                  <c:if test="${sessionScope.loginInfo.memberType eq 4 }">
                  	<li <c:if test="${memberVO.mainMenu eq 'admin' }">class="active"</c:if>><a href="manageBuy.ad?mainMenu=admin&subMenu=manageBuy">관리자메뉴</a></li>
                  </c:if>
               </ul>
               <form class="navbar-form navbar-right" role="search">
                  <div class="form-group">
                     <input type="text" class="form-control" placeholder="Search">
                  </div>
                  <button type="submit" class="btn btn-default">Submit</button>
               </form>
            </div>
            <!-- /.navbar-collapse -->
         </div>
         <!-- /.container-fluid -->
      </nav>
   </div>



   <!-- 부트스트랩 홈페이지에서 실시간 모달 소스 복붙 -->
   <!-- Login Modal -->
   <div class="modal fade" id="loginModal" tabindex="-1" role="dialog"
      aria-labelledby="myModalLabel" aria-hidden="true">
      <div class="modal-dialog">
         <div class="modal-content">
            <div class="modal-header">
               <button type="button" class="close" data-dismiss="modal"
                  aria-label="Close">
                  <span aria-hidden="true">&times;</span>
               </button>
               <h4 class="modal-title" id="myModalLabel">Login</h4>
            </div>
            <div class="modal-body">
            
               <!-- 부트스트랩 홈페이지의 css 폼에서 원하는 형태의 로그인 폼 붙여넣기  -->
               <form class="form-horizontal" action="loginMember.me" method="post">
                  <div class="form-group">
                  
                     <!-- input태그와 라벨이 한 쌍이 되도록 사용 -->
                     <label for="loginMemberId" class="col-sm-2 control-label">ID</label>
                     <div class="col-sm-10">
                        <input type="text" class="form-control" id="loginMemberId" name="memberId" placeholder="ID" autocomplete="off">
                     </div>
                  </div>
                  <div class="form-group">
                     <label for="loginMemberPassword" class="col-sm-2 control-label">Password</label>
                     <div class="col-sm-10">
                        <input type="password" class="form-control" id="loginMemberPassword" name="memberPassword" placeholder="Password">
                     </div>
                  </div>
                  <div class="form-group">
                     <div class="col-sm-offset-2 col-sm-10">
                        <div class="checkbox">
                           <label> <input type="checkbox"> Remember me
                           </label>
                        </div>
                     </div>
                  </div>
                  <div class="modal-footer">
                     <input type="submit" class="col-sm-12 btn btn-primary" value="Login">
                  </div>
               </form>
            </div>
         </div>
      </div>
   </div>


   <!-- Join Modal -->
   <div class="modal fade" id="joinModal" tabindex="-1" role="dialog"
      aria-labelledby="myModalLabel" aria-hidden="true">
      <div class="modal-dialog">
         <div class="modal-content">
            <div class="modal-header">
               <button type="button" class="close" data-dismiss="modal"
                  aria-label="Close">
                  <span aria-hidden="true">&times;</span>
               </button>
               <h4 class="modal-title" id="myModalLabel">Join</h4>
            </div>
            <div class="modal-body">
               <!-- 부트스트랩 홈페이지의 css 폼에서 원하는 형태의 join 폼 붙여넣기  -->
               <form class="form-horizontal" action="insertMember.me" method="post" id="joinForm">
                  <div class="form-group">
                     <!-- input태그와 라벨이 한 쌍이 되도록 사용 -->
                     <label for="memberId" class="col-sm-2 control-label">아이디</label>
                     <div class="col-sm-10">
                        <input type="text" class="form-control" id="memberId" name="memberId" placeholder="I D" autocomplete="off">
                     </div>
                  </div>
                  <div class="form-group">
                     <label for="memberPassword" class="col-sm-2 control-label">비밀번호</label>
                     <div class="col-sm-10">
                        <input type="password" class="form-control" id="memberPassword" name="memberPassword" placeholder="Password">
                     </div>
                  </div>
                  <div class="form-group">
                     <label for="memberPassword1" class="col-sm-2 control-label">비밀번호 확인</label>
                     <div class="col-sm-10">
                        <input type="password" class="form-control" id="memberPassword1" name="memberPassword1" placeholder="Password확인">
                     </div>
                  </div>
                  <div class="form-group">
                     <label for="memberName" class="col-sm-2 control-label">이름</label>
                     <div class="col-sm-10">
                        <input type="text" class="form-control" id="memberName" name="memberName" placeholder="이름" autocomplete="off">
                     </div>
                  </div>
                  <div class="form-group">
                        <label for="memberGender" class="col-sm-2 control-label">성별</label> 
                     <div class="col-sm-10">
                        <label class="radio-inline"> <input type="radio" name="memberGender" id="memberGender1" value="male">남성</label>
                        <label class="radio-inline"> <input type="radio" name="memberGender" id="memberGender2" value="female">여성</label> 
                     </div>
                  </div>
                  <div class="form-group">
                     <label class="col-sm-2 control-label">연락처1</label>
                        <div class="col-sm-10">
                     <input type="tel" class="form-control" name="tel1" placeholder="본인 연락처" autocomplete="off">
                     </div>
                     <label class="col-sm-2 control-label">연락처2</label>
                     <div class="col-sm-10">
                     <input type="tel" class="form-control" name="tel2" placeholder="집전화 혹은 본인 연락처 외의 연락처" autocomplete="off">
                     </div>
                  </div>
                  <div class="form-group">
                     <label for="smsYn" class="col-sm-2 control-label">SMS수신</label> 
                     <div class="col-sm-10">
                        <label class="radio-inline"> <input type="radio" name="smsYn" id="smsYn1" value="Y" checked="checked">YES</label> 
                        <label class="radio-inline"> <input type="radio" name="smsYn" id="smsYn2" value="N">NO</label>
                     </div>
                  </div>
                  <div class="form-group">
                     <!-- input태그와 라벨이 한 쌍이 되도록 사용 -->
                     <label for="email" class="col-sm-2 control-label">Email</label>
                     <div class="col-sm-10">
                        <input type="email" class="form-control" id="email" name="email" placeholder="Email" autocomplete="off">
                     </div>
                  </div>
                  <div class="form-group">
                     <label for="emailYn" class="col-sm-2 control-label">Email수신</label> 
                     <div class="col-sm-10">
                        <label class="radio-inline"> <input type="radio" name="emailYn" id="emailYn1" value="Y" checked>YES</label> 
                        <label class="radio-inline"> <input type="radio" name="emailYn" id="emailYn2" value="N">NO</label>
                     </div>
                  </div>
                  <div class="form-group">
                     <label for="birthday" class="col-sm-2 control-label">생년월일</label>
                  <div class="row">
                       <div class="col-sm-3">
                         <input type="text" class="form-control" name="birthdayYear" placeholder="탄생 해" autocomplete="off">
                       </div>
                       <div class="col-sm-3">
                         <input type="text" class="form-control" name="birthdayMonth" placeholder="탄생 월" autocomplete="off">
                       </div>
                       <div class="col-sm-3">
                         <input type="text" class="form-control" name="birthdayDay" placeholder="탄생 일" autocomplete="off">
                       </div>
                     </div>
                  </div>
                  <div class="form-group">
                     <label class="col-sm-2 control-label">주소</label>
                        <div class="col-sm-10">
                           <input type="tel" class="form-control" name="memberAddr" placeholder="주소" autocomplete="off">
                        </div>
                  </div>
                     <div class="modal-footer">
                     <input type="submit" class="col-sm-12 btn btn-primary" value="Join">
                  </div>
               </form>
            </div>
            <!-- <div class="modal-footer">
               <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
               <button type="button" class="btn btn-primary">Login</button>
            </div> -->
         </div>
      </div>
   </div>



<script src="resources/js/menu.js?ver=38"></script>
</body>
</html>