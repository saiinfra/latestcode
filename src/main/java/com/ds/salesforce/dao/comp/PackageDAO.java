package com.ds.salesforce.dao.comp;

import java.util.ArrayList;
import java.util.List;

import com.domain.PackageDO;
import com.ds.salesforce.dao.ISFBaseDAO;
import com.exception.SFErrorCodes;
import com.exception.SFException;
import com.services.component.FDGetSFoAuthHandleService;
import com.sforce.soap.enterprise.EnterpriseConnection;
import com.sforce.soap.enterprise.QueryResult;
import com.sforce.soap.enterprise.SaveResult;
import com.sforce.soap.enterprise.sobject.OFSServer__Package__c;
import com.sforce.soap.enterprise.sobject.SObject;
import com.util.Constants;
import com.util.Org;
import com.util.SFoAuthHandle;
import com.util.sql.PackageSQLStmts;

/**
 * 
 * @author PackageDAO is Used For Performing CRUD Operations for
 *         {@link Package__c}
 *
 */
public class PackageDAO implements ISFBaseDAO {

	public PackageDAO() {
		super();
	}

	public static void main(String[] args){
		PackageDAO dao = new PackageDAO();
		
		 
         String baseOrgId ="00D610000006tjPEAQ";
         String baseOrgRefreshToken="5Aep861tbt360sO1.uO0UjNoRyP9rNbAguo__QeBtE9I0DtmCAWn8r4UIu4tzqbg2okzwzEmBzokQe8gXPTDFXb";
         String baseOrgToken="00D610000006tjP!AQ4AQLpMKFRZYlHFja7spuak1Eq_C4DoCZVc8qrx8L1ry.5B1LSIG_40GXjIqGyY_JCihNpeFT8JVdFvkEodeROS1PcgFRGD";
         String baseOrgUrl ="https://na34.salesforce.com";
		Org borg = new Org(baseOrgId, baseOrgToken, baseOrgUrl,
				baseOrgRefreshToken, Constants.BaseOrgID);
		FDGetSFoAuthHandleService fdGetSFoAuthHandleService = new FDGetSFoAuthHandleService();

		dao.findById("a0761000001GCp1", fdGetSFoAuthHandleService
				.getSFoAuthHandle(borg));
		
	}
	@Override
	public List<Object> listAll(SFoAuthHandle sfHandle) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insert(Object obj, SFoAuthHandle sfHandle) {
		// create the records
		System.out.println("inserting PackageDo :");
		PackageDO pkgDO = (PackageDO) obj;
		OFSServer__Package__c[] record = new OFSServer__Package__c[1];
		try {
			// Get the name of the sObject
			OFSServer__Package__c a = new OFSServer__Package__c();
			//a.setId(relDO.getId());
			a.setName(pkgDO.getName());
			a.setOFSServer__Description__c(pkgDO.getDescription());
			record[0] = a;
			String pid=commit1(record, sfHandle);
			System.out.println(pid);
			pkgDO.setId(pid);
		} catch (Exception ce) {
			throw new SFException(ce.toString(),
					SFErrorCodes.SFEnvironment_Update_Error);
		}
		return true;
	}
	
	
	public String insertAndGetId(Object obj, SFoAuthHandle sfHandle) {
		// create the records
		System.out.println("inserting PackageDo :");
		String pid="";
		PackageDO pkgDO = (PackageDO) obj;
		OFSServer__Package__c[] record = new OFSServer__Package__c[1];
		try {
			// Get the name of the sObject
			OFSServer__Package__c a = new OFSServer__Package__c();
			
			//a.setId(relDO.getId());
			a.setName(pkgDO.getName());
			a.setOFSServer__Description__c(pkgDO.getDescription());
			a.setOFSServer__ParentPackageID__c(pkgDO.getParentPackageId());
			record[0] = a;
			pid=commit1(record, sfHandle);
			System.out.println(pid);
			pkgDO.setId(pid);
		} catch (Exception ce) {
			throw new SFException(ce.toString(),
					SFErrorCodes.SFEnvironment_Update_Error);
		}
		return pid;
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

	
	public boolean deleteRecords(String[] ids, SFoAuthHandle sfHandle) {
		   try {
			   com.sforce.soap.enterprise.DeleteResult[] deleteResults = sfHandle.getEnterpriseConnection().delete(ids);
		      for (int i = 0; i < deleteResults.length; i++) {
		    	  com.sforce.soap.enterprise. DeleteResult deleteResult = deleteResults[i];
		         if (deleteResult.isSuccess()) {
		            System.out
		                  .println("Deleted Record ID: " + deleteResult.getId());
		        
		         }
		      }
		   } catch (Exception ce) {
		      ce.printStackTrace();
		   }
		   return true;
		}

	
	@Override
	public List<Object> findById(String id, SFoAuthHandle sfHandle) {
		com.sforce.soap.enterprise.sobject.OFSServer__Package__c package__c = null;
		List<Object> list = new ArrayList<Object>();
		try {
			EnterpriseConnection conn = sfHandle.getEnterpriseConnection();
			System.out.println("SQl: "+PackageSQLStmts
					.getPackageQuery(id));
			QueryResult queryResults = conn.query(PackageSQLStmts
					.getPackageQuery(id));
			if (queryResults.getSize() > 0) {
				PackageDO packageDO = null;
				for (int i = 0; i < queryResults.getRecords().length; i++) {
					package__c = (com.sforce.soap.enterprise.sobject.OFSServer__Package__c) queryResults
							.getRecords()[i];

					packageDO = new PackageDO(package__c.getId(),
							package__c.getName(),package__c.getOFSServer__Description__c(),package__c.getOFSServer__ParentPackageID__c());
					System.out.println(packageDO.toString());
					list.add(packageDO);
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
	
	public List<Object> findByParentId(String id, SFoAuthHandle sfHandle) {
		com.sforce.soap.enterprise.sobject.OFSServer__Package__c package__c = null;
		List<Object> list = new ArrayList<Object>();
		try {
			EnterpriseConnection conn = sfHandle.getEnterpriseConnection();
			QueryResult queryResults = conn.query(PackageSQLStmts
					.getPackageParent(id));
			if (queryResults.getSize() > 0) {
				PackageDO packageDO = null;
				for (int i = 0; i < queryResults.getRecords().length; i++) {
					package__c = (com.sforce.soap.enterprise.sobject.OFSServer__Package__c) queryResults
							.getRecords()[i];

					packageDO = new PackageDO(package__c.getId(),
							package__c.getName(),package__c.getOFSServer__Description__c(),package__c.getOFSServer__ParentPackageID__c());
					System.out.println(packageDO.toString());
					list.add(packageDO);
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
	
	
	public List<Object> findByNameId(String name, SFoAuthHandle sfHandle) {
		com.sforce.soap.enterprise.sobject.OFSServer__Package__c package__c = null;
		List<Object> list = new ArrayList<Object>();
		try {
			EnterpriseConnection conn = sfHandle.getEnterpriseConnection();
			QueryResult queryResults = conn.query(PackageSQLStmts
					.getPackageNameQuery(name));
			if (queryResults.getSize() > 0) {
				PackageDO packageDO = null;
				for (int i = 0; i < queryResults.getRecords().length; i++) {
					package__c = (com.sforce.soap.enterprise.sobject.OFSServer__Package__c) queryResults
							.getRecords()[i];

					packageDO = new PackageDO(package__c.getId(),
							package__c.getName(), package__c.getOFSServer__Description__c(),package__c.getOFSServer__ParentPackageID__c());
					System.out.println(packageDO.toString());
					list.add(packageDO);
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
		// System.out.println("SAVE--"+sfHandle.getUserId()+"--"+sfHandle.getPasswd());
		try {
			com.sforce.soap.enterprise.SaveResult[] saveResults = sfHandle
					.getEnterpriseConnection().create(sobjects);
			
			for (SaveResult r : saveResults) {
				if (r.isSuccess()) {
					System.out.println("Created Package record - Id: " + r.getId());
				
				} else {
					for (com.sforce.soap.enterprise.Error e : r.getErrors()) {
						throw new SFException(e.getMessage() + "-status code-"
								+ e.getStatusCode(),
								SFErrorCodes.SFDeployDetails_Update_Error);
					}
					return false;
				}
			}
		} catch (Exception e) {
			throw new SFException(e.toString(), SFErrorCodes.SFDeployDetails_Update_Error);
		}
		return true;
	}
	
	public String commit1(SObject[] sobjects, SFoAuthHandle sfHandle) {
		String id="";
		// System.out.println("SAVE--"+sfHandle.getUserId()+"--"+sfHandle.getPasswd());
		try {
			com.sforce.soap.enterprise.SaveResult[] saveResults = sfHandle
					.getEnterpriseConnection().create(sobjects);
			
			for (SaveResult r : saveResults) {
				if (r.isSuccess()) {
					System.out.println("Created Package record - Id: " + r.getId());
				  id=r.getId();
				} else {
					for (com.sforce.soap.enterprise.Error e : r.getErrors()) {
						throw new SFException(e.getMessage() + "-status code-"
								+ e.getStatusCode(),
								SFErrorCodes.SFDeployDetails_Update_Error);
					}
					return r.getId();
				}
			}
		} catch (Exception e) {
			throw new SFException(e.toString(), SFErrorCodes.SFDeployDetails_Update_Error);
		}
		return id;
	}
}
