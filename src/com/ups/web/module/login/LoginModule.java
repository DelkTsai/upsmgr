package com.ups.web.module.login;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.nutz.integration.shiro.CaptchaFormAuthenticationFilter;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Attr;
import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.GET;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.POST;

import com.ups.web.mvc.AuthenticationFilter;
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
	@Ok("jsp:/index")
	public void loginPage() {
	}

	// 登录页面
	@POST
	@At("/login")
	@Ok(">>:${obj==null?'/login':'/home'}")
	@Filters(@By(type = CaptchaFormAuthenticationFilter.class))
	public Object login(@Attr("loginToken") AuthenticationToken token,HttpSession session) {
		Object msg = null; 
		try {
			Subject subject = SecurityUtils.getSubject();
			ThreadContext.bind(subject);
			subject.login(token);
			session.setAttribute("curruser", token);
			msg = true;
		} catch (IncorrectCredentialsException e) {
			msg = e.getMessage();
//			session.setAttribute("msg",e.getMessage());
		} catch (LockedAccountException e) {
			//Map<String, Object> msgs = Mvcs.getLocaleMessage("zh_CN");
			//String errMsg = msgs.get("admin.login.lockedAccount").toString();
			msg = e.getMessage();
		} catch (AuthenticationException e) {
			//Map<String, Object> msgs = Mvcs.getLocaleMessage("zh_CN");
			//String errMsg = msgs.get("login_error").toString();
			msg = e.getMessage();
		} catch (Exception e) {
			msg = e.getMessage();
//			seesion.setAttribute("msg",e.getMessage());
		}
		return msg;
	}

	// 登录页面
		@GET
		@At("/logout")
		@Ok(">>:/login")
		@RequiresAuthentication
		public Object logout() {
			SecurityUtils.getSubject().logout();
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
