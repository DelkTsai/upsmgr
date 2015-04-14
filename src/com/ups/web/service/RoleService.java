package com.ups.web.service;

import java.util.List;

import org.nutz.dao.Dao;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.IocBean;

import com.ups.web.entity.Role;

//用户Service，处理用户的业务逻辑，数据库访问
@IocBean(args = { "refer:dao" })
public class RoleService extends BaseService {

	// 初始化数据库访问对象Dao
	public RoleService(Dao dao) {
		super(dao);
	}

	// 数据库分页查询
	public void find(Pager pager) {
		List<Role> list = null;
		list = dao.query(Role.class,null,pager);
		pager.setRecordCount(dao.count(Role.class));
		page.setPager(pager);
		page.setData(list);
		isSuccess = true;
	}

	// 数据库添加操作
	public void add(Role role) {
		dao.insert(role);
		isSuccess = true;
	}

	// 数据库更新操作
	public void edit(Role role) {
		dao.update(role);
		isSuccess = true;
	}

	// 数据库删除数据操作
	public void delete(Role role) {
		dao.delete(role);
		isSuccess = true;
	}

	
}
