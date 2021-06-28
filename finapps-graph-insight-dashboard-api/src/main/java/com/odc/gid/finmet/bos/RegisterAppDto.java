package com.odc.gid.finmet.bos;

public class RegisterAppDto {

	private Integer applicationId;
	private String applicationName;

	public RegisterAppDto() {
	}

	public RegisterAppDto(String applicationName) {
		this.applicationName = applicationName;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		if (applicationName != null) {
			this.applicationName = applicationName.trim();
		}
	}

	public Integer getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Integer applicationId) {
		this.applicationId = applicationId;
	}

	@Override
	public String toString() {
		return "RegisterAppDto [applicationId=" + applicationId + ", applicationName=" + applicationName + "]";
	}
}
