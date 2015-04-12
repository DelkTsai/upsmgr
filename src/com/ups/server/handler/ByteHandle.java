package com.ups.server.handler;

import java.util.Date;

import com.ups.web.entity.DeviceData;

//
public class ByteHandle extends Thread {
	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (ServerCache.data.length() >=30) {
				ServerCache.dataUpdate = false;
				byte2Object();
				ServerCache.objUpdate = true;
			}
		}
	}

	// 解析数据包
	private boolean byte2Object() {
		System.out.println(ServerCache.data.substring(0, 30));
		String data = ServerCache.data.substring(0, 30);
		if (!(data.startsWith("FE") && data.endsWith("EF"))) {
			ServerCache.data.delete(0, data.indexOf("FE"));
		} else {
			ServerCache.objList.add(getObject(data));
			ServerCache.data.delete(0, 30);
		}
		return true;
	}

	private DeviceData getObject(String str) {
		DeviceData dd = new DeviceData();

		dd.setDeviceId(str.substring(4, 10));
		dd.setCommunicateMethod(0);
		dd.setOutputVoltage(Integer.parseInt(str.substring(12, 14), 16) + 0.01
				* Integer.parseInt(str.substring(14, 16), 16));
		dd.setBatteryVoltage(Integer.parseInt(str.substring(18, 20), 16) + 0.01
				* Integer.parseInt(str.substring(20, 22), 16));
		dd.setBatteryLoad(Integer.parseInt(str.substring(24, 26), 16) + 0.01
				* Integer.parseInt(str.substring(26, 28), 16));
		dd.setDataTime(new Date());
		dd.setComment("测试数据");
		return dd;
	}
}
