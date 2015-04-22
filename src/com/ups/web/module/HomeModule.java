package com.ups.web.module;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresUser;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.ups.web.bean.Device;
import com.ups.web.service.DeviceDataService;
import com.ups.web.service.DeviceService;
import com.ups.web.service.MenuService;
import com.ups.web.service.UserService;

@IocBean
public class HomeModule {
	
	// 注入Service
		@Inject("userService")
		private UserService service;
		@Inject("deviceService")
		private DeviceService dservice;
		@Inject("deviceDataService")
		private DeviceDataService ddservice;
		@Inject("menuService")
		private MenuService mservice;
	
	// 首页
	@RequiresUser
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
		return Json
				.toJson(ddservice.rs.setv("devinfo", dservice.rs.get("list")));
	}
	
	// 首页
	@At("/home/list")
	@Ok("json")
	public Object list(@Param("..") Pager pager, String deviceId) {
		ddservice.find(pager, deviceId);
		return ddservice.rs;
	}
}
