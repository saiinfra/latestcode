package com.exception;

/**
 * 
 * @author SFErrorCodes implements ErrorCode interface 
 *
 */
public enum SFErrorCodes implements ErrorCode {
	
	Metadata_Conn_Error(101),
	Enterprise_Conn_Error(102),
	Tooling_Conn_Error(103),
	SFDeployMetadata_Query_Error(104),
	SFMetadataLog_Query_Error(105),
	SFObjects_List_Error(106),
	XML_Build_Error(107),
	FileRetrieve_Error(108),
	FileRetrieve_Request_timed_out_Error(109),
	FileRetrieve_Package_Error(110),
	File_Error(111),
	SF_Conn_Error(112),
	FileDeploy_Request_timed_out_Error(113),
	FileDeploy_Error(114),
	SFMetadataLog_No_Records_Error(115),
	Not_Yet_Authenticated_Error(116),
	Null_Org_Error(117),
	Null_MetadataLogId(118),
	Null_Auth_Tokens_Error(119),
	SFMetadataDescription_Insert_Error(120),
	SFMetadataLog_Update_Error(121),
	SFEnvironment_Update_Error(122),
	SFEnvironment_Query_Error(123),
	SFDeployDetails_Update_Error(124),
	SFMetadataDescription_Query_Error(125),
	SFDeploymentSettings_Update_Error(126),
	SF_Not_Valid_Conn_Parameters(127),
	SF_BulkInsert_Error(128),
	SF_ListObject_Error(129),
	SF_HTTP_Error(130),
	INVALID_SESSION_ID(131),
	Auth_Error(100);
	
	private final int number;

	/**
	 * 
	 * @param Setting Error number
	 */
	private SFErrorCodes(int number) {
		this.number = number;
	}
	
	/**
	 * return Number
	 */
	
	public int getNumber() {
		return number;
	}

	@Override
	public String getErrorCode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String setErrorCode(String errrCode) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
