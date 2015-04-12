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
<%@include file="share/header.jsp"%>
</head>
<body>

	<%@include file="share/navbar.jsp"%>

	<div class="container-fluid">
		<div class="row" id="sys">
			<div class="col-xs-12">

				<%@include file="share/dialog.jsp"%>

				<!-- Modal -->
				<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title" id="myModalLabel">
									<span v-show="!show"><i class="fa fa-plus"></i>&nbsp;新增</span><span v-show="show"><i class="fa fa-edit"></i>&nbsp;编辑</span>
								</h4>
							</div>
							<div class="modal-body">
								<form id="dev-editor">
									<div class="form-group">
										<label for="deviceId">设备编号</label> <input type="text" disabled="{{show?'disabled':''}}" class="form-control" id="deviceId" placeholder="设备编号" v-model="form.deviceId">
									</div>
									<div class="form-group">
										<label for="communicateMethod">通讯方式</label> <input type="text" class="form-control" id="communicateMethod" placeholder="通讯方式" v-model="form.communicateMethod">
									</div>
									<div class="form-group">
										<label for="comment">备注</label> <input type="text" class="form-control" id="comment" placeholder="备注" v-model="form.comment">
									</div>
								</form>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-danger" data-dismiss="modal">
									<i class="fa fa-close"></i>&nbsp;关闭
								</button>
								<button type="button" class="btn btn-success" v-on="click:data_save">
									<i class="fa fa-save"></i>&nbsp;保存
								</button>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="col-xs-12 main">
				<div class="form-inline page-header">
					<select class="form-control" v-model="condition.status" options="statusOptions" style="width:100px;display: inline-block;"></select>
					 <input type="text" class="form-control" v-model="condition.deviceId" placeholder="设备编号" style="width:100px;display: inline-block;" />

					<button class="btn btn-success" v-on="click:search">
						<i class="fa fa-search"></i>
					</button>
					<button type="button" class="btn btn-success pull-right" v-on="click:data_add">
						<i class="fa fa-plus"></i>&nbsp;新增
					</button>
				</div>
				<div class="table-responsive">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>#</th>
								<th>设备编号</th>
								<th>安装时间</th>
								<th>通信方式</th>
								<th>工作状态</th>
								<th>备注</th>
							</tr>
						</thead>
						<tbody>
							<tr v-repeat="page.data">
								<td><a title="编辑" href="javascript:;" v-on="click:data_edit((page.pager.pageNumber-1)*page.pager.pageSize+$index)"><i class="fa fa-edit  text-success fa-lg"></i></a> &nbsp;<a title="删除" v-on="click:data_delete((page.pager.pageNumber-1)*page.pager.pageSize+$index)" href="javascript:;"><i class="fa fa-trash fa-lg text-danger"></i></a> &nbsp;&nbsp;&nbsp;{{(page.pager.pageNumber-1)*page.pager.pageSize+$index+1}}</td>
								<td>{{deviceId}}</td>
								<td>{{installTime}}</td>
								<td>{{communicateMethod==0?'WIFI':'RS232'}}</td>
								<td>{{status==0?'正常':'异常'}}</td>
								<td>{{comment}}</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="7"><%@include file="share/pager.jsp"%></td>
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
					pageSize : 5,
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
			$.getJSON("dev/list", $.extend(pager,vue.condition), function(data) {
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

		var vue = new Vue({
			el : "#sys",
			data : {
				show : false,
				statusOptions : [ {
					text : '全部',
					value : '-1'
				}, {
					text : '正常',
					value : '0'
				}, {
					text : '异常',
					value : '1'
				} ],
				form : {
					deviceId : 100000,
					communicateMethod : 0,
					comment : "测试数据"
				},
				condition : {
					status : "-1",
					deviceId : ""
				},
				header : {
					index : "#",
					deviceId : "设备编号",
					installTime : "安装时间",
					communicateMethod : "通讯方式",
					status : "工作状态",
					comment : "备注"
				},
				page : ${obj}
			},
			methods : {
				search : function() {
					data_list({
						pageNumber : 1,
						pageSize : this.page.pager.pageSize
					});
				},
				data_save : function() {
					if (!this.show) {
						$.post("dev/add", this.form, function(data) {
							showSuccess(data.isSuccess, data.msg);
						}, "json");
					} else {
						$.post("dev/edit", this.form, function(data) {
							showSuccess(data.isSuccess, data.msg);
						}, "json");
					}
				},

				data_edit : function(rowid) {
					$('#myModal').modal('show');
					this.form = clone(this.page.data[rowid]);
					this.show = true;
				},
				data_add : function() {
					$('#myModal').modal('show');
					$("#dev-editor")[0].reset();
					this.show = false;
				},
				data_delete : function(rowid) {
					if (confirm("确认删除设备：" + this.page.data[rowid].deviceId)) {
						$.post("dev/delete", this.page.data[rowid], function(
								data) {
							showSuccess(data.isSuccess, data.msg);
						}, "json");
					}
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
					active : true
				}, {
					href : "sys/user",
					text : "系统管理",
					icon : "fa fa-cog",
					active : false
				} ]
			}
		});
	</script>
</body>
</html>