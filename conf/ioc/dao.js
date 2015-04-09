var ioc = {
	dao : {
		type : "org.nutz.dao.impl.NutDao",
		args : [ {
			refer : "mysql"
		} ]
	},
	biz : {
		type : "org.nutz.dao.impl.NutDao"
	},
	mysql : {
		type : "com.alibaba.druid.pool.DruidDataSource",
		events : {
			create : "init",
			depose : "close"
		},
		fields : {
			driverClassName : "com.mysql.jdbc.Driver",
			url : "jdbc:mysql://127.0.0.1/upsmgr?useUnicode=true&characterEncoding=utf8",
			username : "root",
			password : "520134",
			testWhileIdle : true,
			validationQuery : "select 1" ,
            maxActive : 100
		}
	},
	sqlserver : {
		type : "com.alibaba.druid.pool.DruidDataSource",
		events : {
			depose : "close 121.40.207.178"
		},
		fields : {
			driverClassName : "com.microsoft.sqlserver.jdbc.SQLServerDriver",
			url : "jdbc:sqlserver://192.168.0.88:1433; DatabaseName=SYS_MGR",
			dbType : "sqlserver",
			username : "wecanadmin",
			password : "wecan2014"
		}
	},
	postgresql : {
		type : "com.alibaba.druid.pool.DruidDataSource",
		events : {
			depose : "close"
		},
		fields : {
			driverClassName : "org.postgresql.Driver",
			url : "jdbc:postgresql://localhost:5432/mydatabase",
			username : "root",
			password : "123456"
		}
	},
	oracle : {
		type : "com.alibaba.druid.pool.DruidDataSource",
		events : {
			depose : "close"
		},
		fields : {
			driverClassName : "oracle.jdbc.driver.OracleDriver",
			url : "jdbc:oracle:thin:@192.168.8.1:1521:yuewei",
			username : "root",
			password : "123456"
		}
	}

};