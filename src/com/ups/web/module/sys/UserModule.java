package com.ups.web.module.sys;

import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.lang.util.NutMap;
import org.nutz.mvc.adaptor.PairAdaptor;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.ups.web.entity.User;
import com.ups.web.service.UserService;

//用户管理的Controller类
//该类的方法为Action对应用户的每个操作
@AdaptBy(type = PairAdaptor.class)
@At("/sys/user")
@IocBean
public class UserModule {
	
	//注入Service
	@Inject("userService")
	private UserService service;
	// 创建请求结果对象（用于返回增、删、改操作结果给用户）
	private NutMap rs = new NutMap();

	//用户管理页面初始化
	@At("/")
	@Ok("jsp:page.sys.user")//返回视图
	public Object index() {
		Pager pager = new Pager();
		pager.setPageNumber(1);
		pager.setPageSize(5);
		service.find(pager,null);
		return Json.toJson(service.rs);
	}

	//用户分页查询
	@At("/list")
	@Ok("json")//返回json数据
	public Object list(@Param("..") Pager pager,@Param("..") User user) {
		service.find(pager,user);
		return service.rs;
	}

	//用户添加
	@At("/add")
	@Ok("json")//返回json数据
	public Object add(@Param("..") User user) {
		service.add(user);
		if (service.rs.getBoolean("ok")) {
			rs.setv("isSuccess",true).setv("msg", "添加成功，用户名：" + user.getUsername());
		} else {
			rs.setv("isSuccess",false).setv("msg", "添加失败，用户名：" + user.getUsername());
		}
		return rs;

	}

	//用户编辑
	@At("/edit")
	@Ok("json")//返回json数据
	public Object edit(@Param("..") User user) {
		service.edit(user);
		if (service.rs.getBoolean("ok")) {
			rs.setv("isSuccess",true).setv("msg", "修改成功，用户名：" + user.getUsername());
		} else {
			rs.setv("isSuccess",false).setv("msg", "修改失败，用户名：" + user.getUsername());
		}
		return rs;
	}

	//用户删除
	@At("/delete")
	@Ok("json")//返回json数据
	public Object delete(@Param("..") User user) {
		service.delete(user);
		if (service.rs.getBoolean("ok")) {
			rs.setv("isSuccess",true).setv("msg", "删除成功，用户名：" + user.getUsername());
		} else {
			rs.setv("isSuccess",false).setv("msg", "删除失败，用户名：" + user.getUsername());
		}
		return rs;
	}
}
