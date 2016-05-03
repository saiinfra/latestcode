package com.processflow;

import java.util.ArrayList;
import java.util.List;

import com.util.Constants;

public class ProcessTrack {

	private String currentStatus;
	
	private ProcessStatus processStatus;
	private List<ProcessTrack> subordinates;
	
	public ProcessTrack(ProcessStatus processStatus){
		super();
		this.processStatus = processStatus;
		currentStatus = Constants.SUCCES_STATUS;
		if(subordinates == null){
			subordinates = new ArrayList<ProcessTrack>();
		}
	}
	
	public void add(ProcessTrack e) {
		subordinates.add(e);
	}

	public void remove(ProcessTrack e) {
		subordinates.remove(e);
	}

	public List<ProcessTrack> getSubordinates() {
		return subordinates;
	}

	public ProcessStatus getProcessStatus() {
		return processStatus;
	}

	public void setProcessStatus(ProcessStatus processStatus) {
		this.processStatus = processStatus;
	}

	public void setSubordinates(List<ProcessTrack> subordinates) {
		this.subordinates = subordinates;
	}

	public String getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}
	
}
