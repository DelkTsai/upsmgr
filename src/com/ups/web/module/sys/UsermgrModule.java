package com.ups.web.module.sys;

import org.nutz.dao.Dao;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.mvc.adaptor.PairAdaptor;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.ups.web.biz.UserBiz;
import com.ups.web.entity.Result;
import com.ups.web.entity.User;

@AdaptBy(type = PairAdaptor.class)
@At("/sys/user")
@IocBean
public class UsermgrModule {
	@Inject
	protected Dao dao;
	private UserBiz biz;
	private Result rs = new Result();

	@At("/")
	@Ok("jsp:page.sysmgr")
	public Object index() {
		biz = new UserBiz(dao);
		Pager pager = new Pager();
		pager.setPageNumber(1);
		pager.setPageSize(5);
		biz.find(pager,null);
		return Json.toJson(biz.page);
	}

	@At("/list")
	@Ok("json")
	public Object list(@Param("..") Pager pager,@Param("..") User user) {
		biz.find(pager,user);
		return biz.page;
	}

	@At("/add")
	@Ok("json")
	public Object add(@Param("..") User user) {
		biz.add(user);
		if (biz.isSuccess) {
			rs.setSuccess(true);
			rs.setMsg("添加成功，用户名：" + user.getUsername());
		} else {
			rs.setSuccess(false);
			rs.setMsg("添加失败，用户名：" + user.getUsername());
		}
		return rs;

	}

	@At("/edit")
	@Ok("json")
	public Object edit(@Param("..") User user) {
		biz.edit(user);
		if (biz.isSuccess) {
			rs.setSuccess(true);
			rs.setMsg("修改成功，用户名：" + user.getUsername());
		} else {
			rs.setSuccess(false);
			rs.setMsg("修改失败，用户名：" + user.getUsername());
		}
		return rs;
	}

	@At("/delete")
	@Ok("json")
	public Object delete(@Param("..") User user) {
		biz.delete(user);
		if (biz.isSuccess) {
			rs.setSuccess(true);
			rs.setMsg("删除成功，用户名：" + user.getUsername());
		} else {
			rs.setSuccess(false);
			rs.setMsg("删除失败，用户名：" + user.getUsername());
		}
		return rs;
	}
}
