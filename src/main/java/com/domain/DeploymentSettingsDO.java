package com.domain;

/**
 * 
 * @author DeploymentSettingsDO Used For Setting BaseOrg Details
 *
 */
public class DeploymentSettingsDO {

	String id; // object primaryKey
	String orgId;
	String serverUrl;
	String token;
	String refreshToken;

	public DeploymentSettingsDO() {
		super();
	}

	/**
	 * 
	 * @param id
	 * @param orgId
	 *            is Base Organisation Id
	 * @param token
	 *            is Base Organisation Token
	 * @param serverUrl
	 *            is Base Organisation Instance URL
	 * @param refreshToken
	 *            is Base Organisation RefreshToken
	 */
	public DeploymentSettingsDO(String id, String orgId, String token,
			String serverUrl, String refreshToken) {
		this.id = id;
		this.orgId = orgId;
		this.token = token;
		this.serverUrl = serverUrl;
		this.refreshToken = refreshToken;
	}

	/**
	 * 
	 * @param orgId
	 *            is Base Organisation Id
	 * @param token
	 *            is Base Organisation Token
	 * @param serverUrl
	 *            is Base Organisation Instance URL
	 * @param refreshToken
	 *            is Base Organisation RefreshToken
	 */
	public DeploymentSettingsDO(String orgId, String token, String serverUrl,
			String refreshToken) {
		this.orgId = orgId;
		this.token = token;
		this.serverUrl = serverUrl;
		this.refreshToken = refreshToken;
	}

	/**
	 * 
	 * @return Base Organisation Id
	 */
	public String getOrgId() {
		return orgId;
	}

	/**
	 * 
	 * @param Setting
	 *            BaseOrganisation Id
	 */
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	/**
	 * 
	 * @return BaseOrganisation Server URL
	 */
	public String getServerUrl() {
		return serverUrl;
	}

	/**
	 * 
	 * @param Setting
	 *            serverURL
	 */
	public void setServerUrl(String serverUrl) {
		this.serverUrl = serverUrl;
	}

	/**
	 * 
	 * @return BaseOrganisation Token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * 
	 * @param Setting
	 *            BaseOrganisation Token
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * 
	 * @return BaseOrganisation RefreshToken
	 */
	public String getRefreshToken() {
		return refreshToken;
	}
/**
 * 
 * @param Setting BaseOrganisationrefreshToken
 */
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
/**
 * 
 * @return Primary Key
 */
	public String getId() {
		return id;
	}
/**
 * 
 * @param Setting Primary Key
 */
	public void setId(String id) {
		this.id = id;
	}

}
