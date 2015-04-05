<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<title>用户管理| Admin管理系统</title>

<!-- ajax layout which only needs content area -->
<div class="row">
	<div class="col-xs-12 no-padding">
		<!-- PAGE CONTENT BEGINS -->

		<div id="userlist" class="widget-box widget-color-blue2 ">
			<div class="widget-header widget-header-small widget-header-flat" style="padding:5px 10px">
				<div class="widget-title">
					检索： <select class="inline-block chosen-select" name="orgid" title="组织" style="height:32px;">
						<option>选择组织</option>

					</select> <select class="inline-block chosen-select" name="roleid" title="角色" style="height:32px;">
						<option>选择角色</option>

					</select> <input type="text" class="input-small no-padding" name="username" placeholder="用户名" title="用户名" />
					<button class="btn btn-sm btn-primary no-border" type="button" onclick="searchTransmitter()">
						<i class="glyphicon glyphicon-search"></i>
					</button>
				</div>
				<div class="widget-toolbar">
					<a data-action="fullscreen" class="white" href="javascript:;"> <i class="ace-icon fa fa-expand"></i>
					</a> <a data-action="" class="" href="javascript:;"> <i>&nbsp;&nbsp;</i>
					</a>
				</div>
			</div>
			<div class="widget-body"></div>
		</div>
		<div class="widget-box ui-sortable-handle">
			<div class="widget-header widget-header-small widget-header-flat">
				<h5 class="widget-title"></h5>
				<div class="widget-toolbar no-border">
					<ul id="myTab" class="nav nav-tabs">
						<li class="active"><a href="#edit" data-toggle="tab">编辑</a></li>
						<li><a href="#add" data-toggle="tab">新增</a></li>
					</ul>
				</div>
			</div>

			<div class="widget-body">
				<div class="widget-main">
					<div class="tab-content">
						<div id="edit" class="tab-pane in active">
							<form id="form-useredit" action="" class="clearfix form-inline" role="form">
								<div class="col-xs-6">
									<div class="input-group width-100">
										<input type="text" class="hidden" name="id" /> <label for="username-edit">用户名：</label> <input type="text" class="form-control" name="username" id="username-edit" placeholder="用户名" title="用户名" />
									</div>
									<div class="input-group width-100">
										<label for="nickname-edit">昵称：</label> <input type="text" class="form-control" name="nickname" id="nickname-edit" placeholder="昵称" title="昵称" />
									</div>
									
									<div class="input-group width-100">
										<label for="comment-edit">备注：</label> <input type="text" class="form-control" name="comment" id="comment-edit" placeholder="备注" title="备注" />
									</div>
								</div>
								<div class="col-xs-6">
									<div class="input-group width-100">
										<label for="orgid-edit">组织：</label> <select class="chosen-select form-control" id="orgid-edit" name="orgid" title="组织" style="height:33px">
											<option value="0">选择组织</option>
										</select>
									</div>
									<div class="input-group width-100">
										<label for="roleid-edit">角色：</label> <select class="chosen-select form-control" id="roleid-edit" name="roleid" title="角色" style="height:33px">
											<option value="0">选择角色</option>
										</select>
									</div>
									
									<div class="input-group width-100">
									<br/>
										<input type="button" value="保存" class="btn btn-success margin-10 " onclick="editUser()" />
									</div>

								</div>
							</form>
						</div>

						<div id="add" class="tab-pane">
							<form id="form-useradd" action="" class="clearfix form-inline" role="form">
								<div class="col-xs-6">
									<div class="input-group width-100">
										<input type="text" class="hidden" name="id" /> <label for="username-edit">用户名：</label> <input type="text" class="form-control" name="username" id="username-edit" placeholder="用户名" title="用户名" />
									</div>
									<div class="input-group width-100">
										<label for="nickname-edit">昵称：</label> <input type="text" class="form-control" name="nickname" id="nickname-edit" placeholder="昵称" title="昵称" />
									</div>
									
									<div class="input-group width-100">
										<label for="comment-edit">备注：</label> <input type="text" class="form-control" name="comment" id="comment-edit" placeholder="备注" title="备注" />
									</div>
								</div>
								<div class="col-xs-6">
									<div class="input-group width-100">
										<label for="orgid-edit">组织：</label> <select class="chosen-select form-control" id="orgid-edit" name="orgid" title="组织" style="height:33px">
											<option value="0">选择组织</option>
										</select>
									</div>
									<div class="input-group width-100">
										<label for="roleid-edit">角色：</label> <select class="chosen-select form-control" id="roleid-edit" name="roleid" title="角色" style="height:33px">
											<option value="0">选择角色</option>
										</select>
									</div>

									<div class="input-group">
										<br /> <input type="reset" value="重置" class="btn btn-primary margin-10" />&nbsp;&nbsp; <input type="button" value="保存" class="btn btn-success margin-10" onclick="saveUser()" />
									</div>
								</div>

							</form>
						</div>
					</div>
				</div>
			</div>
		</div>


		<!-- PAGE CONTENT ENDS -->
	</div>
	<!-- /.col -->
</div>
<!-- /.row -->

<!-- page specific plugin scripts -->
<script type="text/javascript">
	var scripts = [ null, null ];
	$('.page-content-area').ace_ajax('loadScripts', scripts, function() {
		//inline scripts related to this page
	});
	var org = ${obj['orgmap']};//{"key1":{id:"1","name":"org1"},"key2":{id:"2","name":"org2"}}
	var role = ${obj['rolemap']};

	var content = "#userlist";
	function listUser(content) {
		$(content + " .widget-body")
				.html(
						'<h4 class="center">&nbsp;&nbsp;加载中&nbsp;&nbsp;<i class="ace-icon fa fa-spinner fa-spin orange bigger-125"></i></h4>');

		$.get("sysmgr/user/list", function(data) {
			setTimeout(function() {
				$(content + " .widget-body").html(data);
			}, 0);
		});
	}
	listUser(content);
	function saveUser() {
		$.post("sysmgr/user/add", $("#form-useradd").serialize(),
				function(data) {
					if (data == "true")
						listUser(content);
					else
						alert("添加失败！");
				});
	}
	function delUser(id) {
		if (confirm("确认删除该用户吗？")) {
			$.post("sysmgr/user/del", {
				id : id
			}, function(data) {
				if (data == "true")
					listUser(content);
				else
					alert("添加失败！");
			});
		}
	}
	
	function resetUser(id) {
		if (confirm("确认重置该用户密码吗？")) {
			$.post("sysmgr/user/reset", {
				id : id
			}, function(data) {
				if (data == "true")
					listUser(content);
				else
					alert("操作失败！");
			});
		}
	}
	
	function editUser() {
		$.post("sysmgr/user/edit", $("#form-useredit").serialize(), function(
				data) {
			if (data == "true")
				listUser(content);
			else
				alert("保存失败！");
		});
	}

	$.each(org, function(index, item) {
		var $option = $("<option>").val(item["id"]).text(item["orgname"]);
		$("select[name='orgid']").append($option);
	});

	$.each(role, function(index, item) {
		var $option = $("<option>").val(item["id"]).text(item["rolename"]);
		$("select[name='roleid']").append($option);
	});
</script>

