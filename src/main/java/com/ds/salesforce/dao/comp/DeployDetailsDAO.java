package com.ds.salesforce.dao.comp;

import java.util.List;

import com.domain.ErrorLogBean;
import com.ds.salesforce.dao.ISFBaseDAO;
import com.exception.SFErrorCodes;
import com.exception.SFException;
import com.sforce.soap.enterprise.SaveResult;
import com.sforce.soap.enterprise.sobject.OFSServer__DeploymentDetails__c;
import com.sforce.soap.enterprise.sobject.SObject;
import com.util.SFoAuthHandle;

/**
 * 
 * @author DeployDetailsDAO is Used For Performing CRUD Operations for
 *         {@link DeploymentDetails__c}
 *
 */

public class DeployDetailsDAO implements ISFBaseDAO {

	public DeployDetailsDAO() {
		super();

	}

	@Override
	public List<Object> listAll(SFoAuthHandle sfHandle) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insert(Object obj, SFoAuthHandle sfHandle) {
		// create the records
		ErrorLogBean errorLogBean = (ErrorLogBean) obj;

		OFSServer__DeploymentDetails__c[] record = new OFSServer__DeploymentDetails__c[1];
		try {
			// Get the name of the sObject
			OFSServer__DeploymentDetails__c a = new OFSServer__DeploymentDetails__c();
			a.setOFSServer__MetadataLog__c(errorLogBean.getMetadataLogId());
			a.setOFSServer__Error_Message__c(errorLogBean.getErrorMessage());
			a.setOFSServer__Name__c(errorLogBean.getName() + " for Org: "
					+ errorLogBean.getOrgId());
			a.setOFSServer__Type__c(errorLogBean.getType());
			record[0] = a;
			commit(record, sfHandle);
		} catch (Exception ce) {
			throw new SFException(ce.toString(),
					SFErrorCodes.SFDeployDetails_Update_Error);
		}
		return true;
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
	public List<Object> findById(String orgId, SFoAuthHandle sfHandle) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean commit(SObject[] sobjects, SFoAuthHandle sfHandle) {
		// System.out.println("SAVE--"+sfHandle.getUserId()+"--"+sfHandle.getPasswd());
		try { 
			com.sforce.soap.enterprise.SaveResult[] saveResults = sfHandle
					.getEnterpriseConnection().create(sobjects);

			for (SaveResult r : saveResults) {
				if (r.isSuccess()) {
					System.out.println("Created DeployDetails record - Id: "
							+ r.getId());
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
			throw new SFException(e.toString(),
					SFErrorCodes.SFDeployDetails_Update_Error);
		}
		return true;
	}
}
