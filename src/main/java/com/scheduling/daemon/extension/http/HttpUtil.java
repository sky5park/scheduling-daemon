package com.scheduling.daemon.extension.http;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpUtil {

	private static Log logger = LogFactory.getLog(HttpUtil.class);
	
	public static String doGet(String url) throws Exception {
		return HttpUtil.doGet(url, null);
	}
	
	public static String doGet(String url, Map<String, String>headers) throws Exception {
		String responseResult = null;
		CloseableHttpClient httpClient = HttpClients.createDefault();
		try {
			
			HttpGet httpget = new HttpGet(url);
			
			if(headers != null) {
				headers.forEach((k, v)->httpget.addHeader(k, v));
			}
			
			ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

				@Override
				public String handleResponse(final HttpResponse response) throws ClientProtocolException, IOException {
					// TODO Auto-generated method stub
					
					int status = response.getStatusLine().getStatusCode();
					if(status >= 200 && status <300) {
						HttpEntity entity = response.getEntity();
						return entity != null ? EntityUtils.toString(entity) : null;
						
					}else {
						throw new ClientProtocolException("Unexpected response status => " + status);
					}
				}
			};
			
			responseResult = httpClient.execute(httpget, responseHandler);
			
		}catch(Exception e) {
			logger.error(e);
			throw e;
		}finally {
			if(httpClient != null) {
				httpClient.close();
			}
		}
		
		return responseResult;
	}
	
	public static String doPost(String url, Map<String, String>headers) throws Exception {
		return HttpUtil.doPost(url, headers, null);
	}
	
	public static String doPost(String url, Map<String, String>headers, String postData) throws Exception {
		String responseResult = null;
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		try {
			HttpPost httpPost = new HttpPost(url);
			
			if(headers != null) {
				headers.forEach((k, v)->httpPost.addHeader(k, v));
			}
			
			if(postData != null) {
				httpPost.setEntity(new StringEntity(postData));
			}
			
			ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

				@Override
				public String handleResponse(final HttpResponse response) throws ClientProtocolException, IOException {
					// TODO Auto-generated method stub
					
					int status = response.getStatusLine().getStatusCode();
					if(status >= 200 && status <300) {
						HttpEntity entity = response.getEntity();
						return entity != null ? EntityUtils.toString(entity) : null;
						
					}else {
						/*
						if(status == 301 || status == 302) {
							Header[] responseHeaders = response.getHeaders("Location");
							if(responseHeaders != null) {
								for(int i=0; i <responseHeaders.length; i++) {
									logger.info(responseHeaders[i].getName() + " : " + responseHeaders[i].getValue());
								}
							}
						}
						*/
						throw new ClientProtocolException("Unexpected response status => " + status);
					}
				}
			};
			
			responseResult = httpClient.execute(httpPost, responseHandler);
			
		}catch(Exception e) {
			logger.error(e);
			throw e;
		}finally {
			if(httpClient != null) {
				httpClient.close();
			}
		}
		
		return responseResult;
	}
}
