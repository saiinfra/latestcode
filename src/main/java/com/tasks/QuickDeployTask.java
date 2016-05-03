package com.tasks;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import com.domain.MultiPleDeploymentDO;
import com.services.component.FDQuickDeployCompService;

public class QuickDeployTask implements Runnable {

	String orgId;
	String token;
	String serverURL;
	String refreshToken;
	String metadataLogId;
	boolean isValidate;
	List<MultiPleDeploymentDO> multiPleDeploymentDOs;
	String recentValidationId;

	public QuickDeployTask(String orgId, String token, String serverURL,
			String refreshToken, String metadataLogId) {
		this.metadataLogId = metadataLogId;
		this.orgId = orgId;
		this.token = token;
		this.refreshToken = refreshToken;
		this.serverURL = serverURL;
	}

	public QuickDeployTask(String orgId, String token, String serverURL,
			String refreshToken, String metadataLogId, boolean isValidate,
			String recentValidationId) {
		this.orgId = orgId;
		this.token = token;
		this.refreshToken = refreshToken;
		this.serverURL = serverURL;
		this.isValidate = isValidate;
		this.metadataLogId=metadataLogId;
		this.recentValidationId = recentValidationId;
	}

	public boolean isValidate() {
		return isValidate;
	}

	public void setValidate(boolean isValidate) {
		this.isValidate = isValidate;
	}

	@Override
	public void run() {
		String errors = null;
		boolean errorFlag = false;

		try {

			FDQuickDeployCompService deployService = new FDQuickDeployCompService();
			deployService
					.deployRecentValidation(orgId, token, serverURL,
							refreshToken, metadataLogId, isValidate,
							recentValidationId);

		} catch (Exception e) {
			errorFlag = true;
			StringWriter lerrors = new StringWriter();
			e.printStackTrace(new PrintWriter(lerrors));
			errors = lerrors.toString();
		} finally {
			if (errorFlag) {
				System.out.println("Deploy Operation Complete for requestId: "
						+ getMetadataLogId() + "\nWith Errors: " + errors);
			} else {
				System.out.println("Deploy Operation Complete for requestId: "
						+ getMetadataLogId());
			}
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

	public List<MultiPleDeploymentDO> getMultiPleDeploymentDOs() {
		return multiPleDeploymentDOs;
	}

	public void setMultiPleDeploymentDOs(
			List<MultiPleDeploymentDO> multiPleDeploymentDOs) {
		this.multiPleDeploymentDOs = multiPleDeploymentDOs;
	}

	public String getRecentValidationId() {
		return recentValidationId;
	}

	public void setRecentValidationId(String recentValidationId) {
		this.recentValidationId = recentValidationId;
	}

}