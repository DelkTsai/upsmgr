package com.ups.web;

import java.util.Date;

import org.nutz.dao.Dao;
import org.nutz.dao.util.Daos;
import org.nutz.ioc.Ioc;
import org.nutz.mvc.NutConfig;
import org.nutz.mvc.Setup;

import sun.awt.windows.ThemeReader;

import com.ups.server.Server;
import com.ups.server.handler.WifiHandler;
import com.ups.server.ioserver.RS232IOServer;
import com.ups.server.ioserver.WifiIOServer;
import com.ups.web.entity.User;
import com.ups.web.tool.DESKey;

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
	            user.setUsername("loyal");
	            user.setPassword(des.encrypt("520134"));
	            user.setNickname("最高管理员");
	            user.setCreateTime(new Date());
	            user.setUpdateTime(new Date());
	            dao.insert(user);
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Server.start();
		
	}

	@Override
	public void destroy(NutConfig conf) {
		// TODO Auto-generated method stub

	}

}
