package com.odc.gid.utils;

import java.util.HashMap;

import org.springframework.stereotype.Component;

@Component
public class ProviderIdMap {
	private HashMap<Integer, Integer> map;
	
	public ProviderIdMap() {
		map = new HashMap<Integer, Integer>();
		//Disney's seats
		map.put(3316, 3316);
		map.put(2147, 3316);
		map.put(2769, 3316);
		map.put(3315, 3316);
		map.put(2770, 3316);
		map.put(5535, 3316);
		map.put(3086, 3316);
		map.put(6219, 3316);
	}
	
	public Integer get(Integer key) {
		return map.get(key);
	}
}
