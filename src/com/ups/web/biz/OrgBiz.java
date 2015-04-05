package com.ups.web.biz;

import com.ups.web.entity.Org;
import com.ups.web.entity.PageData;
import com.ups.web.service.OrgService;

public class OrgBiz {
	private OrgService service = new OrgService();
	public PageData pd = new PageData();

	public void addOrg(Org org) {
		boolean ok = false;

		if (service.insert(org)) {
			ok = true;
		}
		pd.setData("result", ok);
	}

	public void editOrg(Org org) {
		boolean ok = false;

		if (service.update(org)) {
			ok = true;
		}
		pd.setData("result", ok);
	}

	public void delOrg(Org org) {
		boolean ok = false;

		if (service.delete(org)) {
			ok = true;
		}
		pd.setData("result", ok);
	}

	public void listAllOrg() {
		pd.setData("list", service.query());
	}
}
