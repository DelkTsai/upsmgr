package com.ups.web.module.sys;

import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.mvc.adaptor.PairAdaptor;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.ups.web.entity.Role;
import com.ups.web.service.RoleService;

//用户管理的Controller类
//该类的方法为Action对应用户的每个操作
@AdaptBy(type = PairAdaptor.class)
@At("/sys/role")
@IocBean
public class RoleModule {

	// 注入Service
	@Inject("roleService")
	private RoleService service;

	// 创建请求结果对象（用于返回增、删、改操作结果给用户）

	// 用户管理页面初始化
	@At("/")
	@Ok("jsp:page.sys.role")
	// 返回视图
	public Object index() {
		Pager pager = new Pager();
		pager.setPageNumber(1);
		pager.setPageSize(10);
		service.find(pager);
		return Json.toJson(service.rs);
	}

	// 角色查询
	@At("/list")
	@Ok("json")
	// 返回json数据
	public Object list(@Param("..") Pager pager) {
		service.find(pager);
		return service.rs;
	}

	// 角色添加
	@At("/add")
	@Ok("json")
	// 返回json数据
	public Object add(@Param("..") Role role) {
		service.add(role);
		return service.rs;

	}

	// 角色编辑
	@At("/edit")
	@Ok("json")
	// 返回json数据
	public Object edit(@Param("..") Role role) {
		service.edit(role);
		return service.rs;
	}

	// 角色删除
	@At("/delete")
	@Ok("json")
	// 返回json数据
	public Object delete(@Param("..") Role role) {
		service.delete(role);
		return service.rs;
	}
}
