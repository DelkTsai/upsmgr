package com.ups.web.module.sys;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.mvc.adaptor.PairAdaptor;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.ups.web.bean.Menu;
import com.ups.web.service.MenuService;

//用户管理的Controller类
//该类的方法为Action对应用户的每个操作
@AdaptBy(type = PairAdaptor.class)
@At("/sys/menu")
@IocBean
public class MenuModule {

	// 注入Service
	@Inject("menuService")
	private MenuService service;

	// 用户管理页面初始化
	@At("/")
	@Ok("jsp:page.sys.menu")
	// 返回视图
	public Object index() {
		service.find();
		return Json.toJson(service.rs);
	}

	// 用户分页查询
	@At("/list")
	@Ok("json")
	// 返回json数据
	public Object list() {
		service.find();
		return service.rs;
	}

	// 用户添加
	@At("/add")
	@Ok("json")
	// 返回json数据
	public Object add(@Param("..") Menu menu) {
		service.add(menu);
		return service.rs;
	}

	// 用户编辑
	@At("/edit")
	@Ok("json")
	// 返回json数据
	public Object edit(@Param("..") Menu menu) {
		service.edit(menu);
		return service.rs;
	}

	// 用户删除
	@At("/delete")
	@Ok("json")
	// 返回json数据
	public Object delete(@Param("..") Menu menu) {
		service.delete(menu);
		return service.rs;
	}
}
