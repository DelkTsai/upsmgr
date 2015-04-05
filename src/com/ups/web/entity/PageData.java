package com.ups.web.entity;

import java.util.HashMap;
public class PageData extends HashMap<String, Object>{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Object getData(String key) {
		return this.get(key);
	}
	
	public Object setData(String key,Object obj) {
		return this.put(key, obj);
	}
	
}
