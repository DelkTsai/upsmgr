package com.ups.web.module.login;

import javax.servlet.http.HttpSession;

import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.adaptor.PairAdaptor;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.ups.web.biz.UserBiz;
import com.ups.web.entity.User;

@AdaptBy(type = PairAdaptor.class)
@IocBean
public class AuthModule {
	@Inject
	protected Dao dao;
	private UserBiz biz;
	@At("/login")
	@Ok(">>:${obj!=null ? 'home' : '/'}")
	@Fail(">>:/")
	@Filters
	public Object login(@Param("..")User user, HttpSession session) {
		biz = new UserBiz(dao);
		if(biz.login(user)){
			session.setAttribute("curruser", biz.page.getData());
			return "success";
		}
		session.setAttribute("msg", biz.page.getData());
		return null;
	}

	@At("/exit")
	@Ok(">>:/")
	@Fail(">>:/")
	public Object exit(HttpSession session) {
		session.invalidate();
		return null;
	}
	
	@At("/home")
	@Ok("jsp:page.home")
	public Object home() {
		
		return "";
	}

	@At("/index")
	@Ok("jsp:page.index")
	public Object index() {
//		new InitSysBiz();
		return "";
	}

}
