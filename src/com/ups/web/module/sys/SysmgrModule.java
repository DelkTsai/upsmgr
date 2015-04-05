package com.ups.web.module.sys;

import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

@At("/sys")
public class SysmgrModule {
	@At("/")
	@Ok("jsp:page.sysmgr")
	public Object index() {
		
		return "";
	}
	@At("/org")
	@Ok("jsp:page.sysmgr")
	public Object org() {
		
		return "";
	}
}
