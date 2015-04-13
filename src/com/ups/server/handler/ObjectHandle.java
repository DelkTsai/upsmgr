package com.ups.server.handler;

import org.nutz.dao.Dao;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.json.JsonLoader;


public class ObjectHandle extends Thread {
	
	@Override
	public void run() {
		Ioc ioc = new NutIoc(new JsonLoader("ioc/dao.js"));
		Dao dao = ioc.get(null,"dao");
		while (true) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (ServerCache.objList.size()>=5) {
				dao.insert(ServerCache.objList.subList(0, 5));
				ServerCache.objList.subList(0, 5).clear();
				ServerCache.objUpdate = false;
			}
		}
	}
	
	
}
