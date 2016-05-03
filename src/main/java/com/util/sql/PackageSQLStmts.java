package com.util.sql;

public class PackageSQLStmts {

	public static String getPackageQuery(String id) {
		String sql = "SELECT Id, Name"
				+ " FROM OFSServer__Package__c"
				+ " where Id= '" + id + "'";
		return sql;
	}
	
	public static String getPackageNameQuery(String name) {
		String sql = "SELECT Id, Name"
				+ " FROM OFSServer__Package__c"
				+ " where Name= '" + name + "'";
		return sql;
	}
	public static String getPackageParent(String id) {
		String sql = "SELECT Id, Name"
				+ " FROM OFSServer__Package__c"
				+ " where OFSServer__ParentPackageID__c= '" + id + "'";
		return sql;
	}
}
