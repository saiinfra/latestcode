package com.util.sql;

public class DeploymentSettingsSQLStmts {

	public static String getOrgEnvQuery(String orgId){
		String sql = "Select Id, OFSServer__BaseOrganizationId__c, OFSServer__TokenCode__c, OFSServer__Server_URL__c, OFSServer__RefreshTokenCode__c"
				+ " FROM OFSServer__DeploymentSetting__c where OFSServer__BaseOrganizationId__c = '"+orgId+"'";
		return sql;
	}
}
