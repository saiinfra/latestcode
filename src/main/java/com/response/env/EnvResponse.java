package com.response.env;

import java.util.List;

import com.processflow.ProcessTrack;
import com.response.BaseResponse;

public class EnvResponse extends BaseResponse{

	List<Object> envList;
	
	public EnvResponse(ProcessTrack processTrack){
		super(processTrack);
	}

	public List<Object> getEnvList() {
		return envList;
	}

	public void setEnvList(List<Object> envList) {
		this.envList = envList;
	}
	
	
}
