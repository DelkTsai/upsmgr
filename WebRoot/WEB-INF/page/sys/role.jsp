<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<base href="<%=path%>/">
<title>首页-UPS管理系统</title>
<%@include file="../share/header.jsp"%>
</head>

<body>

	<%@include file="../share/navbar.jsp"%>
	
	<script type="text/javascript">
		activeMenu("sys/role");
	</script>
	
	<div class="container-fluid">
		<div class="row" id="role">
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
									<span v-show="!show"><i class="fa fa-plus"></i>&nbsp;新增</span><span
										v-show="show"><i class="fa fa-edit"></i>&nbsp;编辑</span>
								</h4>
							</div>
							<div class="modal-body">
								<form id="role-editor">
									<div class="form-group">
										<label for="rolename">角色名</label> <input type="text"
											class="form-control" disabled="{{show?'disabled':''}}"
											id="rolename" placeholder="角色名" v-model="form.rolename">
									</div>

									<div class="form-group">
										<label for="orgid">组织</label> <select class="form-control"
											id="orgid" v-model="form.orgid" options="orgOptions"
											placeholder="组织"></select>
									</div>
									<div class="form-group">
										<label for="comment">备注</label> <input type="text"
											class="form-control" id="comment" placeholder="备注"
											v-model="form.comment">
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
								<th>角色名</th>
								<th>组织</th>
								<th>新增权限</th>
								<th>删除权限</th>
								<th>更改权限</th>
								<th>查看权限</th>
								<th>备注</th>
							</tr>
						</thead>
						<tbody>
							<tr v-repeat="role:page.list">
								<td><a title="编辑" href="javascript:;"
									v-on="click:data_edit(role)"><i
										class="fa fa-edit  text-success fa-lg"></i></a> &nbsp;<a
									title="删除" v-on="click:data_delete(role)" href="javascript:;"><i
										class="fa fa-trash fa-lg text-danger"></i></a>
									&nbsp;&nbsp;&nbsp;{{(page.pager.pageNumber-1)*page.pager.pageSize+$index+1}}</td>
								<td>{{role.rolename}}</td>
								<td>{{getRole(role.orgid)}}</td>
								<td><button class="btn btn-warning btn-xs">
										<i class="fa fa-plus"></i>
									</button></td>
								<td><button class="btn btn-warning btn-xs">
										<i class="fa fa-trash"></i>
									</button></td>
								<td><button class="btn btn-warning btn-xs">
										<i class="fa fa-edit"></i>
									</button></td>
								<td><button class="btn btn-warning btn-xs">
										<i class="fa fa-eye"></i>
									</button></td>
								<td>{{role.comment}}</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="8"><%@include file="../share/pager.jsp"%></td>
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
			$.getJSON("sys/role/list", $.extend(pager, vue.condition),
					function(data) {
						vue.page = data;
					});
		};

		function size_change(obj) {
			vue.page.pager.pageSize = $(obj).val();
			data_list({
				pageNumber : 1,
				pageSize : vue.page.pager.pageSize
			});
		};

		function number_change(obj) {
			if ($(obj).val() > vue.page.pager.pageCount) {
				vue.page.pager.pageNumber = vue.page.pager.pageCount;
				$(obj).val(vue.page.pager.pageCount);
			} else if ($(obj).val() < 1) {
				vue.page.pager.pageNumber = 1;
				$(obj).val(1);
			} else {
				vue.page.pager.pageNumber = $(obj).val();
			}
			data_list({
				pageNumber : vue.page.pager.pageNumber,
				pageSize : vue.page.pager.pageSize
			});
		};
		var data = ${obj};
		var vue = new Vue(
				{
					el : "#role",
					data : {
						isEdit : false,
						orgOptions : [ {
							text : '开发部',
							value : '1'
						}, {
							text : '测试部',
							value : '2'
						}, {
							text : '财务部',
							value : '3'
						}, {
							text : '行政部',
							value : '4'
						} ],
						form : {
							rolename : "test",
							orgid : "1",
							comment : "测试数据"
						},
						condition : {
							username : null,
							roleid : -1
						},
						header : {
							index : "#",
							usericeId : "设备编号",
							installTime : "安装时间",
							communicateMethod : "通讯方式",
							status : "工作状态",
							comment : "备注"
						},
						page : data
					},
					methods : {
						getRole : function(roleid) {
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
							if (!this.isEdit) {
								$.post("sys/role/add", this.form,
										function(data) {
											showSuccess(data.isSuccess,
													data.msg);
										}, "json");
							} else {
								$.post("sys/role/edit", this.form, function(
										data) {
									showSuccess(data.isSuccess, data.msg);
								}, "json");
							}
						},

						data_delete : function(obj) {
							if (confirm("确认删除角色：" + obj.rolename)) {
								$.post("sys/role/delete", obj, function(data) {
									showSuccess(data.isSuccess, data.msg);
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
							$("#role-editor")[0].reset();
							this.isEdit = false;
						},

						nextPage : function() {
							if (this.page.pager.pageNumber == this.page.pager.pageCount)
								return;
							data_list({
								pageNumber : this.page.pager.pageNumber + 1,
								pageSize : this.page.pager.pageSize
							});
						},
						previousPage : function() {
							if (this.page.pager.pageNumber == 1)
								return;
							data_list({
								pageNumber : this.page.pager.pageNumber - 1,
								pageSize : this.page.pager.pageSize
							});
						},
						firstPage : function() {
							if (this.page.pager.pageNumber == 1)
								return;
							data_list({
								pageNumber : 1,
								pageSize : this.page.pager.pageSize
							});
						},
						lastPage : function() {
							if (this.page.pager.pageNumber == this.page.pager.pageCount)
								return;
							data_list({
								pageNumber : this.page.pager.pageCount,
								pageSize : this.page.pager.pageSize
							});
						}
					}

				});

		$("input[type=\"checkbox\"], input[type=\"radio\"]").not(
				"[data-switch-no-init]").bootstrapSwitch("size", "mini");
	</script>
</body>
</html>