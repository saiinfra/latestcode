package com.services.application;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.domain.EnvironmentDO;
import com.domain.EnvironmentInformationDO;
import com.domain.MetadataLogInformationDO;
import com.domain.PackageDO;
import com.domain.PackageInformationDO;
import com.domain.ReleaseInformationDO;
import com.domain.ReleasePackageDO;
import com.ds.salesforce.dao.comp.EnvironmentDAO;
import com.ds.salesforce.dao.comp.EnvironmentInformationDAO;
import com.ds.salesforce.dao.comp.PackageDAO;
import com.ds.salesforce.dao.comp.PackageInformationDAO;
import com.ds.salesforce.dao.comp.ReleaseInformationDAO;
import com.ds.salesforce.dao.comp.ReleasePackageDAO;
import com.services.component.FDGetSFoAuthHandleService;
import com.services.component.release.CreatePackage;
import com.services.component.release.CreatePackageComp;
import com.services.component.release.GetPkgCompList;
import com.util.Constants;
import com.util.Org;
import com.util.SFoAuthHandle;

public class SubmitForApprovalService {
	Org bOrg;
	Org sOrg;
	Org tOrg;
	String status;
	String pkgId;
	String metadataLogId;

	public SubmitForApprovalService(Org bOrg, Org sOrg, String status,
			String pkgId, String metadataLogId) {
		super();
		this.bOrg = bOrg;
		this.sOrg = sOrg;

		this.status = status;
		this.pkgId = pkgId;
		this.metadataLogId = metadataLogId;
	}

	/*
	 * public void delPkgInfoListFromBase() { if (getsOrg() != null) {
	 * ReleaseInformationDAO riDAO = new ReleaseInformationDAO(); // Get the
	 * ReleaseInformation in the client environments. List<Object> relInfoList =
	 * riDAO.findByParentId(getReleaseId(),
	 * FDGetSFoAuthHandleService.getSFoAuthHandle(getsOrg()));
	 * 
	 * for (Iterator iterator = relInfoList.iterator(); iterator.hasNext();) {
	 * EnvironmentDO envDO = new EnvironmentDO(getsOrg().getOrgId(),
	 * getsOrg().getOrgToken(), getsOrg().getOrgURL(), "",
	 * getsOrg().getRefreshToken()); List<Object> pkgInfoList = (new
	 * GetPkgInfoList(relInfoList, envDO)).getList(); for (Iterator iterator2 =
	 * pkgInfoList.iterator(); iterator2 .hasNext();) { PackageInformationDO
	 * pkgInfoDO = (PackageInformationDO) iterator2 .next(); SFoAuthHandle
	 * sfhandle = FDGetSFoAuthHandleService .getSFoAuthHandle(gettOrg());
	 * delPkgInBase(pkgInfoDO, sfhandle); } } } }
	 */
	public void delPkgInBase(PackageInformationDO pkgInfoDO,
			SFoAuthHandle sfhandle) {
		// Deleting Packages From BaseOrg
		String[] ids = new String[1];

		PackageDAO pkgDAO = new PackageDAO();
		List<Object> pkgList = pkgDAO.findByParentId(pkgInfoDO.getId(),
				sfhandle);
		if (pkgList.size() > 0) {
			for (Iterator iterator = pkgList.iterator(); iterator.hasNext();) {
				PackageDO packageDO = (PackageDO) iterator.next();
				ids[0] = packageDO.getId();
				/*
				 * ReleasePackageDAO releasePackageDAO = new
				 * ReleasePackageDAO(); List<Object> releasePackageList =
				 * releasePackageDAO .findByPkgIDAndRID(ids[0],
				 * pkgInfoDO.getReleaseInformationId(), sfhandle);
				 * 
				 * for (Iterator iterator2 = releasePackageList.iterator();
				 * iterator2 .hasNext();) { ReleaseInformationDO object =
				 * (ReleaseInformationDO) iterator2.next();
				 * releasePackageDAO.delete(object, sfhandle);
				 * 
				 * }
				 */

				// releasePackageDAO.delete(obj, sfHandle)
				pkgDAO.deleteRecords(ids, sfhandle);

			}
			// delete Packages
		}
	}

	public String getMetadataLogId() {
		return metadataLogId;
	}

	public void setMetadataLogId(String metadataLogId) {
		this.metadataLogId = metadataLogId;
	}

	public void initiate() {
		// delete packages from Base
		FDGetSFoAuthHandleService fdGetSFoAuthHandleService = new FDGetSFoAuthHandleService();

		MetadataLogInformationDO metadataLogInformationDO = null;
		EnvironmentDO bEnvDO = new EnvironmentDO(getbOrg().getOrgId(),
				getbOrg().getOrgToken(), getbOrg().getOrgURL(), "", getbOrg()
						.getRefreshToken());
		EnvironmentDO sEnvDO = new EnvironmentDO(getsOrg().getOrgId(),
				getsOrg().getOrgToken(), getsOrg().getOrgURL(), "", getsOrg()
						.getRefreshToken());

		
		
		EnvironmentDAO environmentDAO = new EnvironmentDAO();
		List<Object> lst1=environmentDAO.findById(getbOrg().getOrgId(),
				fdGetSFoAuthHandleService.getSFoAuthHandle(sEnvDO,
						Constants.CustomBaseOrgID));
		EnvironmentDO object1=null;
		for (Iterator iterator = lst1.iterator(); iterator.hasNext();) {
			object1 = (EnvironmentDO) iterator.next();
			System.out.println("----" +object1.getId());
			
		}

		try {
			// Get the ReleaseInformation in the each of Source environments.
			PackageInformationDAO pkgInfoDAO = new PackageInformationDAO();

			List<Object> pkgInfoList = pkgInfoDAO.findById(pkgId,
					fdGetSFoAuthHandleService.getSFoAuthHandle(bEnvDO,
							Constants.CustomBaseOrgID));

			for (Iterator<Object> iterator2 = pkgInfoList.iterator(); iterator2
					.hasNext();) {
				PackageInformationDO pkgInfoDO = (PackageInformationDO) iterator2
						.next();
				if (pkgInfoDO.getReadyForDeployment() != null
						&& pkgInfoDO.getReadyForDeployment().booleanValue()) {

					Calendar calendar = Calendar.getInstance();
					calendar.setTime(new Date());
					pkgInfoDO.setCalendar(calendar);
					System.out.println(pkgInfoDO.getCalendar().getTime());

					pkgInfoDAO.updatePackageRetrievedTime(pkgInfoDO,
							fdGetSFoAuthHandleService.getSFoAuthHandle(bEnvDO,

							Constants.CustomBaseOrgID));
					delPkgInBase(pkgInfoDO,
							fdGetSFoAuthHandleService.getSFoAuthHandle(sEnvDO,
									Constants.CustomBaseOrgID));

					String pid = "";
					try {
						pid = (new CreatePackage(getsOrg())).create(pkgInfoDO,
								fdGetSFoAuthHandleService.getSFoAuthHandle(
										sEnvDO, Constants.CustomBaseOrgID));
					} catch (Exception e) {
						e.printStackTrace();
					}

					List<Object> pkgCompList = (new GetPkgCompList(bEnvDO,
							pkgInfoDO.getId())).getListClient();

					(new CreatePackageComp(getsOrg(), pkgCompList)).create(pid,
							fdGetSFoAuthHandleService.getSFoAuthHandle(sEnvDO,
									Constants.CustomBaseOrgID));
					ReleaseInformationDAO releaseInfoDAO = new ReleaseInformationDAO();
					List<Object> lst = releaseInfoDAO.findById(pkgInfoDO
							.getReleaseInformationId(),
							fdGetSFoAuthHandleService.getSFoAuthHandle(bEnvDO,
									Constants.CustomBaseOrgID));
					for (Iterator iterator = lst.iterator(); iterator.hasNext();) {
						ReleaseInformationDO object = (ReleaseInformationDO) iterator
								.next();
						ReleasePackageDAO relPkgDAO = new ReleasePackageDAO();

						// System.out.println("Source ID" +
						// environmentInformationDO.getId());

						ReleasePackageDO relPkgDO = new ReleasePackageDO("1",
								pid, object.getParentReleaseID(),
								object1.getId());

						String pkgId = relPkgDAO.insertAndGetId(relPkgDO,
								fdGetSFoAuthHandleService.getSFoAuthHandle(
										sEnvDO, Constants.CustomBaseOrgID));
						metadataLogInformationDO = RDAppService
								.findMetadataLogInformation(
										getMetadataLogId(),
										fdGetSFoAuthHandleService
												.getSFoAuthHandle(
														bEnvDO,
														Constants.CustomBaseOrgID));
						RDAppService.updateMetadataLogInformationStatus(
								metadataLogInformationDO,
								Constants.COMPLETED_STATUS,
								fdGetSFoAuthHandleService.getSFoAuthHandle(
										bEnvDO, Constants.CustomBaseOrgID));

					}

				}
			}
		} catch (Exception e) {
			RDAppService.updateMetadataLogInformationStatus(
					metadataLogInformationDO, Constants.ERROR_STATUS,
					fdGetSFoAuthHandleService.getSFoAuthHandle(bEnvDO,
							Constants.CustomBaseOrgID));

			// updating Deploy Details Information
			RDAppService.updateDeploymentDetailsInformation(metadataLogId, e
					.getMessage(), metadataLogInformationDO.getSourceOrgId(),
					fdGetSFoAuthHandleService.getSFoAuthHandle(bEnvDO,
							Constants.CustomBaseOrgID));

		}
	}

	public Org getsOrg() {
		return sOrg;
	}

	public void setsOrg(Org sOrg) {
		this.sOrg = sOrg;
	}

	public Org gettOrg() {
		return tOrg;
	}

	public void settOrg(Org tOrg) {
		this.tOrg = tOrg;
	}

	public Org getbOrg() {
		return bOrg;
	}

	public void setbOrg(Org bOrg) {
		this.bOrg = bOrg;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPkgId() {
		return pkgId;
	}

	public void setPkgId(String pkgId) {
		this.pkgId = pkgId;
	}

}
