<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<table class="table table-striped table-bordered table-hover table-condensed no-margin">
	<thead>
		<tr>
			<th class="center"><i class="ace-icon fa fa-user"></i>用户名</th>
			<th class="center"><i class="ace-icon fa fa-users"></i>组织</th>
			<th class="center"><i class="ace-icon fa fa-key"></i>角色</th>
			<th class="center"><i class="ace-icon fa fa-user"></i>昵称</th>
			<th class="center"><i class="ace-icon fa fa-edit"></i>备注</th>
			<th class="center"><i class="ace-icon fa fa-hand-o-up"></i>操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${obj}" var="user">
			<tr onclick="toEdit(this)" href="#edit"  style="cursor: pointer;">
				<td class="center hidden" href="id">${user.id}</td>
				<td class="center" href="username">${user.username}</td>
				<td class="center orgid" href="orgid" val="${user.orgid}"></td>
				<td class="center roleid" href="roleid" val="${user.roleid}"></td>
				<td class="center" href="nickname">${user.nickname}</td>
				<td class="center" href="comment">${user.comment}</td>
				<td class="center" href="opt">
					<div class="btn-group">
						<a class="btn btn-minier btn-danger" title="删除" onclick="delUser(${user.id})"> <i class="fa fa-trash"></i>
						</a>
						<a class="btn btn-minier btn-warning" title="重置密码" onclick="resetUser(${user.id})"> <i class="fa fa-refresh"></i>
						</a>
					</div>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<div class="widget-toolbox">
	<ul class="pagination" style="margin:5px 0 0 0;">
		<li><span>当前<span class="red">1-5</span>条 &nbsp;共计<span class="red">50</span>条
		</span></li>
		<li><span class="no-padding-top no-padding-bottom"> 每页 <select class="inline-block">
					<option value="5">5</option>
					<option value="10">10</option>
					<option value="15">15</option>
			</select>条
		</span></li>
	</ul>
	<ul class="pagination" style="margin:5px 0 0 0;">
		<li><a href="javascript:;"><i class="ace-icon fa fa-angle-double-left"></i></a></li>
		<li><a href="javascript:;"><i class="ace-icon fa fa-angle-left"></i></a></li>
		<li><span class="" style="padding:0px;"><input type="text" value="1" class="input-mini inline-block" style="height:31px;width:35px;text-align:center" /></span></li>
		<li><a href="javascript:;"><i class="ace-icon fa fa-angle-right"></i></a></li>
		<li><a href="javascript:;"><i class="ace-icon fa fa-angle-double-right"></i></a></li>

		<li><span>共计<span class="red">5</span>页
		</span></li>
	</ul>

</div>

<script type="text/javascript">
	$(".orgid").each(function() {
		$(this).text(org[$(this).attr("val")]["orgname"]);
	});
	$(".roleid").each(function() {
		$(this).text(role[$(this).attr("val")]["rolename"]);
	});
	function toEdit(element) {
		$($(element).find("td")).each(
				function(index, item) {
					$("#form-useredit input[name='" + $(item).attr("href") + "']")
							.val($(item).text());
					$("#form-useredit select[name='" + $(item).attr("href") + "']")
					.val($(item).attr("val"));
				});
	}
</script>



