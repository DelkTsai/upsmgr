package com.ups.web.module.login;

import javax.servlet.http.HttpSession;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.GET;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.POST;
import org.nutz.mvc.annotation.Param;

import com.ups.web.bean.User;
import com.ups.web.service.MenuService;
import com.ups.web.service.UserService;

@IocBean
// 抛出异常的话,就走500页面
@Ok("json:{locked:'password|salt',ignoreNull:true}")
// 忽略password和salt属性,忽略空属性的json输出
@Fail("http:500")
// 抛出异常的话,就走500页面
public class LoginModule {

	// 注入Service
	@Inject("userService")
	private UserService service;
	@Inject("menuService")
	private MenuService mservice;

	// 登录页面
	@GET
	@At("/login")
	@Ok("->:/")
	public void loginPage() {
	}

	// 登录页面
	@POST
	@At("/login")
	@Ok(">>:/home")
	public Object login(@Param("..") User user, HttpSession session) {
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
