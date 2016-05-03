package com.processflow;

public class ProcessStatus {

	String activity;
	String activityStatus;
	String activityDetails;
	
	public ProcessStatus(String activity, String activityStatus, String activityDetails){
		this.activity = activity;
		this.activityStatus = activityStatus;
		this.activityDetails = activityDetails;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public String getActivityStatus() {
		return activityStatus;
	}

	public void setActivityStatus(String activityStatus) {
		this.activityStatus = activityStatus;
	}

	public String getActivityDetails() {
		return activityDetails;
	}

	public void setActivityDetails(String activityDetails) {
		this.activityDetails = activityDetails;
	}
	
}
