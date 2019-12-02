package com.scheduling.daemon.extension.util;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class FileUtil {

	
	public static void write(String fileName, String content) throws Exception {
		
		BufferedWriter writer = null;
		try {
			writer  = new BufferedWriter(new FileWriter(fileName));
			writer.write(content);
		}catch(Exception e) {
			throw e;
		}finally {
			if(writer != null) writer.close();
		}
	}
}
