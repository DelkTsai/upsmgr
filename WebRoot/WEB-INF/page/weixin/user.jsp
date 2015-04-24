<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<base href="${base}/">
<title>首页-UPS管理系统</title>
<%@include file="../share/header.jsp"%>
</head>

<body>

	<%@include file="../share/navbar.jsp"%>

	<script type="text/javascript">
		activeMenu("weixin/user");
	</script>

	<div class="container-fluid">
		<div class="row" id="user">
			<div class="col-xs-12">

				<%@include file="../share/dialog.jsp"%>

				<!-- Modal -->
				<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title" id="myModalLabel">
									<span v-show="!isEdit"><i class="fa fa-plus"></i>&nbsp;新增</span><span
										v-show="isEdit"><i class="fa fa-edit"></i>&nbsp;编辑</span>
								</h4>
							</div>
							<div class="modal-body">
								<form id="menu-editor">
									<div class="form-group">
										<label for="pmenu">父菜单</label> <select class="form-control"
											id="pmenu" placeholder="父菜单" options="pmenuOptions"
											v-model="form.pmenu"></select>
									</div>
									<div class="form-group">
										<label for="menuName">菜单名</label> <input type="text"
											class="form-control" id="menuName" placeholder="菜单名"
											v-model="form.menuName">
									</div>
									<div class="form-group">
										<label for="menuText">显示名称</label> <input type="text"
											class="form-control" id="menuText" placeholder="显示名称"
											v-model="form.menuText">
									</div>
									<div class="form-group">
										<label for="menuIcon">菜单图标 &nbsp;&nbsp;<i
											class="{{form.menuIcon}}"></i>&nbsp;&nbsp;<a target="blank"
											href="http://fontawesome.dashgame.com/#new">图标参考</a></label> <input
											type="text" class="form-control" id="menuIcon"
											v-model="form.menuIcon" placeholder="菜单图标">
									</div>
									<div class="form-group">
										<label for="menuLink">URL</label> <input type="text"
											class="form-control" id="menuLink" placeholder="链接"
											disabled="{{form.hasChild&isEdit?'disabled':''}}"
											v-model="form.menuLink"
											value="{{isEdit&form.pmenu!=0?'':'#'}}">
									</div>

								</form>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-danger"
									data-dismiss="modal">
									<i class="fa fa-close"></i>&nbsp;关闭
								</button>
								<button type="button" class="btn btn-success"
									v-on="click:data_save">
									<i class="fa fa-save"></i>&nbsp;保存
								</button>
							</div>
						</div>
					</div>
				</div>

			</div>


			<div class="col-xs-12 main">
				<div class="page-header">
				
				</div>
				<ul class="list-group col-sm-6 col-md-4 col-lg-2"  v-repeat="user:page.list">
					<li class="list-group-item"><img alt="头像" src="{{user.headimgurl}}" width="120" height="120" align="middle"></li>
					<li class="list-group-item">{{user.nickname}}</li>
					<li class="list-group-item">{{user.sex==0?"女":"男"}}</li>
					<li class="list-group-item">{{user.language=="zh_CN"?"中文":"外语"}}</li>
					<li class="list-group-item">{{user.province}} {{user.city}}</li>
					<li class="list-group-item">{{user.country}}</li>
				</ul>
			</div>
		</div>
	</div>

	<%@include file="../share/footer.jsp"%>

	<script type="text/javascript">
		$("#myModal").modal({
			backdrop : false,
			show : false
		});
		function showSuccess(success, msg) {
			if (success) {
				$("#save-success .msg").text(msg);
				$("#save-success").fadeIn(600);
				data_list({
					pageNumber : 1,
					pageSize : 5
				});
				setTimeout(function() {
					$("#save-success").fadeOut(600);
				}, 2500);
			} else {
				$("#save-failure .msg").text(msg);
				$("#save-failure").fadeIn(600);
				setTimeout(function() {
					$("#save-failure").fadeOut(600);
				}, 2500);
			}
		}

		function data_list(pager) {
			$.getJSON("sys/menu/list", $.extend(pager, vue.condition),
					function(data) {
						vue.page = data;
					});
		};

		var vue = new Vue({
			el : "#user",
			data : {
				isEdit : false,
				form : {
					menuName : "test",
					menuText : "1",
					menuIcon : "",
					menuLink : "",
					status : "",
					pmenu : ""
				},
				condition : {
					username : null,
					roleid : -1
				},
				header : {
					index : "#",
					pmenu : "父菜单",
					menuName : "菜单名",
					menuText : "显示名称",
					menuIcon : "菜单图标",
					menuLink : "链接",
					status : "状态"
				},
				page : data,
				pmenuOptions : (function() {
					var opt = [];
					opt.push({
						text : "顶级菜单",
						value : 0
					});
					$(data["list"]).each(function(index, item) {
						if (item["pmenu"] == 0) {
							opt.push({
								text : item["menuText"],
								value : item["id"]
							});
						}
					});
					return opt;
				})()
			},
			methods : {
				toggle : function(item) {
					item.expand = !item.expand;
				},
				getPmenu : function() {
					var text = "未知";
					$(this.orgOptions).each(function(index, item) {
						if (item.value == roleid)
							text = item.text;
					});
					return text;
				},
				search : function() {
					data_list({
						pageNumber : 1,
						pageSize : this.page.pager.pageSize
					});
				},
				data_save : function() {
					$.post(this.isEdit ? "sys/menu/edit" : "sys/menu/add",
							this.form, function(data) {
								showSuccess(data.ok, data.msg);
							}, "json");
				},

				data_delete : function(obj) {
					if (confirm("确认删除菜单：" + obj.menuText)) {
						$.post("sys/menu/delete", obj, function(data) {
							showSuccess(data.ok, data.msg);
						}, "json");
					}
				},

				data_edit : function(obj) {
					$('#myModal').modal('show');
					this.form = clone(obj);
					this.isEdit = true;
				},
				data_add : function() {
					$('#myModal').modal('show');
					$("#menu-editor")[0].reset();
					this.isEdit = false;
				}
			}

		});

		$("input[type=\"checkbox\"], input[type=\"radio\"]").not(
				"[data-switch-no-init]").bootstrapSwitch("size", "mini");
	</script>
</body>
</html>