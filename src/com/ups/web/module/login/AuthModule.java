package com.ups.web.module.login;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.mvc.adaptor.PairAdaptor;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.ups.web.entity.Device;
import com.ups.web.entity.User;
import com.ups.web.service.DeviceDataService;
import com.ups.web.service.DeviceService;
import com.ups.web.service.MenuService;
import com.ups.web.service.UserService;
import com.ups.web.tool.DESKey;

@AdaptBy(type = PairAdaptor.class)
@IocBean
public class AuthModule {

	// 注入Service
	@Inject("userService")
	private UserService service;
	@Inject("deviceService")
	private DeviceService dservice;
	@Inject("deviceDataService")
	private DeviceDataService ddservice;
	@Inject("menuService")
	private MenuService mservice;

	// 初始化Des加密对象，用于用户密码加密
	static DESKey des;
	static {
		try {
			des = new DESKey();
		} catch (Exception e) {
			System.err.println("DES初始化失败！");
		}
	}

	// 用户登录
	@At("/login")
	@Ok(">>:${obj!=null ? 'home' : '/'}")
	@Fail(">>:/")
	@Filters
	public Object login(@Param("..") User user, HttpSession session) {
		service.login(user);
		if (service.rs.getBoolean("ok")) {
			session.setAttribute("curruser", service.rs.get("user"));
			return "";
		}else{
			session.setAttribute("msg", service.rs.get("msg"));
			return null;
		}
	}

	// 用户退出
	@At("/exit")
	@Ok(">>:/")
	@Fail(">>:/")
	public Object exit(HttpSession session) {
		session.invalidate();
		return null;
	}

	// 用户密码修改
	@At("/changePassword")
	@Ok("json")
	@Fail(">>:/")
	public Object changePassword(String oldpassword, String newpassword,
			String repassword, HttpSession session) {
		service.changePwd(oldpassword, newpassword, repassword, session);
		return service.rs;
	}

	// 首页
	@At("/home")
	@Ok("jsp:page.home")
	public Object home() {
		Pager pager = new Pager();
		pager.setPageNumber(1);
		pager.setPageSize(25);
		dservice.find();
		@SuppressWarnings("unchecked")
		List<Device> list = (List<Device>) dservice.rs.get("list");
		if (list.size() > 0) {
			Device device = list.get(0);
			ddservice.find(pager, device.getDeviceId());
		}
		return Json.toJson(ddservice.rs.setv("devinfo", dservice.rs.get("list")));
	}

	// 首页
	@At("/home/list")
	@Ok("json")
	public Object list(@Param("..") Pager pager, String deviceId) {
		ddservice.find(pager, deviceId);
		return ddservice.rs;
	}

	// 登录页面
	@At("/index")
	@Ok("jsp:page.index")
	public Object index(HttpSession session) {
		return session.getAttribute("msg");
	}

}
