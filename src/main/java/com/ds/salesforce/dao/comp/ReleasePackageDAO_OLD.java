package com.ds.salesforce.dao.comp;

import java.util.ArrayList;
import java.util.List;

import com.domain.PackageByOrderDO;
import com.ds.salesforce.dao.ISFBaseDAO;
import com.exception.SFErrorCodes;
import com.exception.SFException;
import com.sforce.soap.enterprise.QueryResult;
import com.sforce.soap.enterprise.sobject.SObject;
import com.util.SFoAuthHandle;
import com.util.sql.ReleasePackageSQLStmts;

public class ReleasePackageDAO_OLD implements ISFBaseDAO {

	public ReleasePackageDAO_OLD() {
		super();
		
	}

	public static void main(String[] args){
		ReleasePackageDAO_OLD dao = new ReleasePackageDAO_OLD();
		/*String bOrgId = "00D180000004nXJEAY"; 
		String bOrgToken = "00D180000004nXJ!AQYAQAxaCitXFncyihTse5LHXQDwNacXfPifn4tfJrZrfSwQElbUyk1X8Ys91y44MtSJZ5AyH.4r.0ZfBCB6tLS85YZ1PoWy"; 
		String bOrgURL = "https://emc--fix2.cs23.my.salesforce.com";
		String refreshToken = "5Aep861kyKookAQmYBUNWt7VdSOpvki7LPGfQbjLo6DPl8ZAmjmPRR0VDVxOecpDkb_9NkArNAVsokSiq7vYAfr";
		findById("a8V18000000Caao", FDGetSFoAuthHandleService.getSFoAuthHandle(bOrgId,
				bOrgToken, bOrgURL, refreshToken, true));*/
	}
	
	@Override
	public List<Object> listAll(SFoAuthHandle sfHandle) {
		// TODO Auto-generated method stub
		return null;
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
	public List<Object> findById(String id, SFoAuthHandle sfHandle) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		List<Object> beanList = null;
		com.sforce.soap.enterprise.sobject.OFSServer__ReleasePackage__c retObj = null;
		System.out.println("SQL: "
				+ ReleasePackageSQLStmts
						.getReleasePackageNameQuery(id));
		try {
			QueryResult queryResults = sfHandle.getEnterpriseConnection()
					.query(ReleasePackageSQLStmts
							.getReleasePackageNameQuery(id));
			if (queryResults.getSize() > 0) {
				beanList = new ArrayList<Object>();
				for (int i = 0; i < queryResults.getRecords().length; i++) {
					retObj = (com.sforce.soap.enterprise.sobject.OFSServer__ReleasePackage__c) queryResults
							.getRecords()[i];
					System.out.println(" - Name: " + retObj.getName());
					System.out.println(" - Order: " + retObj.getOFSServer__Order__c());
					System.out.println(" - Package: " + retObj.getOFSServer__Package__c());
					System.out.println(" - Relase: " + retObj.getOFSServer__Release__c());
					System.out.println(" --------------: ");
				}
			} else {
				System.out
						.println(" Deploymetadata - There are no records size is - : "
								+ queryResults.getSize());
				throw new SFException("There are no records to be deployed",
						SFErrorCodes.SFMetadataDescription_Query_Error);
			}
		} catch (Exception e) {
			throw new SFException(e.toString(),
					SFErrorCodes.SFMetadataDescription_Query_Error);
		}
		return null;
	}
	
	public List<Object> findByReleaseId(String id, SFoAuthHandle sfHandle) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		List<Object> list = new ArrayList<Object>();
		com.sforce.soap.enterprise.sobject.OFSServer__ReleasePackage__c retObj = null;
		System.out.println("SQL: "
				+ ReleasePackageSQLStmts
						.getReleaseQuery(id));
		try {
			QueryResult queryResults = sfHandle.getEnterpriseConnection()
					.query(ReleasePackageSQLStmts
							.getReleaseQuery(id));
			PackageByOrderDO oderbyPackageDO = new PackageByOrderDO();
			if (queryResults.getSize() > 0) {
				for (int i = 0; i < queryResults.getRecords().length; i++) {
					retObj = (com.sforce.soap.enterprise.sobject.OFSServer__ReleasePackage__c) queryResults
							.getRecords()[i];
					System.out.println(" - Name: " + retObj.getName());
					System.out.println(" - Order: " + retObj.getOFSServer__Order__c());
					System.out.println(" - Package: " + retObj.getOFSServer__Package__c());
					Double order = new Double(retObj.getOFSServer__Order__c());
					if(retObj.getOFSServer__Package__c() != null){
						PackageDAO pkgDAO = new PackageDAO();
						List<Object> packageDOList = pkgDAO.findById(retObj.getOFSServer__Package__c(), sfHandle);
						oderbyPackageDO.insert(order, packageDOList);
						
					}
					//System.out.println(" - Relase: " + retObj.getRelease__c());
					//System.out.println(" --------------: ");
				}
				list.add(oderbyPackageDO);
			} else {
				System.out
						.println(" Deploymetadata - There are no records size is - : "
								+ queryResults.getSize());
				throw new SFException("There are no records to be deployed",
						SFErrorCodes.SFMetadataDescription_Query_Error);
			}
		} catch (Exception e) {
			throw new SFException(e.toString(),
					SFErrorCodes.SFMetadataDescription_Query_Error);
		}
		return list;
	}

	@Override
	public boolean commit(SObject[] sobjects, SFoAuthHandle sfHandle) {
		// System.out.println("SAVE--"+sfHandle.getUserId()+"--"+sfHandle.getPasswd());
		return true;
	}

	@Override
	public boolean insert(Object obj, SFoAuthHandle sfHandle) {
		// TODO Auto-generated method stub
		return false;
	}
}
