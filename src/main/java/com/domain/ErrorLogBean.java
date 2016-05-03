package com.domain;

/**
 * 
 * @author ErrorLogBean used For Setting Error Details
 *
 */
public class ErrorLogBean {

	String orgId;
	String metadataLogId;
	String errorMessage;
	String name;
	String type;
	String releaseId;

	public ErrorLogBean() {
		super();
	}

	/**
	 * 
	 * @param metadataLogId
	 *            is the id where the errors are associated.
	 * @param errorMessage
	 *            is the ErrorMessage i.e Exception
	 * @param orgId
	 *            is the Organisation ID
	 * @param name
	 *            Setting Name i.e for Organisation: 00Dj0000001tsUfEAI Error
	 *            Occur
	 */
	public ErrorLogBean(String metadataLogId, String errorMessage,
			String orgId, String name) {
		this.metadataLogId = metadataLogId;
		this.errorMessage = errorMessage;
		this.orgId = orgId;
		this.name = name;
	}
	
	/**
	 * 
	 * @param metadataLogId
	 *            is the id where the errors are associated.
	 * @param errorMessage
	 *            is the ErrorMessage i.e Exception
	 * @param orgId
	 *            is the Organisation ID
	 * @param name
	 *            Setting Name i.e for Organisation: 00Dj0000001tsUfEAI Error
	 *            Occur
	 * @param type
	 *            is the Object Type Error occur
	 */
	public ErrorLogBean(String metadataLogId, String errorMessage,
			String orgId, String name, String type) {
		this.metadataLogId = metadataLogId;
		this.errorMessage = errorMessage;
		this.orgId = orgId;
		this.name = name;
		this.type = type;
	}

	/**
	 * 
	 * @param metadataLogId
	 * @param errorMessage
	 * @param orgId
	 * @param name
	 * @param type
	 * @param releaseId
	 */
	public ErrorLogBean(String metadataLogId, String errorMessage,
			String orgId, String name, String type, String releaseId) {
		this.metadataLogId = metadataLogId;
		this.errorMessage = errorMessage;
		this.orgId = orgId;
		this.name = name;
		this.type = type;
		this.releaseId = releaseId;
	}
	
	/**
	 * 
	 * @return MetadataLogId
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
	 * @return ErrorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * 
	 * @param Setting
	 *            ErrorMessage
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
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
	 * @param Settting
	 *            Name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return Organisation ID
	 */
	public String getOrgId() {
		return orgId;
	}

	/**
	 * setting Organisation Id
	 * 
	 * @param orgId
	 */
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	/**
	 * 
	 * @return Object Type
	 */
	public String getType() {
		return type;
	}

	/**
	 * 
	 * @param Setting
	 *            Object Type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 
	 * @return
	 */
	private String getReleaseId() {
		return releaseId;
	}

	/**
	 * 
	 * @param releaseId
	 */
	private void setReleaseId(String releaseId) {
		this.releaseId = releaseId;
	}

	
}
