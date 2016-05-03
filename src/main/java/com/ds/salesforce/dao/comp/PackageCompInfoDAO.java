package com.ds.salesforce.dao.comp;

import java.util.ArrayList;
import java.util.List;

import com.domain.PackageCompInfoDO;
import com.ds.salesforce.dao.ISFBaseDAO;
import com.exception.SFErrorCodes;
import com.exception.SFException;
import com.sforce.soap.enterprise.EnterpriseConnection;
import com.sforce.soap.enterprise.QueryResult;
import com.sforce.soap.enterprise.UpsertResult;
import com.sforce.soap.enterprise.sobject.SObject;
import com.util.SFoAuthHandle;
import com.util.sql.PackageCompInfoSQLStmts;

/**
 * 
 * @author PackageCompInfoDAO is Used For Performing CRUD Operations for
 *         {@link PackageComponentInformation__c}
 *
 */
public class PackageCompInfoDAO implements ISFBaseDAO {

	public PackageCompInfoDAO() {
		super();

	}

	@Override
	public List<Object> findById(String id, SFoAuthHandle sfHandle) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Object> findByPackageId(String id, SFoAuthHandle sfHandle) {
		// TODO Auto-generated method stub
		PackageCompInfoDO retObj = null;
		retObj = null;
		List list = new ArrayList();
		try {
			EnterpriseConnection conn = sfHandle.getEnterpriseConnection();
			System.out
					.println(" sql : "
							+ PackageCompInfoSQLStmts
									.getParentPackageCompInfoQuery(id));
			QueryResult queryResults = conn.query(PackageCompInfoSQLStmts
					.getParentPackageCompInfoQuery(id));

			if (queryResults.getSize() > 0) {
				for (int i = 0; i < queryResults.getRecords().length; i++) {
					// cast the SObject to a strongly-typed Contact
					com.sforce.soap.enterprise.sobject.OFSClient__PackageComponentInformation__c locObj = (com.sforce.soap.enterprise.sobject.OFSClient__PackageComponentInformation__c) queryResults
							.getRecords()[i];

					System.out.println(" - Id: " + locObj.getId());
					System.out.println(" - Name: " + locObj.getName());
					System.out.println(" - Name__c: "
							+ locObj.getOFSClient__Name__c());
					System.out.println(" - Order__c: "
							+ locObj.getOFSClient__Order__c());
					System.out.println(" - Package__c Id: "
							+ locObj.getOFSClient__Package__c());
					System.out.println(" - Package__c Id: "
							+ locObj.getOFSClient__Package__r().getName());
					System.out.println(" - SourceOrganizationId__c: "
							+ locObj.getOFSClient__SourceOrganizationId__c());
					System.out.println(" - Type: "
							+ locObj.getOFSClient__Type__c());

					String Order = "1";
					retObj = new PackageCompInfoDO(locObj.getId(),
							locObj.getName(), locObj.getOFSClient__Name__c(),
							Order, locObj.getOFSClient__Type__c(),
							locObj.getOFSClient__SourceOrganizationId__c(),
							locObj.getOFSClient__Package__c());

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
		PackageCompInfoDO retObj = null;
		List list = new ArrayList();
		return list;
	}

	@Override
	public boolean insert(Object obj, SFoAuthHandle sfHandle) {
		// create the records
		System.out.println("insereting PkgCompInfo DAO :");
		PackageCompInfoDO pkgCompInfoDO = (PackageCompInfoDO) obj;
		com.sforce.soap.enterprise.sobject.OFSClient__PackageComponentInformation__c[] record = new com.sforce.soap.enterprise.sobject.OFSClient__PackageComponentInformation__c[1];
		try {
			// Get the name of the sObject
			com.sforce.soap.enterprise.sobject.OFSClient__PackageComponentInformation__c a = new com.sforce.soap.enterprise.sobject.OFSClient__PackageComponentInformation__c();
			a.setName(pkgCompInfoDO.getPkgCompInfoName());
			a.setOFSClient__Name__c(pkgCompInfoDO.getObjName());
			a.setOFSClient__Order__c(pkgCompInfoDO.getOrder());
			a.setOFSClient__Package__c(pkgCompInfoDO.getPkgInfoParentId());
			a.setOFSClient__SourceOrganizationId__c(pkgCompInfoDO
					.getSourceOrganizationId());
			a.setOFSClient__Type__c(pkgCompInfoDO.getType());

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
		com.sforce.soap.enterprise.sobject.OFSClient__PackageComponentInformation__c newObj = new com.sforce.soap.enterprise.sobject.OFSClient__PackageComponentInformation__c();
		PackageCompInfoDO lObj = (PackageCompInfoDO) obj;
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
						commitC(sobjects, sfHandle);
					}
				}
			}
		} catch (Exception e) {
			throw new SFException(e.toString(),
					SFErrorCodes.SFEnvironment_Update_Error);
		}
		return true;
	}

	public boolean commitC(SObject[] sobjects, SFoAuthHandle sfHandle) {
		// System.out.println("SAVE--"+sfHandle.getUserId()+"--"+sfHandle.getPasswd());
		try {
			com.sforce.soap.enterprise.SaveResult[] saveResults = sfHandle
					.getEnterpriseConnection().create(sobjects);

			// check the returned results for any errors
			for (int i = 0; i < saveResults.length; i++) {
				if (saveResults[i].isSuccess()) {
					System.out
							.println(i
									+ ". Successfully created MetadataDescription record - Id: "
									+ saveResults[i].getId());
				} else {
					com.sforce.soap.enterprise.Error[] errors = saveResults[i]
							.getErrors();
					StringBuffer sb = new StringBuffer();
					for (int j = 0; j < errors.length; j++) {
						sb.append(errors[j].getMessage());
						sb.append("\n");
						System.out.println("ERROR creating record: "
								+ errors[j].getMessage());
					}
					throw new SFException(sb.toString(),
							SFErrorCodes.SFMetadataDescription_Insert_Error);
				}
			}
		} catch (Exception e) {
			throw new SFException(e.toString(),
					SFErrorCodes.SFMetadataDescription_Insert_Error);
		}
		return true;
	}

}
