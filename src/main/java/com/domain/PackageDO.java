package com.domain;

/**
 * 
 * @author PackageDO Used For Setting Package__c Object data.
 *
 */
public class PackageDO {

	String id;
	String name;
	String description;
	String parentPackageId;

	/**
	 * 
	 * @param id
	 *            is Primary Key
	 * @param name
	 *            is Name of Package
	 * @param description
	 *            is Description of Package
	 */
	public PackageDO(String id, String name, String description,String parentPackageId) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.parentPackageId=parentPackageId;
		
	}

	/**
	 * 
	 * @param name
	 *            is Name of Package
	 * @param description
	 *            is Description of Package
	 */
	public PackageDO(String name, String description,String parentPackageId) {
		this.name = name;
		this.description = description;
		this.parentPackageId=parentPackageId;
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
	 * @param setting
	 *            Primary Key
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 
	 * @return Package Name
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

	
	
	public String getParentPackageId() {
		return parentPackageId;
	}

	public void setParentPackageId(String parentPackageId) {
		this.parentPackageId = parentPackageId;
	}

	/**
	 * return String of Package ID + Package Name
	 */
	public String toString() {
		return this.id + "~" + this.name;
	}

}
