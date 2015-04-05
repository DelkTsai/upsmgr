<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<title>组织管理| Admin管理系统</title>

<!-- ajax layout which only needs content area -->
<div class="row">
	<div class="col-xs-12 no-padding">
		<!-- PAGE CONTENT BEGINS -->

		<div id="rolelist" class="widget-box widget-color-blue2 ">
			<div class="widget-header widget-header-small widget-header-flat"
				style="padding:5px 10px">
				<div class="widget-title">
					检索： <select class="inline-block chosen-select" name="orgid"
						title="组织" style="height:32px;">
						<option>选择组织</option>
					</select>
					<button class="btn btn-sm btn-primary no-border" type="button"
						onclick="searchRole()">
						<i class="glyphicon glyphicon-search"></i>
					</button>
				</div>
				<div class="widget-toolbar">
					<a data-action="fullscreen" class="white" href="javascript:;">
						<i class="ace-icon fa fa-expand"></i>
					</a> 
					<!--a data-action="collapse" class="white" href="javascript:;"> <i
						class="ace-icon fa fa-chevron-up"></i>
					</a-->
					<a data-action="" class="" href="javascript:;"> <i>&nbsp;&nbsp;</i>
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
							<form id="form-roleedit" action="" class="clearfix form-inline"
								role="form">
								<div class="col-xs-6">
									<div class="input-group width-100">
										<input type="text" class="hidden" name="id" /> <label
											for="rolename-edit">角色名称：</label> <input type="text"
											class="form-control" name="rolename" id="rolename-edit"
											placeholder="角色名称" title="角色名称" />
									</div>
									<div class="input-group width-100">
										<label for="orgid-edit">组织：</label> <select
											class="chosen-select form-control" id="orgid-edit"
											name="orgid" title="组织" style="height:33px">
											<option value="0">选择组织</option>
										</select>
									</div>
								</div>
								<div class="col-xs-6">
									<div class="input-group width-100">
										<label for="comment-edit">备注：</label> <input type="text"
											class="form-control" name="comment" id="comment-edit"
											placeholder="备注" title="备注" />
									</div>

									<div class="input-group">
										<br /> <input type="button" value="保存"
											class="btn btn-success margin-10 " onclick="editRole()" />
									</div>
								</div>
							</form>
						</div>

						<div id="add" class="tab-pane">
							<form id="form-roleadd" action="" class="clearfix form-inline"
								role="form">
								<div class="col-xs-6">
									<div class="input-group width-100">
										<label for="rolename-edit">角色名称：</label> <input type="text"
											class="form-control" name="rolename" id="rolename-edit"
											placeholder="角色名称" title="角色名称" />
									</div>
									<div class="input-group width-100">
										<label for="orgid-edit">组织：</label> <select
											class="chosen-select form-control" id="orgid-add"
											name="orgid" title="组织" style="height:33px">
											<option value="0">选择组织</option>
										</select>
									</div>
								</div>
								<div class="col-xs-6">
									<div class="input-group width-100">
										<label for="comment-edit">备注：</label> <input type="text"
											class="form-control" name="comment" id="comment-edit"
											placeholder="备注" title="备注" />
									</div>
									<div class="input-group">
										<br /> <input type="reset" value="重置"
											class="btn btn-primary margin-10" />&nbsp;&nbsp; <input
											type="button" value="保存" class="btn btn-success margin-10"
											onclick="saveRole()" />
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
	
	$.each(org, function(index, item) {
		var $option = $("<option>").val(item["id"]).text(item["orgname"]);
		$("select[name='orgid']").append($option);
	});

	var content = "#rolelist";
	function listRole(content) {
		$(content + " .widget-body")
				.html(
						'<h4 class="center">&nbsp;&nbsp;加载中&nbsp;&nbsp;<i class="ace-icon fa fa-spinner fa-spin orange bigger-125"></i></h4>');

		$.get("sysmgr/role/list", function(data) {
			setTimeout(function() {
				$(content + " .widget-body").html(data);
			}, 0);
		});
	}
	listRole(content);
	function saveRole() {
		$.post("sysmgr/role/add", $("#form-roleadd").serialize(),
				function(data) {
					if (data == "true")
						listRole(content);
					else
						alert("添加失败！");
				});
	}
	function delRole(id) {
		if (confirm("确认删除要删除该用户吗？")) {
			$.post("sysmgr/role/del", {
				id : id
			}, function(data) {
				if (data == "true")
					listRole(content);
				else
					alert("添加失败！");
			});
		}

	}
	function editRole() {
		$.post("sysmgr/role/edit", $("#form-roleedit").serialize(), function(
				data) {
			if (data == "true")
				listRole(content);
			else
				alert("保存失败！");
		});
	}
	
	
	
</script>

