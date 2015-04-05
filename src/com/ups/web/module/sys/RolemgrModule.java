package com.ups.web.module.sys;

import org.nutz.mvc.adaptor.PairAdaptor;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.ups.web.biz.RoleBiz;
import com.ups.web.entity.Role;

@AdaptBy(type = PairAdaptor.class)
@At("/sysmgr/role")
public class RolemgrModule {
	private RoleBiz biz = new RoleBiz();

	@At("/")
	@Ok("jsp:page.sysmgr.role")
	public Object role() {
		biz.initPage();
		return biz.pd;
	}

	@At("/add")
	@Ok("json")
	@Fail("jsp:page.sysmgr.role")
	public Object add(@Param("..") Role role) {
		biz.addRole(role);
		return biz.pd.getData("result");
	}

	@At("/edit")
	@Ok("json")
	@Fail("jsp:page.sysmgr.role")
	public Object edit(@Param("..") Role role) {
		biz.editRole(role);
		return biz.pd.getData("result");
	}

	@At("/del")
	@Ok("json")
	@Fail("jsp:page.sysmgr.role")
	public Object del(@Param("..") Role role) {
		biz.delRole(role);
		return biz.pd.getData("result");
	}

	@At("/list")
	@Ok("jsp:page.sysmgr.rolelist")
	@Fail("jsp:page.sysmgr.role")
	public Object list() {
		biz.listAllRole();
		return biz.pd.getData("list");
	}

}
