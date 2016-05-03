package com.domain;

/**
 * 
 * @author PackageComponentDO Used For Setting PackageComponent__c Object data.
 *
 */
public class PackageComponentDO {

	String id;
	String pkgCompName;
	String objName;
	Double order;
	String pkgParentId;
	String type;
	String sourceOrganizationId;
	String ParentPackageCompID;

	public PackageComponentDO() {
		super();
	}

	/**
	 * 
	 * @param id
	 *            is Primary Key
	 * @param pkgCompName
	 *            is Package Component Name
	 * @param objName
	 *            is Object Name
	 * @param order
	 *            is order of object
	 * @param pkgParentId
	 *            is Package Parent Id
	 * @param sourceOrganizationId
	 *            is Source Organisation ID
	 * @param type
	 *            is Type of Object
	 */
	public PackageComponentDO(String id, String pkgCompName, String objName,
			Double order, String pkgParentId, String sourceOrganizationId,
			String type, String ParentPackageCompID) {
		this.id = id;
		this.pkgCompName = pkgCompName;
		this.objName = objName;
		this.order = order;
		this.type = type;
		this.sourceOrganizationId = sourceOrganizationId;
		this.pkgParentId = pkgParentId;
		this.ParentPackageCompID = ParentPackageCompID;
	}

	/**
	 * 
	 * @param pkgCompName
	 *            is Package Component Name
	 * @param objName
	 *            is Object Name
	 * @param order
	 *            is order of object
	 * @param pkgParentId
	 *            is Package Parent Id
	 * @param sourceOrganizationId
	 *            is Source Organisation ID
	 * @param type
	 *            is Type of Object
	 */
	public PackageComponentDO(String pkgCompName, String objName, Double order,
			String pkgParentId, String sourceOrganizationId, String type,
			String ParentPackageCompID) {
		this.pkgCompName = pkgCompName;
		this.objName = objName;
		this.order = order;
		this.type = type;
		this.sourceOrganizationId = sourceOrganizationId;
		this.pkgParentId = pkgParentId;
		this.ParentPackageCompID = ParentPackageCompID;
	}

	/**
	 * 
	 * @return Primary Key
	 */
	public String getId() {
		return id;
	}

	/**
	 * Setting Primary Key
	 * 
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 
	 * @return Package Component Name
	 */
	public String getPkgCompName() {
		return pkgCompName;
	}

	/**
	 * 
	 * @param Setting
	 *            Package Component Name
	 */
	public void setPkgCompName(String pkgCompName) {
		this.pkgCompName = pkgCompName;
	}

	/**
	 * 
	 * @return Object Name
	 */
	public String getObjName() {
		return objName;
	}

	/**
	 * 
	 * @param Setting
	 *            objName
	 */
	public void setObjName(String objName) {
		this.objName = objName;
	}

	/**
	 * 
	 * @return Order
	 */
	public Double getOrder() {
		return order;
	}

	/**
	 * 
	 * @param Setting
	 *            Order
	 */
	public void setOrder(Double order) {
		this.order = order;
	}

	/**
	 * 
	 * @return Type of Object
	 */
	public String getType() {
		return type;
	}

	/**
	 * 
	 * @param Setting
	 *            Type of Object
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 
	 * @return SourceOrganizationId
	 */
	public String getSourceOrganizationId() {
		return sourceOrganizationId;
	}

	/**
	 * 
	 * @param Setting
	 *            sourceOrganizationId
	 */
	public void setSourceOrganizationId(String sourceOrganizationId) {
		this.sourceOrganizationId = sourceOrganizationId;
	}

	/**
	 * 
	 * @return Package Parent ID
	 */
	public String getPkgParentId() {
		return pkgParentId;
	}

	/**
	 * 
	 * @param Setting
	 *            pkgParentId
	 */
	public void setPkgParentId(String pkgParentId) {
		this.pkgParentId = pkgParentId;
	}

	public String getParentPackageCompID() {
		return ParentPackageCompID;
	}

	public void setParentPackageCompID(String parentPackageCompID) {
		ParentPackageCompID = parentPackageCompID;
	}

	/**
	 * return String which consists of PackageComponent Data
	 */
	public String toString() {
		return this.pkgCompName + "~" + this.objName + "~" + this.order + "~"
				+ "~" + this.type + "~" + this.sourceOrganizationId;
	}
}
