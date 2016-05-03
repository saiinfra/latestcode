package com.domain;

/**
 * 
 * @author DeploymentSettingClientDO Used For Setting Client BaseOrg Details
 *
 */
public class DeploymentSettingClientDO {

	String id; // object primaryKey
	String orgId;
	String serverUrl;
	String token;
	String refreshToken;

	public DeploymentSettingClientDO() {
		super();
	}

	/**
	 * 
	 * @param id
	 * @param orgId
	 *            is Client Base Organisation Id
	 * @param token
	 *            is Client Base Organisation Token
	 * @param serverUrl
	 *            is Client Base Organisation Instance URL
	 * @param refreshToken
	 *            is Client Base Organisation RefreshToken
	 */
	public DeploymentSettingClientDO(String id, String orgId, String token,
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
	 *            is Client Base Organisation Id
	 * @param token
	 *            is Client Base Organisation Token
	 * @param serverUrl
	 *            is Client Base Organisation Instance URL
	 * @param refreshToken
	 *            is Client Base Organisation RefreshToken
	 */
	public DeploymentSettingClientDO(String orgId, String token, String serverUrl,
			String refreshToken) {
		this.orgId = orgId;
		this.token = token;
		this.serverUrl = serverUrl;
		this.refreshToken = refreshToken;
	}

	/**
	 * 
	 * @return Client Base Organisation Id
	 */
	public String getOrgId() {
		return orgId;
	}

	/**
	 * 
	 * @param Setting
	 *           Client BaseOrganisation Id
	 */
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	/**
	 * 
	 * @return Client BaseOrganisation Server URL
	 */
	public String getServerUrl() {
		return serverUrl;
	}

	/**
	 * 
	 * @param Setting
	 *          BaseOrganisation Server URL
	 */
	public void setServerUrl(String serverUrl) {
		this.serverUrl = serverUrl;
	}

	/**
	 * 
	 * @return Client BaseOrganisation Token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * 
	 * @param Setting
	 *            Client BaseOrganisation Token
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * 
	 * @return Client BaseOrganisation RefreshToken
	 */
	public String getRefreshToken() {
		return refreshToken;
	}
/**
 * 
 * @param Setting Client BaseOrganisationrefreshToken
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
