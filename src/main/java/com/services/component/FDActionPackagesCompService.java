package com.services.component;

import java.util.Iterator;
import java.util.List;

import com.domain.EnvironmentDO;
import com.domain.MetadataLogDO;
import com.domain.PackageCompInfoDO;
import com.domain.PackageComponentDO;
import com.domain.PackageDO;
import com.domain.PackageInformationDO;
import com.domain.ReleaseInformationDO;
import com.domain.ReleasePackageDO;
import com.ds.salesforce.dao.comp.EnvironmentDAO;
import com.ds.salesforce.dao.comp.PackageCompInfoDAO;
import com.ds.salesforce.dao.comp.PackageComponentDAO;
import com.ds.salesforce.dao.comp.PackageDAO;
import com.ds.salesforce.dao.comp.PackageInformationDAO;
import com.ds.salesforce.dao.comp.ReleaseInformationDAO;
import com.ds.salesforce.dao.comp.ReleasePackageDAO;
import com.exception.SFException;
import com.services.application.RDAppService;
import com.thoughtworks.selenium.webdriven.commands.GetBodyText;
import com.util.Constants;

public class FDActionPackagesCompService {

	public FDActionPackagesCompService() {
		super();
	}

	public void getPackages(String bOrgId, String bOrgToken, String bOrgURL,
			String refreshToken, String releaseId, String releaseName,
			String releaseStatus, String metadataLogId) {

		MetadataLogDO metadataLogDO = null;
		FDGetSFoAuthHandleService fdGetSFoAuthHandleService = new FDGetSFoAuthHandleService();

		// Prepare Package Object
		ReleaseInformationDO riDO = new ReleaseInformationDO(releaseId,
				releaseName, releaseStatus);
		// Get All the Env Info
		EnvironmentDAO dao = new EnvironmentDAO();
		List<Object> envList = dao.listAll(fdGetSFoAuthHandleService
				.getSFoAuthHandle(bOrgId, bOrgToken, bOrgURL, refreshToken,
						Constants.BaseOrgID));

		// Create ReleaseOjects in all the Environments
		for (Iterator iterator = envList.iterator(); iterator.hasNext();) {
			EnvironmentDO envDO = (EnvironmentDO) iterator.next();
			System.out.println(envDO.getOrgId());
			if (envDO.getOrgId() != null) {
				ReleaseInformationDAO riDAO = new ReleaseInformationDAO();
				// Get the ReleaseInformation in the each of target
				// environments.
				List<Object> riList = riDAO.findByParentId(releaseId,
						fdGetSFoAuthHandleService.getSFoAuthHandle(envDO,
								Constants.CustomOrgID));

				// verify if release status is active / inactive
				boolean flag = false;
				for (Iterator iterator2 = riList.iterator(); iterator2
						.hasNext();) {
					ReleaseInformationDO rInfoDO = (ReleaseInformationDO) iterator2
							.next();
					System.out.println("ReleaseParentId  :" + rInfoDO.getId());
					if (rInfoDO.getStatus().equalsIgnoreCase(
							Constants.RELEASE_STATUS_INACTIVE)) {
						flag = false;
					} else {
						flag = true;
					}

				}
				// if active
				if (flag) {
					// Create Package Name
					String pkgName = "PKG_" + envDO.getOrgId() + "_"
							+ releaseName;
					PackageDO pkgDO = new PackageDO(pkgName, "Description", "");
					PackageDAO PkgDAO = new PackageDAO();

					// Find if the package is existing in the base org
					// ideally this should be avoided
					List<Object> pkgNameList = PkgDAO.findByNameId(pkgName,
							fdGetSFoAuthHandleService.getSFoAuthHandle(bOrgId,
									bOrgToken, bOrgURL, refreshToken,
									Constants.BaseOrgID));
					String pid = "";
					// if exists, get the existing package id.
					if (pkgNameList.size() > 0) {
						for (Iterator iterator2 = pkgNameList.iterator(); iterator2
								.hasNext();) {
							PackageDO objDO = (PackageDO) iterator2.next();
							pid = objDO.getId();
						}
					}
					// else create a new package in base org and hold the key.
					else {
						pid = PkgDAO.insertAndGetId(pkgDO,
								fdGetSFoAuthHandleService.getSFoAuthHandle(
										bOrgId, bOrgToken, bOrgURL,
										refreshToken, Constants.BaseOrgID));
					}

					// Iterate thru the release information list in the target
					// env
					for (Iterator iterator2 = riList.iterator(); iterator2
							.hasNext();) {
						ReleaseInformationDO rInfoDO = (ReleaseInformationDO) iterator2
								.next();
						System.out.println("ReleaseParentId  :"
								+ rInfoDO.getId());
						PackageInformationDAO pkginfoDAO = new PackageInformationDAO();

						// find all the packageInformationInfo list associated
						// with ReleaseInformation
						List<Object> PackageInfoList = pkginfoDAO
								.findByReleaseId(rInfoDO.getId(),
										fdGetSFoAuthHandleService
												.getSFoAuthHandle(envDO,
														Constants.CustomOrgID));
						for (Iterator iterator3 = PackageInfoList.iterator(); iterator3
								.hasNext();) {
							PackageInformationDO pkginfoDO = (PackageInformationDO) iterator3
									.next();
							System.out.println("ParentPackageInfoID : "
									+ pkginfoDO.getId());
							PackageCompInfoDAO pkgCompInfoDAO = new PackageCompInfoDAO();

							// find the PackageComponents associated with
							// PackageInformation
							List<Object> PackageCompList = pkgCompInfoDAO
									.findByPackageId(
											pkginfoDO.getId(),
											fdGetSFoAuthHandleService
													.getSFoAuthHandle(
															envDO,
															Constants.CustomOrgID));

							System.out.println("Package Id :" + pkgDO.getId()
									+ "--- pid:  " + pid);

							// Finally Iterate through PackageComponent List
							// Create PackageComponent objects in Base Org
							// set the earlier created Package id to these
							// PackageComponents
							for (Iterator iterator4 = PackageCompList
									.iterator(); iterator4.hasNext();) {

								PackageCompInfoDO pkgCompInfoDO = (PackageCompInfoDO) iterator4
										.next();

								PackageComponentDO pkgCompDO = pkgCompInfoDO
										.getPkgCompDO();

								pkgCompDO.setPkgParentId(pid);
								// object.setPkgInfoParentId(pkgCompInfoDO.getId());

								PackageComponentDAO pkgCompDAO = new PackageComponentDAO();
								pkgCompDAO.insert(pkgCompDO,
										fdGetSFoAuthHandleService
												.getSFoAuthHandle(bOrgId,
														bOrgToken, bOrgURL,
														refreshToken,
														Constants.BaseOrgID));

								try {
									metadataLogDO = RDAppService
											.findMetadataLog(
													metadataLogId,
													fdGetSFoAuthHandleService
															.getSFoAuthHandle(
																	bOrgId,
																	bOrgToken,
																	bOrgURL,
																	refreshToken,
																	Constants.BaseOrgID));
									// nullify connection
									fdGetSFoAuthHandleService
											.setSfHandleToNUll();

									// updating metadataLog to prcessing state
									RDAppService
											.updateMetadataLogStatus(
													metadataLogDO,
													Constants.COMPLETED_STATUS,
													fdGetSFoAuthHandleService
															.getSFoAuthHandle(
																	bOrgId,
																	bOrgToken,
																	bOrgURL,
																	refreshToken,
																	Constants.BaseOrgID));

								} catch (SFException e) {
									if (metadataLogDO != null) {
										// refresh connection
										fdGetSFoAuthHandleService
												.setSfHandleToNUll();
										// updating metadataLog
										RDAppService
												.updateMetadataLogStatus(
														metadataLogDO,
														Constants.ERROR_STATUS,
														fdGetSFoAuthHandleService
																.getSFoAuthHandle(
																		bOrgId,
																		bOrgToken,
																		bOrgURL,
																		refreshToken,
																		Constants.BaseOrgID));

										// refresh connection
										fdGetSFoAuthHandleService
												.setSfHandleToNUll();
										// updating Deploy Details
										RDAppService
												.updateDeploymentDetails(
														metadataLogId,
														e.getMessage(),
														metadataLogDO
																.getSourceOrgId(),
														fdGetSFoAuthHandleService
																.getSFoAuthHandle(
																		bOrgId,
																		bOrgToken,
																		bOrgURL,
																		refreshToken,
																		Constants.BaseOrgID));
										// refresh connection
										fdGetSFoAuthHandleService
												.setSfHandleToNUll();
									} else {
										System.out
												.println("Salesforce Exception "
														+ e.getMessage());
									}
								}

							}

						}
					}
					// Associate package with release
					ReleasePackageDAO relPkgDAO = new ReleasePackageDAO();
					ReleasePackageDO relPkgDO = new ReleasePackageDO("1", pid,
							releaseId,null);

					String pkgId = relPkgDAO.insertAndGetId(relPkgDO,
							fdGetSFoAuthHandleService.getSFoAuthHandle(bOrgId,
									bOrgToken, bOrgURL, refreshToken,
									Constants.BaseOrgID));

				} else {
					// Need to handle this case
				}
			}
		}

	}
}
