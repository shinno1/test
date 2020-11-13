<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.out{
		margin-top: 100px;
		text-align: center;
	}
	input[type="password"]{
		width: 200px;
	}
</style>
</head>
<body>
<h1>회원탈퇴 페이지</h1>
<div class="out"><input type="password" name="deleteMember" placeholder="비밀번호를 입력해주세요." maxlength="12"> <input type="button" value="회원탈퇴"> </div>
${sessionScope.loginInfo.memberId }
${sessionScope.loginInfo.memberName }
${sessionScope.loginInfo.memberPassword }
${sessionScope.loginInfo.memberPassword }
</body>
</html>