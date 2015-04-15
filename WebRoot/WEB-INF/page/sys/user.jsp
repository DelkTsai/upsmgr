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
		activeMenu("sys/user");
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
									<span v-show="!show"><i class="fa fa-plus"></i>&nbsp;新增</span><span
										v-show="show"><i class="fa fa-edit"></i>&nbsp;编辑</span>
								</h4>
							</div>
							<div class="modal-body">
								<form id="user-editor">
									<div class="form-group">
										<label for="username">用户名</label> <input type="text"
											class="form-control" disabled="{{show?'disabled':''}}"
											id="username" placeholder="用户名" v-model="form.username">
									</div>
									<div class="form-group">
										<label for="nickname">昵称</label> <input type="text"
											class="form-control" id="nickname" placeholder="昵称"
											v-model="form.nickname">
									</div>
									<div class="form-group">
										<label for="roleid">角色</label> <select class="form-control"
											id="roleid" v-model="form.roleid" options="roleOptions"
											placeholder="角色"></select>
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
				<div class="form-inline page-header">
					<select class="form-control" v-model="condition.roleid"
						options="roleOptions" style="width:100px;display: inline-block;"></select>
					<input type="text" class="form-control"
						v-model="condition.username" placeholder="用户名"
						style="width:100px;display: inline-block;" />
					<button class="btn btn-success">
						<i class="fa fa-search" v-on="click:search"></i>
					</button>
					<button class="btn btn-success pull-right" v-on="click:data_add">
						<i class="fa fa-plus"></i> 新增
					</button>
				</div>
				<div class="table-responsive">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>#</th>
								<th>用户名</th>
								<th>昵称</th>
								<th>角色</th>
								<th>创建时间</th>
								<th>更新时间</th>
								<th>状态</th>
								<th>备注</th>
							</tr>
						</thead>
						<tbody>
							<tr v-repeat="user:page.list">
								<td><a title="编辑" href="javascript:;"
									v-on="click:data_edit(user)"><i
										class="fa fa-edit  text-success fa-lg"></i></a> &nbsp;<a
									title="删除"
									v-on="click:data_delete(user)"
									href="javascript:;"><i
										class="fa fa-trash fa-lg text-danger"></i></a>
									&nbsp;&nbsp;&nbsp;{{(page.pager.pageNumber-1)*page.pager.pageSize+$index+1}}</td>
								<td>{{user.username}}</td>
								<td>{{user.nickname}}</td>
								<td>{{getRole(user.roleid)}}</td>
								<td>{{user.createTime}}</td>
								<td>{{user.updateTime}}</td>
								<td>
									<template v-if="status==0">
										<input type="checkbox" checked>
									</template>
									<template v-if="status!=0">
										<input type="checkbox">
									</template>
								</td>
								<td>{{user.comment}}</td>
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
			$.getJSON("sys/user/list", $.extend(pager, vue.condition),
					function(data) {
						vue.page = data["page"];
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
					el : "#user",
					data : {
						isEdit : false,
						roleOptions :(function(){
							var opt = [];
							opt.push({text:"全部",value:-1});
							$(data["roles"]).each(function(index,item){
								opt.push({text:item["rolename"],value:item["id"]});
							});
							return opt;
						})(),
						form : {
							username : "test",
							nickname : "测试员",
							roleid : 0,
							status : 0,
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
						page : data["page"]
					},
					methods : {
						getOption: function(roles){
							var opt = [];
							$(roles).each(function(index,item){
								opt.push({text:item["rolename"],value:item["id"]});
							});
							return opt;
						},
						getRole : function(roleid) {
							var text = "未知";
							$(this.roleOptions).each(function(index, item) {
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
								$.post("sys/user/add", this.form,
										function(data) {
											showSuccess(data.isSuccess,
													data.msg);
										}, "json");
							} else {
								$.post("sys/user/edit", this.form, function(
										data) {
									showSuccess(data.isSuccess, data.msg);
								}, "json");
							}
						},
						data_delete : function(obj) {
							if (confirm("确认删除用户："
									+obj.username)) {
								$.post("sys/user/delete",obj, function(data) {
											showSuccess(data.isSuccess,
													data.msg);
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
							$("#user-editor")[0].reset();
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