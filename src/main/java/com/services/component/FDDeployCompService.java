package com.services.component;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;

import com.domain.MetaBean;
import com.domain.MetadataLogDO;
import com.ds.salesforce.dao.comp.DeployMetadataDAO;
import com.exception.SFErrorCodes;
import com.exception.SFException;
import com.services.application.RDAppService;
import com.sforce.soap.enterprise.QueryResult;
import com.tasks.PreProcessingTask;
import com.util.Constants;
import com.util.FileBasedDeploy;
import com.util.FileBasedRetrieve;
import com.util.SFoAuthHandle;
import com.util.app.DeployList;
import com.util.sql.FolderSQLStmts;

/**
 * 
 * @author FDDeployCompService used to Process Deploy Request
 *
 */
public class FDDeployCompService {

	public FDDeployCompService() {
		super();
	}

	/**
	 * 
	 * @param bOrgId
	 * @param bOrgToken
	 * @param bOrgURL
	 * @param refreshToken
	 * @param metadataLogId
	 * @param isValidate
	 */
	public void deploy(String bOrgId, String bOrgToken, String bOrgURL,
			String refreshToken, String metadataLogId, boolean isValidate) {
		FDGetSFoAuthHandleService fdGetSFoAuthHandleService = new FDGetSFoAuthHandleService();

		MetadataLogDO metadataLogDO = null;
		SFoAuthHandle sfSourceHandle = null;
		SFoAuthHandle sfTargetHandle = null;
		String templateType = "";
		String ObjectName = "";

		// do pre-processing
		// does some sanity checks on input variables
		// updates the refreshed tokens in Environment
		PreProcessingTask preProcessingTask = new PreProcessingTask(bOrgId,
				bOrgToken, bOrgURL, refreshToken, Constants.BaseOrgID,
				metadataLogId);
		bOrgToken = preProcessingTask.doPreProcess();

		System.out.println("BaseOrg Token New" + bOrgToken);
		String packageName = "";
		String action = null;

		try {
			// Get Meta data Log details
			metadataLogDO = RDAppService.findMetadataLog(metadataLogId,
					fdGetSFoAuthHandleService.getSFoAuthHandle(bOrgId,
							bOrgToken, bOrgURL, refreshToken,
							Constants.BaseOrgID));

			// updating metadataLog to processing state
			RDAppService.updateMetadataLogStatus(metadataLogDO,
					Constants.PROCESSING_STATUS, fdGetSFoAuthHandleService
							.getSFoAuthHandle(bOrgId, bOrgToken, bOrgURL,
									refreshToken, Constants.BaseOrgID));

			if ((metadataLogDO.getAction() != null)
					&& ((metadataLogDO.getAction().equals("Deploy"))
							|| (metadataLogDO.getAction().equals("DeployAll"))
							|| (metadataLogDO.getAction().equals("ValidateAll")) || (metadataLogDO
								.getAction().equals("Validate")))) {

				if (metadataLogDO.getStatus() != null
						&& (metadataLogDO.getStatus()
								.equals(Constants.PROCESSING_STATUS))) {
					System.out.println("Action" + metadataLogDO.getAction());
					action = metadataLogDO.getAction();
					DeployMetadataDAO deployMetadataDAO = new DeployMetadataDAO();

					// find to be deployed object list
					List<Object> deployList = deployMetadataDAO.findById(
							metadataLogDO.getLogName(),
							fdGetSFoAuthHandleService.getSFoAuthHandle(bOrgId,
									bOrgToken, bOrgURL, refreshToken,
									Constants.BaseOrgID));

					fdGetSFoAuthHandleService.setSfHandleToNUll();
					System.out.println(((MetaBean) deployList.get(0))
							.getSourceOrg());
					// get source salesforce handle
					sfSourceHandle = fdGetSFoAuthHandleService
							.getSFoAuthHandle(((MetaBean) deployList.get(0))
									.getSourceOrg(), bOrgId, bOrgToken,
									bOrgURL, refreshToken,
									Constants.CustomOrgID);

					// get target salesforce handle
					sfTargetHandle = fdGetSFoAuthHandleService
							.getSFoAuthHandle(((MetaBean) deployList.get(0))
									.getTargetOrg(), bOrgId, bOrgToken,
									bOrgURL, refreshToken,
									Constants.CustomOrgID);

					System.out.println("OAuth Token Checking "
							+ sfTargetHandle.getoAuthToken());

					// gets the map by order
					LinkedHashMap<String, List<MetaBean>> deployMap = (new DeployList())
							.getDeployListByOrder2(deployList, action);

					Iterator<Map.Entry<String, List<MetaBean>>> entries = deployMap
							.entrySet().iterator();
					while (entries.hasNext()) {
						Entry<String, List<MetaBean>> thisEntry = (Entry<String, List<MetaBean>>) entries
								.next();
						Double orderKey = 0.0;
						String currPackg = "";
						// Double orderKey = (Double) thisEntry.getKey();
						String orderKeyStr = (String) thisEntry.getKey();
						StringTokenizer st = new StringTokenizer(orderKeyStr,
								"~");
						if (st.hasMoreTokens()) {
							String s1 = st.nextToken();
							System.out.println("OrderKey : " + s1);
							orderKey = new Double(s1);
						}
						if (st.hasMoreTokens()) {
							String s1 = st.nextToken();
							System.out.println("current Package Name: " + s1);
							packageName = s1;
						}

						System.out.println("cmpStr: " + orderKey);
						List<MetaBean> metabeanList = (List<MetaBean>) thisEntry
								.getValue();
						for (Iterator iterator = metabeanList.iterator(); iterator
								.hasNext();) {
							MetaBean metaBean = (MetaBean) iterator.next();
							// packageName =
							// metabeanList.get(0).getPackageName();
							templateType = metaBean.getType();
							ObjectName = metaBean.getName();

							// Reports/testreport

							if (templateType.equals("Report")
									|| templateType.equals("Dashboard")
									|| templateType.equals("EmailTemplate")) {

								if (templateType.equals("EmailTemplate")) {
									templateType = "Email";
								}
								String objName = processTemplate(ObjectName);

								// find whether any folder associate with that
								// type//
								processQuery(templateType, objName,
										sfSourceHandle, sfTargetHandle);

							}

						}

						String msg = "";
						try {

							FDSFXMLPackageCompService xmlService = new FDSFXMLPackageCompService();
							xmlService.createPackageXML(metabeanList,
									metadataLogId);
							deployObjToTargetOrg(bOrgId, bOrgToken, bOrgURL,
									refreshToken, sfSourceHandle,
									sfTargetHandle, packageName, isValidate,
									metadataLogDO);

							Thread.sleep(Constants.waitFor1Sec);
							msg = Constants.DEPLOY_SUCESS_MESSAGE;
							if (action.equals("Validate"))
								msg = Constants.VALIDATE_SUCESS_MESSAGE;

						} catch (Exception e) {
							msg = e.getMessage();
						} finally {
							// String packgNames =
							// metadataLogDO.getNoOfPackgsByOrderMap().get(orderKey);
							System.out.println("package Names: " + packageName);
							if (action.equals("DeployAll")) {
								msg = Constants.DEPLOY_SUCESS_MESSAGE
										+ " for All Packages";

								RDAppService.updateDeploymentDetails(
										metadataLogId, msg, metadataLogDO
												.getSourceOrgId(),
										fdGetSFoAuthHandleService
												.getSFoAuthHandle(bOrgId,
														bOrgToken, bOrgURL,
														refreshToken,
														Constants.BaseOrgID));
							}
							if (action.equals("ValidateAll")) {
								msg = Constants.VALIDATE_SUCESS_MESSAGE
										+ " for All Packages";
								RDAppService.updateDeploymentDetails(
										metadataLogId, msg, metadataLogDO
												.getSourceOrgId(),
										fdGetSFoAuthHandleService
												.getSFoAuthHandle(bOrgId,
														bOrgToken, bOrgURL,
														refreshToken,
														Constants.BaseOrgID));
							} if (action.equals("Validate") ||  (action.equals("Deploy"))) {
								
								
								RDAppService.updateDeploymentDetails(
										metadataLogId, msg + " for package: "
												+ packageName, metadataLogDO
												.getSourceOrgId(),
										fdGetSFoAuthHandleService
												.getSFoAuthHandle(bOrgId,
														bOrgToken, bOrgURL,
														refreshToken,
														Constants.BaseOrgID));
							}
						}
					}
					/*
					 * if (sfSourceHandle != null) { sfSourceHandle.nullify(); }
					 * sfSourceHandle = null; if (sfTargetHandle != null) {
					 * sfTargetHandle.nullify(); } sfTargetHandle = null;
					 */

					// Update MetadataLog status to Completed
					// fdGetSFoAuthHandleService.setSfHandleToNUll();
					RDAppService.updateMetadataLogStatus(metadataLogDO,
							Constants.COMPLETED_STATUS,
							fdGetSFoAuthHandleService.getSFoAuthHandle(bOrgId,
									bOrgToken, bOrgURL, refreshToken,
									Constants.BaseOrgID));

					// nullify connection
					// fdGetSFoAuthHandleService.setSfHandleToNUll();
				}
			} else {
				// Sleep for few sec to let status updated to "Processing"
				Thread.sleep(Constants.waitFor2Sec);
			}
		} catch (SFException e) {
			if (metadataLogDO != null) {

				// refresh connection
				fdGetSFoAuthHandleService.setSfHandleToNUll();
				// updating metadataLog
				RDAppService.updateMetadataLogStatus(metadataLogDO,
						Constants.ERROR_STATUS, fdGetSFoAuthHandleService
								.getSFoAuthHandle(bOrgId, bOrgToken, bOrgURL,
										refreshToken, Constants.BaseOrgID));
				// refresh connection
				fdGetSFoAuthHandleService.setSfHandleToNUll();
				// updating Deploy Details
				RDAppService.updateDeploymentDetails(metadataLogId, e
						.getMessage(), metadataLogDO.getSourceOrgId(),
						fdGetSFoAuthHandleService.getSFoAuthHandle(bOrgId,
								bOrgToken, bOrgURL, refreshToken,
								Constants.BaseOrgID));
				// refresh connection
				fdGetSFoAuthHandleService.setSfHandleToNUll();
			} else {
				System.out.println("Salesforce Exception " + e.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (metadataLogDO != null) {
				// refresh connection
				fdGetSFoAuthHandleService.setSfHandleToNUll();
				// updating metadataLog
				RDAppService.updateMetadataLogStatus(metadataLogDO,
						Constants.ERROR_STATUS, fdGetSFoAuthHandleService
								.getSFoAuthHandle(bOrgId, bOrgToken, bOrgURL,
										refreshToken, Constants.BaseOrgID));

				// refresh connection
				fdGetSFoAuthHandleService.setSfHandleToNUll();
				// updating Deploy Details
				RDAppService.updateDeploymentDetails(metadataLogId, e
						.getMessage(), metadataLogDO.getSourceOrgId(),
						fdGetSFoAuthHandleService.getSFoAuthHandle(bOrgId,
								bOrgToken, bOrgURL, refreshToken,
								Constants.BaseOrgID));
				// refresh connection
				fdGetSFoAuthHandleService.setSfHandleToNUll();
			} else {
				System.out.println("Salesforce Exception " + e.getMessage());
			}
		}
	}

	public static String processTemplate(String objName) {
		String foldername = "";
		StringTokenizer st1 = new StringTokenizer(objName, "/");
		if (st1.hasMoreTokens()) {
			foldername = st1.nextToken();
			System.out.println("Folder Name : " + foldername);

		}
		return foldername;

	}

	public static void processQuery(String templateType, String objName,
			SFoAuthHandle sfSourceHandle, SFoAuthHandle sfTargetHandle) {

		try {

			QueryResult queryResults = sfSourceHandle.getEnterpriseConnection()
					.query(FolderSQLStmts.getFolder(templateType, objName));

			if (queryResults.getSize() > 0) {
				for (int i = 0; i < queryResults.getRecords().length; i++) {

					com.sforce.soap.enterprise.sobject.Folder retObj = (com.sforce.soap.enterprise.sobject.Folder) queryResults
							.getRecords()[i];
					String name = retObj.getDeveloperName();
					String type = retObj.getType();
					System.out.println("folder Name :" + name);
					System.out.println("Type Name :" + type);
					boolean isReadonly = true;

					QueryResult queryResults1 = sfTargetHandle
							.getEnterpriseConnection().query(
									FolderSQLStmts.getFolder(templateType,
											objName));
					if (queryResults1.getSize() > 0) {

					} else {

						CustomObjectTest customObjectTest = new CustomObjectTest();
						customObjectTest.insert(name, type, sfTargetHandle);
					}

				}

			}
		} catch (Exception e) {

		}

	}

	private void deployObjToTargetOrg(String bOrgId, String bOrgToken,
			String bOrgURL, String refreshToken, SFoAuthHandle sfSourceHandle,
			SFoAuthHandle sfTargetHandle, String packageName,
			boolean isValidate, MetadataLogDO metadataLogDO) {

		System.out.println("OAUTH TOKEN in DeployObject"
				+ sfTargetHandle.getoAuthToken());

		FileBasedRetrieve retrieveObjectsFromSource = new FileBasedRetrieve();
		try {
			retrieveObjectsFromSource.retrieve(sfSourceHandle, packageName,
					metadataLogDO);
		} catch (Exception e) {
			System.out.println(e.toString());
			throw new SFException(e.toString(), SFErrorCodes.FileRetrieve_Error);
		}

		try {
			(new FileBasedDeploy()).deploy(bOrgId, bOrgToken, bOrgURL,
					refreshToken, sfTargetHandle, packageName, isValidate,
					metadataLogDO.getId());
		} catch (Exception e) { // e.printStackTrace(); //
			System.out.println(e.toString());
			throw new SFException(e.toString(), SFErrorCodes.FileDeploy_Error);
		}

	}
}
