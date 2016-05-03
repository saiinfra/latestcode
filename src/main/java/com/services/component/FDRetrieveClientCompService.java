package com.services.component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.domain.EnvironmentDO;
import com.domain.EnvironmentInformationDO;
import com.domain.MetaBean;
import com.domain.MetadataDescriptionDO;
import com.domain.MetadataDescriptionInformationDO;
import com.domain.MetadataLogInformationDO;
import com.ds.salesforce.dao.comp.MetadataDescriptionDAO;
import com.ds.salesforce.dao.comp.MetadataDescriptionInformationDAO;
import com.exception.SFErrorCodes;
import com.exception.SFException;
import com.services.application.RDAppService;
import com.tasks.PreProcessingTask;
import com.util.Constants;
import com.util.CsvFileWriter1;
import com.util.SFoAuthHandle;
import com.util.oauth.RefreshTokens;

public class FDRetrieveClientCompService {

	public FDRetrieveClientCompService() {
		super();
	}

	public void retrieve(String bOrgId, String bOrgToken, String bOrgURL,
			String refreshToken, String orgType, String metadataLogId) {

		MetadataLogInformationDO metadataLogInformationDO = null;
		FDGetSFoAuthHandleService fdGetSFoAuthHandleService = new FDGetSFoAuthHandleService();

		// do pre-processing
		// does some sanity checks on input variables
		// updates the refreshed tokens in Environment
		PreProcessingTask preProcessingTask = new PreProcessingTask(bOrgId,
				bOrgToken, bOrgURL, refreshToken, orgType, metadataLogId);
		bOrgToken = preProcessingTask.doPreProcess();
		// get refreshed base token

		try {
			// Get Meta data Log details
			metadataLogInformationDO = RDAppService.findMetadataLogInformation(
					metadataLogId, fdGetSFoAuthHandleService.getSFoAuthHandle(
							bOrgId, bOrgToken, bOrgURL, refreshToken,
							Constants.CustomBaseOrgID));
			// nullify connection
			fdGetSFoAuthHandleService.setSfHandleToNUll();

			// updating metadataLog to processing state
			RDAppService.updateMetadataLogInformationStatus(
					metadataLogInformationDO, Constants.PROCESSING_STATUS,
					fdGetSFoAuthHandleService.getSFoAuthHandle(bOrgId,
							bOrgToken, bOrgURL, refreshToken,
							Constants.CustomBaseOrgID));
			// nullify connection
			fdGetSFoAuthHandleService.setSfHandleToNUll();

			if (metadataLogInformationDO.getAction() != null
					&& (metadataLogInformationDO.getAction().equals("Retrieve"))) {
				if (metadataLogInformationDO.getStatus() != null
						&& (metadataLogInformationDO.getStatus()
								.equals(Constants.PROCESSING_STATUS))) {
					System.out.println("Retrieve------");
					// refresh connection
					fdGetSFoAuthHandleService.setSfHandleToNUll();
					RefreshTokens refreshTokens1 = new RefreshTokens();
					// getting Token
					String newSToken = refreshTokens1.refreshSFHandle(bOrgId,
							bOrgToken, bOrgURL, Constants.CustomBaseOrgID,
							refreshToken);
					refreshTokens1.getoAuthToken();

					// Creating EnvironmentDO Object
					EnvironmentInformationDO envSoureDO = RDAppService.getEnv1(
							metadataLogInformationDO.getSourceOrgId(),
							fdGetSFoAuthHandleService.getSFoAuthHandle(bOrgId,
									bOrgToken, bOrgURL, refreshToken,
									Constants.CustomBaseOrgID));
					String newSToken1 = refreshTokens1
							.refreshClientCustomSFHandle(envSoureDO, bOrgId,
									bOrgToken, bOrgURL, refreshToken);
					envSoureDO.setToken(newSToken1);

					// refresh connection
					fdGetSFoAuthHandleService.setSfHandleToNUll();
					List<MetaBean> mainMBList = getRetrieveObjListFromSource(
							metadataLogInformationDO.getLogName(),
							fdGetSFoAuthHandleService.getSFoAuthHandle(
									envSoureDO, Constants.CustomBaseOrgID));

					MetadataDescriptionInformationDAO metadataDescriptionInformationDAO = new MetadataDescriptionInformationDAO();
					List<Object> records = metadataDescriptionInformationDAO
							.findById1(metadataLogInformationDO.getId(),
									fdGetSFoAuthHandleService.getSFoAuthHandle(
											bOrgId, bOrgToken, bOrgURL,
											refreshToken,
											Constants.CustomBaseOrgID),
									envSoureDO.getOrgId());

					String[] ids = new String[1];
					if (records.size() > 0) {
						for (Iterator iterator = records.iterator(); iterator
								.hasNext();) {
							MetadataDescriptionInformationDO packageDO = (MetadataDescriptionInformationDO) iterator
									.next();
							ids[0] = packageDO.getId();

							// releasePackageDAO.delete(obj, sfHandle)
							metadataDescriptionInformationDAO.deleteRecords(
									ids, fdGetSFoAuthHandleService
											.getSFoAuthHandle(bOrgId,
													bOrgToken, bOrgURL,
													refreshToken,
													Constants.CustomBaseOrgID));
						}

					}

					// Do bulk inserts in Base Environment Organisation.
					doBulkInserts(mainMBList, bOrgId, bOrgToken, bOrgURL,
							refreshToken);

					// Update Success message
					fdGetSFoAuthHandleService.setSfHandleToNUll();
					// updating metadataLog
					RDAppService.updateMetadataLogInformationStatus(
							metadataLogInformationDO,
							Constants.COMPLETED_STATUS,
							fdGetSFoAuthHandleService.getSFoAuthHandle(bOrgId,
									bOrgToken, bOrgURL, refreshToken,
									Constants.CustomBaseOrgID));
					RDAppService.updateDeploymentDetailsInformation(
							metadataLogId, Constants.RETRIEVE_SUCESS_MESSAGE,
							metadataLogInformationDO.getSourceOrgId(),
							fdGetSFoAuthHandleService.getSFoAuthHandle(bOrgId,
									bOrgToken, bOrgURL, refreshToken,
									Constants.CustomBaseOrgID));
					// nullify connection
					fdGetSFoAuthHandleService.setSfHandleToNUll();
				} else {
					// Sleep for few seconds to let status updated to
					// "Processing"
					Thread.sleep(Constants.waitFor2Sec);
				}
			}
		} catch (SFException e) {
			if (metadataLogInformationDO != null) {
				// refresh connection
				fdGetSFoAuthHandleService.setSfHandleToNUll();
				// updating metadataLog
				RDAppService.updateMetadataLogInformationStatus(
						metadataLogInformationDO, Constants.ERROR_STATUS,
						fdGetSFoAuthHandleService.getSFoAuthHandle(bOrgId,
								bOrgToken, bOrgURL, refreshToken,
								Constants.CustomBaseOrgID));

				// refresh connection
				fdGetSFoAuthHandleService.setSfHandleToNUll();
				// updating Deploy Details Information
				RDAppService.updateDeploymentDetailsInformation(metadataLogId,
						e.getMessage(), metadataLogInformationDO
								.getSourceOrgId(),
						fdGetSFoAuthHandleService.getSFoAuthHandle(bOrgId,
								bOrgToken, bOrgURL, refreshToken,
								Constants.CustomBaseOrgID));
				// refresh connection
				fdGetSFoAuthHandleService.setSfHandleToNUll();
			} else {
				System.out.println("Salesforce Exception " + e.getMessage());
			}
		} catch (Exception e) {
			if (metadataLogInformationDO != null) {
				// refresh connection
				fdGetSFoAuthHandleService.setSfHandleToNUll();
				// updating metadataLog
				RDAppService.updateMetadataLogInformationStatus(
						metadataLogInformationDO, Constants.ERROR_STATUS,
						fdGetSFoAuthHandleService.getSFoAuthHandle(bOrgId,
								bOrgToken, bOrgURL, refreshToken,
								Constants.CustomBaseOrgID));

				// refresh connection
				fdGetSFoAuthHandleService.setSfHandleToNUll();
				// updating Deploy Details Information
				RDAppService.updateDeploymentDetailsInformation(metadataLogId,
						e.getMessage(), metadataLogInformationDO
								.getSourceOrgId(),
						fdGetSFoAuthHandleService.getSFoAuthHandle(bOrgId,
								bOrgToken, bOrgURL, refreshToken,
								Constants.CustomBaseOrgID));
				// refresh connection
				fdGetSFoAuthHandleService.setSfHandleToNUll();
			} else {
				System.out.println("Salesforce Exception " + e.getMessage());
			}
		}
	}

	private List<MetaBean> getRetrieveObjListFromSource(String logName,
			SFoAuthHandle sfHandle) {
		SFoAuthHandle sfSourceHandle = null;
		List<MetaBean> mainMBList = new ArrayList<MetaBean>();
		FDGetSFoAuthHandleService fdGetSFoAuthHandleService = new FDGetSFoAuthHandleService();

		try {
			for (int k = 0; k < Constants.SFTypes.length; k++) {
				String contentType = Constants.SFTypes[k];
				// getting list of objects from source
				FDGetComponentsTypesCompService getAllComponents = new FDGetComponentsTypesCompService();
				// refresh connection
				fdGetSFoAuthHandleService.setSfHandleToNUll();
				List<MetaBean> metaBeanList = getAllComponents
						.listMetadataObjects(logName, contentType, sfHandle);
				System.out.println("record size of " + contentType + " is : "
						+ metaBeanList.size());
				mainMBList.addAll(metaBeanList);
				if (sfSourceHandle != null) {
					sfSourceHandle.nullify();
				}
				sfSourceHandle = null;
				fdGetSFoAuthHandleService.setSfHandleToNUll();
			}
			System.out.println("Total record size of all contenttypes is : "
					+ mainMBList.size());
		} catch (Exception e) {
			if (sfSourceHandle != null) {
				sfSourceHandle.nullify();
			}
			sfSourceHandle = null;
			fdGetSFoAuthHandleService.setSfHandleToNUll();
			throw new SFException(e.toString(),
					SFErrorCodes.SF_ListObject_Error);
		}
		return mainMBList;
	}

	private void doBulkInserts(List<MetaBean> mainMBList, String bOrgId,
			String bOrgToken, String bOrgURL, String refreshToken) {
		int chunkCount = 0, rem = 0, start = 0, end = Constants.ChunkSize;
		FDGetSFoAuthHandleService fdGetSFoAuthHandleService = new FDGetSFoAuthHandleService();

		if (mainMBList.size() > Constants.ChunkSize) {
			rem = (mainMBList.size() % Constants.ChunkSize);
			if ((mainMBList.size() % Constants.ChunkSize) > 0) {
				chunkCount = mainMBList.size() / Constants.ChunkSize + 1;
			} else {
				chunkCount = mainMBList.size() / Constants.ChunkSize;
			}
		} else {
			chunkCount = 1;
			end = mainMBList.size();
		}
		System.out.println(chunkCount);

		// updating records
		for (int i = 0; i < chunkCount; i++) {
			System.out.println("Record Update start: " + start + " ~ end: "
					+ end);
			System.out.println("Updating " + (i + 1) + " set of "
					+ Constants.ChunkSize + " records out of total "
					+ mainMBList.size() + " records" + " with start : " + start
					+ " end :" + end);
			List<MetaBean> l = mainMBList.subList(start, end);
			CsvFileWriter1.writeCsvFile(l, Constants.Retrieve_CSV_File);
			BulkInsertService bulkService = new BulkInsertService();
			bulkService.insertInto(
					Constants.MetadataDescriptionInformation_Name,
					Constants.Retrieve_CSV_File, fdGetSFoAuthHandleService
							.getSFoAuthHandle(bOrgId, bOrgToken, bOrgURL,
									refreshToken, Constants.CustomBaseOrgID));
			if ((mainMBList.size() - end) > Constants.ChunkSize) {
				start = end;
				end = start + (Constants.ChunkSize);
			} else {
				start = end;
				end = start + rem;
			}
			fdGetSFoAuthHandleService.setSfHandleToNUll();
		}
	}
}
