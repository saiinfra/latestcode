package com.response.pkginfo;

import java.util.List;

import com.processflow.ProcessTrack;
import com.response.BaseResponse;

public class PackageInfoResponse extends BaseResponse{

	List<Object> packageInfoList;
	List<String> pidList;
	
	public PackageInfoResponse(ProcessTrack processTrack){
		super(processTrack);
	}

	public List<Object> getPackageInfoList() {
		return packageInfoList;
	}

	public void setPackageInfoList(List<Object> packageInfoList) {
		this.packageInfoList = packageInfoList;
	}

	public List<String> getPidList() {
		return pidList;
	}

	public void setPidList(List<String> pidList) {
		this.pidList = pidList;
	}
	
	
}
