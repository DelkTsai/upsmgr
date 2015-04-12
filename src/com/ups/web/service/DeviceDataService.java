package com.ups.web.service;

import java.util.Date;
import java.util.List;

import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.Dao;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.IocBean;

import com.ups.web.entity.DeviceData;

//设备Service，处理设备的业务逻辑，数据库访问
@IocBean(args = { "refer:dao" })
public class DeviceDataService extends BaseService {

	// 初始化数据库访问对象Dao
	public DeviceDataService(Dao dao) {
		super(dao);
	}

	public void find(String deviceId) {
		List<DeviceData> list = null;

		list = dao.query(DeviceData.class,
				Cnd.where("deviceId", "=", deviceId).limit(1,25).desc("dataTime"));
		
		page.setData(list);
		isSuccess = true;
	}

	// 数据库分页查询
	public void find(Pager pager, String deviceId) {
		List<DeviceData> list = null;
		Condition cnd = Cnd.where("deviceId", "=", deviceId).desc("dataTime");
		list = dao.query(DeviceData.class, cnd, pager);
		pager.setRecordCount(dao.count(DeviceData.class, cnd));
		page.setPager(pager);
		page.setData(list);
		isSuccess = true;
	}

	// 数据库添加操作
	public void batchAdd(List<DeviceData> datas) {
		for (DeviceData deviceData : datas) {
			deviceData.setDataTime(new Date());
			deviceData.setComment("测试数据");
		}
		// Trans.exec(new Atom(){
		// @Override
		// public void run() {
		// }
		// });
		dao.insert(datas);
		isSuccess = true;
	}

	// 数据库添加操作
	public void add(DeviceData data) {
		data.setDataTime(new Date());
		data.setComment("测试数据");
		dao.insert(data);
		isSuccess = true;
	}

	// 数据库更新操作
	public void edit(DeviceData data) {
		dao.updateIgnoreNull(data);
		isSuccess = true;
	}

	// 数据库删除数据操作
	public void delete(DeviceData data) {
		dao.delete(data);
		isSuccess = true;
	}

}
