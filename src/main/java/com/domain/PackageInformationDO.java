package com.domain;

import java.util.Calendar;

/**
 * 
 * @author PackageInformationDO Used For Setting PackageInformation__c Object
 *         data.which is Developer Organization Package Information
 *
 */
public class PackageInformationDO {

	String id;
	String name;
	String description;
	String releaseInformationId;
	Boolean readyForDeployment;
	Calendar calendar;

	public PackageInformationDO() {
		super();
	}

	/**
	 * 
	 * @param id
	 *            is Primary Key
	 * @param name
	 *            is PackageInformation Name
	 * @param description
	 *            is PackageInformation Description
	 * @param releaseInformationId
	 */
	public PackageInformationDO(String id, String name, String description,
			String releaseInformationId) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.releaseInformationId = releaseInformationId;
	}

	/**
	 * 
	 * @param name
	 *            is PackageInformation Name
	 * @param description
	 *            is PackageInformation Description
	 * @param releaseInformationId
	 */
	public PackageInformationDO(String name, String description,
			String releaseInformationId) {
		this.name = name;
		this.description = description;
		this.releaseInformationId = releaseInformationId;
	}

	/**
	 * 
	 * @param name
	 * @param description
	 * @param releaseInformationId
	 * @param readyForDeployment
	 */
	public PackageInformationDO(String id, String name, String description,
			String releaseInformationId, Boolean readyForDeployment,Calendar calendar) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.releaseInformationId = releaseInformationId;
		this.readyForDeployment = readyForDeployment;
		this.calendar = calendar;
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
	 *            Primary Key id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 
	 * @return Description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 
	 * @param Setting
	 *            Description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 
	 * @return Release Information ID
	 */
	public String getReleaseInformationId() {
		return releaseInformationId;
	}

	/**
	 * 
	 * @param Setting
	 *            Release Information ID
	 */
	public void setReleaseInformationId(String releaseInformationId) {
		this.releaseInformationId = releaseInformationId;
	}

	/**
	 * 
	 * @return Name
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
	 * @return
	 */
	public Calendar getCalendar() {
		return calendar;
	}

	/**
	 * 
	 * @param calendar
	 */
	public void setCalendar(Calendar calendar) {
		this.calendar = calendar;
	}

	/**
	 * 
	 * @return
	 */
	public Boolean getReadyForDeployment() {
		return readyForDeployment;
	}

	/**
	 * 
	 * @param readyForDeployment
	 */
	public void setReadyForDeployment(Boolean readyForDeployment) {
		this.readyForDeployment = readyForDeployment;
	}

	/**
	 * return String in combination of description + releaseInformationId
	 */
	public String toString() {
		return this.description + "~" + this.releaseInformationId;
	}
}
