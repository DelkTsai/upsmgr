<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div id="navbar">
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#upsnavbar" aria-expanded="false"
					aria-controls="upsnavbar">
					<span class="sr-only"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span> <span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="home">UPSMGR</a>
			</div>
			<div class="navbar-collapse collapse" id="upsnavbar">
				<ul class="nav navbar-nav">
					<li v-repeat="menu : menus"
						class="{{menu.active?'active':''}} {{menu.hasChild?'dropdown':''}}">
						<template v-if="!menu.hasChild"> <a
							href="{{menu.menuLink}}"><i class="{{menu.menuIcon}}"></i>&nbsp;{{menu.menuText}}</a>
						</template> <template v-if="menu.hasChild"> <a
							href="{{menu.menuLink}}" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-expanded="false"><i
							class="{{menu.menuIcon}} "></i>&nbsp;{{menu.menuText}}<span class="caret"></span></a>
						<ul class="dropdown-menu  dropdown-inverse" role="menu">
							<li v-repeat="sub : menu.subMenus"
								class="{{sub.active?'active':''}}" style="padding-top: 5px;">
								<a href="{{sub.menuLink}}"><i class="{{sub.menuIcon}}"></i>&nbsp;{{sub.menuText}}</a>
							</li>
						</ul>
						</template>
					</li>
					
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a><i class="fa fa-user" style="font-size: 18px;"></i>&nbsp;欢迎，${curruser.username}</a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-expanded="false">设置
							<span class="caret"></span>
					</a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#" data-toggle="modal"
								data-target="#password-change"> <span><i
										class="fa fa-key"></i>&nbsp;&nbsp;修改密码</span>
							</a></li>
							<!--  <li class="divider"></li>  -->
						</ul></li>
					<li><a href="logout">退出&nbsp;<i class="fa fa-sign-out"></i></a></li>
				</ul>
				<!--
			<form class="navbar-form navbar-right">
            <input type="text" class="form-control" placeholder="搜索...">
         	 </form>
			  -->

			</div>
		</div>
	</nav>

	<ol class="breadcrumb" style="margin: 0">
		<i class="fa fa-map-marker text-danger"></i>当前位置：
		<li><a href="home"><i class="fa fa-home"></i>首页</a></li>
		<li v-repeat="menus| menuFilter">{{menuText}}</li>
		<li v-repeat="menus | subFilter">
		<span v-repeat="subMenus | menuFilter">{{menuText}}</span>
	</ol>

</div>


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
				<span class="text-danger pull-left" id="msg"
					style="font-weight: bold;"></span>
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
	
	var navbar = new Vue({
		el : "#navbar",
		data : {
			menus : menu
		},
		filters : {
			menuFilter : function(menus) {
				return menus.filter(function(item) {
					return item.active;
				});
			},
			subFilter : function(menus) {
				return menus.filter(function(item) {
					return item.active && item.hasChild;
				});
			}
		}
	});

	function activeMenu(menu) {
		if (menu.indexOf("/")>0) {
			$(navbar.menus).each(function(index, item) {
				if (item.menuLink == "#") {
					$(item.subMenus).each(function(index, sitem) {
						if (sitem.menuLink == menu) {
							item.active = true;
							sitem.active = true;
						}
					});
				}
			});
		} else {
			$(navbar.menus).each(function(index, item) {
				if (item.menuLink == menu) {
					item.active = true;
				}
			});
		}

	}

	$(function() {
		$("#password-change").modal({
			backdrop : false,
			show : false
		});

		$("#cpass").click(
				function() {
					$.post("changePassword", $("#changepass").serialize(),
							function(data) {
								$("#msg").text(data.msg);
							}, "json");
				});

	});
</script>