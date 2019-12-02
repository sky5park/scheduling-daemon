package com.scheduling.daemon.extension.data;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.scheduling.daemon.extension.util.EnvUtil;
import com.scheduling.daemon.extension.util.StringUtil;


/*
 * 작성자 : shenghwan.byun(shenghwan.byun@gmail.com)
 * 작성일 : 2019.04
 * 
 * 데이터 베이스 접속을 위한 추상클래스
 */

public abstract class AbstractDBManager implements DBManager{

	protected final static Log logger = LogFactory.getLog(AbstractDBManager.class);
	private Connection connection = null;

	public AbstractDBManager() {
		
	}
	
	protected abstract String getDriverName();
	protected abstract String getUrl();
	protected abstract String getUser();
	protected abstract String getPassword();
	
	protected Connection getConnection() {
		return this.connection;
	}
	public void open() throws Exception {
		this.open(true);
	}

	public void open(boolean authCommit) throws Exception {
		
		try {
			Class.forName(this.getDriverName());
			this.connection = DriverManager.getConnection(this.getUrl(), this.getUser(), this.getPassword());
			this.connection.setAutoCommit(authCommit);
		
		}catch(Exception e) {
			logger.error(e);
			throw e;
		}
	}
	
	public void close() throws Exception {
		try {
			if(this.connection != null) {
				
				if(!this.connection.isClosed()) this.connection.close();
				this.connection = null;
			}
		}catch(Exception e) {
			logger.error(e);
			throw e;
		}
	}
	
	public boolean isClosed() throws Exception
	{
		boolean bResult = false;
		
		if(this.connection != null)
			bResult = this.connection.isClosed();
		
		return bResult;
	}
	
	//쿼리 실행
	public List<Map<String, Object>> excuteQuery(String sqlText) throws Exception {
		
		long tick = EnvUtil.getTickCount();
		List<Map<String, Object>> list = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			
			if(this.connection == null) {
				throw new Exception("Database not connected!!");
			}
			
			list = new ArrayList<Map<String, Object>>();
			stmt = connection.createStatement();
			
			ResultSetMetaData rsmd = null;
			logger.info("SQL Text => " + sqlText);
			rs = stmt.executeQuery(sqlText);
			rsmd = rs.getMetaData();
			Map<String, Object> row = null;
			Object v = null;
			String colName = null;
			
			while(rs.next()) {
				row = new HashMap<String, Object>();
				for(int c=0; c < rsmd.getColumnCount(); c++) {
					colName = rsmd.getColumnLabel(c+1);
					v = rs.getObject(colName);
					row.put(colName, v);
				}
				list.add(row);
			}
		}catch(Exception e) {
			logger.error(e);
			throw e;
		}finally {
			if(rs != null && !rs.isClosed()) rs.close();
			if(stmt != null && !stmt.isClosed()) stmt.close();
		}
		logger.info(StringUtil.Format("Query Execute Elapsed Time => {0}", EnvUtil.getTickCount() - tick));
		
		return list;
	}
	
	@Override
	public int update(String sqlText) throws Exception {
		// TODO Auto-generated method stub
		long tick = EnvUtil.getTickCount();
		int iResult = 0;
		Statement stmt = null;
		try {
			
			if(this.connection == null) {
				throw new Exception("Database not connected!!");
			}
			
			stmt = this.connection.createStatement();
			
			iResult = stmt.executeUpdate(sqlText);
			
		}catch(Exception e) {
			logger.error(e);
			throw e;
		}finally {
		
			if(stmt != null && !stmt.isClosed()) stmt.close();
		}
		logger.info(StringUtil.Format("Query Update Elapsed Time => {0}", EnvUtil.getTickCount() - tick));
		return iResult;
	}
	
	@Override
	public void commit() throws Exception {
		// TODO Auto-generated method stub
		if(this.connection != null && !this.connection.isClosed()) {
			this.connection.commit();
		}
	}


	@Override
	public void rollback() throws Exception {
		// TODO Auto-generated method stub
		if(this.connection != null && !this.connection.isClosed()) {
			this.connection.rollback();
		}
	}
}
