package com.services.component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.domain.EnvironmentDO;
import com.domain.MetaBean;
import com.domain.MetadataDescriptionDO;
import com.domain.MetadataLogDO;
import com.domain.PackageDO;
import com.ds.salesforce.dao.comp.MetadataDescriptionDAO;
import com.exception.SFErrorCodes;
import com.exception.SFException;
import com.services.application.RDAppService;
import com.tasks.PreProcessingTask;
import com.util.Constants;
import com.util.CsvFileWriter;
import com.util.SFoAuthHandle;
import com.util.oauth.RefreshTokens;

public class FDRetrieveCompService {

	public FDRetrieveCompService() {
		super();
	}

	public void retrieve(String bOrgId, String bOrgToken, String bOrgURL,
			String refreshToken, String metadataLogId) {

		MetadataLogDO metadataLogDO = null;
		FDGetSFoAuthHandleService fdGetSFoAuthHandleService = new FDGetSFoAuthHandleService();

		// do pre-processing
		// does some sanity checks on input variables
		// updates the refreshed tokens in Environment
		PreProcessingTask preProcessingTask = new PreProcessingTask(bOrgId,
				bOrgToken, bOrgURL, refreshToken, Constants.BaseOrgID,
				metadataLogId);
		bOrgToken = preProcessingTask.doPreProcess();
		// get refreshed base token
		/*
		 * EnvironmentDO envDO = RDAppService.getEnv(bOrgId,
		 * FDGetSFoAuthHandleService.getSFoAuthHandle(bOrgId, bOrgToken,
		 * bOrgURL, refreshToken, true)); bOrgToken = envDO.getToken();
		 */

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

			if (metadataLogDO.getAction() != null
					&& (metadataLogDO.getAction().equals("Retrieve"))) {
				if (metadataLogDO.getStatus() != null
						&& (metadataLogDO.getStatus()
								.equals(Constants.PROCESSING_STATUS))) {
					System.out.println("Retrieve------");
					// refresh connection
					fdGetSFoAuthHandleService.setSfHandleToNUll();
					// get Source Env Details
					RDAppService rdAppService = new RDAppService();
					EnvironmentDO envSoureDO = rdAppService.getEnv(
							metadataLogDO.getSourceOrgId(),
							fdGetSFoAuthHandleService.getSFoAuthHandle(bOrgId,
									bOrgToken, bOrgURL, refreshToken,
									Constants.BaseOrgID));

					RefreshTokens refreshTokens1 = new RefreshTokens();
					String newSToken = refreshTokens1.refreshCustomSFHandle(
							envSoureDO, bOrgId, bOrgToken, bOrgURL,
							refreshToken);
					envSoureDO.setToken(newSToken);

					// refresh connection
					fdGetSFoAuthHandleService.setSfHandleToNUll();
					List<MetaBean> mainMBList = getRetrieveObjListFromSource(
							metadataLogDO.getLogName(),
							fdGetSFoAuthHandleService.getSFoAuthHandle(
									envSoureDO, Constants.CustomOrgID));

					System.out.println("source Organization Id "
							+ envSoureDO.getOrgId());

					// delete components from metadata description table

					MetadataDescriptionDAO metadataDescriptionDAO = new MetadataDescriptionDAO();
					List<Object> records=metadataDescriptionDAO
							.findById1(metadataLogDO.getId(),
									fdGetSFoAuthHandleService.getSFoAuthHandle(
											bOrgId, bOrgToken, bOrgURL,
											refreshToken, Constants.BaseOrgID),
									envSoureDO.getOrgId());
					
					String[] ids = new String[1];
					if (records.size() > 0) {
						for (Iterator iterator = records.iterator(); iterator.hasNext();) {
							MetadataDescriptionDO packageDO = (MetadataDescriptionDO) iterator.next();
							ids[0] = packageDO.getId();
							

                
                       

						// releasePackageDAO.delete(obj, sfHandle)
						metadataDescriptionDAO.deleteRecords(ids, fdGetSFoAuthHandleService.getSFoAuthHandle(
								bOrgId, bOrgToken, bOrgURL,
								refreshToken, Constants.BaseOrgID));
						}

					// Do bulk inserts in Base Env
					doBulkInserts(mainMBList, bOrgId, bOrgToken, bOrgURL,
							refreshToken);

					// Update Success message
					fdGetSFoAuthHandleService.setSfHandleToNUll();
					// updating metadataLog
					RDAppService.updateMetadataLogStatus(metadataLogDO,
							Constants.COMPLETED_STATUS,
							fdGetSFoAuthHandleService.getSFoAuthHandle(bOrgId,
									bOrgToken, bOrgURL, refreshToken,
									Constants.BaseOrgID));
					RDAppService.updateDeploymentDetails(metadataLogId,
							Constants.RETRIEVE_SUCESS_MESSAGE, metadataLogDO
									.getSourceOrgId(),
							fdGetSFoAuthHandleService.getSFoAuthHandle(bOrgId,
									bOrgToken, bOrgURL, refreshToken,
									Constants.BaseOrgID));
					// nullify connection
					fdGetSFoAuthHandleService.setSfHandleToNUll();
				} else {
					// Sleep for few sec to let status updated to "Processing"
					Thread.sleep(Constants.waitFor2Sec);
				}
			}
		} }catch (SFException e) {
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

	private List<MetaBean> getRetrieveObjListFromSource(String logName,
			SFoAuthHandle sfHandle) {
		SFoAuthHandle sfSourceHandle = null;
		FDGetSFoAuthHandleService fdGetSFoAuthHandleService = new FDGetSFoAuthHandleService();

		List<MetaBean> mainMBList = new ArrayList<MetaBean>();
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
		FDGetSFoAuthHandleService fdGetSFoAuthHandleService = new FDGetSFoAuthHandleService();

		int chunkCount = 0, rem = 0, start = 0, end = Constants.ChunkSize;

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
			CsvFileWriter.writeCsvFile(l, Constants.Retrieve_CSV_File);
			BulkInsertService bulkService = new BulkInsertService();
			
			bulkService.insertInto(Constants.MetadataDescription_Name,
					Constants.Retrieve_CSV_File, fdGetSFoAuthHandleService
							.getSFoAuthHandle(bOrgId, bOrgToken, bOrgURL,
									refreshToken, Constants.BaseOrgID));
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
