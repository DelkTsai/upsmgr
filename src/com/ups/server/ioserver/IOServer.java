package com.ups.server.ioserver;

public interface IOServer {
	
	public void init();
	
	public String read(); 
	
	public boolean send(String data); 
	
	public void close();
}
