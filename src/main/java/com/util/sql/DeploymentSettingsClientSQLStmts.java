package com.util.sql;

public class  DeploymentSettingsClientSQLStmts {

	public static String getOrgEnvQuery(String orgId){
		String sql = "Select Id, OFSClient__BaseOrganizationId__c, OFSClient__TokenCode__c, OFSClient__Server_URL__c, OFSClient__RefreshTokenCode__c"
				+ " FROM OFSClient__DeploymentSettingClient__c where OFSClient__BaseOrganizationId__c = '"+orgId+"'";
		return sql;
	}
}
