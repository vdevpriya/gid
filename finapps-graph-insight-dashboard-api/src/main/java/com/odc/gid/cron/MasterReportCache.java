package com.odc.gid.cron;

import java.util.Date;

import org.springframework.stereotype.Component;


@Component
public class MasterReportCache {
	private static final int POOL_SIZE = 4;
	private static final String localCachePath = "/var/data/dpreports/cache/";
	private final String localLatestClosedPath = localCachePath + "latestClosed.txt";
	private Date latestCloseDate = null;
	private Date curCloseDate = null;
	
	
	//@Scheduled(cron = "0 0 0 * * ?")
	public void refreshCashe() {
		
	}
	
	public static String getLocalCachePath() {
		return localCachePath;
	}
}
