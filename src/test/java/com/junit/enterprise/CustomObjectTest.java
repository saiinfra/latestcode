package com.junit.enterprise;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ds.salesforce.dao.comp.CustomSettingDAO;
import com.exception.SFErrorCodes;
import com.exception.SFException;
import com.services.component.FDGetSFoAuthHandleService;
import com.sforce.soap.enterprise.DescribeGlobalResult;
import com.sforce.soap.enterprise.DescribeGlobalSObjectResult;
import com.sforce.soap.enterprise.DescribeSObjectResult;
import com.sforce.soap.enterprise.Field;
import com.sforce.soap.enterprise.SaveResult;
import com.sforce.soap.enterprise.sobject.Folder;
import com.sforce.soap.enterprise.sobject.SObject;
import com.sforce.soap.metadata.FileProperties;
import com.sforce.soap.metadata.ListMetadataQuery;
import com.sforce.ws.ConnectionException;
import com.util.Constants;
import com.util.SFoAuthHandle;

/**
 * 
 * @author CustomObjectTest is JUnit TestCase Used To create Sample Custom
 *         Objects CustomObj_
 * 
 *
 */
public class CustomObjectTest {
	SFoAuthHandle sfHandle = null;
	private static final Logger LOG = LoggerFactory
			.getLogger(CustomObjectTest.class);
	
	// String[] ObjecList = {
	// "com.sforce.soap.enterprise.sobject.OFSServer__MetadataDescription__c"};
	String[] ObjecList = { "OFSServer__DeploymentSetting__c" };

	

	public CustomObjectTest() {

		String orgId = "";
		orgId = "00D61000000YB04EAG";
		// orgId="00Do0000000dw2D";
		String token = "00D61000000YB04!ARMAQH1UB5sOpeUeCkVqtYr.SbZ4g92GlaK.QNJIP_bejeoq0lv89VhRJmPZHmhXYB7m0m77LBonkHd.VFegx2tP6P2oiTQS";
		String rtoken = "5Aep861tbt360sO1.vrCA2niEfjpVBi4p6yJyk8asYbgI1pFhiQGCydA7rjBc0grvXTpB32DQTMRcFeqd_cra0B";
		String url = "https://na34.salesforce.com";
		FDGetSFoAuthHandleService fdGetSFoAuthHandleService = new FDGetSFoAuthHandleService();

		sfHandle = fdGetSFoAuthHandleService.getSFoAuthHandle(orgId, token,
				url, rtoken, Constants.BaseOrgID);
		// listMetadata(sfHandle);
		// insert(sfHandle);
		long startTime = System.currentTimeMillis();
		LOG.info("start for connection");
		sfHandle = fdGetSFoAuthHandleService.getSFoAuthHandle(orgId, token,
				url, rtoken, Constants.BaseOrgID);
		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		LOG.info("Time difference for connection:" + totalTime / 1000
				+ " seconds");
		describeSFObjects(orgId, ObjecList, sfHandle);
	}

	public static void main(String[] args) throws Exception {
		CustomObjectTest sfi = new CustomObjectTest();
	}

	public boolean isObjectAvailable(String orgId, String objName,
			SFoAuthHandle sfHandle) {
		String[] listStr = listAllSFGlobalObjects(orgId, sfHandle);
		for (int i = 0; i < listStr.length; i++) {
			String string = listStr[i];
			if (string.equals(objName)) {
				System.out.println("Object " + objName + " is available in "
						+ orgId);
				return true;
			}
		}
		return false;
	}

	public String[] listAllSFGlobalObjects(String orgId, SFoAuthHandle sfHandle) {
		String[] objlist = null;
		try {
			// Environment env = new Environment();
			// Enviroment__c envObj = env.queryObject(orgId);
			DescribeGlobalResult describeGlobalResult = sfHandle
					.getEnterpriseConnection().describeGlobal();
			// Get the sObjects from the describe global result
			DescribeGlobalSObjectResult[] sobjectResults = describeGlobalResult
					.getSobjects();

			objlist = new String[sobjectResults.length];
			// Write the name of each sObject to the console
			for (int i = 0; i < sobjectResults.length; i++) {
				objlist[i] = sobjectResults[i].getName();

				System.out.println("Hello: " + sobjectResults[i]);

			}
		} catch (ConnectionException ce) {
			ce.printStackTrace();
		}
		return objlist;
	}

	public void listMetadata(SFoAuthHandle sfhandle) {
		try {

			ListMetadataQuery query = new ListMetadataQuery();

			// query.setFolder(null);
			double asOfVersion = 33.0;
			// Assuming that the SOAP binding has already been established.
			FileProperties[] lmr = sfHandle.getMetadataConnection()
					.listMetadata(new ListMetadataQuery[] { query },
							asOfVersion);
			if (lmr != null) {
				for (FileProperties n : lmr) {
					System.out
							.println("Component fullName: " + n.getFullName());
					System.out.println("Component type: " + n.getType());
				}
			}
		} catch (ConnectionException ce) {
			ce.printStackTrace();
		}
	}

	public <SObject> void describeSFObjects(String orgId, String[] objList,
			SFoAuthHandle sfHandle) {
		// TODO Auto-generated method stub
		try {
			// Call describeSObjectResults and pass it an array with
			// the names of the objects to describe.

			DescribeSObjectResult[] describeSObjectResults = sfHandle
					.getEnterpriseConnection().describeSObjects(objList);

			for (int i = 0; i < describeSObjectResults.length; i++) {
				DescribeSObjectResult desObj = describeSObjectResults[i];

				// Get the name of the sObject
				System.out.println(desObj.isCustomSetting());
				LOG.info("Custom Setting There or not  :"
						+ desObj.getCustomSetting());
				System.out.println("Custom Setting There or not  :"
						+ desObj.getCustomSetting());
				String objectName = desObj.getName();
				LOG.info("SObject Name  :" + objectName);
				System.out.println("sObject name: " + objectName);
				// For each described sObject, get the fields
				Field[] fields = desObj.getFields();
				List<String> fieldList = new ArrayList<String>();

				Map<String, List<String>> objFieldsMap = new HashMap<String, List<String>>();
				for (int j = 0; j < fields.length; j++) {
					if (fields[j].getName().endsWith("__c"))
						fieldList.add(fields[j].getName());
				}
				objFieldsMap.put(objectName, fieldList);

				CustomSettingDAO customSettingDAO = new CustomSettingDAO();

				List<Object> list = customSettingDAO.findByName(objFieldsMap,
						sfHandle);

			}
		}
		catch(Exception e)
		{
			//exception
		}
	}

	public boolean insert(String name, String type, String accessType,
			SFoAuthHandle sfHandle) {
		// create the records				CustomObjectTest cc=new CustomObjectTest();


		Folder[] record = new Folder[1];
		try {
			// Get the name of the sObject
			Folder a = new Folder();
			a.setType(type);
			a.setName(name);
			a.setDeveloperName(name);
			a.setAccessType("public");
			a.setIsReadonly(true);
			record[0] = a;
			commit(record, sfHandle);
		} catch (Exception ce) {
			throw new SFException(ce.toString(),
					SFErrorCodes.SFDeployDetails_Update_Error);
		}
		return true;
	}


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