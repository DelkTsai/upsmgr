package com.ups.web.service;

import java.util.List;

import org.nutz.dao.Dao;
import org.nutz.service.*;
import org.nutz.trans.Atom;
import org.nutz.trans.Trans;

import com.ups.web.dao.DaoIoc;

public class IdSysService<T> extends IdEntityService<T>{
	
	public IdSysService() {
		super(DaoIoc.getDao());
	}
	
	public IdSysService(Dao dao) {
		super(dao);
	}
	
	public boolean insert(final List<T> objs) {
		boolean insertOk = false;
		try {
			Trans.exec(new Atom() {
				@Override
				public void run() {
					for (T obj : objs) {
						dao().insert(obj);
					}
				}
			});
			insertOk = true;
		} catch (Exception e) {
			insertOk = false;
		}
		return insertOk;
	}
	
	public boolean insertWith(final List<T> objs) {
		boolean insertOk = false;
		try {
			Trans.exec(new Atom() {
				@Override
				public void run() {
					for (T obj : objs) {
						dao().insertWith(obj,null);
					}
				}
			});
			insertOk = true;
		} catch (Exception e) {
			insertOk = false;
		}
		return insertOk;
	}
	
	public boolean insertLinks(final List<T> objs) {
		boolean insertOk = false;
		try {
			Trans.exec(new Atom() {
				@Override
				public void run() {
					for (T obj : objs) {
						dao().insertLinks(obj,null );
					}
				}
			});
			insertOk = true;
		} catch (Exception e) {
			insertOk = false;
		}
		return insertOk;
	}
}
