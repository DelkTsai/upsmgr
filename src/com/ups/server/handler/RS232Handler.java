package com.ups.server.handler;

import gnu.io.SerialPort;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;

public class RS232Handler implements Runnable {
	
	private SerialPort port = null;
	private InputStream inputStream= null;
	private OutputStream outputStream =null;
	private byte[] b = new byte[1024];
	private boolean runFlag = true;
	
	public RS232Handler() {
		super();
	}

	public RS232Handler(SerialPort port) {
		if (null==port) {
			System.out.println("port is null");
			this.runFlag = false;
			return;
		}
		this.port = port;
	}

	@Override
	public void run() {
		
		try {
			inputStream = port.getInputStream();
			outputStream = port.getOutputStream();
			if (runFlag) {
				if (port==null) {
					System.out.println("Port is closed");
					runFlag = false;
					return;
				}
				int len = -1;
				try {
					if ((len = inputStream.read(b)) != -1) {
//						System.out.println("Port recive:"
//								+ new String(b, 0, len));
//						outputStream.write(b);
//						outputStream.flush();
						ServerCache.data.append(new String(b, 0, len));
						ServerCache.dataUpdate = true;
					} else {
						System.out.println("Port recive:"
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
