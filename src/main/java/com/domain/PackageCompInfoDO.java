package com.domain;

/**
 * 
 * @author PackageCompInfoDO Used For Setting PackageComponentInformation__c
 *         Object data.
 *
 */
public class PackageCompInfoDO {

	String id;
	String pkgCompInfoName;
	String objName;
	String order;
	String pkgInfoParentId;
	String type;
	String sourceOrganizationId;

	public PackageCompInfoDO() {
		super();
	}

	/**
	 * 
	 * @param id
	 *            is Primary Key
	 * @param pkgCompInfoName
	 *            is PackageComponetInformation Name
	 * @param objName
	 *            is Object Name
	 * @param order
	 *            is order of Object
	 * @param type
	 *            is the Type of Object
	 * @param sourceOrganizationId
	 *            is the sourceOrganizationId of Object
	 * @param pkgInfoParentId
	 *            is PackageInformation Parent Id PackageInformation is-A
	 *            RelationShip to PackagComponetInformation
	 */
	public PackageCompInfoDO(String id, String pkgCompInfoName, String objName,
			String order, String type, String sourceOrganizationId,
			String pkgInfoParentId) {
		this.id = id;
		this.pkgCompInfoName = pkgCompInfoName;
		this.objName = objName;
		this.order = order;
		this.type = type;
		this.sourceOrganizationId = sourceOrganizationId;
		this.pkgInfoParentId = pkgInfoParentId;
	}

	/**
	 * 
	 * @param pkgCompInfoName
	 *            is PackageComponetInformation Name
	 * @param objName
	 *            is Object Name
	 * @param order
	 *            is order of Object
	 * @param type
	 *            is the Type of Object
	 * @param sourceOrganizationId
	 *            is the sourceOrganizationId of Object
	 * @param pkgInfoParentId
	 *            is PackageInformation Parent Id PackageInformation is-A
	 *            RelationShip to PackagComponetInformation
	 */
	public PackageCompInfoDO(String pkgCompInfoName, String objName,
			String order, String type, String sourceOrganizationId,
			String pkgInfoParentId) {
		this.pkgCompInfoName = pkgCompInfoName;
		this.objName = objName;
		this.order = order;
		this.type = type;
		this.sourceOrganizationId = sourceOrganizationId;
		this.pkgInfoParentId = pkgInfoParentId;
	}

	/**
	 * 
	 * @return PackageComponentDO Consists of PackageComponent whic is filled
	 *         with PackageCompoentInformation
	 */
	public PackageComponentDO getPkgCompDO() {

		PackageComponentDO pkgCompDO = new PackageComponentDO(
				this.pkgCompInfoName, this.objName, new Double(order),
				this.getPkgInfoParentId(), this.sourceOrganizationId, this.type,this.id);

		return pkgCompDO;
	}
	/**
	 * 
	 * @return primary Key
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
	 * @return Package Component Information Name
	 */
	public String getPkgCompInfoName() {
		return pkgCompInfoName;
	}

	/**
	 * 
	 * @param Setting
	 *            Package Component Information Name
	 */
	public void setPkgCompInfoName(String pkgCompInfoName) {
		this.pkgCompInfoName = pkgCompInfoName;
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
	public String getOrder() {
		return order;
	}

	/**
	 * 
	 * @param Setting
	 *            Order
	 */
	public void setOrder(String order) {
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
	 * @param setting
	 *            type of Object
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
	 * @return PackageInformation Parent ID
	 */
	public String getPkgInfoParentId() {
		return pkgInfoParentId;
	}

	/**
	 * 
	 * @param Setting
	 *            PackageInformation Parent Id
	 */
	public void setPkgInfoParentId(String pkgInfoParentId) {
		this.pkgInfoParentId = pkgInfoParentId;
	}

	/**
	 * return Full String of PackageComponentInformation
	 */
	public String toString() {
		return this.pkgCompInfoName + "~" + this.objName + "~" + this.order
				+ "~" + "~" + this.type + "~" + this.sourceOrganizationId;
	}
}
