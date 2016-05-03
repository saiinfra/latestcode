package com.request.packages;

import com.request.BaseRequest;
import com.util.Org;

public class DeletePackageRequest extends BaseRequest {

	String releaseId;
	
	public DeletePackageRequest(String releaseId, Org org){
		super(org);
		this.releaseId = releaseId;
	}

	public String getReleaseId() {
		return releaseId;
	}

	public void setReleaseId(String releaseId) {
		this.releaseId = releaseId;
	}
}
