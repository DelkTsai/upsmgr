<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
				<span class="sr-only"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="home">UPSMGR</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<li v-repeat="menu : nav"  class="{{menu.active?'active':''}}"><a href="{{menu.href}}"><i class="{{menu.icon}}"></i>&nbsp;{{menu.text}}</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a><i class="fa fa-user" style="font-size: 18px;"></i>&nbsp;欢迎，${curruser.username}</a></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">设置 <span class="caret"></span></a>
					<ul class="dropdown-menu" role="menu">
						<li><a href="resetpwd"><span><i class="fa fa-key"></i>&nbsp;&nbsp;修改密码</span></a></li>
						<li class="divider"></li>
						<li><a href="resetinfo"><span><i class="fa fa-info-circle"></i>&nbsp;&nbsp;个人信息</span></a></li>
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