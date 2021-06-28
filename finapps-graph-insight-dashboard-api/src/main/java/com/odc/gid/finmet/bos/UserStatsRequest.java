package com.odc.gid.finmet.bos;

import java.util.Date;

public class UserStatsRequest {

	private Integer applicationId;
	private String userName;
	private Integer pId;
	private Date loginTime;

	public Integer getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Integer applicationId) {
		if (applicationId > 0) {
			this.applicationId = applicationId;
		}
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		if (userName != null) {
			this.userName = userName;
		} else {
			this.userName = "NO_NAME";
		}
	}

	public Integer getpId() {
		return pId;
	}

	public void setpId(Integer pId) {
		if (pId != null) {
			this.pId = pId;
		} else {
			this.pId = -1;
		}
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	@Override
	public String toString() {
		return "UserStatsRequest [applicationId=" + applicationId + ", userName=" + userName + ", pId=" + pId
				+ ", loginTime=" + loginTime + "]";
	}
}
