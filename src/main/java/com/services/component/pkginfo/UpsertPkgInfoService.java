package com.services.component.pkginfo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.domain.PackageInformationDO;
import com.ds.salesforce.dao.comp.PackageInformationDAO;
import com.request.packages.CreatePackageRequest;
import com.request.pkginfo.PackageInfoRequest;
import com.response.packages.CreatePackageResponse;
import com.response.pkginfo.PackageInfoResponse;
import com.services.BaseService;
import com.services.component.FDGetSFoAuthHandleService;
import com.services.component.packages.CreatePackageService;
import com.util.Constants;

public class UpsertPkgInfoService extends BaseService {

	PackageInfoRequest request;
	PackageInfoResponse response;

	public UpsertPkgInfoService(PackageInfoRequest request,
			PackageInfoResponse response) {
		this.request = request;
		this.response = response;
	}

	public PackageInfoRequest getRequest() {
		return request;
	}

	public void setRequest(PackageInfoRequest request) {
		this.request = request;
	}

	public PackageInfoResponse getResponse() {
		return response;
	}

	public void setResponse(PackageInfoResponse response) {
		this.response = response;
	}

	public void processUpsertPkgInfo() {
		PackageInformationDO pkgInfoDO = (PackageInformationDO) getRequest()
				.getPkgInfoDO();
		FDGetSFoAuthHandleService fdGetSFoAuthHandleService = new FDGetSFoAuthHandleService();

		if (pkgInfoDO.getReadyForDeployment() != null
				&& pkgInfoDO.getReadyForDeployment().booleanValue()) {
			PackageInformationDAO pkgInfoDAO = new PackageInformationDAO();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			pkgInfoDO.setCalendar(calendar);
			System.out.println(pkgInfoDO.getCalendar().getTime());

			pkgInfoDAO.updatePackageRetrievedTime(pkgInfoDO,
					fdGetSFoAuthHandleService.getSFoAuthHandle(getRequest()
							.getEnvDO(), Constants.CustomOrgID));

			CreatePackageRequest createPkgRequest = new CreatePackageRequest(
					getRequest().getOrg());
			CreatePackageResponse createPkgResponse = new CreatePackageResponse(
					getResponse().getProcessTrack());
			CreatePackageService createPkgService = new CreatePackageService(
					createPkgRequest, createPkgResponse);
			createPkgService.createPackageInformation();
		}
	}

	public void processUpsertPkgInfoList() {
		List<Object> pkgInfoList = getRequest().getPkgInfoList();
		List<String> pidList = new ArrayList<String>();
		FDGetSFoAuthHandleService fdGetSFoAuthHandleService = new FDGetSFoAuthHandleService();

		for (Iterator<Object> iterator = pkgInfoList.iterator(); iterator.hasNext();) {
			PackageInformationDO pkgInfoDO = (PackageInformationDO) iterator
					.next();
			if (pkgInfoDO.getReadyForDeployment() != null
					&& pkgInfoDO.getReadyForDeployment().booleanValue()) {
				PackageInformationDAO pkgInfoDAO = new PackageInformationDAO();
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(new Date());
				pkgInfoDO.setCalendar(calendar);
				System.out.println(pkgInfoDO.getCalendar().getTime());

				pkgInfoDAO.updatePackageRetrievedTime(pkgInfoDO,
						fdGetSFoAuthHandleService.getSFoAuthHandle(getRequest()
								.getEnvDO(), Constants.CustomOrgID));

				CreatePackageRequest createPkgRequest = new CreatePackageRequest(request.getOrg(), pkgInfoDO, fdGetSFoAuthHandleService.getSFoAuthHandle(getRequest()
						.getEnvDO(), Constants.CustomOrgID));
				CreatePackageResponse createPkgResponse = new CreatePackageResponse(
						getResponse().getProcessTrack());
				CreatePackageService createPkgService = new CreatePackageService(
						createPkgRequest, createPkgResponse);
				createPkgService.createPackageInformation();
				pidList.add(createPkgResponse.getPackageId());
			}
		}
		getResponse().setPidList(pidList);
	}
}
