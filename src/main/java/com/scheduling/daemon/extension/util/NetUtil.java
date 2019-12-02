package com.scheduling.daemon.extension.util;

import java.net.InetAddress;
/*
 * 작성자 : thaud1324(thaud1324@naver.com)
 * 작성일 : 2019.04
 * 
 */

public class NetUtil {

	public static String getLocalIP() throws Exception {
		String ipAddr = null;
		try {
			InetAddress local = InetAddress.getLocalHost();
			ipAddr = local.getHostAddress();
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
			
		}
		return ipAddr;
	}
	
	public static String getHostName() throws Exception {
		String hostName = null;
		try {
			InetAddress local = InetAddress.getLocalHost();
			hostName = local.getHostName();
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
			
		}
		return hostName;
	}
	
}
