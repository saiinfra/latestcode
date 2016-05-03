package com.response.releaseinformation;

import java.util.List;

import com.processflow.ProcessTrack;
import com.response.BaseResponse;

public class ReleaseInformationResponse extends BaseResponse{

	List<Object> relInfoList;
	
	public ReleaseInformationResponse(ProcessTrack processTrack){
		super(processTrack);
	}

	public List<Object> getRelInfoList() {
		return relInfoList;
	}

	public void setRelInfoList(List<Object> relInfoList) {
		this.relInfoList = relInfoList;
	}
	
	
}
