package com.scheduling.daemon.extension;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.scheduling.daemon.extension.http.HttpUtil;
import com.scheduling.daemon.extension.server.Server;
import com.scheduling.daemon.extension.server.impl.ScheduleServer;
import com.scheduling.daemon.extension.util.FileUtil;
import com.scheduling.daemon.extension.util.IOUtil;
import com.scheduling.daemon.extension.util.PropertiesUtil;

/**
 * 
 *애플리케이션 구조
 *
 *   /-- 애플리케이션 루트
 *      |- bin  -> 실행파일 
 *      |- conf -> 환경설정파일
 *      |- logs -> 로그파
 *      |- lib  -> 참조 라이브러리 파
 */
public class DaemonMainApp 
{
	private static Log logger = null;// LogFactory.getLog(DaemonMainApp.class);

	private final static String CONF_PATH_NAME = "conf";
	private final static String BIN_PATH_NAME = "bin";
	private final static String LOG_PATH_NAME = "logs";
	
	private final static String PROPERTIES_FILE_EXTENSION_NAME = "properties";
	
	private final static String APPLICATION_NAME = "DaemonMainApp";
	public static void main (String[] args) throws Exception {

		try {
			configEnvironment();
			logger = LogFactory.getLog(DaemonMainApp.class);
			printEnvironment();
			
			writeProcessId();
			
			
	        Server server = createScheduleServer();
	        server.start();
	        
		}catch(Exception e) {
			e.printStackTrace();
		}
    }
	
	
	private static void writeProcessId() throws Exception {
		
		try {
			String binPath = System.getProperty("user.dir");
			
			String pidFileName = binPath + File.separator + APPLICATION_NAME + ".pid";
			
			logger.info(pidFileName);
			//프로세스 아이디 기록
			String processId = getProcessId();
			
			logger.info("Process id => " + processId);
			
			FileUtil.write(pidFileName, processId);
		}catch(Exception e) {
			throw e;
		}
				
	}
	private static String getProcessId() {
		
		String processId = null;
		try {
			RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
			String processName= runtime.getName();
			processId = processName.substring(0, processName.indexOf("@"));
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		return processId;
	}
	
	private static Server createScheduleServer() {
		Server server = null;
		try {
			
			String confPath = System.getProperty("daemonConfPath.name");
			server = new ScheduleServer();
	        Map<String, Object> config = new HashMap<String, Object>();
	        
	    	//환경설정파일 읽기
			Map<String, String> properties = PropertiesUtil.loadConfigureProperties(confPath, PROPERTIES_FILE_EXTENSION_NAME);
			if(properties != null && properties.size() > 0) {
				properties.forEach((k, v)->config.put(k, v));
				properties.forEach((k, v)->System.setProperty(k, v));
			}

			config.forEach((k,v)->logger.info("Key : " + k + " -> Value : " + v));
	        server.init(config);

		}catch(Exception e) {
			logger.error(e);
		}
		return server;
	}
	
	private static void configEnvironment() {
		//애플리케이션 실행 경로
		String userDir = System.getProperty("user.dir");
		
		String binPath = IOUtil.getParentDirPath(userDir + File.separator);
		
		String logPath =  binPath + File.separator + LOG_PATH_NAME + File.separator;
		String confPath = binPath + File.separator + CONF_PATH_NAME + File.separator;
		
		//애플리케이션을 구동하기 위한 각종 경로들을 시스템 환경설정에 등록한다.
		//애플리케이션 실행 루트 경로
		System.setProperty("daemonMainApp.home", userDir);
		//애플리케이션 환경설정 경로
		System.setProperty("daemonConfPath.name", confPath);
		//로그파일의 위치경로
		System.setProperty("daemonLogPath.name", logPath);
		//로그파일명
		System.setProperty("daemonLogFile.name", "daemon");
	}
	
	private static void printEnvironment() {
		logger.info(System.getProperty("user.dir"));
		logger.info(System.getProperty("daemonMainApp.home"));
		logger.info(System.getProperty("daemonConfPath.name"));
		logger.info(System.getProperty("daemonLogPath.name"));
		logger.info(System.getProperty("daemonLogFile.name"));
	}
}
