package com.scheduling.daemon.extension.util;

import java.util.Arrays;
/*
 * 작성자 : thaud1324(thaud1324@naver.com)
 * 작성일 : 2019.04
 * 
 */

public class ConvertUtil {

	
	public static byte[] copyArrayOfRange(byte[] data, int index, int length) {
//		byte[] b = new byte[length];
//	
//		int startIdx = index;
//		int endIdx = index + length;
//		int idx = 0;
//		for(int i=startIdx; i < endIdx; i++) {
//			b[idx] = data[i];
//			idx++;
//		}
//		return b;
		
		return Arrays.copyOfRange(data, index, index + length);
	}
}
