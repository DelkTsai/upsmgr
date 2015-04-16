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
<style type="text/css">
#device-info option {
	height: 50px;
}
</style>
</head>

<body>

	<%@include file="share/navbar.jsp"%>

	<script type="text/javascript">
		activeMenu("home");
	</script>

	<div class="container-fluid">
		<div class="row">
			<div class="col-xs-12 main" id="home">
				<h2 class="page-header text-primary">
					仪表盘&nbsp;&nbsp;<select class="form-control " id="device-info"
						v-model="selected" options="options" placeholder="设备"
						style="display: inline-block;width:100%;max-width: 365px;"
						onchange="select_change(this)"></select>
				</h2>
				<div class="row placeholders">
					<div class="col-xs-12" id="chart" style="height: 300px;"></div>
				</div>

				<h3 class="sub-header text-primary">设备历史数据</h3>
				<div class="table-responsive">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>#</th>
								<th>设备编号</th>
								<th>输出电压</th>
								<th>电池电压</th>
								<th>负载</th>
								<th>数据时间</th>
								<th>通信方式</th>
								<th>备注</th>
							</tr>
						</thead>
						<tbody>
							<tr v-repeat="page.list">
								<td>{{(page.pager.pageNumber-1)*page.pager.pageSize+$index+1}}</td>
								<td>{{deviceId}}</td>
								<td>{{outputVoltage}}</td>
								<td>{{batteryVoltage}}</td>
								<td>{{batteryLoad}}</td>
								<td>{{dataTime}}</td>
								<td>{{communicateMethod==0?'WIFI':'RS232'}}</td>
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

	<script src="assets/js/echarts-all.js"></script>

	<script type="text/javascript">
		function getLabel() {
			var label = [];
			for (var i = 0; i < 25; i++) {
				label.push('' + i + ":00");
			}
			return label;
		}

		function data_chart() {
			$.getJSON("devdata/chart", {
				deviceId : vue.selected
			}, function(data) {
				vue.chart.chartData = getChartData(data.list);
				refresh_chart();
			});
		};

		function data_list(pager) {
			$.getJSON("devdata/list", $.extend(pager, {
				deviceId : vue.selected
			}), function(data) {
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

		function getChartData(chartData) {
			var outputVoltage = [];
			var batteryVoltage = [];
			var batteryLoad = [];

			$(chartData).each(function(index, item) {
				outputVoltage.push(item.outputVoltage);
				batteryVoltage.push(item.batteryVoltage);
				batteryLoad.push(item.batteryLoad);
			});
			return {
				outputVoltage : outputVoltage,
				batteryVoltage : batteryVoltage,
				batteryLoad : batteryLoad
			};
		}

		function getOption() {
			var op = [];
			$(data.devinfo).each(
					function(index, item) {
						op.push({
							text : "设备编号："
									+ item.deviceId
									+ " | "
									+ "通信方式："
									+ (item.communicateMethod == 0 ? "WIFI"
											: "RS232") + " | " + "状态："
									+ (item.status == 0 ? "正常" : "异常"),
							value : item.deviceId
						});
					});
			return op;
		}

		var vue = new Vue(
				{
					el : "#home",
					data : {
						selected : "100001",
						options : getOption(),
						chart : {
							option : {
								tooltip : {
									trigger : 'axis'
								},
								legend : {
									data : [ '输出电压', '电池电压', '负载' ]
								},
								toolbox : {
									show : false,
									feature : {
										mark : {
											show : true
										},
										dataView : {
											show : true,
											readOnly : false
										},
										magicType : {
											show : true,
											type : [ 'line', 'bar', 'stack',
													'tiled' ]
										},
										restore : {
											show : true
										},
										saveAsImage : {
											show : true
										}
									}
								},
								calculable : true,
								xAxis : [ {
									type : 'category',
									boundaryGap : false,
									data : getLabel()
								} ],
								yAxis : [ {
									type : 'value'
								} ],
								series : [ {
									name : '输出电压',
									type : 'line',
									stack : '总量',
									//data : outputVoltage
									data : []
								}, {
									name : '电池电压',
									type : 'line',
									stack : '总量',
									//data : batteryVoltage
									data : []
								}, {
									name : '负载',
									type : 'line',
									stack : '总量',
									//data : batteryLoad
									data : []
								} ]
							},
							myChart : echarts.init(document
									.getElementById("chart")),
							chartData : getChartData(data.list)
						},
						page : data
					},
					methods : {
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
	</script>

	<script type="text/javascript">
		function refresh_chart() {
			vue.chart.option.series[0].data = vue.chart.chartData.outputVoltage;
			vue.chart.option.series[1].data = vue.chart.chartData.batteryVoltage;
			vue.chart.option.series[2].data = vue.chart.chartData.batteryLoad;
			vue.chart.myChart.setOption(vue.chart.option);
		}

		refresh_chart();

		window.onresize = vue.chart.myChart.resize;

		function select_change(obj) {
			vue.selected = $(obj).val();
			data_chart();
			data_list({
				pageNumber : 1,
				pageSize : vue.page.pager.pageSize
			});
		};
	</script>


</body>
</html>