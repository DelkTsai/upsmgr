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
		activeMenu("sys/menu");
	</script>

	<div class="container-fluid">
		<div class="row" id="menu">
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
											v-model="form.menuLink" value="{{isEdit&form.pmenu!=0?'':'#'}}">
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
					<button class="btn btn-success" v-on="click:data_add">
						<i class="fa fa-plus"></i> 新增
					</button>
				</div>
				<div class="table-responsive">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>#</th>
								<th>菜单类型</th>
								<th>菜单名</th>
								<th>显示名称</th>
								<th>菜单图标</th>
								<th>URL</th>
								<th>状态</th>
							</tr>
						</thead>
						<tbody v-repeat="menu:page.list">
							<tr>
								<td><a title="编辑" href="javascript:;"
									v-on="click:data_edit(menu)"><i
										class="fa fa-edit  text-success fa-lg"></i></a> &nbsp;&nbsp; <a
									title="删除" v-on="click:data_delete(menu)" href="javascript:;"><i
										class="fa fa-trash fa-lg text-danger"></i></a> &nbsp;&nbsp; <template
										v-if="menu.hasChild"> <a title="展开"
										v-on="click:toggle(menu)" href="javascript:;"><i
										class="fa fa-{{menu.expand?'minus':'plus'}}"></i></a> </template> <template
										v-if="!menu.hasChild"> <a title="展开"
										href="javascript:;"><i class="fa fa-minus"></i></a> </template>
									&nbsp;&nbsp;&nbsp;{{$index+1}}</td>
								<td class="text-primary" style="font-weight: bold;">{{menu.pmenu==0?'顶级菜单':''}}</td>
								<td>{{menu.menuName}}</td>
								<td>{{menu.menuText}}</td>
								<td><i class="{{menu.menuIcon}}"></i>&nbsp;&nbsp;{{menu.menuIcon}}</td>
								<td>{{menu.menuLink}}</td>
								<td>{{menu.status}}</td>
							</tr>

							<tr v-repeat="sub: menu.subMenus" v-show="menu.expand">
								<td class="text-center"><a title="编辑" href="javascript:;"
									v-on="click:data_edit(sub)"><i
										class="fa fa-edit  text-success fa-lg"></i></a> &nbsp;<a
									title="删除" v-on="click:data_delete(sub)" href="javascript:;"><i
										class="fa fa-trash fa-lg text-danger"></i></a>&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;{{$index+1}}</td>
								<td>子菜单</td>
								<td>{{sub.menuName}}</td>
								<td>{{sub.menuText}}</td>
								<td><i class="{{sub.menuIcon}}"></i>&nbsp;&nbsp;{{sub.menuIcon}}</td>
								<td>{{sub.menuLink}}</td>
								<td>{{sub.status}}</td>
							</tr>
						<tr v-show="page.list.length<1"><td align="center" colspan="7">无数据</td></tr>
						</tbody>
						<tfoot>
							<tr>
							</tr>
						</tfoot>
					</table>
				</div>
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
			$.getJSON("/sys/menu/list", $.extend(pager, vue.condition),
					function(data) {
						vue.page = data;
					});
		};

		var vue = new Vue({
			el : "#menu",
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
					$.post(this.isEdit ? "/sys/menu/edit" : "/sys/menu/add",
							this.form, function(data) {
								showSuccess(data.ok, data.msg);
							}, "json");
				},

				data_delete : function(obj) {
					if (confirm("确认删除菜单：" + obj.menuText)) {
						$.post("/sys/menu/delete", obj, function(data) {
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