package com.util.sql;

public class PackageInformationSQLStmts {

	public static String getPackageInformationQuery(String id) {
		String sql = "SELECT Id, Name, OFSClient__Description__c, OFSClient__Release__c, OFSClient__Release__r.Name, OFSClient__ReadyForDeployment__c,OFSClient__Package_Retrieved_Time__c"
				+ " FROM OFSClient__PackageInformation__c"
				+ " where OFSClient__Release__c= '" + id + "'";
		return sql;
	}

	public static String getPkgInfoQueryOnRFD(String id) {
		String sql = "SELECT Id, Name, OFSClient__Description__c, OFSClient__Release__c, "
				+ "OFSClient__Release__r.Name, OFSClient__ReadyForDeployment__c,OFSClient__Package_Retrieved_Time__c"
				+ " FROM OFSClient__PackageInformation__c"
				+ " where id= '" + id + "'";
		return sql;
	}
	public static String getPkgInfo(String id) {
		String sql = "SELECT Id, Name, OFSClient__Description__c, OFSClient__Release__c, "
				+ "OFSClient__Release__r.Name, OFSClient__ReadyForDeployment__c,OFSClient__Package_Retrieved_Time__c"
				+ " FROM OFSClient__PackageInformation__c"
				+ " where id= '" + id + "'";
		return sql;
	}
	
	public static String getAllPkgInfoQuery() {
		String sql = "SELECT Id, Name, OFSClient__Description__c, OFSClient__Release__c, OFSClient__Release__r.Name, OFSClient__ReadyForDeployment__c,OFSClient__Package_Retrieved_Time__c"
				+ " FROM OFSClient__PackageInformation__c";
		return sql;
	}

	public static String getPackageInfoCompQuery(String id) {
		String sql = "SELECT (SELECT Id, Name, OFSClient__Name__c, OFSClient__Order__c, OFSClient__Package__c, "
				+ "OFSClient__ Package__r.Name, OFSClient__SourceOrganizationId__c, OFSClient__Type__c "
				+ " FROM OFSClient__PackageInformation__c.PackageComponentInformations__r ) "
				+ " FROM OFSClient__PackageInformation__c"
				+ " where Id= '"
				+ id + "'";
		return sql;
	}

	public static String getPackageInformationQuery1(String id) {
		String sql = "SELECT Id, OFSClient__Description__c, OFSClient__Release__c, OFSClient__Release__r.Name"
				+ " FROM OFSClient__PackageInformation__c"
				+ " where Id= '"
				+ id + "'";
		return sql;
	}

	public void junk() {
		// PackageInformation__c c = new PackageInformation__c();
		// c.getPackageComponentInformations__r();

		// QueryResult qrP2C =
		// binding.query("SELECT (SELECT Name FROM Parent__c.Children__r) FROM Parent__c LIMIT 1");

		// SObject So = qrP2C.getRecords()[0];
		// QueryResult subQR = (QueryResult)(So.get_any()[0].getObjectValue());
		// SObject subSo = subQR.getRecords()[0];
		// String subSoName = subSo.get_any()[0].getName();
		// String subSoValue = subSo.get_any()[0].getValue();

		// System.out.println(subSoName + ": " + subSoValue);
	}

}
