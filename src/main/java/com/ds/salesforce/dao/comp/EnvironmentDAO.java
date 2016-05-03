package com.ds.salesforce.dao.comp;

import java.util.ArrayList;
import java.util.List;

import com.domain.EnvironmentDO;
import com.ds.salesforce.dao.ISFBaseDAO;
import com.exception.SFErrorCodes;
import com.exception.SFException;
import com.sforce.soap.enterprise.EnterpriseConnection;
import com.sforce.soap.enterprise.QueryResult;
import com.sforce.soap.enterprise.UpsertResult;
import com.sforce.soap.enterprise.sobject.OFSServer__Enviroment__c;
import com.sforce.soap.enterprise.sobject.SObject;
import com.util.SFoAuthHandle;
import com.util.sql.EnvironmentSQLStmts;

/**
 * 
 * @author EnvironmentDAO is Used For Performing CRUD Operations for
 *         {@link Enviroment__c}
 *
 */

public class EnvironmentDAO implements ISFBaseDAO {

	public EnvironmentDAO() {
		super();
	}

	@Override
	public List<Object> findById(String orgId, SFoAuthHandle sfHandle) {
		// TODO Auto-generated method stub
		EnvironmentDO retObj = null;
		retObj = null;
		List<Object> list = new ArrayList<Object>();
		try {
			EnterpriseConnection conn = sfHandle.getEnterpriseConnection();
			System.out.println(" sql : "
					+ EnvironmentSQLStmts.getOrgEnvQuery(orgId));
			QueryResult queryResults = conn.query(EnvironmentSQLStmts
					.getOrgEnvQuery(orgId));

			if (queryResults.getSize() > 0) {
				for (int i = 0; i < queryResults.getRecords().length; i++) {
					// cast the SObject to a strongly-typed Contact
					com.sforce.soap.enterprise.sobject.OFSServer__Enviroment__c locObj = (com.sforce.soap.enterprise.sobject.OFSServer__Enviroment__c) queryResults
							.getRecords()[i];

			/*		System.out.println(" - Id: " + locObj.getId());
					System.out.println(" - Id: " + locObj.getOFSServer__Org_ID__c());
					System.out.println(" - Name: "
							+ locObj.getOFSServer__User_Name__c());
					System.out.println(" - Org: "
							+ locObj.getOFSServer__OrganizationId__c());
					System.out.println(" - token: "
							+ locObj.getOFSServer__TokenCode__c());
					System.out.println(" - refresh: "
							+ locObj.getOFSServer__RefreshTokenCode__c());
					System.out.println(" - encr: "
							+ locObj.getOFSServer__TokenCodeNonEncrypted__c());
					System.out.println(" - server: "
							+ locObj.getOFSServer__Server_URL__c());

					System.out.println(" - Enable Version Control: "
							+ locObj.getOFSServer__Enable_Version_Control__c());

					System.out.println(" - Git URL: "
							+ locObj.getOFSServer__GIT_Server_URL__c());

					System.out.println(" - Git USERNAME: "
							+ locObj.getOFSServer__Git_User_Name__c());

					System.out.println(" - Git PASSWORD: "
							+ locObj.getOFSServer__GitPassword__c());*/

					retObj = new EnvironmentDO(locObj.getId(),
							locObj.getOFSServer__Org_ID__c(),
							locObj.getOFSServer__User_Name__c(),
							locObj.getOFSServer__OrganizationId__c(),
							locObj.getOFSServer__TokenCodeNonEncrypted__c(),
							locObj.getOFSServer__Server_URL__c(),
							locObj.getOFSServer__RefreshTokenCode__c(),
							locObj.getOFSServer__Enable_Version_Control__c(),
							locObj.getOFSServer__GIT_Server_URL__c(),
							locObj.getOFSServer__Git_User_Name__c(),
							locObj.getOFSServer__GitPassword__c());
					
					System.out.println(" - Git PASSWORD: "
							+ locObj.getOFSServer__GitPassword__c());
					list.add(retObj);
				}
			} else {
				System.out.println(" There are no records size is - : "
						+ queryResults.getSize());
			}
		} catch (SFException e) {
			throw new SFException(e.toString(),
					SFErrorCodes.SFEnvironment_Query_Error);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SFException(e.toString(),
					SFErrorCodes.SFEnvironment_Query_Error);
		}
		return list;
	}

	@Override
	public List<Object> listAll(SFoAuthHandle sfHandle) {
		// TODO Auto-generated method stub
		EnvironmentDO retObj = null;
		List list = new ArrayList();
		try {
			EnterpriseConnection conn = sfHandle.getEnterpriseConnection();
			System.out
					.println(" sql : " + EnvironmentSQLStmts.getAllEnvQuery());
			QueryResult queryResults = conn.query(EnvironmentSQLStmts
					.getAllEnvQuery());

			if (queryResults.getSize() > 0) {
				for (int i = 0; i < queryResults.getRecords().length; i++) {
					// cast the SObject to a strongly-typed Contact
					com.sforce.soap.enterprise.sobject.OFSServer__Enviroment__c locObj = (com.sforce.soap.enterprise.sobject.OFSServer__Enviroment__c) queryResults
							.getRecords()[i];
					retObj = new EnvironmentDO(locObj.getId(),
							locObj.getOFSServer__Org_ID__c(),
							locObj.getOFSServer__User_Name__c(),
							locObj.getOFSServer__OrganizationId__c(),
							locObj.getOFSServer__TokenCodeNonEncrypted__c(),
							locObj.getOFSServer__Server_URL__c(),
							locObj.getOFSServer__RefreshTokenCode__c());
					list.add(retObj);
				}
			} else {
				System.out.println(" There are no records size is - : "
						+ queryResults.getSize());
			}
		} catch (SFException e) {
			throw new SFException(e.toString(),
					SFErrorCodes.SFEnvironment_Query_Error);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SFException(e.toString(),
					SFErrorCodes.SFEnvironment_Query_Error);
		}
		return list;
	}

	@Override
	public boolean insert(Object obj, SFoAuthHandle sfHandle) {
		// create the records
		System.out.println("insereting EnvironmentDAO :");
		EnvironmentDO envDO = (EnvironmentDO) obj;
		OFSServer__Enviroment__c[] record = new OFSServer__Enviroment__c[1];
		try {
			// Get the name of the sObject
			OFSServer__Enviroment__c a = new OFSServer__Enviroment__c();
			a.setOFSServer__Org_ID__c(envDO.getOrgId());
			a.setOFSServer__TokenCode__c(envDO.getToken());
			a.setOFSServer__TokenCodeNonEncrypted__c(envDO.getToken());
			a.setOFSServer__Server_URL__c(envDO.getServerURL());

			record[0] = a;
			commit(record, sfHandle);
		} catch (Exception ce) {
			throw new SFException(ce.toString(),
					SFErrorCodes.SFEnvironment_Update_Error);
		}
		return true;
	}

	@Override
	public boolean update(Object obj, SFoAuthHandle sfHandle) {
		com.sforce.soap.enterprise.sobject.OFSServer__Enviroment__c newEnvObj = new com.sforce.soap.enterprise.sobject.OFSServer__Enviroment__c();
		EnvironmentDO lObj = (EnvironmentDO) obj;
		try {
			newEnvObj.setId(lObj.getId());
			newEnvObj.setOFSServer__Org_ID__c(lObj.getOrgIdPlusUserId());
			newEnvObj.setOFSServer__User_Name__c(lObj.getUserName());
			newEnvObj.setOFSServer__TokenCode__c(lObj.getToken());
			newEnvObj.setOFSServer__TokenCodeNonEncrypted__c(lObj
					.getTokenCodeNonEncrypted());
			newEnvObj.setOFSServer__RefreshTokenCode__c(lObj.getRefreshtoken());
			newEnvObj.setOFSServer__Server_URL__c(lObj.getServerURL());
			com.sforce.soap.enterprise.sobject.OFSServer__Enviroment__c[] mobj = new com.sforce.soap.enterprise.sobject.OFSServer__Enviroment__c[1];
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
	public boolean delete(Object obj, SFoAuthHandle sfHandle) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean commit(SObject[] sobjects, SFoAuthHandle sfHandle) {
		try {
			if (sobjects != null && sobjects.length > 0) {
				UpsertResult[] results = sfHandle.getEnterpriseConnection()
						.upsert("Id", sobjects);
				for (UpsertResult r : results) {
					if (r.isSuccess()) {
						System.out.println("Updated Environment component: "
								+ r.getId());
					} else {
						System.out
								.println("Errors were encountered while updating "
										+ r.getId());
						for (com.sforce.soap.enterprise.Error e : r.getErrors()) {
							System.out.println("Error message: "
									+ e.getMessage());
							System.out.println("Status code: "
									+ e.getStatusCode());
						}
					}
				}
			}
		} catch (Exception e) {
			throw new SFException(e.toString(),
					SFErrorCodes.SFEnvironment_Update_Error);
		}
		return true;
	}

	public boolean commit1(SObject[] sobjects, SFoAuthHandle sfHandle) {
		try {
			if (sobjects != null && sobjects.length > 0) {
				UpsertResult[] results = sfHandle.getEnterpriseConnection()
						.upsert("Id", sobjects);
				for (UpsertResult r : results) {
					if (r.isSuccess()) {
						System.out.println("Updated Environment component: "
								+ r.getId());
					} else {
						System.out
								.println("Errors were encountered while updating "
										+ r.getId());
						for (com.sforce.soap.enterprise.Error e : r.getErrors()) {
							System.out.println("Error message: "
									+ e.getMessage());
							System.out.println("Status code: "
									+ e.getStatusCode());
						}
					}
				}
			}
		} catch (Exception e) {
			throw new SFException(e.toString(),
					SFErrorCodes.SFEnvironment_Update_Error);
		}
		return true;
	}
}
