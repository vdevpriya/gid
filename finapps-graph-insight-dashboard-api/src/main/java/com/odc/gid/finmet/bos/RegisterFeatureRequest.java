package com.odc.gid.finmet.bos;

import java.util.List;

public class RegisterFeatureRequest {

	private Integer applicationId;
	private List<String> featureNames;

	public Integer getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Integer applicationId) {
		if (applicationId > 0) {
			this.applicationId = applicationId;
		}
	}

	public List<String> getFeatureNames() {
		return featureNames;
	}

	public void setFeatureNames(List<String> featureNames) {
		this.featureNames = featureNames;
	}

	@Override
	public String toString() {
		return "RegisterFeatureRequest [applicationId=" + applicationId + ", featureNames=" + featureNames + "]";
	}
}
