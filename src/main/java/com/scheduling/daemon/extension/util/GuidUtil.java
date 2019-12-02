package com.scheduling.daemon.extension.util;

import java.util.UUID;
/*
 * 작성자 : thaud1324(thaud1324@naver.com)
 * 작성일 : 2019.04
 * 
 */

public class GuidUtil {

	public static String getGuid() {
		return UUID.randomUUID().toString();
	}
}
