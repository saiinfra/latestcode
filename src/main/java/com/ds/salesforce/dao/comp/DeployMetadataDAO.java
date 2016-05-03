package com.ds.salesforce.dao.comp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.domain.MetaBean;
import com.domain.PackageDO;
import com.ds.salesforce.dao.ISFBaseDAO;
import com.exception.SFErrorCodes;
import com.exception.SFException;
import com.sforce.soap.enterprise.QueryResult;
import com.sforce.soap.enterprise.sobject.SObject;
import com.util.SFoAuthHandle;
import com.util.sql.DeployMetadataSQLStmts;

/**
 * 
 * @author DeployMetadataDAO is Used For Performing CRUD Operations for
 *         {@link DeployMetadata__c}
 *
 */
public class DeployMetadataDAO implements ISFBaseDAO {

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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Object obj, SFoAuthHandle sfHandle) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Object> findById(String metadataLogIdName,
			SFoAuthHandle sfHandle) {
		// TODO Auto-generated method stub
		List<Object> beanList = null;
		MetaBean metaBean = null;
		com.sforce.soap.enterprise.sobject.OFSServer__DeployMetadata__c retObj = null;
		System.out.println("SQL: "
				+ DeployMetadataSQLStmts
						.getToBeDeployedRecordQuery(metadataLogIdName));
		try {
			QueryResult queryResults = sfHandle.getEnterpriseConnection()
					.query(DeployMetadataSQLStmts
							.getToBeDeployedRecordQuery(metadataLogIdName));
			if (queryResults.getSize() > 0) {
				beanList = new ArrayList<Object>();
				for (int i = 0; i < queryResults.getRecords().length; i++) {
					retObj = (com.sforce.soap.enterprise.sobject.OFSServer__DeployMetadata__c) queryResults
							.getRecords()[i];
					System.out.println(retObj.getOFSServer__Order__c());
					
					System.out.println(" - Name: " + retObj.getOFSServer__Name__c());
					System.out.println(" - Type: " + retObj.getOFSServer__Type__c());
					System.out.println(" - Source Org Id: "
							+ retObj.getOFSServer__SourceOrganizationId__c());
					System.out.println(" - Target Org Id: "
							+ retObj.getOFSServer__OrganizationId__c());
				
				
					System.out.println(" - Order: " + retObj.getOFSServer__Order__c());
					System.out.println(" - Package: " + retObj.getOFSServer__Package__c());
					PackageDO packageDO = null;
					if(retObj.getOFSServer__Package__c() != null){
						PackageDAO dao = new PackageDAO();
						List<Object> packageDOList = dao.findById(retObj.getOFSServer__Package__c(), sfHandle);
						for (Iterator iterator = packageDOList.iterator(); iterator.hasNext();) {
							packageDO = (PackageDO) iterator.next();
							System.out.println("pache Name: "+packageDO.getName());
						}
					}
					
					System.out.println(" - metadata log: "
							+ retObj.getOFSServer__MetadataLog__r().getName());
					System.out.println(" - Id: " + retObj.getId());
					String packageName= "";
					if(packageDO != null){
						packageName = packageDO.getName();
					}
					metaBean = new MetaBean(retObj.getId(),
							retObj.getOFSServer__Name__c(), retObj.getOFSServer__Type__c(),
							retObj.getOFSServer__SourceOrganizationId__c(),
							retObj.getOFSServer__OrganizationId__c(), new Double(retObj.getOFSServer__Order__c()), packageName);
					beanList.add(metaBean);
					System.out.println(" --------------: ");
				}
			} else {
				System.out.println(" Deploymetadata - There are no records size is - : "
						+ queryResults.getSize());
				throw new SFException("There are no records to be deployed",
						SFErrorCodes.SFMetadataDescription_Query_Error);
			}
		} catch (Exception e) {
			throw new SFException(e.toString(),
					SFErrorCodes.SFMetadataDescription_Query_Error);
		}
		return beanList;
	}

	@Override
	public boolean commit(SObject[] sobjects, SFoAuthHandle sfHandle) {
		// TODO Auto-generated method stub
		return false;
	}

}
