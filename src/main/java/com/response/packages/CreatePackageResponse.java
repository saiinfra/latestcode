package com.response.packages;

import com.processflow.ProcessTrack;
import com.response.BaseResponse;

public class CreatePackageResponse extends BaseResponse {

	String packageId;
	
	public CreatePackageResponse(ProcessTrack processTrack){
		super(processTrack);
	}

	public String getPackageId() {
		return packageId;
	}

	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}
	
}
