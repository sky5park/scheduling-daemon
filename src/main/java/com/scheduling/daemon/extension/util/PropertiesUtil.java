package com.scheduling.daemon.extension.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/*
 * 작성자 : thaud1324(thaud1324@naver.com)
 * 작성일 : 2019.04
 * 
 */

public class PropertiesUtil {

	
	public static Properties getProperties(String fileName) throws Exception{
		Properties props = null;
		FileInputStream fs = null;
		
		try {
			fs = new FileInputStream(fileName);
			props = new Properties();
			props.load(fs);
			
		}catch(Exception e) {
			throw e;
		}finally {
			if(fs != null) {
				fs.close();
			}
		}
		
		return props;
	}
	
	public static Map<String, String> loadConfigureProperties(String configPath, String extensionName) throws Exception{
		Map<String, String> map = null;
		
		/*
		 * {DAEMON_HOME_PATH}/conf 폴더의  확장자가 .properties인 파일을 모두 읽어 들인다.
		 * 
		 * 
		 */
		try {
			File[] files = IOUtil.getFileList(configPath);
			if(files != null) {
				map = new HashMap<String, String>();

				for(File f : files) {
					if(f.getName().substring(f.getName().lastIndexOf(".") + 1).toLowerCase().equals(extensionName)) {
						Properties props = PropertiesUtil.getProperties(f.getPath());
						for(Object obj : props.keySet()) {
							String key = obj.toString();
							String value = props.getProperty(key);
							map.put(key, value);
						}
					}
				}
			}
		}catch(Exception ex) {
			throw ex;
		}
		return map;
	}

	
}
