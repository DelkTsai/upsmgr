package com.ups.server.handler;

public class ObjectHandle extends Thread {
	@Override
	public void run() {
		ServerCache.objUpdate = false;
		if (ServerCache.objList.size()>=5) {
			
		}
//		ServerCache.objList.get(0);
	}
}
