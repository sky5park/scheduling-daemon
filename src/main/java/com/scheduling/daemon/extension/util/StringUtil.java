package com.scheduling.daemon.extension.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/*
 * 작성자 : thaud1324(thaud1324@naver.com)
 * 작성일 : 2019.04
 * 
 */

public class StringUtil {

	private StringUtil() {}
	
//	public static boolean isNull(String str) {
//		return str == null ? true : false;
//	}
//	
	public static boolean isEmpty(String str) {
		return str.isEmpty();
	}
	
	public static String getStringToBytes(byte[] data)  {
		return getStringToBytes(data, "ksc5601");
	}
	
	public static String getStringToBytes(byte[] data, String encoding) {
		String s = "";
		try {
			s = new String(data, encoding);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return s;
	}
	

	public static String Format(String format, Object ... params) {
		Pattern p = Pattern.compile("\\{\\d\\}");
		Matcher match = p.matcher(format);
		
		while(match.find()){
			
			if(getIndex(match.group(0)) == -1){
				throw new IllegalArgumentException();
			}
			
			if(getIndex(match.group(0)) > params.length-1){
				throw new IllegalArgumentException("인덱스는 인수 목록의 크기보다 작아야합니다.");
			}			
			
			if(params[getIndex(match.group(0))] == null){
				params[getIndex(match.group(0))] = "NULL";
			}

			format = format.replace(match.group(0), params[getIndex(match.group(0))].toString());
		}

		return format;
	}
	
	public static String[] split(String str, String regex) {
		String[] splitStrings = null;
		try {
			if(str != null && regex != null)
				splitStrings = str.split(regex);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return splitStrings;
	}
	
	private static int getIndex(String param){
		String strIndex=null;
		int index = -1;
		strIndex = param.replace("{", "").replace("}", "");
		
		try{
			index = Integer.parseInt(strIndex);
		}catch(Exception ex){
			return -1;
		}
		return index;
	}
}
