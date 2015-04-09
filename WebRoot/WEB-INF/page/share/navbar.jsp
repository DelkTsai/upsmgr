<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="home">UPSMGR</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<li v-repeat="menu : nav" class="{{menu.active?'active':''}}"><a
					href="{{menu.href}}"><i class="{{menu.icon}}"></i>&nbsp;{{menu.text}}</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a><i class="fa fa-user" style="font-size: 18px;"></i>&nbsp;欢迎，${curruser.username}</a></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-expanded="false">设置 <span
						class="caret"></span></a>
					<ul class="dropdown-menu" role="menu">
						<li><a href="#" data-toggle="modal"
							data-target="#password-change"><span><i
									class="fa fa-key"></i>&nbsp;&nbsp;修改密码</span></a></li>
						<!-- 
							<li class="divider"></li>
						<li><a href="resetinfo"><span><i
									class="fa fa-info-circle"></i>&nbsp;&nbsp;个人信息</span></a></li>
						 -->

					</ul>
				<li><a href="exit">退出&nbsp;<i class="fa fa-sign-out"></i></a></li>
			</ul>
			<!--
			<form class="navbar-form navbar-right">
            <input type="text" class="form-control" placeholder="搜索...">
         	 </form>
			  -->

		</div>
	</div>
</nav>

<div class="modal fade" id="password-change" tabindex="-1" role="dialog"
	aria-labelledby="passLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="passLabel">
					<span v-show="!show"><i class="fa fa-key"></i>&nbsp;修改密码</span>
				</h4>
			</div>
			<div class="modal-body">
				<form id="changepass">
					<div class="form-group">
						<label for="oldpassword">原密码</label> <input type="password"
							class="form-control" id="oldpassword" name="oldpassword"
							placeholder="原密码">
					</div>
					<div class="form-group">
						<label for="newpassword">新密码</label> <input type="password"
							class="form-control" id="newpassword" name="newpassword"
							placeholder="新密码">
					</div>
					<div class="form-group">
						<label for="repassword">确认新密码</label> <input type="password"
							class="form-control" id="repassword" name="repassword"
							placeholder="确认新密码">
					</div>
				</form>
			</div>
			<div class="modal-footer">
			<span class="text-danger pull-left" id="msg" style="font-weight: bold;"></span>
				<button type="button" class="btn btn-danger" data-dismiss="modal">
					<i class="fa fa-close"></i>&nbsp;关闭
				</button>
				<button type="button" id="cpass" class="btn btn-success">
					<i class="fa fa-save"></i>&nbsp;保存
				</button>
			</div>
		</div>
	</div>
</div>
<script>
	$(function() {
		$("#password-change").modal({
			backdrop : false,
			show : false
		});

		$("#cpass").click(function() {
			$.post("changePassword", $("#changepass").serialize(),
				function(data) {
				$("#msg").text(data.msg);
			},"json");
		});
			
	});
</script>