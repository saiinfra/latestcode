package com.services.component.release;

public class GetPackageProcess {

	private String id;

	private String name;
	private String description;

	public GetPackageProcess(String id, String name,String description) {
		this.name = name;
		this.id = id;
		this.description=description;
	}
	
	public GetPackageProcess(String description) {
		this.description=description;
	}

	public String toString() {
		return description;
	}

}
