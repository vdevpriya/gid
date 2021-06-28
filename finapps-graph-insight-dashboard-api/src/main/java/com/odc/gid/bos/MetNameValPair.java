package com.odc.gid.bos;

public class MetNameValPair implements Comparable<MetNameValPair> {
	private String mName;
	private Double mValue;
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	public Double getmValue() {
		return mValue;
	}
	public void setmValue(Double mValue) {
		this.mValue = mValue;
	}
	public MetNameValPair(String mName, Double mValue) {
		super();
		this.mName = mName;
		this.mValue = mValue;
	}
	@Override
	public String toString() {
		return "MetNameValPair [mName=" + mName + ", mValue=" + mValue + "]";
	}
	@Override
	public int compareTo(MetNameValPair o) {
		return this.mValue > o.mValue ? -1:1; 
	}
//	@Override
//	public int hashCode() {
//		// TODO Auto-generated method stub
//		return mName.hashCode();
//	}
	@Override
	public boolean equals(Object obj) {
		MetNameValPair o = (MetNameValPair) obj;
		// TODO Auto-generated method stub
		return ((this.mName.equals(o.mName)) && (this.mValue ==o.mValue));
	}
	
}
