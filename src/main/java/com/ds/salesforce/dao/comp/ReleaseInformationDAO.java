package com.ds.salesforce.dao.comp;

import java.util.ArrayList;
import java.util.List;

import com.domain.ReleaseInformationDO;
import com.ds.salesforce.dao.ISFBaseDAO;
import com.exception.SFErrorCodes;
import com.exception.SFException;
import com.sforce.soap.enterprise.EnterpriseConnection;
import com.sforce.soap.enterprise.QueryResult;
import com.sforce.soap.enterprise.UpsertResult;
import com.sforce.soap.enterprise.sobject.OFSClient__ReleaseInformation__c;
import com.sforce.soap.enterprise.sobject.SObject;
import com.util.Constants;
import com.util.SFoAuthHandle;
import com.util.sql.ReleaseInformationSQLStmts;

/**
 * 
 * @author ReleaseInformationDAO is Used For Performing CRUD Operations for
 *         {@link ReleaseInformation__c}
 *
 */
public class ReleaseInformationDAO implements ISFBaseDAO {

	public ReleaseInformationDAO() {
		super();
	}

	@Override
	public List<Object> findById(String id, SFoAuthHandle sfHandle) {
		// TODO Auto-generated method stub
		ReleaseInformationDO retObj = null;
		retObj = null;
		List list = new ArrayList();
		try {
			EnterpriseConnection conn = sfHandle.getEnterpriseConnection();
			System.out.println(" sql : "
					+ ReleaseInformationSQLStmts.getRIQuery(id));
			QueryResult queryResults = conn.query(ReleaseInformationSQLStmts
					.getRIQuery(id));

			if (queryResults.getSize() > 0) {
				for (int i = 0; i < queryResults.getRecords().length; i++) {
					// cast the SObject to a strongly-typed Contact
					com.sforce.soap.enterprise.sobject.OFSClient__ReleaseInformation__c locObj = (com.sforce.soap.enterprise.sobject.OFSClient__ReleaseInformation__c) queryResults
							.getRecords()[i];

					System.out.println(" - Id: " + locObj.getId());
					System.out.println(" - ParentReleaseID: "
							+ locObj.getOFSClient__ParentReleaseID__c());
					System.out.println(" - Name: " + locObj.getName());
					System.out.println(" - Status: "
							+ locObj.getOFSClient__Status__c());

					retObj = new ReleaseInformationDO(locObj.getId(),
							locObj.getOFSClient__ParentReleaseID__c(),
							locObj.getName(), locObj.getOFSClient__Status__c());
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

	public List<Object> findByParentId(String id, SFoAuthHandle sfHandle) {
		// TODO Auto-generated method stub
		ReleaseInformationDO retObj = null;
		retObj = null;
		List list = new ArrayList();
		try {
			EnterpriseConnection conn = sfHandle.getEnterpriseConnection();
			System.out.println("sri....."
					+ sfHandle.getEnterpriseConnection().getUserInfo()
							.getOrganizationId());
			System.out.println(" sql : "
					+ ReleaseInformationSQLStmts.getParentRIQuery(id));
			QueryResult queryResults = conn.query(ReleaseInformationSQLStmts
					.getParentRIQuery(id));

			if (queryResults.getSize() > 0) {
				for (int i = 0; i < queryResults.getRecords().length; i++) {
					// cast the SObject to a strongly-typed Contact
					com.sforce.soap.enterprise.sobject.OFSClient__ReleaseInformation__c locObj = (com.sforce.soap.enterprise.sobject.OFSClient__ReleaseInformation__c) queryResults
							.getRecords()[i];

					System.out.println(" - Id: " + locObj.getId());
					System.out.println(" - ParentReleaseID: "
							+ locObj.getOFSClient__ParentReleaseID__c());
					System.out.println(" - Name: " + locObj.getName());
					System.out.println(" - Status: "
							+ locObj.getOFSClient__Status__c());

					if (locObj.getOFSClient__Status__c().equals(
							Constants.RELEASE_STATUS_ACTIVE)) {
						retObj = new ReleaseInformationDO(locObj.getId(),
								locObj.getOFSClient__ParentReleaseID__c(),
								locObj.getName(),
								locObj.getOFSClient__Status__c());
						list.add(retObj);
					}
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
		return null;
	}

	@Override
	public boolean insert(Object obj, SFoAuthHandle sfHandle) {
		// create the records
		System.out.println("inserting ReleasesDO :");
		ReleaseInformationDO relDO = (ReleaseInformationDO) obj;
		OFSClient__ReleaseInformation__c[] record = new OFSClient__ReleaseInformation__c[1];
		try {
			// Get the name of the sObject
			OFSClient__ReleaseInformation__c a = new OFSClient__ReleaseInformation__c();
			// a.setId(relDO.getId());
			a.setOFSClient__Status__c(relDO.getStatus());
			a.setOFSClient__ParentReleaseID__c(relDO.getParentReleaseID());
			a.setName(relDO.getParentReleaseName());
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
		com.sforce.soap.enterprise.sobject.OFSClient__ReleaseInformation__c newRelObj = new com.sforce.soap.enterprise.sobject.OFSClient__ReleaseInformation__c();
		ReleaseInformationDO lObj = (ReleaseInformationDO) obj;
		try {
			newRelObj.setId(lObj.getId());
			newRelObj.setOFSClient__ParentReleaseID__c(lObj
					.getParentReleaseID());
			newRelObj.setOFSClient__Status__c(lObj.getStatus());
			com.sforce.soap.enterprise.sobject.OFSClient__ReleaseInformation__c[] mobj = new com.sforce.soap.enterprise.sobject.OFSClient__ReleaseInformation__c[1];
			mobj[0] = newRelObj;
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
						System.out
								.println("Updated ReleaseInformation component: "
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
