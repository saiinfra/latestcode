package com.future;

import java.util.concurrent.Callable;

import com.services.component.FDRetrieveCompService;

/**
 * 
 * * @author ListObjectsFromSourceFutureTask Used For Retrieve All Components
 * from Source
 *
 */
public class RetrieveFutureTask implements Callable<Boolean> {

	String orgId;
	String token;
	String serverURL;
	String refreshToken;
	String metadataLogId;

	public RetrieveFutureTask(String orgId, String token, String serverURL,
			String refreshToken, String metadataLogId) {
		this.metadataLogId = metadataLogId;
		this.orgId = orgId;
		this.token = token;
		this.refreshToken = refreshToken;
		this.serverURL = serverURL;
	}

	@Override
	public Boolean call() {
		try {
			FDRetrieveCompService retrievService = new FDRetrieveCompService();
			retrievService.retrieve(getOrgId(), getToken(), getServerURL(),
					getRefreshToken(), getMetadataLogId());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
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

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

}