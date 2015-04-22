package com.ups.web;

import org.nutz.dao.Dao;
import org.nutz.dao.util.Daos;
import org.nutz.ioc.Ioc;
import org.nutz.mvc.NutConfig;
import org.nutz.mvc.Setup;

import com.ups.server.Server;
import com.ups.web.bean.User;
import com.ups.web.service.UserService;

//程序启动初始化类
public class MainSetup implements Setup {

	@Override
	public void init(NutConfig conf) {
		
		Ioc ioc = conf.getIoc();
		Dao dao = ioc.get(Dao.class);
		Daos.createTablesInPackage(dao, "com.ups.web", false);
		
		// 初始化默认根用户
		if (dao.count(User.class) == 0) {
			UserService us = ioc.get(UserService.class);
			us.add("admin", "123456");
		}

		// 开启数据采集服务
		Server.start();

	}

	@Override
	public void destroy(NutConfig conf) {
		// TODO Auto-generated method stub

	}

}
