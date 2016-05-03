package com.services.application.getpackage;

import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.domain.EnvironmentDO;
import com.domain.ReleaseInformationDO;
import com.domain.ReleasesDO;
import com.ds.salesforce.dao.comp.ReleasesDAO;
import com.exception.SFErrorCodes;
import com.exception.SFException;
import com.request.deploy.GetPackageRequest;
import com.request.env.EnvRequest;
import com.request.packages.DeletePackageRequest;
import com.request.pkginfo.PackageInfoRequest;
import com.request.releaseinformation.ReleaseInformationRequest;
import com.response.deploy.GetPackageResponse;
import com.response.env.EnvResponse;
import com.response.packages.DeletePackageResponse;
import com.response.pkginfo.PackageInfoResponse;
import com.response.releaseinformation.ReleaseInformationResponse;
import com.services.application.RDAppService;
import com.services.component.FDGetSFoAuthHandleService;
import com.services.component.env.EnvService;
import com.services.component.packages.DeletePackageService;
import com.services.component.pkginfo.GetPkgInfoListService;
import com.services.component.pkginfo.LinkPkgInfoWithRelInfoService;
import com.services.component.pkginfo.UpsertPkgInfoService;
import com.services.component.releaseinformation.ReleaseInformationService;
import com.util.Constants;
import com.util.Org;

public class GetPackageAppService {

	GetPackageRequest request;
	GetPackageResponse response;

	private static final Logger LOG = LoggerFactory
			.getLogger(GetPackageAppService.class);

	public GetPackageAppService() {
		super();
	}

	public GetPackageAppService(GetPackageRequest request,
			GetPackageResponse response) {
		super();
		this.request = request;
		this.response = response;
	}

	public boolean deleteExistingPackages() {
		// Deleting Packages From BaseOrg
		DeletePackageRequest deletePkgReq = new DeletePackageRequest(
				getRequest().getReleaseParentId(), getRequest().getOrg());
		DeletePackageResponse deletePkgRes = new DeletePackageResponse(
				getResponse().getProcessTrack());

		DeletePackageService deletePkgService = new DeletePackageService(
				deletePkgReq, deletePkgRes);
		deletePkgService.processDeletePackage();
		if (getResponse().getProcessTrack().getCurrentStatus()
				.equals(Constants.SUCCES_STATUS)) {
			return true;
		} else {
			return false;
		}
	}

	public List<Object> getAllEnv() {
		// get all the envs
		EnvRequest envRequest = new EnvRequest(getRequest().getOrg());
		EnvResponse envResponse = new EnvResponse(getResponse()
				.getProcessTrack());
		EnvService envService = new EnvService(envRequest, envResponse);
		envService.processToGetAllEnv();
		if (getResponse().getProcessTrack().getCurrentStatus()
				.equals(Constants.SUCCES_STATUS)) {
			if (envResponse.getEnvList() != null
					&& !envResponse.getEnvList().isEmpty()) {
				return envResponse.getEnvList();
			}
		}
		return null;
	}

	public List<Object> getReleaseInfoList(EnvironmentDO envDO) {
		ReleaseInformationRequest relInfoRequest = new ReleaseInformationRequest(
				getRequest().getReleaseParentId(), envDO);
		ReleaseInformationResponse relInfoResponse = new ReleaseInformationResponse(
				getResponse().getProcessTrack());

		ReleaseInformationService relInfoService = new ReleaseInformationService(
				relInfoRequest, relInfoResponse);
		relInfoService.processToGetRelInfoList();
		if (getResponse().getProcessTrack().getCurrentStatus()
				.equals(Constants.SUCCES_STATUS)) {
			if (relInfoResponse.getRelInfoList() != null
					&& !relInfoResponse.getRelInfoList().isEmpty()) {
				return relInfoResponse.getRelInfoList();
			}
		}
		return null;
	}

	public List<Object> getPackageInfoList(ReleaseInformationDO rinfoDO,
			EnvironmentDO envDO) {

		if (rinfoDO != null) {
			PackageInfoRequest pkgInfoRequest = new PackageInfoRequest(rinfoDO,
					envDO);
			PackageInfoResponse pkgInfoResponse = new PackageInfoResponse(
					getResponse().getProcessTrack());
			GetPkgInfoListService getPkgInfoListService = new GetPkgInfoListService(
					pkgInfoRequest, pkgInfoResponse);
			getPkgInfoListService.processPkgInfoList();
			if (getResponse().getProcessTrack().getCurrentStatus()
					.equals(Constants.SUCCES_STATUS)) {
				if (pkgInfoResponse.getPackageInfoList() != null
						&& !pkgInfoResponse.getPackageInfoList().isEmpty()) {
					return pkgInfoResponse.getPackageInfoList();
				}
			}
		}
		return null;
	}

	public List<String> upsertPkgInfoList(List<Object> pkgInfoList,
			EnvironmentDO envDO) {
		if (pkgInfoList != null) {
			Org org = getRequest().getOrg();
			System.out.println(org.getOrgType());
			PackageInfoRequest pkgInfoRequest = new PackageInfoRequest(getRequest().getOrg(),
					pkgInfoList, envDO);
			PackageInfoResponse pkgInfoResponse = new PackageInfoResponse(
					getResponse().getProcessTrack());
			UpsertPkgInfoService upsertPkgService = new UpsertPkgInfoService(
					pkgInfoRequest, pkgInfoResponse);
			upsertPkgService.processUpsertPkgInfoList();
			if (getResponse().getProcessTrack().getCurrentStatus()
					.equals(Constants.SUCCES_STATUS)) {
				return pkgInfoResponse.getPidList();
			}
		}

		return null;
	}

	public boolean linkPkgInfoWithReleaseInfo(List<String> pidList,
			EnvironmentDO envDO, String releaseId) {
		if (pidList != null && !pidList.isEmpty()) {
			PackageInfoRequest pkgInfoRequest = new PackageInfoRequest(getRequest().getOrg(), pidList,
					envDO, releaseId);
			PackageInfoResponse pkgInfoResponse = new PackageInfoResponse(
					getResponse().getProcessTrack());

			LinkPkgInfoWithRelInfoService relPkgService = new com.services.component.pkginfo.LinkPkgInfoWithRelInfoService(
					pkgInfoRequest, pkgInfoResponse);
			relPkgService.processLinkRelWithPkgInfo();
			if (getResponse().getProcessTrack().getCurrentStatus()
					.equals(Constants.SUCCES_STATUS)) {
				return true;
			}
		}
		return false;
	}

	public void doPostProcess() {
		FDGetSFoAuthHandleService fdGetSFoAuthHandleService = new FDGetSFoAuthHandleService();

		try {
			RDAppService.updateToComplete(getRequest().getReqId(),
					Constants.COMPLETED_STATUS, getRequest().getOrg());

			ReleasesDAO rDAO = new ReleasesDAO();
			List<Object> release = rDAO.findById(getRequest()
					.getReleaseParentId(), fdGetSFoAuthHandleService
					.getSFoAuthHandle(getRequest().getOrg()));
			if (release.size() > 0) {

				for (Iterator releaseIterator = release.iterator(); releaseIterator
						.hasNext();) {
					ReleasesDO releasesDO = (ReleasesDO) releaseIterator.next();
					releasesDO.setStatus("Package Retrieved");
					rDAO.update(releasesDO, fdGetSFoAuthHandleService
							.getSFoAuthHandle(getRequest().getOrg()));

				}

			}
		} catch (SFException e) {
			throw new SFException(e.toString(),
					SFErrorCodes.SFDeployDetails_Update_Error);
		}
	}

	public void initiate() {
		if (deleteExistingPackages()) {
			List<Object> envList = getAllEnv();

			if (envList != null) {
				if (envList != null && !envList.isEmpty()) {
					for (Iterator<Object> iterator = envList.iterator(); iterator
							.hasNext();) {
						EnvironmentDO envDO = (EnvironmentDO) iterator.next();
						if (envDO.getOrgId() != null) {
							// Get the ReleaseInformation in the each of target
							// environments.
							List<Object> relInfoList = getReleaseInfoList(envDO);
							if (relInfoList != null && !relInfoList.isEmpty()) {
								for (Iterator<Object> iterator2 = relInfoList
										.iterator(); iterator2.hasNext();) {
									ReleaseInformationDO relInfoDO = (ReleaseInformationDO) iterator2
											.next();
									List<Object> pkgInfoList = getPackageInfoList(
											relInfoDO, envDO);

									// upsert packageInfo List in specified
									// envDO
									List<String> pidList = upsertPkgInfoList(
											pkgInfoList, envDO);

									// link packageInfo objects with release
									linkPkgInfoWithReleaseInfo(pidList, envDO,
											getRequest().getReleaseParentId());
								}
							}
						}
					}
				}
			}
			// find ReleaseOjects in all the Environments
		}

	}

	public GetPackageRequest getRequest() {
		return request;
	}

	public void setRequest(GetPackageRequest request) {
		this.request = request;
	}

	public GetPackageResponse getResponse() {
		return response;
	}

	public void setResponse(GetPackageResponse response) {
		this.response = response;
	}

}
