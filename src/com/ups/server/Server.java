package com.ups.server;

import com.ups.server.handler.ServerCache;
import com.ups.server.ioserver.RS232IOServer;
import com.ups.server.ioserver.WifiIOServer;

public class Server {
	public static void start() {

		RS232IOServer rs232 = new RS232IOServer();
		WifiIOServer wifi = new WifiIOServer();
		ServerCache sc = new ServerCache();
		rs232.setName("RS232数据采集线程");
		wifi.setName("WIFI数据采集线程");
		sc.setName("数据缓存处理线程");
		sc.start();
		rs232.start();
		wifi.start();

	}

	public static void stop() {

		RS232IOServer rs232 = new RS232IOServer();
		WifiIOServer wifi = new WifiIOServer();
		rs232.setName("RS232数据采集线程");
		wifi.setName("WIFI数据采集线程");
		rs232.start();
		wifi.start();

	}
}
