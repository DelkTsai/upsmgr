package com.ups.web.service;

import java.util.List;

import org.nutz.dao.Dao;
import org.nutz.service.IdNameEntityService;
import org.nutz.trans.Atom;
import org.nutz.trans.Trans;

import com.ups.web.dao.DaoIoc;

public class IdNameSysService<T> extends IdNameEntityService<T> {
	public IdNameSysService() {
		super(DaoIoc.getDao());
	}

	public IdNameSysService(Dao dao) {
		super(dao);
	}

	public boolean insert(T obj) {
		boolean insertOk = false;

		try {
			dao().insert(obj);
			insertOk = true;
		} catch (Exception e) {
			System.out.println("数据插入失败！");
		}
		return insertOk;
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
						dao().insertWith(obj, null);
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
						dao().insertLinks(obj, null);
					}
				}
			});
			insertOk = true;
		} catch (Exception e) {
			insertOk = false;
		}
		return insertOk;
	}

	public boolean update(T obj) {
		boolean insertOk = false;
		try {
			dao().update(obj);
			insertOk = true;
		} catch (Exception e) {

		}
		return insertOk;
	}

	public boolean delete(T obj) {
		boolean insertOk = false;
		try {
			dao().delete(obj);
			insertOk = true;
		} catch (Exception e) {

		}
		return insertOk;
	}

	public List<T> query(){
		return dao().query(getEntityClass(), null);
	}
}
