package com.scheduling.daemon.extension.util;

import java.util.Calendar;
/*
 * 작성자 : thaud1324(thaud1324@naver.com)
 * 작성일 : 2019.04
 * 
 */

public class EnvUtil {

	public static long getTickCount(){
		long time;
		Calendar calendar = Calendar.getInstance();
		time = calendar.getTimeInMillis();
		
		return time;
	}
}
