package com.ups.web.dao;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.nutz.dao.Dao;
import org.nutz.dao.impl.NutDao;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.json.JsonLoader;

public class DaoIoc {

	private static Ioc ioc;
	
	public static Dao getDao() {
		if (ioc==null) ioc= new NutIoc(new JsonLoader("dao.js"));
		Dao dao = ioc.get(NutDao.class, "dao");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d H:m:s.S");
		System.out.println(sdf.format(new Date())+" INFO [" + dao.toString()+"]");
		return dao;
	}

}
