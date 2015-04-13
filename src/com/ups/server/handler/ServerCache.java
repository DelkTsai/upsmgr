package com.ups.server.handler;

import java.util.ArrayList;
import java.util.List;

import com.ups.web.entity.DeviceData;

public class ServerCache extends Thread {
	public static StringBuilder data = new StringBuilder();
	public static List<DeviceData> objList = new ArrayList<DeviceData>();
	public static boolean dataUpdate = false;
	public static boolean objUpdate = false;
	
	@Override
	public void run() {
		ByteHandle bh = new ByteHandle();
		ObjectHandle oh = new ObjectHandle();
		bh.setName("数据包解析及对象化线程");
		oh.setName("对象持久化线程");
		bh.start();
		oh.start();
	}
}
