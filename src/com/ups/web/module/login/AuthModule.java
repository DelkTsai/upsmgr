package com.ups.web.module.login;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.lang.util.NutMap;
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
	
	//注入Service
	@Inject("userService")
	private UserService service;
	@Inject("deviceService")
	private DeviceService dservice;
	@Inject("deviceDataService")
	private DeviceDataService ddservice;
	@Inject("menuService")
	private MenuService mservice;
	
	private NutMap rs = new NutMap();

	// 初始化Des加密对象，用于用户密码加密
		private static DESKey des;
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
		if (service.login(user)) {
			session.setAttribute("curruser", service.page.getData());
			mservice.find();
			session.setAttribute("menus", Json.toJson(mservice.rs.get("page")));
			return "success";
		}
		session.setAttribute("msg", service.page.getData());
		return null;
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
		public Object changePassword(String oldpassword,String newpassword,String repassword,HttpSession session) {
			NutMap rs = new NutMap();
			User user = (User)session.getAttribute("curruser");
			if("loyal".equals(user.getUsername())){
				rs.setv("isSuccess", false).setv("msg", "该用户密码不允许修改");
			}
			else if (!oldpassword.equals(des.decrypt(user.getPassword()))) {
				rs.setv("isSuccess", false).setv("msg", "原密码错误");
			}else if (newpassword==null||newpassword.equals("")) {
				rs.setv("isSuccess", false).setv("msg", "新密码不能为空");
			}
			else if (!newpassword.equals(repassword)) {
				rs.setv("isSuccess", false).setv("msg", "两次密码输入不一致");
			}else {
				user.setPassword(des.encrypt(newpassword));
				service.edit(user);
				if (!service.isSuccess) rs.setv("isSuccess", false).setv("msg", "密码保存失败");
				else  rs.setv("isSuccess", true).setv("msg", "密码修改成功，重新登录生效");
			}
			return rs;
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
		List<Device> list = (List<Device>)dservice.page.getData();
		if (list.size()>0) {
			Device device = list.get(0);
			ddservice.find(pager,device.getDeviceId());
		}
		rs.setv("devinfo", dservice.page.getData()).setv("page", ddservice.page).setv("chartData", ddservice.page.getData());
		return Json.toJson(rs);
	}
	
	// 首页
		@At("/home/list")
		@Ok("json")
		public Object list(@Param("..") Pager pager,String deviceId) {
			ddservice.find(pager,deviceId);
			return ddservice.page;
		}

	// 登录页面
	@At("/index")
	@Ok("jsp:page.index")
	public Object index(HttpSession session) {
		return session.getAttribute("msg");
	}

}
