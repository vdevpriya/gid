package com.odc.gid.bos;

public class MetNameValIntPair {
	private Integer mName;
	private Double mValue;
	public Integer getmName() {
		return mName;
	}
	public void setmName(Integer mName) {
		this.mName = mName;
	}
	public Double getmValue() {
		return mValue;
	}
	public void setmValue(Double mValue) {
		this.mValue = mValue;
	}
	@Override
	public String toString() {
		return "MetNameValIntPair [mName=" + mName + ", mValue=" + mValue + "]";
	}
	public MetNameValIntPair(Integer mName, Double mValue) {
		super();
		this.mName = mName;
		this.mValue = mValue;
	}
	
	
	
}
