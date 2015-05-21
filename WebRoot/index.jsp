<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<base href="${base}/">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="chrome=1,IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>登录-UPS管理系统</title>

<!-- Bootstrap core CSS -->
<link href="assets/css/bootstrap.css" rel="stylesheet">
<link href="assets/css/font-awesome.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="assets/css/signin.css" rel="stylesheet">

<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!--[if lt IE 9]><script src="assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
<script src="assets/js/ie-emulation-modes-warning.js"></script>
<script src="assets/js/jquery1x.js"></script>
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="assets/js/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="assets/js/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<style type="text/css">
* {
	font-family: "Microsoft YaHei";
}
</style>
</head>

<body style="background: url(assets/image/OS_X_10_906.jpg) no-repeat;background-size:cover;">

	<div class="container" style="margin-top: 5%;">
		<form class="form-signin" style="background:rgba(3,3,3,0.2);border-radius:10px; ">
			<h3 class="pull-right inline" style="color: #fff;font-weight: bold;">UPS管理系统</h3>
			<h2 class="form-signin-heading text-primary">
				<i class="glyphicon glyphicon-user"></i>
			</h2>
			<br> <label for="inputEmail" class="sr-only">用户名</label> <input
				type="text" id="username" name="username" class="form-control"
				placeholder="用户名" required autofocus> <br> <label
				for="inputPassword" class="sr-only">密码</label> <input
				type="password" name="password" id="password" class="form-control"
				placeholder="密码" required>
			<div class="checkbox">
				<label> <!-- <input type="checkbox" value="remember-me"> 记住我 -->
				</label> <span id="msg" class="pull-right "></span>
			</div>
			<button class="btn btn-lg btn-primary btn-block" type="button"
				id="login">
				<i class="fa fa-sign-in "></i>&nbsp;登录
			</button>

			<%
				session.removeAttribute("msg");
			%>
		</form>


	</div>
	<!-- /container -->

	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="assets/js/ie10-viewport-bug-workaround.js"></script>

	<script type="text/javascript">
		$("#login").click(function() {
			$.ajax({
				url : "login",
				type : "POST",
				data : $("form").serialize(),
				error : function(request) {
					if(request.status==200)
					window.location = "home";
				},
				dataType : "json",
				success : function(data) {
					if (data && data.ok) {
						window.location = "";
					} else {
						alert(data.msg);
					}
				}
			});

		});
		$("input").click(function() {
			$("#msg").html("");
		});
	</script>
</body>
</html>

