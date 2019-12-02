package com.scheduling.daemon.extension.util;

import java.io.UnsupportedEncodingException;

/*
 * 작성자 : thaud1324(thaud1324@naver.com)
 * 작성일 : 2019.04
 * 
 */

public class ByteUtil {

	public static void copyArray(String src, byte[] dst, int len) {

		if(dst == null) {
			dst = new byte[len];
		}
		
		byte[] srcBytes = src.getBytes();
		
		for(int i=0; i < len; i++) {
			dst[i] = srcBytes[i]; 
		}
	}
	
	public static byte[] getByteToString(String str, String encodingName) {
		byte[] bytes = null;
		try {
			bytes =  str.getBytes(encodingName);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bytes;
	}
	
}
