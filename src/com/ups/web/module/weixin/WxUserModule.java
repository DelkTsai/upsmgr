package com.ups.web.module.weixin;

import java.util.ArrayList;
import java.util.List;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.json.entity.JsonEntity;
import org.nutz.lang.ContinueLoop;
import org.nutz.lang.Each;
import org.nutz.lang.ExitLoop;
import org.nutz.lang.LoopException;
import org.nutz.lang.util.NutMap;
import org.nutz.mvc.adaptor.PairAdaptor;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.weixin.bean.WxUser;
import org.nutz.weixin.spi.WxResp;

import com.ups.web.bean.Menu;
import com.ups.web.service.WxApi;

//用户管理的Controller类
//该类的方法为Action对应用户的每个操作
@AdaptBy(type = PairAdaptor.class)
@At("/weixin/user")
@IocBean
public class WxUserModule {

	// 注入Service
	@Inject("wxApi")
	private WxApi api;

	private NutMap rs = new NutMap();

	// 用户管理页面初始化
	@At("/")
	@Ok("jsp:page.weixin.user")
	// 返回视图
	public Object index() {
		final List<String> list = new ArrayList<String>();
		Each<String> each = new Each<String>() {
			@Override
			public void invoke(int index, String ele, int length)
					throws ExitLoop, ContinueLoop, LoopException {
				list.add(ele);
			}
		};
		api.user_get(each);
		
		List<WxResp> resps = new ArrayList<WxResp>();
		for (String string : list) {
			resps.add(api.user_info(string, "zh_CN"));
		}
		
		return Json.toJson(rs.setv("list", resps));
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
	public Object add(@Param("..") NutMap menus) {
		api.menu_create(menus);
		return rs.setv("ok", true);
	}

	// 用户编辑
	@At("/edit")
	@Ok("json")
	// 返回json数据
	public Object edit(@Param("..") Menu menu) {
		return null;
	}

	// 用户删除
	@At("/delete")
	@Ok("json")
	// 返回json数据
	public Object delete(@Param("..") Menu menu) {
		return null;
	}
}
