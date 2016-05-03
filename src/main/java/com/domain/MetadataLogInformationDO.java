package com.domain;

import java.util.HashMap;

/**
 * 
 * @author MetadataLogDO Filling MetadataLogInformation__c Object Data
 *
 */
public class MetadataLogInformationDO {

	private String id;
	private String logName;
	private String action;
	private String status;
	private String sourceOrgId;
	private String baseOrgId;
	private String baseOrgToken;
	private String baseOrgServerUrl;
	private String serverOrgServerUrl;
	private HashMap<Double, String> noOfPackgsByOrderMap = new HashMap<Double, String>();

	/**
	 * 
	 * @param id
	 *            is Primary key
	 * @param baseOrgId
	 *            is Base organisation ID
	 * @param baseOrgToken
	 *            is Base organisation Token
	 * @param baseOrgServerUrl
	 *            is Base Organisation Id
	 * @param sourceOrgId
	 *            is the Source Organisation ID
	 * @param logName
	 *            is the MetadataLog Name
	 * @param action
	 *            is the MetatdataLog Action
	 * @param status
	 *            is the MetadataLog Status
	 * @param noOfPackgsByOrderMap
	 *            is number of Packages By Order
	 */
	public MetadataLogInformationDO(String id, String baseOrgId, String baseOrgToken,
			String baseOrgServerUrl, String sourceOrgId, String logName,
			String action, String status,
			HashMap<Double, String> noOfPackgsByOrderMap) {
		this.id = id;
		this.baseOrgId = baseOrgId;
		this.baseOrgToken = baseOrgToken;
		this.baseOrgServerUrl = baseOrgServerUrl;
		this.sourceOrgId = sourceOrgId;
		this.logName = logName;
		this.action = action;
		this.status = status;
		this.noOfPackgsByOrderMap = noOfPackgsByOrderMap;
	}

	/**
	 * 
	 * @return MetadataLog Name
	 */
	public String getLogName() {
		return logName;
	}

	/**
	 * 
	 * @param Setting
	 *            MetadataLog Name
	 */
	public void setLogName(String logName) {
		this.logName = logName;
	}

	/**
	 * 
	 * @return MetadataLog Action
	 */
	public String getAction() {
		return action;
	}

	/**
	 * 
	 * @param Setting
	 *            MetadataLog Action
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * 
	 * @return MetadataLog Status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * 
	 * @param MetadataLog
	 *            status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * 
	 * @return SourceOrganisation ID
	 */
	public String getSourceOrgId() {
		return sourceOrgId;
	}

	/**
	 * 
	 * @param Setting
	 *            SourceOrganisation Id
	 */
	public void setSourceOrgId(String sourceOrgId) {
		this.sourceOrgId = sourceOrgId;
	}

	/**
	 * 
	 * @return BaseOrganisation ID
	 */
	public String getBaseOrgId() {
		return baseOrgId;
	}

	/**
	 * 
	 * @param Setting
	 *            BaseOrganisation ID
	 */
	public void setBaseOrgId(String baseOrgId) {
		this.baseOrgId = baseOrgId;
	}

	/**
	 * 
	 * @return BaseOrganisation Token
	 */
	public String getBaseOrgToken() {
		return baseOrgToken;
	}

	/**
	 * 
	 * @param Setting
	 *            BaseOrgToken
	 */
	public void setBaseOrgToken(String baseOrgToken) {
		this.baseOrgToken = baseOrgToken;
	}

	/**
	 * 
	 * @return BaseOrgServerURl
	 */
	public String getBaseOrgServerUrl() {
		return baseOrgServerUrl;
	}

	/**
	 * 
	 * @param Setting
	 *            baseOrgServerURL
	 */
	public void setBaseOrgServerUrl(String baseOrgServerUrl) {
		this.baseOrgServerUrl = baseOrgServerUrl;
	}

	/**
	 * 
	 * @return Server Organisation ServerURL
	 */
	public String getServerOrgServerUrl() {
		return serverOrgServerUrl;
	}

	/**
	 * 
	 * @param Setting
	 *            serverOrgServerUrl
	 */
	public void setServerOrgServerUrl(String serverOrgServerUrl) {
		this.serverOrgServerUrl = serverOrgServerUrl;
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
	 *            Primary key
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 
	 * @return Number Of Packages By Order From HashMap Object
	 */
	public HashMap<Double, String> getNoOfPackgsByOrderMap() {
		return noOfPackgsByOrderMap;
	}

	/**
	 * 
	 * @param Setting Number Of Packages By Order To HashMap
	 */
	public void setNoOfPackgsByOrderMap(
			HashMap<Double, String> noOfPackgsByOrderMap) {
		this.noOfPackgsByOrderMap = noOfPackgsByOrderMap;
	}

}
