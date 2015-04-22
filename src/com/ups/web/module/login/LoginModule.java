package com.ups.web.module.login;

import javax.servlet.http.HttpSession;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.ups.web.bean.User;
import com.ups.web.service.MenuService;
import com.ups.web.service.UserService;

@IocBean
@At("/user")
// 抛出异常的话,就走500页面
public class LoginModule {

	// 注入Service
	@Inject("userService")
	private UserService service;
	@Inject("menuService")
	private MenuService mservice;

	// 用户登录
	@At("/login")
	// @Ok(">>:${obj!=null ? 'home' : '/'}")
	@Ok(">>: /home")
	@Fail("jsp:page.login")
	public void login(@Param("..") User user, HttpSession session) {
		service.login(user);
		if (service.rs.getBoolean("ok")) {
			session.setAttribute("curruser", service.rs.get("user"));
			mservice.find();
			session.setAttribute("menus", Json.toJson(mservice.rs.get("list")));
		} else {
			session.setAttribute("msg", service.rs.get("msg"));
		}
	}

	// // 用户退出
	@At("/logout")
	@Ok(">>:/user/login")
	@Fail(">>:/user/login")
	public Object logout(HttpSession session) {
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

}
