package com.ds.salesforce.dao.comp;

import java.util.ArrayList;
import java.util.List;

import com.domain.ReleasesDO;
import com.ds.salesforce.dao.ISFBaseDAO;
import com.exception.SFErrorCodes;
import com.exception.SFException;
import com.sforce.soap.enterprise.EnterpriseConnection;
import com.sforce.soap.enterprise.QueryResult;
import com.sforce.soap.enterprise.UpsertResult;
import com.sforce.soap.enterprise.sobject.OFSServer__Releases__c;
import com.sforce.soap.enterprise.sobject.SObject;
import com.util.SFoAuthHandle;
import com.util.sql.ReleasesSQLStmts;

/**
 * 
 * @author ReleasesDAO is Used For Performing CRUD Operations for
 *         {@link Releases__c}
 *
 *
 */
public class ReleasesDAO implements ISFBaseDAO {

	public ReleasesDAO() {
		super();
	}

	@Override
	public List<Object> findById(String id, SFoAuthHandle sfHandle) {
		// TODO Auto-generated method stub
		ReleasesDO retObj = null;
		retObj = null;
		List list = new ArrayList();
		try {
			EnterpriseConnection conn = sfHandle.getEnterpriseConnection();
			System.out
					.println(" sql : " + ReleasesSQLStmts.getReleaseQuery(id));
			QueryResult queryResults = conn.query(ReleasesSQLStmts
					.getReleaseQuery(id));

			if (queryResults.getSize() > 0) {
				for (int i = 0; i < queryResults.getRecords().length; i++) {
					// cast the SObject to a strongly-typed Contact
					com.sforce.soap.enterprise.sobject.OFSServer__Releases__c locObj = (com.sforce.soap.enterprise.sobject.OFSServer__Releases__c) queryResults
							.getRecords()[i];

					System.out.println(" - Id: " + locObj.getId());
					System.out.println(" - Name: " + locObj.getName());
					System.out.println(" - Status: " + locObj.getOFSServer__Status__c());

					retObj = new ReleasesDO(locObj.getId(), locObj.getName(),
							locObj.getOFSServer__Status__c());
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
		return null;
	}

	@Override
	public boolean insert(Object obj, SFoAuthHandle sfHandle) {
		// create the records
		System.out.println("inserting ReleasesDO :");
		ReleasesDO relDO = (ReleasesDO) obj;
		OFSServer__Releases__c[] record = new OFSServer__Releases__c[1];
		try {
			// Get the name of the sObject
			OFSServer__Releases__c a = new OFSServer__Releases__c();
			a.setId(relDO.getId());
			a.setOFSServer__Status__c(relDO.getStatus());
			a.setName(relDO.getName());
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
		com.sforce.soap.enterprise.sobject.OFSServer__Releases__c newRelObj = new com.sforce.soap.enterprise.sobject.OFSServer__Releases__c();
		ReleasesDO lObj = (ReleasesDO) obj;
		try {
			newRelObj.setId(lObj.getId());
			newRelObj.setName(lObj.getName());
			newRelObj.setOFSServer__Status__c(lObj.getStatus());
			com.sforce.soap.enterprise.sobject.OFSServer__Releases__c[] mobj = new com.sforce.soap.enterprise.sobject.OFSServer__Releases__c[1];
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
						System.out.println("Updated Releases component: "
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
