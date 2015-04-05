package com.ups.web.module.sys;

import org.nutz.mvc.adaptor.PairAdaptor;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.ups.web.biz.OrgBiz;
import com.ups.web.entity.Org;

@AdaptBy(type = PairAdaptor.class)
@At("/sysmgr/org")
public class OrgmgrModule {
	private OrgBiz biz = new OrgBiz();

	@At("/")
	@Ok("jsp:page.sysmgr.org")
	public Object org() {

		return "";
	}

	@At("/add")
	@Ok("json")
	@Fail("jsp:page.sysmgr.org")
	public Object add(@Param("..") Org user) {
		biz.addOrg(user);
		return biz.pd.getData("result");
	}

	@At("/edit")
	@Ok("json")
	@Fail("jsp:page.sysmgr.org")
	public Object edit(@Param("..") Org user) {
		biz.editOrg(user);
		return biz.pd.getData("result");
	}

	@At("/del")
	@Ok("json")
	@Fail("jsp:page.sysmgr.org")
	public Object del(@Param("..") Org user) {
		biz.delOrg(user);
		return biz.pd.getData("result");
	}

	@At("/list")
	@Ok("jsp:page.sysmgr.orglist")
	@Fail("jsp:page.sysmgr.org")
	public Object list() {
		biz.listAllOrg();
		return biz.pd.getData("list");
	}

}
