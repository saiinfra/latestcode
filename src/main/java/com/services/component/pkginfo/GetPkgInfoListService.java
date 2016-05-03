package com.services.component.pkginfo;

import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.domain.EnvironmentDO;
import com.domain.ReleaseInformationDO;
import com.ds.salesforce.dao.comp.PackageInformationDAO;
import com.request.pkginfo.PackageInfoRequest;
import com.response.pkginfo.PackageInfoResponse;
import com.services.BaseService;
import com.services.component.FDGetSFoAuthHandleService;
import com.util.Constants;

public class GetPkgInfoListService extends BaseService {
	PackageInfoRequest request;
	PackageInfoResponse response;

	public GetPkgInfoListService(PackageInfoRequest request,
			PackageInfoResponse response) {
		super();
		this.request = request;
		this.response = response;
	}

	public void processPkgInfoList() {
		// get PackageInformation object for specific release information
		List<Object> packageInfoList = null;
		FDGetSFoAuthHandleService fdGetSFoAuthHandleService = new FDGetSFoAuthHandleService();

		ReleaseInformationDO rInfoDO = (ReleaseInformationDO) getRequest()
				.getRelInfoDO();

		System.out.println("ReleaseId  :" + rInfoDO.getId());
		PackageInformationDAO pkginfoDAO = new PackageInformationDAO();

		// find all the packageInformationInfo list associated
		// with ReleaseInformation

		packageInfoList = pkginfoDAO.findByReleaseId(rInfoDO.getId(),
				fdGetSFoAuthHandleService.getSFoAuthHandle(getRequest()
						.getEnvDO(), Constants.CustomOrgID));

		getResponse().setPackageInfoList(packageInfoList);

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
}
