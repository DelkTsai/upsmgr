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
							<tr v-repeat="page.devdata">
								<td>{{$index+1}}</td>
								<td>{{deviceId}}</td>
								<td>{{outputVoltage}}</td>
								<td>{{batteryVoltage}}</td>
								<td>{{batteryLoad}}</td>
								<td>{{dataTime}}</td>
								<td>{{communicateMethod}}</td>
								<td>{{comment}}</td>
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
		var vue = new Vue({
			el : "#home",
			data : {
				page : ${obj}
			}
		});
	</script>

	<script type="text/javascript">
	
		var outputVoltage = [];
		var batteryVoltage = [];
		var batteryLoad = [];
		
		$(vue.page.devdata).each(function(index,item) {
			outputVoltage.push(item.outputVoltage);
			batteryVoltage.push(item.batteryVoltage);
			batteryLoad.push(item.batteryLoad);
		});
	
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
						//data : outputVoltage
						data : outputVoltage
					},
					{
						name : '电池电压',
						type : 'line',
						stack : '总量',
						//data : batteryVoltage
						data : batteryVoltage
					},
					{
						name : '负载',
						type : 'line',
						stack : '总量',
						//data : batteryLoad
						data : batteryLoad
					} ]
		};

		myChart.setOption(option);
		
		window.onresize = myChart.resize;
	</script>


</body>
</html>