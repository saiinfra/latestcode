package com.request;

import com.util.Org;

public class BaseRequest {

	Org org;
	String reqId; // metadatalogId;
	String orgId;
	String orgToken;
	String orgURL;
	String refreshToken;
	String orgType;
	
	public BaseRequest(){
		super();
	}
	
	public BaseRequest(String reqId){
		super();
		this.reqId = reqId;
	}
	
	public BaseRequest(Org org){
		super();
		this.org = org;
	}
	
	public BaseRequest(String reqId, Org org){
		super();
		this.reqId = reqId;
		this.org = org;
	}

	public BaseRequest(String reqId, String orgId, String orgToken, String orgURL,
			String refreshToken, String orgType){
		super();
		this.reqId = reqId;
		this.orgId = orgId;
		this.orgToken = orgToken;
		this.orgURL = orgURL;
		this.refreshToken = refreshToken;
		this.orgType = orgType;
		org = new Org(orgId, orgToken, orgURL, refreshToken, orgType);
	}
	
	public Org getOrg() {
		return org;
	}

	public void setOrg(Org org) {
		this.org = org;
	}

	public String getReqId() {
		return reqId;
	}

	public void setReqId(String reqId) {
		this.reqId = reqId;
	}
	
	
}

