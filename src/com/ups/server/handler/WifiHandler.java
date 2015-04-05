package com.ups.server.handler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;

public class WifiHandler extends Thread {
	private Socket socket = null;
	private boolean runFlag = true;
	private InputStream inputStream = null;
	private OutputStream outputStream = null;
	byte[] b = new byte[1024];

	public WifiHandler(Socket socket) {
		if (null == socket) {
			this.runFlag = false;
		}
		this.socket = socket;
	}

	@Override
	public void run() {
		if (null == socket) {
			System.out.println("socket is null");
			return;
		}
		try {
			inputStream = socket.getInputStream();
			outputStream = socket.getOutputStream();
			while (runFlag) {
				if (socket.isClosed()) {
					System.out.println("socket is closed");
					runFlag = false;
					return;
				}
				int len = -1;
				try {
					if ((len = inputStream.read(b)) != -1) {
//						System.out.println("Server recive:"
//								+ new String(b, 0, len));
//						outputStream.write(b);
//						outputStream.flush();
						ServerCache.data.append(new String(b, 0, len));
						ServerCache.dataUpdate = true;
					} else {
						System.out.println("Server recive:"
								+ new String(b, 0, len));
					}
				} catch (SocketException e) {
					e.printStackTrace();
					return;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			return;
		} catch (Exception e) {
			return;
		}

	}
}