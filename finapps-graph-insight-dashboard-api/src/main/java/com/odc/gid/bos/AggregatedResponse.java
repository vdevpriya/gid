package com.odc.gid.bos;

import java.util.List;
import java.util.Set;

public class AggregatedResponse {
	private List<MetNameValPair> uniqueDevicesData;
	private List<MetNameValIntPair> devicesPerUserData;
	private Set<MetNameValPair> pieData;
	private double totalUsers;
	private double totalMatches;
	//private String unit;
	private String lastUpdatedDate;
	
	public String getLastUpdatedDate() {
		return lastUpdatedDate;
	}
	public void setLastUpdatedDate(String lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}
	@Override
	public String toString() {
		return "AggregatedResponse [uniqueDevicesData=" + uniqueDevicesData
				+ ", devicesPerUserData=" + devicesPerUserData + ", pieData="
				+ pieData + ", totalUsers=" + totalUsers + ", totalMatches="
				+ totalMatches + ", lastUpdatedDate=" + lastUpdatedDate + "]";
	}
	public double getTotalUsers() {
		return totalUsers;
	}
	public void setTotalUsers(double totalUsers) {
		this.totalUsers = totalUsers;
	}
	public double getTotalMatches() {
		return totalMatches;
	}
	public void setTotalMatches(double totalMatches) {
		this.totalMatches = totalMatches;
	}
//	public String getUnit() {
//		return unit;
//	}
//	public void setUnit(String unit) {
//		this.unit = unit;
//	}
	public List<MetNameValPair> getUniqueDevicesData() {
		return uniqueDevicesData;
	}
	public void setUniqueDevicesData(List<MetNameValPair> uniqueDevicesData) {
		this.uniqueDevicesData = uniqueDevicesData;
	}
	public List<MetNameValIntPair> getDevicesPerUserData() {
		return devicesPerUserData;
	}
	public void setDevicesPerUserData(List<MetNameValIntPair> devicesPerUserData) {
		this.devicesPerUserData = devicesPerUserData;
	}
	public Set<MetNameValPair> getPieData() {
		return pieData;
	}
	public void setPieData(Set<MetNameValPair> pieData) {
		this.pieData = pieData;
	}
	
}
