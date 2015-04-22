package com.ups.web.service;

import java.util.Date;
import java.util.List;

import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.IocBean;

import com.ups.web.bean.Role;

//用户Service，处理用户的业务逻辑，数据库访问
@IocBean(fields="dao")
public class RoleService extends BaseService<Role> {

	// 初始化数据库访问对象Dao

	// 数据库分页查询
	public void find(Pager pager) {
		List<Role> list = null;
		list = dao().query(Role.class,null,pager);
		pager.setRecordCount(dao().count(Role.class));
		rs.setv("ok", true).setv("pager",pager ).setv("list", list);
	}

	// 数据库添加操作
	public void add(Role role) {
		rs.setv("ok",false).setv("msg", "添加失败，角色名：" + role.getName());
		role.setCreateTime(new Date());
		dao().insert(role);
		rs.setv("ok",true).setv("msg", "添加成功，角色名：" + role.getName());
	}

	// 数据库更新操作
	public void edit(Role role) {
		rs.setv("ok",false).setv("msg", "修改失败，角色名：" + role.getName());
		role.setUpdateTime(new Date());
		dao().update(role);
		rs.setv("ok",true).setv("msg", "修改成功，角色名：" + role.getName());
	}

	// 数据库删除数据操作
	public void delete(Role role) {
		rs.setv("ok",false).setv("msg", "删除失败，角色名：" + role.getName());
		dao().delete(role);
		rs.setv("ok",true).setv("msg", "删除成功，角色名：" + role.getName());
	}

	
}
