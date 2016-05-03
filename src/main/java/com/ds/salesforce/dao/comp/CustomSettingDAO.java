package com.ds.salesforce.dao.comp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.domain.DeploymentSettingsDO;
import com.domain.EnvironmentDO;
import com.ds.salesforce.dao.ISFBaseDAO;
import com.exception.SFErrorCodes;
import com.exception.SFException;
import com.sforce.soap.enterprise.EnterpriseConnection;
import com.sforce.soap.enterprise.QueryResult;
import com.sforce.soap.enterprise.SaveResult;
import com.sforce.soap.enterprise.sobject.OFSServer__Enviroment__c;
import com.sforce.soap.enterprise.sobject.SObject;
import com.util.Constants;
import com.util.SFoAuthHandle;
import com.util.sql.DeploymentSettingsSQLStmts;

/**
 * 
 * @author DeploySettingsDAO is Used For Performing CRUD Operations for
 *         {@link DeploymentSetting__c}
 *
 */
public class CustomSettingDAO implements ISFBaseDAO {

	public CustomSettingDAO() {
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
		// TODO Auto-generated method stub

		return true;
	}

	public boolean commit(SObject[] sobjects, SFoAuthHandle sfHandle) {
		// System.out.println("SAVE--"+sfHandle.getUserId()+"--"+sfHandle.getPasswd());
		try {
			com.sforce.soap.enterprise.SaveResult[] saveResults = sfHandle
					.getEnterpriseConnection().create(sobjects);

			for (SaveResult r : saveResults) {
				if (r.isSuccess()) {
					System.out.println("Created DeployDetails record - Id: "
							+ r.getId());
				} else {
					for (com.sforce.soap.enterprise.Error e : r.getErrors()) {
						System.out.println("-status code-" + e.getStatusCode());
						throw new SFException(e.getMessage() + "-status code-"
								+ e.getStatusCode(),
								SFErrorCodes.SFDeployDetails_Update_Error);
					}
					return false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
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
		com.sforce.soap.enterprise.sobject.OFSServer__DeploymentSetting__c retObj = null;
		DeploymentSettingsDO dsDO = new DeploymentSettingsDO();
		List list = new ArrayList();
		try {
			EnterpriseConnection conn = sfHandle.getEnterpriseConnection();
			QueryResult queryResults = conn.query(DeploymentSettingsSQLStmts
					.getOrgEnvQuery(orgId));
			if (queryResults.getSize() > 0) {
				for (int i = 0; i < queryResults.getRecords().length; i++) {
					// cast the SObject to a strongly-typed Contact
					retObj = (com.sforce.soap.enterprise.sobject.OFSServer__DeploymentSetting__c) queryResults
							.getRecords()[i];
					dsDO = new DeploymentSettingsDO(retObj.getId(),
							retObj.getOFSServer__BaseOrganizationId__c(),
							retObj.getOFSServer__TokenCode__c(),
							retObj.getOFSServer__Server_URL__c(),
							retObj.getOFSServer__RefreshTokenCode__c());
					System.out.println(" - Id: " + retObj.getId());
					System.out.println(" - BaseOrg: "
							+ retObj.getOFSServer__BaseOrganizationId__c());
					System.out.println(" - token: "
							+ retObj.getOFSServer__TokenCode__c());
					System.out.println(" - server url: "
							+ retObj.getOFSServer__Server_URL__c());
					System.out.println(" - refreshToken: "
							+ retObj.getOFSServer__RefreshTokenCode__c());
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

	public List<Object> findByName(Map<String, List<String>> map,
			SFoAuthHandle sfHandle) {
		// TODO Auto-generated method stub
		List list = new ArrayList();
		String Columns = "";
		String query = "";
		for (Map.Entry<String, List<String>> entry : map.entrySet()) {
			String key = entry.getKey();
			System.out.println("Key : " + entry.getKey());
			for (String val : entry.getValue()) {
				Columns = Columns + val + ",";
			}
			int index = Columns.lastIndexOf(",");
			Columns = new StringBuilder(Columns).replace(index, index + 1, " ")
					.toString();
			System.out.println(Columns);
			query = "SELECT" + " " + Columns + "" + "from " + " " + key + "";
			System.out.println("key-----------" + key + "  " + query);

			try {
				EnterpriseConnection conn = sfHandle.getEnterpriseConnection();
				QueryResult queryResults = conn.query(query);
                SObject[] ss=new SObject[queryResults.getSize()];
				for (int k = 0; k < queryResults.getRecords().length; k++) {

					com.sforce.soap.enterprise.sobject.SObject retObj =
							 (com.sforce.soap.enterprise.sobject.SObject)queryResults.getRecords()[k];
					ss[0]=retObj;
					String destiOrgID="00DS0000003Km6LMAS";
					String destiToken="00DS0000003Km6L!AQsAQEErUwd04udOBkK9zjdhmPClOD7M57saZ1xLNpmnj1NSPRke36tOiyIRNgaLAymlHGl__2W64nV_d75MgWHqDueKEjd3";
					String destiURL="https://emc--OppVis.cs1.my.salesforce.com";
					String destiRefreshCode="5Aep861KIwKdekr90IIyhfEcZZxgNJfv58m5BvQaUg0P_g1MEaXRqGZ8bZ978X9BuwlK.w_JfBFmPR4wHphbu33";
					SFoAuthHandle dd=new SFoAuthHandle(destiOrgID, destiToken,destiURL, destiRefreshCode, Constants.CustomOrgID);
					 commit(ss, dd);
					
					// com.sforce.soap.enterprise.sobject.SObject retObj =
					// (com.sforce.soap.enterprise.sobject.SObject)queryResults.getRecords()[k];
					// String strObjType = String.valueOf(retObj);
				}
				
			   

			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return list;
	}

	public List<Object> findByName1(String sql, String objname,
			SFoAuthHandle sfHandle) {
		// TODO Auto-generated method stub
		com.sforce.soap.enterprise.sobject.OFSServer__DeploymentSetting__c retObj = null;
		DeploymentSettingsDO dsDO = new DeploymentSettingsDO();
		List list = new ArrayList();
		try {
			EnterpriseConnection conn = sfHandle.getEnterpriseConnection();
			QueryResult queryResults = conn.query(sql);
			if (queryResults.getSize() > 0) {
				for (int i = 0; i < queryResults.getRecords().length; i++) {
					// cast the SObject to a strongly-typed Contact
					retObj = (com.sforce.soap.enterprise.sobject.OFSServer__DeploymentSetting__c) queryResults
							.getRecords()[i];
					dsDO = new DeploymentSettingsDO(retObj.getId(),
							retObj.getOFSServer__BaseOrganizationId__c(),
							retObj.getOFSServer__TokenCode__c(),
							retObj.getOFSServer__Server_URL__c(),
							retObj.getOFSServer__RefreshTokenCode__c());
					System.out.println(" - Id: " + retObj.getId());
					System.out.println(" - BaseOrg: "
							+ retObj.getOFSServer__BaseOrganizationId__c());
					System.out.println(" - token: "
							+ retObj.getOFSServer__TokenCode__c());
					System.out.println(" - server url: "
							+ retObj.getOFSServer__Server_URL__c());
					System.out.println(" - refreshToken: "
							+ retObj.getOFSServer__RefreshTokenCode__c());
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