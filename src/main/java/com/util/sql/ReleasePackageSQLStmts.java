package com.util.sql;

public class ReleasePackageSQLStmts {

	public static String getReleasePackageNameQuery(String id) {
		String sql = "SELECT Id, Name, OFSServer__Package__c, OFSServer__Order__c, OFSServer__Release__c"
				+ " FROM OFSServer__ReleasePackage__c"
				+ " where Id= '" + id + "'";
		return sql;
	}
	
	public static String getReleaseQuery(String id) {
		String sql = "SELECT Id, Name, OFSServer__Package__c, OFSServer__Order__c, OFSServer__Release__c"
				+ " FROM OFSServer__ReleasePackage__c"
				+ " where OFSServer__Release__c= '" + id + "'";
		return sql;
	}
	public static String getfindByPkgIDAndRID(String pid,String rid) {
		String sql = "SELECT Id, Name, OFSServer__Package__c, OFSServer__Order__c, OFSServer__Release__c"
				+ " FROM OFSServer__ReleasePackage__c"
				+ " where OFSServer__Package__c= '" + pid + "' and OFSServer__Release__c='" +rid+"'";
		return sql;
	}
	
	public static String getFindByReleaseId(String rid) {
		String sql = "SELECT Id, Name, OFSServer__Package__c, OFSServer__Order__c, OFSServer__Release__c"
				+ " FROM OFSServer__ReleasePackage__c"
				+ " where OFSServer__Release__c= '" + rid + "'";
		return sql;
	}
}
