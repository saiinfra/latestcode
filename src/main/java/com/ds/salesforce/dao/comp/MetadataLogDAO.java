package com.ds.salesforce.dao.comp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.domain.MetadataLogDO;
import com.domain.PackageByOrderDO;
import com.domain.PackageDO;
import com.ds.salesforce.dao.ISFBaseDAO;
import com.exception.SFErrorCodes;
import com.exception.SFException;
import com.sforce.soap.enterprise.EnterpriseConnection;
import com.sforce.soap.enterprise.QueryResult;
import com.sforce.soap.enterprise.SaveResult;
import com.sforce.soap.enterprise.sobject.OFSServer__MetadataLog__c;
import com.sforce.soap.enterprise.sobject.SObject;
import com.util.SFoAuthHandle;
import com.util.sql.MetadataLogSQLStmts;

/**
 * 
 * @author MetadataLogDAO is Used For Performing CRUD Operations for
 *         {@link MetadataLog__c}
 *
 */
public class MetadataLogDAO implements ISFBaseDAO {

	public MetadataLogDAO() {
		super();
	}

	@Override
	public List<Object> listAll(SFoAuthHandle sfHandle) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insert(Object obj, SFoAuthHandle sfHandle) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Object obj, SFoAuthHandle sfHandle) {
		try {

			if (obj == null) {
				return false;
			}
			MetadataLogDO metadataLogDOobj = (MetadataLogDO) obj;
			com.sforce.soap.enterprise.sobject.OFSServer__MetadataLog__c metadataLog__c = new com.sforce.soap.enterprise.sobject.OFSServer__MetadataLog__c();

			if (metadataLogDOobj instanceof MetadataLogDO) {
				metadataLog__c.setId(metadataLogDOobj.getId());
				metadataLog__c.setOFSServer__Status__c(metadataLogDOobj.getStatus());
				
			}

			SaveResult[] saveResults = sfHandle
					.getEnterpriseConnection()
					.update(new com.sforce.soap.enterprise.sobject.OFSServer__MetadataLog__c[] { metadataLog__c });
			for (SaveResult r : saveResults) {
				if (r.isSuccess()) {
					System.out.println("Updated MetadataLogDAO component: "
							+ r.getId());
				} else {
					for (com.sforce.soap.enterprise.Error e : r.getErrors()) {

						throw new SFException(e.getMessage() + "-status code-"
								+ e.getStatusCode(),
								SFErrorCodes.SFMetadataLog_Update_Error);
					}
					return false;
				}
			}

		} catch (Exception e) {
			throw new SFException(e.toString(),
					SFErrorCodes.SFMetadataLog_Update_Error);
		}
		return false;
	}
	public boolean updateValidationId(Object obj, SFoAuthHandle sfHandle) {
		try {

			if (obj == null) {
				return false;
			}
			MetadataLogDO metadataLogDOobj = (MetadataLogDO) obj;
			com.sforce.soap.enterprise.sobject.OFSServer__MetadataLog__c metadataLog__c = new com.sforce.soap.enterprise.sobject.OFSServer__MetadataLog__c();

			if (metadataLogDOobj instanceof MetadataLogDO) {
				metadataLog__c.setId(metadataLogDOobj.getId());
				metadataLog__c.setOFSServer__ValidationSucessId__c(metadataLogDOobj.getValidationSuccessId());
				
			}

			SaveResult[] saveResults = sfHandle
					.getEnterpriseConnection()
					.update(new com.sforce.soap.enterprise.sobject.OFSServer__MetadataLog__c[] { metadataLog__c });
			for (SaveResult r : saveResults) {
				if (r.isSuccess()) {
					System.out.println("Updated MetadataLogDAO component: "
							+ r.getId());
				} else {
					for (com.sforce.soap.enterprise.Error e : r.getErrors()) {

						throw new SFException(e.getMessage() + "-status code-"
								+ e.getStatusCode(),
								SFErrorCodes.SFMetadataLog_Update_Error);
					}
					return false;
				}
			}

		} catch (Exception e) {
			throw new SFException(e.toString(),
					SFErrorCodes.SFMetadataLog_Update_Error);
		}
		return false;
	}

	@Override
	public boolean delete(Object obj, SFoAuthHandle sfHandle) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<Object> findById1(String metadataLogId, SFoAuthHandle sfHandle) {
		com.sforce.soap.enterprise.sobject.OFSServer__MetadataLog__c metadataLog__c = null;
		List<Object> list = new ArrayList<Object>();
		try {
			EnterpriseConnection conn = sfHandle.getEnterpriseConnection();
			QueryResult queryResults = conn.query(MetadataLogSQLStmts
					.getMetdataLogRecordQuery(metadataLogId));
			if (queryResults.getSize() > 0) {
				MetadataLogDO metadataDO = null;
				for (int i = 0; i < queryResults.getRecords().length; i++) {
					metadataLog__c = (com.sforce.soap.enterprise.sobject.OFSServer__MetadataLog__c) queryResults
							.getRecords()[i];

					
					metadataDO = new MetadataLogDO(metadataLog__c.getId(),
							metadataLog__c.getOFSServer__BaseOrgId__c(),
							metadataLog__c.getOFSServer__BaseOrgToken__c(),
							metadataLog__c.getOFSServer__BaseOrgUrl__c(),
							metadataLog__c.getOFSServer__OrganizationId__c(),
							metadataLog__c.getName(),
							metadataLog__c.getOFSServer__Action__c(),
							metadataLog__c.getOFSServer__Status__c(), null,null);
					System.out.println(" - Action: "
							+ metadataLog__c.getOFSServer__Action__c());
					System.out.println(" - Org Id: "
							+ metadataLog__c.getOFSServer__OrganizationId__c());
					System.out.println(" - Status: "
							+ metadataLog__c.getOFSServer__Status__c());
					System.out.println(" - Id: " + metadataLog__c.getId());
					System.out.println(" --------------: ");
					list.add(metadataDO);
				}
			} else {
				System.out.println(" There are no records size is - : "
						+ queryResults.getSize());
			}
		} catch (Exception e) {
			throw new SFException(e.toString(),
					SFErrorCodes.SFMetadataLog_Query_Error);
		}
		return list;
	}

	@Override
	public List<Object> findById(String metadataLogId, SFoAuthHandle sfHandle) {
		com.sforce.soap.enterprise.sobject.OFSServer__MetadataLog__c metadataLog__c = null;
		List<Object> list = new ArrayList<Object>();
		try {
			EnterpriseConnection conn = sfHandle.getEnterpriseConnection();
			QueryResult queryResults = conn.query(MetadataLogSQLStmts
					.getMetdataLogRecordQuery(metadataLogId));
			if (queryResults.getSize() > 0) {
				MetadataLogDO metadataDO = null;
				HashMap<Double, String> noOfPackgsByOrderMap = new HashMap<Double, String>();
				for (int i = 0; i < queryResults.getRecords().length; i++) {
					metadataLog__c = (com.sforce.soap.enterprise.sobject.OFSServer__MetadataLog__c) queryResults
							.getRecords()[i];
					noOfPackgsByOrderMap = new HashMap<Double, String>();
					
					System.out.println(" - Action: "
							+ metadataLog__c.getOFSServer__Action__c());
					System.out.println(" - Org Id: "
							+ metadataLog__c.getOFSServer__OrganizationId__c());
					System.out.println(" - Status: "
							+ metadataLog__c.getOFSServer__Status__c());
					System.out.println(" - Id: " + metadataLog__c.getId());
					System.out.println(" - Release: "
							+ metadataLog__c.getOFSServer__Releases__c());
					System.out.println(" - ReleaseEnvironment__c: "
							+ metadataLog__c.getOFSServer__ReleaseEnvironment__c());
					System.out.println(" --------------: ");

					if (metadataLog__c.getOFSServer__Releases__c() != null) {
						System.out.println("releases");
						ReleasePackageDAO dao = new ReleasePackageDAO();
						List<Object> list1 = dao.findByReleaseId(
								metadataLog__c.getOFSServer__Releases__c(), sfHandle);
						/*
						 * for (Iterator iterator = list1.iterator(); iterator
						 * .hasNext();) { PackageByOrderDO object =
						 * (PackageByOrderDO) iterator .next();
						 * 
						 * // gets the map by order HashMap<Double,
						 * List<Object>> deployMap = object .getMap();
						 * 
						 * Iterator<Map.Entry<Double, List<Object>>> entries =
						 * deployMap .entrySet().iterator(); while
						 * (entries.hasNext()) { Entry<Double, List<Object>>
						 * thisEntry = (Entry<Double, List<Object>>) entries
						 * .next();
						 * 
						 * Double key = (Double) thisEntry.getKey();
						 * System.out.println("key: " + key); List<Object>
						 * packgList = (List<Object>) thisEntry .getValue();
						 * String concatPackgStr = ""; for (Iterator iterator2 =
						 * packgList.iterator(); iterator2 .hasNext();) {
						 * PackageDO object2 = (PackageDO) iterator2 .next();
						 * concatPackgStr += object2.getName() + "~";
						 * System.out.println("Final List values: " +
						 * concatPackgStr); } noOfPackgsByOrderMap.put(key,
						 * concatPackgStr); } }
						 */
					}

					metadataDO = new MetadataLogDO(metadataLog__c.getId(),
							metadataLog__c.getOFSServer__BaseOrgId__c(),
							metadataLog__c.getOFSServer__BaseOrgToken__c(),
							metadataLog__c.getOFSServer__BaseOrgUrl__c(),
							metadataLog__c.getOFSServer__OrganizationId__c(),
							metadataLog__c.getName(),
							metadataLog__c.getOFSServer__Action__c(),
							metadataLog__c.getOFSServer__Status__c(), noOfPackgsByOrderMap,null);
					list.add(metadataDO);
				}
			} else {
				System.out.println(" There are no records size is - : "
						+ queryResults.getSize());
			}
		} catch (Exception e) {
			throw new SFException(e.toString(),
					SFErrorCodes.SFMetadataLog_Query_Error);
		}
		return list;
	}

	@Override
	public boolean commit(SObject[] sobjects, SFoAuthHandle sfHandle) {
		// TODO Auto-generated method stub
		return false;
	}

}
