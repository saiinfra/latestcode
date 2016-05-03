package com.util.sql;


public class PackageCompInfoSQLStmts {

	public static String getPackageCompInfoQuery(String id) {
		String sql = "SELECT Id, Name, OFSClient__Name__c, OFSClient__Order__c, OFSClient__Package__c, "
				+ "OFSClient__Package__r.Name, OFSClient__SourceOrganizationId__c, OFSClient__Type__c"
				+ " FROM OFSClient__PackageComponentInformation__c"
				+ " where Id= '" + id + "'";
		return sql;
	}
	public static String getParentPackageCompInfoQuery(String id) {
		String sql = "SELECT Id, Name, OFSClient__Name__c, OFSClient__Order__c, OFSClient__Package__c, "
				+ "OFSClient__Package__r.Name, OFSClient__SourceOrganizationId__c, OFSClient__Type__c"
				+ " FROM OFSClient__PackageComponentInformation__c"
				+ " where OFSClient__Package__c= '" + id + "'";
		return sql;
	}

}
