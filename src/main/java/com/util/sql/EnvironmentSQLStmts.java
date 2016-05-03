package com.util.sql;

public class EnvironmentSQLStmts {

	public static String getOrgEnvQuery(String orgId) {
		String sql = "Select Id, OFSServer__Org_ID__c,OFSServer__OrganizationId__c, OFSServer__User_Name__c, OFSServer__Password__c, OFSServer__Server_URL__c, "
				+ " OFSServer__TokenCode__c, OFSServer__RefreshTokenCode__c, OFSServer__TokenCodeNonEncrypted__c, OFSServer__Type__c,OFSServer__Enable_Version_Control__c,OFSServer__GIT_Server_URL__c,OFSServer__Git_User_Name__c,OFSServer__GitPassword__c,OFSServer__Git_Passwords__c"
				+ " FROM OFSServer__Enviroment__c where OFSServer__OrganizationId__c = '"
				+ orgId + "'";
		return sql;
	}

	public static String getAllEnvQuery() {
		String sql = "Select Id, OFSServer__Org_ID__c, OFSServer__OrganizationId__c, OFSServer__User_Name__c, OFSServer__Password__c, OFSServer__Server_URL__c, "
				+ " OFSServer__TokenCode__c, OFSServer__RefreshTokenCode__c, OFSServer__TokenCodeNonEncrypted__c, OFSServer__Type__c,OFSServer__Enable_Version_Control__c,OFSServer__GIT_Server_URL__c,OFSServer__Git_User_Name__c,OFSServer__GitPassword__c,OFSServer__Git_Passwords__c"
				+ " FROM OFSServer__Enviroment__c";
		return sql;
	}

	public static String getAllClientEnvQuery() {
		String sql = "Select Id, OFSClient__Org_ID__c, OFSClient__OrganizationId__c, OFSClient__User_Name__c, OFSClient__Password__c, OFSClient__Server_URL__c, "
				+ " OFSClient__TokenCode__c, OFSClient__RefreshTokenCode__c, OFSClient__TokenCodeNonEncrypted__c, OFSClient__Type__c,OFSClient__Auth_Type__c"
				+ " FROM OFSClient__EnviromentInformation__c";
		return sql;
	}

	public static String getClientOrgEnvQuery(String orgId) {
		String sql = "Select Id, OFSClient__Org_ID__c,OFSClient__OrganizationId__c, OFSClient__User_Name__c, OFSClient__Password__c, OFSClient__Server_URL__c, "
				+ " OFSClient__TokenCode__c, OFSClient__RefreshTokenCode__c, OFSClient__TokenCodeNonEncrypted__c, OFSClient__Type__c,OFSClient__Auth_Type__c"
				+ " FROM OFSClient__EnviromentInformation__c where OFSClient__OrganizationId__c = '"
				+ orgId + "'";
		return sql;
	}
}
