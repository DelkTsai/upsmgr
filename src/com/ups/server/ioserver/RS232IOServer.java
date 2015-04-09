package com.ups.server.ioserver;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;

import com.ups.server.handler.RS232Handler;

public class RS232IOServer extends Thread implements SerialPortEventListener {
	// 对串口进行访问控制的核心类
	// 1、确定是否有可用的通信端口
	// 2、为IO操作打开通信端口
	// 3、决定端口的所有权
	// 4、处理端口所有权的争用
	// 5、管理端口所有权变化引发的事件（Event）
	CommPortIdentifier cpi;
	// 设备所有串口集合
	@SuppressWarnings("rawtypes")
	Enumeration ports;
	// 串口对象
	SerialPort port = null;

	// 构造方法、实例化、参数设置
	public RS232IOServer() {

	}

	public void serialEvent(SerialPortEvent event) {
		switch (event.getEventType()) {
		case SerialPortEvent.BI:
			System.out.print("BI\n");
		case SerialPortEvent.OE:
			System.out.print("OE\n");
		case SerialPortEvent.FE:
			System.out.print("FE\n");
		case SerialPortEvent.PE:
			System.out.print("PE\n");
		case SerialPortEvent.CD:
			System.out.print("CD\n");
		case SerialPortEvent.CTS:
			System.out.print("CTS\n");
		case SerialPortEvent.DSR:
			System.out.print("DSR\n");
		case SerialPortEvent.RI:
			System.out.print("RI\n");
		case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
			System.out.println("输出缓冲为空");
			break;
		case SerialPortEvent.DATA_AVAILABLE:
			Thread handler = new Thread(new RS232Handler(port));
			handler.start();
			// System.exit(0);
			break;
		}
	}

	@Override
	public void run() {
		// System.out.println("获取端口实例");
		ports = CommPortIdentifier.getPortIdentifiers();
		while (ports.hasMoreElements()) {
			cpi = (CommPortIdentifier) ports.nextElement();
			if (cpi.getPortType() == CommPortIdentifier.PORT_SERIAL) {
				if (cpi.getName().equals("COM2")) {
					try {
						// 实例化串口对象
						port = (SerialPort) cpi.open("UPSIO", 2000);
						// 设置串口监听
						port.addEventListener(this);
						// 启用串口监听
						port.notifyOnDataAvailable(true);

						System.out
								.println("**************串口监听已打开，等待设备通信**************");

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
