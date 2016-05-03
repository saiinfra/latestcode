package com.services;

import com.request.BaseRequest;
import com.response.BaseResponse;

public abstract class BaseService {

	BaseRequest request;
	BaseResponse response;
	
	public BaseService(){
		super();
	}
	
	public BaseService(BaseRequest request, BaseResponse response){
		this.request = request;
		this.response = response;
	}

	public BaseRequest getRequest() {
		return request;
	}

	public void setRequest(BaseRequest request) {
		this.request = request;
	}

	public BaseResponse getResponse() {
		return response;
	}

	public void setResponse(BaseResponse response) {
		this.response = response;
	}
	
}
