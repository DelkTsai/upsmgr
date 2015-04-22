package com.ups.web.service;

import org.nutz.dao.Dao;
import org.nutz.lang.util.NutMap;
import org.nutz.service.IdNameEntityService;

public class BaseService<T> extends IdNameEntityService<T> {
	public NutMap rs = new NutMap();
	public BaseService() {
		super();
	}
	
	public BaseService(Dao dao){
		super(dao);
	}
	
	 public BaseService(Dao dao, Class<T> entityType) {
	        super(dao, entityType);
	    }
}
