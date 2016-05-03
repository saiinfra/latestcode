package com.util.oauth;

public class AuthAccessDO {
	String accessToken;
	String refreshToken;
	String instanceUrl;

	public AuthAccessDO(String accessToken, String refreshToken,
			String instanceUrl) {
		super();
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
		this.instanceUrl = instanceUrl;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getInstanceUrl() {
		return instanceUrl;
	}

	public void setInstanceUrl(String instanceUrl) {
		this.instanceUrl = instanceUrl;
	}

}
