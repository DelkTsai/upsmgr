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
		<div class="row">
			<div class="col-xs-12 main" id="home">
				<h1 class="page-header text-primary">仪表盘</h1>
				<div class="row placeholders">
					<div class="col-xs-12" id="chart" style="height: 300px;"></div>
				</div>

				<h3 class="sub-header text-primary">设备实时信息</h3>
				<div class="table-responsive">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>#</th>
								<th>设备编号</th>
								<th>输出电压</th>
								<th>电池电压</th>
								<th>负载</th>
								<th>工作状态</th>
							</tr>
						</thead>
						<tbody>
							<tr v-repeat="tabelData.td">
								<td>{{$index+1}}</td>
								<td>{{deviceid}}</td>
								<td>{{outvoltage}}</td>
								<td>{{cellvoltage}}</td>
								<td>{{load}}</td>
								<td>{{status}}</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>

	<%@include file="share/footer.jsp"%>

	<script src="assets/js/echarts-all.js"></script>

	<script type="text/javascript">
		var navbar = new Vue({
			el : "#navbar",
			data : {
				nav : [ {
					href : "home",
					text : "仪表盘",
					icon : "fa fa-dashboard",
					active : true
				}, {
					href : "dev",
					text : "设备管理",
					icon : "fa fa-laptop",
					active : false
				}, {
					href : "sys/user",
					text : "系统管理",
					icon : "fa fa-cog",
					active : false
				} ]
			}
		});
	</script>

	<script type="text/javascript">
		function getLabel() {
			var label = [];
			for (var i = 0; i < 25; i++) {
				label.push('' + i + ":00");
			}
			return label;
		}
		var myChart = echarts.init(document.getElementById("chart"));
		var option = {
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
						type : [ 'line', 'bar', 'stack', 'tiled' ]
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
			series : [
					{
						name : '输出电压',
						type : 'line',
						stack : '总量',
						data : [ 150, 232, 201, 154, 190, 330, 410, 232, 201,
								154, 190, 330, 410, 232, 201, 154, 190, 330,
								410, 232, 201, 154, 190, 330, 410, 232, 201,
								154 ]
					},
					{
						name : '电池电压',
						type : 'line',
						stack : '总量',
						data : [ 320, 332, 301, 334, 390, 330, 320, 320, 332,
								301, 334, 390, 330, 320, 320, 332, 301, 334,
								390, 330, 320, 320, 332, 301, 334, 390, 330,
								320, 320 ]
					},
					{
						name : '负载',
						type : 'line',
						stack : '总量',
						data : [ 820, 932, 901, 934, 1290, 1330, 1320, 820,
								932, 901, 934, 1290, 1330, 1320, 820, 932, 901,
								934, 1290, 1330, 1320, 820, 932, 901, 934, 1290 ]
					} ]
		};

		myChart.setOption(option);
		
		window.onresize = myChart.resize;
	</script>

	<script type="text/javascript">
		function getDevInfo() {
			var info = [];
			for (var int = 0; int < 5; int++) {
				info.push({
					deviceid : 10000+int,
					outvoltage : 23 + "V",
					cellvoltage : 10 + "V",
					load : 25,
					status : "正常"
				});
			}
			return info;
		}
		var main = new Vue({
			el : "#home",
			data : {
				tabelData : {
					th : {
						index : "#",
						deviceid : "设备编号",
						outvoltage : "输出电压",
						cellvoltage : "电池电压",
						load : "负载",
						status : "工作状态"
					},
					td : getDevInfo()
				}
			}
		});
	</script>

</body>
</html>