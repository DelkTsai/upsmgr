package com.ups.web.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.shiro.crypto.hash.Sha256Hash;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.lang.random.R;

import com.ups.web.bean.Role;
import com.ups.web.bean.User;

//用户Service，处理用户的业务逻辑，数据库访问
@IocBean(fields = "dao")
public class UserService extends BaseService<User> {

	// 初始化数据库访问对象Dao

	// 数据库分页查询
	public void find(Pager pager, User user) {
		List<User> list = null;
		Condition cnd = null;
		if (user == null)
			cnd = Cnd.orderBy().desc("createTime");
		else
			cnd = Cnd.where("username",
					Strings.isBlank(user.getUsername()) ? "<>" : "=",
					user.getUsername());
		pager.setRecordCount(dao().count(User.class, cnd));
		list = dao().query(User.class, cnd, pager);
		for (User user2 : list) {
			user2 = dao().fetchLinks(user2, "roles");
		}
		rs.setv("ok", true).setv("pager", pager).setv("list", list)
				.setv("roles", dao().query(Role.class, null));
	}

	// 数据库添加操作
	public void add(User user) {
		rs.setv("ok", false).setv("msg", "添加失败，用户名：" + user.getUsername());
		user.setSalt(R.UU16());
		user.setPassword(new Sha256Hash("123456", user.getSalt()).toHex());
		user.setCreateTime(new Date());
		user.setUpdateTime(new Date());
		dao().insert(user);
		rs.setv("ok", true).setv("msg", "添加成功，用户名：" + user.getUsername());
	}

	// 创建初始化用户
	public void add(String username, String password) {

		rs.setv("ok", false).setv("msg", "用户添加失败");

		User user = new User();
		user.setUsername(username.trim());
		user.setSalt(R.UU16());
		user.setPassword(new Sha256Hash(password, user.getSalt()).toHex());
		user.setCreateTime(new Date());
		user.setUpdateTime(new Date());
		dao().insert(user);

		rs.setv("ok", true).setv("msg", "添加成功，用户名：" + user.getUsername());
	}

	// 数据库更新操作
	public void edit(User user) {
		rs.setv("ok", false).setv("msg", "修改失败，用户名：" + user.getUsername());
		user.setUpdateTime(new Date());
		dao().update(user);
		rs.setv("ok", true).setv("msg", "修改成功，用户名：" + user.getUsername());
	}

	// 数据库删除数据操作
	public void delete(User user) {
		rs.setv("ok", false).setv("msg", "删除失败，用户名：" + user.getUsername());
		dao().delete(user);
		rs.setv("ok", true).setv("msg", "删除成功，用户名：" + user.getUsername());
	}

	// 用户登录操作
	public void login(User user) {
		String password = user.getPassword();
		user = dao().fetch(User.class, user.getUsername());
		if (user == null) {
			rs.setv("ok", false).setv("msg", "用户名不存在");
		} else if (!new Sha256Hash(password, user.getSalt()).toHex().equals(user.getPassword())) {
			rs.setv("ok", false).setv("msg", "密码错误");
		} else {
			rs.setv("ok", true).setv("user", user);
		}
	}

	// 用户密码更改操作
	public void changePwd(String oldpassword, String newpassword,
			String repassword, HttpSession session) {

		User user = (User) session.getAttribute("curruser");
		if ("loyal".equals(user.getUsername())) {
			rs.setv("ok", false).setv("msg", "该用户密码不允许修改");
		} else if (!new Sha256Hash(oldpassword, user.getSalt()).toHex().equals(user.getPassword())) {
			rs.setv("ok", false).setv("msg", "原密码错误");
		} else if (newpassword == null || newpassword.equals("")) {
			rs.setv("ok", false).setv("msg", "新密码不能为空");
		} else if (!newpassword.equals(repassword)) {
			rs.setv("ok", false).setv("msg", "两次密码输入不一致");
		} else {
			rs.setv("ok", false).setv("msg", "密码保存失败");
			user.setSalt(R.UU16());
			user.setPassword(new Sha256Hash(newpassword, user.getSalt()).toHex());
			user.setUpdateTime(new Date());
			dao().update(user, "^(password|salt|updateTime)$");
			rs.setv("ok", true).setv("msg", "密码修改成功，重新登录生效");
		}

	}
}
