package com.scheduling.daemon.extension.util;

import java.io.File;
/*
 * 작성자 : thaud1324(thaud1324@naver.com)
 * 작성일 : 2019.04
 * 
 */

public class IOUtil {

	public static File[] getFileList(String path) throws Exception {
		File[] files = null;
		try {
			File dir = new File(path);
			if(dir.isDirectory()) {
				files = dir.listFiles();
			}
		}catch(Exception e) {
			throw e;
		}
		return files;
	}
	
	public static String getParentDirPath(String fileOrDirPath) {
	    boolean endsWithSlash = fileOrDirPath.endsWith(File.separator);
	    return fileOrDirPath.substring(0, fileOrDirPath.lastIndexOf(File.separatorChar, 
	            endsWithSlash ? fileOrDirPath.length() - 2 : fileOrDirPath.length() -1));
	}

}
