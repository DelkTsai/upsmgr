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
		bh.start();
		oh.start();
	}
}
