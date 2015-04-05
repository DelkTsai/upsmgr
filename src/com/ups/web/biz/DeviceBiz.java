package com.ups.web.biz;

import java.util.Date;
import java.util.List;

import org.nutz.dao.Cnd;
import org.nutz.dao.pager.Pager;

import com.ups.web.entity.Device;
import com.ups.web.entity.Page;
import com.ups.web.service.DeviceService;

public class DeviceBiz {
	private DeviceService service = new DeviceService();
	public Page page = new Page();
	public boolean isSuccess = false;

	public void find(Pager pager,Device device) {
		List<Device> list = service.query(Cnd.where("deviceId", "=", device!=null?device.getDeviceId():null).and("status", "=", device!=null?device.getStatus():null).orderBy().desc("installTime"),
				pager);
		pager.setRecordCount(service.count());
		page.setPager(pager);
		page.setData(list);
		isSuccess = true;
	}

	public void add(Device device) {
		device.setInstallTime(new Date());
		service.insert(device);
		isSuccess = true;
	}

	public void edit(Device device) {
		service.update(device);
		isSuccess = true;
	}

	public void delete(Device device) {
		service.delete(device);
		isSuccess = true;
	}

}
