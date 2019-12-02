package com.scheduling.daemon.extension.data;

import com.scheduling.daemon.extension.constants.DBProviderEnum;
import com.scheduling.daemon.extension.data.impl.OracleDBManager;
/*
 * 데이터베이스를 관리하는 매니저 클래스를 생성하기 위한 클래
 * 
 */
public class DBManagerFactory {

	
	public static DBManager createInstance(DBProviderEnum dbProvider, String driver, String url, String user, String pwd) throws Exception {
		DBManager manager = null;
		if(dbProvider == DBProviderEnum.Oracle) {
			manager = new OracleDBManager(driver, url, user, pwd);
		}
		return manager;
	}
}
