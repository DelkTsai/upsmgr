package com.ups.web.service;

import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.lang.util.NutMap;

public class BaseService {
	@Inject protected Dao dao;
	public NutMap rs = new NutMap();
	public BaseService() {
		rs.setv("ok", false);
	}
}
