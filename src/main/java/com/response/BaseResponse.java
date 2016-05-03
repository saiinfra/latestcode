package com.response;

import com.processflow.ProcessTrack;

public abstract class BaseResponse {

	ProcessTrack processTrack;
	
	public BaseResponse(){
		super();
	}
	
	public BaseResponse(ProcessTrack processTrack){
		super();
		this.processTrack = processTrack;
	}

	public ProcessTrack getProcessTrack() {
		return processTrack;
	}

	public void setProcessTrack(ProcessTrack processTrack) {
		this.processTrack = processTrack;
	}
}

