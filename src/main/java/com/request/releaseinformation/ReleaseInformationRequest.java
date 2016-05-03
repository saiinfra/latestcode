package com.request.releaseinformation;

import com.domain.EnvironmentDO;
import com.request.BaseRequest;

public class ReleaseInformationRequest extends BaseRequest {

	EnvironmentDO envDO;
	String releaseId;
	
	public ReleaseInformationRequest(String releaseId, EnvironmentDO envDO){
		this.envDO = envDO;
		this.releaseId = releaseId;
	}

	public EnvironmentDO getEnvDO() {
		return envDO;
	}

	public void setEnvDO(EnvironmentDO envDO) {
		this.envDO = envDO;
	}

	public String getReleaseId() {
		return releaseId;
	}

	public void setReleaseId(String releaseId) {
		this.releaseId = releaseId;
	}
	
}

