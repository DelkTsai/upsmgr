package com.ups.server.handler;

import java.util.ArrayList;
import java.util.List;

public class ServerCache extends Thread {
	public static StringBuilder data = new StringBuilder();
	public static List<Object> objList = new ArrayList<Object>();
	public static boolean dataUpdate = false;
	public static boolean objUpdate = false;
	
	public ServerCache() {
		
	}
	
	@Override
	public void run() {
		ByteHandle bh = new ByteHandle();
		ObjectHandle oh = new ObjectHandle();
		bh.start();
		oh.start();
	}
}
