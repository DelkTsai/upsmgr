package com.ups.server;

import com.ups.server.handler.ServerCache;
import com.ups.server.ioserver.RS232IOServer;
import com.ups.server.ioserver.WifiIOServer;

public class Server {
	public static void start() {

		RS232IOServer rs232 = new RS232IOServer();
		WifiIOServer wifi = new WifiIOServer();
		ServerCache sc = new ServerCache();
		sc.start();
		rs232.start();
		wifi.start();

	}

	public static void stop() {

		RS232IOServer rs232 = new RS232IOServer();
		WifiIOServer wifi = new WifiIOServer();

		rs232.start();
		wifi.start();

	}
}
