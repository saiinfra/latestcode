package com.ds.salesforce.dao.comp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.domain.PackageInformationDO;
import com.domain.ReleaseInformationDO;
import com.ds.salesforce.dao.ISFBaseDAO;
import com.exception.SFErrorCodes;
import com.exception.SFException;
import com.sforce.soap.enterprise.EnterpriseConnection;
import com.sforce.soap.enterprise.QueryResult;
import com.sforce.soap.enterprise.UpsertResult;
import com.sforce.soap.enterprise.sobject.OFSClient__PackageInformation__c;
import com.sforce.soap.enterprise.sobject.SObject;
import com.util.SFoAuthHandle;
import com.util.sql.PackageInformationSQLStmts;

/**
 * 
 * @author PackageInformationDAO is Used For Performing CRUD Operations for
 *         {@link PackageInformation__c}
 *
 */

public class PackageInformationDAO implements ISFBaseDAO {

	public PackageInformationDAO() {
		super();

	}

	public static void main(String[] args){
		PackageInformationDAO dao = new PackageInformationDAO();
		
		
        /* String baseOrgId ="00D610000007yNVEAY";
         String baseOrgRefreshToken="5Aep861tbt360sO1.uO0UjNoRyP9rNbAguo__QeBtE9I0DtmCDxwo7brSO5PmIOdl7shrpeIuiwaynkros4QT68";
         String baseOrgToken="00D610000006tjP!AQ4AQAamQNlPSEd_aGvJ2vIV0AZCt4350damKY1sOvrwHOG27JoexgRhnQCkACmku1YjOFlTaz9rdUpMtg5XqHuLKMgb3a7q";
         String baseOrgUrl ="https://na34.salesforce.com";
		Org borg = new Org(baseOrgId, baseOrgToken, baseOrgUrl,
				baseOrgRefreshToken, Constants.BaseOrgID);
		dao.findByReadyForDepId("a0561000000aibp", FDGetSFoAuthHandleService
				.getSFoAuthHandle(borg));*/
		
	}
	
	@Override
	public List<Object> findById(String id, SFoAuthHandle sfHandle) {
		// TODO Auto-generated method stub


		// TODO Auto-generated method stub
		PackageInformationDO retObj = null;
		retObj = null;
		List list = new ArrayList();
		try {
			EnterpriseConnection conn = sfHandle.getEnterpriseConnection();
			System.out
					.println(" sql : "
							+ PackageInformationSQLStmts
									.getPkgInfo(id));
			QueryResult queryResults = conn.query(PackageInformationSQLStmts
					.getPkgInfo(id));

			if (queryResults.getSize() > 0) {
				for (int i = 0; i < queryResults.getRecords().length; i++) {
					// cast the SObject to a strongly-typed Contact
					com.sforce.soap.enterprise.sobject.OFSClient__PackageInformation__c locObj = (com.sforce.soap.enterprise.sobject.OFSClient__PackageInformation__c) queryResults
							.getRecords()[i];

					System.out.println(" - Id: " + locObj.getId());
					System.out.println(" - Name : " + locObj.getName());
					System.out.println(" - desc: "
							+ locObj.getOFSClient__Description__c());
					System.out.println(" - release Id: "
							+ locObj.getOFSClient__Release__c());
					System.out.println(" - release Name: "
							+ locObj.getOFSClient__Release__r().getName());
					
					System.out.println("Package Status: "+locObj.getOFSClient__ReadyForDeployment__c());
					retObj = new PackageInformationDO(locObj.getId(),
							locObj.getName(),
							locObj.getOFSClient__Description__c(),
							locObj.getOFSClient__Release__c(),
							locObj.getOFSClient__ReadyForDeployment__c(),
							locObj.getOFSClient__Package_Retrieved_Time__c());

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

	public List<Object> findByReadyForDepId(String id, SFoAuthHandle sfHandle) {
		// TODO Auto-generated method stub
		PackageInformationDO retObj = null;
		retObj = null;
		List list = new ArrayList();
		try {
			EnterpriseConnection conn = sfHandle.getEnterpriseConnection();
			System.out
					.println(" sql : "
							+ PackageInformationSQLStmts
									.getPkgInfoQueryOnRFD(id));
			QueryResult queryResults = conn.query(PackageInformationSQLStmts
					.getPkgInfoQueryOnRFD(id));

			if (queryResults.getSize() > 0) {
				for (int i = 0; i < queryResults.getRecords().length; i++) {
					// cast the SObject to a strongly-typed Contact
					com.sforce.soap.enterprise.sobject.OFSClient__PackageInformation__c locObj = (com.sforce.soap.enterprise.sobject.OFSClient__PackageInformation__c) queryResults
							.getRecords()[i];

					System.out.println(" - Id: " + locObj.getId());
					System.out.println(" - Name : " + locObj.getName());
					System.out
							.println(" - desc: " + locObj.getOFSClient__Description__c());
					System.out.println(" - release Id: "
							+ locObj.getOFSClient__Release__c());
					System.out.println(" - release Name: "
							+ locObj.getOFSClient__Release__r().getName());
					System.out.println(" - ready status: "
							+ locObj.getOFSClient__ReadyForDeployment__c());
					
					System.out.println(" - ready status: "
							+ locObj.getOFSClient__Package_Retrieved_Time__c());
					
					
					retObj = new PackageInformationDO(locObj.getId(),
							locObj.getName(), locObj.getOFSClient__Description__c(),
							locObj.getOFSClient__Release__c(), locObj.getOFSClient__ReadyForDeployment__c(),locObj.getOFSClient__Package_Retrieved_Time__c());

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

	public List<Object> findByReleaseId(String id, SFoAuthHandle sfHandle) {
		// TODO Auto-generated method stub
		PackageInformationDO retObj = null;
		retObj = null;
		List list = new ArrayList();
		try {
			EnterpriseConnection conn = sfHandle.getEnterpriseConnection();
			System.out
					.println(" sql : "
							+ PackageInformationSQLStmts
									.getPackageInformationQuery(id));
			QueryResult queryResults = conn.query(PackageInformationSQLStmts
					.getPackageInformationQuery(id));

			if (queryResults.getSize() > 0) {
				for (int i = 0; i < queryResults.getRecords().length; i++) {
					// cast the SObject to a strongly-typed Contact
					com.sforce.soap.enterprise.sobject.OFSClient__PackageInformation__c locObj = (com.sforce.soap.enterprise.sobject.OFSClient__PackageInformation__c) queryResults
							.getRecords()[i];

					System.out.println(" - Id: " + locObj.getId());
					System.out.println(" - Name : " + locObj.getName());
					System.out.println(" - desc: "
							+ locObj.getOFSClient__Description__c());
					System.out.println(" - release Id: "
							+ locObj.getOFSClient__Release__c());
					System.out.println(" - release Name: "
							+ locObj.getOFSClient__Release__r().getName());
					
					System.out.println("Package Status: "+locObj.getOFSClient__ReadyForDeployment__c());
					retObj = new PackageInformationDO(locObj.getId(),
							locObj.getName(),
							locObj.getOFSClient__Description__c(),
							locObj.getOFSClient__Release__c(),
							locObj.getOFSClient__ReadyForDeployment__c(),
							locObj.getOFSClient__Package_Retrieved_Time__c());

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

		PackageInformationDO retObj = null;
		List list = new ArrayList();
		try {
			EnterpriseConnection conn = sfHandle.getEnterpriseConnection();
			System.out.println(" sql : "
					+ PackageInformationSQLStmts.getAllPkgInfoQuery());
			QueryResult queryResults = conn.query(PackageInformationSQLStmts
					.getAllPkgInfoQuery());

			if (queryResults.getSize() > 0) {
				for (int i = 0; i < queryResults.getRecords().length; i++) {
					// cast the SObject to a strongly-typed Contact
					com.sforce.soap.enterprise.sobject.OFSClient__PackageInformation__c locObj = (com.sforce.soap.enterprise.sobject.OFSClient__PackageInformation__c) queryResults
							.getRecords()[i];
					retObj = new PackageInformationDO(locObj.getId(),
							locObj.getOFSClient__Description__c(),
							locObj.getName());
					list.add(retObj);
				}
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
		System.out.println("insereting PackageInformationDO :");
		PackageInformationDO pkgInfoDO = (PackageInformationDO) obj;
		OFSClient__PackageInformation__c[] record = new OFSClient__PackageInformation__c[1];
		try {
			// find ReleaseInformation record
			ReleaseInformationDAO relDAO = new ReleaseInformationDAO();
			List<Object> relInfoList = relDAO.findById(
					pkgInfoDO.getReleaseInformationId(), sfHandle);

			// Create ReleaseOjects in all the Environments
			for (Iterator iterator = relInfoList.iterator(); iterator.hasNext();) {
				ReleaseInformationDO relInfoDO = (ReleaseInformationDO) iterator
						.next();
				System.out.println(relInfoDO.getId());
				OFSClient__PackageInformation__c a = new OFSClient__PackageInformation__c();
				a.setName(pkgInfoDO.getName());
				a.setOFSClient__Description__c(pkgInfoDO.getDescription());
				a.setOFSClient__Release__c(relInfoDO.getId());
				record[0] = a;
				commit(record, sfHandle);
				
				
			}
			// Get the name of the sObject

		} catch (Exception ce) {
			throw new SFException(ce.toString(),
					SFErrorCodes.SFEnvironment_Update_Error);
		}
		return true;
	}

	@Override
	public boolean update(Object obj, SFoAuthHandle sfHandle) {
		com.sforce.soap.enterprise.sobject.OFSClient__PackageInformation__c newEnvObj = new com.sforce.soap.enterprise.sobject.OFSClient__PackageInformation__c();
		PackageInformationDO lObj = (PackageInformationDO) obj;
		try {
			newEnvObj.setId(lObj.getId());
			newEnvObj.setName(lObj.getName());
			newEnvObj.setOFSClient__Description__c(lObj.getDescription());
			newEnvObj.setOFSClient__Release__c(lObj.getReleaseInformationId());
			newEnvObj.setOFSClient__ReadyForDeployment__c(lObj.getReadyForDeployment());
			com.sforce.soap.enterprise.sobject.OFSClient__PackageInformation__c[] mobj = new com.sforce.soap.enterprise.sobject.OFSClient__PackageInformation__c[1];
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

	public boolean updatePackageRetrievedTime(Object obj, SFoAuthHandle sfHandle) {
		com.sforce.soap.enterprise.sobject.OFSClient__PackageInformation__c newEnvObj = new com.sforce.soap.enterprise.sobject.OFSClient__PackageInformation__c();
		PackageInformationDO lObj = (PackageInformationDO) obj;
		try {
			newEnvObj.setId(lObj.getId());
			newEnvObj.setName(lObj.getName());
			newEnvObj.setOFSClient__Description__c(lObj.getDescription());
			newEnvObj.setOFSClient__Release__c(lObj.getReleaseInformationId());
			newEnvObj.setOFSClient__ReadyForDeployment__c(lObj.getReadyForDeployment());
			newEnvObj.setOFSClient__Package_Retrieved_Time__c(lObj.getCalendar());
			// Set the value of Package_Retrieved_Time__c to null
			//newEnvObj.setFieldsToNull(new String[] {"OFSClient__Package_Retrieved_Time__c"});
			com.sforce.soap.enterprise.sobject.OFSClient__PackageInformation__c[] mobj = new com.sforce.soap.enterprise.sobject.OFSClient__PackageInformation__c[1];
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
	public boolean clearPackageRetrievedTime(Object obj, SFoAuthHandle sfHandle) {
		com.sforce.soap.enterprise.sobject.OFSClient__PackageInformation__c newEnvObj = new com.sforce.soap.enterprise.sobject.OFSClient__PackageInformation__c();
		PackageInformationDO lObj = (PackageInformationDO) obj;
		try {
			newEnvObj.setId(lObj.getId());
			newEnvObj.setName(lObj.getName());
			newEnvObj.setOFSClient__Description__c(lObj.getDescription());
			newEnvObj.setOFSClient__Release__c(lObj.getReleaseInformationId());
			newEnvObj.setOFSClient__ReadyForDeployment__c(lObj.getReadyForDeployment());
			newEnvObj.setFieldsToNull(new String[] {"OFSClient__Package_Retrieved_Time__c"});
			com.sforce.soap.enterprise.sobject.OFSClient__PackageInformation__c[] mobj = new com.sforce.soap.enterprise.sobject.OFSClient__PackageInformation__c[1];
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
						System.out.println("Updated PkgINfo : " + r.getId());
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
			//e.printStackTrace();
			throw new SFException(e.toString(),
					SFErrorCodes.SFEnvironment_Update_Error);
		}
		return true;
	}
}
