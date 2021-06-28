package com.odc.gid.utils;

public class MetricValUnitOperator {
	public static String getUnit(Double d) {
		if(d < 1000)
			return "";
		double log = Math.abs(Math.log10(d));
		if(log>2 && log<6)
			return "K";
		if(log>5 && log<9)
			return "M";
		if(log>8)
			return "B";
		else
			return "";
	}
	
	public static double convertIntoUnit(Double d, String unit) {
		if(unit == "" || unit == null)
			return d;
		if(unit == "K")
			return d/1000;
		if(unit == "M")
			return d/(1000*1000);
		if (unit == "B")
			return d/(1000*1000*1000);
		return d;
	}
}
