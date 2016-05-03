package com.util.sql;

public class ReleasesSQLStmts {

	public static String getReleaseQuery(String id) {
		String sql = "SELECT Id, Name, OFSServer__Status__c"
				+ " FROM OFSServer__Releases__c"
				+ " where Id= '" + id + "'";
		return sql;
	}
}
