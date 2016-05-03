package com.ds.salesforce.dao.comp;

import java.util.ArrayList;
import java.util.List;

import com.domain.ReleasePackageDO;
import com.domain.ReleasesDO;
import com.ds.salesforce.dao.ISFBaseDAO;
import com.exception.SFErrorCodes;
import com.exception.SFException;
import com.sforce.soap.enterprise.EnterpriseConnection;
import com.sforce.soap.enterprise.QueryResult;
import com.sforce.soap.enterprise.UpsertResult;
import com.sforce.soap.enterprise.sobject.OFSServer__ReleasePackage__c;
import com.sforce.soap.enterprise.sobject.SObject;
import com.util.SFoAuthHandle;
import com.util.sql.ReleasePackageSQLStmts;
import com.util.sql.ReleasesSQLStmts;

/**
 * 
 * @author ReleasePackageDAO is Used For Performing CRUD Operations for
 *         {@link ReleasePackage__c}
 *
 */
public class ReleasePackageDAO implements ISFBaseDAO {

	public ReleasePackageDAO() {
		super();
	}

	public List<Object> findByReleaseId(String id, SFoAuthHandle sfHandle) {
		// TODO Auto-generated method stub
		ReleasePackageDO retObj = null;
		retObj = null;
		List list = new ArrayList();
		try {
			EnterpriseConnection conn = sfHandle.getEnterpriseConnection();
			System.out.println(" sql : "
					+ ReleasePackageSQLStmts.getFindByReleaseId(id) + "");
			QueryResult queryResults = conn.query(ReleasePackageSQLStmts
					.getFindByReleaseId(id));

			if (queryResults.getSize() > 0) {
				for (int i = 0; i < queryResults.getRecords().length; i++) {
					// cast the SObject to a strongly-typed Contact
					com.sforce.soap.enterprise.sobject.OFSServer__ReleasePackage__c locObj = (com.sforce.soap.enterprise.sobject.OFSServer__ReleasePackage__c) queryResults
							.getRecords()[i];

					System.out.println(" - Id: " + locObj.getId());
					System.out.println(" - Name: " + locObj.getName());
					System.out.println(" - Order: "
							+ locObj.getOFSServer__Order__c());
					System.out.println(" - package: "
							+ locObj.getOFSServer__Package__c());
					System.out.println(" - release: "
							+ locObj.getOFSServer__Release__c());

					retObj = new ReleasePackageDO(locObj.getId(),
							locObj.getName(), locObj.getOFSServer__Order__c(),
							locObj.getOFSServer__Package__c(),
							locObj.getOFSServer__Release__c());
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
	public List<Object> findById(String id, SFoAuthHandle sfHandle) {
		// TODO Auto-generated method stub
		ReleasePackageDO retObj = null;
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
					com.sforce.soap.enterprise.sobject.OFSServer__ReleasePackage__c locObj = (com.sforce.soap.enterprise.sobject.OFSServer__ReleasePackage__c) queryResults
							.getRecords()[i];

					System.out.println(" - Id: " + locObj.getId());
					System.out.println(" - Name: " + locObj.getName());
					System.out.println(" - Order: "
							+ locObj.getOFSServer__Order__c());
					System.out.println(" - package: "
							+ locObj.getOFSServer__Package__c());
					System.out.println(" - release: "
							+ locObj.getOFSServer__Release__c());

					retObj = new ReleasePackageDO(locObj.getId(),
							locObj.getName(), locObj.getOFSServer__Order__c(),
							locObj.getOFSServer__Package__c(),
							locObj.getOFSServer__Release__c());
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

	public List<Object> findByPkgIDAndRID(String pid, String rid,
			SFoAuthHandle sfHandle) {
		// TODO Auto-generated method stub
		ReleasePackageDO retObj = null;
		retObj = null;
		List list = new ArrayList();
		try {
			EnterpriseConnection conn = sfHandle.getEnterpriseConnection();
			System.out.println(" sql : "
					+ ReleasePackageSQLStmts.getfindByPkgIDAndRID(pid, rid)
					+ "");
			QueryResult queryResults = conn.query(ReleasePackageSQLStmts
					.getfindByPkgIDAndRID(pid, rid));

			if (queryResults.getSize() > 0) {
				for (int i = 0; i < queryResults.getRecords().length; i++) {
					// cast the SObject to a strongly-typed Contact
					com.sforce.soap.enterprise.sobject.OFSServer__ReleasePackage__c locObj = (com.sforce.soap.enterprise.sobject.OFSServer__ReleasePackage__c) queryResults
							.getRecords()[i];

					System.out.println(" - Id: " + locObj.getId());
					System.out.println(" - Name: " + locObj.getName());
					System.out.println(" - Order: "
							+ locObj.getOFSServer__Order__c());
					System.out.println(" - package: "
							+ locObj.getOFSServer__Package__c());
					System.out.println(" - release: "
							+ locObj.getOFSServer__Release__c());

					retObj = new ReleasePackageDO(locObj.getId(),
							locObj.getName(), locObj.getOFSServer__Order__c(),
							locObj.getOFSServer__Package__c(),
							locObj.getOFSServer__Release__c());
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

	public List<Object> findByPkgIDAndRID1(String pid, String rid,
			SFoAuthHandle sfHandle) {
		// TODO Auto-generated method stub
		ReleasePackageDO retObj = null;
		retObj = null;
		List list = new ArrayList();
		try {
			EnterpriseConnection conn = sfHandle.getEnterpriseConnection();
			System.out.println(" sql : "
					+ ReleasePackageSQLStmts.getfindByPkgIDAndRID(pid, rid)
					+ "");
			QueryResult queryResults = conn.query(ReleasePackageSQLStmts
					.getfindByPkgIDAndRID(pid, rid));

			if (queryResults.getSize() > 0) {
				for (int i = 0; i < queryResults.getRecords().length; i++) {
					// cast the SObject to a strongly-typed Contact
					com.sforce.soap.enterprise.sobject.OFSServer__ReleasePackage__c locObj = (com.sforce.soap.enterprise.sobject.OFSServer__ReleasePackage__c) queryResults
							.getRecords()[i];

					System.out.println(" - Id: " + locObj.getId());
					System.out.println(" - Name: " + locObj.getName());
					System.out.println(" - Order: "
							+ locObj.getOFSServer__Order__c());
					System.out.println(" - package: "
							+ locObj.getOFSServer__Package__c());
					System.out.println(" - release: "
							+ locObj.getOFSServer__Release__c());

					retObj = new ReleasePackageDO(locObj.getId(),
							locObj.getName(), locObj.getOFSServer__Order__c(),
							locObj.getOFSServer__Package__c(),
							locObj.getOFSServer__Release__c());
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
		ReleasePackageDO relDO = (ReleasePackageDO) obj;
		OFSServer__ReleasePackage__c[] record = new OFSServer__ReleasePackage__c[1];
		try {
			// Get the name of the sObject
			OFSServer__ReleasePackage__c a = new OFSServer__ReleasePackage__c();
			a.setId(relDO.getId());
			a.setName(relDO.getName());
			a.setOFSServer__Order__c(relDO.getOrder());
			a.setOFSServer__Package__c(relDO.getPackageC());
			a.setOFSServer__Release__c(relDO.getReleaseC());
			record[0] = a;
			commit(record, sfHandle);
		} catch (Exception ce) {
			throw new SFException(ce.toString(),
					SFErrorCodes.SFEnvironment_Update_Error);
		}
		return true;
	}

	public String insertAndGetId(Object obj, SFoAuthHandle sfHandle) {
		// create the records
		System.out.println("inserting ReleasesPackgeDO :");
		ReleasePackageDO relDO = (ReleasePackageDO) obj;
		OFSServer__ReleasePackage__c[] record = new OFSServer__ReleasePackage__c[1];
		String id = "";
		try {
			// Get the name of the sObject
			OFSServer__ReleasePackage__c a = new OFSServer__ReleasePackage__c();
			// a.setId(relDO.getId());
			a.setName(relDO.getName());
			a.setOFSServer__Order__c(relDO.getOrder());
			a.setOFSServer__Package__c(relDO.getPackageC());
			a.setOFSServer__Release__c(relDO.getReleaseC());
			//a.setOFSServer__Source_Environment__c(relDO.getSourceEnv());
			a.setOFSServer__Target_Environment__c(relDO.getSourceEnv());
			record[0] = a;
			id = commitC(record, sfHandle);
		} catch (Exception ce) {
			throw new SFException(ce.toString(),
					SFErrorCodes.SFEnvironment_Update_Error);
		}
		return id;
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
						System.out.println("Updated ReleasePackage component: "
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

	public String commitC(SObject[] sobjects, SFoAuthHandle sfHandle) {
		String id = "";
		try {
			if (sobjects != null && sobjects.length > 0) {
				UpsertResult[] results = sfHandle.getEnterpriseConnection()
						.upsert("Id", sobjects);
				for (UpsertResult r : results) {
					if (r.isSuccess()) {
						System.out.println("Updated ReleasePackage component: "
								+ r.getId());
						id = r.getId();
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
						id = r.getId();
					}
				}
			}
		} catch (Exception e) {
			throw new SFException(e.toString(),
					SFErrorCodes.SFEnvironment_Update_Error);
		}
		return id;
	}
}
