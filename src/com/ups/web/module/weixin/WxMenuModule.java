package com.ups.web.module.weixin;

import java.util.List;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.lang.util.NutMap;
import org.nutz.mvc.adaptor.PairAdaptor;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.weixin.bean.WxMenu;

import com.ups.web.bean.Menu;
import com.ups.web.service.WxApi;

//用户管理的Controller类
//该类的方法为Action对应用户的每个操作
@AdaptBy(type = PairAdaptor.class)
@At("/weixin/menu")
@IocBean
public class WxMenuModule {

	// 注入Service
	@Inject("wxApi")
	private WxApi api;

	private NutMap rs = new NutMap();
	
	// 用户管理页面初始化
	@At("/")
	@Ok("jsp:page.weixin.menu")
	// 返回视图
	public Object index() {
		return Json.toJson(rs.setv("list", api.menu_get()));
	}

	// 用户分页查询
	@At("/list")
	@Ok("json")
	// 返回json数据
	public Object list() {
		return Json.toJson(rs.setv("list", api.menu_get()));
	}

	// 用户添加
	@At("/add")
	@Ok("json")
	// 返回json数据
	public Object create(@Param("..") List<WxMenu> menus) {
		api.menu_create(menus);
		return rs.setv("ok", true);
	}

	// 用户删除
	@At("/delete")
	@Ok("json")
	// 返回json数据
	public Object delete(@Param("..") Menu menu) {
		return null;
	}
}
