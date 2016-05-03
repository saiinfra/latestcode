package com.tasks;

import java.io.PrintWriter;
import java.io.StringWriter;

import com.processflow.ProcessStatus;
import com.processflow.ProcessTrack;
import com.request.deploy.GetPackageRequest;
import com.response.deploy.GetPackageResponse;
import com.response.packages.DeletePackageResponse;
import com.services.application.SubmitForApprovalService;
import com.services.application.getpackage.GetPackageAppService;
import com.services.component.release.ReleaseEnvService;
import com.util.Constants;
import com.util.Org;

public class SubmitForApprovalTask implements Runnable {

	String metadataLogId;
	String bOrgId;
	String bOrgToken;
	String bOrgURL;
	String bOrgRefreshToken;
	String sOrgId;
	String sOrgToken;
	String sOrgURL;
	String sOrgRefreshToken;
	String tOrgId;
	String tOrgToken;
	String tOrgURL;
	String tOrgRefreshToken;
	String status;
	String  pkgId;

	public SubmitForApprovalTask(String bOrgId,
			String bOrgToken, String bOrgURL, String bOrgRefreshToken,String sOrgId,
			String sOrgToken, String sOrgURL, String sOrgRefreshToken,
			String status, String pkgId, String metadataLogId) {
		this.metadataLogId = metadataLogId;
		this.bOrgId=bOrgId;
		this.bOrgURL=bOrgURL;
		this.bOrgToken=bOrgToken;
		this.bOrgRefreshToken=bOrgRefreshToken;
		this.sOrgId = sOrgId;
		this.sOrgToken = sOrgToken;
		this.sOrgURL = sOrgURL;
		this.sOrgRefreshToken = sOrgRefreshToken;
		
		this.status=status;
		this.pkgId=pkgId;
		
		
	}

	public String getbOrgId() {
		return bOrgId;
	}

	public void setbOrgId(String bOrgId) {
		this.bOrgId = bOrgId;
	}

	public String getbOrgToken() {
		return bOrgToken;
	}

	public void setbOrgToken(String bOrgToken) {
		this.bOrgToken = bOrgToken;
	}

	public String getbOrgURL() {
		return bOrgURL;
	}

	public void setbOrgURL(String bOrgURL) {
		this.bOrgURL = bOrgURL;
	}

	public String getbOrgRefreshToken() {
		return bOrgRefreshToken;
	}

	public void setbOrgRefreshToken(String bOrgRefreshToken) {
		this.bOrgRefreshToken = bOrgRefreshToken;
	}

	@Override
	public void run() {
		String errors = null;
		boolean errorFlag = false;
		try {
			
			Org bOrg = new Org(getbOrgId(), getbOrgToken(), getbOrgURL(),
					getbOrgRefreshToken(), Constants.CustomBaseOrgID);
			Org sOrg = new Org(getsOrgId(), getsOrgToken(), getsOrgURL(),
					getsOrgRefreshToken(), Constants.BaseOrgID);
			
			SubmitForApprovalService subForAppService = new SubmitForApprovalService(bOrg,
					sOrg,getStatus(),getPkgId(),getMetadataLogId());
			subForAppService.initiate();
		} catch (Exception e) {
			errorFlag = true;
			StringWriter lerrors = new StringWriter();
			e.printStackTrace(new PrintWriter(lerrors));
			errors = lerrors.toString();
		} finally {
			if (errorFlag) {
				System.out.println("Package Operation Complete for requestId: "
						+ getPkgId() + "\nWith Errors: " + errors);
			} else {
				System.out.println("Package Operation Complete for requestId: "
						+ getPkgId());
			}
		}
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPkgId() {
		return pkgId;
	}

	public void setPkgId(String pkgId) {
		this.pkgId = pkgId;
	}

	public String getMetadataLogId() {
		return metadataLogId;
	}

	public void setMetadataLogId(String metadataLogId) {
		this.metadataLogId = metadataLogId;
	}

	public String getsOrgId() {
		return sOrgId;
	}

	public void setsOrgId(String sOrgId) {
		this.sOrgId = sOrgId;
	}

	public String getsOrgToken() {
		return sOrgToken;
	}

	public void setsOrgToken(String sOrgToken) {
		this.sOrgToken = sOrgToken;
	}

	public String getsOrgURL() {
		return sOrgURL;
	}

	public void setsOrgURL(String sOrgURL) {
		this.sOrgURL = sOrgURL;
	}

	public String getsOrgRefreshToken() {
		return sOrgRefreshToken;
	}

	public void setsOrgRefreshToken(String sOrgRefreshToken) {
		this.sOrgRefreshToken = sOrgRefreshToken;
	}

	public String gettOrgId() {
		return tOrgId;
	}

	public void settOrgId(String tOrgId) {
		this.tOrgId = tOrgId;
	}

	public String gettOrgToken() {
		return tOrgToken;
	}

	public void settOrgToken(String tOrgToken) {
		this.tOrgToken = tOrgToken;
	}

	public String gettOrgURL() {
		return tOrgURL;
	}

	public void settOrgURL(String tOrgURL) {
		this.tOrgURL = tOrgURL;
	}

	public String gettOrgRefreshToken() {
		return tOrgRefreshToken;
	}

	public void settOrgRefreshToken(String tOrgRefreshToken) {
		this.tOrgRefreshToken = tOrgRefreshToken;
	}

	

}