package com.ups.web.service;

import org.nutz.dao.Dao;
import org.nutz.lang.util.NutMap;

import com.ups.web.entity.Page;

public class BaseService {
	protected Dao dao;
	public Page page;
	public NutMap rs;
	public boolean isSuccess;
	public BaseService() {
		super();
	}
	public BaseService(Dao dao) {
		this.dao = dao;
		this.page = new Page();
		this.isSuccess = false;
		this.rs = new NutMap();
	}
}
