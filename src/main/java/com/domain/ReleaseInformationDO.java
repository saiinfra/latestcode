package com.domain;

/**
 * 
 * @author ReleaseInformationDO Used For Setting ReleaseInformation__c Object
 *         data.which is Developer Organisation Release Information
 *
 */
public class ReleaseInformationDO {

	String id;
	String parentReleaseID;
	String status;
	String parentReleaseName;

	/**
	 * 
	 * @param id
	 * @param parentReleaseID
	 * @param parentReleaseName
	 * @param status
	 */

	public ReleaseInformationDO(String id, String parentReleaseID,
			String parentReleaseName, String status) {
		this.id = id;
		this.parentReleaseID = parentReleaseID;
		this.parentReleaseName = parentReleaseName;
		this.status = status;
	}

	/**
	 * 
	 * @param parentReleaseID
	 *            is Parent Release ID i.e Base organisation Release ID
	 * @param parentReleaseName
	 *            is ReleaseName
	 * @param status
	 *            is Release Status
	 */
	public ReleaseInformationDO(String parentReleaseID,
			String parentReleaseName, String status) {
		this.parentReleaseID = parentReleaseID;
		this.parentReleaseName = parentReleaseName;
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
	 *            Primary Key
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 
	 * @return Parent Release ID
	 */
	public String getParentReleaseID() {
		return parentReleaseID;
	}

	/**
	 * 
	 * @param Setting
	 *            parentReleaseID
	 */
	public void setParentReleaseID(String parentReleaseID) {
		this.parentReleaseID = parentReleaseID;
	}

	/**
	 * 
	 * @return ReleaseName
	 */
	public String getParentReleaseName() {
		return parentReleaseName;
	}

	/**
	 * 
	 * @param Setting
	 *            ReleaseName
	 */
	public void setParentReleaseName(String parentReleaseName) {
		this.parentReleaseName = parentReleaseName;
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
	 * @param Setting Release status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

}
