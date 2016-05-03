package com.domain;

import java.util.Calendar;

/**
 * 
 * @author MetaBean Used for Setting Data Which is Retrieved From
 *         DeployMetadata__c Object
 *
 */
public class MetaBean {

	String metadataLogId;
	String metadataLogName;
	private String name;
	private String type;
	private String sourceOrg;
	private String targetOrg;
	private Double order;
	private String packageName;
	String lastModifiedById;
	String lastModifiedByName;
	Calendar lastModifiedByDate;

	public MetaBean() {
		super();
	}

	/**
	 * 
	 * @param name
	 *            is Component Name
	 * @param type
	 *            is Component Type
	 */
	public MetaBean(String name, String type) {
		this.name = name;
		this.type = type;
	}

	/**
	 * 
	 * @param name
	 *            is Component Name
	 * @param type
	 *            is Component Type
	 * @param sourceOrg
	 *            is Source Organisation Where Components Are Retrieved
	 */

	public MetaBean(String name, String type, String sourceOrg) {
		this.name = name;
		this.type = type;
		this.sourceOrg = sourceOrg;
	}

	/**
	 * 
	 * @param metadataLogId
	 *            is MetadataLogId of Retrieved Component
	 * @param name
	 *            is Component Name
	 * @param type
	 *            is Component Type
	 * @param sourceOrg
	 *            is Source Organisation Where Components Are Retrieved
	 */
	public MetaBean(String metadataLogId, String name, String type,
			String sourceOrg, String lastModifiedById,
			String lastModifiedByName,Calendar lastModifiedByDate) {
		this.metadataLogId = metadataLogId;
		this.name = name;
		this.type = type;
		this.sourceOrg = sourceOrg;
		this.lastModifiedById = lastModifiedById;
		this.lastModifiedByName = lastModifiedByName;
		this.lastModifiedByDate=lastModifiedByDate;
	}

	public Calendar getLastModifiedByDate() {
		return lastModifiedByDate;
	}

	public void setLastModifiedByDate(Calendar lastModifiedByDate) {
		this.lastModifiedByDate = lastModifiedByDate;
	}

	/**
	 * 
	 * @param metadataLogId
	 *            is MetadataLogId of Retrieved Component
	 * @param name
	 *            is Component Name
	 * @param type
	 *            is Component Type
	 * @param sourceOrg
	 *            is Source Organisation Where Components Are Retrieved
	 * @param targetOrg
	 *            is Target Organisation Where Components Are to Be Deployed
	 * @param order
	 *            is the order of component
	 * @param packageName
	 *            is the name of the Package From Components Build
	 */
	public MetaBean(String metadataLogId, String name, String type,
			String sourceOrg, String targetOrg, Double order, String packageName) {
		this.metadataLogId = metadataLogId;
		this.name = name;
		this.type = type;
		this.sourceOrg = sourceOrg;
		this.targetOrg = targetOrg;
		this.order = order;
		this.packageName = packageName;
	}

	/**
	 * 
	 * @param Setting
	 *            Component Name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return Component Name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param Setting
	 *            Component Type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 
	 * @return Type of Component
	 */
	public String getType() {
		return type;
	}

	/**
	 * 
	 * @return MetadataLog ID
	 */
	public String getMetadataLogId() {
		return metadataLogId;
	}

	/**
	 * 
	 * @param Setting
	 *            metadataLogId
	 */
	public void setMetadataLogId(String metadataLogId) {
		this.metadataLogId = metadataLogId;
	}

	/**
	 * 
	 * @return Source Organisation ID
	 */
	public String getSourceOrg() {
		return sourceOrg;
	}

	/**
	 * 
	 * @param Setting
	 *            Source Organisation ID
	 */
	public void setSourceOrg(String sourceOrg) {
		this.sourceOrg = sourceOrg;
	}

	/**
	 * 
	 * @return Target Organisation ID
	 */
	public String getTargetOrg() {
		return targetOrg;
	}

	/**
	 * 
	 * @param Setting
	 *            Organisation ID
	 */
	public void setTargetOrg(String targetOrg) {
		this.targetOrg = targetOrg;
	}

	/**
	 * 
	 * @return Component Order
	 */
	public Double getOrder() {
		return order;
	}

	/**
	 * 
	 * @param Setting
	 *            Component order
	 */
	public void setOrder(Double order) {
		this.order = order;
	}

	/**
	 * 
	 * @return PackageName
	 */
	public String getPackageName() {
		return packageName;
	}

	/**
	 * 
	 * @param Setting
	 *            packageName
	 */
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getLastModifiedById() {
		return lastModifiedById;
	}

	public void setLastModifiedById(String lastModifiedById) {
		this.lastModifiedById = lastModifiedById;
	}

	public String getLastModifiedByName() {
		return lastModifiedByName;
	}

	public void setLastModifiedByName(String lastModifiedByName) {
		this.lastModifiedByName = lastModifiedByName;
	}



	
}