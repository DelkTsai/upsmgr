package com.ups.web.module.dev;

import org.nutz.dao.pager.Pager;
import org.nutz.json.Json;
import org.nutz.mvc.adaptor.JsonAdaptor;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.ups.web.biz.DeviceBiz;
import com.ups.web.entity.Device;
import com.ups.web.entity.Result;

@At("/dev")
public class DevmgrModule {
	private DeviceBiz biz = new DeviceBiz();
	private Result rs = new Result();

	@At("/")
	@Ok("jsp:page.devmgr")
	public Object index() {
		Pager pager = new Pager();
		pager.setPageNumber(1);
		pager.setPageSize(5);
		biz.find(pager,null);
		return Json.toJson(biz.page);
	}

	@At("/list")
	@Ok("json")
	public Object list(@Param("..") Pager pager,@Param("..") Device device) {
		biz.find(pager,device);
		return biz.page;
	}

	@At("/add")
	@Ok("json")
	public Object add(@Param("..") Device device) {
		biz.add(device);
		if (biz.isSuccess) {
			rs.setSuccess(true);
			rs.setMsg("添加成功，设备编号：" + device.getDeviceId());
		}else {
			rs.setSuccess(false);
			rs.setMsg("添加失败，设备编号：" + device.getDeviceId());
		}
		return rs;
	}

	@At("/edit")
	@Ok("json")
	public Object edit(@Param("..") Device device) {
		biz.edit(device);
		if (biz.isSuccess) {
			rs.setSuccess(true);
			rs.setMsg("修改成功，设备编号：" + device.getDeviceId());
		}else {
			rs.setSuccess(false);
			rs.setMsg("修改失败，设备编号：" + device.getDeviceId());
		}
		return rs;
	}

	@At("/delete")
	@Ok("json")
	public Object delete(@Param("..") Device device) {
		biz.delete(device);
		if (biz.isSuccess) {
			rs.setSuccess(true);
			rs.setMsg("删除成功，设备编号：" + device.getDeviceId());
		}else {
			rs.setSuccess(false);
			rs.setMsg("删除失败，设备编号：" + device.getDeviceId());
		}
		return rs;
	}
}
