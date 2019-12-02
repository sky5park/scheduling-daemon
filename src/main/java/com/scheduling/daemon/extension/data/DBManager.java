package com.scheduling.daemon.extension.data;

import java.util.List;
import java.util.Map;

/*
 * 작성자 : thaud1324(thaud1324@naver.com)
 * 작성일 : 2019.04
 * 
 * 데이터베이스에 쿼리를 실행하기 위한 인터페이스
 */

public interface DBManager {
	public void open() throws Exception;
	public void close() throws Exception;
	public List<Map<String, Object>> excuteQuery(String sqlText) throws Exception;
	public int update(String sqlText) throws Exception;
	
	public void commit() throws Exception;
	public void rollback() throws Exception;
	
	public boolean isClosed() throws Exception;
}
