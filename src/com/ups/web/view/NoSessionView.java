package com.ups.web.view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nutz.mvc.view.AbstractPathView;

public class NoSessionView extends AbstractPathView {

	public NoSessionView(String dest) {
		super(dest);
	}

	@Override
	public void render(HttpServletRequest req, HttpServletResponse resp,
			Object obj) throws Exception {
		resp.addHeader("sessionstatus", "timeout");
		resp.flushBuffer();
	}

}
