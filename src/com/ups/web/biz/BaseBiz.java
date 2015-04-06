package com.ups.web.biz;

import org.nutz.dao.Dao;

import com.ups.web.entity.Page;

public class BaseBiz {
	protected Dao dao;
	public Page page;
	public boolean isSuccess;
	public BaseBiz() {
		super();
	}
	public BaseBiz(Dao dao) {
		this.dao = dao;
		this.page = new Page();
		this.isSuccess = false;
	}
}
