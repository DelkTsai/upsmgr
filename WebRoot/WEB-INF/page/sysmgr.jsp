<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<base href="${base}">
<title>首页-UPS管理系统</title>
<%@include file="share/header.jsp"%>
</head>

<body>

	<%@include file="share/navbar.jsp"%>

	<div class="container-fluid">
		<div class="row" id="user">
			<div class="col-xs-12">

				<%@include file="share/dialog.jsp"%>

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
										<label for="status">状态</label>
											<template v-if="form.status==0">
										<input class="form-control" id="status" type="checkbox" checked>
									</template>
									<template v-if="form.status!=0">
										<input class="form-control" id="status" type="checkbox">
									</template>
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

			<div class="col-sm-1 sidebar">
				<ul class="nav nav-sidebar">
					<li class="active"><a href="javascript:;">用户管理<span
							class="sr-only"></span></a></li>
					<!-- 
					<li><a href="#">角色管理<span class="sr-only"></span></a></li>
					<li><a href="#"> 组织管理<span class="sr-only"></span></a></li>
					<li><a href="#"> 菜单管理<span class="sr-only"></span></a></li>
					 -->
				</ul>
			</div>

			<div class="col-xs-12 col-sm-11 col-sm-offset-1 main">
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
							<tr v-repeat="page.data">
								<td><a title="编辑" href="javascript:;"
									v-on="click:data_edit((page.pager.pageNumber-1)*page.pager.pageSize+$index)"><i
										class="fa fa-edit  text-success fa-lg"></i></a> &nbsp;<a
									title="删除"
									v-on="click:data_delete((page.pager.pageNumber-1)*page.pager.pageSize+$index)"
									href="javascript:;"><i
										class="fa fa-trash fa-lg text-danger"></i></a>
									&nbsp;&nbsp;&nbsp;{{(page.pager.pageNumber-1)*page.pager.pageSize+$index+1}}</td>
								<td>{{username}}</td>
								<td>{{nickname}}</td>
								<td>{{getRole(roleid)}}</td>
								<td>{{createTime}}</td>
								<td>{{updateTime}}</td>
								<td>
									<template v-if="status==0">
										<input type="checkbox" checked>
									</template>
									<template v-if="status!=0">
										<input type="checkbox">
									</template>
								</td>
								<td>{{comment}}</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="8"><%@include file="share/pager.jsp"%></td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
		</div>
	</div>

	<%@include file="share/footer.jsp"%>

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

		var vue = new Vue(
				{
					el : "#user",
					data : {
						isEdit : false,
						roleOptions : [ {
							text : '全部',
							value : '-1'
						}, {
							text : '未分配',
							value : '0'
						}, {
							text : '测试员',
							value : '1'
						}, {
							text : '管理员',
							value : '2'
						} ],
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
						page : data
					},
					methods : {
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

						data_delete : function(rowid) {
							if (confirm("确认删除设备："
									+ this.page.data[rowid].username)) {
								$.post("sys/user/delete",
										this.page.data[rowid], function(data) {
											showSuccess(data.isSuccess,
													data.msg);
										}, "json");
							}
						},

						data_edit : function(rowid) {
							$('#myModal').modal('show');
							this.form = clone(this.page.data[rowid]);
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

		var navbar = new Vue({
			el : "#navbar",
			data : {
				nav : [ {
					href : "home",
					text : "仪表盘",
					icon : "fa fa-dashboard",
					active : false
				}, {
					href : "dev",
					text : "设备管理",
					icon : "fa fa-laptop",
					active : false
				}, {
					href : "sys/user",
					text : "系统管理",
					icon : "fa fa-cog",
					active : true
				} ]
			}
		});
		$("input[type=\"checkbox\"], input[type=\"radio\"]").not(
				"[data-switch-no-init]").bootstrapSwitch("size", "mini");
	</script>
</body>
</html>