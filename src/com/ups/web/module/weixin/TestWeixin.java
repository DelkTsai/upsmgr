package com.ups.web.module.weixin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nutz.json.Json;
import org.nutz.mvc.View;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;
import org.nutz.weixin.aes.AesException;
import org.nutz.weixin.aes.WXBizMsgCrypt;
import org.nutz.weixin.spi.WxHandler;
import org.nutz.weixin.util.Wxs;

@At({ "/weixin" })
public class TestWeixin {

	String encodingAesKey = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFG";
	String token = "loyal";
	String appId = "wx7193c2d6b190a8eb";

	protected WxHandler wxHandler;

//	@At({ "/" })
//	@Fail("http:200")
//	@Filters
//	@Ok("void")
//	public void verify(String signature, String timestamp, String nonce,
//			String echostr, HttpServletResponse response) throws IOException {
//		WXBizMsgCrypt pc = null;
//		try {
//			pc = new WXBizMsgCrypt(token, encodingAesKey, appId);
//
//			PrintWriter out = response.getWriter();
//			out.print(pc.verifyUrl(signature, timestamp, nonce, echostr));
//			out.close();
//			out = null;
//		} catch (AesException e) {
//			e.printStackTrace();
//		}
//	}

	@At({ "/"})
	@Fail("http:200")
	@Filters
	public View msgIn(String MsgType, HttpServletRequest req) throws IOException {
		System.out.println(MsgType);
		return Wxs.handle(getWxHandler(MsgType), req, MsgType);
	}

	public WxHandler getWxHandler(String key) {
		return wxHandler;
	}

}
