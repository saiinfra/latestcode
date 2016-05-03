package com.util.sql;

public class DeployMetadataSQLStmts {

	public static String getToBeDeployedRecordQuery(String metadataLogIdName) {
		String sql = "SELECT Id, OFSServer__Name__c, OFSServer__SourceOrganizationId__c, OFSServer__OrganizationId__c, "
				+ " OFSServer__Type__c, OFSServer__Order__c, OFSServer__Package__c, OFSServer__MetadataLog__r.Name FROM OFSServer__DeployMetadata__c"
				+ " where "
				+ " OFSServer__MetadataLog__r.Name='"
				+ metadataLogIdName
				+ "'" + " ORDER BY OFSServer__Order__c ASC NULLS LAST";

		return sql;
	}
}
