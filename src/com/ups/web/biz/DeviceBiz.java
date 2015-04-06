package com.ups.web.biz;

import java.util.Date;
import java.util.List;

import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.Dao;
import org.nutz.dao.pager.Pager;
import org.nutz.lang.Strings;

import com.ups.web.entity.Device;

public class DeviceBiz extends BaseBiz{
	public DeviceBiz(Dao dao) {
		super(dao);
	}
	public void find(Pager pager, Device device) {
		List<Device> list = null;
		Condition cnd = null;
		if (device == null)
			cnd = Cnd.orderBy().desc("installTime");
		else
			cnd = Cnd
					.where("deviceId",
							Strings.isBlank(device.getDeviceId()) ? "<>" : "=",
							device.getDeviceId())
					.and("status", device.getStatus() < 0 ? "<>" : "=",
							device.getStatus()).desc("installTime");
		list = dao.query(Device.class,cnd, pager);

		pager.setRecordCount(dao.count(Device.class,cnd));
		page.setPager(pager);
		page.setData(list);
		isSuccess = true;
	}

	public void add(Device device) {
		device.setInstallTime(new Date());
		dao.insert(device);
		isSuccess = true;
	}

	public void edit(Device device) {
		dao.updateIgnoreNull(device);
		isSuccess = true;
	}

	public void delete(Device device) {
		dao.delete(device);
		isSuccess = true;
	}

}
