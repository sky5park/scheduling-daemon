package com.scheduling.daemon.extension.server;

import java.util.Map;

public interface Server {

	public void init(Map<String, Object> config);
	public void start();
	public void stop();
	
}
