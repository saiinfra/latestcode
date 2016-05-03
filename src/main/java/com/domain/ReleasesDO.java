package com.domain;

/**
 * 
 * @author ReleasesDO Used For Setting Release__c Object data.
 *
 */
public class ReleasesDO {

	String id;
	String name;
	String status;

	/**
	 * 
	 * @param id
	 *            is Primary Key
	 * @param name
	 *            is Name of the Release
	 * @param status
	 *            is Release Status
	 */
	public ReleasesDO(String id, String name, String status) {
		this.id = id;
		this.name = name;
		this.status = status;
	}

	/**
	 * 
	 * @return Primary Key
	 */
	public String getId() {
		return id;
	}

	/**
	 * 
	 * @param Setting
	 *            Primary key id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 
	 * @return Release Name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param Setting
	 *            Name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return Release Status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * 
	 * @param Setting
	 *            Release status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

}
