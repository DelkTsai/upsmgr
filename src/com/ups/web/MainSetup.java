package com.ups.web;

import java.util.Date;

import org.nutz.dao.Dao;
import org.nutz.dao.util.Daos;
import org.nutz.ioc.Ioc;
import org.nutz.mvc.NutConfig;
import org.nutz.mvc.Setup;

import com.ups.server.Server;
import com.ups.web.entity.User;
import com.ups.web.tool.DESKey;

//程序启动初始化类
public class MainSetup implements Setup {

	@Override
	public void init(NutConfig conf) {

		Ioc ioc = conf.getIoc();
		Dao dao = ioc.get(Dao.class);
		Daos.createTablesInPackage(dao, "com.ups.web", false);
		DESKey des;
		try {
			des = new DESKey();
			// 初始化默认根用户
			if (dao.count(User.class) == 0) {
				User user = new User();
				user.setUsername("admin");
				user.setPassword(des.encrypt("123456"));
				user.setNickname("最高管理员");
				user.setCreateTime(new Date());
				user.setUpdateTime(new Date());
				dao.insert(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 开启数据采集服务
		Server.start();

	}

	@Override
	public void destroy(NutConfig conf) {
		// TODO Auto-generated method stub

	}

}
