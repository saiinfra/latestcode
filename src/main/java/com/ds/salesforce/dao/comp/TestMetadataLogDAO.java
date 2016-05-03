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
import com.domain.TestMetadataLogDO;
import com.ds.salesforce.dao.ISFBaseDAO;
import com.exception.SFErrorCodes;
import com.exception.SFException;
import com.sforce.soap.enterprise.EnterpriseConnection;
import com.sforce.soap.enterprise.QueryResult;
import com.sforce.soap.enterprise.SaveResult;
import com.sforce.soap.enterprise.sobject.SObject;
import com.util.SFoAuthHandle;
import com.util.sql.MetadataLogSQLStmts;

/**
 * 
 * @author MetadataLogDAO is Used For Performing CRUD Operations for
 *         {@link MetadataLog__c}
 *
 */
public class TestMetadataLogDAO implements ISFBaseDAO {

	public TestMetadataLogDAO() {
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
			TestMetadataLogDO metadataLogDOobj = (TestMetadataLogDO) obj;
			com.sforce.soap.enterprise.sobject.OFSServer__MetadataLog__c metadataLog__c = new com.sforce.soap.enterprise.sobject.OFSServer__MetadataLog__c();

			if (metadataLogDOobj instanceof TestMetadataLogDO) {
				metadataLog__c.setId(metadataLogDOobj.getId());
				metadataLog__c.setOFSServer__Status__c(metadataLogDOobj.getStatus());
			//	metadataLog__c.setOFSServer_ Message__c(metadataLogDOobj.getMessage());
				
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

	@Override
	public List<Object> findById(String metadataLogId, SFoAuthHandle sfHandle) {
		com.sforce.soap.enterprise.sobject.OFSServer__MetadataLog__c metadataLog__c = null;
		List<Object> list = new ArrayList<Object>();
		try {
			EnterpriseConnection conn = sfHandle.getEnterpriseConnection();
			QueryResult queryResults = conn.query(MetadataLogSQLStmts
					.gettestMetdataLogRecordQuery(metadataLogId));
			if (queryResults.getSize() > 0) {
				TestMetadataLogDO testMetadataLogDO = null;
				for (int i = 0; i < queryResults.getRecords().length; i++) {
					metadataLog__c = (com.sforce.soap.enterprise.sobject.OFSServer__MetadataLog__c) queryResults
							.getRecords()[i];

					/*testMetadataLogDO = new TestMetadataLogDO(
							metadataLog__c.getId(), metadataLog__c.getName(),
							metadataLog__c.getOFSServer__Name__c(),
							metadataLog__c.getOFSServer__Script__c(),
							metadataLog__c.getOFSServer__Action__c(),
							metadataLog__c.getOFSServer__Status__c(),
							metadataLog__c.getOFSServer__ID__c(),"");*/

					System.out.println(" - Action: "
							+ metadataLog__c.getOFSServer__Action__c());

					System.out.println(" - Status: "
							+ metadataLog__c.getOFSServer__Status__c());
					System.out.println(" - Id: " + metadataLog__c.getId());
					System.out.println(" --------------: ");
					list.add(testMetadataLogDO);
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
