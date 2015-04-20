package com.ups.web.service;

import org.nutz.weixin.bean.WxInMsg;
import org.nutz.weixin.bean.WxOutMsg;
import org.nutz.weixin.impl.BasicWxHandler;
import org.nutz.weixin.util.Wxs;

public class MyWxHandler extends BasicWxHandler {
	
	public MyWxHandler(String token) {
		super(token);
	}

	@Override
	public WxOutMsg defaultMsg(WxInMsg msg) {
		return Wxs.respText(null, "消息类型："+msg.getMsgType());
	}
	
	@Override
	public WxOutMsg text(WxInMsg msg) {
		return Wxs.respText(null, "消息类型："+msg.getMsgType()+";内容："+msg.getContent());
	}
	
	@Override
	public WxOutMsg voice(WxInMsg msg) {
		return Wxs.respVoice(null, msg.getMediaId());
	}
	
	@Override
	public WxOutMsg video(WxInMsg msg) {
		return Wxs.respVideo(null, msg.getMediaId(), "您发送的视频：", "很不错的视频资源");
	}
	
	@Override
	public WxOutMsg image(WxInMsg msg) {
		return Wxs.respImage(null, msg.getMediaId());
	}
	
	
}
