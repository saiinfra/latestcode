package com.util;

public class Org {
	
	String orgId;
	String orgToken;
	String orgURL;
	String refreshToken;
	String orgType;
	
	public Org(String orgId, String orgToken, String orgURL,
			String refreshToken){
		this.orgId = orgId;
		this.orgToken = orgToken;
		this.orgURL = orgURL;
		this.refreshToken = refreshToken;
	}

	public Org(String orgId, String orgToken, String orgURL,
			String refreshToken, String orgType){
		this.orgId = orgId;
		this.orgToken = orgToken;
		this.orgURL = orgURL;
		this.refreshToken = refreshToken;
		this.orgType = orgType;
	}
	
	

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgToken() {
		return orgToken;
	}

	public void setOrgToken(String orgToken) {
		this.orgToken = orgToken;
	}

	public String getOrgURL() {
		return orgURL;
	}

	public void setOrgURL(String orgURL) {
		this.orgURL = orgURL;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getOrgType() {
		return orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	
	
	
	
}
