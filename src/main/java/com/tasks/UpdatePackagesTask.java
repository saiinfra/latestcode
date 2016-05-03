package com.tasks;

import java.io.PrintWriter;
import java.io.StringWriter;

import com.services.component.packages.UpdatePackageService;
import com.util.Constants;
import com.util.Org;

public class UpdatePackagesTask implements Runnable {

	String orgId;
	String token;
	String serverURL;
	String refreshToken;

	String metadataLogId;
	String action;
	String status;
	String packageParentId;

	public UpdatePackagesTask(String orgId, String token, String serverURL,
			String refreshToken, String action, String status,
			String packageParentId, String metadataLogId) {
		this.orgId = orgId;
		this.token = token;
		this.refreshToken = refreshToken;
		this.serverURL = serverURL;
		this.action = action;
		this.status = status;
		this.packageParentId = packageParentId;
		this.metadataLogId = metadataLogId;
	}

	public UpdatePackagesTask(String orgId, String token, String serverURL,
			String refreshToken, String status,
			String packageParentId, String metadataLogId) {
		this.orgId = orgId;
		this.token = token;
		this.refreshToken = refreshToken;
		this.serverURL = serverURL;
		this.status = status;
		this.packageParentId = packageParentId;
		this.metadataLogId = metadataLogId;
	}

	
	@Override
	public void run() {
		String errors = null;
		boolean errorFlag = false;
		try {
			Org borg = new Org(getOrgId(), getToken(), getServerURL(),
					getRefreshToken(), Constants.BaseOrgID);
			UpdatePackageService updatePkgSvc = new UpdatePackageService(borg,
					getMetadataLogId(), getAction(), getStatus(),
					getPackageParentId());
			updatePkgSvc.initiate();

		} catch (Exception e) {
			errorFlag = true;
			StringWriter lerrors = new StringWriter();
			e.printStackTrace(new PrintWriter(lerrors));
			errors = lerrors.toString();
		} finally {
			if (errorFlag) {
				System.out.println("UpdatePackage Operation Complete for requestId: "
						+ getPackageParentId() + "\nWith Errors: " + errors);
			} else {
				System.out.println("UpdatePackage Operation Complete for requestId: "
						+ getPackageParentId());
			}
		}
	}

	public String getMetadataLogId() {
		return metadataLogId;
	}

	public void setMetadataLogId(String metadataLogId) {
		this.metadataLogId = metadataLogId;
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

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPackageParentId() {
		return packageParentId;
	}

	public void setPackageParentId(String packageParentId) {
		this.packageParentId = packageParentId;
	}

}