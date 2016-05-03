package com.tasks;

import com.exception.SFErrorCodes;
import com.exception.SFException;
import com.util.SFoAuthHandle;
import com.util.oauth.RefreshTokens;

public class PreProcessingTask {

	String orgId;
	String token;
	String serverURL;
	String refreshtoken;
	String metadataLogId;
	String orgType;
	private SFoAuthHandle sfHandle;

	public PreProcessingTask(String orgId, String token, String serverURL, String refreshtoken,String orgType,
			String metadataLogId) throws SFException {
		super();
		this.metadataLogId = metadataLogId;
		this.orgId = orgId;
		this.token = token;
		this.serverURL = serverURL;
		this.refreshtoken = refreshtoken;
		this.orgType=orgType;

	}

	public String doPreProcess() {
		if (getOrgId() == null) {
			throw new SFException("Base Org Id is null",
					SFErrorCodes.Null_Org_Error);
		}
		if (getToken() == null) {
			throw new SFException("Base Org token is null",
					SFErrorCodes.Null_Auth_Tokens_Error);
		}
		if (getServerURL() == null) {
			throw new SFException("Base Org URL is null",
					SFErrorCodes.Null_Org_Error);
		}
		else if (getMetadataLogId() == null) {
			throw new SFException("MetadataLogId is null",
					SFErrorCodes.Null_MetadataLogId);
		}
		//get new access tokens if the existing tokens have expired
		RefreshTokens refreshTokens=new RefreshTokens();
		refreshTokens.refreshSFHandle(getOrgId(), getToken(), getServerURL(), getOrgType(),getRefreshtoken());
		System.out.println("Preprocessing task base org :" +refreshTokens.getoAuthToken());
		return refreshTokens.getoAuthToken();
	}
	
	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getServerURL() {
		return serverURL;
	}

	public void setServerURL(String serverURL) {
		this.serverURL = serverURL;
	}

	public String getMetadataLogId() {
		return metadataLogId;
	}

	public void setMetadataLogId(String metadataLogId) {
		this.metadataLogId = metadataLogId;
	}

	public String getRefreshtoken() {
		return refreshtoken;
	}

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
