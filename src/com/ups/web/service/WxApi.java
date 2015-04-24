package com.ups.web.service;

import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.weixin.impl.WxApi2Impl;

@IocBean
public class WxApi extends WxApi2Impl {
	public WxApi() {
		this.appid="wx7193c2d6b190a8eb";
		this.encodingAesKey="abcdefghijklmnopqrstuvwxyz0123456789ABCDEFG";
		this.token="loyal";
		this.appsecret = "8cbf3298f61e426b5130f91b6eb9c7f5";
	}
}
