package com.ups.web.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.nutz.mvc.ActionContext;
import org.nutz.mvc.ActionFilter;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.View;
import org.nutz.mvc.view.ServerRedirectView;

import com.ups.web.view.NoSessionView;

public class SessionCheck implements ActionFilter {
	private String name;
	private String path;

	public SessionCheck(String name, String path) {
		this.name = name;
		this.path = path;
	}

	@Override
	public View match(ActionContext context) {
		HttpSession session = Mvcs.getHttpSession(false);
		HttpServletRequest req = context.getRequest();
		if (session == null || null == session.getAttribute(name)){
			if (req.getHeader("x-requested-with") != null && req.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest"))  
				return new NoSessionView(path);
			return new ServerRedirectView(path);
		}
		return null;
	}

}
