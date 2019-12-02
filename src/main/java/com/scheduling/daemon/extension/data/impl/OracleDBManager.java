package com.scheduling.daemon.extension.data.impl;

import java.sql.SQLException;

import com.scheduling.daemon.extension.data.AbstractDBManager;


/*
 * 
 * 오라클 데이터베이스를 지원하기 위한 클래스
 */

public class OracleDBManager extends AbstractDBManager {

	
	private String url = null;
	private String user = null;
	private String pwd = null;
	private String driverName = null;
	
	public OracleDBManager(String driver, String url, String user, String pwd) throws SQLException {
		this.driverName = driver;
		this.url = url;
		this.user = user;
		this.pwd = pwd;
	}

	@Override
	protected String getDriverName() {
		// TODO Auto-generated method stub
		return this.driverName;
	}

	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return this.url;
	}


	@Override
	protected String getUser() {
		// TODO Auto-generated method stub
		return this.user;
	}


	@Override
	protected String getPassword() {
		// TODO Auto-generated method stub
		return this.pwd;
	}
}
