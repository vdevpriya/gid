package com.odc.gid.statics;

public enum MetricNames {
	TOTAL_IDS("total ids"),
	IDS_BY_TYPE("ids by type"),
	TOTAL_MATCHES("total matches"),
	MATCHES_BY_TYPE("matches by type"), 
	TOTAL_CLUSTERS("total clusters"),
	CLUSTER_SIZE_HISTOGRAM("cluster size histogram"); 

	private String group;

	MetricNames(String group) {
		this.group = group;
	}

	public String getGrop() {
		return this.group;
	}    
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.group;
	}
}
