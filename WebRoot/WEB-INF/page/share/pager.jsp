<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<nav>
	<ul class="pagination" style="margin: 0;">
		<li class="{{page.pager.pageNumber==1?'disabled':''}}"><a href="javascript:;" v-on="click:firstPage"><i class="fa fa-angle-double-left"></i> </a></li>
		<li class="{{page.pager.pageNumber==1?'disabled':''}}"><a href="javascript:;" v-on="click:previousPage"><i class="fa fa-angle-left"></i> </a></li>
		<li><span style="padding: 0;height: 34px;line-height: 32px;text-align: center;width: 40px;"> 
		{{page.pager.pageNumber}}/{{page.pager.pageCount}}</span></li>
		<li class="{{page.pager.pageNumber==page.pager.pageCount?'disabled':''}}"><a href="javascript:;" v-on="click:nextPage"><i class="fa fa-angle-right"></i> </a></li>
		<li class="{{page.pager.pageNumber==page.pager.pageCount?'disabled':''}}"><a href="javascript:;" v-on="click:lastPage"><i class="fa fa-angle-double-right"></i> </a></li>
		<li><span style="padding: 0 5px 0 0;height: 34px;line-height: 32px;">  
		<select class="text-center"  style="width:40px;height:32px;border-radius:0;display: inline-block;border: none;" onchange="size_change(this)">
					<option value="5" selected="{{page.pager.pageSize==5?'selected':''}}">5</option>
					<option value="10" selected="{{page.pager.pageSize==10?'selected':''}}">10</option>
					<option value="15" selected="{{page.pager.pageSize==15?'selected':''}}">15</option>
					<option value="20" selected="{{page.pager.pageSize==20?'selected':''}}">20</option>
			</select>/页
		</span></li>
		<li><span style="padding: 0 5px;height: 34px;line-height: 32px;">  
			 共 <font class="text-danger">{{page.pager.recordCount}}</font> 条
		</span></li>
		<!-- 
		<li><span style="padding: 0;"> <input onchange="number_change(this)" class="text-center" type="text" value="" style="height: 32px;width: 50px;border-radius:0;border: none;" placeholder="跳转" />
		</span></li>
		 -->
	</ul>
</nav>