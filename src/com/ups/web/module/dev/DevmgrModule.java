package com.ups.web.module.dev;

import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.ups.web.bean.Device;
import com.ups.web.service.DeviceService;

//设备管理的Controller类
//该类的方法为Action对应用户的每个操作
@At("/dev")
@IocBean
public class DevmgrModule {

	// 注入Service
	@Inject("deviceService")
	private DeviceService service;

	// 设备管理初始化
	@At("/")
	@Ok("jsp:page.devmgr")
	// 返回视图
	public Object index() {
		Pager pager = new Pager();
		pager.setPageNumber(1);
		pager.setPageSize(5);
		service.find(pager, null);
		return Json.toJson(service.rs);
	}

	// 设备分页查询
	@At("/list")
	@Ok("json")
	// 返回json数据
	public Object list(@Param("..") Pager pager, @Param("..") Device device) {
		service.find(pager, device);
		return service.rs;
	}

	// 设备添加
	@At("/add")
	@Ok("json")
	// 返回json数据
	public Object add(@Param("..") Device device) {
		service.add(device);
		return service.rs;
	}

	// 设备编辑
	@At("/edit")
	@Ok("json")
	// 返回json数据
	public Object edit(@Param("..") Device device) {
		service.edit(device);
		return service.rs;
	}

	// 设备删除
	@At("/delete")
	@Ok("json")
	// 返回json数据
	public Object delete(@Param("..") Device device) {
		service.delete(device);
		return service.rs;
	}

}
