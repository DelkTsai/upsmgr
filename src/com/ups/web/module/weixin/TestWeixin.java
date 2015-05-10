package com.ups.web.module.weixin;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.nutz.mvc.View;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Filters;
import org.nutz.weixin.spi.WxHandler;
import org.nutz.weixin.util.Wxs;

import com.ups.web.service.MyWxHandler;

@At("/weixin")
public class TestWeixin {

	String encodingAesKey = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFG";
	String token = "loyal";
	String appId = "wx7193c2d6b190a8eb";
	String appsecret = "8cbf3298f61e426b5130f91b6eb9c7f5";

	protected WxHandler wxHandler;

	@At({ "/","/?" })
	@Fail("http:200")
	@Filters
	public View msgIn(String key, String signature, String timestamp,
			String nonce, String echostr, HttpServletRequest req)
			throws IOException {
		if (Wxs.check(token, signature, timestamp, nonce))
			return Wxs.handle(getWxHandler(key), req, key);
		return null;
	}

	public WxHandler getWxHandler(String key) {
		wxHandler = new MyWxHandler(token);
		return wxHandler;
	}

}
