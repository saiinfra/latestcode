package com.ds.salesforce.dao.comp;

import java.util.List;

import com.domain.ErrorLogBean;
import com.ds.salesforce.dao.ISFBaseDAO;
import com.exception.SFErrorCodes;
import com.exception.SFException;
import com.sforce.soap.enterprise.SaveResult;
import com.sforce.soap.enterprise.sobject.OFSClient__DeploymentDetailsInformation__c;
import com.sforce.soap.enterprise.sobject.SObject;
import com.util.SFoAuthHandle;

/**
 * 
 * @author DeployDetailsInformationDAO is Used For Performing CRUD Operations for
 *         {@link DeploymentDetailsInformation__c} Client Side
 *
 */

public class DeployDetailsInformationDAO implements ISFBaseDAO {

	public DeployDetailsInformationDAO() {
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

		OFSClient__DeploymentDetailsInformation__c[] record = new OFSClient__DeploymentDetailsInformation__c[1];
		try {
			// Get the name of the sObject
			OFSClient__DeploymentDetailsInformation__c a = new OFSClient__DeploymentDetailsInformation__c();
			a.setOFSClient__MetadataLog__c(errorLogBean.getMetadataLogId());
			a.setOFSClient__Error_Message__c(errorLogBean.getErrorMessage());
			a.setOFSClient__Name__c(errorLogBean.getName() + " for Org: "
					+ errorLogBean.getOrgId());
			a.setOFSClient__Type__c(errorLogBean.getType());
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
					System.out.println("Created DeployDetailsInformation record - Id: "
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
