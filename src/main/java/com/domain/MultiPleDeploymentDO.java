package com.domain;

public class MultiPleDeploymentDO {

	String metadataLog;
	String baseOrg;
	String baseOrgToken;
	String refreshToken;
	String baseOrgURL;
	String validationId;

	public MultiPleDeploymentDO(String metadataLog, String baseOrg,
			String baseOrgToken, String refreshToken, String baseOrgURL) {

		this.metadataLog = metadataLog;
		this.baseOrg = baseOrg;
		this.baseOrgToken = baseOrgToken;
		this.refreshToken = refreshToken;
		this.baseOrgURL = baseOrgURL;

	}
	public MultiPleDeploymentDO(String metadataLog, String baseOrg,
			String baseOrgToken, String refreshToken, String baseOrgURL,String validationId) {

		this.metadataLog = metadataLog;
		this.baseOrg = baseOrg;
		this.baseOrgToken = baseOrgToken;
		this.refreshToken = refreshToken;
		this.baseOrgURL = baseOrgURL;
		this.validationId=validationId;

	}
	public String getMetadataLog() {
		return metadataLog;
	}

	public void setMetadataLog(String metadataLog) {
		this.metadataLog = metadataLog;
	}

	public String getBaseOrg() {
		return baseOrg;
	}

	public void setBaseOrg(String baseOrg) {
		this.baseOrg = baseOrg;
	}

	public String getBaseOrgToken() {
		return baseOrgToken;
	}

	public void setBaseOrgToken(String baseOrgToken) {
		this.baseOrgToken = baseOrgToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getBaseOrgURL() {
		return baseOrgURL;
	}

	public void setBaseOrgURL(String baseOrgURL) {
		this.baseOrgURL = baseOrgURL;
	}
	public String getValidationId() {
		return validationId;
	}
	public void setValidationId(String validationId) {
		this.validationId = validationId;
	}
	

}
