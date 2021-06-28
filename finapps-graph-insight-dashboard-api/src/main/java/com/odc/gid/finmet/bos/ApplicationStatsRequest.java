package com.odc.gid.finmet.bos;

public class ApplicationStatsRequest {
	
	private Integer applicationId;
	private Integer featureId;
	private Integer avgResponseTime;

	public Integer getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Integer applicationId) {
		if (applicationId > 0) {
			this.applicationId = applicationId;
		}
	}

	public Integer getFeatureId() {
		return featureId;
	}

	public void setFeatureId(Integer featureId) {
		if (featureId > 0) {
			this.featureId = featureId;
		}
	}

	public Integer getAvgResponseTime() {
		return avgResponseTime;
	}

	public void setAvgResponseTime(Integer avgResponseTime) {
		if (avgResponseTime >= 0) {
			this.avgResponseTime = avgResponseTime;
		}
	}

	@Override
	public String toString() {
		return "ApplicationStatsRequest [applicationId=" + applicationId + ", featureId=" + featureId
				+ ", avgResponseTime=" + avgResponseTime + "]";
	}
}
