package com.util.oauth;

public class AuthUserInfoDO {
	String userName;
	String userId;
	String orgId;

	public AuthUserInfoDO(String userName, String userId,
			String orgId) {
		super();
		this.userName = userName;
		this.userId = userId;
		this.orgId = orgId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	

}
