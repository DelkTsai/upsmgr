package com.ups.web.module.dev;

import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.lang.util.NutMap;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.ups.web.entity.Device;
import com.ups.web.service.DeviceService;

//设备管理的Controller类
//该类的方法为Action对应用户的每个操作
@At("/dev")
@IocBean
public class DevdataModule {

	// 注入Service
	@Inject("deviceService")
	private DeviceService service;
	// 创建请求结果对象（用于返回增、删、改操作结果给用户）
	private NutMap rs = new NutMap();

	// 设备管理初始化
	@At("/")
	@Ok("jsp:page.devmgr")//返回视图
	public Object index() {
		Pager pager = new Pager();
		pager.setPageNumber(1);
		pager.setPageSize(5);
		service.find(pager, null);
		return Json.toJson(service.page);
	}

	// 设备分页查询
	@At("/list")
	@Ok("json")//返回json数据
	public Object list(@Param("..") Pager pager, @Param("..") Device device) {
		service.find(pager, device);
		return service.page;
	}

	// 设备添加
	@At("/add")
	@Ok("json")//返回json数据
	public Object add(@Param("..") Device device) {
		service.add(device);
		if (service.isSuccess) {
			rs.setv("isSuccess", true).setv("msg",
					"添加成功，设备编号：" + device.getDeviceId());
		} else {
			rs.setv("isSuccess", false).setv("msg",
					"添加失败，设备编号：" + device.getDeviceId());
		}
		return rs;
	}

	// 设备编辑
	@At("/edit")
	@Ok("json")//返回json数据
	public Object edit(@Param("..") Device device) {
		service.edit(device);
		if (service.isSuccess) {
			rs.setv("isSuccess", true).setv("msg",
					"修改成功，设备编号：" + device.getDeviceId());
		} else {
			rs.setv("isSuccess", false).setv("msg",
					"修改失败，设备编号：" + device.getDeviceId());
		}
		return rs;
	}

	// 设备删除
	@At("/delete")
	@Ok("json")//返回json数据
	public Object delete(@Param("..") Device device) {
		service.delete(device);
		if (service.isSuccess) {
			rs.setv("isSuccess", true).setv("msg",
					"删除成功，设备编号：" + device.getDeviceId());
		} else {
			rs.setv("isSuccess", false).setv("msg",
					"删除失败，设备编号：" + device.getDeviceId());
		}
		return rs;
	}
}
