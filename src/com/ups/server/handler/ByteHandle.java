package com.ups.server.handler;

public class ByteHandle extends Thread {
	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (ServerCache.data.length()>10) {
				ServerCache.dataUpdate = false;
				byte2Object();
				ServerCache.objUpdate = true;
			}
		}
	}
	
	//解析数据包
	private boolean byte2Object() {
		System.out.println(ServerCache.data.substring(0, 10));
		ServerCache.data.delete(0, 10);
		return true;
	}
}
