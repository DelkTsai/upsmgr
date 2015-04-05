<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2 main" >
	<h3 class="page-header text-primary">新增</h3>
	<form id="tran-editor">
		<div class="form-group">
			<label for="deviceId">设备编号</label> <input type="text" class="form-control" id="deviceId" placeholder="设备编号">
		</div>
		<div class="form-group">
			<label for="deviceId">设备编号</label> <input type="text" class="form-control" id="deviceId" placeholder="设备编号">
		</div>
		<div class="form-group">
			<input type="reset" class="btn btn-primary" value="重置"> <input type="button" class="btn btn-success pull-right" value="保存">
		</div>
	</form>
</div>
<div class="col-xs-12 col-sm-8 col-md-9 col-lg-10 main">
	<h3 class="page-header text-primary">设备信息</h3>
	<div class="table-responsive">
		<table class="table table-striped">
			<thead>
				<tr>
					<th>#</th>
					<th>Header</th>
					<th>Header</th>
					<th>Header</th>
					<th>Header</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>1,001</td>
					<td>Lorem</td>
					<td>ipsum</td>
					<td>dolor</td>
					<td>sit</td>
				</tr>
				<tr>
					<td>1,002</td>
					<td>amet</td>
					<td>consectetur</td>
					<td>adipiscing</td>
					<td>elit</td>
				</tr>
				<tr>
					<td>1,003</td>
					<td>Integer</td>
					<td>nec</td>
					<td>odio</td>
					<td>Praesent</td>
				</tr>
				<tr>
					<td>1,003</td>
					<td>libero</td>
					<td>Sed</td>
					<td>cursus</td>
					<td>ante</td>
				</tr>
				<tr>
					<td>1,004</td>
					<td>dapibus</td>
					<td>diam</td>
					<td>Sed</td>
					<td>nisi</td>
				</tr>
				<tr>
					<td>1,005</td>
					<td>Nulla</td>
					<td>quis</td>
					<td>sem</td>
					<td>at</td>
				</tr>
				<tr>
					<td>1,006</td>
					<td>nibh</td>
					<td>elementum</td>
					<td>imperdiet</td>
					<td>Duis</td>
				</tr>
				<tr>
					<td>1,007</td>
					<td>sagittis</td>
					<td>ipsum</td>
					<td>Praesent</td>
					<td>mauris</td>
				</tr>
				<tr>
					<td>1,008</td>
					<td>Fusce</td>
					<td>nec</td>
					<td>tellus</td>
					<td>sed</td>
				</tr>
				<tr>
					<td>1,009</td>
					<td>augue</td>
					<td>semper</td>
					<td>porta</td>
					<td>Mauris</td>
				</tr>
				<tr>
					<td>1,010</td>
					<td>massa</td>
					<td>Vestibulum</td>
					<td>lacinia</td>
					<td>arcu</td>
				</tr>
				<tr>
					<td>1,011</td>
					<td>eget</td>
					<td>nulla</td>
					<td>Class</td>
					<td>aptent</td>
				</tr>
				<tr>
					<td>1,012</td>
					<td>taciti</td>
					<td>sociosqu</td>
					<td>ad</td>
					<td>litora</td>
				</tr>
				<tr>
					<td>1,013</td>
					<td>torquent</td>
					<td>per</td>
					<td>conubia</td>
					<td>nostra</td>
				</tr>
				<tr>
					<td>1,014</td>
					<td>per</td>
					<td>inceptos</td>
					<td>himenaeos</td>
					<td>Curabitur</td>
				</tr>
				<tr>
					<td>1,015</td>
					<td>sodales</td>
					<td>ligula</td>
					<td>in</td>
					<td>libero</td>
				</tr>
			</tbody>
		</table>
	</div>

	<!-- page specific plugin scripts -->
	<script type="text/javascript">
		var scripts = [ null, null ];
		$('.page-content-area').ace_ajax('loadScripts', scripts, function() {
			//inline scripts related to this page
		});
		var content = "#orglist";
		function listOrg(content) {
			$(content + " .widget-body")
					.html(
							'<h4 class="center">&nbsp;&nbsp;加载中&nbsp;&nbsp;<i class="ace-icon fa fa-spinner fa-spin orange bigger-125"></i></h4>');

			$.get("sysmgr/org/list", function(data) {
				setTimeout(function() {
					$(content + " .widget-body").html(data);
				}, 0);
			});
		}
		listOrg(content);
		function saveOrg() {
			$.post("sysmgr/org/add", $("#form-orgadd").serialize(), function(
					data) {
				if (data == "true")
					listOrg(content);
				else
					alert("添加失败！");
			});
		}
		function delOrg(id) {
			if (confirm("确认删除要删除该用户吗？")) {
				$.post("sysmgr/org/del", {
					id : id
				}, function(data) {
					if (data == "true")
						listOrg(content);
					else
						alert("添加失败！");
				});
			}

		}
		function editOrg() {
			$.post("sysmgr/org/edit", $("#form-orgedit").serialize(), function(
					data) {
				if (data == "true")
					listOrg(content);
				else
					alert("保存失败！");
			});
		}
		function toEdit(element) {
			$($(element).find("td")).each(
					function(index, item) {
						$("#form-orgedit [name='" + $(item).attr("id") + "']")
								.val($(item).text());
					});
		}
	</script>