package com.services.component;

import java.util.Iterator;
import java.util.List;

import com.domain.EnvironmentDO;
import com.domain.MetadataLogDO;
import com.domain.ReleaseInformationDO;
import com.ds.salesforce.dao.comp.EnvironmentDAO;
import com.ds.salesforce.dao.comp.ReleaseInformationDAO;
import com.services.application.RDAppService;
import com.util.Constants;
import com.util.SFoAuthHandle;

public class FDReleaseCompService {

	public FDReleaseCompService() {
		super();

	}

	public void release(String bOrgId, String bOrgToken, String bOrgURL,
			String refreshToken, String releaseId, String releaseName,
			String releaseStatus, String metadataLogId) {
		MetadataLogDO metadataLogDO = null;
		SFoAuthHandle sfSourceHandle = null;
		SFoAuthHandle sfTargetHandle = null;
		FDGetSFoAuthHandleService fdGetSFoAuthHandleService = new FDGetSFoAuthHandleService();

		try {

			// Get Meta data Log details
			metadataLogDO = RDAppService.findMetadataLog(metadataLogId,
					fdGetSFoAuthHandleService.getSFoAuthHandle(bOrgId,
							bOrgToken, bOrgURL, refreshToken,
							Constants.BaseOrgID));
			// nullify connection
			fdGetSFoAuthHandleService.setSfHandleToNUll();

			if (metadataLogDO.getAction() != null
					&& (metadataLogDO.getAction().equals("Active"))) {

				// Prepare Releases Object
				ReleaseInformationDO riDO = new ReleaseInformationDO(releaseId,
						releaseName, releaseStatus);
				// Get All the Env Info
				EnvironmentDAO dao = new EnvironmentDAO();
				List<Object> envList = dao.listAll(fdGetSFoAuthHandleService
						.getSFoAuthHandle(bOrgId, bOrgToken, bOrgURL,
								refreshToken, Constants.BaseOrgID));
				// Create ReleaseOjects in all the Environments
				for (Iterator iterator = envList.iterator(); iterator.hasNext();) {

					try {

						EnvironmentDO envDO = (EnvironmentDO) iterator.next();
						System.out.println(envDO.getOrgId());
						if (envDO.getOrgId() != null) {

							ReleaseInformationDAO riDAO = new ReleaseInformationDAO();
							List<Object> releaseinfoList = riDAO
									.findByParentId(
											releaseId,
											fdGetSFoAuthHandleService
													.getSFoAuthHandle(
															envDO,
															Constants.CustomOrgID));

							if (releaseinfoList.size() > 0) {

								for (Iterator iterator3 = releaseinfoList
										.iterator(); iterator3.hasNext();) {
									ReleaseInformationDO riDo = (ReleaseInformationDO) iterator3
											.next();
									riDo.setStatus(Constants.RELEASE_STATUS_ACTIVE);

									riDAO.update(
											riDo,
											fdGetSFoAuthHandleService
													.getSFoAuthHandle(
															envDO,
															Constants.CustomOrgID));

								}
							} else {
								// up-date Release info each of Env
								(new ReleaseInformationDAO()).insert(riDO,
										fdGetSFoAuthHandleService
												.getSFoAuthHandle(envDO,
														Constants.CustomOrgID));
							}

						}
					} catch (Exception e) {
						// TODO: handle exception
					}
				}

				RDAppService.updateMetadataLogStatus(metadataLogDO,
						Constants.RELEASE_COMPLETED_STATUS,
						fdGetSFoAuthHandleService.getSFoAuthHandle(bOrgId,
								bOrgToken, bOrgURL, refreshToken,
								Constants.BaseOrgID));

			} else {

				if (metadataLogDO.getAction() != null
						&& (metadataLogDO.getAction().equals("InActive"))) {

					System.out.println("qeqweqweqw");
					EnvironmentDAO dao = new EnvironmentDAO();
					List<Object> envList = dao
							.listAll(fdGetSFoAuthHandleService
									.getSFoAuthHandle(bOrgId, bOrgToken,
											bOrgURL, refreshToken,
											Constants.BaseOrgID));
					for (Iterator iterator = envList.iterator(); iterator
							.hasNext();) {
						EnvironmentDO envDO = (EnvironmentDO) iterator.next();
						System.out.println(envDO.getOrgId());
						if (envDO.getOrgId() != null) {

							ReleaseInformationDAO riDAO = new ReleaseInformationDAO();
							List<Object> releaseinfoList = riDAO
									.findByParentId(
											releaseId,
											fdGetSFoAuthHandleService
													.getSFoAuthHandle(
															envDO,
															Constants.CustomOrgID));

							for (Iterator iterator2 = releaseinfoList
									.iterator(); iterator2.hasNext();) {
								ReleaseInformationDO riDo = (ReleaseInformationDO) iterator2
										.next();
								riDo.setStatus(Constants.RELEASE_STATUS_INACTIVE);

								riDAO.update(riDo, fdGetSFoAuthHandleService
										.getSFoAuthHandle(envDO,
												Constants.CustomOrgID));

							}

						}

					}
					RDAppService.updateMetadataLogStatus(metadataLogDO,
							Constants.RELEASE_COMPLETED_STATUS,
							fdGetSFoAuthHandleService.getSFoAuthHandle(bOrgId,
									bOrgToken, bOrgURL, refreshToken,
									Constants.BaseOrgID));
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}
}
