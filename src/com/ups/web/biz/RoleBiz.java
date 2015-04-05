package com.ups.web.biz;

import java.util.HashMap;
import java.util.Map;

import org.nutz.json.Json;
import org.nutz.lang.Lang;

import com.ups.web.entity.Org;
import com.ups.web.entity.PageData;
import com.ups.web.entity.Role;
import com.ups.web.service.OrgService;
import com.ups.web.service.RoleService;

public class RoleBiz {
	private RoleService service = new RoleService();
	public PageData pd = new PageData();
	
	public void initPage() {
		@SuppressWarnings("unchecked")
		Map<String, Org> orgmap = Lang.collection2map(HashMap.class,
				new OrgService().query(), "id");
		pd.setData("orgmap", Json.toJson(orgmap));
	}

	public void addRole(Role role) {
		boolean ok = false;

		if (service.insert(role)) {
			ok = true;
		}
		pd.setData("result", ok);
	}

	public void editRole(Role role) {
		boolean ok = false;

		if (service.update(role)) {
			ok = true;
		}
		pd.setData("result", ok);
	}

	public void delRole(Role role) {
		boolean ok = false;

		if (service.delete(role)) {
			ok = true;
		}
		pd.setData("result", ok);
	}

	public void listAllRole() {
		pd.setData("list", service.query());
	}
}
