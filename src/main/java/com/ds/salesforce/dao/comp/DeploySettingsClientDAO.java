package com.ds.salesforce.dao.comp;

import java.util.ArrayList;
import java.util.List;

import com.domain.DeploymentSettingClientDO;
import com.ds.salesforce.dao.ISFBaseDAO;
import com.exception.SFErrorCodes;
import com.exception.SFException;
import com.sforce.soap.enterprise.EnterpriseConnection;
import com.sforce.soap.enterprise.QueryResult;
import com.sforce.soap.enterprise.UpsertResult;
import com.sforce.soap.enterprise.sobject.OFSClient__DeploymentSettingClient__c;
import com.sforce.soap.enterprise.sobject.SObject;
import com.util.SFoAuthHandle;
import com.util.sql.DeploymentSettingsClientSQLStmts;


/**
 * 
 * @author DeploySettingsClientDAO is Used For Performing CRUD Operations for {@link DeploymentSettingClient__c}
 *
 */
public class DeploySettingsClientDAO implements ISFBaseDAO {

	public DeploySettingsClientDAO() {
		super();
	}
	
	@Override
	public List<Object> listAll(SFoAuthHandle sfHandle) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insert(Object obj, SFoAuthHandle sfHandle) {
		// create the records
		DeploymentSettingClientDO deploySettingsDO = (DeploymentSettingClientDO) obj;
		OFSClient__DeploymentSettingClient__c[] record = new OFSClient__DeploymentSettingClient__c[1];
		try {
			// Get the name of the sObject
			OFSClient__DeploymentSettingClient__c a = new OFSClient__DeploymentSettingClient__c();
			a.setOFSClient__BaseOrganizationId__c(deploySettingsDO.getOrgId());
			a.setOFSClient__Server_URL__c(deploySettingsDO.getServerUrl());
			a.setOFSClient__TokenCode__c(deploySettingsDO.getToken());
			a.setOFSClient__RefreshTokenCode__c(deploySettingsDO.getRefreshToken());
			record[0] = a;
			commit(record, sfHandle);
		} catch (Exception ce) {
			throw new SFException(ce.toString(),
					SFErrorCodes.SFDeploymentSettings_Update_Error);
		}
		return true;
	}

	@Override
	public boolean update(Object obj, SFoAuthHandle sfHandle) {
		// TODO Auto-generated method stub
		com.sforce.soap.enterprise.sobject.OFSClient__DeploymentSettingClient__c newEnvObj = new com.sforce.soap.enterprise.sobject.OFSClient__DeploymentSettingClient__c();
		DeploymentSettingClientDO dsDO = (DeploymentSettingClientDO) obj;
		// lObj = (com.sforce.soap.enterprise.sobject.DeploymentSetting__c) obj;
		try {
			newEnvObj.setId(dsDO.getId());
			newEnvObj.setOFSClient__BaseOrganizationId__c(dsDO.getOrgId());
			newEnvObj.setOFSClient__TokenCode__c(dsDO.getToken());
			newEnvObj.setOFSClient__Server_URL__c(dsDO.getServerUrl());
			newEnvObj.setOFSClient__RefreshTokenCode__c(dsDO.getRefreshToken());
			com.sforce.soap.enterprise.sobject.OFSClient__DeploymentSettingClient__c[] mobj = new com.sforce.soap.enterprise.sobject.OFSClient__DeploymentSettingClient__c[1];
			mobj[0] = newEnvObj;
			commit(mobj, sfHandle);
		} catch (SFException e) {
			throw new SFException(e.toString(),
					SFErrorCodes.SFEnvironment_Update_Error);
		} catch (Exception e) {
			throw new SFException(e.toString(),
					SFErrorCodes.SFEnvironment_Update_Error);
		}
		return true;
	}

	@Override
	public boolean commit(SObject[] sobjects, SFoAuthHandle sfHandle) {
		try {
			com.sforce.soap.enterprise.UpsertResult[] saveResults = sfHandle
					.getEnterpriseConnection().upsert("Id", sobjects);

			for (UpsertResult r : saveResults) {
				if (r.isSuccess()) {
					System.out.println("Created DeploySettings record - Id: "
							+ r.getId());
				} else {
					for (com.sforce.soap.enterprise.Error e : r.getErrors()) {
						throw new SFException(e.getMessage() + "-status code-"
								+ e.getStatusCode(),
								SFErrorCodes.SFDeploymentSettings_Update_Error);
					}
					return false;
				}
			}
			System.out
					.println("saving deploymentSettingsDAO from CustomAuth :");
		} catch (Exception e) {
			throw new SFException(e.toString(),
					SFErrorCodes.SFDeployDetails_Update_Error);
		}
		return true;
	}

	@Override
	public boolean delete(Object obj, SFoAuthHandle sfHandle) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Object> findById(String orgId, SFoAuthHandle sfHandle) {
		// TODO Auto-generated method stub
		com.sforce.soap.enterprise.sobject.OFSClient__DeploymentSettingClient__c retObj = null;
		DeploymentSettingClientDO dsDO = new DeploymentSettingClientDO();
		List list = new ArrayList();
		try {
			EnterpriseConnection conn = sfHandle.getEnterpriseConnection();
			QueryResult queryResults = conn.query(DeploymentSettingsClientSQLStmts
					.getOrgEnvQuery(orgId));
			if (queryResults.getSize() > 0) {
				for (int i = 0; i < queryResults.getRecords().length; i++) {
					// cast the SObject to a strongly-typed Contact
					retObj = (com.sforce.soap.enterprise.sobject.OFSClient__DeploymentSettingClient__c) queryResults
							.getRecords()[i];
					dsDO = new DeploymentSettingClientDO(retObj.getId(),
							retObj.getOFSClient__BaseOrganizationId__c(),
							retObj.getOFSClient__TokenCode__c(),
							retObj.getOFSClient__Server_URL__c(),
							retObj.getOFSClient__RefreshTokenCode__c());
					System.out.println(" - Id: " + retObj.getId());
					System.out.println(" - BaseOrg: "
							+ retObj.getOFSClient__BaseOrganizationId__c());
					System.out.println(" - token: " + retObj.getOFSClient__TokenCode__c());
					System.out.println(" - server url: "
							+ retObj.getOFSClient__Server_URL__c());
					System.out.println(" - refreshToken: "
							+ retObj.getOFSClient__RefreshTokenCode__c());
					list.add(dsDO);
				}
			} else {
				System.out.println(" There are no records size is - : "
						+ queryResults.getSize());
			}
		} catch (SFException e) {
			throw new SFException(e.toString(),
					SFErrorCodes.SFEnvironment_Query_Error);
		} catch (Exception e) {
			throw new SFException(e.toString(),
					SFErrorCodes.SFEnvironment_Query_Error);
		}
		return list;
	}
}
