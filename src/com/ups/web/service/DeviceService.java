package com.ups.web.service;

import java.util.Date;
import java.util.List;

import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.Dao;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;

import com.ups.web.entity.Device;

//设备Service，处理设备的业务逻辑，数据库访问
@IocBean(args = { "refer:dao" })
public class DeviceService extends BaseService {

	// 初始化数据库访问对象Dao
	public DeviceService(Dao dao) {
		super(dao);
	}

	public void find() {
		List<Device> list = null;
	
		list = dao.query(Device.class, null, null);
		
		page.setData(list);
		isSuccess = true;
	}
	
	// 数据库分页查询
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
		list = dao.query(Device.class, cnd, pager);

		pager.setRecordCount(dao.count(Device.class, cnd));
		page.setPager(pager);
		page.setData(list);
		isSuccess = true;
	}

	// 数据库添加操作
	public void add(Device device) {
		device.setInstallTime(new Date());
		dao.insert(device);
		isSuccess = true;
	}

	// 数据库更新操作
	public void edit(Device device) {
		dao.updateIgnoreNull(device);
		isSuccess = true;
	}

	// 数据库删除数据操作
	public void delete(Device device) {
		dao.delete(device);
		isSuccess = true;
	}

}
