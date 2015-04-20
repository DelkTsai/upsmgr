package com.ups.web.service;

import java.util.ArrayList;
import java.util.List;

import org.nutz.weixin.bean.WxMenu;
import org.nutz.weixin.impl.WxApi2Impl;

public class WeixinMenuService extends WxApi2Impl{

	public List<WxMenu> wxm; 
	
	public WeixinMenuService() {
		super();
		this.appid="wx7193c2d6b190a8eb";
		this.encodingAesKey="abcdefghijklmnopqrstuvwxyz0123456789ABCDEFG";
		this.token="loyal";
		this.appsecret = "8cbf3298f61e426b5130f91b6eb9c7f5";
		wxm = new ArrayList<WxMenu>();
		WxMenu wm1 = new WxMenu();
		wm1.setKey("menu1");
		wm1.setName("首页");
		wm1.setType("view");
		wm1.setUrl("http://loyal.wicp.net/upsmgr");
		
		WxMenu wm2 = new WxMenu();
		wm2.setKey("menu2");
		wm2.setName("推广展示");
		wm2.setType("view");
		wm2.setUrl("http://loyal.wicp.net/upsmgr");
		
		WxMenu sm1 = new WxMenu();
		sm1.setKey("menu1sm1");
		sm1.setName("北欧四国");
		sm1.setType("view");
		sm1.setUrl("http://eqxiu.com/s/8utYjsTP");
		WxMenu sm2 = new WxMenu();
		sm2.setKey("menu1sm2");
		sm2.setName("通讯录");
		sm2.setType("view");
		sm2.setUrl("http://eqxiu.com/s/X59IzH2v");
		WxMenu sm3 = new WxMenu();
		sm3.setKey("menu1sm3");
		sm3.setName("五一出游");
		sm3.setType("view");
		sm3.setUrl("http://eqxiu.com/s/Nmx8xauW");
		
		List<WxMenu> sms = new ArrayList<WxMenu>();
		sms.add(sm1);
		sms.add(sm2);
		sms.add(sm3);
		
		wm2.setSubButtons(sms);
		
//		WxMenu wm3 = new WxMenu();
//		wm3.setKey("menu3");
//		wm3.setName("菜单3");
//		wm3.setType("view");
//		wm3.setUrl("http://loyal.wicp.net/upsmgr");
		
		wxm.add(wm1);
		wxm.add(wm2);
//		wxm.add(wm3);
	}
	
	
	
}
