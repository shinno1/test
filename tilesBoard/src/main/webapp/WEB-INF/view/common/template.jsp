<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><tiles:insertAttribute name="title" /></title>
<link rel="stylesheet" type="text/css" href="resources/css/reset.css"/>
<!-- 부트스트립 사용하기 위한 링크: 붙이는 순서 중요 -->
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<!-- Jquey를 사용을 위한 태그 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script src="resources/js/jquery.validate.js"></script>
<style type="text/css">
label.error{
	color: red;
	font-size: 12px;
	display: block;
}
.nav-pills>li.active>a{
	background-color: #ffdddd;
}
.nav-pills>li.active>a:hover{
    background: #ffdddd;
}
.nav-pills>li>a:focus{
    background: #ffdddd;
    color: white;
}

.panel, .panel-heading{
     border-radius: 30px;
}
.myContainer{
	margin-right: auto;
	margin-left: auto;
	width: 80%;
}
</style>
<script type="text/javascript">
//validate에 정규식 사용하게 설정
$.validator.addMethod('regx', function(value, element, regexpr){
 return regexpr.test(value);
});
</script>
</head>
<body>
<!-- 부트스트랩은 클래스 명으로 되어있기 때문에 클래스명으로 불러오면 됨(여러 개 사용 가능):홈페이지 참고-->
<div class="container-fluid">
	<div class="myContainer">
		<div class="row">
			<div class="col-md-12">
				<tiles:insertAttribute name="menu"/><!-- 이름이 menu인 애를 긁어와주세요. -->
			</div>
		</div>
		<div class="row">
			<div class="col-md-2" style="padding: 0px;">
				<tiles:insertAttribute name="side"/>
			</div>
			<div class="col-md-10">
				<tiles:insertAttribute name="body"/>
			</div>
		</div>
	</div>
</div>
</body>
</html>










