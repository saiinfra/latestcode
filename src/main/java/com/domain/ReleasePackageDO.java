package com.domain;

/**
 * 
 * @author ReleasePackageDO Used For Setting ReleasePackage__c Object data.Which
 *         is Child of Releases__c
 *
 */
public class ReleasePackageDO {

	String id;
	String name;
	String order;
	String packageC;
	String releaseC;
	String sourceEnv;

	/**
	 * 
	 * @param id
	 *            is Primary Key
	 * @param name
	 *            is Name of Package
	 * @param order
	 *            is Order of Package
	 * @param packageC
	 *            is Package ID
	 * @param releaseC
	 *            is Release ID
	 */
	public ReleasePackageDO(String id, String name, String order,
			String packageC, String releaseC, String sourceEnv) {
		this.id = id;
		this.name = name;
		this.order = order;
		this.packageC = packageC;
		this.releaseC = releaseC;
		this.sourceEnv = sourceEnv;
	}

	/**
	 * 
	 * @param name
	 *            is Name of Package
	 * @param order
	 *            is Order of Package
	 * @param packageC
	 *            is Package ID
	 * @param releaseC
	 *            is Release ID
	 */
	public ReleasePackageDO(String name, String order, String packageC,
			String releaseC, String sourceEnv) {
		this.name = name;
		this.order = order;
		this.packageC = packageC;
		this.releaseC = releaseC;
		this.sourceEnv = sourceEnv;

	}

	/**
	 * 
	 * @param order
	 *            is Order of Package
	 * @param packageC
	 *            is Package ID
	 * @param releaseC
	 *            is Release ID
	 */
	public ReleasePackageDO(String order, String packageC, String releaseC,String sourceEnv) {
		this.order = order;
		this.packageC = packageC;
		this.releaseC = releaseC;
		this.sourceEnv = sourceEnv;

	}

	/**
	 * 
	 * 
	 * @return Primary key
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
	 * @return Package Name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param Setting
	 *            Package Name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return Package Order
	 */
	public String getOrder() {
		return order;
	}

	/**
	 * 
	 * @param Setting
	 *            Order of Package
	 */
	public void setOrder(String order) {
		this.order = order;
	}

	/**
	 * 
	 * 
	 * @return Package ID
	 */
	public String getPackageC() {
		return packageC;
	}

	/**
	 * 
	 * @param Setting
	 *            Package Id
	 */
	public void setPackageC(String packageC) {
		this.packageC = packageC;
	}

	/**
	 * 
	 * @return Release ID
	 */
	public String getReleaseC() {
		return releaseC;
	}

	/**
	 * 
	 * @param Setting
	 *            Release ID
	 */
	public void setReleaseC(String releaseC) {
		this.releaseC = releaseC;
	}

	public String getSourceEnv() {
		return sourceEnv;
	}

	public void setSourceEnv(String sourceEnv) {
		this.sourceEnv = sourceEnv;
	}

}
