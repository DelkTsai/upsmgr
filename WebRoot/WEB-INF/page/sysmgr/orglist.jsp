<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<table class="table table-striped table-bordered table-hover table-condensed no-margin">
	<thead>
		<tr >
			<th class="center"><i class="ace-icon fa fa-users"></i>组织名称</th>
			<th class="center"><i class="ace-icon fa fa-user"></i>经理</th>
			<th class="center"><i class="ace-icon fa fa-edit"></i>备注</th>
			<th class="center"><i class="ace-icon fa fa-hand-o-up"></i>操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${obj}" var="org">
			<tr onclick="toEdit(this)" href="#edit" style="cursor: pointer;">
				<td class="center hidden" id="id">${org.id}</td>
				<td class="center" id="orgname">${org.orgname}</td>
				<td class="center" id="orgid">${org.userid}</td>
				<td class="center" id="comment">${org.comment}</td>
				<td class="center" id="opt">
					<div class="btn-group">
						<a class="btn btn-minier btn-danger" title="删除" onclick="delOrg(${org.id})" >
							<i class="fa fa-trash"></i>
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
		<li><span class="no-padding-top no-padding-bottom"> 每页 
		<select class="inline-block">
					<option value="5">5</option>
					<option value="10">10</option>
					<option value="15">15</option>
			</select>条
		</span></li>
	</ul>
	<ul class="pagination" style="margin:5px 0 0 0;">
		<li><a href="javascript:;"><i class="ace-icon fa fa-angle-double-left"></i></a></li>
		<li><a href="javascript:;"><i class="ace-icon fa fa-angle-left"></i></a></li>
		<li><span class="" style="padding:0px;"><input type="text" value="1" class="input-mini inline-block" style="height:31px;width:35px;text-align:center"/></span></li>
		<li><a href="javascript:;"><i class="ace-icon fa fa-angle-right"></i></a></li>
		<li><a href="javascript:;"><i class="ace-icon fa fa-angle-double-right"></i></a></li>
		
		<li><span>共计<span class="red">5</span>页
		</span></li>
	</ul>

</div>





