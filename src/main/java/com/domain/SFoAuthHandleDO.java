package com.domain;

/**
 * SFoAuthHandleDO  Used For Setting Connection Making Data Both Base organisation And Destination organisation
 */
import com.util.SFoAuthHandle;

public class SFoAuthHandleDO {

	String orgId;
	String token;
	String serverURL;
	String refreshtoken;
	SFoAuthHandle sfoAuthHandle;
	String orgType;

	/**
	 * 
	 * @param orgId
	 *            is Organisation ID
	 * @param token
	 *            is Organisation Token
	 * @param serverURL
	 *            is Organisation serverURL
	 * @param refreshtoken
	 *            is Organisation Refresh Token
	 * @param isBase
	 *            is Boolean Flag which indicates false for(Other Organisations)
	 *            and true for (Base organisation)
	 * 
	 */
	public SFoAuthHandleDO(String orgId, String token, String serverURL,
			String refreshtoken, String orgType) {
		this.orgId = orgId;
		this.token = token;
		this.refreshtoken = refreshtoken;
		this.serverURL = serverURL;
		this.orgType = orgType;
	}

	/**
	 * 
	 * @return Organisation ID
	 */

	public String getOrgId() {
		return orgId;
	}

	/**
	 * 
	 * @param Setting organisation ID
	 */
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	/**
	 * 
	 * @return Organisation Token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * 
	 * @param Setting Organisation Token
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * 
	 * @return Organisation ServerURL
	 */
	public String getServerURL() {
		return serverURL;
	}

	/**
	 * 
	 * @param Setting Organisation ServerURL
	 */
	public void setServerURL(String serverURL) {
		this.serverURL = serverURL;
	}

	/**
	 * 
	 * @return Organisation RefreshToken
	 */
	public String getRefreshtoken() {
		return refreshtoken;
	}

	/**
	 * 
	 * @param Setting Organisation RefreshToken
	 */
	public void setRefreshtoken(String refreshtoken) {
		this.refreshtoken = refreshtoken;
	}

	public String getOrgType() {
		return orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	

}
