package com.ups.server.ioserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.ups.server.handler.WifiHandler;

public class WifiIOServer extends Thread implements IOServer {
	@Override
	public void init() {
		try {
			ServerSocket serverSocket = new ServerSocket(8888);
			Socket socket = null;
			int count = 0;
			System.out.println("**************服务器已启动，等待客户端连接**************");
			while (true) {
				socket = serverSocket.accept();
				WifiHandler serverHandler = new WifiHandler(socket);
				serverHandler.start();
				count++;
//				System.out.println("客户端连接数量" + count);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println();
	}

	@Override
	public void run() {
		init();
	};

	@Override
	public String read() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean send(String data) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub

	}

}
