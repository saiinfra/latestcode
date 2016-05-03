package com.request.deploy;

import com.request.BaseRequest;
import com.tasks.PackagesTask;
import com.util.Org;

public class GetPackageRequest extends BaseRequest{

	String releaseParentId;
	String releaseParentName;
	String releaseStatus;
	
	public GetPackageRequest(String orgId, String orgToken, String orgURL,
			String refreshToken, String orgType, String releaseParentId,
			String releaseParentName, String releaseStatus, String metadataLogId){
		super(metadataLogId, orgId, orgToken, orgURL, refreshToken, orgType);
		this.releaseParentId = releaseParentId;
		this.releaseParentName = releaseParentName;
		this.releaseStatus = releaseStatus;
	}

	public String getReleaseParentId() {
		return releaseParentId;
	}

	public void setReleaseParentId(String releaseParentId) {
		this.releaseParentId = releaseParentId;
	}

	public String getReleaseParentName() {
		return releaseParentName;
	}

	public void setReleaseParentName(String releaseParentName) {
		this.releaseParentName = releaseParentName;
	}

	public String getReleaseStatus() {
		return releaseStatus;
	}

	public void setReleaseStatus(String releaseStatus) {
		this.releaseStatus = releaseStatus;
	}
	
}
