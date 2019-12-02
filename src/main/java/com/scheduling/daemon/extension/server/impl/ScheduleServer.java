package com.scheduling.daemon.extension.server.impl;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import com.scheduling.daemon.extension.server.Server;
import com.scheduling.daemon.extension.task.JobTask;
import com.scheduling.daemon.extension.task.impl.HttpRequestJobTask;

public class ScheduleServer implements Server{

	
	private final static String PERIOD_KEY_NAME = "period";
	private final static String DELAY_KEY_NAME = "delay";

	
	private Timer timer = null;
	
	
	//timer 실행 주
	private long period = 1000 * 60;
	
	private long delay = 0;
	
	public ScheduleServer() {
		this.timer = new Timer("ScheduleTimer");
	}
	
	@Override
	public void init(Map<String, Object> config) {
		// TODO Auto-generated method stub
		
		//예외처리 필요
		if(config != null) {
			if(config.containsKey(PERIOD_KEY_NAME)) {
				int minute = Integer.parseInt( config.get(PERIOD_KEY_NAME).toString());
				this.period = this.period * minute;
			}
			
			if(config.containsKey(DELAY_KEY_NAME)) {
				this.delay = Long.parseLong(config.get(DELAY_KEY_NAME).toString());
			}
		}
		
		
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub

		this.timer.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				//실행할 비지니스 컴포넌트를 실행한다.
				JobTask task = new HttpRequestJobTask();
				try {
					task.run();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}}
		, this.delay,  this.period);
		
	}
	
	@Override
	public void stop() {
		// TODO Auto-generated method stub
		timer.cancel();
	}






	
	
}
