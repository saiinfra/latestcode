package com.util.sql;

public class ReleaseInformationSQLStmts {

	public static String getRIQuery(String id) {
		String sql = "SELECT Id, Name, OFSClient__ParentReleaseID__c, OFSClient__Status__c"
				+ " FROM OFSClient__ReleaseInformation__c"
				+ " where Id= '" + id + "'";
		return sql;
	}
	public static String getParentRIQuery(String id) {
		String sql = "SELECT Id, Name, OFSClient__ParentReleaseID__c, OFSClient__Status__c"
				+ " FROM OFSClient__ReleaseInformation__c"
				+ " where OFSClient__ParentReleaseID__c= '" + id + "'";
		return sql;
	}
}
