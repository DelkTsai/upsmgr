package com.ups.web.service;

import java.util.List;

import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.IocBean;

import com.ups.web.entity.Menu;

//用户Service，处理用户的业务逻辑，数据库访问
@IocBean
public class MenuService extends BaseService {

	// 初始化数据库访问对象Dao

	// 数据库分页查询
	public void find() {
		List<Menu> list=null;
		list = dao.query(Menu.class, Cnd.where("pmenu", "=", "0"));
		for (Menu menu : list) {
			menu.setSubMenus(dao.query(Menu.class, Cnd.where("pmenu", "=", menu.getId())));
			menu.setExpand(false);
			menu.setActive(false);
			if (menu.getSubMenus().isEmpty()) {
				menu.setHasChild(false);
			}else{
				menu.setHasChild(true);
			}
		}
		rs.setv("ok", true).setv("list", list);
	}

	// 数据库添加操作
	public void add(Menu menu) {
		dao.insert(menu);
		rs.setv("ok", true);
	}

	// 数据库更新操作
	public void edit(Menu menu) {
		dao.update(menu);
		rs.setv("ok", true);
	}

	// 数据库删除数据操作
	public void delete(Menu menu) {
		dao.delete(menu);
		rs.setv("ok", true);
	}

	// 用户登录操作
	
}
