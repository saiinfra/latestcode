package com.request.log;

import com.request.BaseRequest;
import com.util.Org;

public class LogRequest extends BaseRequest{

	String metadataLogId;
	Org org;
	
	public LogRequest(String metadataLogId, Org org){
		super();
		this.metadataLogId = metadataLogId;
		this.org = org;
	}

	public String getMetadataLogId() {
		return metadataLogId;
	}

	public void setMetadataLogId(String metadataLogId) {
		this.metadataLogId = metadataLogId;
	}

	public Org getOrg() {
		return org;
	}

	public void setOrg(Org org) {
		this.org = org;
	}
	
	
}
