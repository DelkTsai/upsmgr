package com.ups.web.module.dev;

import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.ups.web.service.DeviceDataService;

//设备管理的Controller类
//该类的方法为Action对应用户的每个操作
@At("/devdata")
@IocBean
public class DevdataModule {

	// 注入Service
	@Inject("deviceDataService")
	private DeviceDataService service;
	// 创建请求结果对象（用于返回增、删、改操作结果给用户）

//	// 设备管理初始化
//	@At("/")
//	@Ok("jsp:page.devmgr")//返回视图
//	public Object index() {
//		Pager pager = new Pager();
//		pager.setPageNumber(1);
//		pager.setPageSize(5);
//		service.find(pager, null);
//		return Json.toJson(service.page);
//	}

	// 设备分页查询
	@At("/list")
	@Ok("json")//返回json数据
	public Object list(@Param("..") Pager pager,String deviceId) {
		service.find(pager, deviceId);
		return service.rs;
	}

	// 设备分页查询
		@At("/chart")
		@Ok("json")//返回json数据
		public Object chart(String deviceId) {
			service.find(deviceId);
			return service.rs;
		}

}
