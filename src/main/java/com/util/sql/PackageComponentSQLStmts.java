package com.util.sql;


public class PackageComponentSQLStmts {

	public static String getPackageCompQuery(String id) {
		String sql = "SELECT Id, Name, OFSServer__Name__c, OFSServer__Order__c, OFSServer__Package__c, "
				+ "OFSServer__Package__r.Name, OFSServer__SourceOrganizationId__c, OFSServer__Type__c"
				+ " FROM OFSServer__PackageComponent__c"
				+ " where Id= '" + id + "'";
		return sql;
	}
	public static String getParentPackageCompQuery(String id) {
		String sql = "SELECT Id, Name, OFSServer__Name__c, OFSServer__Order__c, OFSServer__Package__c, "
				+ "OFSServer__Package__r.Name, OFSServer__SourceOrganizationId__c, OFSServer__Type__c"
				+ " FROM OFSServer__PackageComponent__c"
				+ " where OFSServer__Package__c= '" + id + "'";
		return sql;
	}
	public static String getParentPackageComPID(String id) {
		String sql = "SELECT Id, Name, OFSServer__Name__c, OFSServer__Order__c, OFSServer__Package__c, "
				+ "OFSServer__Package__r.Name, OFSServer__SourceOrganizationId__c, OFSServer__Type__c"
				+ " FROM OFSServer__PackageComponent__c"
				+ " where OFSServer__ParentPackageCompID__c= '" + id + "'";
		return sql;
	}

}
