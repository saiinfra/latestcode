package com.services.component;

import com.request.log.LogRequest;
import com.response.log.LogResponse;
import com.services.BaseService;
import com.services.application.RDAppService;
import com.util.Constants;

public class LogService extends BaseService {

	LogRequest request;
	LogResponse response;

	public LogService(LogRequest request, LogResponse response) {
		this.request = request;
		this.response = response;
	}

	public void log() {
		RDAppService.updateToComplete(getRequest().getMetadataLogId(),
				Constants.COMPLETED_STATUS, getRequest().getOrg());
	}

	public LogRequest getRequest() {
		return request;
	}

	public void setRequest(LogRequest request) {
		this.request = request;
	}

	public LogResponse getResponse() {
		return response;
	}

	public void setResponse(LogResponse response) {
		this.response = response;
	}
	
	
}
