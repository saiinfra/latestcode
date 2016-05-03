package com.ds.salesforce.dao.comp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.domain.MetadataLogInformationDO;
import com.ds.salesforce.dao.ISFBaseDAO;
import com.exception.SFErrorCodes;
import com.exception.SFException;
import com.sforce.soap.enterprise.EnterpriseConnection;
import com.sforce.soap.enterprise.QueryResult;
import com.sforce.soap.enterprise.SaveResult;
import com.sforce.soap.enterprise.sobject.SObject;
import com.util.SFoAuthHandle;
import com.util.sql.MetadataLogInformationSQLStmts;

/**
 * 
 * @author MetadataLogInformationDAO is Used For Performing CRUD Operations for
 *         {@link MetadataLogInformation__c}
 *
 */
public class MetadataLogInformationDAO implements ISFBaseDAO {

	public MetadataLogInformationDAO() {
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
			MetadataLogInformationDO metadataLogInformationDOobj = (MetadataLogInformationDO) obj;
			com.sforce.soap.enterprise.sobject.OFSClient__MetadataLogInformation__c metadataLogInformation__c = new com.sforce.soap.enterprise.sobject.OFSClient__MetadataLogInformation__c();

			if (metadataLogInformationDOobj instanceof MetadataLogInformationDO) {
				metadataLogInformation__c.setId(metadataLogInformationDOobj.getId());
				metadataLogInformation__c.setOFSClient__Status__c(metadataLogInformationDOobj.getStatus());
			}

			SaveResult[] saveResults = sfHandle
					.getEnterpriseConnection()
					.update(new com.sforce.soap.enterprise.sobject.OFSClient__MetadataLogInformation__c[] { metadataLogInformation__c });
			for (SaveResult r : saveResults) {
				if (r.isSuccess()) {
					System.out.println("Updated MetadataLogInformationDAO component: "
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
		com.sforce.soap.enterprise.sobject.OFSClient__MetadataLogInformation__c metadataLogInformation__c = null;
		List<Object> list = new ArrayList<Object>();
		try {
			EnterpriseConnection conn = sfHandle.getEnterpriseConnection();
			QueryResult queryResults = conn.query(MetadataLogInformationSQLStmts
					.getMetdataLogRecordQuery(metadataLogId));
			if (queryResults.getSize() > 0) {
				MetadataLogInformationDO metadataDO = null;
				for (int i = 0; i < queryResults.getRecords().length; i++) {
					metadataLogInformation__c = (com.sforce.soap.enterprise.sobject.OFSClient__MetadataLogInformation__c) queryResults
							.getRecords()[i];

					
					metadataDO = new MetadataLogInformationDO(metadataLogInformation__c.getId(),
							metadataLogInformation__c.getOFSClient__BaseOrgId__c(),
							metadataLogInformation__c.getOFSClient__BaseOrgToken__c(),
							metadataLogInformation__c.getOFSClient__BaseOrgUrl__c(),
							metadataLogInformation__c.getOFSClient__OrganizationId__c(),
							metadataLogInformation__c.getName(),
							metadataLogInformation__c.getOFSClient__Action__c(),
							metadataLogInformation__c.getOFSClient__Status__c(), null);
				
					System.out.println(" - Action: "
							+ metadataLogInformation__c.getOFSClient__Action__c());
					System.out.println(" - Org Id: "
							+ metadataLogInformation__c.getOFSClient__OrganizationId__c());
					System.out.println(" - Status: "
							+ metadataLogInformation__c.getOFSClient__Status__c());
					System.out.println(" - Id: " + metadataLogInformation__c.getId());
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
		com.sforce.soap.enterprise.sobject.OFSClient__MetadataLogInformation__c metadataLogInformation__c = null;
		List<Object> list = new ArrayList<Object>();
		try {
			EnterpriseConnection conn = sfHandle.getEnterpriseConnection();
			QueryResult queryResults = conn.query(MetadataLogInformationSQLStmts
					.getMetdataLogRecordQuery(metadataLogId));
			if (queryResults.getSize() > 0) {
				MetadataLogInformationDO metadataDO = null;
				HashMap<Double, String> noOfPackgsByOrderMap = new HashMap<Double, String>();
				for (int i = 0; i < queryResults.getRecords().length; i++) {
					metadataLogInformation__c = (com.sforce.soap.enterprise.sobject.OFSClient__MetadataLogInformation__c) queryResults
							.getRecords()[i];
					noOfPackgsByOrderMap = new HashMap<Double, String>();
					
					System.out.println(" - Action: "
							+ metadataLogInformation__c.getOFSClient__Action__c());
					System.out.println(" - Org Id: "
							+ metadataLogInformation__c.getOFSClient__OrganizationId__c());
					System.out.println(" - Status: "
							+ metadataLogInformation__c.getOFSClient__Status__c());
					System.out.println(" - Id: " + metadataLogInformation__c.getId());
					
					
					System.out.println(" --------------: ");

				
					metadataDO = new MetadataLogInformationDO(metadataLogInformation__c.getId(),
							metadataLogInformation__c.getOFSClient__BaseOrgId__c(),
							metadataLogInformation__c.getOFSClient__BaseOrgToken__c(),
							metadataLogInformation__c.getOFSClient__BaseOrgUrl__c(),
							metadataLogInformation__c.getOFSClient__OrganizationId__c(),
							metadataLogInformation__c.getName(),
							metadataLogInformation__c.getOFSClient__Action__c(),
							metadataLogInformation__c.getOFSClient__Status__c(), noOfPackgsByOrderMap);
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
