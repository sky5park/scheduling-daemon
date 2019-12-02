package com.scheduling.daemon.extension.task.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.scheduling.daemon.extension.http.HttpUtil;
import com.scheduling.daemon.extension.task.JobTask;

public class HttpRequestJobTask implements JobTask{

	private static Log logger = LogFactory.getLog(HttpRequestJobTask.class);
	@Override
	public void run() throws Exception {
		// TODO Auto-generated method stub
		try {
			Map<String, String> keyString = new HashMap<String, String>();
			
			/* 개발 테스트용 정보 */
//			String client_id = System.getProperty("X-Authenticate-Token");
//			keyString.put("X-Authenticate-Token", client_id);
			
			/* 현대백화점 정보 */
			String client_id = System.getProperty("x-authenticate-client-id");
			String client_secret = System.getProperty("x-authenticate-client-secret");
			keyString.put("x-authenticate-client-id", client_id);
			keyString.put("x-authenticate-client-secret", client_secret);
			
			String url = System.getProperty("api_url");
			String resposeString2 = HttpUtil.doGet(url, keyString);
			
			// 문자열의 data:Success 로 넘어오는 경우가 아니면 state:sucess 를 state:fail로 변경
			if(!resposeString2.contains("Success")) {
				resposeString2 = resposeString2.replace("success", "fail");
			}
			
			logger.info("result => " + resposeString2);
		}catch(Exception e) {
			logger.error(e);
		}
	}

}
