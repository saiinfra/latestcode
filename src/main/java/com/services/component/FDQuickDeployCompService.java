package com.services.component;

import java.util.List;

import com.domain.MetaBean;
import com.domain.MetadataLogDO;
import com.ds.salesforce.dao.comp.DeployMetadataDAO;
import com.exception.SFErrorCodes;
import com.exception.SFException;
import com.services.application.RDAppService;
import com.tasks.PreProcessingTask;
import com.util.Constants;
import com.util.FileBasedQuickDeploy;
import com.util.SFoAuthHandle;

/**
 * 
 * @author FDDeployCompService used to Process Deploy Request
 *
 */
public class FDQuickDeployCompService {

	public FDQuickDeployCompService() {
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
	public void deployRecentValidation(String bOrgId, String bOrgToken,
			String bOrgURL, String refreshToken, String metadataLogId,
			boolean isValidate, String validationId) {
		MetadataLogDO metadataLogDO = null;
		SFoAuthHandle sfSourceHandle = null;
		SFoAuthHandle sfTargetHandle = null;
		FDGetSFoAuthHandleService fdGetSFoAuthHandleService = new FDGetSFoAuthHandleService();

		// do pre-processing
		// does some sanity checks on input variables
		// updates the refreshed tokens in Environment
		PreProcessingTask preProcessingTask = new PreProcessingTask(bOrgId,
				bOrgToken, bOrgURL, refreshToken, Constants.BaseOrgID,
				metadataLogId);
		bOrgToken = preProcessingTask.doPreProcess();
		// get refreshed base token
		String packageName = "";

		try {
			// Get Meta data Log details
			metadataLogDO = RDAppService.findMetadataLog(metadataLogId,
					fdGetSFoAuthHandleService.getSFoAuthHandle(bOrgId,
							bOrgToken, bOrgURL, refreshToken,
							Constants.BaseOrgID));
			// nullify connection
			fdGetSFoAuthHandleService.setSfHandleToNUll();

			// updating metadataLog to prcessing state
			RDAppService.updateMetadataLogStatus(metadataLogDO,
					Constants.PROCESSING_STATUS, fdGetSFoAuthHandleService
							.getSFoAuthHandle(bOrgId, bOrgToken, bOrgURL,
									refreshToken, Constants.BaseOrgID));
			// nullify connection
			fdGetSFoAuthHandleService.setSfHandleToNUll();
			String msg = "";

			if ((metadataLogDO.getAction() != null)
					&& ((metadataLogDO.getAction().equals("Deploy"))
							|| (metadataLogDO.getAction().equals("DeployAll"))
							|| (metadataLogDO.getAction().equals("ValidateAll"))
							|| (metadataLogDO.getAction().equals("QuickDeploy")) || (metadataLogDO
								.getAction().equals("Validate")))) {
				if (metadataLogDO.getStatus() != null
						&& (metadataLogDO.getStatus()
								.equals(Constants.PROCESSING_STATUS))) {
					System.out.println("Action" + metadataLogDO.getAction());
					DeployMetadataDAO deployMetadataDAO = new DeployMetadataDAO();

					// find to be deployed object list
					List<Object> deployList = deployMetadataDAO.findById(
							metadataLogDO.getLogName(),
							fdGetSFoAuthHandleService.getSFoAuthHandle(bOrgId,
									bOrgToken, bOrgURL, refreshToken,
									Constants.BaseOrgID));
					fdGetSFoAuthHandleService.setSfHandleToNUll();

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

					try {

						deployObjToTargetOrg(bOrgId, bOrgToken, bOrgURL,
								refreshToken, sfSourceHandle, sfTargetHandle,
								packageName, isValidate, metadataLogDO,
								validationId);

						Thread.sleep(Constants.waitFor1Sec);
					} catch (Exception e) {
						msg = e.getMessage();
						RDAppService.updateMetadataLogStatus(metadataLogDO,
								Constants.COMPLETED_STATUS,
								fdGetSFoAuthHandleService.getSFoAuthHandle(
										bOrgId, bOrgToken, bOrgURL,
										refreshToken, Constants.BaseOrgID));
					} finally {
						// String packgNames =
						// metadataLogDO.getNoOfPackgsByOrderMap().get(orderKey);
						System.out.println("package Names: " + packageName);
						RDAppService.updateDeploymentDetails(metadataLogId, msg
								+ " for package: " + packageName, metadataLogDO
								.getSourceOrgId(), fdGetSFoAuthHandleService
								.getSFoAuthHandle(bOrgId, bOrgToken, bOrgURL,
										refreshToken, Constants.BaseOrgID));
					}
				}

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

	/**
	 * 
	 * @param bOrgId
	 * @param bOrgToken
	 * @param bOrgURL
	 * @param refreshToken
	 * @param sfSourceHandle
	 * @param sfTargetHandle
	 * @param packageName
	 * @param isValidate
	 * @param metadataLogDO
	 * @param validationId
	 * @throws SFException
	 */
	private void deployObjToTargetOrg(String bOrgId, String bOrgToken,
			String bOrgURL, String refreshToken, SFoAuthHandle sfSourceHandle,
			SFoAuthHandle sfTargetHandle, String packageName,
			boolean isValidate, MetadataLogDO metadataLogDO, String validationId)
			throws SFException {

		try {
			(new FileBasedQuickDeploy()).deployRecentValidation(bOrgId,
					bOrgToken, bOrgURL, refreshToken, sfTargetHandle,
					packageName, isValidate, metadataLogDO, validationId);
		} catch (Exception e) {
			// e.printStackTrace();
			// System.out.println(e.toString());
			throw new SFException(e.toString(), SFErrorCodes.FileDeploy_Error);
		}
	}
}
